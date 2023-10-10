package com.example.pigolevmyapplication.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pigolevmyapplication.data.entity.Film
import com.example.pigolevmyapplication.databinding.FragmentHomeBinding
import com.example.pigolevmyapplication.utils.AnimationHelper
import com.example.pigolevmyapplication.utils.AutoDisposable
import com.example.pigolevmyapplication.utils.addTo
import com.example.pigolevmyapplication.view.MainActivity
import com.example.pigolevmyapplication.view.rv_adapters.FilmListRecyclerAdapter
import com.example.pigolevmyapplication.view.rv_adapters.TopSpacingItemDecoration
import com.example.pigolevmyapplication.viewmodel.HomeFragmentViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*


class HomeFragment : Fragment() {
    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(HomeFragmentViewModel::class.java)
    }
    private lateinit var binding: FragmentHomeBinding
    private lateinit var filmsAdapter: FilmListRecyclerAdapter
    private val autoDisposable = AutoDisposable()

    private var filmsDataBase = mutableListOf<Film>()


        //Используем backing field
       set(value) {
            //Если придет такое же значение, то мы выходим из метода
            if (field == value) return
            //Если пришло другое значение, то кладем его в переменную
            field = value
            //Обновляем RV адаптер
            filmsAdapter.updateItems(field)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        autoDisposable.bindTo(lifecycle)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)
        binding.mainRecycler.apply {
            filmsAdapter =
                FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {
                    override fun click(film: Film) {
                        (requireActivity() as MainActivity).launchDetailsFragment(film)
                    }
                })
            adapter = filmsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)


            viewModel.filmsListData
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { list ->
                    filmsAdapter.addItems(list)
                    filmsDataBase = list
                }
                .addTo(autoDisposable)
            viewModel.showProgressBar
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    binding.progressBar.isVisible = it
                }
                .addTo(autoDisposable)
        }





        searchViewInit(binding)

        binding.mainRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            var canScrollUp = false
            var canScrollDown = false
            var isLoaded = false

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val tempLayoutManager = binding.mainRecycler.layoutManager as LinearLayoutManager
                val visibleItemCount: Int = tempLayoutManager.childCount
                val totalItemCount: Int = tempLayoutManager.itemCount
                val firstVisibleItemPosition: Int = tempLayoutManager.findFirstVisibleItemPosition()
                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                 //Вызываем загрузку следующей страницы при достижении конца списка
                    && firstVisibleItemPosition >= 0) {
                    viewModel.nextPaqe()
                }
                if (firstVisibleItemPosition == 0 && !canScrollUp && !isLoaded){
                    //Вызываем загрузку предыдущей страницы при достижении начала списка
                   viewModel.previousPage()
                    isLoaded = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                canScrollUp = binding.mainRecycler.canScrollVertically(-1)
                canScrollDown = binding.mainRecycler.canScrollVertically(1)
                if (dy!=0) isLoaded = false
                }
            }
        )
        AnimationHelper.performFragmentCircularRevealAnimation(binding.homeFragmentRoot, requireActivity(), 1)


    }



    private fun searchViewInit(binding: FragmentHomeBinding) {
        binding.searchView.setOnClickListener {
            binding.searchView.isIconified = false
        }
        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText?.isEmpty() == true) {
                    filmsAdapter.updateItems(filmsDataBase.toMutableList())
                    return true
                }
                val result = filmsDataBase.filter {

                    it.title.lowercase(Locale.getDefault()).contains(
                        newText?.lowercase(Locale.getDefault())!!
                    )
                }
                filmsAdapter.updateItems(result.toMutableList())
                return true
            }
        })
    }

    private fun initPullToRefresh() {
        //Вешаем слушатель, чтобы вызвался pull to refresh
        binding.pullToRefresh.setOnRefreshListener {
            //Чистим адаптер(items нужно будет сделать паблик или создать для этого публичный метод)
            filmsAdapter.items.clear()
            //Делаем новый запрос фильмов на сервер
            viewModel.getFilms()
            //Убираем крутящееся колечко
            binding.pullToRefresh.isRefreshing = false
        }
    }
}

















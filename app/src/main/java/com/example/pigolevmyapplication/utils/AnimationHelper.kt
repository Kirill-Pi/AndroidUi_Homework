package com.example.pigolevmyapplication.utils

import android.app.Activity
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateDecelerateInterpolator
import java.util.concurrent.Executors
import kotlin.math.hypot

object AnimationHelper {

    private const val menuItems = 5
        fun performFragmentCircularRevealAnimation(rootView: View, activity: Activity, position: Int) {

        Executors.newSingleThreadExecutor().execute {
             while (true) {
                if (rootView.isAttachedToWindow) {
                    activity.runOnUiThread {
                        //val itemCenter = rootView.width / (menuItems * 2)
                        //val step = (itemCenter * 2) * (position - 1) + itemCenter

                        //Setup animation random  start point
                        val x: Int = (0..rootView.width.toInt()).random().toInt()
                        val y: Int = (0..rootView.height.toInt()).random().toInt()

                        //val x: Int = step
                        //val y: Int = rootView.y.roundToInt() + rootView.height

                        val startRadius = 0
                        val endRadius = hypot(rootView.width.toDouble(), rootView.height.toDouble())
                        ViewAnimationUtils.createCircularReveal(rootView, x, y, startRadius.toFloat(), endRadius.toFloat()).apply {
                            duration = 500
                            interpolator = AccelerateDecelerateInterpolator()
                            start()
                        }
                        rootView.visibility = View.VISIBLE
                    }
                    return@execute
                }
            }
        }
    }
}
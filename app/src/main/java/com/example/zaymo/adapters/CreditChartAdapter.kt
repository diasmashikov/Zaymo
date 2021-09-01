package com.example.zaymo.adapters

import android.graphics.RectF
import com.example.zaymo.models.Credit
import com.robinhood.spark.SparkAdapter

class CreditChartAdapter() : SparkAdapter() {

    private val list = listOf(10, 30, 40, 20, 50, 60 ,70, 80, 30, 50, 90, 100)

    override fun getY(index: Int): Float {
        val chosenDayData = list[index]
        return chosenDayData.toFloat()
    }

    override fun getItem(index: Int) = list[index]

    override fun getCount() = list.size

}
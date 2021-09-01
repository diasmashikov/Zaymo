package com.example.zaymo.ui.zaymo.fragmentsZaymo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.fragment.app.Fragment
import com.example.zaymo.R
import kotlinx.android.synthetic.main.fragment_borrowing.*
import kotlinx.android.synthetic.main.fragment_lend.*
import kotlin.math.roundToInt
import kotlin.properties.Delegates


class LendFragment : Fragment(R.layout.fragment_lend) {

    var totalLendingSum by Delegates.notNull<Int>()
    var lendingRate by Delegates.notNull<Float>()
    var period by Delegates.notNull<Int>()
    var totalProfit by Delegates.notNull<Int>()
    var totalProfitEachMonth by Delegates.notNull<Int>()
    var totalRevenue by Delegates.notNull<Int>()
    var totalRevenueEachMonth by Delegates.notNull<Int>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*
        determineCreditScoreRanking()
        calculateCreditData()
        setSeekBar()
        setSwitchForAutomaticPay()
        updateAutomaticPayPeriodFromText()
        updateAutomaticPayPeriodFromSpinner()

         */
    }

    /*
    fun setSeekBar() {

        sbToLendRate.max = 100
        sbToLendRate.progress = 0

        sbToLendRate.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, b: Boolean) {

                tvToLendRate.setText(progress.toString() + "%")

                calculateCreditData()

                updateCreditCalculationsData(progress)




            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

    }
    fun setSwitchForAutomaticPay() {
        sToLendEnableAutomaticPay.setOnCheckedChangeListener { compoundButton, b ->
            if(sToLendEnableAutomaticPay.isChecked == true) {
                tvToLendNotifyPeriod.visibility = View.INVISIBLE
                tvToLendAutomaticPayPeriod.visibility = View.VISIBLE
            } else {
                tvToLendNotifyPeriod.visibility = View.VISIBLE
                tvToLendAutomaticPayPeriod.visibility = View.GONE
            }

        }
    }
    fun setAutomaticPayPeriod() {
        val periodUnit =  spToLendPeriodUnit.selectedItem.toString()
        val sumOfPeriodUnit = if (etToLendNumberOfTime.text.toString() == "") 1 else etToLendNumberOfTime.text.toString().toInt()

        var calculatedRevenuePerMonthInDays = String.format("%.3f", (totalRevenueEachMonth.toFloat() / (30.0 / sumOfPeriodUnit)))
        var calculatedRevenuePerMonthInWeeks = String.format("%.3f",(totalRevenueEachMonth.toFloat() / (30.0 / (7 * sumOfPeriodUnit))))
        var calculatedRevenuePerYearInMonths =  String.format("%.3f",(totalRevenueEachMonth * sumOfPeriodUnit).toFloat())

        when(periodUnit) {
            "День" -> {
                if(sumOfPeriodUnit == 1) {
                    setAutomaticPaySequenceText("Каждый день Рустам.М будет выплачивать $calculatedRevenuePerMonthInDays T Диас.М")
                }
                else if((sumOfPeriodUnit > 1 && sumOfPeriodUnit < 5) || (sumOfPeriodUnit > 21 && sumOfPeriodUnit < 29)) {
                    setAutomaticPaySequenceText("Каждые $sumOfPeriodUnit дня Рустам.М будет выплачивать $calculatedRevenuePerMonthInDays T Диас.М")
                }
                else if((sumOfPeriodUnit > 4 && sumOfPeriodUnit < 22) || sumOfPeriodUnit == 30 || sumOfPeriodUnit == 30) {
                    setAutomaticPaySequenceText("Каждые $sumOfPeriodUnit дней Рустам.М будет выплачивать $calculatedRevenuePerMonthInDays T Диас.М")
                }
            }
            "Неделя" -> {
                if(sumOfPeriodUnit == 1) {
                    setAutomaticPaySequenceText("Каждую неделю Рустам.М будет выплачивать $calculatedRevenuePerMonthInWeeks T Диас.М")
                }
                else if(sumOfPeriodUnit > 1 && sumOfPeriodUnit < 4) {
                    setAutomaticPaySequenceText("Каждые $sumOfPeriodUnit недели Рустам.М будет выплачивать $calculatedRevenuePerMonthInWeeks T Диас.М")
                }

            }
            "Месяц" -> {
                if(sumOfPeriodUnit == 1) {
                    setAutomaticPaySequenceText("Каждый месяц вы будете выплачивать $calculatedRevenuePerYearInMonths T")
                }
                else if(sumOfPeriodUnit > 1 && sumOfPeriodUnit <= 4) {
                    setAutomaticPaySequenceText("Каждые $sumOfPeriodUnit месяца Рустам.М будет выплачивать $calculatedRevenuePerYearInMonths T Диас.М")
                }
                else if(sumOfPeriodUnit > 4 && sumOfPeriodUnit <= 12 )
                tvToLendAutomaticPaySequence.setText("Каждые $sumOfPeriodUnit месяцев Рустам.М будет выплачивать $calculatedRevenuePerYearInMonths T Диас.М")

            }
        }
    }
    private fun setAutomaticPaySequenceText(text: String) {
        tvToLendAutomaticPaySequence.setText(text)
    }
    fun calculateCreditData() {
        val lengthRate = tvToLendRate.text.toString().length - 2
        totalLendingSum = tvToLendSum.text.toString().slice(0..4).toInt()
        lendingRate = tvToLendRate.text.toString().slice(0..lengthRate).trim().toFloat() / 100
        period = tvToLendPeriod.text.toString().slice(0..0).toInt()
        totalProfit = (totalLendingSum * lendingRate).roundToInt()
        totalProfitEachMonth = totalProfit / period
        totalRevenue = totalLendingSum + totalProfit
        totalRevenueEachMonth = totalRevenue / period

    }
    fun updateCreditCalculationsData(progress: Int) {

        tvToLendRate.setText(progress.toFloat().toString() + "%")
        tvToLendRevenue.setText(totalRevenue.toString() + " T")

        setAutomaticPayPeriod()

    }
    fun updateAutomaticPayPeriodFromText() {
        etToLendNumberOfTime.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                setAutomaticPayPeriod()
            }
        })
    }
    fun updateAutomaticPayPeriodFromSpinner() {
        spToLendPeriodUnit.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View, position: Int, id: Long) {
                setAutomaticPayPeriod()
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
            }
        })
    }
    fun determineCreditScoreRanking() {
        val creditScore = tvToLendCreditScore.text.toString().toInt()

        if(creditScore <= 1000 && creditScore >= 900) {
            tvToLendCreditScore.setTextColor(resources.getColor(R.color.highCreditScore))
        } else if(creditScore < 900 && creditScore >= 700) {
            tvToLendCreditScore.setTextColor(resources.getColor(R.color.mediumCreditScore))
        } else if(creditScore < 700) {
            tvToLendCreditScore.setTextColor(resources.getColor(R.color.mediumCreditScore))
        }
    }

     */


}
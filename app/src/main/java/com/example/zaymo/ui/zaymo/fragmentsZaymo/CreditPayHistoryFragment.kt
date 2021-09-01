package com.example.zaymo.ui.zaymo.fragmentsZaymo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zaymo.R
import com.example.zaymo.adapters.CreditPayHistoryAdapter
import com.example.zaymo.adapters.CurrentLendingsAdapter
import com.example.zaymo.models.Credit
import kotlinx.android.synthetic.main.fragment_credit_pay_history.*
import kotlinx.android.synthetic.main.fragment_lending.*

class CreditPayHistoryFragment : Fragment(R.layout.fragment_credit_pay_history) {

    lateinit private var creditPayHistoryAdapter: CreditPayHistoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerViewPayHistory()
    }

    fun setUpRecyclerViewPayHistory() {
        creditPayHistoryAdapter = CreditPayHistoryAdapter()
        rvCreditPayHistory.apply {
            adapter = creditPayHistoryAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        val list = listOf(Credit(1), Credit(2), Credit(3))
        creditPayHistoryAdapter.differ.submitList(list)
    }
}
package com.example.zaymo.ui.zaymo.fragmentsZaymo

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.zaymo.R
import kotlinx.android.synthetic.main.fragment_credit.*
import kotlinx.android.synthetic.main.fragment_profile.*


class CreditFragment : Fragment(R.layout.fragment_credit) {

    val args: CreditFragmentArgs by navArgs()

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val credit = args.credit
        btnCheckPaymentHistory.setOnClickListener {
            findNavController().navigate(
                R.id.action_creditFragment_to_creditPayHistoryFragment,
            )
        }

        pbCreditInterest.max = 77000
        pbCreditInterest.progress = 11666 + 2232

        pbCreditBody.max = 77000
        pbCreditBody.progress = 11666

    }
}
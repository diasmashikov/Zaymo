package com.example.zaymo.ui.zaymo.fragmentsZaymo

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.RadioButton
import android.widget.TableLayout
import androidx.fragment.app.Fragment
import com.example.zaymo.R
import com.example.zaymo.adapters.CreditChartAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_profile.*


@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var creditChartAdapter: CreditChartAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        eventListeners()

        toolbar.inflateMenu(R.menu.profile_menu)


    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.profile_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    private fun eventListeners() {

    }

    private fun selectCreditOrDebt(selectedButton: RadioButton, unselectedButton: RadioButton) {
        selectedButton.setBackgroundResource(R.drawable.btnrounded)
        selectedButton.setTextColor(getResources().getColor(R.color.white))
        unselectedButton.setBackgroundResource(0)
        unselectedButton.setTextColor(getResources().getColor(R.color.mainAppColor))
    }

    private fun selectTimeLine(
        selectedButton: RadioButton,
        unselectedButtonOne: RadioButton,
        unselectedButtonTwo: RadioButton,
        unselectedButtonThree: RadioButton
    ) {
        selectedButton.setBackgroundResource(R.drawable.btnrounded)
        selectedButton.setTextColor(getResources().getColor(R.color.white))
        unselectedButtonOne.setBackgroundResource(0)
        unselectedButtonOne.setTextColor(getResources().getColor(R.color.mainAppColor))
        unselectedButtonTwo.setBackgroundResource(0)
        unselectedButtonTwo.setTextColor(getResources().getColor(R.color.mainAppColor))
        unselectedButtonThree.setBackgroundResource(0)
        unselectedButtonThree.setTextColor(getResources().getColor(R.color.mainAppColor))
    }



}

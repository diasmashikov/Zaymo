package com.example.zaymo.ui.zaymo.fragmentsZaymo

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zaymo.R
import com.example.zaymo.adapters.CurrentLendingsAdapter
import com.example.zaymo.adapters.OfferLendingsAdapter
import com.example.zaymo.models.Credit
import com.example.zaymo.ui.zaymo.ZaymoActivity
import com.example.zaymo.ui.zaymo.ZaymoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_borrowing.*
import kotlinx.android.synthetic.main.fragment_lending.*
import net.cachapa.expandablelayout.ExpandableLayout


@AndroidEntryPoint
class LendingsFragment : Fragment(R.layout.fragment_lending) {
    lateinit var viewModel: ZaymoViewModel

    lateinit var currentLendingsAdapter: CurrentLendingsAdapter
    lateinit var offerLendingsAdapter: OfferLendingsAdapter
    private var isCurrentLendingsExpanded = false
    private var isOfferLendingsExpanded = false



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as ZaymoActivity).viewModel

        setupRecyclerViewCurrentLendings()
        setupRecyclerViewOfferLendings()

        observeViews()

        eventListeners()

    }

    private fun setupRecyclerViewCurrentLendings() {
        currentLendingsAdapter = CurrentLendingsAdapter()
        rvLendingCurrentLendings.apply {
            adapter = currentLendingsAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        btnLendingExpCurrentLendings.setOnClickListener {

            showOrHideRecyclerView(isCurrentLendingsExpanded, elLendingCurrentLendings, btnLendingExpCurrentLendings, "active")
        }



        val list = listOf(Credit(1), Credit(2), Credit(3))
        currentLendingsAdapter.differ.submitList(list)
    }

    private fun setupRecyclerViewOfferLendings() {
        offerLendingsAdapter = OfferLendingsAdapter()
        rvLendingOfferLendings.apply {
            adapter = offerLendingsAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        btnLendingExpOfferLendings.setOnClickListener {

            showOrHideRecyclerView(isOfferLendingsExpanded, elLendingOfferLendings, btnLendingExpOfferLendings, "offer")
        }

        val list = listOf(Credit(1), Credit(2), Credit(3))
        offerLendingsAdapter.differ.submitList(list)
    }

    private fun eventListeners() {

        currentLendingsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("credit", it)
            }
            findNavController().navigate(
                    R.id.action_lendingsFragment_to_creditFragment,
                    bundle
            )
        }

        offerLendingsAdapter.setOnBtnClickListener {
            val bundle = Bundle().apply {
                putSerializable("credit", it)
            }

            findNavController().navigate(
                    R.id.action_lendingsFragment_to_lendFragment,
                    bundle
            )
        }
    }

    private fun observeViews() {

        fun observeActiveCreditButton() {
            viewModel.activeCreditsButtonState.observe(viewLifecycleOwner, Observer { state ->
                isCurrentLendingsExpanded = state

                closeOrOpenRecyclerView(isCurrentLendingsExpanded, elLendingCurrentLendings, btnLendingExpCurrentLendings)

            })
        }

        fun observeOfferCreditButton() {
            viewModel.offerCreditsButtonState.observe(viewLifecycleOwner, Observer { state ->
                isOfferLendingsExpanded = state

                if (isOfferLendingsExpanded == true) {
                    elLendingOfferLendings.collapse()
                    btnLendingExpOfferLendings.setImageResource(R.drawable.ic_arrow_down)
                } else {
                    elLendingOfferLendings.expand()
                    btnLendingExpOfferLendings.setImageResource(R.drawable.ic_arrow_up)
                }

                closeOrOpenRecyclerView(isOfferLendingsExpanded, elLendingOfferLendings, btnLendingExpOfferLendings)

            })
        }

        fun observeLastLocation() {
            viewModel.lastPositionCreditCoordinateY.observe(viewLifecycleOwner, { position ->
                nsLending.post(Runnable { nsLending.scrollTo(0, position) })
            })
        }

        observeActiveCreditButton()
        observeOfferCreditButton()
        observeLastLocation()
    }


    private fun closeOrOpenRecyclerView(closeOpenState: Boolean, recyclerView: ExpandableLayout, closeOpenButton: ImageButton) {
        if (closeOpenState == true) {
            recyclerView.collapse()
            closeOpenButton.setImageResource(R.drawable.ic_arrow_down)
        } else {
            recyclerView.expand()
            closeOpenButton.setImageResource(R.drawable.ic_arrow_up)
        }
    }

    private fun showOrHideRecyclerView(closeOpenState: Boolean, recyclerView: ExpandableLayout, closeOpenButton: ImageButton, recyclerViewType: String) {
        recyclerView.duration = 200

        if(closeOpenState == true) {
            recyclerView.collapse()
            closeOpenButton.setImageResource(R.drawable.ic_arrow_down)
            if(recyclerViewType == "offer") {
                viewModel.setOfferCreditsButtonState(false)
            } else if (recyclerViewType == "active") {
                viewModel.setActiveCreditsButtonState(false)
            }
        }
        else {
            recyclerView.expand()
            closeOpenButton.setImageResource(R.drawable.ic_arrow_up)
            if(recyclerViewType == "offer") {
                viewModel.setOfferCreditsButtonState(true)
            } else if (recyclerViewType == "active") {
                viewModel.setActiveCreditsButtonState(true)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Это используется для сохранения положения при прокрутке вниз по ресайклер вьюхам
        nsLending.fullScroll(View.FOCUS_DOWN)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Это используется для сохранения положения при тыке BottomNavView компонентиков
        viewModel.setLastCreditCoordinateY(nsLending.scrollY)
    }



}

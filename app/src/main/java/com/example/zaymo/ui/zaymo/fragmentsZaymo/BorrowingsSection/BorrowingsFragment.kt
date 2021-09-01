package com.example.zaymo.ui.zaymo.fragmentsZaymo.BorrowingsSection

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zaymo.R
import com.example.zaymo.adapters.CurrentBorrowingsAdapter
import com.example.zaymo.adapters.OfferBorrowingsAdapter
import com.example.zaymo.models.Credit
import com.example.zaymo.ui.zaymo.ZaymoActivity
import com.example.zaymo.ui.zaymo.ZaymoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_borrowing.*
import net.cachapa.expandablelayout.ExpandableLayout


@AndroidEntryPoint
class BorrowingsFragment : Fragment(R.layout.fragment_borrowing) {
    lateinit var viewModel: ZaymoViewModel

    lateinit var currentBorrowingsAdapter: CurrentBorrowingsAdapter
    lateinit var offerBorrowingsAdapter: OfferBorrowingsAdapter

    private var isOfferBorrowingsExpanded = true
    private var isCurrentBorrowingsExpanded = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as ZaymoActivity).viewModel

        setupRecyclerViewCurrentBorrowings()
        setupRecyclerViewOfferBorrowings()

        observeViews()

        eventListeners()
    }

    private fun setupRecyclerViewCurrentBorrowings() {
        currentBorrowingsAdapter = CurrentBorrowingsAdapter()
        rvBorrowingCurrentBorrowings.apply {
            adapter = currentBorrowingsAdapter

            layoutManager = LinearLayoutManager(activity)
        }

        btnBorrowingExpCurrentBorrowings.setOnClickListener {

            showOrHideRecyclerView(isCurrentBorrowingsExpanded, elBorrowingCurrentBorrowings, btnBorrowingExpCurrentBorrowings, "active")

        }

        val list = listOf(Credit(1), Credit(2), Credit(3))
        currentBorrowingsAdapter.differ.submitList(list)
    }

    private fun setupRecyclerViewOfferBorrowings() {
        offerBorrowingsAdapter = OfferBorrowingsAdapter()
        rvBorrowingOfferBorrowings.apply {
            adapter = offerBorrowingsAdapter
            layoutManager = LinearLayoutManager(activity)

        }
        btnBorrowingExpOfferBorrowings.setOnClickListener {

            showOrHideRecyclerView(isOfferBorrowingsExpanded, elBorrowingOfferBorrowings, btnBorrowingExpOfferBorrowings, "offer")
        }

        val list = listOf(Credit(1), Credit(2), Credit(3))
        offerBorrowingsAdapter.differ.submitList(list)

    }

    private fun eventListeners() {

        btnBorrow.setOnClickListener {
            findNavController().navigate(
                    R.id.action_borrowingsFragment_to_requestBorrowingFragment,
            )
        }

        currentBorrowingsAdapter.setOnBtnClickListener {
            findNavController().navigate(
                    R.id.action_borrowingsFragment_to_payDebtFragment,
            )
        }

        offerBorrowingsAdapter.setOnBtnClickListener {
            findNavController().navigate(
                    R.id.action_borrowingsFragment_to_acceptDebtFragment,
            )
        }

    }

    private fun observeViews() {

        // Оно следит за состоянием динамичных компонентов
        // через ViewModel при переходе с разных фрагментов

         fun observeActiveBorrowButton() {
            viewModel.activeBorrowingsButtonState.observe(viewLifecycleOwner, Observer { state ->
                isCurrentBorrowingsExpanded = state

                closeOrOpenRecyclerView(isCurrentBorrowingsExpanded, elBorrowingCurrentBorrowings, btnBorrowingExpCurrentBorrowings)

            })
        }

         fun observeOfferBorrowButton() {
            viewModel.offerBorrowingsButtonState.observe(viewLifecycleOwner, Observer { state ->
                isOfferBorrowingsExpanded = state

                closeOrOpenRecyclerView(isOfferBorrowingsExpanded, elBorrowingOfferBorrowings, btnBorrowingExpOfferBorrowings)

            })
        }

         fun observeLastLocation() {
            viewModel.lastPositionBorrowingCoordinateY.observe(viewLifecycleOwner, { position ->
                nsBorrowing.post(Runnable { nsBorrowing.scrollTo(0, position) })
            })
        }

        observeActiveBorrowButton()
        observeOfferBorrowButton()
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
                viewModel.setOfferBorrowingsButtonState(false)
            } else if (recyclerViewType == "active") {
                viewModel.setActiveBorrowingsButtonState(false)
            }
        }
        else {
            recyclerView.expand()
            closeOpenButton.setImageResource(R.drawable.ic_arrow_up)
            if(recyclerViewType == "offer") {
                viewModel.setOfferBorrowingsButtonState(true)
            } else if (recyclerViewType == "active") {
                viewModel.setActiveBorrowingsButtonState(true)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Это используется для сохранения положения при прокрутке вниз по ресайклер вьюхам
        nsBorrowing.fullScroll(View.FOCUS_DOWN);
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Это используется для сохранения положения при тыке BottomNavView компонентиков
        viewModel.setLastBorrowingCoordinateY(nsBorrowing.scrollY)
    }

}

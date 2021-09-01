package com.example.zaymo.ui.zaymo

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.zaymo.repository.ZaymoRepository

class ZaymoViewModel @ViewModelInject constructor(
    val app: Application,
    val zaymoRepository: ZaymoRepository
): AndroidViewModel(app) {

    // LendingsFragment

    private val _activeCreditsButtonState = MutableLiveData<Boolean>()
    val activeCreditsButtonState: LiveData<Boolean>
        get() = _activeCreditsButtonState

    private val _offerCreditsButtonState = MutableLiveData<Boolean>()
    val offerCreditsButtonState: LiveData<Boolean>
        get() = _offerCreditsButtonState

    private val _lastPositionCreditCoordinateY = MutableLiveData<Int>()
    val lastPositionCreditCoordinateY: LiveData<Int>
        get() = _lastPositionCreditCoordinateY

    // BorrowingsFragment

    private val _activeBorrowingsButtonState = MutableLiveData<Boolean>()
    val activeBorrowingsButtonState: LiveData<Boolean>
        get() = _activeBorrowingsButtonState

    private val _offerBorrowingsButtonState = MutableLiveData<Boolean>()
    val offerBorrowingsButtonState: LiveData<Boolean>
        get() = _offerBorrowingsButtonState

    private val _lastPositionBorrowingCoordinateY = MutableLiveData<Int>()
    val lastPositionBorrowingCoordinateY: LiveData<Int>
        get() = _lastPositionBorrowingCoordinateY

    init {
        _activeCreditsButtonState.value = false
        _offerCreditsButtonState.value = false
        _lastPositionCreditCoordinateY.value = 0

        _activeBorrowingsButtonState.value = false
        _offerBorrowingsButtonState.value = false
        _lastPositionBorrowingCoordinateY
    }

    fun setOfferCreditsButtonState(state: Boolean) {
        _offerCreditsButtonState.value = state
    }

    fun setActiveCreditsButtonState(state: Boolean) {
        _activeCreditsButtonState.value = state
    }

    fun setLastCreditCoordinateY(position: Int) {
        _lastPositionCreditCoordinateY.value = position
    }

    fun setOfferBorrowingsButtonState(state: Boolean) {
        _offerBorrowingsButtonState.value = state
    }

    fun setActiveBorrowingsButtonState(state: Boolean) {
        _activeBorrowingsButtonState.value = state
    }

    fun setLastBorrowingCoordinateY(position: Int) {
        _lastPositionBorrowingCoordinateY.value = position
    }


}
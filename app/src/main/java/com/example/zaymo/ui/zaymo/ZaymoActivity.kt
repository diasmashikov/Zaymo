package com.example.zaymo.ui.zaymo

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.zaymo.R
import com.example.zaymo.ui.zaymo.fragmentsZaymo.BorrowingsSection.BorrowingsFragment
import com.example.zaymo.ui.zaymo.fragmentsZaymo.LendingsFragment
import com.example.zaymo.ui.zaymo.fragmentsZaymo.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class ZaymoActivity : AppCompatActivity() {

    val viewModel: ZaymoViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            actionBar.setElevation(0f)
        }
        setContentView(R.layout.activity_main)


        bottomNavigationView.setupWithNavController(zaymoNavHostFragment.findNavController())

    }





}
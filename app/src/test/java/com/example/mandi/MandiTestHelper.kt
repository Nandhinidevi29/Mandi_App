package com.example.mandi

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import org.robolectric.Robolectric

object MandiTestHelper {
    val mainActivity: MainActivity =
        Robolectric.buildActivity(MainActivity::class.java).create().resume().get()

    fun startFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = mainActivity.supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(fragment, null)
        fragmentTransaction.commit()
    }
}
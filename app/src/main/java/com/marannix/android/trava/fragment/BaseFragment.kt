package com.marannix.android.trava.fragment

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProvider
import com.marannix.android.trava.sharedpreferences.PreferencesHelper
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var userPreference: PreferencesHelper
    /**
     * Function to close soft keyboard when transitioning from one fragment to another while the keyboard is visible
     */
    fun closeKeyboard() {
        if (view != null) {
            val inputMethodManager = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view?.rootView?.windowToken, 0)
        }
    }
}
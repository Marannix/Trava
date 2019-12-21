package com.marannix.android.trava.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState, persistentState)
    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commit()
    }
}
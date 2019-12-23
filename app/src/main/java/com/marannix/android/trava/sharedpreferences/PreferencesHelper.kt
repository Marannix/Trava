package com.marannix.android.trava.sharedpreferences

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesHelper @Inject constructor(
    context: Context
) {

    private var prefUserToken = "user_preference"
    private var prefCityToken = "key_city_object"

    var userPreference = context.getSharedPreferences(prefUserToken, Context.MODE_PRIVATE)!!

    fun setSelectedCity(city: String) {
        userPreference.edit().putString(prefCityToken, city).apply()
    }

    fun getSelectedCity(): String {
        return userPreference.getString(prefCityToken, "")!!
    }
}
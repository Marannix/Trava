package com.marannix.android.trava.sharedpreferences

import android.content.Context
import com.google.gson.Gson
import javax.inject.Inject
import javax.inject.Singleton
import com.airbnb.lottie.LottieCompositionFactory.fromJson



@Singleton
class PreferencesHelper @Inject constructor(
    context: Context
) {

    private val recentCities = HashSet<String>()

    private var prefUserToken = "user_preference"
    private var prefCityToken = "key_city"

    var userPreference = context.getSharedPreferences(prefUserToken, Context.MODE_PRIVATE)!!

    fun setRecentCities(recentCities: String) {
        this.recentCities.add(recentCities)
        userPreference.edit().putStringSet(prefCityToken, this.recentCities).apply()
    }

    fun getRecentCities(): MutableSet<String> {
        return userPreference.getStringSet(prefCityToken, null)!!
    }
}
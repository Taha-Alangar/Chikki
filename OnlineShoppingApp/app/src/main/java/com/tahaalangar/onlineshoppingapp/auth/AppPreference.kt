package com.tahaalangar.onlineshoppingapp.auth

import android.content.Context
import android.content.SharedPreferences


class AppPreference(context: Context) {
    private val PREFERENCE_NAME="AppPreference"
    private var sharedPreference: SharedPreferences
    var editor: SharedPreferences.Editor

    init {

        sharedPreference=context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        editor=sharedPreference.edit()
    }

    //Method to save a string
    fun saveString(key:String,value:String){
        editor.putString(key,value)
        editor.apply()

    }
    //to retrieve the data
    fun getString(key: String,defaultValue:String):String?{
        return sharedPreference.getString(key,defaultValue)
    }

    fun saveBoolean(key: String, value: Boolean) {
        sharedPreference.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPreference.getBoolean(key, defaultValue)
    }
    // Method to clear all data from SharedPreferences
    fun clear() {
        editor.clear().apply()
    }
}
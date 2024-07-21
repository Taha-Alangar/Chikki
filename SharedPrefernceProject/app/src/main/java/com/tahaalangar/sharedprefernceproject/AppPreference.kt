package com.tahaalangar.sharedprefernceproject

import android.content.Context
import android.content.SharedPreferences

class AppPreference(context: Context) {
    private val PREFERENCE_NAME="AppPreference"
    private var sharedPreference:SharedPreferences
    private var editor:SharedPreferences.Editor

    init {

        sharedPreference=context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE)
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
    //Method to save a string
    fun saveInt(key:String,value:Int){
        editor.putInt(key,value)
        editor.apply()

    }

    //to retrieve the data
    fun getInt(key: String,defaultValue:Int):Int?{
        return sharedPreference.getInt(key,defaultValue)
    }
    
    fun saveBoolean(key:String,value:Boolean){
        editor.putBoolean(key,value)
        editor.apply()

    }

    //to retrieve the data
    fun getInt(key: String,defaultValue:Boolean):Boolean?{
        return sharedPreference.getBoolean(key,defaultValue)
    }
     // like wise you use many data types
}
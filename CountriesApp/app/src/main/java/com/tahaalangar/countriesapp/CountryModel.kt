package com.tahaalangar.countriesapp

data class CountryModel(
    val countryImage:Int,
    val countryName:String,
    val countryDescription:String
)

data class Country(
    val countryModels:List<CountryModel>
)

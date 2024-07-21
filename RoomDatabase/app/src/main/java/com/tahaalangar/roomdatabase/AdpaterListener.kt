package com.tahaalangar.roomdatabase

interface AdpaterListener {
    fun onUpdate(pojo:Contact)
    fun onDelete(pojo:Contact)
}
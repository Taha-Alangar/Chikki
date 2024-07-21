package com.tahaalangar.pharmacy

interface AdapterListerner {
    fun onUpdate(pojo: MedicinePojo)
    fun onDelete(pojo: MedicinePojo)
}
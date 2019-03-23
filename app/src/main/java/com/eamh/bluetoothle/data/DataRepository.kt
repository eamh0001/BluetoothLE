package com.eamh.bluetoothle.data

interface DataRepository {
    fun getAllData()
    fun getData(id:String)
}
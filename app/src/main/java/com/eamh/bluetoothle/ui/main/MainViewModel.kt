package com.eamh.bluetoothle.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eamh.bluetoothle.dummy.DummyContent
import com.eamh.bluetoothle.ui.simplelist.SimpleItemRecyclerViewAdapter
import com.eamh.bluetoothle.ui.simplelist.SimpleListItem
import com.eamh.bluetoothle.utils.Event

class MainViewModel: ViewModel() {
    val adapter = SimpleItemRecyclerViewAdapter()
    val onItemSelected : LiveData<Event<SimpleListItem>> = adapter.onItemSelected

    init {
        adapter.addAll(
            DummyContent.ITEMS
                .map {
                    SimpleListItem(
                        id = it.id,
                        title = it.content,
                        subtitle = it.content)
                }
        )
    }
}
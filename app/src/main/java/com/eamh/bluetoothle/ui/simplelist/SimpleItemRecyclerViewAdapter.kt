package com.eamh.bluetoothle.ui.simplelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.eamh.bluetoothle.R
import com.eamh.bluetoothle.databinding.ItemListContentBinding
import com.eamh.bluetoothle.utils.Event
import kotlinx.android.synthetic.main.item_list_content.view.*


class SimpleItemRecyclerViewAdapter :
    RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener
    private val items = mutableListOf<SimpleListItem>()
    private val _onItemSelected = MutableLiveData<Event<SimpleListItem>>()

    val onItemSelected : LiveData<Event<SimpleListItem>> =_onItemSelected

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as SimpleListItem
            _onItemSelected.value = Event(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemListContentBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_list_content,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.item = item
        holder.itemView.setOnClickListener {
            _onItemSelected.value = Event(item)
        }
    }

    override fun getItemCount() = items.size

    fun add(item: SimpleListItem){
        items.add(item)
        notifyDataSetChanged()
    }

    fun addAll(items: List<SimpleListItem>){
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clear(){
        items.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemListContentBinding) : RecyclerView.ViewHolder(binding.root) {
        var item: SimpleListItem? = null
        set(value) {
            field = value
            binding.viewModel = value
            binding.executePendingBindings()
        }
    }
}
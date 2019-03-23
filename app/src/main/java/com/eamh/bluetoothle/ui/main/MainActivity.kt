package com.eamh.bluetoothle.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.eamh.bluetoothle.R
import com.eamh.bluetoothle.databinding.ActivityMainBinding

import com.eamh.bluetoothle.ui.detail.ItemDetailActivity
import com.eamh.bluetoothle.ui.detail.ItemDetailFragment
import com.eamh.bluetoothle.ui.simplelist.SimpleListItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_list.*

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ItemDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class MainActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
//        item_list.layoutManager = LinearLayoutManager(this)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.onItemSelected.observe(this, Observer {
            it?.getContentIfNotHandled()?.let(::manageNewItemSelected)
        })

        twoPane = resources.getBoolean(R.bool.tablet_mode)

        setSupportActionBar(toolbar)
        toolbar.title = title
    }

    private fun manageNewItemSelected(simpleListItem: SimpleListItem) {
        if (twoPane) {
            val fragment = ItemDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ItemDetailFragment.ARG_ITEM_ID, simpleListItem.id)
                }
            }
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.item_detail_container, fragment)
                .commit()
        } else {
            val intent = Intent(this, ItemDetailActivity::class.java).apply {
                putExtra(ItemDetailFragment.ARG_ITEM_ID, simpleListItem.id)
            }
            startActivity(intent)
        }
    }
}

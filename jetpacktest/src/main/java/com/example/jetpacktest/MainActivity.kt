package com.example.jetpacktest

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt("count_reserved", 0)

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(countReserved)
        ).get(MainViewModel::class.java)

        bt_add.setOnClickListener {
            viewModel.counter++
            refreshCounter()
        }
        bt_clear.setOnClickListener {
            viewModel.counter = 0
            refreshCounter()
        }
        refreshCounter()
    }

    private fun refreshCounter() {
        infoText.text = viewModel.counter.toString()
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt("count_reserved", viewModel.counter)
        }
    }
}

class MainViewModel(countReserved: Int) : ViewModel() {
    var counter = countReserved
}

class MainViewModelFactory(private val countReserved: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(countReserved) as T
    }
}

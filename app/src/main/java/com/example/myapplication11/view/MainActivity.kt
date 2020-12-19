package com.example.myapplication11.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication11.R
import com.example.myapplication11.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ListViewModel
    private val photoListAdapter = PhotoListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.fetchData()

        rv_list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = photoListAdapter
        }
        observeViewModel()
    }
    fun observeViewModel() {
        viewModel.photos.observe(this, Observer { photos ->
            photos?.let {
                photoListAdapter.updatePhotos(it)
            }
        })
    }
}
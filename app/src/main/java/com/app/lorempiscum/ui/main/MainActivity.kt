package com.app.lorempiscum.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.lorempiscum.R
import com.app.lorempiscum.base.BaseActivity
import com.app.lorempiscum.databinding.ActivityMainBinding
import com.app.lorempiscum.model.local.Image
import com.app.lorempiscum.ui.adapter.ImagesAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity() :
    BaseActivity<MainViewModel, ActivityMainBinding>() {

    private val imageMainViewModel: MainViewModel by viewModel()
    private var images = ArrayList<Image>()
    private lateinit var imagesAdapter: ImagesAdapter
    private var mPage: Int = 1

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = imageMainViewModel
        super.onCreate(savedInstanceState)

        initAdapter()
        scrollState()

        imageMainViewModel.getImages(mPage)
        imageMainViewModel.imageLiveData.observe(this, Observer { list ->
            imagesAdapter.addData(list)
        })
    }

    private fun initAdapter() {
        imagesAdapter = ImagesAdapter(this, images)
        rvImages.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = imagesAdapter
        }
        imagesAdapter.notifyDataSetChanged()
    }

    private fun scrollState() {
        rvImages.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    mPage += 1
                    imageMainViewModel.getImages(mPage)
                }
            }
        })
    }

}
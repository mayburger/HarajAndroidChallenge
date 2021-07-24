package com.example.harajtask.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.harajtask.R
import com.example.harajtask.databinding.ActivityMainBinding
import com.example.harajtask.model.Product
import com.example.harajtask.ui.detail.DetailActivity
import com.example.harajtask.ui.adapters.ProductAdapter
import com.example.harajtask.utils.*
import com.example.harajtask.utils.constants.ListTypeConstant
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val binding by viewBinding(ActivityMainBinding::inflate)
    val viewModel by viewModels<MainViewModel>()

    @Inject
    lateinit var productAdapter: ProductAdapter
    var listType = ListTypeConstant.LIST

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.recycler.apply {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        binding.listType.setOnClickListener {
            when (listType) {
                ListTypeConstant.LIST -> {
                    listType = ListTypeConstant.SQUARE
                    productAdapter.listType = ListTypeConstant.SQUARE
                    binding.recycler.adapter = productAdapter
                    binding.recycler.layoutManager = LinearLayoutManager(this@MainActivity)
                    binding.listType.setImageResource(R.drawable.ic_square)
                    binding.recycler.fadeShow(duration = 500)
                }
                ListTypeConstant.SQUARE -> {
                    listType = ListTypeConstant.GRID
                    productAdapter.listType = ListTypeConstant.GRID
                    binding.recycler.adapter = productAdapter
                    binding.recycler.layoutManager = GridLayoutManager(this@MainActivity, 2)
                    binding.listType.setImageResource(R.drawable.ic_grid)
                    binding.recycler.fadeShow(duration = 500)
                }
                else -> {
                    listType = ListTypeConstant.LIST
                    productAdapter.listType = ListTypeConstant.LIST
                    binding.recycler.adapter = productAdapter
                    binding.recycler.layoutManager = LinearLayoutManager(this@MainActivity)
                    binding.listType.setImageResource(R.drawable.ic_list)
                    binding.recycler.fadeShow(duration = 500)
                }
            }
        }

        productAdapter.setListener(object : ProductAdapter.Callback {
            override fun onSelectedItem(product: Product) {
                DetailActivity.startActivity(this@MainActivity, product)
            }
        })

        io {
            viewModel.products.collectLatest {
                kotlinx.coroutines.delay(1500)
                viewModel.isLoading.postValue(false)
                productAdapter.submitData(it)
            }
        }

        viewModel.isLoading.observe(this,{
            if (!it){
                binding.recycler.fadeShow(duration = 1000)
                binding.recycler.animToY(0f, duration = 1000)
            }
        })


    }
}
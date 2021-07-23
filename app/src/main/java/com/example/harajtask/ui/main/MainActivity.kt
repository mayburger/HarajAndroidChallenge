package com.example.harajtask.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.harajtask.R
import com.example.harajtask.data.ProductRepository
import com.example.harajtask.databinding.ActivityMainBinding
import com.example.harajtask.model.Product
import com.example.harajtask.ui.detail.DetailActivity
import com.example.harajtask.ui.main.adapters.ProductAdapter
import com.example.harajtask.utils.constants.ListTypeConstant
import com.example.harajtask.utils.viewBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val binding by viewBinding(ActivityMainBinding::inflate)
    val viewModel by viewModels<MainViewModel>()

    @Inject
    lateinit var productAdapter:ProductAdapter
    var listType = ListTypeConstant.LIST

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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
                    productAdapter.refresh()
                    binding.listType.setImageResource(R.drawable.ic_square)
                }
                ListTypeConstant.SQUARE -> {
                    listType = ListTypeConstant.GRID
                    productAdapter.listType = ListTypeConstant.GRID
                    binding.recycler.adapter = productAdapter
                    binding.recycler.layoutManager = GridLayoutManager(this@MainActivity,2)
                    productAdapter.refresh()
                    binding.listType.setImageResource(R.drawable.ic_grid)
                }
                else -> {
                    listType = ListTypeConstant.LIST
                    productAdapter.listType = ListTypeConstant.LIST
                    binding.recycler.adapter = productAdapter
                    binding.recycler.layoutManager = LinearLayoutManager(this@MainActivity)
                    productAdapter.refresh()
                    binding.listType.setImageResource(R.drawable.ic_list)
                }
            }
        }

        productAdapter.setListener(object:ProductAdapter.Callback{
            override fun onSelectedItem(product: Product) {
                DetailActivity.startActivity(this@MainActivity, product)
            }
        })

        lifecycleScope.launch {
            viewModel.products.collectLatest {
                productAdapter.submitData(it)
            }
        }

    }
}
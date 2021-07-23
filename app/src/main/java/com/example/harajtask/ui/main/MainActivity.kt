package com.example.harajtask.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.harajtask.R
import com.example.harajtask.data.ProductRepository
import com.example.harajtask.databinding.ActivityMainBinding
import com.example.harajtask.ui.main.adapters.ProductAdapter
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recycler.apply {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        lifecycleScope.launch {
            viewModel.products.collectLatest {
                productAdapter.submitData(it)
            }
        }

    }
}
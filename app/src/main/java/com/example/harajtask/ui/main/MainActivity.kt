package com.example.harajtask.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.harajtask.BR
import com.example.harajtask.R
import com.example.harajtask.base.BaseActivity
import com.example.harajtask.databinding.ActivityMainBinding
import com.example.harajtask.model.Product
import com.example.harajtask.ui.adapters.ProductAdapter
import com.example.harajtask.ui.adapters.ProductAdapter.Companion.grid
import com.example.harajtask.ui.adapters.ProductAdapter.Companion.list
import com.example.harajtask.ui.adapters.ProductAdapter.Companion.square
import com.example.harajtask.ui.detail.DetailActivity
import com.example.harajtask.utils.*
import com.example.harajtask.utils.constants.ListTypeConstant
import com.example.harajtask.utils.ext.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var productAdapter: ProductAdapter
    var listType = ListTypeConstant.LIST

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this

        binding.recycler.apply {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        productAdapter.setListener(object : ProductAdapter.Callback {
            override fun onSelectedItem(product: Product) {
                DetailActivity.startActivity(this@MainActivity, product)
            }
        })

        observeDataCollection()
        handleViewTypeChanges()
    }

    fun handleViewTypeChanges(){
        binding.listType.setOnClickListener {
            when (productAdapter.listType) {
                ListTypeConstant.LIST -> {
                    productAdapter.square(binding.recycler)
                    binding.listType.setImageResource(R.drawable.ic_square)
                }
                ListTypeConstant.SQUARE -> {
                    productAdapter.grid(binding.recycler)
                    binding.listType.setImageResource(R.drawable.ic_grid)
                }
                else -> {
                    productAdapter.list(binding.recycler)
                    binding.listType.setImageResource(R.drawable.ic_list)
                }
            }
            binding.recycler.adapter = productAdapter
            binding.recycler.fadeShow(duration = 500)
        }
    }

    fun observeDataCollection(){
        io{
            viewModel.products.collectLatest {
                productAdapter.submitData(it)
            }
        }

        binding.buttonRetry.setOnClickListener {
            productAdapter.refresh()
        }

        productAdapter.addLoadStateListener {
            if (it.refresh is LoadState.Loading){
                binding.recycler.hide()
                binding.progress.show()
                // Error Views
                binding.errorTitle.hide()
                binding.errorDesc.hide()
                binding.buttonRetry.hide()
            } else if (it.refresh is LoadState.Error){
                binding.recycler.hide()
                binding.progress.hide()
                // Error Views
                binding.errorTitle.show()
                binding.errorDesc.show()
                binding.buttonRetry.show()
            } else if (it.refresh !is LoadState.Loading && it.refresh !is LoadState.Error){
                binding.progress.hide()
                binding.recycler.fadeShow(duration = 800)
                binding.recycler.animToY(0f, duration = 800)
            }
        }
    }
}
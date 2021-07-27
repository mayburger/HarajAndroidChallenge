package com.example.harajtask.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.harajtask.BR
import com.example.harajtask.R
import com.example.harajtask.base.BaseActivity
import com.example.harajtask.databinding.ActivityMainBinding
import com.example.harajtask.model.Product
import com.example.harajtask.ui.adapters.LoadStateAdapter
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
    lateinit var pagingLoadStateAdapter:LoadStateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        pagingLoadStateAdapter = LoadStateAdapter(productAdapter::retry)
        binding.recycler.apply {
            adapter  = productAdapter.withLoadStateFooter(pagingLoadStateAdapter)
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
            binding.recycler.adapter = productAdapter.withLoadStateFooter(pagingLoadStateAdapter)
            binding.recycler.fadeShow(duration = 500)
        }
    }

    fun observeDataCollection(){
        io{
            viewModel.products.collectLatest {
                productAdapter.submitData(it)
            }
        }

        binding.swipeRefresh.setColorSchemeResources(R.color.colorPrimary)
        binding.swipeRefresh.setOnRefreshListener {
            productAdapter.refresh()
        }

        productAdapter.addLoadStateListener {
            if (it.refresh is LoadState.Loading){
                binding.swipeRefresh.isRefreshing = true
            } else if (it.refresh is LoadState.Error){
                binding.swipeRefresh.isRefreshing = false
                pagingLoadStateAdapter.loadState = it.refresh
            } else if (it.refresh !is LoadState.Loading && it.refresh !is LoadState.Error){
                binding.swipeRefresh.isRefreshing = false
                if (binding.recycler.isHidden()){
                    binding.recycler.fadeShow(duration = 800)
                    binding.recycler.animToY(0f, duration = 800)
                }
            }
        }
    }
}
package com.example.harajtask.ui.main.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.harajtask.base.BasePagingViewHolder
import com.example.harajtask.databinding.ItemProductGridBinding
import com.example.harajtask.databinding.ItemProductListBinding
import com.example.harajtask.model.Product
import com.example.harajtask.utils.constants.ListTypeConstant

class ProductAdapter(val context:Context): PagingDataAdapter<Product, BasePagingViewHolder<Product>>(ProductComparator){

    companion object{
        private val ProductComparator = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean =
                oldItem.equals(newItem)
        }
    }

    override fun onBindViewHolder(holder: BasePagingViewHolder<Product>, position: Int) {
        holder.onBind(position, getItem(position) ?: Product())
    }

    var listType = 0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BasePagingViewHolder<Product> {
        return when(listType){
            ListTypeConstant.GRID->{
                ProductGridViewHolder(ItemProductGridBinding.inflate(LayoutInflater.from(context),parent, false))
            }
            ListTypeConstant.SQUARE->{
                ProductGridViewHolder(ItemProductGridBinding.inflate(LayoutInflater.from(context),parent, false))
            }
            else->{
                ProductListViewHolder(ItemProductListBinding.inflate(LayoutInflater.from(context),parent, false))
            }
        }
    }

    class ProductListViewHolder(private val binding: ItemProductListBinding) : BasePagingViewHolder<Product>(binding.root) {
        override fun onBind(position: Int, data: Product) {
            binding.viewModel = ItemProductViewModel(data)
        }
    }


    class ProductGridViewHolder(private val binding: ItemProductGridBinding) : BasePagingViewHolder<Product>(binding.root) {
        override fun onBind(position: Int, data: Product) {
            binding.viewModel = ItemProductViewModel(data)
        }
    }

}
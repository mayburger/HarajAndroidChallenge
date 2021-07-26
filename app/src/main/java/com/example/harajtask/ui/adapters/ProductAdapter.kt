package com.example.harajtask.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.harajtask.base.BasePagingViewHolder
import com.example.harajtask.databinding.ItemProductGridBinding
import com.example.harajtask.databinding.ItemProductListBinding
import com.example.harajtask.model.Product
import com.example.harajtask.ui.adapters.viewmodels.ItemProductViewModel
import com.example.harajtask.utils.constants.ListTypeConstant
import com.example.harajtask.utils.ext.grid2
import com.example.harajtask.utils.ext.isArabic
import com.example.harajtask.utils.ext.vertical

class ProductAdapter(val context:Context): PagingDataAdapter<Product, BasePagingViewHolder<Product>>(
    ProductComparator
){

    companion object{
        private val ProductComparator = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean =
                oldItem.equals(newItem)
        }

        fun ProductAdapter.list(recyclerView: RecyclerView){
            recyclerView.vertical()
            listType = ListTypeConstant.LIST
        }

        fun ProductAdapter.grid(recyclerView: RecyclerView){
            recyclerView.grid2()
            listType = ListTypeConstant.GRID
        }

        fun ProductAdapter.square(recyclerView: RecyclerView){
            recyclerView.vertical()
            listType = ListTypeConstant.SQUARE
        }
    }

    override fun onBindViewHolder(holder: BasePagingViewHolder<Product>, position: Int) {
        holder.onBind(position, getItem(position) ?: Product())
    }

    var listType = 0
    private var mListener: Callback? = null

    interface Callback {
        fun onSelectedItem(product: Product)
    }

    fun setListener(listener: Callback) {
        this.mListener = listener
    }

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

    inner class ProductListViewHolder(private val binding: ItemProductListBinding) : BasePagingViewHolder<Product>(binding.root) {
        override fun onBind(position: Int, data: Product) {
            binding.viewModel = ItemProductViewModel(data)
            binding.root.setOnClickListener {
                mListener?.onSelectedItem(data)
            }
            if (data.title?.isArabic() == true){
                binding.title.textAlignment = TextView.TEXT_ALIGNMENT_TEXT_START
            } else{
                binding.title.textAlignment = TextView.TEXT_ALIGNMENT_TEXT_END
            }
        }
    }


    inner class ProductGridViewHolder(private val binding: ItemProductGridBinding) : BasePagingViewHolder<Product>(binding.root) {
        override fun onBind(position: Int, data: Product) {
            binding.viewModel = ItemProductViewModel(data)
            binding.root.setOnClickListener {
                mListener?.onSelectedItem(data)
            }
            if (data.title?.isArabic() == true){
                binding.title.textAlignment = TextView.TEXT_ALIGNMENT_TEXT_START
            } else{
                binding.title.textAlignment = TextView.TEXT_ALIGNMENT_TEXT_END
            }
        }
    }

}
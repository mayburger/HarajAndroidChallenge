package com.example.harajtask.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.harajtask.databinding.ActivityDetailBinding
import com.example.harajtask.model.Product
import com.example.harajtask.utils.viewBinding

class DetailActivity : AppCompatActivity(), DetailNavigator{

    companion object{
        const val EXTRA_PRODUCT = "extra_product"

        fun startActivity(context: Context, product:Product){
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_PRODUCT, product)
            context.startActivity(intent)
        }
    }

    val binding by viewBinding(ActivityDetailBinding::inflate)
    val viewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.navigator = this

        viewModel.setProduct(intent.getParcelableExtra(EXTRA_PRODUCT)!!)

        binding.share.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, viewModel.product.value?.title)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }

    override fun onBack() {
        super.onBackPressed()
    }


}
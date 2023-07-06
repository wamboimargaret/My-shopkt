package megan.wamboi.myshop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import megan.wamboi.myshop.databinding.ProductBinding

class ProductAdapter (var productList: List<Product>): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
      val binding = ProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
      return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        var product = productList[position]
        holder.bind(product)
    }

    class ProductViewHolder(private val binding: ProductBinding):RecyclerView.ViewHolder(binding.root) {
fun bind(product:Product){
    binding.apply {
    Picasso.get().load(product.thumbnail).into(ivItem)
        tvitem.text = product.title
        tvdescription.text = product.description
        tvPrice.text =product.price.toString()
        tvRating.text =product.rating.toString()
        tvStock.text = product.stock.toString()
    }

}

    }
}
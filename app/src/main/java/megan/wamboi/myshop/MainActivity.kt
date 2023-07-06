package megan.wamboi.myshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import megan.wamboi.myshop.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var productAdapter: ProductAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView: RecyclerView=binding.rvrecycler
        recyclerView.layoutManager= GridLayoutManager(this,3)
        productAdapter = ProductAdapter(mutableListOf())
        recyclerView.adapter= productAdapter
    }

    override fun onResume() {
        super.onResume()
        getProducts()
    }

    fun getProducts(){
        val apiclient = ApiClient.buildClient(ApiInterface::class.java)
        val request = apiclient.getProducts()
        request.enqueue(object : Callback<ProductsResponse> {
            override fun onResponse(call: Call<ProductsResponse>, response: Response<ProductsResponse>) {
               if(response.isSuccessful){

                  var products = response.body()?.products
                  Toast.makeText(baseContext, "fetched ${products?.size} products",Toast.LENGTH_LONG ).show()
               }
                else{
                    Toast.makeText(baseContext,response.errorBody()?.string(), Toast.LENGTH_LONG).show()
               }
            }

            override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
                Toast.makeText(baseContext,"Error: ${t.message}",Toast.LENGTH_LONG).show()
            }
        })
    }
}
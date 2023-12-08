package me.amitghosh.walmart.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.amitghosh.walmart.adapter.MyAdapter
import me.amitghosh.walmart.model.Product
import me.amitghosh.walmart.R
import me.amitghosh.walmart.databinding.ActivityItemListingBinding


class ItemListing : AppCompatActivity() {
    private lateinit var binding: ActivityItemListingBinding

    var selectedProducts = ArrayList<Product>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemListingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnBack = findViewById<AppCompatButton>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }

        val products = ArrayList<Product>()
        products.add(Product("apple_logo","ipad_pro","iPad","iPad Pro 11-inch","8GB RAM, 512 GB Memory",1400.0))
        products.add(Product("google_logo", "pixel_pro", "Pixel", "17 pro", "12GB RAM, 512GB Memory", 900.0))
        products.add(Product("samsung_logo", "s23", "Galaxy", "S23 Ultra", "16GB RAM, 1TB Memory", 1250.0))
        products.add(Product("dell_logo", "dell_xps", "XPS", "17 inch", "64GB RAM, 4TB Memory", 2550.0))

        val btnViewCart = findViewById<AppCompatButton>(R.id.btnViewCart)
        btnViewCart.setOnClickListener {
            showArrayListInDialog("Items in Cart", selectedProducts)
        }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val itemAdapter = MyAdapter(this, products, this)
        recyclerView.adapter = itemAdapter
    }

    public fun addSelectedProducts(product: Product) {
        selectedProducts.add(product)
    }

    private fun showToastMessage(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()
    }

    private fun showArrayListInDialog(header: String, dataList: ArrayList<Product>) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(header)

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, dataList)

        val listView = ListView(this)
        listView.adapter = arrayAdapter

        builder.setView(listView)

        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = builder.create()
        alertDialog.show()
    }
}
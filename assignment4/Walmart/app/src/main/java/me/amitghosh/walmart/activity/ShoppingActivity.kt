package me.amitghosh.walmart.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import me.amitghosh.walmart.R
import me.amitghosh.walmart.databinding.ActivityMainBinding
import me.amitghosh.walmart.databinding.ActivityShoppingBinding

class ShoppingActivity : AppCompatActivity() {
    lateinit var binding: ActivityShoppingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityShoppingBinding.inflate(layoutInflater)

        setContentView(binding.root)


        val sinIntent = intent
        binding.tvEmail.text = sinIntent.getStringExtra("userName")

        binding.llElectronics.setOnClickListener {
            val electronicsIntent = Intent(this, ItemListing::class.java)
            startActivity(electronicsIntent)
        }
    }
}
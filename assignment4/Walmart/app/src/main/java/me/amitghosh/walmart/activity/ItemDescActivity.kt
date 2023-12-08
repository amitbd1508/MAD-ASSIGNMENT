package me.amitghosh.walmart.activity

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.amitghosh.walmart.databinding.ActivityItemDescBinding
import java.io.File

class ItemDescActivity : AppCompatActivity() {
    private lateinit var binding: ActivityItemDescBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemDescBinding.inflate(layoutInflater)

        setContentView(binding.root)


        binding.tvItemName.text = intent.getStringExtra("itemName")
        binding.tvItemModel.text = intent.getStringExtra("itemModel")
        binding.tvItemDesc.text = intent.getStringExtra("itemPrice")



        val fileImage = intent.getStringExtra("itemName")?.let { File(cacheDir, it) }
        val fileLogo = File(cacheDir, intent.getStringExtra("itemName") + "_logo")

        //Log.i("mak_findings", intent.getStringExtra("itemName") + "_logo")

        if (fileImage != null) {
            if (fileImage.exists()) {
                val imageBitmap = BitmapFactory.decodeFile(fileImage.absolutePath)
                binding.ivItemImage.setImageBitmap(imageBitmap)
            }
        }

        if (fileLogo.exists()) {
            val logoBitmap = BitmapFactory.decodeFile(fileLogo.absolutePath)
            binding.ivItemLogo.setImageBitmap(logoBitmap)
        }

        binding.btnHome.setOnClickListener {
            finish()
        }
    }
}
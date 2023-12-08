package me.amitghosh.walmart.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import me.amitghosh.walmart.model.Product
import me.amitghosh.walmart.R
import me.amitghosh.walmart.activity.ItemDescActivity
import me.amitghosh.walmart.activity.ItemListing
import java.io.File
import java.io.FileOutputStream


class MyAdapter(
    private val context: Context,
    var iList: ArrayList<Product>,
    private val itemListing: ItemListing
) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivItemImage: ImageView
        var ivLogo: ImageView

        var tvItemName: TextView
        var tvItemModel: TextView
        var tvPrice: TextView
        var rlItem: RelativeLayout

        var btnAddToCart: AppCompatButton

        init {
            ivItemImage = itemView.findViewById(R.id.ivItemImage) as ImageView
            ivLogo = itemView.findViewById(R.id.ivLogo) as ImageView

            tvItemName = itemView.findViewById(R.id.tvItemName) as TextView
            tvItemModel = itemView.findViewById(R.id.tvItemModel) as TextView
            tvPrice = itemView.findViewById(R.id.tvPrice) as TextView
            rlItem = itemView.findViewById(R.id.rlItem) as RelativeLayout

            btnAddToCart = itemView.findViewById(R.id.btnAddToCart) as AppCompatButton
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        val layoutParams = view.layoutParams
        layoutParams.height = WRAP_CONTENT
        view.layoutParams = layoutParams
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return iList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = iList[position]
        holder.tvItemName.text = currentItem.productName
        holder.tvItemModel.text = currentItem.productModel

        val context = holder.ivItemImage.context
        val resourceId = context.resources.getIdentifier(
            currentItem.imageName, "drawable", context.packageName
        )
        val resourceId2 = context.resources.getIdentifier(
            currentItem.logoName, "drawable", context.packageName
        )

        holder.ivItemImage.setImageResource(resourceId)
        holder.ivLogo.setImageResource(resourceId2)

        holder.rlItem.setOnClickListener {
            val itemDescActivityIntent = Intent(context, ItemDescActivity::class.java)

            val ivItemImageBitmap = holder.ivItemImage.drawable.toBitmap()
            val ivItemLogoBitmap = holder.ivLogo.drawable.toBitmap()

            itemDescActivityIntent.putExtra("itemName", holder.tvItemName.text)
            itemDescActivityIntent.putExtra("itemModel", holder.tvItemModel.text)
            itemDescActivityIntent.putExtra("itemPrice", holder.tvPrice.text)

            //Write bitmap to cache
            val fileImage = File(context.cacheDir, holder.tvItemName.text.toString())
            val fileLogo = File(context.cacheDir, "${holder.tvItemName.text}_logo")

            val fileOutputStream = FileOutputStream(fileImage)
            ivItemImageBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)

            val fileOutputStream2 = FileOutputStream(fileLogo)
            ivItemLogoBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream2)

            fileOutputStream.close()
            fileOutputStream2.close()

            context.startActivity(itemDescActivityIntent)
        }

        holder.btnAddToCart.setOnClickListener {
            itemListing.addSelectedProducts(
                Product(
                    "",
                    "",
                    holder.tvItemName.text.toString(),
                    holder.tvItemModel.text.toString(),
                    "",
                    holder.tvPrice.text.toString().replace("$", "").toDouble()
                )
            )
        }
    }
}
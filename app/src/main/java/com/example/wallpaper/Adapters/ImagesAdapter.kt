package com.example.wallpaper.Adapters

import android.R.attr.path
import android.annotation.SuppressLint
import android.app.Dialog
import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.example.wallpaper.Model.imgsModel
import com.example.wallpaper.R


class ImagesAdapter(imageList: ArrayList<imgsModel>) :
    RecyclerView.Adapter<ImagesAdapter.ImageHolder>() {

    var imagesList = imageList
    lateinit var context: Context


    class ImageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgItem = itemView.findViewById<ImageView>(R.id.imageItem)
        var button = itemView.findViewById<Button>(R.id.btn)


    }

    @SuppressLint("ResourceType")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        context = parent.context
        return ImageHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.img_item, parent, false)
        )

    }

    @SuppressLint("ResourceAsColor", "ResourceType")
    override fun onBindViewHolder(holder: ImageHolder, position: Int) {

        Glide.with(context).load(imagesList[position].download_url).into(holder.imgItem)

        holder.itemView.setOnClickListener {
            val dialog = Dialog(context)
            dialog.window?.setBackgroundDrawable(ColorDrawable(android.R.color.transparent))
            dialog.setContentView(R.layout.full_image)

            val wallpaperManager = WallpaperManager.getInstance(context)
            val wallImg = dialog.findViewById<ImageView>(R.id.wallImg)
            val btn = dialog.findViewById<Button>(R.id.btn)
                Glide.with(context).load(imagesList[position].download_url).into(wallImg)
            btn.setOnClickListener {
                Glide.with(context)
                    .asBitmap()
                    .load(imagesList[position].download_url)
                    .into(object : SimpleTarget<Bitmap?>() {

                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: Transition<in Bitmap?>?
                        ) {
                            WallpaperManager.getInstance(context.applicationContext)
                                .setBitmap(resource)
                        }
                    })

            }
            dialog.show()
        }

    }

    override fun getItemCount(): Int {
        return imagesList.size
    }

}
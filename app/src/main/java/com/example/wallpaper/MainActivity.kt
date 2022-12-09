package com.example.wallpaper


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.wallpaper.Adapters.ImagesAdapter
import com.example.wallpaper.Model.imgsModel
import com.example.wallpaper.databinding.ActivityMainBinding
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

     private val TAG = "MainActivity"
    var url = "https://picsum.photos/v2/list"
    var imageList = ArrayList<imgsModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var request = StringRequest(Request.Method.GET,url, { response ->

            var array = JSONArray(response)

            for (x in 0..array.length()-1){
                var obj = array.getJSONObject(x)
                var id = obj.getInt("id")
                var author = obj.getString("author")
                var width = obj.getInt("width")
                var height = obj.getInt("height")
                var url = obj.getString("url")
                var download_url = obj.getString("download_url")
                var data = imgsModel(id, author, width, height, url, download_url)
                imageList.add(data)


            }

            binding.recyclerview.layoutManager = GridLayoutManager(applicationContext,2)
            binding.recyclerview.adapter = ImagesAdapter(imageList)


        },Response.ErrorListener {

        })

        var queue:RequestQueue = Volley.newRequestQueue(applicationContext)
        queue.add(request)


    }
}
package com.example.wallpaper.Model

class imgsModel {

    var id = 0
    lateinit var author: String
    var width = 0
    var height = 0
    lateinit var url: String
    lateinit var download_url: String

    constructor(
        id: Int,
        author: String,
        width: Int,
        height: Int,
        url: String,
        download_url: String

    ){
        this.id = id
        this.author = author
        this.width = width
        this.height = height
        this.url = url
        this.download_url = download_url
    }
}
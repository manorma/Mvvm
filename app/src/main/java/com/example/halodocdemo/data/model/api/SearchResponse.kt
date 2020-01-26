package com.example.halodocdemo.data.model.api

data class SearchResponse(val hits:List<Hit>){

}

data class Hit(val created_at:String?,
               val title:String?,
               val url:String?,
               val author:String?)
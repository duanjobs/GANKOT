package com.duanjobs.gankot.bean

/**
 * Created by duanjobs on 17/8/29.
 */
data class GankArticle(val _id:String,val createdAt:String,val desc:String,
                  var images:Array<String>,val publishedAt:String,val source:String,val type:String,val url:String,
                  val used:Boolean,val who:String)
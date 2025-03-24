package com.am.core.domain.entity

data class Course(
    val id : Int,
    val title : String,
    val text : String,
    val price : String,
    val rate : Float,
    val startDate : String,
    val hasLike : Boolean,
    val publishDate : String
)

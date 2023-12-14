package com.example.homework_7

import java.io.Serializable

data class Image(
    val imageUrl: String,
    val tags: List<String>
): Serializable

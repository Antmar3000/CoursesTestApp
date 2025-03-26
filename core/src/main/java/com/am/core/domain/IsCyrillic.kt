package com.am.core.domain

fun isCyrillic (char: Char) : Boolean {
    return char in 'А'..'я' || char == 'ё' || char == 'Ё'
}
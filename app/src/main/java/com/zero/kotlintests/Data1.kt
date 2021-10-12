package com.zero.kotlintests

data class Data1(override val x: String, val y: String) : Data() {
    override fun toString(): String {
        return "$x: $y"
    }
}

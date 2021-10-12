package com.zero.kotlintests

data class Data2(override val x:String, val y:String, val z:String):Data() {

    override fun toString(): String {
        return "$x: $y: $z"
    }
}

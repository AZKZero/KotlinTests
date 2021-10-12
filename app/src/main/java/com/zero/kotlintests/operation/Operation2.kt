package com.zero.kotlintests.operation

import kotlin.properties.Delegates

class Operation2() : OperationBase() {
    override var x by Delegates.notNull<Int>()
    override var y by Delegates.notNull<Int>()
    override fun operationX(): String = "${x * y}"

    override fun operationY(): String = "$x{?}$y"

    override fun operationZ(): String = "${x % y}"
}
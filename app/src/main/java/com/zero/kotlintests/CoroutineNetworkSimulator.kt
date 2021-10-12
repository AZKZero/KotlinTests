package com.zero.kotlintests

import com.zero.kotlintests.operation.OperationBase
import kotlinx.coroutines.*
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

class CoroutineNetworkSimulator {
    fun simulateCallWithCallback(
        coroutineScope: CoroutineScope,
        x: Int,
        y: Int,
        d1: Boolean = false,
        function1: (data: Data) -> Unit
    ) {
        coroutineScope.launch {
            function1(call(x, y) { a, b ->
                assert(a > 0 && b > 0)
                if (d1) Data1("d.$a", "d1.$b") else Data2("d.$a", "d2.$b", "d2.${a + b}")
            }
            )
        }
    }

    private suspend fun <R> call(x: Int, y: Int, function: (x: Int, y: Int) -> R): R {
        delay(500L)
        return function(x + y, x - y)
    }

    private suspend fun <T : OperationBase> call2(
        x: Int,
        y: Int,
        z: T,
    ): T {
        delay(500L)
        return z.apply {
            this.x = x
            this.y = y
        }
    }

    fun <R : OperationBase> simulateCallWithGenericCallback(
        coroutineScope: CoroutineScope,
        x: Int,
        y: Int,
        ref: KClass<R>,
        function1: (s1: String, s2: String, s3: String) -> Unit
    ) {
        coroutineScope.launch {
            val result = call2(x, y, ref.createInstance())
            withContext(Dispatchers.Main) {
                function1(
                    result.operationX(),
                    result.operationY(),
                    result.operationZ()
                )
            }
        }
    }
}
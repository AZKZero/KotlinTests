package com.zero.kotlintests

import com.zero.kotlintests.operation.Operation1
import com.zero.kotlintests.operation.Operation2
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.core.IsInstanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test


class KotlinLambdaTest {
    private fun processedAddWithLambda(a: Int, b: Int, c: (x: Int, y: Int) -> Int): Int =
        a + c(a, b)

    lateinit var coroutineNetworkSimulator: CoroutineNetworkSimulator

    @Before
    fun setup() {
        coroutineNetworkSimulator = CoroutineNetworkSimulator()
    }

    @Test
    fun testLambda_multiply_multiplicationAddReturned() {
        val result = processedAddWithLambda(5, 6) { x, y -> x * y }
        assertThat(result, `is`(35))
    }

    @Test
    fun testLambda_division_divisionAddReturned() {
        val result = processedAddWithLambda(10, 20) { x, y -> y / x }
        assertThat(result, `is`(12))
    }

    @Test
    fun testLambda_simulation_coroutine_d() {
        runBlocking {
            coroutineNetworkSimulator.simulateCallWithCallback(this, 10, 5) { data ->
                assertThat(data, IsInstanceOf(Data::class.java))
                assertThat(data.x, `is`("d.15"))
            }
        }
    }

    @Test
    fun testLambda_simulation_coroutine_d1() {
        runBlocking {
            coroutineNetworkSimulator.simulateCallWithCallback(this, 10, 5, true) { data ->
                assertThat(data, IsInstanceOf(Data1::class.java))
                data as Data1
                assertThat(data.x, `is`("d.15"))
                assertThat(data.y, `is`("d1.5"))
            }
        }
    }

    @Test
    fun testLambda_simulation_coroutine_d2() {
        runBlocking {
            coroutineNetworkSimulator.simulateCallWithCallback(this, 10, 5, false) { data ->
                assertThat(data, IsInstanceOf(Data2::class.java))
                data as Data2
                assertThat(data.x, `is`("d.15"))
                assertThat(data.y, `is`("d2.5"))
                assertThat(data.z, `is`("d2.20"))
            }
        }
    }

    @Test
    fun testLambda_simulation_coroutine2_13_85_3() {
        runBlocking {
            coroutineNetworkSimulator.simulateCallWithGenericCallback(
                this,
                8,
                5,
                Operation1::class
            ) { s1, s2, s3 ->
                assertThat(s1, `is`("13"))
                assertThat(s2, `is`("85"))
                assertThat(s3, `is`("3"))
            }
        }
    }

    @Test
    fun testLambda_simulation_coroutine2_40_85_3() {
        runBlocking {
            coroutineNetworkSimulator.simulateCallWithGenericCallback(
                this,
                8,
                5,
                Operation2::class
            ) { s1, s2, s3 ->
                assertThat(s1, `is`("40"))
                assertThat(s2, `is`("8{?}5"))
                assertThat(s3, `is`("3"))
            }
        }
    }
}
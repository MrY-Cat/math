@file:Suppress("unUsed")

package yyd.mrycat.math.test

import yyd.mrycat.math.combinatorial.A
import yyd.mrycat.math.combinatorial.C
import yyd.mrycat.math.data.Matrix

/*✔测试结果：符合预期*/
fun test(name:String = "模板测试函数")
{
    println("----测试[$name]开始----")
    //println("test something here.")
    println("----测试[$name]结束----")
}

fun nowTest() = test5()

/*✔测试结果：符合预期*/
fun test5(name:String = "Matrix.det")
{
    println("----测试[$name]开始----")
    val m1 = Matrix(3, 3) { _, _ -> (0..1).random() }
    println(m1)
    println("m1.det:${m1.det()}")
    val m2 = Matrix(3, 4, arrayOf(0, 0, 0, 1, 1, 1))
    println(m2)
    try
    {
        println("m1.det:${m2.det()}")
    }
    catch(e:Exception)
    {
        println(e.message)
    }
    println("----测试[$name]结束----")
}
/*✔测试结果：符合预期*/
fun test4(name:String = "Matrix行的获取与设置")
{
    println("----测试[$name]开始----")
    val m = Matrix(4, 5, arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14))
    println(m)
    println(m.size)
    println(m[3].joinToString())
    m[2] = arrayOf(1, 2, 3, 4, 5)
    println(m[2].joinToString())
    m[2, 2] = -2
    println(m[2].joinToString())
    try
    {
        m[4] = arrayOf(2, 3)
        //m[5] = arrayOf(1, 1, 1, 1)
        //m[4, 5]+m[0, 0]
    }
    catch(e:Exception)
    {
        println(e.message)
    }
    println("----测试[$name]结束----")
}
/*✔测试结果：符合预期*/
fun test3(name:String = "Matrix的初始化")
{
    println("----测试[$name]开始----")
    val m1 = Matrix(3, 3, Array(9) { i -> i })
    val m4 = Matrix(3, 3) { i, j -> i*j }
    val m5 = Matrix(3, 3) { r, c -> r.toDouble()/c }
    val m6 = Matrix(3, 3, arrayOf(1, 1, 1))
    val m7 = Matrix(3, 3, arrayOf(10.0, 10.0, 10.0))
    val m8 = Matrix(3, 3, arrayOf(11L, 11L, 11L))
    val m9 = Matrix(3, 3, arrayOf(-1.0f, -1.0f, -1.0f))
    val m10 = Matrix(3, 3, arrayOf(2.toByte(), 2.toByte(), 2.toByte()))
    val m12 = Matrix(3, 3, arrayOf(222.toBigInteger(), 222.toBigInteger(), 222.toBigInteger()))
    val m13 = Matrix(3, 3, arrayOf(2222.toBigDecimal(), 2222.toBigDecimal(), 2222.toBigDecimal()))
    val m14 = Matrix(3, 3, arrayOf(2.toShort(), 2.toShort(), 2.toShort()))
    println(m1.joinToString()+"\n${m1[2, 2]}")
    println(m4.joinToString()+"\n${m4[2, 2]}")
    println(m5.joinToString()+"\n${m5[2, 2]}")
    println(m6.joinToString()+"\n${m6[2, 2]}")
    println(m7.joinToString()+"\n${m7[2][2]}")
    println(m8.joinToString())
    println(m9.joinToString())
    println(m10.joinToString())
    println(m12.joinToString())
    println(m13.joinToString())
    println(m14.joinToString())
    println("----测试[$name]结束----")
}
/*✔测试结果：已记录在[MathOverFlow]中*/
fun test2(name:String = "组合数溢出点计算")
{
    println("----测试[$name]开始----")
    println("溢出范围为：")
    var nStart = 0L
    var nEnd = 0L
    var rBefore = 0L
    for(n in 67L..3810780L)
    {
        for(r in 0L..n)
        {
            if(C(n, r) > Long.MAX_VALUE.toBigInteger())
            {
                if(rBefore != r)
                {
                    if(nStart == nEnd) println("${nStart}->r_left >= $rBefore")
                    else println("in ${nStart}..${nEnd}->r_left >= $rBefore")
                    rBefore = r
                    nStart = n
                    nEnd = n
                }
                else nEnd = n
                break
            }
        }
    }
    for(n in 4290000000L..4300000000L)
    {
        if(C(n, 2L) > Long.MAX_VALUE.toBigInteger())
        {
            println("in ${3810780}..${n-1}->r_left >= ${3}")
            println(C(n, 2L))
            break
        }
    }
    println(Long.MAX_VALUE)
    println("----测试[$name]结束----")
}
/*✔测试结果：已记录在[MathOverFlow]中*/
fun test1(name:String = "排列数溢出点计算")
{
    println("----测试[$name]开始----")
    val r = 2L
    println(Long.MAX_VALUE)
    for(n in 3037000498L..Long.MAX_VALUE)
    {
        if(A(n, r) > Long.MAX_VALUE.toBigInteger())
        {
            println(n-1)
            println(A(n-1, r))
            break
        }
    }
    println(A(3037000501L, r))
    println("----测试[$name]结束----")
}

@file:Suppress("unUsed")

import yyd.mrycat.math.combinatorial.A
import yyd.mrycat.math.combinatorial.C

/*✔测试结果：符合预期*/
fun test(name:String = "模板测试函数")
{
    println("----测试[$name]开始----")
    //println("test something here.")
    println("----测试[$name]结束----")
}

fun nowTest() = test2()

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

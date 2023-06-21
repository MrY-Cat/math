@file:Suppress("FunctionName")

package yyd.mrycat.math.util
/*✔[RFC-1]*/
import yyd.mrycat.math.exception.MathIllegalException

/**
 * 用于判断数值计算是否会溢出[Long.MAX_VALUE].
 *
 * 该类中的函数应完全记录什么情况会溢出，若只能通过强行运算的结果来判断，则没必要实现.
 */
object MathOverFlow
{
    /**
     * 精确判断阶乘公式若使用此参数，<返回值>是否会溢出[Long.MAX_VALUE].
     * @see yyd.mrycat.math.combinatorial
     */
    fun factorial(n:Int):Boolean = (n > 20)

    /**
     * 精确判断排列数公式若使用此参数，<返回值>是否会溢出[Long.MAX_VALUE].
     * @see yyd.mrycat.math.combinatorial
     */
    fun A(n:Int, r:Int):Boolean
    {
        if(n < 0 || r < 0) throw MathIllegalException("暂不支持计算负整数的排列数")
        if(n <= 20) return false
        return when(n.toLong())
        {
            21L -> r >= 19
            in 22..23 -> r >= 15
            in 24..25 -> r >= 14
            in 26..29 -> r >= 13
            in 30..35 -> r >= 14
            in 36..43 -> r >= 13
            in 44..58 -> r >= 12
            in 59..83 -> r >= 11
            in 84..132 -> r >= 10
            in 133..238 -> r >= 9
            in 239..515 -> r >= 8
            in 516..1450 -> r >= 7
            in 1451..6210 -> r >= 6
            in 6211..55110 -> r >= 5
            in 55111..2097153 -> r >= 4
            in 2097154L..3037000500L -> r >= 3
            in 3037000501L..Long.MAX_VALUE -> r >= 2
            else -> throw MathIllegalException("整型参数n不可能触及此when分支")
        }
    }

    /**
     * 精确判断组合数公式若使用此参数，<返回值>是否会溢出[Long.MAX_VALUE].
     * @see yyd.mrycat.math.combinatorial
     */
    fun C(n:Int, r:Int):Boolean
    {
        if(n < 0 || r < 0) throw MathIllegalException("暂不支持计算负整数的组合数")
        if(n <= 66) return false
        val r_left = if(r > n/2) n-r else r
        return when(n.toLong())
        {
            67L -> r_left >= 30
            68L -> r_left >= 28
            69L -> r_left >= 27
            70L -> r_left >= 26
            in 71..72 -> r_left >= 25
            in 73..74 -> r_left >= 24
            in 75..76 -> r_left >= 23
            in 77..79 -> r_left >= 22
            in 80..83 -> r_left >= 21
            in 84..88 -> r_left >= 20
            in 89..94 -> r_left >= 19
            in 95..101 -> r_left >= 18
            in 102..111 -> r_left >= 17
            in 112..125 -> r_left >= 16
            in 126..143 -> r_left >= 15
            in 144..169 -> r_left >= 14
            in 170..206 -> r_left >= 13
            in 207..265 -> r_left >= 12
            in 266..361 -> r_left >= 11
            in 362..534 -> r_left >= 10
            in 535..887 -> r_left >= 9
            in 888..1733 -> r_left >= 8
            in 1734..4337 -> r_left >= 7
            in 4338..16175 -> r_left >= 6
            in 16176..121977 -> r_left >= 5
            in 121978..3810779 -> r_left >= 4
            in 3810780L..4294967296L -> r_left >= 3
            in 4294967297L..Long.MAX_VALUE -> r_left >= 2
            else -> throw MathIllegalException("整型参数n不可能触及此when分支")
        }
    }

    /**
     * 精确判断错排公式若使用此参数，<返回值>是否会溢出[Long.MAX_VALUE].
     * @see yyd.mrycat.math.combinatorial
     */
    fun D(n:Int):Boolean = (n > 20)
}
/*100行[2023-06-21]*/
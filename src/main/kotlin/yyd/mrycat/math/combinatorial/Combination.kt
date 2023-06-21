@file:Suppress("unUsed", "FunctionName")

package yyd.mrycat.math.combinatorial
/*✔[RFC-1]*/
import yyd.mrycat.math.exception.MathIllegalException
import yyd.mrycat.math.exception.MathOverflowException
import yyd.mrycat.math.util.MathOverFlow
import java.math.BigInteger

/**
 * 组合数公式.
 * @return 从非负整数[n]中选[r](非负整数)个的组合数.
 * @throws MathIllegalException 当[n]或[r]为负整数时.
 */
fun C(n:Long, r:Long):BigInteger
{
    if(n < 0 || r < 0) throw MathIllegalException("暂不支持计算负整数的组合数")
    if(n < r) return BigInteger.ZERO
    if(n == r) return BigInteger.ONE
    if(2*r > n) return C(n, n-r)
    var result = BigInteger.ONE
    for(i in 1L..r)
    {
        result *= BigInteger.valueOf(n+1-i)
        result /= BigInteger.valueOf(i)     //此步计算结果必定为整数，证明略
    }
    return result
}

/**
 * 组合数公式.
 * @return 从非负整数[n]中选[r](非负整数)个的组合数.
 * @throws MathIllegalException 当n或r为负整数时.
 * @throws MathOverflowException 当返回值溢出[Long.MAX_VALUE]时.
 * @suppress 当[n]>66时，可能导致溢出，例如[n]=67时溢出的组合数为([n]=67,[r]=30)～([n]=67,[r]=37) 此时请使用[C].
 * @see MathOverFlow.C
 */
fun C(n:Int, r:Int):Long
{
    if(n < 0 || r < 0) throw MathIllegalException("暂不支持计算负整数的组合数")
    if(MathOverFlow.C(n, r)) throw MathOverflowException("数值溢出警告:组合数A(${n},${r})将超过Long.MAX_VALUE，请使用返回BigInteger的另一计算函数")
    if(n < r) return 0L
    if(n == r) return 1L
    if(2*r > n) return C(n, n-r)
    var result = 1L
    for(i in 1L..r)
    {
        result *= n+1-i
        result /= i     //此步计算结果必定为整数，证明略
    }
    return result
}
/*53行[2023-06-21]*/
@file:Suppress("unUsed", "FunctionName")

package yyd.mrycat.math.combinatorics
/*✔[RFC-1]*/
import yyd.mrycat.math.exception.MathIllegalException
import yyd.mrycat.math.exception.MathOverflowException
import yyd.mrycat.math.util.MathOverFlow
import java.math.BigInteger

/**
 * 排列数公式.
 * @return 从非负整数[n]中选[r](非负整数)个的排列数.
 * @throws MathIllegalException 当[n]或[r]为负整数时.
 */
fun A(n:Long, r:Long):BigInteger
{
    if(n < 0 || r < 0) throw MathIllegalException("暂不支持计算负整数的排列数")
    if(n < r) return BigInteger.ZERO
    if(n == r || n-1 == r) return factorial(n)
    var result = BigInteger.ONE
    for(i in n downTo n+1-r) result *= BigInteger.valueOf(i)
    return result
}

/**
 * 排列数公式.
 * @return 从非负整数[n]中选[r](非负整数)个的排列数.
 * @throws MathIllegalException 当[n]或[r]为负整数时.
 * @throws MathOverflowException 当返回值溢出[Long.MAX_VALUE]时.
 * @suppress 当对每个[n]依次增加[r]时，首先溢出的排列数们为A([n]=21,[r]≥19)、A([n]=22,[r]≥17)...此时请使用[A].
 * @see MathOverFlow.A
 */
fun A(n:Int, r:Int):Long
{
    if(n < 0 || r < 0) throw MathIllegalException("暂不支持计算负整数的排列数")
    if(MathOverFlow.A(n, r)) throw MathOverflowException("数值溢出警告:排列数A(${n},${r})将超过Long.MAX_VALUE，请使用返回BigInteger的另一计算函数")
    if(n < r) return 0L
    if(n == r || n-1 == r) return factorial(n)
    var result = 1L
    for(i in n downTo n+1-r) result *= i.toLong()
    return result
}
/*43行[2023-06-21]*/
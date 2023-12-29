/**阶乘**/
package yyd.mrycat.math.combinatorics
/*✔[RFC-1]*/
import yyd.mrycat.math.exception.MathIllegalException
import yyd.mrycat.math.exception.MathOverflowException
import yyd.mrycat.math.util.MathOverflow
import java.math.BigInteger

/**
 * 阶乘公式.
 * @return 非负整数[n]的阶乘.
 * @throws MathIllegalException 当[n]为负数时.
 */
fun factorial(n:Long):BigInteger
{
    if(n < 0L) throw MathIllegalException("不支持计算负整数的阶乘")
    if(n in 0L..1L) return BigInteger.ONE
    var result = BigInteger.ONE
    for(i in 2L..n) result *= BigInteger.valueOf(i)
    return result
}

/**
 * 阶乘公式.
 * @return 非负整数[n]的阶乘.
 * @throws MathIllegalException 当[n]为负数时.
 * @throws MathOverflowException 当返回值溢出[Long.MAX_VALUE]时
 * @suppress 当[n]>20时返回值将必定溢出，此时请使用返回[BigInteger]的[同名函数][factorial].
 */
fun factorial(n:Int):Long
{
    if(n < 0) throw MathIllegalException("不支持计算负整数的阶乘")
    if(MathOverflow.factorial(n)) throw MathOverflowException("数值溢出警告:阶乘${n}!将超过Long.MAX_VALUE，请使用返回BigInteger的另一计算函数")
    if(n in 0..1) return 1L
    var result = 1L
    for(i in 2L..n.toLong()) result *= i
    return result
}
/*38行[2023-06-06]*/
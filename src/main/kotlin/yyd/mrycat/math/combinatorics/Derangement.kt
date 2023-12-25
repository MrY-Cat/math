@file:Suppress("unUsed", "FunctionName")

package yyd.mrycat.math.combinatorics
/*✔[RFC-1]*/
import yyd.mrycat.math.exception.MathIllegalException
import yyd.mrycat.math.exception.MathOverflowException
import yyd.mrycat.math.util.MathOverflow
import java.math.BigInteger

/**
 * 错排公式.
 * @return 正整数[n]的错排数.
 * @throws MathIllegalException 当[n]<1时.
 */
fun D(n:Long):BigInteger
{
    if(n < 0) throw MathIllegalException("不支持计算非正整数的错排数")
    if(n == 1L) return BigInteger.ZERO
    if(n == 2L) return BigInteger.ONE
    //return factorial(n).toBigDecimal().divide(MathConstant.E, 256, RoundingMode.HALF_UP).add(BigDecimal.valueOf(0.5)).toBigInteger()
    //若使用简化公式速度相比通项更慢，精度越大越慢。
    var result = BigInteger.ZERO
    for(i in 2L..n)
    {
        result += if(i%2 == 0L) A(n, n-i) else -A(n, n-i)
    }
    return result
}

/**
 * 错排公式.
 * @return 正整数[n]的错排数.
 * @throws MathIllegalException 当[n]<1时.
 * @throws MathOverflowException 当返回值溢出[Long.MAX_VALUE]时.
 * @suppress 当[n]>20时返回值将必定溢出，此时请使用返回[BigInteger]的[D].
 */
fun D(n:Int):Long
{
    if(n < 0) throw MathIllegalException("不支持计算非正整数的错排数")
    if(MathOverflow.D(n)) throw MathOverflowException("数值溢出警告:错排数D(${n})的值将超过Long.MAX_VALUE，请使用返回BigInteger的另一计算函数")
    if(n == 1) return 0
    if(n == 2) return 1
    var result = 0L
    for(i in 2..n)
    {
        result += if(i%2 == 0) A(n, n-i) else -A(n, n-i)
    }
    return result
    /*
    1.递推公式D(n)=(n-1)*(D(n-1)+D(n-2))速度比通项公式略慢。
    2.简化公式D(n)=[n!/e+1/2]若采用Double运算，速度与通项公式接近，但在n>18时不能得到精确结果。
    3.简化公式若采用BigDecimal.valueOf(factorial(n)).divide(MathConstant.E, 500, RoundingMode.HALF_UP).add(BigDecimal.valueOf(0.5)).toLong()计算，结果精确但速度非常慢。
    */
}
/*55行[2023-06-21]*/
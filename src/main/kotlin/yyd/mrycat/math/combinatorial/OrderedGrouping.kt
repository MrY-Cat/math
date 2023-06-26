@file:Suppress("unUsed", "FunctionName")

package yyd.mrycat.math.combinatorial
/*✔[RFC-1]*/
import yyd.mrycat.math.exception.MathIllegalException
import yyd.mrycat.math.exception.MathOverflowException
import java.math.BigInteger

/**
 * 有序分组种类数.
 *
 * 即将N（无需给出，将根据[eachGroupCount]求和得出）个不同的小球分为有顺序的若干组，每组的数目给定，分组情况的种类数.
 *
 * @return 分组情况的种类数.
 * @param eachGroupCount 每一组指定的个数.
 * @throws MathIllegalException 当某一组指定的个数为负数时.
 *
 */
fun orderedGroupingSpeciesNumber(vararg eachGroupCount:Long):BigInteger
{
    if(eachGroupCount.any { it < 0L }) throw MathIllegalException("有序分组中，每一组所需的个数不能为负数")
    val n = eachGroupCount.sum()
    var result = factorial(n)
    eachGroupCount.forEach()
    {
        result /= factorial(it)  //此步结果一定为整数
    }
    return result
}

/**
 * 有序分组种类数.
 *
 * 即将N（无需给出，将根据[eachGroupCount]求和得出）个不同的小球分为有顺序的若干组，每组的数目给定且≥0，分组情况的种类数.
 *
 * @return 分组情况的种类数.
 * @param eachGroupCount 每一组指定的个数.
 * @throws MathIllegalException 当某一组指定的个数为负数时.
 * @throws MathOverflowException 当计算中间值溢出[Long.MAX_VALUE]时.
 * @suppress 当N>20时，计算中间值N!将必定溢出，此时请使用[orderedGroupingSpeciesNumber].
 */
fun orderedGroupingSpeciesNumber(vararg eachGroupCount:Int):Long
{
    if(eachGroupCount.any { it < 0 }) throw MathIllegalException("有序分组中，每一组所需的个数不能为负数")
    val n = eachGroupCount.sum()
    var result = factorial(n)
    eachGroupCount.forEach()
    {
        result /= factorial(it)  //此步结果一定为整数
    }
    return result
}
/*53行[2023-06-21]*/
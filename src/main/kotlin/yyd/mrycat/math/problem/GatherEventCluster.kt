package yyd.mrycat.math.problem

import yyd.mrycat.math.combinatorics.orderedGroupingSpeciesNumber
import yyd.mrycat.math.combinatorics.orderedIntegerPartition
import yyd.mrycat.math.data.Matrix
import yyd.mrycat.math.exception.MathIllegalException
import yyd.mrycat.math.util.MathConstant
import java.lang.StrictMath.pow
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.absoluteValue

/**
 * 集齐完备事件组期望次数.
 *
 * 该算法采用基于期望公式和拆分矩阵的方法，计算对N（无需给出，即[probabilities]中元素数）个事件构成的完备事件组，使每个事件都恰好至少发生一次所需的总试验次数X的期望E(X).
 *
 * 若要查看该算法的公式，请访问[公式][https://github.com/MrY-Cat/math/tree/main/image/gatherEventClusterEX.jpg].
 * @param probabilities 每个事件的概率(顺序不影响结果).
 * @param InfiniteSeriesCalculationAccuracy 当存在某个概率≠1/N时，算法中无穷级数计算的项数，不指定时默认为10/[probabilities].[min]，注意：项数过少可能导致结果与解析解有较大误差.
 * @throws MathIllegalException 当某个概率不属于0..1或概率总和不属于(1-1E-15)..(1+1E-15)时.
 * @see gatherEventClusterEY
 */
fun gatherEventClusterEX(vararg probabilities:Double, InfiniteSeriesCalculationAccuracy:Int = (10/probabilities.min()).toInt()):BigDecimal
{
    if(probabilities.any { it < 0.0 || it > 1.0 }) throw MathIllegalException("n个事件的概率均应属于0到1")
    if((1.0-probabilities.sum()).absoluteValue > MathConstant.DoublePlusErrorAccuracy) throw MathIllegalException("n个事件的概率总和应为1.0")
    val n = probabilities.size
    var result = BigDecimal.ZERO
    if(!probabilities.any { it != probabilities[0] })
    {//等概率情况下
        for(i in 1..n)
        {
            result += n.toBigDecimal().divide(i.toBigDecimal(), 256, RoundingMode.HALF_UP)
        }
        return result
    }
    for(r in n..InfiniteSeriesCalculationAccuracy)
    {
        var pEr = BigDecimal.ZERO
        val splitMatrix = orderedIntegerPartition(r-1, n-1).toMatrix<Long>()//计算最内层循环所用到的拆分矩阵
        for(i in 1..n)
        {
            var pAi = BigDecimal.ZERO
            for(j in 1..splitMatrix.row)
            {
                var pBj = BigDecimal.ONE
                for(k in 1..n)
                {
                    if(k == i) continue//第i个元素不在拆分范围内
                    val locate:Int = if(k < i) k else k-1
                    pBj *= BigDecimal.valueOf(probabilities[k-1]).pow(splitMatrix[j, locate].toInt())
                }
                pBj *= orderedGroupingSpeciesNumber(*splitMatrix[j].toLongArray()).toBigDecimal()//有序分组种类数
                pAi += pBj
            }
            pEr += BigDecimal.valueOf(probabilities[i-1])*pAi
        }
        result += BigDecimal.valueOf(r.toLong())*pEr
    }
    return result
}

/**
 * 集齐完备事件组期望次数.
 *
 * 该算法采用基于马尔科夫链的方法，计算对N（无需给出，即[probabilities]中元素数）个事件构成的完备事件组，使每个事件都恰好至少发生一次所需的总试验次数Y的期望E(Y).
 * @param probabilities 每个事件的概率(顺序不影响结果).
 * @throws MathIllegalException 当某个概率不属于0..1或概率总和不属于(1-1E-15)..(1+1E-15)时.
 * @see gatherEventClusterEX
 */
fun gatherEventClusterEY(vararg probabilities:Double):BigDecimal
{
    if(probabilities.any { it < 0.0 || it > 1.0 }) throw MathIllegalException("n个事件的概率均应属于0到1")
    if((1.0-probabilities.sum()).absoluteValue > MathConstant.DoublePlusErrorAccuracy) throw MathIllegalException("n个事件的概率总和应为1.0")
    val n = probabilities.size
    var result = BigDecimal.ZERO


    for(s in 0..n-1)//s个独立变量，每个都可能是1-n
    {
        val matrix = Matrix(pow(n.toDouble(), s.toDouble()).toInt(), s) { _, _ -> 0 }

        for(i in 1..pow(n.toDouble(), s.toDouble()).toLong())//共n^s种情况
        {
            result = BigDecimal.ZERO//
        }
    }


    return result
}

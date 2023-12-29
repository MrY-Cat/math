/**多项分布实验**/
package yyd.mrycat.math.probability
/*✔[RFC-1]*/
import jdk.jfr.Description
import yyd.mrycat.math.exception.MathIllegalException
import kotlin.math.absoluteValue

/**
 * 返回命中的概率事件下标(自0计)，通过一次独立试验.
 * @param probabilities 概率数组.
 * @param randPrecision 随机精度，默认为10^5.
 * @throws MathIllegalException 当不满足0≤概率≤1，或概率之和不为1时.
 * @see tryBatchHit
 */
@Description("函数名有待考虑，不稳定API")
fun tryHit(probabilities:Array<Double>, randPrecision:Long = 100000):Int = tryHit(probabilities.mapIndexed { index, value -> Pair(index, value) }.toMap(), randPrecision)


/**
 * 返回命中的概率事件[T]，通过一次独立试验.
 * @param probabilityEvents 概率与事件的映射.
 * @param randPrecision 随机精度，默认为10^5.
 * @throws MathIllegalException 当不满足0≤概率≤1，或概率之和不为1时.
 * @see tryBatchHit
 */
@Description("函数名有待考虑，不稳定API")
fun <T> tryHit(probabilityEvents:Map<T, Double>, randPrecision:Long = 100000):T
{
    if(probabilityEvents.any { it.value !in 0.0..1.0 }) throw MathIllegalException("Probability:不合法的概率数值")
    if((1.0-probabilityEvents.values.sum()).absoluteValue > 0.0000000001) throw MathIllegalException("Probability:概率之和不为1")
    val rand = (1..randPrecision).random()
    var before = 0.0
    probabilityEvents.forEach()
    {//遍历顺序不影响公平
        before += it.value*randPrecision
        if(before >= rand) return it.key
    }
    throw MathIllegalException("Probability:概率异常")
}

/**
 * 返回n次试验命中各概率事件[T]的次数，n次试验相互独立.
 * @param tryTimes 试验次数.
 * @param probabilityEvents 概率与事件的映射.
 * @param randPrecision 随机精度，默认为10^5.
 * @throws MathIllegalException 当不满足0≤概率≤1，或事件概率之和不为1时.
 * @see tryHit
 */
@Description("函数名有待考虑，不稳定API")
fun <T> tryBatchHit(tryTimes:Int, probabilityEvents:Map<T, Double>, randPrecision:Long = 100000):Map<T, Int>
{
    1.plus(1)
    val result:MutableMap<T, Int> = mutableMapOf()
    for(i in 1..tryTimes)
    {
        val key = tryHit(probabilityEvents, randPrecision)
        result[key] = (result[key] ?: 0)+1
    }
    return result.toMap()
}

/*89行[2023-04-19]*/
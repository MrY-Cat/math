/**伯努利实验**/
package yyd.mrycat.math.probability

import jdk.jfr.Description
import yyd.mrycat.math.exception.MathIllegalException


/**
 * 返回是否命中某概率，通过一次伯努利试验.
 * @param probability 概率.
 * @param randPrecision 随机精度，默认为10^5.
 * @throws MathIllegalException 当不满足0≤概率≤1时.
 * @see tryBatchHit
 */
@Description("函数名有待考虑，不稳定API")
fun tryHit(probability:Double, randPrecision:Long = 100000):Boolean = tryBatchHit(1, probability, randPrecision) == 1

/**
 * 返回n次试验命中某概率的次数，通过n次独立伯努利试验.
 * @param tryTimes 试验次数.
 * @param probability 概率.
 * @param randPrecision 随机精度，默认为10^5.
 * @throws MathIllegalException 当不满足0≤概率≤1时.
 * @see tryHit
 */
@Description("函数名有待考虑，不稳定API")
fun tryBatchHit(tryTimes:Int, probability:Double, randPrecision:Long = 100000):Int
{
    if(probability !in 0.0..1.0) throw MathIllegalException("Probability:不合法的概率数值")
    var hitCount = 0
    for(i in 1..tryTimes)
    {
        if((1..randPrecision).random() <= randPrecision*probability) hitCount++
    }
    return hitCount
}

@file:Suppress("unUsed", "FunctionName")

package yyd.mrycat.math.util
/*✔[RFC-1]*/
import java.math.BigDecimal

/**
 * 用于存储一些数学常数.
 */
object MathConstant
{
    /**
     * 自然常数，精确到128位小数.
     */
    val E = BigDecimal("2.71828182845904523536028747135266249775724709369995957496696762772407663035354759457138217852516642742746639193200305992181741359")

    /**
     * 圆周率π，精确到128位小数.
     */
    val PI = BigDecimal("3.14159265358979323846264338327950288419716939937510582097494459230781640628620899862803482534211706798214808651328230664709384460")

    /**
     * 浮点数加减时可能产生的小数误差.
     *
     * 例如，判断1.0/3+1.0/3+1.0/3是否是1时，需要看与1的误差是否大于本数更稳妥.
     */
    const val DoublePlusAccuracyError = 0.000000000000001
}
/*22行[2023-06-21]*/
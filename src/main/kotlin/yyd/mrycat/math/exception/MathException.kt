/**数学异常**/
package yyd.mrycat.math.exception
/*✔[RFC-1]*/

/**
 * 数学异常总接口.
 *
 * 本数学包抛出的异常均应继承此接口.
 */
sealed interface MathException

/**
 * 数学非法异常.
 */
class MathIllegalException(message:String):Exception(message), MathException

/**
 * 数学越界异常.
 */
class MathIndexOutOfBoundsException(message:String):IndexOutOfBoundsException(message), MathException

/**
 * 数据溢出异常.
 */
class MathOverflowException(message:String):Exception(message), MathException
/*25行[2023-06-06]*/
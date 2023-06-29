@file:Suppress("unUsed", "FunctionName")

package yyd.mrycat.math.combinatorics
/*✔[RFC-1]*/
import yyd.mrycat.math.data.Matrix
import yyd.mrycat.math.exception.MathIllegalException
import yyd.mrycat.math.exception.MathOverflowException
import yyd.mrycat.math.util.MathOverflow
import java.math.BigInteger

/**
 * 组合数公式.
 * @return 从非负整数[n]中选[r](非负整数)个的组合数.
 * @throws MathIllegalException 当[n]或[r]为负整数时.
 * @see combination
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
 * @suppress 当对每个[n]依次增加[r]时，首先溢出的组合数们为C([n]=67，30≤[r]≤37)、C([n]=68，28≤[r]≤40)...此时请使用[C].
 * @see combination
 * @see MathOverflow.C
 */
fun C(n:Int, r:Int):Long
{
    if(n < 0 || r < 0) throw MathIllegalException("暂不支持计算负整数的组合数")
    if(MathOverflow.C(n, r)) throw MathOverflowException("数值溢出警告:组合数A(${n},${r})将超过Long.MAX_VALUE，请使用返回BigInteger的另一计算函数")
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

/**
 * 组合结果矩阵.
 *
 * 生成算法为迭代法，按照生成规则，根据一种结果迭代下一种。第一种组合结果为：1,2,...,r-1,r.
 *
 * 生成规则：根据当前组合结果，按照"从右往左首个小于n-j(j为该元素右侧元素数)的元素b增加1，同时b右侧的所有元素从左到右依次变为其左侧元素+1"得到下一个组合结果.
 * @return 从[n]个元素(1,2,..,n)中选[r]个的组合的结果矩阵，该矩阵共有[C] ([n],[r])行，[r]列，每行存储一种组合结果，每种结果中的元素都是自小到大排列的.
 * @throws MathIllegalException 当n或r为负整数时.
 * @throws MathOverflowException 当矩阵行数溢出[Int.MAX_VALUE]时.
 * @see C
 */
fun combination(n:Int, r:Int):Matrix<Int>
{
    if(n < 0 || r < 0) throw MathIllegalException("暂不支持生成负整数的组合结果")
    val speciesNumber = C(n.toLong(), r.toLong())
    if(speciesNumber > Int.MAX_VALUE.toBigInteger()) throw MathOverflowException("数值溢出警告:组合结果矩阵的行数将超过Int.MAX_VALUE，暂不支持存储如此多行的矩阵")
    val matrix = Matrix(speciesNumber.toInt(), r) { _, _ -> 0 }
    /*生成规则：
      首个组合结果为[1,2,...,r-1,r]
      根据当前组合结果，按照"从右往左首个小于n-j(j为该元素右侧元素数)的元素b增加1，同时b右侧的所有元素从左到右依次变为其左侧元素+1"得到下一个组合结果
    */
    matrix[1] = List(matrix.col) { index -> index+1 }
    for(row in 1 until matrix.row)
    {//根据上一行按规则变动得到下一行
        for(c in matrix.col downTo 1)
        {//寻找b的位置
            if(matrix[row, c] < n-(matrix.col-c))
            {//找到b的列数c
                matrix[row+1, c] = matrix[row, c]+1
                for(j in 1..matrix.col)
                {
                    if(j < c) matrix[row+1, j] = matrix[row, j]
                    if(j > c) matrix[row+1, j] = matrix[row+1, j-1]+1
                }
                break
            }
        }
    }
    return matrix
}
/*97行[2023-06-29]*/
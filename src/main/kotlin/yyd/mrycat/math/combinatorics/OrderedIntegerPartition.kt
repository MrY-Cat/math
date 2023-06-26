@file:Suppress("unUsed", "FunctionName")

package yyd.mrycat.math.combinatorics
/*✔[RFC-1]*/
import yyd.mrycat.math.data.Matrix
import yyd.mrycat.math.exception.MathIllegalException
import yyd.mrycat.math.exception.MathOverflowException
import java.math.BigInteger

/**
 * 正整数有序拆分种类数.
 *
 * 即将正整数[n]拆分为[split]个考虑顺序的正整数之和，拆分情况的种类数，其其等价于调用[C] ([n]-1,[split]-1).
 * @return 拆分情况的种类数.
 * @throws MathIllegalException 当[n]<=0或[split]<=0时.
 * @see OrderedIntegerPartition
 */
fun OrderedIntegerPartitionSpeciesNumber(n:Long, split:Long):BigInteger = C(n-1, split-1)

/**
 * 正整数有序拆分种类数.
 *
 * 即将正整数[n]拆分为[split]个考虑顺序的正整数之和，拆分情况的种类数，其等价于调用[C] ([n]-1,[split]-1).
 * @return 拆分情况的种类数.
 * @throws MathIllegalException 当[n]<=0或[split]<=0时.
 * @throws MathOverflowException 当返回值溢出[Long.MAX_VALUE]时.
 * @suppress 当对每个[n]依次增加[split]时，首先溢出的情况为([n]=68,31≤[split]≤38)、([n]=69,29≤[split]≤41)...此时请使用[OrderedIntegerPartitionSpeciesNumber].
 * @see OrderedIntegerPartition
 */
fun OrderedIntegerPartitionSpeciesNumber(n:Int, split:Int):Long = C(n-1, split-1)

/**
 * 正整数有序拆分结果矩阵.
 *
 * 拆分算法为迭代法，按照遍历规则，根据一种结果迭代下一种。第一种拆分结果为：首个元素是[n]-[split]+1，其余[split]-1个元素是1.
 *
 * 遍历规则：根据当前拆分结果，按照"从右往左除末尾元素外首个非1元素将1分给其右侧元素nₖ，同时b右侧的所有元素将自身仅保留1余下的数还给nₖ"得到下一种结果.
 * @param n 被拆分的正整数.
 * @param split 拆分个数.
 * @return 正整数[n]有序拆分为[split]个正整数之和的拆分结果矩阵，该矩阵共有[C] ([n]-1,[split]-1)行，[split]列，每行存储一种有序拆分结果.
 * @throws MathIllegalException 当[n]<=0或[split]<=0时.
 * @throws MathOverflowException 当矩阵行数溢出[Int.MAX_VALUE]时.
 */
fun OrderedIntegerPartition(n:Int, split:Int):Matrix<Int>
{
    if(n <= 0 || split <= 0) throw MathIllegalException("被拆分数n和split均应>0")
    val speciesNumber = OrderedIntegerPartitionSpeciesNumber(n, split)
    if(speciesNumber > Int.MAX_VALUE) throw MathOverflowException("数值溢出警告:拆分矩阵的行数将超过Int.MAX_VALUE，暂不支持这么多行的矩阵")
    val matrix = Matrix(speciesNumber.toInt(), split) { _, _ -> 0 }
    /*拆分算法：
      首个拆分结果为[n-split+1,...,1]
      根据前一个拆分结果，按照规则"从右往左除末尾元素外首个非1元素a将1分给其右侧元素nₖ，同时b右侧的所有元素将自身仅保留1余下的数还给nₖ"得到下一个拆分结果
    */
    matrix[1] = List(matrix.col) { index -> if(index == 0) n-split+1 else 1 }//初始化首行拆分结果
    for(r in 1 until matrix.row)
    {//根据上一行按规则变动得到下一行
        var bCol:Int
        var bGetFromRight = 0
        for(c in matrix.col downTo 1)
        {//根据r行，寻找a、b的位置，并计算b右侧应收缴的总数，找到后得到下一行
            val element = matrix[r, c]
            if(c != matrix.col && element > 1)
            {//找到a的列数
                bCol = c+1 //b的列数
                matrix[r+1, c] = matrix[r, c]-1//a拿1给b
                matrix[r+1, bCol] += bGetFromRight+1//b拿到a给的1和从右侧收缴的数，注意在此之前b已经变为1
                //a之前的数保持原样
                for(j in 1 until c)
                {
                    matrix[r+1, j] = matrix[r, j]
                }
                break//新行已得到，退出寻找
            }
            else
            {
                if(element > 1) bGetFromRight += element-1//收缴该位置多余的数，保留1
                matrix[r+1, c] = 1//新行的该位置填1
            }
        }
    }
    return matrix
}
/*83行[2023-06-26]*/
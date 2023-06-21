package yyd.mrycat.math
//myMath.kt
import yyd.mrycat.math.combinatorial.C
import yyd.mrycat.math.combinatorial.factorial
import yyd.mrycat.math.data.Matrix
import yyd.mrycat.math.exception.MathException
import yyd.mrycat.math.exception.MathIllegalException
import java.math.BigInteger



/**
 * 正整数有序拆分.
 *
 * 该算法时间复杂度为 O(split*C(n-1,split-1))，即和矩阵元素数成正比.
 * @param n 被拆分的正整数.
 * @param split 拆分为多少个个正整数之和.
 * @return n有序拆分得到的拆分矩阵M.
 *
 * M共C(n-1,split-1)行，每行存储一种有序拆分结果，每行有split列.
 * @see Matrix
 * @exception Exception 被拆分数n和split均应为正整数.
 * @author qq2278010681
 */
fun integerPartitionOrderly(n:Int, split:Int):Matrix<Int>
{
    if(n<=0||split<=0) throw MathIllegalException("被拆分数n和split均应为正整数")
    val matrix= Matrix(C(n-1, split-1).toInt(), split){ _, _->0}//拆分矩阵
    /*拆分结果算法为：
      首个拆分结果为[n-split+1,...,1]
      根据前一个拆分结果，按照规则"从右往左除末尾元素外首个不是1的元素a将自身分1给其右侧元素b，同时b右侧所有元素把自身仅保留1，多出的数还给b"便得到下一个拆分结果
    */
    for(j in 1..matrix.col)
    {//初始化首行拆分结果
        matrix[1, j] = if(j == 1) n-split+1 else 1
    }
    for(i in 2..matrix.row)
    {//根据上一行按规则变动得到下一行
        var bGetFromRight = 0
        var b:Int
        for(j in matrix.col downTo 1)
        {//根据上一行，寻找a、b的位置，并计算b右侧应收缴的总数，找到后得到当前行
            val nowNum = matrix[i-1, j]
            if(j != matrix.col && nowNum != 1)
            {//找到该j为a的位置
                b = j+1 //b的位置
                matrix[i, j] = matrix[i-1, j]-1//a拿出1给b
                matrix[i, b] += bGetFromRight+1//b拿到a给的1和从右侧收缴的数，注意在此之前b已经变为1
                //将a之前的数保持原样
                for(temp in 1 until j)
                {
                    matrix[i, temp] = matrix[i-1, temp]
                }
                break//当前行已得到，退出寻找以计算下一行
            }
            else
            {
                if(nowNum>1) bGetFromRight += nowNum-1//收缴该位置多余的数，保留1
                matrix[i, j] = 1//将新行的该位置填1
            }
        }
    }
    return matrix
}

@file:Suppress("unUsed", "FunctionName")

package yyd.mrycat.math.combinatorial
//myMath.kt
import yyd.mrycat.math.data.Matrix
import yyd.mrycat.math.exception.MathIllegalException


/**
 * 正整数有序拆分结果.
 *
 * 拆分算法为迭代法，按照遍历规则，根据一种结果迭代下一种。第一种拆分结果为：首个元素是[n]-[split]+1，其余[split]-1个元素是1.
 *
 * 遍历规则：根据当前拆分结果，按照"从右往左除末尾元素外首个非1元素将1分给其右侧元素nₖ，同时b右侧的所有元素将自身仅保留1余下的数还给nₖ"得到下一种结果.
 * @param n 被拆分的正整数.
 * @param split 拆分个数.
 * @return 正整数[n]有序拆分为[split]个正整数之和的拆分结果矩阵，该矩阵共有[C] ([n]-1,[split]-1)行，[split]列，每行存储一种有序拆分结果.
 * @exception MathIllegalException 当[n]<=0或[split]<=0时.
 */
fun OrderedIntegerPartition(n:Int, split:Int):Matrix<Int>
{
    if(n <= 0 || split <= 0) throw MathIllegalException("被拆分数n和split均应为正整数")
    val matrix = Matrix(C(n-1, split-1).toInt(), split) { _, _ -> 0 }//拆分矩阵
    /*拆分算法：
      首个拆分结果为[n-split+1,...,1]
      根据前一个拆分结果，按照规则"从右往左除末尾元素外首个非1元素将1分给其右侧元素nₖ，同时b右侧的所有元素将自身仅保留1余下的数还给nₖ"得到下一个拆分结果
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
                if(nowNum > 1) bGetFromRight += nowNum-1//收缴该位置多余的数，保留1
                matrix[i, j] = 1//将新行的该位置填1
            }
        }
    }
    return matrix
}

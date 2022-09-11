package my.math
//myMath.kt
import java.math.BigInteger

/**
 * 阶乘公式.
 * @return 非负整数n的阶乘.
 * @exception Exception 阶乘的参数不能为负整数.
 * @author qq2278010681
 */
fun factorial(n:Int):BigInteger
{
    if(n<0) throw Exception("阶乘的参数不能为负整数")
    if(n==0) return BigInteger.ONE
    var bigResult=BigInteger.ONE
    for(i in 1L..n.toLong())
    {
        bigResult*=BigInteger.valueOf(i)
    }
    return bigResult
}

/**
 * 排列数公式.
 * @return 从n(非负整数)中选r(非负整数)个的排列数.
 * @exception Exception 参数n和r均不能为负整数.
 * @author qq2278010681
 */
@Suppress("FunctionName")
fun A(n:Int, r:Int): BigInteger
{
    if(n < 0 || r < 0) throw Exception("参数n和r均不能为负整数")
    if(n < r) return BigInteger.ZERO
    if(n == r) return factorial(n)
    var bigResult = BigInteger.ONE
    for(i in 1L..r.toLong())
    {
        bigResult *= BigInteger.valueOf(n+1-i)
    }
    return bigResult
}

/**
 * 组合数公式.
 * @return 从n(非负整数)中选r(非负整数)个的组合数.
 * @exception Exception 参数n和r均不能为负整数.
 * @author qq2278010681
 */
@Suppress("FunctionName")
fun C(n:Int, r:Int): BigInteger
{
    if(n < 0 || r < 0) throw Exception("参数n和r均不能为负整数")
    if(n < r) return BigInteger.ZERO
    if(n == r) return BigInteger.ONE
    if(2 * r > n) return C(n, n - r)
    var bigResult=BigInteger.ONE
    for(i in 1L..r.toLong())
    {
        bigResult*=BigInteger.valueOf((n+1-i))
        bigResult /= BigInteger.valueOf(i)//由于连续的i个数中必有i的倍数，此步计算结果为整数
    }
    return bigResult
}

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
    if(n<=0||split<=0) throw Exception("被拆分数n和split均应为正整数")
    val matrix= Matrix(C(n - 1, split - 1).toInt(), split){_,_->0}//拆分矩阵
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

/**
 * 有序分组.
 *
 * 即将n个小球分为有顺序的若干组，每组为给定的数目.
 * @return 分组情况的种类数.
 * @param n 总数.
 * @param countInGroups 每一组指定的个数.
 * @exception Exception 总数n和每一组所需的个数均不能为负数.
 * @exception Exception 每一组所需个数之和应等于总数n.
 * @author qq2278010681
 */
fun groupingCombinationsOrderly(n:Int, vararg countInGroups:Int):BigInteger
{
    if(n<0||countInGroups.any{it<0}) throw Exception("总数n和每一组所需的个数均不能为负数")
    if(n!=countInGroups.sum()) throw Exception("每一组所需个数之和应等于总数n")
    //由排列组合公式知道，结果为n!/(countInGroups[0]!*countInGroups[1]!*...)
    var bigResult= factorial(n)
    countInGroups.forEach()
    {
        bigResult/= factorial(it)
    }
    return bigResult
}

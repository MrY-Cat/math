package yyd.mrycat.math.data
/*✔[RFC-1]*/
import yyd.mrycat.math.exception.MathIllegalException
import yyd.mrycat.math.exception.MathIndexOutOfBoundsException
import java.math.BigDecimal
import java.math.BigInteger

/**
 * 矩阵类.
 *
 * @param row 矩阵的总行数，访问时自1计.
 * @param col 矩阵的总列数，访问时自1计.
 * @param init 指派r行c列元素的映射.
 * @param T 矩阵元素类型.
 */
class Matrix<T:Number>(val row:Int, val col:Int, private val init:(Int, Int) -> T)
{
    /**
     * 用于构造矩阵的伴生对象.
     * @see invoke
     * @see invoke
     */
    companion object
    {
        /**
         * 默认构造函数，将r行c列的元素初始化为init(r,c).
         */
        inline operator fun <reified T:Number> invoke(row:Int, col:Int, noinline init:(Int, Int) -> T) = Matrix(row, col, init)
        /**
         * 根据给定[数组][arr]初始化矩阵元素，若数组元素不足，后续元素将初始化为0.
         */
        inline operator fun <reified T:Number> invoke(row:Int, col:Int, arr:Array<T>) = invoke(row, col)
        { r:Int, c:Int ->
            if((r-1)*col+(c-1) <= arr.size-1) arr[(r-1)*col+(c-1)]
            else when(T::class.java)
            {
                BigDecimal::class.java -> BigDecimal.ZERO
                BigInteger::class.java -> BigInteger.ZERO
                java.lang.Double::class.java -> 0.0
                java.lang.Float::class.java -> 0.0F
                java.lang.Long::class.java -> 0L
                java.lang.Integer::class.java -> 0
                java.lang.Short::class.java -> 0.toShort()
                java.lang.Byte::class.java -> 0.toByte()
                else -> throw MathIllegalException("不支持的矩阵元素类型")
            } as T
        }
    }
    /**
     * 矩阵元素总数，始终与[row]*[col]一致.
     */
    val size get() = row*col

    /**
     * 存储矩阵元素的一维数组.
     */
    private val data = Array<Number>(size) { index -> (init(index/row+1, index%row+1)) }

    /**
     * 获取矩阵元素.
     * @param r 访问的行数(自1计).
     * @param c 访问的列数(自1计).
     * @throws MathIndexOutOfBoundsException 访问矩阵的行数或列数超出范围时.
     */
    @Suppress("Unchecked_cast")
    operator fun get(r:Int, c:Int):T
    {
        if(r < 1 || r > row) throw MathIndexOutOfBoundsException("访问矩阵的行数超出范围")
        if(c < 1 || c > col) throw MathIndexOutOfBoundsException("访问矩阵的列数超出范围")
        return data[((r-1)*col+(c-1))] as T
    }

    /**
     * 设置矩阵元素.
     * @param r 设置的行数(自1计).
     * @param c 设置的列数(自1计).
     * @param value 新元素.
     * @throws MathIndexOutOfBoundsException 访问矩阵的行数或列数超出范围时.
     */
    operator fun set(r:Int, c:Int, value:T)
    {
        if(r < 1 || r > row) throw MathIndexOutOfBoundsException("访问矩阵的行数超出范围")
        if(c < 1 || c > col) throw MathIndexOutOfBoundsException("访问矩阵的列数超出范围")
        data[((r-1)*col+(c-1))] = value
    }

    /**
     * 获取矩阵的行.
     * @param r 访问的行数(自1计).
     * @return 该行元素组成的数组.
     * @throws MathIndexOutOfBoundsException 访问矩阵的行数超出范围时.
     */
    @Suppress("Unchecked_cast")
    operator fun get(r:Int):Array<T>
    {
        if(r < 1 || r > row) throw MathIndexOutOfBoundsException("访问矩阵的行数超出范围")
        val rowArray = java.lang.reflect.Array.newInstance(data[0]::class.java, col) as Array<Number>
        return data.copyInto(rowArray, 0, ((r-1)*col), (r*col)) as Array<T>
    }

    /**
     * 设置矩阵的行.
     * @param r 设置的行数(自1计).
     * @param values 新元素行.
     * @throws MathIndexOutOfBoundsException 访问矩阵的行数超出范围时.
     * @throws MathIllegalException 新元素行的元素数目与列数不一致时.
     */
    operator fun set(r:Int, values:Array<T>)
    {
        if(r < 1 || r > row) throw MathIndexOutOfBoundsException("访问矩阵的行数超出范围")
        if(values.size != col) throw MathIllegalException("新元素行的元素数目与矩阵列数不一致")
        for(j in 1..col) this[r, j] = values[(j-1)]
    }

    /**
     * 计算矩阵的行列式.
     * @throws MathIllegalException 非方阵的矩阵进行该运算时.
     */
    fun det():Double
    {
        if(this.row != this.col) throw MathIllegalException("不支持非方阵的矩阵进行行列式运算")
        val matrix = Matrix(row, col) { r, c -> BigDecimal.valueOf(this[r, c].toDouble()) }
        for(j in 1 until col)
        {
            for(i in j+1..row)
            {
                if(matrix[j, j] == BigDecimal.ZERO)
                {
                    for(r in j+1..row)
                    {
                        if(matrix[r, j] != BigDecimal.ZERO)
                        {
                            val line = matrix[j]
                            matrix[j] = matrix[r]
                            matrix[r] = line
                            break
                        }
                        else if(r == row) return 0.0
                    }
                }
                val newline = Array(col) { k -> (matrix[i, k+1]-matrix[i, j]/matrix[j, j]*matrix[j, k+1]) }
                matrix[i] = newline
            }
        }
        var result = BigDecimal.ONE
        for(s in 1..row)
        {
            result *= matrix[s, s]
        }
        return result.toDouble()
    }

    /**
     * 将矩阵转为字符串.
     * @see joinToString
     */
    override fun toString():String = this.joinToString()

    /**
     * 将矩阵按照给定分隔符转为字符串.
     * @param rowSep 行间分隔符.
     * @param elementSep 行内元素间分隔符.
     */
    fun joinToString(rowSep:String = "\n", elementSep:String = " "):String
    {
        var result = "\n"
        for(r in 1..row)
        {
            for(c in 1..col)
            {
                result += this[r, c].toString()+elementSep
            }
            result += rowSep
        }
        return result
    }
}
/*178行[2023-06-06]*/
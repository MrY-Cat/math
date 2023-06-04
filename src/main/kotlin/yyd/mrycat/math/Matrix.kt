package yyd.mrycat.math

/**
 * 矩阵类
 *
 * @param row 行数，访问时自1计.
 * @param col 列数，访问时自1计，缺少时默认为行数(即初始化为方阵).
 * @param T 矩阵元素的类型，矩阵进行运算时元素必须支持该运算，否则抛出异常.
 * @constructor r行c列元素将被构造为init(r,c)
 * @author qq2278010681
 */
class Matrix<T : Any>(val row:Int, val col:Int = row, init:(Int, Int) -> T)
{
    /**
     * 矩阵中元素总数，即行数*列数.
     */
    val size = row*col

    /**
     * 存储矩阵元素的一维数组
     */
    private val data:Array<Any> = Array(size) { index->init(index/row+1,index%row+1)}

    /**
     * 使用该构造函数时，元素的初始化将会按照一维数组(自0计)来使用lambda
     */
    constructor(row:Int, col:Int = row, init:(Int) -> T):this(row,col,{r,c->init((r-1)*col+(c-1))})

    /**
     * @param r 访问的行数(自1计).
     * @param c 访问的列数(自1计).
     * @exception Exception 访问矩阵的行数或列数超出范围.
     */
    operator fun set(r:Int, c:Int, value:T)
    {
        if(r<1 || r>row || c<1 || c>col) throw Exception("访问矩阵的行数或列数超出范围")
        data[(r-1)*col+(c-1)] = value
    }
    /**
     * @param r 访问的行数(自1计).
     * @param c 访问的列数(自1计).
     * @exception Exception 访问矩阵的行数或列数超出范围.
     */
    @Suppress("Unchecked_cast")
    operator fun get(r:Int, c:Int):T
    {
        if(r<1 || r>row || c<1 || c>col) throw Exception("访问矩阵的行数或列数超出范围")
        return data[(r-1)*col+(c-1)] as T
    }
    /**
     * @param r 访问的行数(自1计).
     * @return 该行元素组成的数组
     * @exception Exception 访问矩阵的行数超出范围.
     */
    @Suppress("Unchecked_cast")
    operator fun get(r:Int):Array<T>
    {
        if(r<1 || r>row ) throw Exception("访问矩阵的行数超出范围")
        val rowArray=java.lang.reflect.Array.newInstance(data[0]::class.java,col) as Array<Any>
        return data.copyInto(rowArray,0,(r-1)*col,r*col) as Array<T>
    }

    /**
     * 将矩阵转为String，其元素按照对应类型进行toString().
     *
     * 行间分隔符为"\n"，行内元素间分隔符为" ".
     *
     * @see joinToString
     */
    override fun toString():String=this.joinToString()
    /**
     * 将矩阵按照给定分隔符转为String，其元素按照对应类型进行toString().
     * @param rowSep 行间分隔符，默认为"\n".
     * @param elementSep 行内元素间分隔符，默认为" ".
     */
    fun joinToString(rowSep:String="\n", elementSep:String=" "):String
    {
        var result="\n"
        for(r in 1..row)
        {
            for(c in 1..col)
            {
                result+=this[r,c].toString()+elementSep
            }
            result+=rowSep
        }
        return result
    }
}

import my.math.*

class Main
{
    companion object
    {
        @JvmStatic
        fun main(args:Array<String>)
        {//此处测试或验证相关问题
            val eventTotal=12//事件总数
            //val eachProbability:Array<Double> = arrayOf(1.0/6,1.0/6,1.0/6,1.0/6,1.0/6,1.0/6)//骰子70项结果：14.69869299
            val eachProbability:Array<Double> = arrayOf(1.0/6,2.0/6,3.0/6)//奇怪硬币500-1000项：7.2999999999999979678213
            //val eachProbability:Array<Double> = arrayOf(1.0/2,1.0/2)//两面硬币1000-5000项：2.9999999999999999999999735926761895601166213132215420119
            println(gatherEventClusterEr(eventTotal, *eachProbability.toDoubleArray(), calculationAccuracyOfInfiniteSeries = 70))

        }
    }
}

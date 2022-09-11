import my.math.*

class Main
{
    companion object
    {
        @JvmStatic
        fun main(args:Array<String>)
        {
            val eventTotal=10//事件总数
            val eachProbability:Array<Double> = arrayOf(11.0/100,11.0/100,11.0/100,11.0/100,11.0/100,11.0/100,11.0/100,11.0/100,11.0/100,1.0/100)
            //val eachProbability:Array<Double> = arrayOf(1.0/6,2.0/6,3.0/6)//各个事件概率
            println(gatherEventClusterEr(eventTotal, *eachProbability.toDoubleArray()))

        }
    }
}

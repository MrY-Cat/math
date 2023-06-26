# my.math
## 基于kotlin的一个简单数学函数包
### 一.目录结构：
1.组合数学公式：实现在[combinatorics](https://github.com/MrY-Cat/math/tree/main/src/main/kotlin/yyd/mrycat/math/combinatorics)目录下，每个公式单独一个文件，例如[Factorial](https://github.com/MrY-Cat/math/tree/main/src/main/kotlin/yyd/mrycat/math/combinatorics/Factorial.kt)。

2.自定义数据类：实现在[data](https://github.com/MrY-Cat/math/tree/main/src/main/kotlin/yyd/mrycat/math/data)目录下，每个类单独一个文件，例如[Matrix](https://github.com/MrY-Cat/math/tree/main/src/main/kotlin/yyd/mrycat/math/data/Matrix.kt)。

3.复杂数学问题：实现在[problem](https://github.com/MrY-Cat/math/tree/main/src/main/kotlin/yyd/mrycat/math/problem)目录下，每个问题单独一个文件，例如[GatherEventCluster](https://github.com/MrY-Cat/math/tree/main/src/main/kotlin/yyd/mrycat/math/problem/GatherEventCluster.kt)。

4.自定义异常类：实现在[exception](https://github.com/MrY-Cat/math/tree/main/src/main/kotlin/yyd/mrycat/math/exception)目录下，要求该包代码中人为主动抛出的所有异常均应继承自接口[MathException](https://github.com/MrY-Cat/math/tree/main/src/main/kotlin/yyd/mrycat/math/exception/MathException.kt)。

5.自定义工具类：实现在[util](https://github.com/MrY-Cat/math/tree/main/src/main/kotlin/yyd/mrycat/math/util)目录下，每个类单独一个文件，例如[MathOverFlow](https://github.com/MrY-Cat/math/tree/main/src/main/kotlin/yyd/mrycat/math/util/MathOverFlow.kt)。

6.测试：目前实现在[test](https://github.com/MrY-Cat/math/tree/main/src/main/kotlin/yyd/mrycat/math/test)目录下的Tests.kt中。

7.若实现到其他数学方面的公式或函数，在[主目录](https://github.com/MrY-Cat/math/tree/main/src/main/kotlin/yyd/mrycat/math)下新开一个子目录。

### 二.支持的内容：

#### 支持的公式：

• [阶乘公式](https://github.com/MrY-Cat/math/tree/main/src/main/kotlin/yyd/mrycat/math/combinatorics/Factorial.kt)

• [排列数公式](https://github.com/MrY-Cat/math/tree/main/src/main/kotlin/yyd/mrycat/math/combinatorics/Arrangement.kt)

• [组合数公式](https://github.com/MrY-Cat/math/tree/main/src/main/kotlin/yyd/mrycat/math/combinatorics/Combination.kt)

• [错排公式](https://github.com/MrY-Cat/math/tree/main/src/main/kotlin/yyd/mrycat/math/combinatorics/Derangement.kt)

• [有序分组情况数](https://github.com/MrY-Cat/math/tree/main/src/main/kotlin/yyd/mrycat/math/combinatorics/OrderedGrouping.kt)

• [有序整数拆分(情况数&结果矩阵)](https://github.com/MrY-Cat/math/tree/main/src/main/kotlin/yyd/mrycat/math/combinatorics/OrderedIntegerPartition.kt)

#### 支持的数据：

• [矩阵](https://github.com/MrY-Cat/math/tree/main/src/main/kotlin/yyd/mrycat/math/data/Matrix.kt)

#### 支持的问题：

• [集齐完备事件组期望次数问题](https://github.com/MrY-Cat/math/tree/main/src/main/kotlin/yyd/mrycat/math/problem/GatherEventCluster.kt)（加权完全图的随机游走覆盖时间？）

### 三.欢迎贡献：

欢迎贡献代码，请fork代码到自己的仓库或新建分支，修改后再提交Pull request 。

#### 注意：

1.对于返回大整数的组合数学公式，n增大时结果增长可能很迅速，需实现两个同名函数（可参考[Combination](https://github.com/MrY-Cat/math/tree/main/src/main/kotlin/yyd/mrycat/math/combinatorics/Combination.kt)）：

• 前者接收Long返回BigInterger，后者接收Int返回Long，二者独立实现。

• 在后者的函数注释中尽可能注明将会溢出的情况，提示在此情况下应使用前者。

• 若函数的返回值溢出条件可计算且函数中间值的溢出条件不先于返回值：应在[MathOverflow](https://github.com/MrY-Cat/math/tree/main/src/main/kotlin/yyd/mrycat/math/util/MathOverflow.kt)中实现同名函数用于精确判断是否溢出，并在后者内部调用，满足溢出情况则抛出MathOverflowException。

2.对于某些精确度要求较高的计算，应使用BigDecimal，缺少超过Double精度的数学常数时在[MathConstant](https://github.com/MrY-Cat/math/tree/main/src/main/kotlin/yyd/mrycat/math/util/MathConstant.kt)中添加。

• 例如在[Derangement](https://github.com/MrY-Cat/math/tree/main/src/main/kotlin/yyd/mrycat/math/combinatorics/Derangement.kt)中，若采用简化公式D(n)=[n!/e+1/2]，则需使用更高精度的[MathConstant.E](https://github.com/MrY-Cat/math/tree/main/src/main/kotlin/yyd/mrycat/math/util/MathConstant.kt)，否则在n>18时计算将有误差。


### 四.写在最后：

• 开发该项目的出发点是面向需求（x）编程学习kotlin、对数学问题进行计算验证、方便后续遇到相关数学问题时使用。

• 代码水平和数学水平有限，如有错误和疏漏欢迎指出。

• [代码风格](https://github.com/MrY-Cat/math/tree/main/kotlinCodeStyle.xml)可能与经典的左大括号不换行相违背，为个人遗留习惯，不喜勿喷（dog）。

• 欢迎路过的大佬提交Pull request贡献代码或发布issue。


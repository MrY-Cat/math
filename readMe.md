# math
## 基于kotlin的一个数学函数工具包
### 一.前言
• 项目的出发点是发现kotlin自带的数学函数包中的数学函数较为有限和基础，试图开发一个在kotlin下专门提供丰富数学函数和工具的包，以方便其他项目引入该包后直接调用相关数学函数。

• 项目为开源项目，任何人都可以参与贡献或提出需求与意见，提交Pr时请保留良好的注释（虽然项目的注释比较详尽，但本人还未学习如何打包进去，欢迎大佬提交Pull request解决([#5](https://github.com/MrY-Cat/math/issues/5))）。

• 若要在其他项目中使用本项目的数学函数，请在仓库Releases页面中下载最新Jar包，添加到项目的依赖库中（由于本人比较菜，还未学习怎么搞到meaven central让项目能直接在gradle.kt中添加使用，欢迎大佬提交Pull request实现）。

• 项目目前主要是本人面向需求编程、对数学问题进行计算验证时增加相关功能(由于没人贡献×
### 二.目录结构
1.组合数学公式：实现在[combinatorics](https://github.com/MrY-Cat/math/tree/master/src/main/kotlin/yyd/mrycat/math/combinatorics)目录下，每个公式单独一个文件，例如[Factorial](https://github.com/MrY-Cat/math/tree/master/src/main/kotlin/yyd/mrycat/math/combinatorics/Factorial.kt)。

2.自定义数据类：实现在[data](https://github.com/MrY-Cat/math/tree/master/src/main/kotlin/yyd/mrycat/math/data)目录下，每个类单独一个文件，例如[Matrix](https://github.com/MrY-Cat/math/tree/master/src/main/kotlin/yyd/mrycat/math/data/Matrix.kt)。

3.复杂数学问题：实现在[problem](https://github.com/MrY-Cat/math/tree/master/src/main/kotlin/yyd/mrycat/math/problem)目录下，每个问题单独一个文件，例如[GatherEventCluster](https://github.com/MrY-Cat/math/tree/master/src/main/kotlin/yyd/mrycat/math/problem/GatherEventCluster.kt)。

4.自定义异常类：实现在[exception](https://github.com/MrY-Cat/math/tree/master/src/main/kotlin/yyd/mrycat/math/exception)目录下，要求该包代码中人为主动抛出的所有异常均应继承自接口[MathException](https://github.com/MrY-Cat/math/tree/master/src/main/kotlin/yyd/mrycat/math/exception/MathException.kt)。

5.自定义工具类：实现在[util](https://github.com/MrY-Cat/math/tree/master/src/main/kotlin/yyd/mrycat/math/util)目录下，每个类单独一个文件，例如[MathOverflow](https://github.com/MrY-Cat/math/tree/master/src/main/kotlin/yyd/mrycat/math/util/MathOverflow.kt)。

6.图片资源：存放在[image](https://github.com/MrY-Cat/math/tree/master/image)目录下，用于保存注释中URL所链接的图片。

7.测试：目前实现在[test](https://github.com/MrY-Cat/math/tree/master/src/main/kotlin/yyd/mrycat/math/test)目录下的Tests.kt中。

8.若实现到其他数学方面的公式或函数，在[主目录](https://github.com/MrY-Cat/math/tree/master/src/main/kotlin/yyd/mrycat/math)下新开一个子目录。

### 三.已实现内容

#### 支持的数学公式

• [阶乘公式](https://github.com/MrY-Cat/math/tree/master/src/main/kotlin/yyd/mrycat/math/combinatorics/Factorial.kt)

• [排列数公式](https://github.com/MrY-Cat/math/tree/master/src/main/kotlin/yyd/mrycat/math/combinatorics/Arrangement.kt)

• [组合数公式&组合生成](https://github.com/MrY-Cat/math/tree/master/src/main/kotlin/yyd/mrycat/math/combinatorics/Combination.kt)

• [错排公式](https://github.com/MrY-Cat/math/tree/master/src/main/kotlin/yyd/mrycat/math/combinatorics/Derangement.kt)

• [有序分组情况数](https://github.com/MrY-Cat/math/tree/master/src/main/kotlin/yyd/mrycat/math/combinatorics/OrderedGrouping.kt)

• [有序整数拆分(情况数&结果矩阵)](https://github.com/MrY-Cat/math/tree/master/src/main/kotlin/yyd/mrycat/math/combinatorics/OrderedIntegerPartition.kt)

#### 支持的数据结构

• [矩阵](https://github.com/MrY-Cat/math/tree/master/src/main/kotlin/yyd/mrycat/math/data/Matrix.kt)

#### 支持的数学问题

• [集齐完备事件组期望次数问题](https://github.com/MrY-Cat/math/tree/master/src/main/kotlin/yyd/mrycat/math/problem/GatherEventCluster.kt)（加权完全图的随机游走覆盖时间？）

### 四.欢迎贡献

欢迎贡献代码，请fork代码到自己的仓库或新建分支，修改后再提交Pull request 。

#### 注意

1.对于返回大整数的组合数学公式，n增大时结果增长可能很迅速，需实现两个同名函数（可参考[Combination](https://github.com/MrY-Cat/math/tree/master/src/main/kotlin/yyd/mrycat/math/combinatorics/Combination.kt)）：

• 前者接收Long返回BigInteger，后者接收Int返回Long，二者独立实现。

• 在后者的函数注释中尽可能注明将会溢出的情况，提示在此情况下应使用前者。

• 若函数的返回值溢出条件可计算且函数中间值的溢出条件不先于返回值：应在[MathOverflow](https://github.com/MrY-Cat/math/tree/master/src/main/kotlin/yyd/mrycat/math/util/MathOverflow.kt)中实现同名函数用于精确判断是否溢出，并在后者内部调用，满足溢出情况则抛出MathOverflowException。

2.对于某些精确度要求较高的计算，应使用BigDecimal，缺少超过Double精度的数学常数时在[MathConstant](https://github.com/MrY-Cat/math/tree/master/src/main/kotlin/yyd/mrycat/math/util/MathConstant.kt)中添加。

• 例如在[Derangement](https://github.com/MrY-Cat/math/tree/master/src/main/kotlin/yyd/mrycat/math/combinatorics/Derangement.kt)中，若采用简化公式D(n)=[n!/e+1/2]，则需使用更高精度的[MathConstant.E](https://github.com/MrY-Cat/math/tree/master/src/main/kotlin/yyd/mrycat/math/util/MathConstant.kt)，否则在n>18时计算将有误差。


### 五.写在最后

• 代码水平和数学水平有限，如有错误、疏漏、意见欢迎提出。

• [个人代码风格](https://github.com/MrY-Cat/math/tree/master/kotlinCodeStyle.xml)可能与经典的左大括号不换行相违背，为个人遗留习惯，不喜勿喷（dog）。

• 欢迎各位大佬提交Pull request贡献代码或发布issue提出意见或建议。


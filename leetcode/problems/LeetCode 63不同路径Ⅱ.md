## LeetCode 63不同路径Ⅱ
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201115163606596.png)

现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？

网格中的障碍物和空位置分别用 1 和 0 来表示。


```
示例 1：
输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
输出：2
解释：
3x3 网格的正中间有一个障碍物。
从左上角到右下角一共有 2 条不同的路径：
1. 向右 -> 向右 -> 向下 -> 向下
2. 向下 -> 向下 -> 向右 -> 向右

示例 2：
输入：obstacleGrid = [[0,1],[0,0]]
输出：1

提示：
m == obstacleGrid.length
n == obstacleGrid[i].length
1 <= m, n <= 100
obstacleGrid[i][j] 为 0 或 1
```
这题你可以使用搜索，其实跟搜索关系越来越大了，但依然可以使用dp，**就是在遍历的时候遇到障碍物的位置跳过计算即可** 。该位置始终为0即可。

实现代码为：
```java
 public int uniquePathsWithObstacles(int[][] obstacleGrid) {
     int m=obstacleGrid.length;
     int n=obstacleGrid[0].length;
     int dp[][]=new int[m+1][n+1];
     dp[0][1]=1;
     for(int i=1;i<m+1;i++)
     {
         for(int j=1;j<n+1;j++)
         {
             if(obstacleGrid[i-1][j-1]!=1)
             dp[i][j]=dp[i-1][j]+dp[i][j-1];
         }
     }
     //System.out.println(Arrays.deepToString(dp));
     return  dp[m][n];
 }
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201115164304845.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)
## 最后
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201114173756879.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)
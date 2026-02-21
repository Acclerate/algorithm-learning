# LeetCode 27 移除元素 - 详细算法解析

## 题目回顾
给你一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，并返回移除后数组的新长度。

## 核心思想：双指针技巧

这个算法使用了经典的**双指针**技术，也叫做**快慢指针**方法：

- **快指针(fast)**：负责遍历整个数组，检查每个元素
- **慢指针(slow)**：负责记录有效元素应该放置的位置

## 代码逐行分析

```java
public int removeElement(int[] nums, int val) {
    int slow = 0;  // 慢指针，指向下一个可写入的位置
    
    // 快指针遍历整个数组
    for(int fast = 0; fast < nums.length; fast++){
        // 如果当前元素不等于要删除的值
        if(nums[fast] != val){
            // 将有效元素复制到慢指针位置
            nums[slow] = nums[fast];
            // 慢指针向前移动一位
            slow++;
        }
        // 如果等于val，则跳过（不复制），快指针继续前进
    }
    
    // 返回有效元素的个数
    return slow;
}
```

## 执行过程图解

让我们用示例来演示执行过程：

### 示例1：nums = [3,2,2,3], val = 3

| 步骤 | fast | slow | nums状态 | 说明 |
|------|------|------|----------|------|
| 初始 | - | 0 | [3,2,2,3] | slow=0，准备写入第一个位置 |
| 1 | 0 | 0 | [3,2,2,3] | nums[0]=3=val，跳过 |
| 2 | 1 | 0 | [2,2,2,3] | nums[1]=2≠val，复制到位置0，slow=1 |
| 3 | 2 | 1 | [2,2,2,3] | nums[2]=2≠val，复制到位置1，slow=2 |
| 4 | 3 | 2 | [2,2,2,3] | nums[3]=3=val，跳过 |

最终结果：slow=2，前两个元素为[2,2]

### 示例2：nums = [0,1,2,2,3,0,4,2], val = 2

| 步骤 | fast | slow | nums状态 | 说明 |
|------|------|------|----------|------|
| 初始 | - | 0 | [0,1,2,2,3,0,4,2] | slow=0 |
| 1 | 0 | 0 | [0,1,2,2,3,0,4,2] | nums[0]=0≠2，复制到位置0，slow=1 |
| 2 | 1 | 1 | [0,1,2,2,3,0,4,2] | nums[1]=1≠2，复制到位置1，slow=2 |
| 3 | 2 | 2 | [0,1,2,2,3,0,4,2] | nums[2]=2=2，跳过 |
| 4 | 3 | 2 | [0,1,2,2,3,0,4,2] | nums[3]=2=2，跳过 |
| 5 | 4 | 2 | [0,1,3,2,3,0,4,2] | nums[4]=3≠2，复制到位置2，slow=3 |
| 6 | 5 | 3 | [0,1,3,0,3,0,4,2] | nums[5]=0≠2，复制到位置3，slow=4 |
| 7 | 6 | 4 | [0,1,3,0,4,0,4,2] | nums[6]=4≠2，复制到位置4，slow=5 |
| 8 | 7 | 5 | [0,1,3,0,4,0,4,2] | nums[7]=2=2，跳过 |

最终结果：slow=5，前五个元素为[0,1,3,0,4]

## 算法特点

### 时间复杂度：O(n)
- 只需要遍历数组一次
- 每个元素最多被访问一次

### 空间复杂度：O(1)
- 只使用了常数级别的额外空间（两个指针变量）
- 原地修改数组，没有使用额外数组

### 关键优势
1. **高效性**：一次遍历完成
2. **节省空间**：原地操作，无需额外存储
3. **保持相对顺序**：虽然题目说顺序可以改变，但此算法实际上保持了非目标元素的相对顺序

## 算法变体对比

原始AC代码：
```java
public int removeElement(int[] nums, int val) {
    int index = 0;
    for(int i = 0; i < nums.length; i++) {
        if(nums[i] == val)
            continue;
        nums[index++] = nums[i];
    }
    return index;
}
```

您的代码与原始AC代码本质上完全相同：
- `index` 对应 `slow`
- `i` 对应 `fast` 
- `continue` 语句与 `if` 条件判断逻辑等价

## 实际应用场景

这种双指针技巧在很多场景中都有应用：
1. **数组去重**：移除重复元素
2. **条件筛选**：根据特定条件保留元素
3. **移动零**：将数组中的0移到末尾
4. **合并有序数组**：归并排序中的合并步骤

## 调试建议

如果要验证算法正确性，可以添加调试输出：

```java
public int removeElement(int[] nums, int val) {
    int slow = 0;
    System.out.println("初始状态: " + Arrays.toString(nums));
    
    for(int fast = 0; fast < nums.length; fast++){
        System.out.printf("fast=%d, slow=%d, nums[%d]=%d ", 
                         fast, slow, fast, nums[fast]);
        
        if(nums[fast] != val){
            nums[slow] = nums[fast];
            System.out.printf("-> 复制到位置%d, slow变为%d\n", slow, slow+1);
            slow++;
        } else {
            System.out.println("-> 等于val，跳过");
        }
    }
    
    System.out.println("最终结果: " + Arrays.toString(Arrays.copyOf(nums, slow)));
    return slow;
}
```

这样可以帮助理解每一步的具体操作过程。
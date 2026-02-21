import java.util.Arrays;

/**
 * LeetCode 27 移除元素算法演示
 * 详细展示双指针执行过程
 */
public class RemoveElementDemo {
    
    /**
     * 原始算法实现
     */
    public static int removeElement(int[] nums, int val) {
        int slow = 0;
        for(int fast = 0; fast < nums.length; fast++){
            if(nums[fast] != val){
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }
    
    /**
     * 带详细过程输出的版本
     */
    public static int removeElementWithTrace(int[] nums, int val) {
        System.out.println("=== 算法执行过程演示 ===");
        System.out.println("输入数组: " + Arrays.toString(nums));
        System.out.println("要移除的值: " + val);
        System.out.println();
        
        int slow = 0;
        System.out.println("初始状态:");
        System.out.println("slow指针位置: " + slow);
        System.out.println("当前数组状态: " + Arrays.toString(nums));
        System.out.println("------------------------");
        
        for(int fast = 0; fast < nums.length; fast++){
            System.out.printf("第%d步 - fast指针在位置%d, 值为%d\n", 
                             fast + 1, fast, nums[fast]);
            
            if(nums[fast] != val){
                System.out.printf("  -> %d ≠ %d, 进行复制操作\n", nums[fast], val);
                nums[slow] = nums[fast];
                System.out.printf("  -> 将nums[%d]的值%d复制到nums[%d]\n", 
                                 fast, nums[fast], slow);
                slow++;
                System.out.printf("  -> slow指针移动到位置%d\n", slow);
            } else {
                System.out.printf("  -> %d = %d, 跳过不复制\n", nums[fast], val);
            }
            
            System.out.println("  当前数组状态: " + Arrays.toString(nums));
            System.out.println("  有效元素范围: [0, " + (slow-1) + "]");
            System.out.println("------------------------");
        }
        
        System.out.println("=== 执行完成 ===");
        System.out.println("返回值(slow): " + slow);
        System.out.println("最终有效数组: " + Arrays.toString(Arrays.copyOf(nums, slow)));
        return slow;
    }
    
    /**
     * 图形化显示数组状态
     */
    public static void displayArrayState(int[] nums, int slow, int fast, int val) {
        System.out.print("数组状态: [");
        for(int i = 0; i < nums.length; i++) {
            if(i == slow) {
                System.out.print("(" + nums[i] + ") "); // slow指针位置用括号标记
            } else if(i == fast) {
                System.out.print("[" + nums[i] + "] "); // fast指针位置用方括号标记
            } else {
                System.out.print(nums[i] + " ");
            }
        }
        System.out.println("]");
        
        // 显示指针位置
        System.out.print("指针位置:  ");
        for(int i = 0; i < nums.length; i++) {
            if(i == slow && i == fast) {
                System.out.print("SF ");
            } else if(i == slow) {
                System.out.print("S  ");
            } else if(i == fast) {
                System.out.print("F  ");
            } else {
                System.out.print("   ");
            }
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        // 测试示例1
        System.out.println("【示例1】");
        int[] nums1 = {3, 2, 2, 3};
        int val1 = 3;
        removeElementWithTrace(nums1.clone(), val1);
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // 测试示例2
        System.out.println("【示例2】");
        int[] nums2 = {0, 1, 2, 2, 3, 0, 4, 2};
        int val2 = 2;
        removeElementWithTrace(nums2.clone(), val2);
        
        // 验证算法正确性
        System.out.println("\n=== 算法正确性验证 ===");
        testAlgorithm();
    }
    
    /**
     * 测试算法正确性
     */
    public static void testAlgorithm() {
        // 测试用例
        int[][] testCases = {
            {3, 2, 2, 3},
            {0, 1, 2, 2, 3, 0, 4, 2},
            {1},
            {1, 1, 1},
            {},
            {5, 5, 5, 5, 5}
        };
        
        int[] vals = {3, 2, 1, 1, 1, 5};
        
        for(int i = 0; i < testCases.length; i++) {
            int[] nums = testCases[i].clone();
            int val = vals[i];
            int result = removeElement(nums, val);
            
            System.out.printf("测试%d: 原数组%s, 移除%d, 结果长度=%d, 有效数组=%s\n",
                            i+1, 
                            Arrays.toString(testCases[i]),
                            val,
                            result,
                            Arrays.toString(Arrays.copyOf(nums, result)));
        }
    }
}
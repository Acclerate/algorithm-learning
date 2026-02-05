/**
 * LeetCode 21: 合并两个有序列表
 * 
 * 问题描述：
 * 将两个升序链表合并为一个新的 升序 链表并返回。
 * 新链表是通过拼接给定的两个链表的所有节点组成的。
 * 
 * 示例：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */

// 链表节点定义
class ListNode {
    int val;
    ListNode next;
    
    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class MergeTwoSortedLists {
    
    /**
     * 方法一：创建新节点（适合理解，但空间效率低）
     * 时间复杂度: O(m + n)
     * 空间复杂度: O(m + n) - 创建了新节点
     */
    public ListNode mergeTwoLists_v1(ListNode l1, ListNode l2) {
        // 1. 创建虚拟头节点 - 简化边界处理
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;  // tail 始终指向结果链表的最后一个节点
        
        // 2. 同时遍历两个链表
        while (l1 != null || l2 != null) {
            // 3. 处理边界：某个链表已经遍历完
            if (l1 == null) {
                tail.next = l2;  // 直接连接 l2 剩余部分
                break;
            } else if (l2 == null) {
                tail.next = l1;  // 直接连接 l1 剩余部分
                break;
            } else {
                // 4. 比较当前节点值，选择较小的
                if (l1.val < l2.val) {
                    tail.next = new ListNode(l1.val);  // ⚠️ 创建新节点
                    tail = tail.next;                  // 移动 tail
                    l1 = l1.next;                      // 移动 l1 指针
                } else {
                    tail.next = new ListNode(l2.val);  // ⚠️ 创建新节点
                    tail = tail.next;                  // 移动 tail
                    l2 = l2.next;                      // 移动 l2 指针
                }
            }
        }
        
        // 5. 返回真正的头节点（跳过虚拟头节点）
        return dummy.next;
    }
    
    /**
     * 方法二：直接连接原节点（推荐！最优解）
     * 时间复杂度: O(m + n)
     * 空间复杂度: O(1) - 只用几个指针变量
     * 
     * 优化点：不创建新节点，直接复用原有节点
     */
    public ListNode mergeTwoLists_v2(ListNode l1, ListNode l2) {
        // 1. 创建虚拟头节点
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;  // 尾指针
        
        // 2. 同时遍历两个链表（注意：用 && 更简洁）
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;    // ✅ 直接连接 l1 当前节点
                l1 = l1.next;       // l1 指针前移
            } else {
                tail.next = l2;    // ✅ 直接连接 l2 当前节点
                l2 = l2.next;       // l2 指针前移
            }
            tail = tail.next;      // tail 指针前移
        }
        
        // 3. 连接剩余部分（必有一个为 null）
        tail.next = (l1 != null) ? l1 : l2;
        
        return dummy.next;
    }
    
    /**
     * 方法三：递归解法（优雅但理解难度较高）
     * 时间复杂度: O(m + n)
     * 空间复杂度: O(m + n) - 递归栈空间
     */
    public ListNode mergeTwoLists_recursive(ListNode l1, ListNode l2) {
        // 基础情况：某个链表为空
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        
        // 递归情况：选择较小的节点，递归处理剩余部分
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists_recursive(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists_recursive(l1, l2.next);
            return l2;
        }
    }
    
    // ==================== 辅助方法 ====================
    
    /**
     * 根据数组创建链表
     */
    public ListNode createList(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        
        for (int val : arr) {
            tail.next = new ListNode(val);
            tail = tail.next;
        }
        
        return dummy.next;
    }
    
    /**
     * 打印链表
     */
    public void printList(ListNode head) {
        if (head == null) {
            System.out.println("null");
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) {
                sb.append(" -> ");
            }
            head = head.next;
        }
        System.out.println(sb.toString());
    }
    
    /**
     * 比较两个链表是否相等
     */
    public boolean listEquals(ListNode l1, ListNode l2) {
        while (l1 != null && l2 != null) {
            if (l1.val != l2.val) return false;
            l1 = l1.next;
            l2 = l2.next;
        }
        return l1 == null && l2 == null;
    }
    
    // ==================== 测试用例 ====================
    
    public static void main(String[] args) {
        MergeTwoSortedLists solution = new MergeTwoSortedLists();
        
        System.out.println("=== LeetCode 21: 合并两个有序列表 ===\n");
        
        // 测试用例 1: 基本测试
        System.out.println("【测试用例 1】基本测试");
        ListNode l1 = solution.createList(new int[]{1, 2, 4});
        ListNode l2 = solution.createList(new int[]{1, 3, 4});
        System.out.print("输入: l1 = ");
        solution.printList(l1);
        System.out.print("      l2 = ");
        solution.printList(l2);
        
        ListNode result = solution.mergeTwoLists_v2(l1, l2);
        System.out.print("输出: ");
        solution.printList(result);
        System.out.println("期望: 1 -> 1 -> 2 -> 3 -> 4 -> 4\n");
        
        // 测试用例 2: 两个空链表
        System.out.println("【测试用例 2】两个空链表");
        result = solution.mergeTwoLists_v2(null, null);
        System.out.print("输出: ");
        solution.printList(result);
        System.out.println("期望: null\n");
        
        // 测试用例 3: 一个空链表
        System.out.println("【测试用例 3】一个空链表");
        l1 = null;
        l2 = solution.createList(new int[]{0});
        System.out.print("输入: l1 = null, l2 = ");
        solution.printList(l2);
        result = solution.mergeTwoLists_v2(l1, l2);
        System.out.print("输出: ");
        solution.printList(result);
        System.out.println("期望: 0\n");
        
        // 测试用例 4: 单节点链表
        System.out.println("【测试用例 4】单节点链表");
        l1 = solution.createList(new int[]{1});
        l2 = solution.createList(new int[]{2});
        System.out.print("输入: l1 = ");
        solution.printList(l1);
        System.out.print("      l2 = ");
        solution.printList(l2);
        result = solution.mergeTwoLists_v2(l1, l2);
        System.out.print("输出: ");
        solution.printList(result);
        System.out.println("期望: 1 -> 2\n");
        
        // 测试用例 5: 完全不相交
        System.out.println("【测试用例 5】完全不相交");
        l1 = solution.createList(new int[]{1, 3, 5});
        l2 = solution.createList(new int[]{2, 4, 6});
        System.out.print("输入: l1 = ");
        solution.printList(l1);
        System.out.print("      l2 = ");
        solution.printList(l2);
        result = solution.mergeTwoLists_v2(l1, l2);
        System.out.print("输出: ");
        solution.printList(result);
        System.out.println("期望: 1 -> 2 -> 3 -> 4 -> 5 -> 6\n");
        
        // 测试用例 6: 有重复值
        System.out.println("【测试用例 6】有重复值");
        l1 = solution.createList(new int[]{1, 1, 2});
        l2 = solution.createList(new int[]{1, 3, 3});
        System.out.print("输入: l1 = ");
        solution.printList(l1);
        System.out.print("      l2 = ");
        solution.printList(l2);
        result = solution.mergeTwoLists_v2(l1, l2);
        System.out.print("输出: ");
        solution.printList(result);
        System.out.println("期望: 1 -> 1 -> 1 -> 2 -> 3 -> 3\n");
        
        // 测试用例 7: 一个链表全部小于另一个
        System.out.println("【测试用例 7】一个链表全部小于另一个");
        l1 = solution.createList(new int[]{1, 2, 3});
        l2 = solution.createList(new int[]{7, 8, 9});
        System.out.print("输入: l1 = ");
        solution.printList(l1);
        System.out.print("      l2 = ");
        solution.printList(l2);
        result = solution.mergeTwoLists_v2(l1, l2);
        System.out.print("输出: ");
        solution.printList(result);
        System.out.println("期望: 1 -> 2 -> 3 -> 7 -> 8 -> 9\n");
        
        // 对比三种方法
        System.out.println("=== 对比三种方法 ===");
        l1 = solution.createList(new int[]{1, 2, 4});
        l2 = solution.createList(new int[]{1, 3, 4});
        
        ListNode result1 = solution.mergeTwoLists_v1(
            solution.createList(new int[]{1, 2, 4}),
            solution.createList(new int[]{1, 3, 4})
        );
        ListNode result2 = solution.mergeTwoLists_v2(l1, l2);
        ListNode result3 = solution.mergeTwoLists_recursive(
            solution.createList(new int[]{1, 2, 4}),
            solution.createList(new int[]{1, 3, 4})
        );
        
        System.out.print("方法一（创建新节点）: ");
        solution.printList(result1);
        System.out.print("方法二（连接原节点）: ");
        solution.printList(result2);
        System.out.print("方法三（递归解法）: ");
        solution.printList(result3);
        
        System.out.println("\n=== 方法对比 ===");
        System.out.println("方法一：易懂但空间效率低 O(m+n)");
        System.out.println("方法二：最优解，空间效率高 O(1) ⭐ 推荐");
        System.out.println("方法三：优雅但递归栈空间 O(m+n)");
    }
}

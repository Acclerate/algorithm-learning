package algorithm;
// ## LeetCode 03无重复字符的最长子串(滑动窗口)

// **题目描述：**
// >给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

// **示例 1:**
// >输入: "abcabcbb"
// >输出: 3 
// >解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

// **示例 2:**
// >输入: "bbbbb"
// >输出: 1
// >解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

// **示例 3:**
// >输入: "pwwkew"
// >输出: 3
// >解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
// >请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

// ## 分析
// 此题就是给一个字符串让你**找出最长没有重复的一个字串。** 要搞清子串和子序列的区别：
// - 子串：**是连续的**，可以看成原串的一部分截取。
// - 子序列：不一定是连续的，但是要保证各个元素之间相对位置不变。
import java.util.HashMap;
import java.util.Map;

public class leetcode003IengthOfLongestSubstring {
    public int lengthOfLongestSubstring001(String s) {
        // Step 1: 准备好“记账本”
        // ASCII字符通常不超过128个，用数组记录每个字符出现的次数
        // 下标是字符的ASCII码，值是该字符出现的次数
        int[] window = new int[128];

        // Step 2: 定义窗口边界和结果
        int left = 0; // 左指针：代表窗口的左边缘
        int right = 0; // 右指针：代表窗口的右边缘
        int max = 0; // 记录历史最长长度

        // Step 3: 右指针开始向右滑动（探索）
        while (right < s.length()) {
            char rightChar = s.charAt(right);

            // 进人：把右边的新字符加入窗口
            window[rightChar]++;

            // Step 4: 处理冲突
            // 如果刚加进来的字符数量 > 1，说明重复了
            // 就要移动左指针，不断“踢人”，直到窗口内不再重复
            while (window[rightChar] > 1) {
                char leftChar = s.charAt(left);
                window[leftChar]--; // 踢出左边的字符
                left++; // 左边缘向右收缩
            }

            // Step 5: 更新记录
            // 现在的窗口 (left ~ right) 肯定是合法的（无重复）
            // 窗口长度 = right - left + 1
            max = Math.max(max, right - left + 1);

            // 继续向右探索
            right++;
        }

        return max;
    }

    public int lengthOfLongestSubstring002(String s) {
        Map<Character, Integer> dic = new HashMap<>(); // key: 字符, value: 上一次出现的下标
        int i = -1; // 左指针，指向无重复子串的前一个位置 (初始为-1方便计算长度)
        int res = 0; // 结果
        int len = s.length();

        for (int j = 0; j < len; j++) {
            // 如果当前字符之前出现过
            if (dic.containsKey(s.charAt(j))) {
                // 左指针 i 跳跃到 "该字符上一次出现的位置" 和 "当前左指针" 中较大的那个
                // 为什么要取 max？防止左指针回退 (比如 abba，走到第二个a时，i不能退回到第一个a的位置)
                i = Math.max(i, dic.get(s.charAt(j)));
            }

            // 更新哈希表：记录当前字符最新出现的位置
            dic.put(s.charAt(j), j);

            // 更新最大长度：当前位置 j - 左指针 i
            res = Math.max(res, j - i);
        }
        return res;
    }

    public int lengthOfLongestSubstring003(String s) {
        // 道具
        Map<Character, Integer> map = new HashMap<>();
        int i = -1, res = 0;

        // 循环
        for (int j = 0; j < s.length(); j++) {
            char c = s.charAt(j); // 提出来写，清爽

            // 动作1 & 2：查 + 缩
            if (map.containsKey(c)) {
                i = Math.max(i, map.get(c)); // 必考点：Math.max
            }

            // 动作3：记
            map.put(c, j);

            // 动作4：量
            res = Math.max(res, j - i);
        }

        return res;
    }

    public static void main(String[] args) {
        leetcode003IengthOfLongestSubstring i = new leetcode003IengthOfLongestSubstring();
        System.out.println("方法1(数组滑动窗口): " + i.lengthOfLongestSubstring001("abcabcdbb"));
        System.out.println("方法2(哈希跳跃窗口): " + i.lengthOfLongestSubstring002("abcabcdbb"));
    }
}
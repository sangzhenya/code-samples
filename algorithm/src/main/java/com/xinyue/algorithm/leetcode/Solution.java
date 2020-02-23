package com.xinyue.algorithm.leetcode;

import com.xinyue.algorithm.offer.TreeNode;

import java.util.*;

public class Solution {
    /************ 241. 为运算表达式设计优先级 ************/
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> ways = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> leftValues = diffWaysToCompute(input.substring(0, i));
                List<Integer> rightValues = diffWaysToCompute(input.substring(i + 1));
                for (Integer leftV : leftValues) {
                    for (Integer rightV : rightValues) {
                        if (c == '+') {
                            ways.add(leftV + rightV);
                        } else if (c == '-') {
                            ways.add(leftV - rightV);
                        } else {
                            ways.add(leftV * rightV);
                        }
                    }
                }
            }
        }
        if (ways.size() == 0) {
            ways.add(Integer.valueOf(input));
        }
        return ways;
    }

    /**************** 95. 不同的二叉搜索树 II ****************/
    public List<TreeNode> generateTrees(int n) {
        if (n < 1) {
            return new ArrayList<>();
        }
        return generateSubtrees(1, n);
    }

    private List<TreeNode> generateSubtrees(int s, int e) {
        List<TreeNode> res = new ArrayList<>();
        if (s > e) {
            res.add(null);
            return res;
        }
        for (int i = s; i <= e; i++) {
            List<TreeNode> leftNodes = generateSubtrees(s, i - 1);
            List<TreeNode> rightNodes = generateSubtrees(i + 1, e);
            for (TreeNode leftNode : leftNodes) {
                for (TreeNode rightNode : rightNodes) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftNode;
                    root.right = rightNode;
                    res.add(root);
                }
            }
        }
        return res;
    }

    /******  53. 最大子序和  ******/
    public int maxSubArray(int[] nums) {
        int sum = 0;
        int gSum = Integer.MIN_VALUE;
        for (int num : nums) {
            sum = (sum <= 0) ? num : sum + num;
            gSum = Math.max(sum, gSum);
        }
        return gSum;
    }

    /******  169. 多数元素  ******/
    public int majorityElement(int[] nums) {
        int cnt = 1;
        int val = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (val == nums[i]) {
                cnt += 1;
            } else {
                cnt -= 1;
                if (cnt == 0) {
                    val = nums[i];
                    cnt = 1;
                }
            }
        }
        return val;
    }

    /****** 215. 数组中的第K个最大元素 ******/
    public int findKthLargest(int[] nums, int k) {
        k = nums.length - k;
        int l = 0;
        int h = nums.length - 1;
        while (l < h) {
            int j = partition(nums, l, h);
            if (j == k) {
                break;
            } else if (j > k) {
                h = j - 1;
            } else {
                l = j + 1;
            }
        }
        return nums[k];
    }

    private int partition(int[] nums, int l, int h) {
        int p = nums[l];
        int i = l;
        int j = h + 1;
        while (true) {
            while (i < h && nums[++i] < p) { }
            while (j > l && nums[--j] > p) { }
            if (i >= j) {
                break;
            }
            swap(nums, i, j);
        }
        swap(nums, l, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /****** 70. 爬楼梯 ******/
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int pre1 = 2;
        int pre2 = 1;
        for (int i = 2; i < n; i++) {
            int temp = pre1 + pre2;
            pre2 = pre1;
            pre1 = temp;
        }
        return pre1;
    }

    /****** 198. 打家劫舍 ******/
    public int rob(int[] nums) {
        int pre1 = 0;
        int pre2 = 0;
        for (int num : nums) {
            int cur = Math.max(pre2 + num, pre1);
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }

    /****** 64. 最小路径和 ******/
    public int minPathSum(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        int[] dp = new int[cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (j == 0) {
                } else if (i == 0) {
                    dp[j] = dp[j - 1];
                } else {
                    dp[j] = Math.min(dp[j - 1], dp[j]);
                }
                dp[j] += grid[i][j];
            }
        }
        return dp[cols - 1];
    }

    /****** 413. 等差数列划分 ******/
    public int numberOfArithmeticSlices(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        int[] dp = new int[n];
        for (int i = 2; i < n; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                dp[i] = dp[i - 1] + 1;
            }
        }
        int total = 0;
        for (int cnt : dp) {
            total += cnt;
        }
        return total;
    }
    /****** 343. 整数拆分 ******/
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * dp[i - j], j * (i - j)));
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[n];
    }

    /****** 279. 完全平方数 ******/
    public int numSquares(int n) {
        List<Integer> sList = generateSquareList(n);
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (Integer sq : sList) {
                if (sq > i) {
                    break;
                }
                min = Math.min(min, dp[i - sq] + 1);
            }
            dp[i] = min;
        }
        return dp[n];
    }

    /****** 91. 解码方法 ******/
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= n; i++) {
            int one = Integer.parseInt(s.substring(i - 1, i));
            if (one != 0) {
                dp[i] += dp[i - 1];
            }
            if (s.charAt(i - 2) == '0') {
                continue;
            }
            int two = Integer.parseInt(s.substring(i - 2, i));
            if (two <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

    private List<Integer> generateSquareList(int n) {
        List<Integer> sList = new ArrayList<>();
        int diff = 3;
        int sq = 1;
        while (sq <= n) {
            sList.add(sq);
            sq += diff;
            diff += 2;
        }
        return sList;
    }

    /****************************************************************************************/
    public static void main(String[] args) {
        System.out.println(new Solution().numSquares(13));
    }
}

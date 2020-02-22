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

    /****************************************************************************************/
    public static void main(String[] args) {
//        new Solution().diffWaysToCompute()
        int[] ints = {1, 3, 5, 7, 9, 10, 4, 6, 8, 2};
        System.out.println(Arrays.toString(ints));
        new Solution().findKthLargest(ints, 2);
        System.out.println(Arrays.toString(ints));
    }
}

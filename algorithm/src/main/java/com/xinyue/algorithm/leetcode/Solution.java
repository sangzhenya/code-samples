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
    /******************************************/
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


    /****************************************************************************************/
    public static void main(String[] args) {
//        new Solution().diffWaysToCompute()
    }
}

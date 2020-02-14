package com.xinyue.algorithm.offer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class OfferMain {

    private Map<Integer, Integer> indexInMap = new HashMap<>();

    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        for (int i = 0; i < in.length; i++) {
            indexInMap.put(in[i], i);
        }
        return reConstructBinaryTree(pre, 0, pre.length - 1, 0);
    }

    private TreeNode reConstructBinaryTree(int[] pre, int preLeft, int preRight, int inLeft) {
        if (preLeft > preRight) {
            return null;
        }
        TreeNode treeNode = new TreeNode(pre[preLeft]);
        int inIndex = indexInMap.get(pre[preLeft]);
        int leftTreeSize = inIndex - inLeft;
        treeNode.left = reConstructBinaryTree(pre, preLeft + 1, preLeft + leftTreeSize, inLeft);
        treeNode.right = reConstructBinaryTree(pre, preLeft + leftTreeSize + 1, preRight, inLeft + leftTreeSize + 1);
        return treeNode;
    }

    /**********************************************************************************/
    public int minNumberInRotateArray(int [] array) {
        if (array.length == 0) {
            return 0;
        }
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (array[left] == array[mid] && array[mid] == array[right]) {
                return minNumbers(array, left, right);
            } else if (array[mid] < array[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return array[left];
    }

    private int minNumbers(int[] arr, int left, int right) {
        for (int i = 0; i < right; i++) {
            if (arr[i] > arr[i + 1]) {
                return arr[i + 1];
            }
        }
        return arr[left];
    }

    /**********************************************************************************/
    public int JumpFloor(int target) {
        if (target <= 2) {
            return target;
        }
        int pre1 = 1;
        int pre2 = 2;
        int result = 0;
        for (int i = 2; i < target; i++) {
            result = pre1 + pre2;
            pre1 = pre2;
            pre2 = result;
        }
        return result;
    }

    /**********************************************************************************/
    public int JumpFloorII(int target) {
        int[] dp = new int[target];
        Arrays.fill(dp, 1);
        for (int i = 0; i < target; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j];
            }
        }
        return dp[target - 1];
    }


    /**********************************************************************************/
    public int RectCover(int target) {
        if (target <= 2) {
            return target;
        }
        int pre1 = 1;
        int pre2 = 2;
        int result = 0;
        for (int i = 2; i < target; i++) {
            result = pre1 + pre2;
            pre1 = pre2;
            pre2 = result;
        }
        return result;
    }

    /**********************************************************************************/
    public int NumberOf1(int n) {
        int count = 0;
        while (n != 0) {
            count += 1;
            n &= (n - 1);
        }
        return count;
    }

    /**********************************************************************************/
    public static void main(String[] args) {
        new OfferMain().JumpFloorII(10);
    }
}

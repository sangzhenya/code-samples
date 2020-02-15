package com.xinyue.algorithm.offer;

import java.util.*;

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
    public double Power(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        if (exponent == 1) {
            return base;
        }

        boolean isNeg = false;
        if (exponent < 0) {
            isNeg = true;
            exponent = -exponent;
        }

        double pow = Power(base * base, exponent / 2);
        if (exponent % 2 != 0) {
            pow = pow * base;
        }
        return isNeg ? 1 / pow : pow;
    }

    /**********************************************************************************/
    public void reOrderArray(int [] array) {
        int oddCount = 0;
        for (int num : array) {
            if (num % 2 == 1) {
                oddCount += 1;
            }
        }
        int i = 0;
        int j = oddCount;
        int[] copy = array.clone();
        for (int num : copy) {
            if (num % 2 == 1) {
                array[i++] = num;
            } else {
                array[j++] = num;
            }
        }
    }

    /**********************************************************************************/
    public ListNode FindKthToTail(ListNode head,int k) {
        if (head == null) {
            return null;
        }

        ListNode p1 = head;
        while (p1 != null && k-- > 0) {
            p1 = p1.next;
        }

        if (k > 0) {
            return null;
        }

        ListNode p2 = head;
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }


    /**********************************************************************************/
    public ListNode ReverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = null;
        ListNode node = ReverseList(next);
        next.next = head;
        return node;
    }

    /**********************************************************************************/
    public ListNode Merge(ListNode list1,ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        if (list1.val < list2.val) {
            list1.next = Merge(list1.next, list2);
            return list1;
        } else {
            list2.next = Merge(list1, list2.next);
            return list2;
        }
    }

    /**********************************************************************************/
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if (root1 == null || root2 == null) {
            return false;
        }
        return isSubtreeWithRoot(root1, root2) || HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
    }

    private boolean isSubtreeWithRoot(TreeNode root1, TreeNode root2) {
        if (root2 == null) {
            return true;
        }
        if (root1 == null) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }
        return isSubtreeWithRoot(root1.left, root2.left) && isSubtreeWithRoot(root1.right, root2.right);
    }

    /**********************************************************************************/
    public void Mirror(TreeNode root) {
        if (root == null) {
            return;
        }
        swapTree(root);
        Mirror(root.left);
        Mirror(root.right);
    }

    private void swapTree(TreeNode root) {
        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;
    }


    /**********************************************************************************/
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> result = new ArrayList<>();
        int c1 = 0;
        int c2 = matrix[0].length - 1;
        int r1 = 0;
        int r2 = matrix.length - 1;
        while (c1 <= c2 && r1 <= r2) {
            for (int i = c1; i <= c2; i++) {
                result.add(matrix[r1][i]);
            }
            for (int i = r1 + 1; i <= r2; i++) {
                result.add(matrix[i][c2]);
            }
            if (r1 != r2) {
                for (int i = c2 - 1; i >= c1; i--) {
                    result.add(matrix[r2][i]);
                }
            }
            if (c1 != c2) {
                for (int i = r2 - 1; i > r1; i--) {
                    result.add(matrix[i][c1]);
                }
            }
            c1 += 1;
            c2 -= 1;
            r1 += 1;
            r2 -= 1;
        }
        return result;
    }

    /**********************************************************************************/

    private Stack<Integer> dataStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public void push(int node) {
        dataStack.push(node);
        minStack.push(minStack.isEmpty() ? node : Math.min(node, minStack.peek()));
    }

    public void pop() {
        dataStack.pop();
        minStack.pop();
    }

    public int top() {
        return dataStack.peek();
    }

    public int min() {
        return minStack.peek();
    }

    /**********************************************************************************/
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        int n = popA.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0, j = 0; i < n; i++) {
            stack.push(pushA[i]);
            while (j < n && !stack.isEmpty() && stack.peek() == popA[j]) {
                stack.pop();
                j += 1;
            }
        }
        return stack.isEmpty();
    }

    /**********************************************************************************/
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> result = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }
                result.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        return result;
    }

    /**********************************************************************************/
    public boolean VerifySquenceOfBST(int [] sequence) {
        if (sequence == null || sequence.length == 0) {
            return false;
        }

        return verify(sequence, 0, sequence.length - 1);
    }

    private boolean verify(int[] seq, int first, int last) {
        if (last - first <= 1) {
            return true;
        }
        int rootVal = seq[last];
        int currentIndex = first;
        while (currentIndex < last && seq[currentIndex] <= rootVal) {
            currentIndex += 1;
        }
        for (int i = currentIndex; i < last; i++) {
            if (seq[i] < rootVal) {
                return false;
            }
        }
        return verify(seq, first, currentIndex - 1) && verify(seq, currentIndex, last - 1);
    }

    /**********************************************************************************/
    private ArrayList<ArrayList<Integer>> result = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        backtracking(root, target, new ArrayList<>());
        return result;
    }

    private void backtracking(TreeNode node, int target, ArrayList<Integer> path) {
        if (node == null) {
            return;
        }
        path.add(node.val);
        target -= node.val;
        if (target == 0 && node.left == null && node.right == null) {
            result.add(new ArrayList<>(path));
        } else {
            backtracking(node.left, target, path);
            backtracking(node.right, target, path);
        }
        path.remove(path.size() - 1);
    }

    /**********************************************************************************/
    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }

        RandomListNode current = pHead;
        while (current != null) {
            RandomListNode clone = new RandomListNode(current.label);
            clone.next = current.next;
            current.next = clone;
            current = clone.next;
        }

        current = pHead;
        while (current != null) {
            RandomListNode clone = current.next;
            if (current.random != null) {
                clone.random = current.random.next;
            }
            current = clone.next;
        }

        current = pHead;
        RandomListNode cloneHead = pHead.next;
        while (current.next != null) {
            RandomListNode next = current.next;
            current.next = next.next;
            current = next;
        }
        return cloneHead;
    }

    /**********************************************************************************/
    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(3);
        node.next.next = new ListNode(5);
        node.next.next.next = new ListNode(7);
        node.next.next.next.next = new ListNode(9);

        ListNode node1 = new ListNode(2);
        node1.next = new ListNode(4);
        node1.next.next = new ListNode(6);
        node1.next.next.next = new ListNode(8);
        node1.next.next.next.next = new ListNode(10);
        System.out.println(new OfferMain().Merge(node, node1));
        System.out.println(node);
        System.out.println(node1);
    }
}

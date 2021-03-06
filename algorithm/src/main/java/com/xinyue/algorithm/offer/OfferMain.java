package com.xinyue.algorithm.offer;

import java.util.*;

public class OfferMain {

    private Map<Integer, Integer> indexInMap = new HashMap<>();

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
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
    public int minNumberInRotateArray(int[] array) {
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
    public void reOrderArray(int[] array) {
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
    public ListNode FindKthToTail(ListNode head, int k) {
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
    public ListNode Merge(ListNode list1, ListNode list2) {
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
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
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
    public ArrayList<Integer> printMatrix(int[][] matrix) {
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
    public boolean IsPopOrder(int[] pushA, int[] popA) {
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
    public boolean VerifySquenceOfBST(int[] sequence) {
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

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
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
    private TreeNode pre = null;
    private TreeNode header = null;

    public TreeNode Convert(TreeNode pRootOfTree) {
        inOrder(pRootOfTree);
        return header;
    }

    private void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);
        node.left = pre;
        if (pre != null) {
            pre.right = node;
        }
        pre = node;
        if (header == null) {
            header = node;
        }
        inOrder(node.right);
    }

    /**********************************************************************************/
    private ArrayList<String> sResult = new ArrayList<>();

    public ArrayList<String> Permutation(String str) {
        if (str.length() == 0) {
            return sResult;
        }
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        backtrackingPermutation(chars, new boolean[chars.length], new StringBuilder());
        return sResult;
    }

    private void backtrackingPermutation(char[] chars, boolean[] used, StringBuilder stringBuilder) {
        if (stringBuilder.length() == chars.length) {
            sResult.add(stringBuilder.toString());
        }
        for (int i = 0; i < chars.length; i++) {
            if (used[i]) {
                continue;
            }
            if (i != 0 && chars[i] == chars[i - 1] && !used[i - 1]) {
                continue;
            }
            used[i] = true;
            stringBuilder.append(chars[i]);
            backtrackingPermutation(chars, used, stringBuilder);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            used[i] = false;
        }
    }

    /**********************************************************************************/
    public int MoreThanHalfNum_Solution(int[] array) {
        int majority = array[0];
        for (int i = 0, cnt = 1; i < array.length; i++) {
            cnt = (array[i] == majority ? cnt + 1 : cnt - 1);
            if (cnt == 0) {
                majority = array[i];
                cnt += 1;
            }
        }
        int cnt = 0;
        for (int val : array) {
            if (val == majority) {
                cnt += 1;
            }
        }

        return cnt > array.length / 2 ? majority : 0;
    }

    /**********************************************************************************/
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        if (k > input.length || k < 0) {
            return result;
        }
        findKthSmallest(input, k - 1);
        for (int i = 0; i < k; i++) {
            result.add(input[i]);
        }
        return result;
    }

    private void findKthSmallest(int[] input, int k) {
        int l = 0;
        int h = input.length - 1;
        while (l < h) {
            int j = partition(input, l, h);
            if (j == k) {
                break;
            } else if (j > k) {
                h = j - 1;
            } else {
                l = j + 1;
            }
        }
    }

    private int partition(int[] nums, int l, int h) {
        int p = nums[l];
        int i = l;
        int j = h + 1;
        while (true) {
            while (i != h && nums[++i] < p) { }
            while (j != l && nums[--j] > p) { }
            if (i > j) {
                break;
            }
            swap(nums, i, j);
        }
        swap(nums, l, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**********************************************************************************/
    public int FindGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int gSum = Integer.MIN_VALUE;

        int sum = 0;
        for (int num : array) {
            sum = sum <= 0 ? num : sum + num;
            gSum = Math.max(gSum, sum);
        }
        return gSum;
    }

    /**********************************************************************************/
    public int NumberOf1Between1AndN_Solution(int n) {
        int cnt = 0;
        for (int i = 1; i <= n; i *= 10) {
            int a = n / i;
            int b = n % i;
            cnt += (a + 8) / 10 * i + (a % 10 == 1 ? b + 1 : 0);
        }
        return cnt;

    }

    /**********************************************************************************/
    public String PrintMinNumber(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return "";
        }
        int length = numbers.length;
        String[] nums = new String[length];
        for (int i = 0; i < length; i++) {
            nums[i] = String.valueOf(numbers[i]);
        }

        Arrays.sort(nums, (s1, s2) -> (s1 + s2).compareTo(s2 + s1));

        StringBuilder result = new StringBuilder();
        for (String num : nums) {
            result.append(num);
        }
        return result.toString();
    }

    /**********************************************************************************/
    public int GetUglyNumber_Solution(int index) {
        if (index <= 6) {
            return index;
        }
        int i2 = 0;
        int i3 = 0;
        int i5 = 0;
        int[] dp = new int[index];
        dp[0] = 1;

        for (int i = 1; i < index; i++) {
            int next2 = dp[i2] * 2;
            int next3 = dp[i3] * 3;
            int next5 = dp[i5] * 5;
            dp[i] = Math.min(next2, Math.min(next3, next5));
            if (dp[i] == next2) {
                i2 += 1;
            }
            if (dp[i] == next3) {
                i3 += 1;
            }
            if (dp[i] == next5) {
                i5 += 1;
            }
        }
        return dp[index - 1];
    }

    /**********************************************************************************/
    public int FirstNotRepeatingChar(String str) {
        int[] cnts = new int[256];
        for (int i = 0; i < str.length(); i++) {
            cnts[str.charAt(i)] += 1;
        }
        for (int i = 0; i < str.length(); i++) {
            if (cnts[str.charAt(i)] == 1) {
                return i;
            }
        }
        return -1;
    }

    /**********************************************************************************/
    private long count = 0;
    private int[] temp;

    public int InversePairs(int[] array) {
        temp = new int[array.length];
        mergeSort(array, 0, array.length - 1);
        return (int) (count % 1000000007);
    }

    private void mergeSort(int[] array, int low, int high) {
        if (high - low < 1) {
            return;
        }
        int mid = low + (high - low) / 2;
        mergeSort(array, low, mid);
        mergeSort(array, mid + 1, high);
        merge(array, low, mid, high);
    }

    private void merge(int[] array, int low, int mid, int high) {
        int i = low;
        int j = mid + 1;
        int k = low;

        while (i <= mid || j <= high) {
            if (i > mid) {
                temp[k] = array[j++];
            } else if (j > high) {
                temp[k] = array[i++];
            } else if (array[i] <= array[j]) {
                temp[k] = array[i++];
            } else {
                temp[k] = array[j++];
                count += mid - i + 1;
            }
            k += 1;
        }
        if (high + 1 - low >= 0) {
            System.arraycopy(temp, low, array, low, high + 1 - low);
        }
    }

    /**********************************************************************************/
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode l1 = pHead1, l2 = pHead2;
        while (l1 != l2) {
            l1 = (l1 == null) ? pHead2 : l1.next;
            l2 = (l2 == null) ? pHead1 : l2.next;
        }
        return l1;
    }

    /**********************************************************************************/
    public int GetNumberOfK(int[] array, int k) {
        int first = binarySearch(array, k);
        int last = binarySearch(array, k + 1);
        if (first == array.length || array[first] != k) {
            return 0;
        }
        return last - first;
    }

    private int binarySearch(int[] nums, int k) {
        int l = 0;
        int h = nums.length;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (nums[m] >= k) {
                h = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    /**********************************************************************************/
    public int TreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(TreeDepth(root.left), TreeDepth(root.right));
    }

    /**********************************************************************************/
    private boolean isBalanced = true;

    public boolean IsBalanced_Solution(TreeNode root) {
        height(root);
        return isBalanced;
    }

    private int height(TreeNode root) {
        if (root == null || !isBalanced) {
            return 0;
        }
        int left = height(root.left);
        int right = height(root.right);
        if (Math.abs(left - right) > 1) {
            isBalanced = false;
        }
        return 1 + Math.max(left, right);
    }

    /**********************************************************************************/
    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        int diff = 0;
        for (int num : array) {
            diff ^= num;
        }
        diff &= -diff;
        for (int num : array) {
            if ((num & diff) == 0) {
                num1[0] ^= num;
            } else {
                num2[0] ^= num;
            }
        }
    }

    /**********************************************************************************/
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int start = 1;
        int end = 2;
        int curSum = 3;
        while (end < sum) {
            if (curSum > sum) {
                curSum -= start;
                start += 1;
            } else if (curSum < sum) {
                end += 1;
                curSum += end;
            } else {
                ArrayList<Integer> tempList = new ArrayList<>();
                for (int i = start; i <= end; i++) {
                    tempList.add(i);
                }
                result.add(tempList);
                curSum -= start;
                start += 1;
                end += 1;
                curSum += end;
            }
        }
        return result;
    }

    /**********************************************************************************/
    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        int i = 0;
        int j = array.length - 1;
        while (i < j) {
            int curSum = array[i] + array[j];
            if (curSum == sum) {
                return new ArrayList<>(Arrays.asList(array[i], array[j]));
            } else if (curSum < sum) {
                i += 1;
            } else {
                j -= 1;
            }
        }
        return new ArrayList<>();
    }

    /**********************************************************************************/
    public String LeftRotateString(String str, int n) {
        if (n >= str.length()) {
            return str;
        }
        return str.substring(n) + str.substring(0, n);
    }

    public String LeftRotateString2(String str, int n) {
        if (n >= str.length()) {
            return str;
        }
        char[] chars = str.toCharArray();
        reverse(chars, 0, n - 1);
        reverse(chars, n, str.length() - 1);
        reverse(chars, 0, str.length() - 1);
        return str.substring(n) + str.substring(0, n);
    }

    private void reverse(char[] arr, int i, int j) {
        while (i < j) {
            swapChar(arr, i++, j--);
        }
    }

    private void swapChar(char[] arr, int i, int j) {
        char t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    /**********************************************************************************/
    public String ReverseSentence(String str) {
        int n = str.length();
        char[] chars = str.toCharArray();
        int i = 0;
        int j = 0;
        while (j <= n) {
            if (j == n || chars[j] == ' ') {
                reverse(chars, i, j - 1);
                i = j + 1;
            }
            j += 1;
        }
        reverse(chars, 0, n - 1);
        return new String(chars);
    }

    /**********************************************************************************/
    public boolean isContinuous(int[] numbers) {
        if (numbers.length < 5) {
            return false;
        }
        Arrays.sort(numbers);
        int cnt = 0;
        for (int number : numbers) {
            if (number == 0) {
                cnt += 1;
            }
        }

        for (int i = cnt; i < numbers.length - 1; i++) {
            if (numbers[i] == numbers[i + 1]) {
                return false;
            }
            cnt -= (numbers[i + 1] - numbers[i] - 1);
        }
        return cnt >= 0;
    }

    /**********************************************************************************/
    public int LastRemaining_Solution(int n, int m) {
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return 0;
        }
        return (LastRemaining_Solution(n - 1, m) + m) % n;
    }

    /**********************************************************************************/
    public int Sum_Solution(int n) {
        int sum = n;
        boolean b = (n > 0) && ((sum += Sum_Solution(n - 1)) > 0);
        return sum;
    }

    /**********************************************************************************/
    public int Add(int num1, int num2) {
        return num2 == 0 ? num1 : Add(num1 ^ num2, (num1 & num2) << 1);
    }

    /**********************************************************************************/
    public int StrToInt(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        boolean withFlag = str.charAt(0) == '-' || str.charAt(0) == '+';
        boolean isNeg = str.charAt(0) == '-';
        String substring = str;
        if (withFlag) {
            substring = str.substring(1);
        }
        if (!isNeg && substring.compareTo("2147483647") > 0) {
            return 0;
        } else if (isNeg && substring.compareTo("2147483648") > 0) {
            return 0;
        }

        char[] chars = str.toCharArray();
        int res = 0;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (i == 0 && (c == '+' || c == '-')) {
                continue;
            }
            if (c < '0' || c > '9') {
                return 0;
            }
            res = res * 10 + (c - '0');
        }
        return isNeg ? -res : res;
    }

    /**********************************************************************************/
    public int[] multiply(int[] A) {
        int n = A.length;
        int[] B = new int[n];
        for (int i = 0, product = 1; i < n; product *= A[i], i++) {
            B[i] = product;
        }
        for (int i = n - 1, product = 1; i >= 0; product *= A[i], i--) {
            B[i] *= product;
        }
        return B;
    }

    /**********************************************************************************/
    public boolean match(char[] str, char[] pattern) {
        int m = str.length;
        int n = pattern.length;
        boolean[][] dp = new boolean[m + 1][n + 1];

        // 状态：dp[i][j] 表示 s 中前 i 个字符与 p 的前 j 个字符组成的表示式是否匹配
        dp[0][0] = true;

        for (int i = 1; i <= n; i++) {
            if (pattern[i - 1] == '*') {
                dp[0][i] = dp[0][i - 2];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str[i - 1] == pattern[j - 1] || pattern[j - 1] == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pattern[j - 1] == '*') {
                    if (pattern[j - 2] == str[i - 1] || pattern[j - 2] == '.') {
                        dp[i][j] |= dp[i][j - 1];
                        dp[i][j] |= dp[i - 1][j];
                        dp[i][j] |= dp[i][j - 2];
                    } else {
                        dp[i][j] = dp[i][j - 2];
                    }
                }
            }
        }

        return dp[m][n];
    }

    /**********************************************************************************/
    public boolean isNumeric(char[] str) {
        if (str == null || str.length == 0) {
            return false;
        }
        return new String(str).matches("[+-]?\\d*(\\.\\d+)?([eE][+-]?\\d+)?");
    }

    /**********************************************************************************/
    private int[] cns = new int[256];
    private Queue<Character> queue = new LinkedList<>();

    //Insert one char from stringstream
    public void Insert(char ch){
        cns[ch] += 1;
        queue.add(ch);
        while (!queue.isEmpty() && cns[queue.peek()] > 1) {
            queue.poll();
        }
    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce(){
        return queue.isEmpty() ? '#' : queue.peek();
    }

    /**********************************************************************************/
    public int cutRope(int target) {
        //动态规划:长度为i的可得最大乘积:dp[i]=dp[j]*dp[i-j]的最大值
        int[] dp = new int[target + 1];
        dp[1] = 1;
        for (int i = 2; i <= target; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), dp[j] * (i - j)));
            }
        }
        return dp[target];
    }
    /**********************************************************************************/
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return null;
        }
        ListNode slow = pHead;
        ListNode fast = pHead;
        do {
            slow = slow.next;
            fast = fast.next.next;
        } while (slow != fast);

        fast = pHead;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
    /**********************************************************************************/
    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }
        ListNode next = pHead.next;
        if (pHead.val == next.val) {
            while (next != null && pHead.val == next.val) {
                next = next.next;
            }
            return deleteDuplication(next);
        } else {
            pHead.next = deleteDuplication(pHead.next);
            return pHead;
        }
    }

    /**********************************************************************************/
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode.right != null) {
            TreeLinkNode node = pNode.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        } else {
            while (pNode.next != null) {
                TreeLinkNode parent = pNode.next;
                if (parent.left == pNode) {
                    return parent;
                }
                pNode = pNode.next;
            }
        }
        return null;
    }

    /**********************************************************************************/
    boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) {
            return true;
        }
        return isSymmetrical(pRoot.left, pRoot.right);
    }

    private boolean isSymmetrical(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null) {
            return true;
        }
        if (n1 == null || n2 == null) {
            return false;
        }
        if (n1.val != n2.val) {
            return false;
        }
        return isSymmetrical(n1.left, n2.right) && isSymmetrical(n1.right, n2.left);
    }

    /**********************************************************************************/
    public ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(pRoot);
        boolean revese = false;
        while (!nodeQueue.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();
            int cnt = nodeQueue.size();
            while (cnt-- > 0) {
                TreeNode node = nodeQueue.poll();
                if (node == null) {
                    continue;
                }
                list.add(node.val);
                nodeQueue.add(node.left);
                nodeQueue.add(node.right);
            }
            if (revese) {
                Collections.reverse(list);
            }
            revese = !revese;
            if (list.size() != 0) {
                result.add(list);
            }
        }
        return result;
    }

    /**********************************************************************************/
    ArrayList<ArrayList<Integer> > Print2(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(pRoot);
        while (!nodeQueue.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();
            int cnt = nodeQueue.size();
            while (cnt-- > 0) {
                TreeNode node = nodeQueue.poll();
                if (node == null) {
                    continue;
                }
                list.add(node.val);
                nodeQueue.add(node.left);
                nodeQueue.add(node.right);
            }
            if (list.size() != 0) {
                result.add(list);
            }
        }
        return result;
    }

    /**********************************************************************************/
    private String deserializeStr;

    String Serialize(TreeNode root) {
        if (root == null) {
            return "#";
        }
        return root.val + " " + Serialize(root.left) + " " + Serialize(root.right);
    }
    TreeNode Deserialize(String str) {
        deserializeStr = str;
        return Deserialize();
    }

    private TreeNode Deserialize() {
        if (deserializeStr.length() == 0) {
            return null;
        }
        int index = deserializeStr.indexOf(" ");
        String node = index == -1 ? deserializeStr : deserializeStr.substring(0, index);
        deserializeStr = index == -1 ? "" : deserializeStr.substring(index + 1);
        if (node.equals("#")) {
            return null;
        }
        TreeNode t = new TreeNode(Integer.parseInt(node));
        t.left = Deserialize();
        t.right = Deserialize();
        return t;
    }

    /**********************************************************************************/
    private TreeNode ret;
    private int cnt = 0;
    TreeNode KthNode(TreeNode pRoot, int k) {
        inOrderNew(pRoot, k);
        return ret;
    }

    private void inOrderNew(TreeNode pRoot, int k) {
        if (pRoot == null || cnt >= k) {
            return;
        }
        inOrderNew(pRoot.left, k);
        cnt += 1;
        if (cnt == k) {
            ret = pRoot;
        }
        inOrderNew(pRoot.right, k);
    }

    /**********************************************************************************/
    private PriorityQueue<Integer> left = new PriorityQueue<>((o1, o2) -> o2 - o1);
    private PriorityQueue<Integer> right = new PriorityQueue<>();
    int totalCount = 0;
    public void Insert(Integer num) {
        if (totalCount % 2 == 0) {
            left.add(num);
            right.add(left.poll());
        } else {
            right.add(num);
            left.add(right.poll());
        }
        totalCount += 1;

    }

    public Double GetMedian() {
        if (totalCount % 2 == 0) {
            return (left.peek() + right.peek()) / 2.0;
        } else {
            return Double.valueOf(right.peek());
        }
    }

    /**********************************************************************************/
    public ArrayList<Integer> maxInWindows(int [] num, int size) {
        ArrayList<Integer> maxInResult = new ArrayList<>();
        if (size > num.length || size < 1) {
            return maxInResult;
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < size; i++) {
            heap.add(num[i]);
        }
        maxInResult.add(heap.peek());
        for (int i = 0, j = i + size; j < num.length; i++, j++) {
            heap.remove(num[i]);
            heap.add(num[j]);
            maxInResult.add(heap.peek());
        }
        return maxInResult;
    }

    /**********************************************************************************/
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (rows == 0 || cols == 0) {
            return false;
        }
        this.rows = rows;
        this.cols = cols;
        boolean[][] marked = new boolean[rows][cols];
        char[][] reMatrix = buildMatrix(matrix);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (backtracking(reMatrix, str, marked, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean backtracking(char[][] matrix, char[] str, boolean[][] marked, int pathLen, int r, int c) {
        if (pathLen == str.length) {
            return true;
        }
        if (r < 0 || r > rows || c < 0 || c > cols || matrix[r][c] != str[pathLen] || marked[r][c]) {
            return false;
        }
        marked[r][c] = true;
        for (int[] ints : next) {
            if (backtracking(matrix, str, marked, pathLen + 1, r + ints[0], c + ints[1])) {
                return true;
            }
        }
        marked[r][c] = false;
        return false;
    }

    private char[][] buildMatrix(char[] array) {
        char[][] matrix = new char[rows][cols];
        for (int i = 0, idx = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = array[idx++];
            }
        }
        return matrix;
    }

    /**********************************************************************************/
    private static int[][] next = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    private int rows;
    private int cols;
    private int mCnt = 0;
    private int threshold;
    private int[][] digitSum;

    public int movingCount(int threshold, int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.threshold = threshold;
        initDigitSum();
        boolean[][] marked = new boolean[rows][cols];
        dfs(marked, 0, 0);
        return mCnt;
    }

    private void dfs(boolean[][] marked, int r, int c) {
        if (r < 0 || r >= rows || c < 0 || c >= cols || marked[r][c]) {
            return;
        }
        marked[r][c] = true;
        if (digitSum[r][c] > this.threshold) {
            return;
        }
        mCnt += 1;
        for (int[] ints : next) {
            dfs(marked, r + ints[0], c + ints[1]);
        }
    }

    private void initDigitSum() {
        int[] digitSumOne = new int[Math.max(rows, cols)];
        for (int i = 0; i < digitSumOne.length; i++) {
            int n = i;
            while (n > 0) {
                digitSumOne[i] += n % 10;
                n /= 10;
            }
        }
        this.digitSum = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.digitSum[i][j] = digitSumOne[i] + digitSumOne[j];
            }
        }
    }

    /**********************************************************************************/
    public static void main(String[] args) {
        OfferMain offerMain = new OfferMain();
        offerMain.Insert(5);
        offerMain.Insert(2);
        offerMain.Insert(3);
        offerMain.Insert(4);

        System.out.println(offerMain.GetMedian());;

    }
}

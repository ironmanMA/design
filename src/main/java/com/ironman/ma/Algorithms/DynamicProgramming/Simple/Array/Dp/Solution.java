package com.ironman.ma.Algorithms.DynamicProgramming.Simple.Array.Dp;

import java.util.*;

public class Solution {
    public int longestSubsequenceLength(final List<Integer> A) {
        int[] LDS_RL = new int[A.size()];
        int[] LDS_LR = new int[A.size()];
        int allMax = 0;
        //fill lds first
        LDS_RL[A.size() - 1] = 1;
        for (int i = A.size() - 2; i >= 0; i--) {
            int max = 0;
            for (int j = i + 1; j < A.size(); j++) {
                if (A.get(j) < A.get(i)) {
                    max = Math.max(LDS_RL[j], max);
                }
            }
            LDS_RL[i] = max + 1;
        }
        //max out on LIS
        LDS_LR[0] = 1;
        for (int i = 0; i < A.size(); i++) {
            int max = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (A.get(j) < A.get(i)) {
                    max = Math.max(LDS_LR[j], max);
                }
            }
            LDS_LR[i] = max + 1;
            allMax = Math.max(allMax, LDS_LR[i] + LDS_RL[i] - 1);
        }
        return allMax;
    }

    public int solve(int[][] A) {
        int high = 0;
        int[][] aux = new int[A.length][A[0].length];

        for (int col = 0; col < A[0].length; col++) {
            int max1 = 0;
            for (int row = 0; row < A.length; row++) {
                if (A[row][col] == 1) {
                    max1++;
                } else {
                    max1 = 0;
                }
                aux[row][col] = max1;
            }
        }
        for (int[] anAux : aux) {
            Arrays.sort(anAux);
            for (int col = 0; col < aux[0].length; col++) {
                high = Math.max(high, (aux[0].length - col) * anAux[col]);
            }
        }
        //sort columns
        return high;
    }

    public int numDecodings(String A) {
        if (A == null || A.isEmpty()) {
            return 0;
        }
        char[] arr = A.toCharArray();
        long[] sumArr = new long[arr.length];
        sumArr[0] = 1;
        if (arr[0] == '0') {
            sumArr[0] = 0;
        }
        if (arr.length == 1) {
            return (int) sumArr[0];
        }

        if (arr[1] != '0') {
            sumArr[1] = sumArr[0];
        }
        if (Integer.parseInt(arr[0] + "" + arr[1]) < 27) {
            if (arr[0] == '0' && arr[1] == '0') {
                sumArr[1] = 0;
            } else if (arr[0] != '0') {
                sumArr[1] += 1;
            } else if (arr[0] == '0') {
                sumArr[1] = 1;
            }
        } else {
            if (arr[1] != '0') {
                sumArr[1] = sumArr[0];
            }
        }

        for (int i = 2; i < arr.length; i++) {
            long ans = 0;
            if (arr[i] != '0') {
                ans += sumArr[i - 1];
            }
            if (Integer.parseInt(arr[i - 1] + "" + arr[i]) < 27) {
                if (arr[i - 1] == '0' && arr[i] == '0') {
                    ans = 0;
                } else if (arr[i - 1] != '0') {
                    ans += sumArr[i - 2];
                }
            } else {
                if (arr[i] != '0') {
                    ans = sumArr[i - 1];
                }
            }
            sumArr[i] = ans;
        }
        return (int) sumArr[sumArr.length - 1];
    }

    public int minDistance(String A, String B) {
        if (A == null || A.isEmpty()) {
            return B.length();
        }
        if (B == null || B.isEmpty()) {
            return A.length();
        }
        int[][] matrix = new int[A.length() + 1][B.length() + 1];
        for (int i = 1; i <= B.length(); i++) {
            matrix[0][i] = i;
        }
        for (int i = 1; i <= A.length(); i++) {
            matrix[i][0] = i;
        }
        for (int row = 1; row <= A.length(); row++) {
            for (int col = 1; col <= B.length(); col++) {
                if (A.charAt(row - 1) == B.charAt(col - 1)) {
                    matrix[row][col] = matrix[row - 1][col - 1];
                } else {
                    int sum = Math.min(matrix[row][col - 1], matrix[row - 1][col]);
                    matrix[row][col] = Math.min(matrix[row - 1][col - 1], sum) + 1;
                }
            }
        }
        return matrix[A.length()][B.length()];
    }

    public int isMatch(final String str, final String regex) {
        boolean[][] matrix = new boolean[str.length() + 1][regex.length() + 1];

        matrix[0][0] = true;
        for (int i = 1; i <= regex.length(); i++) {
            if (regex.charAt(i - 1) == '*') {
                matrix[0][i] = matrix[0][i - 2];
            } else {
                matrix[0][i] = false;
            }
        }
        for (int i = 1; i <= str.length(); i++) {
            matrix[i][0] = false;
        }
        for (int row = 1; row <= str.length(); row++) {
            for (int col = 1; col <= regex.length(); col++) {
                if (regex.charAt(col - 1) == '*') {
                    if (regex.charAt(col - 2) == '.') {
                        matrix[row][col] = matrix[row - 1][col] | matrix[row - 1][col - 1] | matrix[row][col - 1];
                    } else {
                        if (regex.charAt(col - 2) == str.charAt(row - 1)) {
                            matrix[row][col] = matrix[row - 1][col] | matrix[row - 1][col - 1] | matrix[row][col - 2];
                        } else {
                            matrix[row][col] = matrix[row][col - 2];
                        }
                    }
                } else if (regex.charAt(col - 1) == '.') {
                    matrix[row][col] = matrix[row - 1][col - 1];
                } else {
                    if (regex.charAt(col - 1) == str.charAt(row - 1)) {
                        matrix[row][col] = matrix[row - 1][col - 1];
                    } else {
                        matrix[row][col] = false;
                    }
                }
            }
        }
        if (matrix[str.length()][regex.length()]) {
            return 1;
        } else {
            return 0;
        }
    }


    HashMap<String, Boolean> sub = new HashMap<String, Boolean>();

    private boolean isAnagram(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        char[] aArr = a.toCharArray();
        char[] bArr = b.toCharArray();
        Arrays.sort(aArr);
        Arrays.sort(bArr);
        for (int i = 0; i < aArr.length; i++) {
            if (aArr[i] != bArr[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean isScrambleBool(String A, String B) {
        if (A == null && B == null) {
            return true;
        }
        if (A == null || B == null || (A.length() != B.length())) {
            return false;
        }
        if (sub.containsKey(A + B)) {
            return sub.get(A + B);
        }
        if (!isAnagram(A, B)) {
            sub.put(A + B, false);
            return false;
        }
        if (A.equals(B)) {
            sub.put(A + B, true);
            return true;
        }
        if (A.length() == 1) {
            if (!A.equals(B)) {
                sub.put(A + B, false);
                return false;
            } else {
                sub.put(A + B, true);
                return true;
            }
        }
        sub = new HashMap<String, Boolean>();
        for (int i = 1; i < A.length(); i++) {
            String s11 = A.substring(0, i);
            String s12 = A.substring(i, A.length());
            String s21a = B.substring(0, i);
            String s22a = B.substring(i, B.length());
            String s21b = B.substring(B.length() - i, B.length());
            String s22b = B.substring(0, B.length() - i);
            if (isScrambleBool(s11, s21a) && isScrambleBool(s12, s22a)) {
                return true;
            }
            if (isScrambleBool(s11, s21b) && isScrambleBool(s12, s22b)) {
                return true;
            }
        }
        return false;
    }

    public int isScramble(final String A, final String B) {
        if (isScrambleBool(A, B)) {
            return 1;
        }
        return 0;
    }

    private ArrayList<Integer> getBest(ArrayList<Integer> max, ArrayList<Integer> curr) {
        if (curr.size() > max.size()) {
            return curr;
        } else if (curr.size() < max.size()) {
            return max;
        } else {
            Collections.sort(max);
            Collections.sort(curr);
            for (int i = 0; i < max.size(); i++) {
                if (curr.get(i) < max.get(i)) {
                    return curr;
                } else if (curr.get(i) > max.get(i)) {
                    return max;
                }
            }
        }
        return max;
    }

    public int[] solve(int A, final int[] B) {
        HashMap<Integer, ArrayList<Integer>> bestMapSum = new HashMap<Integer, ArrayList<Integer>>();
        ArrayList<Integer> index = new ArrayList<Integer>();
        for (int i = 0; i < B.length; i++) {
            index.add(i);
        }
        Collections.sort(index, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return B[a] - B[b];
            }
        });
        for (int i = 0; i <= A; i++) {
            ArrayList<Integer> bestSubAns = new ArrayList<Integer>();
            for (int j = 0; j < index.size(); j++) {
                ArrayList<Integer> subAns = new ArrayList<Integer>();
                if (i < B[index.get(j)]) {
                    break;
                } else {
                    subAns.add(index.get(j));
                    subAns.addAll(bestMapSum.get(i - B[index.get(j)]));
                    bestSubAns = getBest(bestSubAns, subAns);
                }
            }

            bestMapSum.put(i, bestSubAns);
        }
        ArrayList<Integer> answer = bestMapSum.get(A);
        System.out.println(answer);
        int[] ans = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            ans[i] = answer.get(i);
        }
        return ans;
    }

    public int solve(final int[] A) {
        ArrayList<HashMap<Integer, Integer>> lenz = new ArrayList<HashMap<Integer, Integer>>();
        for (int s : A) {
            lenz.add(new HashMap<Integer, Integer>());
        }
        int allMax = 1;
        for (int i = A.length - 2; i >= 0; i--) {
            HashMap<Integer, Integer> src = lenz.get(i);
            for (int j = i + 1; j < A.length; j++) {
                int diff = A[j] - A[i];
                HashMap<Integer, Integer> dst = lenz.get(j);
                if (dst.containsKey(diff)) {
                    if (!src.containsKey(diff)) {
                        src.put(diff, dst.get(diff) + 1);
                    }
                } else {
                    if (!src.containsKey(diff)) {
                        src.put(diff, 2);
                    }
                }
                allMax = Math.max(src.get(diff), allMax);
            }
        }
        return allMax;
    }

    public int solve(int N, int S) {
        long[][] sumz = new long[N + 1][S + 1];
        long mod = 1000000007;
        for (int i = 0; i <= S; i++) {
            if (i < 10)
                sumz[1][i] = 1;
        }
        for (int i = 2; i <= N; i++) {
            sumz[i][0] = 1;
            for (int j = 1; j <= S; j++) {
                long sum = 0;
                if (i == N && j == S) {
                    sum -= sumz[N - 1][S];
                }
                for (int k = 0; k < 10; k++) {
                    if (j - k >= 0 && j - k <= S) {

                        sum = (sum % mod + sumz[i - 1][j - k] % mod) % mod;
                    }
                }
                sumz[i][j] = sum % mod;
            }
        }
        return (int) (sumz[N][S]);
    }

    public int canJump(int[] A) {
        boolean[] canJump = new boolean[A.length];
        canJump[A.length - 1] = true;
        int minTrueLocation = A.length - 1;

        for (int i = A.length - 2; i >= 0; i--) {
            if (A[i] == 0) {
                canJump[i] = false;
            } else if (A[i] + i >= A.length - 1) {
                canJump[i] = true;
                minTrueLocation = i;
            } else {
                if (minTrueLocation <= A[i] + i) {
                    canJump[i] = true;
                    minTrueLocation = i;
                } else {
                    canJump[i] = false;
                }
            }
        }
        if (canJump[0]) {
            return 1;
        }
        return 0;
    }

    public int maxcoin(int[] A) {
        long[][] max = new long[A.length][A.length];
        for (int i = 0; i < A.length; i++) {
            max[i][i] = A[i];
        }
        for (int i = 0; i + 1 < A.length; i++) {
            max[i][i + 1] = Math.max(max[i][i], max[i + 1][i + 1]);
        }
        for (int diag = 2; diag < A.length; diag++) {
            for (int row = 0; row + diag < A.length; row++) {
                int col = row + diag;

                //if you pick lE: row, col
                long currPickLe = A[row];
                //he picks best of both ends: row+1, col
                if (A[row + 1] > A[col]) {
                    currPickLe += max[row + 2][col];
                } else {
                    currPickLe += max[row + 1][col - 1];
                }

                //if you pick rE: row, col
                long currPickRe = A[col];
                //he picks best of both ends: row, col-1
                if (A[row] > A[col - 1]) {
                    currPickRe += max[row + 1][col - 1];
                } else {
                    currPickRe += max[row][col - 2];
                }
                max[row][col] = Math.max(currPickLe, currPickRe);
            }
        }

        return (int) max[0][A.length - 1];
    }
}

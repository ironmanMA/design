package com.ironman.ma.Algorithms.Hashing.Core.FourSum;

import java.util.*;

public class Solution {
    public int[][] fourSum(int[] A, int B) {
        Arrays.sort(A);
        ArrayList<String> res = new ArrayList<String>();
        HashSet<String> resH = new HashSet<String>();

        HashMap<Long, ArrayList<String>> vis = new HashMap<Long, ArrayList<String>>();
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                ArrayList<String> indices = new ArrayList<String>();
                long sum = A[i] + A[j];
                if (vis.containsKey(sum)) {
                    indices = vis.get(sum);
                }
                indices.add(i + "," + j);
                vis.put(sum, indices);
            }
        }

        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                long sum = A[i] + A[j];
                sum = B - sum;
                if (!vis.containsKey(sum)) {
                    continue;
                }
                ArrayList<String> indices = vis.get(sum);
                String fh = i + "," + j;
                for (String poten : indices) {
                    if (!poten.contains(i + "")
                            && !poten.contains(j + "")
                            && !resH.contains(fh + "," + poten)) {
                        res.add(fh + "," + poten);
                        resH.add(fh + "," + poten);
                    }
                }
            }
        }

        resH = new HashSet<String>();
        ArrayList<ArrayList<Integer>> ansL = new ArrayList<ArrayList<Integer>>();
        for (String pot : res) {
            ArrayList<Integer> part = new ArrayList<Integer>();
            String[] k = pot.split(",");
            part.add(A[Integer.parseInt(k[0])]);
            part.add(A[Integer.parseInt(k[1])]);
            part.add(A[Integer.parseInt(k[2])]);
            part.add(A[Integer.parseInt(k[3])]);
            Collections.sort(part);
            String hash = part.get(0) + "," + part.get(1) + "," + part.get(2) + "," + part.get(3);
            if (!resH.contains(hash)) {
                ansL.add(part);
                resH.add(hash);
            }
        }

        Collections.sort(ansL, new Comparator<ArrayList<Integer>>() {
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                if (o1.get(0).equals(o2.get(0))) {
                    if (o1.get(1).equals(o2.get(1))) {
                        if (o1.get(2).equals(o2.get(2))) {
                            return o1.get(3) - o2.get(3);
                        } else {
                            return o1.get(2) - o2.get(2);
                        }
                    } else {
                        return o1.get(1) - o2.get(1);
                    }
                } else {
                    return o1.get(0) - o2.get(0);
                }
            }
        });

        int[][] ans = new int[resH.size()][4];
        int iter = 0;
        for (ArrayList<Integer> pot : ansL) {
            int[] part = new int[4];
            part[0] = pot.get(0);
            part[1] = pot.get(1);
            part[2] = pot.get(2);
            part[3] = pot.get(3);
            ans[iter] = part;
            iter++;
        }
//        System.out.println(resH);
        return ans;
    }

    public int[] equal(int[] A) {
        int[] maxAns = new int[0];
        HashMap<Long, String> viz = new HashMap<Long, String>();
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                long sum = A[i] + A[j];
                String index = i + "," + j;
                if (viz.containsKey(sum)) {
                    String[] si = viz.get(sum).split(",");
                    int A1 = Integer.parseInt(si[0]);
                    int B1 = Integer.parseInt(si[1]);
                    if (A1 < i && B1 != j && B1 != i) {
                        int[] ans = new int[4];
                        ans[0] = A1;
                        ans[1] = B1;
                        ans[2] = i;
                        ans[3] = j;
                        if (maxAns.length == 0) {
                            maxAns = ans;
                        }
                        if (ans[0] == maxAns[0]) {
                            if (ans[1] == maxAns[1]) {
                                if (ans[2] == maxAns[2]) {
                                    if (ans[3] == maxAns[3]) {
                                        continue;
                                    } else if (ans[3] < maxAns[3]) {
                                        maxAns = ans;
                                    }
                                } else if (ans[2] < maxAns[2]) {
                                    maxAns = ans;
                                }
                            } else if (ans[1] < maxAns[1]) {
                                maxAns = ans;
                            }
                        } else if (ans[0] < maxAns[0]) {
                            maxAns = ans;
                        }
                    }
                } else {
                    viz.put(sum, index);
                }
            }
        }
        return maxAns;
    }

    private boolean equalSet(HashMap<Character, Integer> allVal,
                             HashMap<Character, Integer> vizVal) {
        if (allVal.size() != vizVal.size()) {
            return false;
        }
        for (Map.Entry<Character, Integer> entry : allVal.entrySet()) {
            if (vizVal.get(entry.getKey()) == null || entry.getValue() > vizVal.get(entry.getKey())) {
                return false;
            }
        }
        return true;
    }

    public String minWindow(String A, String B) {
        int startIndex = 0;
        int minLen = Integer.MAX_VALUE;
        char[] Barr = B.toCharArray();
        HashMap<Character, Integer> allVal = new HashMap<Character, Integer>();
        for (char c : Barr) {
            int seen = 0;
            if (allVal.containsKey(c)) {
                seen = allVal.get(c);
            }
            allVal.put(c, seen + 1);
        }
        HashMap<Character, Integer> vizVal = new HashMap<Character, Integer>();
        int prev = 0;
        for (int i = 0; i < A.length(); i++) {
            if (allVal.containsKey(A.charAt(i))) {
                int seen = 0;
                if (vizVal.containsKey(A.charAt(i))) {
                    seen = vizVal.get(A.charAt(i));
                }
                vizVal.put(A.charAt(i), seen + 1);
            }
            if (equalSet(allVal, vizVal)) {
                String saw = A.substring(prev, i + 1);
                int len = i - prev + 1;
                if (len < minLen) {
                    minLen = len;
                    startIndex = prev;
                }
                //move prev
                while (!allVal.containsKey(A.charAt(prev))) {
                    prev++;
                }
                int occu = vizVal.get(A.charAt(prev));
                vizVal.put(A.charAt(prev), occu - 1);
                prev++;
                saw = A.substring(prev, i + 1);
                saw.length();
            }
            if (i == A.length() - 1) {
                while (equalSet(allVal, vizVal)) {
                    String saw = A.substring(prev, i + 1);
                    int len = i - prev + 1;
                    if (len < minLen) {
                        minLen = len;
                        startIndex = prev;
                    }
                    if (vizVal.containsKey(A.charAt(prev))) {
                        int occu = vizVal.get(A.charAt(prev));
                        vizVal.put(A.charAt(prev), occu - 1);
                    }
                    prev++;
                    saw = A.substring(prev, i + 1);
                    saw.length();
                }
            }
        }


        if (minLen == Integer.MAX_VALUE) {
            return "";
        }
        return A.substring(startIndex, startIndex + minLen);
    }

    private boolean equalMap(HashMap<String, Integer> allVal,
                             HashMap<String, Integer> vizVal) {
        if (allVal.size() != vizVal.size()) {
            return false;
        }
        for (Map.Entry<String, Integer> entry : allVal.entrySet()) {
            if (vizVal.get(entry.getKey()) == null || entry.getValue() > vizVal.get(entry.getKey())) {
                return false;
            }
        }
        return true;
    }

    private boolean allSame(String k) {
        HashSet<Character> occ = new HashSet<Character>();
        for (char c : k.toCharArray()) {
            occ.add(c);
        }
        return occ.size() == 1;
    }

    public ArrayList<Integer> findSubstringOld(String A, final List<String> B) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        int windowLen = 0;
        int fullSpace = 0;
        windowLen = B.get(0).length();
        HashMap<String, Integer> dict = new HashMap<String, Integer>();
        for (String dStr : B) {
            fullSpace += dStr.length();
            if (dict.containsKey(dStr)) {
                dict.put(dStr, dict.get(dStr) + 1);
            } else {
                dict.put(dStr, 1);
            }

        }
        HashMap<String, Integer> localDict = new HashMap<String, Integer>();
        for (int i = 0; i <= A.length() - fullSpace; ) {
            String toFind = A.substring(i, i + windowLen);
            if (dict.containsKey(toFind)) {
                int realIndex = i;
                if (localDict.size() > 0) {
                    realIndex = i - (B.size() - 1) * windowLen;
                }
                //empty/partial
                /*
                 *  empty real_i=i
                 *  partial real_i=i-(B.size()-1) * windowLen
                 * */

                for (int j = i; j < realIndex + fullSpace; ) {
                    String toFindL = A.substring(j, j + windowLen);
                    if (dict.containsKey(toFindL)) {
                        if (localDict.containsKey(toFindL)) {
                            localDict.put(toFindL, localDict.get(toFindL) + 1);
                        } else {
                            localDict.put(toFindL, 1);
                        }
                        j += windowLen;
                    } else {
                        localDict = new HashMap<String, Integer>();
                        i = j + windowLen;
                        break;
                    }
                }
                if (equalMap(dict, localDict)) {
                    ans.add(realIndex);
                    //remove first slot and increment i
                    int occ = localDict.get(toFind);
                    if (occ == 1) {
                        localDict.remove(toFind);
                    } else {
                        localDict.put(toFind, occ - 1);
                    }
                    if (dict.size() == 1 && allSame(toFind)) {
                        i = realIndex + 1;
                    } else {
                        i = realIndex + fullSpace;
                    }
                }
            } else {
                localDict = new HashMap<String, Integer>();
                i++;
            }
        }
        return ans;
    }


    private boolean isAnagram(String a, String b) {
        HashMap<Character, Integer> mapA = new HashMap<Character, Integer>();
        HashMap<Character, Integer> mapB = new HashMap<Character, Integer>();
        for (int i = 0; i < a.length(); i++) {
            if (mapA.containsKey(a.charAt(i))) {
                mapA.put(a.charAt(i), mapA.get(a.charAt(i)) + 1);
            } else {
                mapA.put(a.charAt(i), 1);
            }
            if (mapB.containsKey(b.charAt(i))) {
                mapB.put(b.charAt(i), mapB.get(b.charAt(i)) + 1);
            } else {
                mapB.put(b.charAt(i), 1);
            }
        }
        for (Map.Entry<Character, Integer> entry : mapA.entrySet()) {
            if (!entry.getValue().equals(mapB.get(entry.getKey()))) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Integer> findSubstring(String A, final List<String> B) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        long targetHash = 0;
        int winLen = 0;
        int fullLen = 0;
        StringBuilder tarStr = new StringBuilder("");
        HashMap<String, Integer> mapA = new HashMap<String, Integer>();
        for (String word : B) {
            winLen = word.length();
            fullLen += word.length();
            targetHash += word.hashCode();
            tarStr.append(word);
            if (mapA.containsKey(word)) {
                mapA.put(word, mapA.get(word) + 1);
            } else {
                mapA.put(word, 1);
            }

        }
        String targ = tarStr.toString();
        if (winLen == 1) {
            for (int j = 0; j <= A.length() - fullLen; j++) {
                String locaStr = A.substring(j, j + fullLen);
                if (isAnagram(locaStr, targ)) {
                    ans.add(j);
                }
            }
        } else {
            for (int j = 0; j <= A.length() - fullLen; j++) {
                long localHash = 0;
                for (int i = j; i < j + fullLen; i += winLen) {
                    String lc = A.substring(i, i + winLen);
                    if (!mapA.containsKey(lc)) {
                        break;
                    }
                    localHash += lc.hashCode();
                }
                if (localHash == targetHash) {
                    ans.add(j);
                }
            }
        }

        return ans;
    }

    public int longestConsecutive(final List<Integer> A) {
        int maxLen = 0;
        HashMap<Integer, String> maxUp = new HashMap<Integer, String>();
        for (int a : A) {
            String mapOg = a + "," + a;
            maxUp.put(a, mapOg);

            int newA = a + 1;
            while (maxUp.containsKey(newA)) {
                String[] maxc = maxUp.get(a).split(",");
                String[] maxu = maxUp.get(newA).split(",");

                String newMap = Math.min(Integer.parseInt(maxc[0]),
                        Integer.parseInt(maxu[0])) + "," +
                        Math.max(Integer.parseInt(maxc[1]),
                                Integer.parseInt(maxu[1]));
                maxUp.put(a, newMap);
                maxUp.put(newA, newMap);
                newA = Math.max(Integer.parseInt(maxc[1]), Integer.parseInt(maxu[1])) + 1;
            }
            newA = a - 1;
            while (maxUp.containsKey(newA)) {
                String[] maxc = maxUp.get(a).split(",");
                String[] maxu = maxUp.get(newA).split(",");

                String newMap = Math.min(Integer.parseInt(maxc[0]),
                        Integer.parseInt(maxu[0])) + "," +
                        Math.max(Integer.parseInt(maxc[1]),
                                Integer.parseInt(maxu[1]));
                maxUp.put(a, newMap);
                maxUp.put(newA, newMap);
                newA = Math.min(Integer.parseInt(maxc[0]), Integer.parseInt(maxu[0])) - 1;
            }
            int len = 1 + Integer.parseInt(maxUp.get(a).split(",")[1]) -
                    Integer.parseInt(maxUp.get(a).split(",")[0]);
            if (len > maxLen) {
                maxLen = len;
            }
        }
        return maxLen;
    }

    public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<Integer> B) {
        ArrayList<Integer> ans = new ArrayList<Integer>(A.size());
        if (A.size() == 1) {
            ans.add(A.get(0) + B.get(0));
            return ans;
        }
        Collections.sort(A, Collections.reverseOrder());
        Collections.sort(B, Collections.reverseOrder());
        int aM1 = 0;
        int aM2 = 0;
        int bM1 = -1;
        int bM2 = -1;

        ArrayList<Integer> max1 = A;
        ArrayList<Integer> max1Op = B;
        ArrayList<Integer> max2 = B;
        ArrayList<Integer> max2Op = A;

        if (A.get(0) < B.get(1)) {
            max1 = B;
            max1Op = A;
            aM1 = 1;
        } else if (B.get(0) < A.get(1)) {
            max2 = A;
            max2Op = B;
            aM2 = 1;
        }

        for (int i = 1; i <= A.size(); i++) {
            int sum1 = max1.get(aM1) + max1Op.get(bM1 + 1);
            int sum2 = max2.get(aM2) + max2Op.get(bM2 + 1);
            if (sum1 >= sum2) {
                ans.add(sum1);
                bM1 = bM1 + 1;
            } else {
                ans.add(sum2);
                bM2 = bM2 + 1;
            }
        }
        return ans;
    }

    public ArrayList<Integer> order(ArrayList<Integer> heights, ArrayList<Integer> infronts) {
        int size = heights.size();
        TreeMap<Integer, Integer> k = new TreeMap<Integer, Integer>();
        for (int i = 0; i < size; i++) {
            k.put(heights.get(i), infronts.get(i));
        }
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            res.add(-1);
        }
        while (k.size() != 0) {
            int num = k.firstKey();
            int pos = k.get(num);
            //	System.out.println("num is"+num+"pos is"+pos);
            int count = -1;
            int i = 0;
            while (count != pos) {
                if (res.get(i) == -1) {
                    count++;
                }
                i++;
            }
            i--;
            //	System.out.println("i value after 1st"+i);
            while (res.get(i) != -1) {
                i++;
            }
            //	System.out.println("i value after 2t"+i);
            res.set(i, num);
            k.remove(num);
        }
        return res;
    }

    public static class Solution2 {
        public ArrayList<Integer> order(ArrayList<Integer> heights, ArrayList<Integer> infronts) {

            quicksort(heights, 0, heights.size() - 1, infronts);
            SegmentTree root = new SegmentTree(heights.get(0));

            for (int i = 1; i < heights.size(); i++) {
                root.insert(heights.get(i), infronts.get(i));
            }

            ArrayList<Integer> output = new ArrayList<Integer>();
            root.inorder(output);
            return output;
        }

        public void quicksort(ArrayList<Integer> ar, int start, int end, ArrayList<Integer> infronts) {
            if (start < end) {
                int middle = partition(ar, start, end, infronts);
                quicksort(ar, start, middle - 1, infronts);
                quicksort(ar, middle + 1, end, infronts);
            }
        }

        public int partition(ArrayList<Integer> ar, int start, int end, ArrayList<Integer> infronts) {
            int index = start - 1;
            int pivot = ar.get(end);
            int traverse = start;
            while (traverse < end) {
                if (ar.get(traverse) > pivot) {
                    index++;
                    swap(ar, infronts, traverse, index);
                }
                traverse++;
            }
            index++;
            swap(ar, infronts, traverse, index);
            return index;
        }

        public void swap(ArrayList<Integer> ar, ArrayList<Integer> offset, int traverse, int index) {
            int temp = ar.get(traverse);
            ar.set(traverse, ar.get(index));
            ar.set(index, temp);

            temp = offset.get(traverse);
            offset.set(traverse, offset.get(index));
            offset.set(index, temp);
        }


        class SegmentTree {
            SegmentTree left, right;
            int data, value;

            public SegmentTree(int data) {
                this.data = data;
                this.value = 1;
            }

            public void inorder(ArrayList<Integer> output) {
                if (this.left != null) {
                    this.left.inorder(output);
                }
                output.add(this.data);
                if (this.right != null) {
                    this.right.inorder(output);
                }
            }

            public void insert(int data, int value) {
//			SegmentTree newNode = new SegmentTree(data);
                if (value < this.value) {
                    this.value += 1;
                    if (this.left != null) {
                        this.left.insert(data, value);
                    } else {
                        SegmentTree newNode = new SegmentTree(data);
                        this.left = newNode;
                    }
                } else {
                    if (this.right != null) {
                        this.right.insert(data, value - this.value);
                    } else {
                        SegmentTree newNode = new SegmentTree(data);
                        this.right = newNode;
                    }
                }
            }
        }

    }

    public void mainly() {
        outerloop:
        for (int i=0; i < 5; i++) {
            for (int j=0; j < 5; j++) {
                if (i * j > 6) {
                    System.out.println("Breaking");
                    break outerloop;
                }
                System.out.println(i + " " + j);
            }
        }
        System.out.println("Done");
    }

}

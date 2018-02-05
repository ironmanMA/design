package com.ironman.ma.Algorithms.Strings.KMP.PrefixArray;

import javafx.stage.Stage;

import java.util.*;

public class Solution {
    public int[] generatePrefixArrayForNeedle(char[] needle) {
        int[] res = new int[needle.length];
        int j = 0, i = 1;
        while (j <= i && i < needle.length) {
            if (needle[j] == needle[i]) {
                res[i] = j + 1;
                j++;
                i++;
            } else {
                if (j == 0) {
                    res[i] = 0;
                    i++;
                } else {
                    j = res[j - 1];
                }
            }
        }
        return res;
    }

    public int strStr(final String needle, final String haystack) {
        int index = -1;
        if (needle == null || needle.isEmpty() || haystack == null || haystack.isEmpty()
                || needle.length() > haystack.length()) {
            return index;
        }
        int[] needleMap = generatePrefixArrayForNeedle(needle.toCharArray());
        int nP = 0, hP = 0;
        while (nP <= needle.length() && hP < haystack.length()) {
            if (needle.charAt(nP) == haystack.charAt(hP)) {
                nP++;
                hP++;
                if (nP == needle.length()) {
                    index = hP - needle.length();
                    break;
                }
            } else {
                if (nP == 0) {
                    hP++;
                } else {
                    nP = needleMap[nP - 1];
                }
            }
        }
        return index;
    }

    public int romanToInt(String A) {
        HashMap<Character, Integer> val = new HashMap<Character, Integer>();
        val.put('I', 1);
        val.put('V', 5);
        val.put('X', 10);
        val.put('L', 50);
        val.put('C', 100);
        val.put('D', 500);
        val.put('M', 1000);

        char prev = A.charAt(A.length() - 1);
        int prevVal = val.get(prev);
        int index = A.length() - 2, res = prevVal;

        while (index >= 0) {
            char curr = A.charAt(index);
            int currVal = val.get(curr);
            if (currVal >= prevVal) {
                res += currVal;
            } else {
                res -= currVal;
            }
            prevVal = currVal;
            index--;
        }
        return res;
    }

    static HashMap<Integer, Character> val;
    static HashMap<Integer, String> microVal;

    public String bestSolution(int num) {
        StringBuilder ans = new StringBuilder("");
        if (num < 1) {
            return ans.toString();
        }
        int[] list = new int[]{1000, 500, 100, 50, 10, 5, 1};
        for (int denomination : list) {
            // check for immediate subtraction
            if (microVal.containsKey(num)) {
                ans.append(microVal.get(num));
                break;
            }
            while (num >= denomination) {
                ans.append(val.get(denomination));
                num -= denomination;
            }
        }
        return ans.toString();
    }

    public String intToRoman(int A) {
        if (A >= 4000 || A < 1) {
            return null;
        }
        StringBuilder ans = new StringBuilder("");
        val = new HashMap<Integer, Character>();
        microVal = new HashMap<Integer, String>();


        val.put(1, 'I');
        microVal.put(4, "IV");
        val.put(5, 'V');
        microVal.put(9, "IX");
        val.put(10, 'X');
        microVal.put(40, "XL");
        val.put(50, 'L');
        microVal.put(90, "XC");
        val.put(100, 'C');
        microVal.put(400, "CD");
        val.put(500, 'D');
        microVal.put(900, "CM");
        val.put(1000, 'M');

        int div = 10;
        while (A > 0) {
            int numToConvert = A % div;
            ans.insert(0, bestSolution(numToConvert));
            A = A - numToConvert;
            div = div * 10;
        }
        return ans.toString();
    }

    public String convert(String A, int B) {
        StringBuilder res = new StringBuilder();
        if (B < 1 || A == null || A.isEmpty()) {
            return res.toString();
        }
        if (B == 1 || B >= A.length()) {
            return A;
        }
        int[] levelIndex = new int[2 * B - 2];
        int index = 0;
        for (int i = 0; i < B; i++) {
            levelIndex[index] = i;
            index++;
        }
        for (int i = B - 2; i > 0; i--) {
            levelIndex[index] = i;
            index++;
        }
        for (int i = 0; i < B; i++) {
            for (int j = 0; j < A.length(); j++) {
                if (levelIndex[(j) % levelIndex.length] == i) {
                    res.append(A.charAt(j));
                }
            }
        }
        return res.toString();
    }

    public String genSpace(int j) {
        StringBuilder s = new StringBuilder();
        while (j > 0) {
            s.append(" ");
            j--;
        }
        return s.toString();
    }

    public ArrayList<String> genrateSpaces(int numSpaces, int maxWidth) {
        ArrayList<String> spaces = new ArrayList<String>();
        if (numSpaces == 0) {
            return spaces;
        }
        if (numSpaces == 1) {
            spaces.add(genSpace(maxWidth));
            return spaces;
        }
        int main = maxWidth / numSpaces;
        int rem = maxWidth % numSpaces;
        for (int i = 0; i < numSpaces; i++) {
            String spa = genSpace(main);
            if (rem > 0) {
                spa = spa + " ";
                rem--;
            }
            spaces.add(spa);
        }
        return spaces;
    }

    public String fit(ArrayList<String> subList, int maxWidth, int taken, boolean isLast) {
        ArrayList<String> spaces = genrateSpaces(subList.size() - 1, maxWidth - taken);
        StringBuilder s = new StringBuilder();
        int index = 0;
        if (isLast) {
            for (int i = 0; i < subList.size() - 1; i++) {
                s.append(subList.get(i));
                s.append(" ");
                index++;
            }
            s.append(subList.get(subList.size() - 1));
            s.append(genSpace(maxWidth - taken - (subList.size() - 1)));
            return s.toString();

        }
        for (int i = 0; i < subList.size() - 1; i++) {
            s.append(subList.get(i));
            s.append(spaces.get(index));
            index++;
        }
        s.append(subList.get(subList.size() - 1));
        s.append(genSpace(maxWidth - taken - (subList.size() - 1)));
        return s.toString();
    }

    public ArrayList<String> fullJustify(ArrayList<String> A, int B) {
        ArrayList<String> res = new ArrayList<String>();
        if (A == null || A.size() < 1 || B < 1) {
            return res;
        }

        int len = 0;
        ArrayList<String> subList = new ArrayList<String>();
        for (int greedyIndex = 0; greedyIndex < A.size(); greedyIndex++) {
            String cand = A.get(greedyIndex);
            if (len + cand.length() + subList.size() <= B) {
                len += cand.length();
                subList.add(cand);
            } else {
                res.add(fit(subList, B, len, false));
                greedyIndex--;
                subList = new ArrayList<String>();
                len = 0;
            }
        }
        res.add(fit(subList, B, len, true));
        return res;
    }

    public ArrayList<String> prettyJSON(String A) {
        ArrayList<String> res = new ArrayList<String>();
        if (A == null || A.isEmpty()) {
            return res;
        }
        int index = 0;
        String prev = "";
        StringBuilder app = new StringBuilder();
        while (index < A.length()) {
            if (A.charAt(index) == '[' || A.charAt(index) == '{') {
                if (app.toString().trim().length() > 0) {
                    res.add(app.toString());
                }
                app = new StringBuilder(prev);
                app.append(A.charAt(index));
                res.add(app.toString());
                prev = prev + "\t";
                app = new StringBuilder(prev);
            } else if (A.charAt(index) == ',') {
                app.append(A.charAt(index));
                res.add(app.toString());
                app = new StringBuilder(prev);
            } else if (A.charAt(index) == ']' || A.charAt(index) == '}') {
                if (app.toString().trim().length() > 0) {
                    res.add(app.toString());
                }
                prev = prev.substring(1);
                app = new StringBuilder(prev);
                app.append(A.charAt(index));
                if (index + 1 < A.length() && A.charAt(index + 1) == ',') {
                    app.append(A.charAt(index + 1));
                    index++;
                }
                res.add(app.toString());
                app = new StringBuilder(prev);
            } else {
                app.append(A.charAt(index));
            }
            index++;
        }
        if (app.toString().trim().length() > 0) {
            res.add(app.toString());
        }
        return res;
    }

    public String addBinary(String A, String B) {
        if (A == null || A.isEmpty()) {
            return B;
        }
        if (B == null || B.isEmpty()) {
            return A;
        }
        int carry = 0;
        int indexA = A.length() - 1, indexB = B.length() - 1;
        StringBuilder add = new StringBuilder();
        while (indexA >= -1 && indexB >= -1) {
            int a = 0, b = 0;
            if (indexA >= 0) {
                a = Integer.parseInt(A.charAt(indexA) + "");
                indexA--;
            }
            if (indexB >= 0) {
                b = Integer.parseInt(B.charAt(indexB) + "");
                indexB--;
            }
            int ans = a + b + carry;
            if (ans <= 1) {
                add.append(ans);
                carry = 0;
            } else if (ans == 2) {
                add.append(0);
                carry = 1;
            } else {
                add.append(1);
                carry = 1;
            }
            if (indexA < 0 && indexB < 0) {
                break;
            }
        }
        if (carry == 1) {
            add.append(1);
        }
        return add.reverse().toString();
    }

    static LinkedList<String> queue = new LinkedList<String>();

    public String div(String A, int div) {
        StringBuilder divisor = new StringBuilder();
        int carry = 0;
        int index = 0;
        while (index < A.length()) {
            int cand = Integer.parseInt(A.charAt(index) + "");
            if (cand == 1 && carry == 0 && index + 1 < A.length()) {
                if (index != 0) {
                    divisor.append(0);
                }
                cand = Integer.parseInt(A.charAt(index) + "" + A.charAt(index + 1));
                index++;
            }
            cand += 10 * carry;
            int divd = cand / div;
            carry = cand % div;
            divisor.append(divd);
            index++;
        }
        if (carry == 1) {
            return -1 + "" + divisor.toString();
        }

//        73786976294838206464
        return divisor.toString();
    }

    public int power(String A) {
        if (A == null || A.isEmpty()) {
            return 0;
        }
        if (A.length() == 1 && Integer.parseInt(A) == 1) {
            return 0;
        }
        queue.add(A);
        while (queue.size() > 0) {
            String numStr = queue.get(0);
            queue.remove(0);

            if (numStr.length() <= 18) {
                //9,223,372,036,854,775,807

                long num = Long.parseLong(numStr);


                if (num < 0) {
                    return 0;
                }

                while (num > 1) {
                    if (num % 2 == 1) {
                        return 0;
                    }
                    num = num / 2;
                }

                return 1;
            }
            String divz = div(numStr, 2);
            queue.add(divz);
        }
        return 0;

    }

    public int cntBits(ArrayList<Integer> A) {
        if (A == null || A.size() == 0) {
            return 0;
        }
        long res = 0;
        long mod = 1000000007;
        int count = 0;
        while (count < 32) {
            count++;
            long onez = 0, zeroz = 0;
            for (int i = 0; i < A.size(); i++) {
                int local = A.get(i);
                if ((local & 1) == 1) {
                    onez++;
                } else {
                    zeroz++;
                }
                local = local >> 1;
                A.set(i, local);
            }
            res = (res + ((onez * zeroz) % mod + (onez * zeroz) % mod) % mod) % mod;
        }

        return (int) (res % mod);
    }

    public int divide(int A, int B) {
        if (B == 0) {
            return Integer.MAX_VALUE;
        }

        int count = 0;
        int mul = 1;
        int div = 0;

        if ((A < 0 && B > 0)
                || (A > 0 && B < 0)) {
            mul = -1;
        }
        A = Math.abs(A);
        if (A < 0) {
            A = Integer.MAX_VALUE;
        }
        B = Math.abs(B);

        if (B == 1) {
            if (mul < 0) {
                return 0 - A;
            }
            if (A < 0) {
                return Integer.MAX_VALUE;
            } else {
                return A;
            }
        }

        int jockey = (B << count);
        while (jockey - A <= 0) {
            count++;
            jockey = (B << count);
        }

        count = count - 1;
        while (count >= 0) {
            if (A >= (B << count)) {
                div += 1 << (count);
                A -= (B << count);
            }
            count--;
        }
        if (mul < 0) {
            return 0 - div;
        }
        return div;
    }

    public void merge(ArrayList<Integer> a, ArrayList<Integer> b) {
        int aIndex = 0, bIndex = 0;
        while (aIndex < a.size() || bIndex < b.size()) {
            if (bIndex == b.size()) {
                break;
            }
            if (aIndex == a.size()) {
                while (bIndex < b.size()) {
                    a.add(b.get(bIndex));
                    bIndex++;
                }
                break;
            }
            if (a.get(aIndex) >= b.get(bIndex)) {
                a.add(aIndex, b.get(bIndex));
                bIndex++;
            } else {
                aIndex++;
            }
        }

        System.out.println(a);
        return;
    }

    public int removeDuplicates(ArrayList<Integer> a) {
        int indexA = 1;
        int prev = a.get(0);
        int seen = 1;
        int start = indexA;
        int end = indexA;
        while (indexA < a.size()) {
            if (a.get(indexA) == prev) {
                if (seen >= 2) {
                    end++;
                } else {
                    start++;
                }
                indexA++;
                seen++;
            } else {
                if (seen > 2) {
                    a.subList(start, end + 1).clear();
                }
                indexA = start + 1;
                prev = a.get(indexA - 1);
                seen = 1;
                start = indexA;
                end = indexA;
            }
        }
        if (end + 1 <= a.size()) {
            a.subList(start, end + 1).clear();
        }
        System.out.println(a);
        return a.size();
    }

    public int removeElementOld(ArrayList<Integer> a, int b) {
        int start = Integer.MAX_VALUE;
        int end = Integer.MIN_VALUE;
        int indexA = 0;
        while (indexA < a.size()) {
            if (a.get(indexA) == b) {
                start = Math.min(indexA, start);
                end = Math.max(indexA, end);
            } else {
                if (start != Integer.MAX_VALUE && end != Integer.MIN_VALUE) {
                    a.subList(start, end + 1).clear();
                    indexA = start;
                    start = Integer.MAX_VALUE;
                    end = Integer.MIN_VALUE;
                }
            }
            indexA++;
        }
        if (end + 1 <= a.size() && start != Integer.MAX_VALUE && end != Integer.MIN_VALUE) {
            a.subList(start, end + 1).clear();
        }
        System.out.println(a);
        return a.size();
    }

    public int removeElement(ArrayList<Integer> a, int b) {
        int start = Integer.MAX_VALUE;
        int end = Integer.MIN_VALUE;
        int indexA = 0;
        while (indexA < a.size()) {
            if (a.get(indexA) == b) {
                start = Math.min(indexA, start);
                end = Math.max(indexA, end);
            } else {
                if (start != Integer.MAX_VALUE && end != Integer.MIN_VALUE) {
                    int tmp = a.get(start);
                    a.set(start, a.get(indexA));
                    a.set(indexA, tmp);
                    start++;
                    end++;
                }
            }
            indexA++;
        }
        System.out.println(a);
        return Math.min(indexA, start);
    }

    public int twoSumClosest(ArrayList<Integer> A, int B, int indexC1) {
        int iterA = 0;
        int iterB = A.size() - 1;
        int closeSum = A.get(iterA) + A.get(iterB);
        int lowest = Integer.MAX_VALUE;
        while (iterA < A.size() && iterB >= 0) {
            int sum = A.get(iterA) + A.get(iterB);
            if (Math.abs(sum - B) < lowest
                    && iterA != iterB && iterA != indexC1 && iterB != indexC1) {
                lowest = Math.abs(sum - B);
                closeSum = sum;
            }
            if (sum == B
                    && iterA != iterB && iterA != indexC1 && iterB != indexC1) {
                return sum;
            } else if (sum < B) {
                iterA++;
            } else {
                iterB--;
            }
        }
        return closeSum;
    }

    public int threeSumClosest(ArrayList<Integer> A, int B) {
        Collections.sort(A);
        int lowestDistTillNow = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < A.size(); i++) {
            int cand1 = A.get(i);
            int closestToFind = twoSumClosest(A, B - cand1, i);
            if (Math.abs(cand1 + closestToFind - B) < Math.abs(lowestDistTillNow)) {
                lowestDistTillNow = Math.abs(cand1 + closestToFind - B);
                sum = (cand1 + closestToFind);
            }
        }
        return sum;
    }

    public int nTriang(ArrayList<Integer> A) {
        long count = 0;
        long modBase = 1000000009;
        Collections.sort(A);
        for (int s1 = 0; s1 <= A.size() - 3; s1++) {
            int l1 = A.get(s1);
            int s2 = s1 + 1;
            int s3 = s2 + 1;
            while (s2 < A.size() - 1 && s3 < A.size()) {
                int l2 = A.get(s2);
                int l3 = A.get(s3);
                if (l1 + l2 > l3) {
                    count = (count % modBase + 1) % modBase;
                    if (s2 < s3 - 1) {
                        s2++;
                    } else {
                        s3++;
                    }
                    System.out.println(l1 + ", " + l2 + ", " + l3);
                } else {
                    s2++;
                    if (s2 == s3) {
                        s3++;
                    }
                }
            }
        }
        return (int) (count % modBase);
    }

    public int nTriangNew(ArrayList<Integer> A) {
        long count = 0;
        long modBase = 1000000009;
        Collections.sort(A);
        long n = A.size();
        for (long i = 0; i < n - 2; ++i) {
            long k = i + 2;
            for (long j = i + 1; j < n; ++j) {
                while (k < n && A.get((int) i) + A.get((int) j) > A.get((int) k)) {
                    ++k;
                }
                count = (count + k - j - 1)%modBase;
            }
        }
        return (int) (count % modBase);
    }

    public ArrayList<Integer> maxone(ArrayList<Integer> A, int B) {
        int maxOnes=Integer.MIN_VALUE;
        int maxi=0,maxj=0;
        int c0=0;
        int i=0;int j=0;
        while(j<A.size() && i<A.size() && A.size()-i>maxOnes){
            while(c0<=B && j<A.size()){
                if(A.get(j)==0){
                    c0++;
                }
                j++;
            }
            c0--;
            if(j!=A.size())
                j--;
            //max zeros found
            if(j-i>maxOnes && c0<=B){
                maxOnes=(j-i);
                maxi=i;
                maxj=j;
            }
            while(i<A.size() && A.get(i)!=0){
                i++;
            }
            i++;
            c0--;
        }
        ArrayList<Integer> res=new ArrayList<Integer>();
        for(int k=maxi;k<maxj;k++){
            res.add(k);
        }
        return res;
    }

}

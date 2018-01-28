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

}

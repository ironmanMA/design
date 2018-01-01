package com.ironman.ma.Algorithms.PandC;

import java.util.*;

public class GenerateIPs {
    static HashMap<String, ArrayList<String>> memo = new HashMap<String, ArrayList<String>>();

    static int numSubIPs(String ip) {
        return ip.split("/.").length;
    }

    static ArrayList<String> generateSubIPAddrs(String subString, int depth) {
        if (subString.isEmpty()) {
            return new ArrayList<String>();
        }

        String cacheKey = subString + "_" + depth;

        if (memo.containsKey(cacheKey)) {
            return memo.get(subString);
        }

        ArrayList<String> subList = new ArrayList<String>();
        if (subString.length() == 1) {
            subList.add(subString);
            memo.put(cacheKey, subList);
            return subList;
        }

        if (depth == 4) {
            if (subString.length() > 3 || Integer.parseInt(subString) > 255) {
                return subList;
            } else {
                subList.add(subString);
                memo.put(cacheKey, subList);
                return subList;
            }

        }

        HashSet<String> finRes = new HashSet<String>();

        //take1
        ArrayList<String> list1 = generateSubIPAddrs(subString.substring(1), depth + 1);
        for (String partIP : list1) {
            if (!finRes.contains(partIP) && (numSubIPs(partIP) + depth == 4)) {
                // if(validateIP(partIP)){
                finRes.add(partIP);
                subList.add(subString.charAt(0) + "." + partIP);
                // }
            }
        }

        //take2
        if (subString.length() > 2) {
            list1 = generateSubIPAddrs(subString.substring(2), depth + 1);
            for (String partIP : list1) {
                if (!finRes.contains(partIP) && (numSubIPs(partIP) + depth == 4)) {
                    // if(validateIP(partIP)){
                    finRes.add(partIP);
                    subList.add(subString.charAt(0) + subString.charAt(1) + "." + partIP);
                    // }
                }
            }
        }
        //take3 and check
        if ((Integer.parseInt(subString.substring(0, 3)) <= 255) && subString.length() > 3) {
            list1 = generateSubIPAddrs(subString.substring(2), depth + 1);
            for (String partIP : list1) {
                if (!finRes.contains(partIP) && (numSubIPs(partIP) + depth == 4)) {
                    // if(validateIP(partIP)){
                    finRes.add(partIP);
                    subList.add(subString.charAt(0) + subString.charAt(1) + subString.charAt(2) + "." + partIP);
                    // }
                }
            }
        }

        memo.put(cacheKey, subList);
        return subList;

    }

//    public static ArrayList<String> generateIPAddrs(String s) {
//        return generateSubIPAddrs(s,1);
//    }

    public static ArrayList<String> generateIPAddrs(String input) {
        class IpLevelNode {
            public int level = 0;
            public String predecessor;
            public String successor;

            public IpLevelNode(int level, String ipToAppend, String predecessor, String successor) {
                this.level = level;
                this.successor = successor;
                if (level == 0) {
                    this.predecessor = ipToAppend;
                } else {
                    this.predecessor = predecessor + "." + ipToAppend;
                }
            }
        }

        ArrayList<String> out = new ArrayList<String>();
        Deque<IpLevelNode> stack = new LinkedList<IpLevelNode>();
        // Push 3 possibilities onto the stack
        stack.addFirst(new IpLevelNode(0, input.substring(0, 1), "", input.substring(1)));
        stack.addFirst(new IpLevelNode(0, input.substring(0, 2), "", input.substring(2)));
        stack.addFirst(new IpLevelNode(0, input.substring(0, 3), "", input.substring(3)));

        while (!stack.isEmpty()) {
            IpLevelNode node = stack.removeFirst();
            int curlevel = node.level;
            String predecessor = node.predecessor;
            String remaining = node.successor;
            if (curlevel == 3 && remaining.length() == 0) {
                out.add(node.predecessor);
                continue;
            }
            int i = 1;
            while (i <= 3) {
                if (remaining.length() < i) break;
                String ipToAppend = remaining.substring(0, i);
                String successor = remaining.substring(i);
                if (ipToAppend.length() > 0) {
                    int numIpToAppend = Integer.parseInt(ipToAppend);
                    if (numIpToAppend <= 255)
                        stack.addFirst(new IpLevelNode(curlevel + 1, ipToAppend, predecessor, successor));
                }
                i++;
            }
        }
        return out;
    }
}

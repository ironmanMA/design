package com.ironman.ma.Stack.game.of.two.stacks;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Stack;

/**
 * Created by 127.0.0.1.ma on 08/10/17.
 */
public class Solution {

    private static int sumLimit = -1;

    public static void main(String[] args) throws IOException {
//        Scanner input = new Scanner(System.in);
        Reader input = new Reader();
        int numInputs = input.nextInt();
//        System.out.println(numInputs);
        for (int i = 0; i < numInputs; i++) {
            int stack1Size = input.nextInt();
            int stack2Size = input.nextInt();
//            System.out.println(stack1Size + ", " + stack2Size);
            sumLimit = input.nextInt();
//            System.out.println("Sum Limit :" + sumLimit);
//            ArrayList<Integer> list1 = new ArrayList<Integer>();
            int[] listArr1 = new int[stack1Size + 1];
//            ArrayList<Integer> list2 = new ArrayList<Integer>();
            int[] listArr2 = new int[stack2Size + 1];
//            Stack<Integer> stack1 = new Stack<Integer>();
//            int sumTillList = 0;
            for (int j = 0; j < stack1Size; j++) {
//                int member = input.nextInt();
//                System.out.println("Stack 1 :" + member);
//                sumTillList += member;
//                if (sumTillList <= sumLimit)
//                    list1.add(member);
//                    list1.add(input.nextInt());
                listArr1[j] = input.nextInt();
            }
//            list1.add(sumLimit+1);
            listArr1[stack1Size] = sumLimit + 1;
//            for (int j = list.size() - 1; j >= 0; j--) {
//                stack1.push(list.get(j));
//            }

//            Stack<Integer> stack2 = new Stack<Integer>();
//            sumTillList = 0;
            for (int j = 0; j < stack2Size; j++) {
//                int member = input.nextInt();
//                System.out.println("Stack 2 :" + member);
//                sumTillList += member;
//                if (sumTillList <= sumLimit)
//                    list2.add(member);
//                    list2.add(input.nextInt());
                listArr2[j] = input.nextInt();
            }
//            list2.add(sumLimit+1);
            listArr2[stack2Size] = sumLimit + 1;
//            for (int j = list.size() - 1; j >= 0; j--) {
//                stack2.push(list.get(j));
//            }
//            System.out.println(stack1.size() + ", " + stack2.size());
//            System.out.println(getMaxPicks(list1, 0, list2, 0, 0, 0));
//            System.out.println(getMaxPicks(listArr1, 0, listArr2, 0, 0, 0));
            System.out.println(getMaxPicks(listArr1, listArr2));
//            System.out.println(getMaxPicks(stack1, stack2, 0, 0));
        }

        input.close();
    }

    private static int getMaxPicks(int[] listArr1, int[] listArr2) {
        int numPicks = 0, currSum = 0;
        Stack<Integer> pickedA = new Stack<Integer>();
        Stack<Integer> pickedB = new Stack<Integer>();
        for (int i = 0; i < listArr1.length; i++) {
            if (currSum + listArr1[i] <= sumLimit) {
                currSum += listArr1[i];
                pickedA.push(listArr1[i]);
                numPicks++;
            } else {
                break;
            }

        }
        for (int i = 0; i < listArr2.length; i++) {
            /*
                pop Till Less
             */
            while (currSum + listArr2[i] > sumLimit) {
                if (pickedA.size() == 0) {
                    break;
                } else {
                    int num = pickedA.pop();
                    currSum -= num;
                }
            }
            if (currSum + listArr2[i] <= sumLimit) {
                currSum += listArr2[i];
                pickedB.push(listArr2[i]);
                numPicks = Math.max(numPicks, (pickedA.size() + pickedB.size()));
            } else {
                return numPicks;
            }

        }
        return numPicks;
    }

    private static int getMaxPicks(int[] list1, int toVistList1, int[] list2, int toVistList2, int currSum, int numPicks) {
        if (
                toVistList1 < list1.length &&
                        toVistList2 < list2.length &&
                        list1[(toVistList1)] + currSum <= sumLimit &&
                        list2[(toVistList2)] + currSum <= sumLimit) {

            int picksList1Pop = getMaxPicks(list1, toVistList1 + 1,
                    list2, toVistList2,
                    list1[(toVistList1)] + currSum, numPicks + 1);
            int picksList2Pop = getMaxPicks(list1, toVistList1, list2, toVistList2 + 1, list2[(toVistList2)] + currSum,
                    numPicks + 1);
            return Math.max(picksList1Pop, picksList2Pop);
        } else if (
                toVistList1 < list1.length &&
                        toVistList2 < list2.length &&
                        list1[(toVistList1)] + currSum <= sumLimit &&
                        list2[(toVistList2)] + currSum > sumLimit) {
            return getMaxPicks(list1, toVistList1 + 1, list2, toVistList2, list1[(toVistList1)] + currSum,
                    numPicks + 1);
        } else if (
                toVistList1 < list1.length &&
                        toVistList2 < list2.length &&
                        list1[(toVistList1)] + currSum > sumLimit &&
                        list2[(toVistList2)] + currSum <= sumLimit) {
            return getMaxPicks(list1, toVistList1, list2, toVistList2 + 1, list2[(toVistList2)] + currSum,
                    numPicks + 1);
        } else {
            return numPicks;
        }

    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }

    /*
    private static int getMaxPicks(ArrayList<Integer> list1, int toVistList1,
                                   ArrayList<Integer> list2, int toVistList2,
                                   int currSum, int numPicks) {
        if (
                toVistList1 < list1.size() &&
                        toVistList2 < list2.size() &&
                        list1.get(toVistList1) + currSum <= sumLimit &&
                        list2.get(toVistList2) + currSum <= sumLimit) {

            int picksList1Pop = getMaxPicks(list1, toVistList1 + 1,
                    list2, toVistList2,
                    list1.get(toVistList1) + currSum, numPicks + 1);
            int picksList2Pop = getMaxPicks(list1, toVistList1, list2, toVistList2 + 1, list2.get(toVistList2) + currSum,
                    numPicks + 1);
            return Math.max(picksList1Pop, picksList2Pop);
        } else if (
                toVistList1 < list1.size() &&
                        toVistList2 < list2.size() &&
                        list1.get(toVistList1) + currSum <= sumLimit &&
                        list2.get(toVistList2) + currSum > sumLimit) {
            return getMaxPicks(list1, toVistList1 + 1, list2, toVistList2, list1.get(toVistList1) + currSum,
                    numPicks + 1);
        } else if (
                toVistList1 < list1.size() &&
                        toVistList2 < list2.size() &&
                        list1.get(toVistList1) + currSum > sumLimit &&
                        list2.get(toVistList2) + currSum <= sumLimit) {
            return getMaxPicks(list1, toVistList1, list2, toVistList2 + 1, list2.get(toVistList2) + currSum,
                    numPicks + 1);
        } else {
            return numPicks;
        }

    }

    */

/*
    private static Integer getMaxPicks(Stack<Integer> stack1, Stack<Integer> stack2, int currSum, int currPicks) {
        if (stack1.peek() + currSum <= sumLimit &&
                stack2.peek() + currSum <= sumLimit) {
            int stack1Peek = stack1.peek();
            int stack2Peek = stack2.peek();
//            if (stack1Peek < stack2Peek) {
//                stack1.pop();
//                return getMaxPicks(stack1, stack2, currSum + stack1Peek, currPicks + 1);
//            } else {
//                stack2.pop();
//                return getMaxPicks(stack1, stack2, currSum + stack2Peek, currPicks + 1);
//            }
            stack1.pop();
            int pickIfStack1Pop = getMaxPicks(stack1, stack2, currSum + stack1Peek, currPicks + 1);

            stack2.pop();
            stack1.push(stack1Peek);
            int pickIfStack2Pop = getMaxPicks(stack1, stack2, currSum + stack2Peek, currPicks + 1);
            return Math.max(pickIfStack1Pop, pickIfStack2Pop);

        } else if (stack1.peek() + currSum <= sumLimit) {
            int stack1Peek = stack1.pop();
            return getMaxPicks(stack1, stack2, currSum + stack1Peek, currPicks + 1);
        } else if (stack2.peek() + currSum <= sumLimit) {
            int stack2Peek = stack2.pop();
            return getMaxPicks(stack1, stack2, currSum + stack2Peek, currPicks + 1);
        } else {
            return currPicks;
        }
    }
*/
}

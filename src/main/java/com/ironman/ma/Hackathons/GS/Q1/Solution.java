package com.ironman.ma.Hackathons.GS.Q1;

import java.io.*;
import java.util.*;

public class Solution {
    static TreeMap<String, ArrayList<Long>> mapBuyBook = new TreeMap<String, ArrayList<Long>>();
    static TreeMap<String, ArrayList<Long>> mapSellBook = new TreeMap<String, ArrayList<Long>>();
    static TreeSet<String> mapBook = new TreeSet<String>();
    static HashMap<Long, Order> orderTable = new HashMap<Long, Order>();
    static HashMap<Long, Order> cancelledOrderTable = new HashMap<Long, Order>();

    static class Order {
        long id;
        long timestamp;
        String timestampStr;
        String symbol;
        String type;
        String side;
        float price;
        long quantity;
    }

    /*
     * Complete the function below.
     */
    static String[] processQueries(String[] queries) {
        LinkedList<String> ansList = new LinkedList<String>();
        // Write your code here.
        long prevTs = Long.MIN_VALUE;
        for (String command : queries) {
            String[] commandForm = command.split(",");
            if (commandForm[0].equals("N")) {
                Order order = new Order();
                order.id = Long.parseLong(commandForm[1]);
                order.timestamp = Long.parseLong(commandForm[2]);
                if (order.timestamp < prevTs) {
                    ansList.add(order.id + " - Reject - 303 - Invalid order details");
                    continue;
                }
                order.timestampStr = commandForm[2];
                order.symbol = commandForm[3];
                order.type = commandForm[4];
                if (!(order.type.equals("M")
                        || order.type.equals("L")
                        || order.type.equals("I"))) {
                    ansList.add(order.id + " - Reject - 303 - Invalid order details");
                    continue;
                }
                order.side = commandForm[5];
                order.price = Float.parseFloat(commandForm[6]);
                if (commandForm[7].indexOf(".") > 0 || order.price < 0) {
                    ansList.add(order.id + " - Reject - 303 - Invalid order details");
                    continue;
                }
                order.quantity = Long.parseLong(commandForm[7]);
                if (order.quantity < 0) {
                    ansList.add(order.id + " - Reject - 303 - Invalid order details");
                    continue;
                }

                if (orderTable.containsKey(order.id)) {
                    ansList.add(order.id + " - Reject - 303 - Invalid order details");
                    continue;
                }

                if (order.side.equals("B")) {
                    ArrayList<Long> idList = new ArrayList<Long>();
                    if (mapBuyBook.containsKey(order.symbol)) {
                        idList = mapBuyBook.get(order.symbol);
                    }
                    idList.add(order.id);
                    mapBuyBook.put(order.symbol, idList);
                } else if (order.side.equals("S")) {
                    ArrayList<Long> idList = new ArrayList<Long>();
                    if (mapSellBook.containsKey(order.symbol)) {
                        idList = mapSellBook.get(order.symbol);
                    }
                    idList.add(order.id);
                    mapSellBook.put(order.symbol, idList);
                } else {
                    ansList.add(order.id + " - Reject - 303 - Invalid order details");
                    continue;
                }
                orderTable.put(order.id, order);
                mapBook.add(order.symbol);
                ansList.add(order.id + " - Accept");
                prevTs = order.timestamp;

            } else if (commandForm[0].equals("A")) {
                long id = Long.parseLong(commandForm[1]);
                if (orderTable.containsKey(id)) {
                    Order orderFromTable = orderTable.get(id);
                    Order order = new Order();
                    if (order.timestamp < prevTs) {
                        ansList.add(order.id + " - Reject - 303 - Invalid order details");
                        continue;
                    }
                    order.id = Long.parseLong(commandForm[1]);
                    order.timestamp = Long.parseLong(commandForm[2]);
                    order.timestampStr = commandForm[2];
                    order.symbol = commandForm[3];
                    order.type = commandForm[4];
                    order.side = commandForm[5];
                    order.price = Float.parseFloat(commandForm[6]);
                    if (commandForm[7].indexOf(".") > 0 || order.price < 0) {
                        ansList.add(order.id + " - AmendReject - 101 - Invalid amendment details");
                        continue;
                    }
                    order.quantity = Long.parseLong(commandForm[7]);
                    if (order.quantity < 0) {
                        ansList.add(order.id + " - AmendReject - 101 - Invalid amendment details");
                        continue;
                    }

                    if (orderTable.containsKey(order.id)) {
                        ansList.add(order.id + " - AmendReject - 101 - Invalid amendment details");
                        continue;
                    }
                    if (orderFromTable.timestamp != order.timestamp
                            || orderFromTable.symbol != order.symbol
                            || orderFromTable.type != order.type
                            || orderFromTable.side != order.side
                            || orderFromTable.timestamp != order.timestamp) {
                        ansList.add(order.id + " - AmendReject - 101 - Invalid amendment details");
                        continue;
                    }
                    orderTable.put(order.id, order);

                } else {
                    ansList.add(id + " - AmendReject - 404 - Order does not exist");
                }
            } else if (commandForm[0].equals("X")) {
                long id = Long.parseLong(commandForm[1]);
                long ts = Long.parseLong(commandForm[2]);
                if (ts < prevTs) {
                    ansList.add(id + " - CancelReject - 303 - Invalid order details");
                    continue;
                }
                if (orderTable.containsKey(id)) {
                    orderTable.remove(id);
                    ansList.add(id + " – CancelAccept");
                } else {
                    ansList.add(id + " – CancelReject - 404 - Order does not exist");
                    continue;
                }
                prevTs = ts;
            } else if (commandForm[0].equals("M")) {

            } else if (commandForm[0].equals("Q")) {
                long time = Long.MAX_VALUE;
                ArrayList<String> symbolList = new ArrayList<String>(mapBook);
                for (String s : commandForm) {
                    if (isNumeric(s)) {
                        time = Long.parseLong(s);
                    } else {
                        symbolList = new ArrayList<String>();
                        symbolList.add(s);
                    }
                }
                ArrayList<Long> sells = new ArrayList<Long>();
                ArrayList<Long> buys = new ArrayList<Long>();

                int iterBuy = 0;
                int iterSell = 0;
                for (String symbolCand : symbolList) {
                    iterBuy = 0;
                    iterSell = 0;

                    if (mapBuyBook.containsKey(symbolCand)) {
                        buys = mapBuyBook.get(symbolCand);
                    }
                    if (mapSellBook.containsKey(symbolCand)) {
                        sells = mapSellBook.get(symbolCand);
                    }
                    while (iterBuy < buys.size() || iterSell < sells.size()) {
                        String buyPart = "";
                        String sellPart = "";
                        String full = symbolCand;
                        if (iterBuy < buys.size()) {
                            Order buy = orderTable.get(buys.get(iterBuy));
                            if (buy.timestamp < time) {
                                buyPart = buy.id + "," + buy.type + "," + buy.quantity + "," + buy.price;
                            }
                            iterBuy++;
                        }
                        if (iterSell < sells.size()) {
                            Order sell = orderTable.get(sells.get(iterSell));
                            if (sell.timestamp < time) {
                                sellPart = sell.price + "," + sell.quantity + "," + sell.type + "," + sell.id;
                            }
                            iterSell++;
                        }
                        if (buyPart.isEmpty() && sellPart.isEmpty()) {
                            break;
                        }
                        full = full + "|" + buyPart + "|" + sellPart;
                        ansList.add(full);
                    }
                }
            }
        }
        String[] ansArr = new String[ansList.size()];
        int ansIter = 0;
        for (String ans : ansList) {
            ansArr[ansIter] = ans;
            ansIter++;
        }
        return ansArr;
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bw = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bw = null;
        if (bw == null) {
            bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        int queriesSize = Integer.parseInt(scan.nextLine().trim());

        String[] queries = new String[queriesSize];

        for (int queriesItr = 0; queriesItr < queriesSize; queriesItr++) {
            String queriesItem = scan.nextLine();
            queries[queriesItr] = queriesItem;

        }

        String[] response = processQueries(queries);

        for (int responseItr = 0; responseItr < response.length; responseItr++) {
            bw.write(response[responseItr]);

            if (responseItr != response.length - 1) {
                bw.write("\n");
            }
        }

        bw.newLine();

        bw.close();
    }
}


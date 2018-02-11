package com.ironman.ma.Hackathons.GS.Q1;

import java.io.*;
import java.util.*;

public class Solution {
    static TreeMap<String, ArrayList<Long>> mapBuyBook = new TreeMap<String, ArrayList<Long>>();
    static TreeMap<String, ArrayList<Long>> mapBuySell = new TreeMap<String, ArrayList<Long>>();
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
        long prevTs = Long.MAX_VALUE;
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
                orderTable.put(order.id, order);
                if (order.side.equals("B")) {
                    ArrayList<Long> idList = new ArrayList<Long>();
                    if (mapBuyBook.containsKey(order.symbol)) {
                        idList = mapBuyBook.get(order.symbol);
                    }
                    idList.add(order.id);
                    mapBuyBook.put(order.symbol, idList);
                } else {
                    ArrayList<Long> idList = new ArrayList<Long>();
                    if (mapBuySell.containsKey(order.symbol)) {
                        idList = mapBuySell.get(order.symbol);
                    }
                    idList.add(order.id);
                    mapBuySell.put(order.symbol, idList);
                }
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

            }

        }
        String[] ans = new String[0];

        return ans;
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


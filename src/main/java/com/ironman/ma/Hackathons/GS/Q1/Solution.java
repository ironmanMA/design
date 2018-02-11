package com.ironman.ma.Hackathons.GS.Q1;

import java.io.*;
import java.util.*;

public class Solution {
    static TreeMap<String, ArrayList<Long>> mapBuyBook = new TreeMap<String, ArrayList<Long>>();
    static TreeMap<String, ArrayList<Long>> mapSellBook = new TreeMap<String, ArrayList<Long>>();
    static TreeSet<String> mapBook = new TreeSet<String>();
    static HashMap<Long, Order> orderTable = new HashMap<Long, Order>();
    static HashMap<Long, Order> CompletedOrderTable = new HashMap<Long, Order>();

    static class Order {
        long id;
        long timestamp;
        String timestampStr;
        String symbol;
        String type;
        String side;
        float price = 0;
        long quantity = 0;
        long matchedQuantity = 0;
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
                    order.id = Long.parseLong(commandForm[1]);
                    if (Long.parseLong(commandForm[2]) < prevTs) {
                        ansList.add(order.id + " - AmendReject - 101 - Invalid amendment details");
                        continue;
                    }
                    order.timestamp = orderFromTable.timestamp;
                    order.timestampStr = orderFromTable.timestampStr;
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

                    if (!orderFromTable.symbol.equals(order.symbol)
                            || !orderFromTable.type.equals(order.type)
                            || !orderFromTable.side.equals(order.side)) {
                        ansList.add(order.id + " - AmendReject - 101 - Invalid amendment details");
                        continue;
                    }
                    if (orderFromTable.matchedQuantity >= order.quantity) {
                        //remove this
                        ansList.add(order.id + " - AmendAccept");
                        orderTable.remove(order.id);
                        continue;
                    }
                    order.quantity -= orderFromTable.matchedQuantity;
                    order.matchedQuantity = orderFromTable.matchedQuantity;
                    orderTable.put(order.id, order);
                    ansList.add(order.id + " - AmendAccept");
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
                    ansList.add(id + " - CancelAccept");
                } else {
                    ansList.add(id + " – CancelReject - 404 - Order does not exist");
                    continue;
                }
                prevTs = ts;
            } else if (commandForm[0].equals("M")) {
                long time = Long.MAX_VALUE;
                ArrayList<String> symbolList = new ArrayList<String>(mapBook);
                if (commandForm.length > 1) {
                    time = Long.parseLong(commandForm[1]);
                }
                if (commandForm.length > 2) {
                    symbolList = new ArrayList<String>();
                    symbolList.add(commandForm[2]);
                }
                for (String symbolCand : symbolList) {
                    ArrayList<Long> sells = new ArrayList<Long>();
                    ArrayList<Long> buys = new ArrayList<Long>();
                    if (mapBuyBook.containsKey(symbolCand)) {
                        buys = mapBuyBook.get(symbolCand);
                    }
                    if (mapSellBook.containsKey(symbolCand)) {
                        sells = mapSellBook.get(symbolCand);
                    }

                    if (buys == null || sells == null || buys.size() == 0 || sells.size() == 0) {
                        continue;
                    }

                    sells = cleanAndSortSell(sells);
                    buys = cleanAndSortBuy(buys);

                    mapBuyBook.put(symbolCand, buys);
                    mapSellBook.put(symbolCand, sells);

                    if (sells.size() == 0 || buys.size() == 0) {
                        continue;
                    }
                    int iterBuy = 0;
                    int iterSell = 0;
                    while (iterBuy < buys.size() && iterSell < sells.size()) {
                        String buyPart = "";
                        String sellPart = "";
                        String full = symbolCand;
                        Order buy = orderTable.get(buys.get(iterBuy));
                        Order sell = orderTable.get(sells.get(iterSell));

                        if (buy.timestamp > time) {
                            iterBuy++;
                            continue;
                        }
                        if (sell.timestamp > time) {
                            iterSell++;
                            continue;
                        }

                        if (buy.price >= sell.price || buy.type.equals("M")) {
                            long buyingQuantity = Math.min(buy.quantity, sell.quantity);
                            float buyingPrice = sell.price;

                            buyPart = buy.id + "," + buy.type + "," + buyingQuantity + "," + String.format("%.2f", buyingPrice);
                            sellPart = String.format("%.2f", buyingPrice) + "," + buyingQuantity + "," + sell.type + "," + sell.id;
                            full = full + "|" + buyPart + "|" + sellPart;
                            ansList.add(full);

                            if (buy.quantity == buyingQuantity || buy.type.equals("I")) {
                                orderTable.remove(buys.get(iterBuy));
                                buys.remove(iterBuy);
                            } else {
                                buy.quantity -= buyingQuantity;
                                buy.matchedQuantity += buyingQuantity;
                                orderTable.put(buys.get(iterBuy), buy);
                            }

                            if (sell.quantity == buyingQuantity || sell.type.equals("I")) {
                                orderTable.remove(sells.get(iterSell));
                                sells.remove(iterSell);
                            } else {
                                sell.quantity -= buyingQuantity;
                                sell.matchedQuantity += buyingQuantity;
                                orderTable.put(sells.get(iterSell), sell);
                            }
                        } else {
                            break;
                        }
                    }
                    //remove all IOC orders
//                    cleanupForIOC
                    sells = cleanupForIOC(sells);
                    buys = cleanupForIOC(buys);

                    mapBuyBook.put(symbolCand, buys);
                    mapSellBook.put(symbolCand, sells);


                }
            } else if (commandForm[0].equals("Q")) {
                long time = Long.MAX_VALUE;
                ArrayList<String> symbolList = new ArrayList<String>(mapBook);
                for (int i = 1; i < commandForm.length; i++) {
                    String s = commandForm[i];
                    if (isNumeric(s)) {
                        time = Long.parseLong(s);
                    } else {
                        symbolList = new ArrayList<String>();
                        symbolList.add(s);
                    }
                }
                for (String symbolCand : symbolList) {
                    ArrayList<Long> sells = new ArrayList<Long>();
                    ArrayList<Long> buys = new ArrayList<Long>();
                    int iterBuy = 0;
                    int iterSell = 0;

                    int outputCount = 0;

                    if (mapBuyBook.containsKey(symbolCand)) {
                        buys = mapBuyBook.get(symbolCand);
                    }
                    if (mapSellBook.containsKey(symbolCand)) {
                        sells = mapSellBook.get(symbolCand);
                    }

                    if (buys == null || sells == null) {
                        continue;
                    }

                    sells = cleanAndSortSell(sells);
                    buys = cleanAndSortBuy(buys);

                    mapBuyBook.put(symbolCand, buys);
                    mapSellBook.put(symbolCand, sells);

                    if (sells.size() == 0 && buys.size() == 0) {
                        continue;
                    }
                    while (iterBuy < buys.size() || iterSell < sells.size()) {
                        if (outputCount > 5) {
                            break;
                        }
                        String buyPart = "";
                        String sellPart = "";
                        String full = symbolCand;

                        if (iterBuy < buys.size()) {
                            Order buy = orderTable.get(buys.get(iterBuy));
                            if (buy != null && buy.timestamp <= time) {
                                buyPart = buy.id + "," + buy.type + "," + buy.quantity + "," + String.format("%.2f", buy.price);
                            } else {
                                iterBuy++;
                                continue;
                            }
                        }
                        if (iterSell < sells.size()) {
                            Order sell = orderTable.get(sells.get(iterSell));
                            if (sell != null && sell.timestamp <= time) {
                                sellPart = String.format("%.2f", sell.price) + "," + sell.quantity + "," + sell.type + "," + sell.id;
                            } else {
                                iterSell++;
                                continue;

                            }
                        }
                        if (buyPart.isEmpty() && sellPart.isEmpty()) {
                            break;
                        }
                        iterBuy++;
                        iterSell++;
                        full = full + "|" + buyPart + "|" + sellPart;
                        ansList.add(full);
                        outputCount++;
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

    public static ArrayList<Long> cleanup(ArrayList<Long> arr) {
        ArrayList<Long> res = new ArrayList<Long>();
        for (Long anArr : arr) {
            if (orderTable.containsKey(anArr)) {
                res.add(anArr);
            }
        }
        return res;
    }

    public static ArrayList<Long> cleanupForIOC(ArrayList<Long> arr) {
        ArrayList<Long> res = new ArrayList<Long>();
        for (Long anArr : arr) {
            if (orderTable.containsKey(anArr)) {
                if (!orderTable.get(anArr).type.equals("I")) {
                    res.add(anArr);
                }
            }
        }
        return res;
    }

    public static ArrayList<Long> cleanAndSortBuy(ArrayList<Long> sellArray) {
        sellArray = cleanup(sellArray);
        Collections.sort(sellArray, new Comparator<Long>() {
            public int compare(Long order1ID, Long order2ID) {
                Order bo1 = orderTable.get(order1ID);
                Order bo2 = orderTable.get(order2ID);
                if (bo1.type.equals("M")) {
                    if (bo2.type.equals("M")) {
                        return (int) (bo1.timestamp - bo2.timestamp);
                    }
                    return -1;
                }
                if (bo1.price == bo2.price) {
                    return (int) (bo1.timestamp - bo2.timestamp);
                } else {
                    return (int) (bo2.price - bo1.price);
                }
            }
        });
        return sellArray;
    }

    public static ArrayList<Long> cleanAndSortSell(ArrayList<Long> buyArray) {
        buyArray = cleanup(buyArray);
        Collections.sort(buyArray, new Comparator<Long>() {
            public int compare(Long order1ID, Long order2ID) {
                Order bo1 = orderTable.get(order1ID);
                Order bo2 = orderTable.get(order2ID);
                if (bo1.type.equals("M")) {
                    if (bo2.type.equals("M")) {
                        return (int) (bo1.timestamp - bo2.timestamp);
                    }
                    return 1;
                }
                if (bo1.price == bo2.price) {
                    return (int) (bo1.timestamp - bo2.timestamp);
                } else {
                    return (int) (bo1.price - bo2.price);
                }
            }
        });
        return buyArray;
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


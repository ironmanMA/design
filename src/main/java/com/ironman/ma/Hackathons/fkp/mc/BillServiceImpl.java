package com.ironman.ma.Hackathons.fkp.mc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BillServiceImpl implements BillService {
    Map<String, Group> allGroups;
    Map<String, User> allUsers;
    Map<String, Bill> allBills;

    Map<String, Set<String>> userBillMap;

    public BillServiceImpl() {
        allGroups = new HashMap<String, Group>();
        allUsers = new HashMap<String, User>();
        allBills = new HashMap<String, Bill>();
        userBillMap = new HashMap<String, Set<String>>();
    }

    public synchronized void registerBill(Bill bill) {
        if ((bill == null)
                || (bill.getGroupTag() != null && !allGroups.containsKey(bill.getGroupTag()))
                || (bill.getParticipants() == null)
                || (bill.getParticipants().size() == 0)) {
            System.out.println("NOT LEGIT BILL");
            return;
        }
        Double total = 0d;
        for (Map.Entry<String, Double> participants : bill.getParticipants().entrySet()) {
            total += participants.getValue();
        }
        if (!total.equals(bill.getBillAmount())) {
            System.out.println("NOT LEGIT BILL");
            return;
        }
        allBills.put(bill.getBillId(), bill);

        if (bill.isIndividual()) {
            for (String userId : bill.getParticipants().keySet()) {
                registerBillForUser(userId, bill.getBillId());
            }
        } else {
            Group group = allGroups.get(bill.getGroupTag());
            group.unSettledBills.add(bill.getBillId());
            for (String userId : group.participants) {
                registerBillForUser(userId, bill.getBillId());
            }
        }
    }

    private void registerBillForUser(String userId, String billId) {
        Set<String> unSettledBills = new HashSet<String>();
        if (userBillMap.containsKey(userId)) {
            unSettledBills = userBillMap.get(userId);
        }
        unSettledBills.add(billId);
        userBillMap.put(userId, unSettledBills);
    }

    public synchronized String registerGroup(Group group) {
        allGroups.put(group.groupId, group);
        return group.groupId;
    }

    public synchronized String registerUser(String userId) {
        User user = new User(userId);
        allUsers.put(user.userId, user);
        return user.userId;
    }

    public String getUserName(String userId) {
        return allUsers.get(userId).userName;
    }

    public synchronized void registerUserToGroup(String userId, String groupId) {
        if (!allGroups.containsKey(groupId)) {
            System.out.println("UNKNOWN GROUP " + groupId);
            return;
        }
        if (!allUsers.containsKey(userId)) {
            System.out.println("UNKNOWN USER " + groupId);
            return;
        }
        Group group = allGroups.get(groupId);
        group.participants.add(userId);
        allGroups.put(groupId, group);
    }

    public Double totalToBeSettled(String userId) {
        if (userId == null
                || !allUsers.containsKey(userId)) {
            System.out.println("UNKOWN USER");
        }
        Double totalOwe = 0d;
        if (!userBillMap.containsKey(userId)) {
            System.out.println("NO BILLS USER");
            return totalOwe;
        }
        for (String billId : userBillMap.get(userId)) {
            totalOwe += totalToBeSettledForThisBill(userId, billId);
        }
        return totalOwe;
    }

    private Double totalToBeSettledForThisBill(String userId, String billId) {
        Bill bill = allBills.get(billId);
        Double totalOwe = 0d;
        if (bill.getParticipants().containsKey(userId)) {
            totalOwe = bill.getBillAmount() / bill.getParticipants().size();
            totalOwe -= bill.getParticipants().get(userId);
        }
        return totalOwe;
    }

    public Double totalToBeSettledInAGroup(String userId, String groupId) {
        if (groupId == null
                || userId == null
                || !allGroups.containsKey(groupId)
                || !allUsers.containsKey(userId)) {
            System.out.println("UNKOWN PAIRING");
        }
        Group group = allGroups.get(groupId);
        Double totalOwe = 0d;
        if (group.participants.contains(userId)) {
            for (String billId : group.unSettledBills) {
                totalOwe += totalToBeSettledForThisBill(userId, billId);
            }
        }
        return totalOwe;
    }
}

package com.ironman.ma.Hackathons.fkp.mc;

public class Solution {

    public static void main(String[] args) {
        BillServiceImpl billService = new BillServiceImpl();

        String user1 = billService.registerUser("A");
        String user2 = billService.registerUser("B");

        Bill bill = new Bill("udhaar");
        bill.addUser(user1, 10d);
        bill.addUser(user2, 30d);
        bill.setBillAmount(40d);

        billService.registerBill(bill);

        System.out.println(billService.getUserName(user1) + " " + billService.totalToBeSettled(user1));
        System.out.println(billService.getUserName(user2) + " " + billService.totalToBeSettled(user2));

        System.out.println("NEW");

        Group group = new Group("apartment-kharche");
        group.addParticipants(user1);
        group.addParticipants(user2);
        String user3 = billService.registerUser("C");
        group.addParticipants(user3);

        String groupId = billService.registerGroup(group);


        Bill bill2 = new Bill("swiggy-dinner");
        bill2.addUser(user1, 30d);
        bill2.addUser(user2, 10d);
        bill2.addUser(user3, 10d);

        bill2.setBillAmount(50d);
        bill2.setGroupTag(groupId);

        billService.registerBill(bill2);

        System.out.println(billService.getUserName(user1) + " " + billService.totalToBeSettled(user1));
        System.out.println(billService.getUserName(user2) + " " + billService.totalToBeSettled(user2));
        System.out.println(billService.getUserName(user3) + " " + billService.totalToBeSettled(user3));
        System.out.println("NEW ");

        System.out.println(billService.getUserName(user1) + " " + billService.totalToBeSettledInAGroup(user1, groupId));
        System.out.println(billService.getUserName(user2) + " " + billService.totalToBeSettledInAGroup(user2, groupId));

        String user4 = billService.registerUser("D");
        billService.registerUserToGroup(user4, groupId);
        System.out.println("user3 id =  " + user3);
        System.out.println("user4 id =  " + user4);
        System.out.println(billService.getUserName(user3) + " " + billService.totalToBeSettledInAGroup(user3, groupId));
        System.out.println(billService.getUserName(user4) + " " + billService.totalToBeSettledInAGroup(user4, groupId));

        Bill bill3 = new Bill("swiggy-dinner2");
        bill3.addUser(user4, 30d);
        bill3.addUser(user3, 10d);
        bill3.setBillAmount(40d);
        bill3.setGroupTag(groupId);

        billService.registerBill(bill3);
        System.out.println("NEW BILL");
        System.out.println(billService.getUserName(user1) + " " + billService.totalToBeSettledInAGroup(user1, groupId));
        System.out.println(billService.getUserName(user2) + " " + billService.totalToBeSettledInAGroup(user2, groupId));
        System.out.println(billService.getUserName(user3) + " " + billService.totalToBeSettledInAGroup(user3, groupId));
        System.out.println(billService.getUserName(user4) + " " + billService.totalToBeSettledInAGroup(user4, groupId));
    }

}

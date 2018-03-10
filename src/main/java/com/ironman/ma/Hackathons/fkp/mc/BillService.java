package com.ironman.ma.Hackathons.fkp.mc;

import java.util.Set;

public interface BillService {
    void registerBill(Bill bill);

    String registerGroup(Group group);

    String registerUser(String userName);

    String getUserName(String userId);

    void registerUserToGroup(String userId, String groupId);

    Double totalToBeSettled(String userId);

    Double totalToBeSettledInAGroup(String userId, String groupId);
}

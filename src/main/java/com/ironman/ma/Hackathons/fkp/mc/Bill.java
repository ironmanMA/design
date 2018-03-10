package com.ironman.ma.Hackathons.fkp.mc;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Bill {
    private String billId;
    private Map<String, Double> participants;
    private Double billAmount;
    private String groupTag;
    private String billName;
    private boolean isIndividual = true;

    public Bill(String billName) {
        this.billName = billName;
        this.billId = new Date().getTime() + UUID.randomUUID().toString();
        this.participants = new HashMap<String, Double>();
    }

    public void addUser(String userId) {
        participants.put(userId, 0d);
    }

    public void addUser(String userId, Double putForward) {
        participants.put(userId, putForward);
    }

    public void setBillAmount(Double billAmount) {
        this.billAmount = billAmount;
    }

    public void setGroupTag(String groupTag) {
        if (groupTag != null) {
            isIndividual = false;
        }
        this.groupTag = groupTag;
    }

    public Map<String, Double> getParticipants() {
        return participants;
    }

    public Double getBillAmount() {
        return billAmount;
    }

    public String getBillId() {
        return billId;
    }

    public String getGroupTag() {
        return groupTag;
    }

    public boolean isIndividual() {
        return isIndividual;
    }
}


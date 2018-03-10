package com.ironman.ma.Hackathons.fkp.mc;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Group {
    String groupId;
    Set<String> participants;
    Set<String> unSettledBills;
    Set<String> settledBills;
    String groupName;

    public Group(String groupName) {
        this.groupName = groupName;
        this.groupId = new Date().getTime() + UUID.randomUUID().toString();
        participants = new HashSet<String>();
        unSettledBills = new HashSet<String>();
    }

    public void addParticipants(String user) {
        this.participants.add(user);
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Set<String> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<String> participants) {
        this.participants = participants;
    }

    public Set<String> getUnSettledBills() {
        return unSettledBills;
    }

    public void setUnSettledBills(Set<String> unSettledBills) {
        this.unSettledBills = unSettledBills;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}

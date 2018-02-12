package com.ironman.ma.Hackathons.HealthifyMe.HME;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Solution {

    private static ArrayList<ArrayList<Slot>> allSlots;
    private static HashMap<String, Integer> weekDayMap;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
    private static int numEntries = 0;
    private static Calendar calendar = Calendar.getInstance();

    private static class Slot {
        int slotStartMinuteID;
        Date startTime;
        Date endTime;
        int slotEndMinuteID;

        public Slot(String startTime, String endTime) throws ParseException {
            startTime = "1970-1-1 " + startTime + ":00";
            endTime = "1970-1-1 " + endTime + ":00";
            this.startTime = simpleDateFormat.parse(startTime);
            this.endTime = simpleDateFormat.parse(endTime);

            this.slotStartMinuteID = this.startTime.getHours() * 60 + this.startTime.getMinutes();
            this.slotEndMinuteID = this.endTime.getHours() * 60 + this.endTime.getMinutes();
        }

        public Slot(Date startTime, Date endTime) throws ParseException {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public String toString() {
            return "\n{ start_time='" + simpleDateFormat.format(startTime)
                    + ", end_time='" + simpleDateFormat.format(endTime) + "}";
        }
    }

    public static void register(String input) throws ParseException {
        numEntries++;
        String[] tokens = input.split(",");
        int allSlotId = weekDayMap.get(tokens[0].toUpperCase());
        Slot slot = new Slot(tokens[1], tokens[2]);
        allSlots.get(allSlotId).add(slot);
    }

    private static void prePareForQueries() {
        for (int i = 0; i < allSlots.size(); i++) {
            ArrayList<Slot> weekDaySlots = allSlots.get(i);
            Collections.sort(weekDaySlots, new Comparator<Slot>() {
                public int compare(Slot o1, Slot o2) {
                    if (o1.slotStartMinuteID == o2.slotStartMinuteID) {
                        return (o1.slotEndMinuteID - o2.slotEndMinuteID);
                    } else {
                        return o1.slotStartMinuteID - o2.slotStartMinuteID;
                    }
                }
            });
            allSlots.set(i, weekDaySlots);
        }
    }

    private static void preProcess() {
        String[] weekDayList = new String[]{"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
        allSlots = new ArrayList<ArrayList<Slot>>();
        weekDayMap = new HashMap<String, Integer>();
        int iter = 0;
        for (String week : weekDayList) {
            weekDayMap.put(week, iter);
            allSlots.add(new ArrayList<Slot>());
            iter++;
        }
    }

    private static void processQuery(String input) throws ParseException {
        String[] tokens = input.split(",");
        long slotCount = Long.parseLong(tokens[0]);
        Date startTime = new Date();
        if (tokens.length > 1) {
            startTime = simpleDateFormat.parse(tokens[1] + ":00");
        }
        ArrayList<Slot> slots = getNextKSlots(startTime, slotCount);
        System.out.println(slots);
    }

    private static ArrayList<Slot> getNextKSlots(Date startTime, long slotCount) throws ParseException {
        ArrayList<Slot> results = new ArrayList<Slot>();
        if (numEntries == 0) {
            return results;
        }

        calendar.clear();
        calendar.setTime(startTime);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        int insertionIndex = searchInsertionPoint(startTime, allSlots.get(dayOfWeek));

        //process currDate separately
        while (slotCount > 0 && insertionIndex < allSlots.get(dayOfWeek).size()) {
            Slot next = allSlots.get(dayOfWeek).get(insertionIndex);
            results.add(constructNextSlot(startTime, next));
            slotCount--;
            insertionIndex++;
        }

        Date prevDay = startTime;
        while (slotCount > 0) {
            calendar.clear();
            calendar.setTime(prevDay);
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            startTime = calendar.getTime();
            dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            ArrayList<Slot> nextSlots = allSlots.get(dayOfWeek);
            int iter = 0;
            while (slotCount > 0 && iter < nextSlots.size()) {
                Slot next = nextSlots.get(iter);
                results.add(constructNextSlot(startTime, next));
                slotCount--;
                iter++;
            }
            prevDay = startTime;
        }
        return results;
    }

    private static Slot constructNextSlot(Date currDate, Slot nextSlot) throws ParseException {
        Date newStartTime = currDate;
        calendar.clear();
        calendar.setTime(newStartTime);
        calendar.set(Calendar.HOUR_OF_DAY, nextSlot.startTime.getHours());
        calendar.set(Calendar.MINUTE, nextSlot.startTime.getMinutes());
        newStartTime = calendar.getTime();

        Date newEndTime = currDate;
        calendar.clear();
        calendar.setTime(newEndTime);
        calendar.set(Calendar.HOUR_OF_DAY, nextSlot.endTime.getHours());
        calendar.set(Calendar.MINUTE, nextSlot.endTime.getMinutes());
        newEndTime = calendar.getTime();

        return new Slot(newStartTime, newEndTime);

    }

    private static int searchInsertionPoint(Date startTime, ArrayList<Slot> dates) {
        int res = 0;
        if (dates.size() == 0) {
            return 1;
        }
        int slotId = startTime.getHours() * 60 + startTime.getMinutes();
        int low = 0;
        int high = dates.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midSlotId = dates.get(mid).slotStartMinuteID;
            int nextTomidSlotId = Integer.MAX_VALUE;
            if (mid + 1 < dates.size()) {
                nextTomidSlotId = dates.get(mid).slotStartMinuteID;
            }

            if (midSlotId <= slotId && slotId < nextTomidSlotId) {
                res = mid + 1;
                break;
            } else if (slotId < midSlotId) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }

    public static void main(String[] args) throws ParseException {
        preProcess();
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();
        for (int i = 0; i < cases; i++) {
            String slot = input.next();
            register(slot);
        }
        prePareForQueries();
        int tests = input.nextInt();
        for (int i = 0; i < tests; i++) {
            String test = input.nextLine();
            System.out.print(test);
            if (!test.isEmpty())
                processQuery(test);
            else {
                i--;
            }
        }
    }


}

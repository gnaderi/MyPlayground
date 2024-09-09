package books;// A Java program for merging overlapping intervals

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class IntervalIntersection {
    // Driver code
    public static void main(String[] args) {

        List<Interval> intervals = new ArrayList<>();

        intervals.add(new Interval(1, 2));
        intervals.add(new Interval(3, 5));
        intervals.add(new Interval(6, 9));
        intervals.add(new Interval(8, 10));
        intervals.add(new Interval(4, 7));
        intervals.add(new Interval(6, 7));
        intervals.add(new Interval(6, 11));
        mergeIntervals(intervals);
    }

    static void findIntersection(List<Interval> intervals) {
        intervals.sort(Comparator.comparingInt(o -> o.start));
        Interval intersection = new Interval(intervals.get(0).start, intervals.get(0).end);
        int busyTime = -1;
        for (int i = 1; i < intervals.size(); i++) {

            intersection.start = Math.max(intersection.start, intervals.get(i).start);
            intersection.end = Math.min(intersection.end, intervals.get(i).end);
        }
        System.out.println("[" + intersection.end + ", " + intersection.start + "]");
    }

    static void findMaxGuests(List<Interval> intervals) {
        // Sort arrival and exit arrays
        intervals.sort(Comparator.comparingInt(o -> o.start));
        List<Integer> exitTS = intervals.stream().map(i -> i.end).sorted().toList();

        // partySize indicates number of guests at a time
        int partySize = 1;
        int maxPartySize = 1;
        Interval busyInterval = intervals.get(0);
        int i = 1, j = 0;
        while (i < intervals.size() && j < exitTS.size()) {
            if ((intervals.get(i).start) < exitTS.get(j)) {
                partySize++;
                if (partySize > maxPartySize) {
                    maxPartySize = partySize;
                    busyInterval = intervals.get(i);
                }
                i++;
            } else {
                j++;
                partySize--;
            }


        }
        System.out.println("Maximum Number of Guests = " + maxPartySize + " at time " + busyInterval.start);
    }

    public static List<Interval> mergeIntervals(List<Interval> intervals) {
        intervals.sort(Comparator.comparingInt(o -> o.start));

        int index = 0;
        for (int i = 1; i < intervals.size(); i++) {
            // If this is not first Interval and overlaps
            // with the previous one
            if (intervals.get(i).start <= intervals.get(index).end) {
                // Merge previous and current Intervals
                intervals.get(index).end = Math.max(intervals.get(index).end, intervals.get(i).end);
            } else {
                index++;
                intervals.get(index).start = intervals.get(i).start;
                intervals.get(index).end = intervals.get(i).end;
            }
        }
        System.out.println("Number of " + (intervals.size() - index) + " merged!");
        System.out.println("Final result as bellow:");
        int[][] result = new int[index + 1][2];
        for (int i = 0; i <= index; i++) {
            System.out.println("[" + intervals.get(i).start + ", " + intervals.get(i).end + "]");
        }
        return intervals.subList(0, index + 1);
    }
}


class Interval {
    int start, end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

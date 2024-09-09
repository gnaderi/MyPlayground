package books;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MinNumberChairs {
    public static void main(String[] args) {
        int[] starts = {1, 2, 6, 5, 3};
        int[] ends = {5, 5, 7, 6, 8};
        List<Interval> list = new ArrayList<>();
        for (int i = 0; i < starts.length; i++) {
            list.add(new Interval(starts[i], ends[i]));
        }
        findMinChairs(list);
    }

    static void findMinChairs(List<Interval> intervals) {
        intervals.sort(Comparator.comparingInt(o -> o.start));
        int partySize = 1;
        int maxPartySize = 1;
        Interval busyInterval = intervals.get(0);
        int i = 1, j = 0;
        while (i < intervals.size() && j < intervals.size()) {
            if ((intervals.get(i).start) < intervals.get(j).end) {
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

    static class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}

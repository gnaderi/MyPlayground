package anotherOne;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Problem Statement:
 * You are given a list of conference talks with their start and end times. Each talk is represented as (start_time, end_time), where both start_time
 * and end_time are integers. Your task is to schedule the talks in such a way that no two talks overlap, maximizing the number of talks attended.
 * Write a function/method that takes a list of talks and returns a schedule as a list of tuples, where each tuple represents a scheduled talk.
 * Example:
 * # Input
 * talks = [(1, 3), (2, 4), (3, 5), (5, 6), (7, 8), (8, 10)]
 * # Output
 * schedule = [(1, 3), (3, 5), (5, 6), (7, 8), (8, 10)]
 * Constraints:
 * The input list of talks is not sorted.
 * You can assume that the input talks are valid (i.e., start_time <= end_time).
 * Talks may have the same start or end time, but they should not overlap in the schedule.
 * You need to maximize the number of talks attended
 */
public class MaximNumberOfTalks {
    public static void main(String[] args) {
        List<Sesion> sessions = new ArrayList<>();
        sessions.add(new Sesion(2, 4));
        sessions.add(new Sesion(1, 3));
        sessions.add(new Sesion(3, 5));
        sessions.add(new Sesion(7, 8));
        sessions.add(new Sesion(5, 6));
        sessions.add(new Sesion(8, 10));
        List<Sesion> maxSesions = new MaximNumberOfTalks().maxTalk(sessions);
        for (Sesion sesion : maxSesions) {
            System.out.print("(" + sesion.start + "," + sesion.end + ")");
        }
    }

    public List<Sesion> maxTalk(List<Sesion> sesions) {
        sesions = sesions.stream().sorted(Comparator.comparingInt(o -> o.start)).collect(toList());
        List<Sesion> maxSesions = new ArrayList<>();
        Sesion last = sesions.get(0);
        maxSesions.add(last);
        for (int i = 1; i < sesions.size(); i++) {
            if (sesions.get(i).start < last.end) {
                continue;
            }
            last = sesions.get(i);
            maxSesions.add(last);
        }
        return maxSesions;
    }

    static class Sesion {
        public int start;
        public int end;

        public Sesion(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}


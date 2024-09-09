package servicnow;


import java.time.LocalDateTime;

/*
Input: time of day
Output: angle between the hands of the clock
 */
public class ClockDegree {
    public static void main(String[] args) {
        System.out.println("Angle between the hands of the clock= "
                + angleBetweenHandsClock(LocalDateTime.now().getHour(), LocalDateTime.now().getMinute()));
        System.out.println("angleBetweenHandsClockSimple() = " + angleBetweenHandsClockSimple(LocalDateTime.now().getHour(), LocalDateTime.now().getMinute()));

        for (int hour = 1; hour < 24; hour++) {
            for (int minute = 1; minute < 60; minute++) {
                if (angleBetweenHandsClockSimple(hour, minute) != angleBetweenHandsClock(hour, minute)) {
                    System.out.println(hour + ":" + minute);
                    System.out.println("angleBetweenHandsClock() = " + angleBetweenHandsClockSimple(hour, minute));
                    System.out.println("angleBetweenHandsClockSimple() = " + angleBetweenHandsClockSimple(hour, minute));

                }
            }
        }


    }

    public static int angleBetweenHandsClockSimple(int hour, int minutes) {
        final int angle = Math.abs(hour % 12) * 30 - (int) (5.5 * minutes);
        return Math.min(360 - angle, Math.abs(angle));
    }

    public static int angleBetweenHandsClock(int hour, int minutes) {
        final int hd = (hour % 12) * 30 + (int) ((minutes / 60.0) * 30);
        final int md = minutes * 6;
        final int angle = Math.abs(md - hd);
        return Math.min(360 - angle, angle);
    }
}

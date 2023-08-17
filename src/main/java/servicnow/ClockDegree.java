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
    }

    public static int angleBetweenHandsClock(int hour, int minutes) {
        final int hd = hour * 30 + (int) ((minutes / 60.0) * 30);
        final int md = minutes * 6;
        final int angle = Math.abs(md - hd);
        return (angle > 180) ? 360 - angle : angle;
    }
}


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MayanStatuesTest {
    @Test
    public void testSample() {
        Point2D[] statuesLocations = new Point2D[5];
        for (int i = 0; i < statuesLocations.length; i++) {
            statuesLocations[i] = new Point2D();
        }
        statuesLocations[0].x = -1;
        statuesLocations[0].y = -2;
        statuesLocations[1].x = 1;
        statuesLocations[1].y = 2;
        statuesLocations[2].x = 2;
        statuesLocations[2].y = 4;
        statuesLocations[3].x = -3;
        statuesLocations[3].y = 2;
        statuesLocations[4].x = 2;
        statuesLocations[4].y = -2;
        MayanStatues mayanStatues = new MayanStatues();
        assertEquals(4, mayanStatues.minimalNumberOfRays(statuesLocations));
    }
}
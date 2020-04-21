import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 Bob the Adventurer is one step away from solving the mystery of an ancient Mayan tomb. He just approched the secret chamber where the secret Mayan scriptures are locked in a chest.

 There are N ancient statues in the room. After long thought, Bob figured out that in order to open the treasure chest he needs to stand in the middle of the room and hit every statue with a laser ray at the same time.

 Bob is a highly experienced adventurer, so setting multiple laser rays at the same time is not a problem for him. Moreover, every ray that he creates is perfectly straight and never changes direction at all.

 The middle of the room, where Bob is standing, has coordinates (0, 0). Every statue is located at some point with coordinates (x, y). Each statue is made of pure glass, so that if any ray hits it, it does not stop, but goes through the statue and continues beyond in the same, unchanged direction.

 Bob wonders how he can hit every ancient statue in the room using the fewest rays possible.

 Assume that the following declarations are given:

class Point2D { public int x; public int y; }

Write a function

 class Solution { public int solution(Point2D[] A); }

 that, given an array of points A, representing the locations of the statues, returns the minimal number of rays that Bob must set in order to hit every statue in the room.

 For example, given an array A

 A[0].x = -1 A[0].y = -2 (statue 0) A[1].x = 1 A[1].y = 2 (statue 1) A[2].x = 2 A[2].y = 4 (statue 2) A[3].x = -3 A[3].y = 2 (statue 3) A[4].x = 2 A[4].y = -2 (statue 4) your function should return 4.

 As is shown in the image, it is possible to create four rays in such a way that:
 
 https://i.stack.imgur.com/ad5gc.png

 the first will hit statue 0;

 the second will hit statues 1 and 2;

 the third will hit statue 3;

 the fourth will hit statue 4.



 Assume that:

 N is an integer within the range [1..100,000];

 the coordinates of each point in array A are integers within the range [−1,000,000,000..1,000,000,000];

 the elements of A are all distinct;

 Array A does not contain point (0,0).



 Complexity:

 expected worst-case time complexity is O(N);

 expected worst-case space complexity is O(N*log(N)), beyond input storage (not counting the storage required for input arguments).



 */
public class MayanStatues {
    enum Area {
        A1("A1"),
        A2("A2"),
        A3("A3"),
        A4("A4");
        String area;

        Area(String area) {
            this.area = area;
        }

        @Override
        public String toString() {
            return area;
        }
    }

    public int minimalNumberOfRays(Point2D[] statuesLocations) {
        Map<Area, Set<Double>> roomSection = new HashMap<>();
        roomSection.put(Area.A1, new HashSet<>());
        roomSection.put(Area.A2, new HashSet<>());
        roomSection.put(Area.A3, new HashSet<>());
        roomSection.put(Area.A4, new HashSet<>());

        for (int i = 0; i < statuesLocations.length; i++) {
            Area areaKey = getPointSection(statuesLocations[i]);
            Set<Double> sectionRays = roomSection.get(areaKey);
            sectionRays.add(statuesLocations[i].y * 1.0 / statuesLocations[i].x);
            roomSection.put(areaKey, sectionRays);
        }
        int raysNeeded = 0;
        for (Area section : Area.values()) {
            raysNeeded += roomSection.get(section).size();
        }
        return raysNeeded;
    }

    private Area getPointSection(Point2D statuesLocation) {
        if (statuesLocation.x >= 0) {
            if (statuesLocation.y >= 0)
                return Area.A1;
            else
                return Area.A4;
        } else {
            if (statuesLocation.y < 0)
                return Area.A3;
            else
                return Area.A2;
        }
    }
}

class Point2D {
    public int x;
    public int y;
}
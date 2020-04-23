import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class OneMonthChallengeTest {

    OneMonthChallenge oneMonthChallenge = new OneMonthChallenge();

    @Test
    void singleNumber() {
        int singleNumber = oneMonthChallenge.singleNumber(new int[]{1, 9, 2, 8, 3, 7, 4, 6, 5, 6, 4, 7, 3, 9, 1, 2, 8});
        assertEquals(5, singleNumber);
    }

    @Test
    void isHappy() {
        assertTrue(oneMonthChallenge.isHappy(19));
        assertTrue(oneMonthChallenge.isHappy(7));
        assertFalse(oneMonthChallenge.isHappy(2));
        assertFalse(oneMonthChallenge.isHappy(4));
    }

    @Test
    void maxSubArray() {
        assertEquals(6, oneMonthChallenge.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    @Test
    void moveZeroes() {
        assertEquals(Arrays.toString(new int[]{1, 3, 12, 0, 0}), Arrays.toString(oneMonthChallenge.moveZeroes(new int[]{0, 1, 0, 3, 12})));

    }

    @Test
    void maxProfit() {
        assertEquals(7, oneMonthChallenge.maxProfit(new int[]{7,1,5,3,6,4}));
        assertEquals(4, oneMonthChallenge.maxProfit(new int[]{1, 2, 3, 4, 5}));
        assertEquals(0, oneMonthChallenge.maxProfit(new int[]{7, 6, 4, 3, 1}));
    }

    @Test
    void groupAnagrams() {

        assertEquals("[[eat, tea, ate], [bat], [tan, nat]]" ,
                oneMonthChallenge.groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"}).toString());

    }
}
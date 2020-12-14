
package test;
import static org.junit.jupiter.api.Assertions.*;

import main.Solution;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collection;

public class SolutionTest {

    String testInput;
    Solution s;
    String finalOutput;
    String expectedOutput;

    @Before
    public void setData(){
        testInput =  "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        s = new Solution(testInput);
        finalOutput = s.getSolution();
        expectedOutput = "1, 3, 6-8, 12-15, 21-24, 31";

    }

    @Test
    public void testCollect() {
        //Method should take in a string of comma seperated numbers and convert them to a collection of Integers

        //Actual method uses streams and Lambda expressions to collect the data
        Collection<Integer> actual = s.collect(testInput);
        //Expected manually instantiates the collection using Arrays.asList
        Collection<Integer> expected = Arrays.asList(1,3,6,7,8,12,13,14,15,21,22,23,24,31);


        //Tests equality
        assertEquals(expected, actual , "Should return same list of integers");

        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));
    }

    @Test
    public void testCollectEdgeCases(){
        //Method should handle edge cases - Empty Input, Single number,
        //Method should take in a string of comma seperated numbers and convert them to a collection of Integers

        Collection<Integer> actual = s.collect("1,2");
        Collection<Integer> expected = Arrays.asList(1,2);

        //Tests equality
        assertEquals(expected, actual , "Should return same list of integers");

        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));

        actual = s.collect("1");
        expected = Arrays.asList(1);

        //Tests equality
        assertEquals(expected, actual , "Should return same list of integers");

        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));
    }

    @Test
    public void testSummarizeCollection() {

        //Test that both aren't null
        assertNotNull(finalOutput);
        assertNotNull(expectedOutput);

        //Test main logic
        assertEquals(expectedOutput, finalOutput);
    }

    @Test
    public void testSummarizeCollectionEdgeCases() {

        String actual = s.summarizeCollection(s.collect("1"));
        String expected = "1";

        //Tests equality
        assertEquals(expected, actual , "Should return same integer");

        //Edge Case - Two sequential numbers provided
        actual = s.summarizeCollection(s.collect("1,2"));
        expected = "1-2";

        //Tests equality
        assertEquals(expected, actual);

        //Edge Case - Two non-sequential numbers provided
        actual = s.summarizeCollection(s.collect("1,4"));
        expected = "1, 4";

        //Tests equality
        assertEquals(expected, actual );

        //Edge Case - No numbers / Empty String Provided
        actual = s.summarizeCollection(s.collect(""));
        expected = "There is no input";

        //Tests equality
        assertEquals(expected, actual , "Should return empty string");

    }


}
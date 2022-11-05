package activities;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class Act1 {

   @Test
    void iterablesEqual() {
        final List<String> list1 = Arrays.asList("orange", "mango", "banana","zad");
        final List<String> expected = Arrays.asList("banana", "mango", "orange");

        // Add a new value
        //list1.add("apple");
        // Sort array
        Collections.sort(list1);

        // Assertion
        assertIterableEquals(expected, list1,"expected should match actual"); //Fail
    }
}

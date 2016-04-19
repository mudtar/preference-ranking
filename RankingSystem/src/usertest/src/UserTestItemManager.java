import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A model class to handle the basic data elements of the user test.
 *
 * @author  Ian Burton
 * @version 2016.04.18.1
 */
public class UserTestModel {
    /**
     * A dummy list of items to present to the user. This will not be
     * part of the final program, but is used here until code is written
     * to pull the test items from the database.
     */
    private List<String> testItems = new ArrayList<>(
        Arrays.asList("a", "b", "c", "d"));

    /**
     * Returns a shuffled list of all unique pair of testItems. Look
     * into: will this work with an odd number of testItems?
     * 
     * @return a shuffled list of all unique pairs of testItems
     */
    public List<List<String>> getTestItemPairs() {
        List<List<String>> testItemPairs = new ArrayList<>();

        // The List index of the most recent item whose unique
        // permutations have all been generated and stored. We haven't
        // handled any yet, so start at -1.
        int handledUpToIndex = -1;

        for (String testItem1 : testItems) {
            // The List index of the current item being paired.
            int innerLoopIndex = 0;

            for (String testItem2 : testItems) {
                // Make sure that the item being paired is not one
                // that's already been fully handled. Also make sure
                // that we aren't making a pair of duplicates.
                if ((innerLoopIndex > handledUpToIndex) &&
                    (testItem1 != testItem2)) {

                    // Add a List of two unique test items to the List
                    // of pairs. Randomize the order of the pair.
                    List<String> pair = Arrays.asList(testItem1, testItem2);
                    Collections.shuffle(pair);
                    testItemPairs.add(pair);
                }

                // Increment to represent the next item to be paired.
                innerLoopIndex++;
            }

            // All possible unique permutations for the most recently
            // handled item have been generated and stored. Increment
            // this so that we don't deal with this item anymore, so as
            // not to generate duplicate pairs.
            handledUpToIndex++;
        }

        // Randomize the order of the pairs.
        Collections.shuffle(testItemPairs);

        return testItemPairs;
    }
}

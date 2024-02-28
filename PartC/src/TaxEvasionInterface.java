// ATHANASIOS PANAGIOTIDIS
// p3220143

import java.util.ArrayList;
import java.util.List;

/**
 * Defines the methods for a binary search tree that handles items type LargeDepositor
 */
public interface TaxEvasionInterface {
    /**
     * inserts an item type LargeDepositor in the binary seach tree
     */
    void insert(LargeDepositor item);

    /**
     * reads a text file, with the name filename, and updates the tree using insert method
     */
    void load(String filename);

    /**
     * updates the savings variable of the item with the given AFM
     */
    void updateSavings(int AFM, double savings);

    /**
     * searches the tree for an item with the given AFM
     * if the search is successful @return LargeDepositor type item with that AFM
     */
    LargeDepositor searchByAFM(int AFM);

    /**
     * searches the tree for items with the given last name
     * if the search is successful @return a list of the items with that last name
     * if the list size is less than or equal to 5 the method is printing the items in the list
     */
    List searchByLastName(String last_name);

    /**
     * removes the item with the given AFM from the tree
     */
    void remove(int AFM);

    /**
     * calculates and returns the average amount of deposits, based on the depositors stored in the tree
     */
    double getMeanSavings();

    /**
     * prints the k depositors that is more likely that they have commited a tax fraud
     */
    void printTopLargeDepositors(int k);

    /**
     * Prints the elements of all nodes in the tree, sorted by ascending order according to the AFM
     */
    void printByAFM();
}

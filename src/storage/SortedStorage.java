/*
 * SortedStorage.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * This program implements a storage functionality which can store
 * objects of type String and null objects. The storage functionality behaves
 * like a set
 *
 * @author      Deepika Kini
 * @author      Manasa Deshpande
 */

package storage;

public class SortedStorage {
    // storage object
    private String storage="";
    // indicates whether a null value has been added
    private boolean hasNull;
    // indicates number of null values
    private int nullCount;

    /**
     * This constructor initializes the nullCount to 0 and hasNull to false
     */
    public SortedStorage(){
        this.hasNull = false;
        this.nullCount = 0;
    }

    /**
     * Splits the storage with the delimiter "END"
     *
     * @return the the resulting string array after splitting
     */
    private String[] splitStorage(){
        return storage.split("END");
    }

    /**
     * Converts the storage object to a string containing the added strings
     * and count of null values
     *
     * @return the string implementation of the object
     */
    public String toString(){
        String[] storageArray = splitStorage();
        String returnString;
        returnString = "Values stored are: ";
        // Iterates over the contents of the storage and prints them
        for (int index = 0; index < storageArray.length; index++){
            returnString += storageArray[index];
            returnString +=  " ";
        }
        returnString += "\nNumber of null values: " + nullCount;
        return returnString;
    }

    /**
     * Toggles the boolean null indicator value to true and increments the null
     * value counter
     */
    private boolean addNull(){
        hasNull = true;
        nullCount++;
        return true;
    }

    /**
     * This methods checks and returns false if the string parameter is already
     * present in the storage, otherwise it compares the input string with
     * existing elements and places the new string in ascending order, returns
     * true
     *
     * @param obj the string object to be added
     * @return true if string has been added, false otherwise
     */
    protected boolean add(Object obj){
        String[] storageArray = splitStorage();
        int index= 0;
        boolean inserted;
        // if object is null, incrementing respective counter, not editing
        // storage
        if (obj == null) {
            return addNull();
        }
        // if obj is not null, then it's string implementation is added to storage
        String x = obj.toString();
        // Checking if string to be inserted is already present
        inserted = find(x);
        if(inserted) {
            return false;
        }
        // if string is not added to storage yet and the entire storage array
        // has not been traversed during comparison yet
        while (!inserted && (index < storageArray.length)){
            if (x.compareTo(storageArray[index]) < 0){
                // if string to be added precedes the string at index then
                // replacing it with new string concatenated
                String toReplace = x + "END" + storageArray[index];
                storage = storage.replace(storageArray[index], toReplace);
                inserted = true;
            }
            index++;
        }
        // if new string did not precede any existing strings, add new string
        // at the end
        if(!inserted){
            if(storageArray.length == 1){
                storage = "END" + x + "END";
            }
            else{
                storage = storage.concat(x + "END");
            }
        }
        return true;
    }

    /**
     * Retrieves the index of string parameter if it is present in the storage
     *
     * @param x the string to be indexed
     * @return the index of the string, -1 otherwise
     */
    private int getIndex(String x){
        int found = storage.indexOf(x);
        return found;
    }

    /**
     * Searches for the string parameter x in the storage
     *
     * @param obj the object parameter to be searched
     * @return true if string was found, false otherwise
     */
    protected boolean find(Object obj){
        if(obj == null){
            return hasNull;
        }
        String x = obj.toString();
        // string x is found if it's index is more than 0 in storage
        // Adding a 'D' to eliminate substring results
        boolean result = getIndex("D" + x + "E") >= 0;
        return result;
    }

    /**
     * This method checks if null values are present in storage
     *
     * @return true if null is present, false otherwise
     */
    protected boolean includesNull(){
        return hasNull;
    }

    /**
     * deletes null values and decrements null count if null values are present
     * and returns true, returns false if null is not present
     *
     * @return true if null is deleted, false otherwise
     */
    private boolean deleteNull(){
        if(nullCount == 0) {
            return false;
        }
        else {
            nullCount--;
            if(nullCount == 0)
                hasNull = false;
            return true;
        }
    }

    /**
     * This method deletes the string parameter x from the storage if it is
     * present
     *
     * @param obj the object to be deleted
     * @return true if element was present and deleted, false otherwise
     */
    protected boolean delete(Object obj){
        // if object is null, decrementing respective counter, not editing
        // storage
        if (obj == null) {
            return deleteNull();
        }
        String x = obj.toString();
        if(find(x)) {
            // if string x is present in storage
            String deleteString = "END" + x + "END";
            storage = storage.replace(deleteString, "END");
            return true;
        }
        else {
            // string not found in storage
            return false;
        }
    }
}

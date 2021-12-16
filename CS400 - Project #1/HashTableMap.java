import java.util.NoSuchElementException;
import java.util.LinkedList;

// --== CS400 File Header Information ==--
// Name: Nijae King
// Email: nrking@wisc.edu
// Team: Blue Team
// Group: AB
// TA: Mu
// Lecturer: Florian Heimerl
// Notes to Grader: n/a

public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
  private LinkedList<ValueType>[] hashTable;
  private LinkedList<KeyType>[] keyTable;
  private double loadFactorThreshold = 0.85;
  
  /**
   * One parameter constructor, sets array capacity to parameter capacity
   * @param capacity size of hash table
   */
  public HashTableMap(int capacity) {
    this.hashTable = (LinkedList<ValueType>[]) new LinkedList[capacity];
    this.keyTable = (LinkedList<KeyType>[]) new LinkedList[capacity];
  }
  
  /**
   * Default constructor, sets array capacity to ten
   */
  public HashTableMap() {
    int defaultCapacity = 10;
    this.hashTable = (LinkedList<ValueType>[]) new LinkedList[defaultCapacity];
    this.keyTable = (LinkedList<KeyType>[]) new LinkedList[defaultCapacity];
  }

  /**
   * Verifies key is not already stored within hash table and places it at the generated index
   * @return true if successfully added, false otherwise
   */
  @Override
  public boolean put(KeyType key, ValueType value) {
    // Verify valid key
    if(key == null)
      return false;
    
    // Generate hash index
    int hashIndex = (Math.abs(key.hashCode()))%hashTable.length;
    
    // Verify key doesn't exist already
    if(keyTable[hashIndex] != null && keyTable[hashIndex].contains(key))
      return false;
    
    // Add elements
    if(keyTable[hashIndex] == null && hashTable[hashIndex] == null) {
      keyTable[hashIndex] = new LinkedList<KeyType>();
      hashTable[hashIndex] = new LinkedList<ValueType>();
    }
    
    hashTable[hashIndex].add(value);
    keyTable[hashIndex].add(key);
    
    // Check if load factor threshold is reached
    if(rehashRequired())
      expandAndRehashTable();
    
    return true;
  }

  /**
   * Verifies and finds the element stored with the given key
   * @param key to search for
   * @throws NoSuchElementException if the key is not stored within the hashtable
   * @returns ValueType value stored with given key
   */
  @Override
  public ValueType get(KeyType key) throws NoSuchElementException {
    // Generate hash index
    int hashIndex = (Math.abs(key.hashCode()))%hashTable.length;
    
    // Verify key is in table
    if(!containsKey(key))
      throw new NoSuchElementException("Key doesn't exist");
    
    // Find element & return
    return hashTable[hashIndex].get(keyTable[hashIndex].indexOf(key));
  }

  /**
   * Iterates through the hash table counting total number of indexs that aren't null
   * @return Returns number of non-nulls
   */
  @Override
  public int size() {
    int size = 0;
    
    for(int i = 0; i < hashTable.length; i++) {
      if(hashTable[i] != null) {
        size += hashTable[i].size();
      }
    }

    return size;
  }

  /**
   * Checks to see if the key is contained within the hash table
   * @return true if it is contained, false otherwise
   */
  @Override
  public boolean containsKey(KeyType key) {
    // Generate hash index
    int hashIndex = (Math.abs(key.hashCode()))%hashTable.length;
    
    // Checks at the generated hash index for the key
    if(keyTable[hashIndex] != null && keyTable[hashIndex].contains(key)) 
      return true; // Returns true if found
    
    return false;
  }

  /**
   * Removes and key and value from then hash index and returns value removed
   * @return value removed from hash table
   */
  @Override
  public ValueType remove(KeyType key) {
    // Generate hash index
    int hashIndex = (Math.abs(key.hashCode()))%hashTable.length;
   
    // Search for key and removes key/value pair if found
    if(keyTable[hashIndex].contains(key)) {
      int listIndexKey = keyTable[hashIndex].indexOf(key);
      keyTable[hashIndex].remove(listIndexKey);
      hashTable[hashIndex].remove(listIndexKey);
    }
    
    // If key not found return null
    return null;
  }

  /**
   * Sets each index in the hash table to null
   */
  @Override
  public void clear() {
    for(int i = 0; i < hashTable.length; i++) {
      hashTable[i] = null;
      keyTable[i] = null;
    }
  }
  
  // --------------- //
  // Private Helpers //
  // --------------- //
  
  public boolean rehashRequired() {
    // Equation for checking if current load factor exceeds threshold
    return ((double)size() / (double)hashTable.length) >= loadFactorThreshold;
  }
  
  public void expandAndRehashTable() {
    // Create an array of double the size
    LinkedList<ValueType>[] newHashTable = 
        (LinkedList<ValueType>[]) new LinkedList[hashTable.length*2];
    LinkedList<KeyType>[] newKeyTable =
        (LinkedList<KeyType>[]) new LinkedList[keyTable.length*2];
    
    // Iterate through arrays
    for(int i = 0; i < hashTable.length; i++) {
      // Iterate through the chain
      if(keyTable[i] == null)
        continue;
      for(int linkedindx = 0; linkedindx < keyTable[i].size(); linkedindx++) {
        // Key & value pair
        KeyType currKey = keyTable[i].get(linkedindx);
        ValueType currVal = hashTable[i].get(linkedindx);
        
        // Generate hash index
        int hashIndex = (Math.abs(currKey.hashCode()))%newHashTable.length;
        
        //Store new elements
        if(newKeyTable[hashIndex] == null && newHashTable[hashIndex] == null) {
          newKeyTable[hashIndex] = new LinkedList<KeyType>();
          newHashTable[hashIndex] = new LinkedList<ValueType>();
        }
        
        newKeyTable[hashIndex].add(currKey);
        newHashTable[hashIndex].add(currVal);
      }
    }
    
    // Replace old arrays w/ new
    hashTable = newHashTable;
    keyTable = newKeyTable;
  }
}

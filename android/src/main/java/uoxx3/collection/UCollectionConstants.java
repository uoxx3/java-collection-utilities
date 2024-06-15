package uoxx3.collection;

/**
 * Interface containing constants related to collections.
 */
public interface UCollectionConstants {
	
	/**
	 * The load factor for collections using hashing.
	 */
	float COLLECTION_HASH_FACTOR = 0.75f;
	
	/**
	 * The threshold size for a collection to be considered of significant size.
	 */
	int COLLECTION_SIZE_THRESHOLD = 10;
	
	/**
	 * The size of an empty collection.
	 */
	int EMPTY_SIZE = 0;
	
	/**
	 * Constant indicating an index not found in a collection.
	 */
	int INDEX_NOT_FOUND = -1;
	
}

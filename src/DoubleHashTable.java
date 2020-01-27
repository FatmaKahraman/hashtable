
public class DoubleHashTable<Key, Value> extends HashMap<Key, Value> {
	private static final int PRIME = 11;

	@Override
	protected void createTable() {
		table = new HashEntry[tableSize];
		for (int i = 0; i < tableSize; i++)
			table[i] = null;
	}


	@Override
	protected int increment(Key key) {    //double hashingde increment
		return (PRIME - ((int) key % PRIME));
	}

	@Override
	public void bucketPut(Key key, Value value, String folderName) {   
		int temp = findHashEntry(hashValue(key), key, value);

		if (temp >= 0) {
			table[temp].put(folderName);

		} else {
			table[-(temp + 1)] = new HashEntry<Key, Value>(key, value);
			table[-(temp + 1)].put(folderName);
			n++;

		}

	}


}

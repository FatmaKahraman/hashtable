
import java.util.ArrayList;

public abstract class HashMap<Key, Value> implements Map<Key, Value> {
	protected int tableSize;
	protected int n;
	protected long collutionCount;
	protected final HashEntry<Key, Value> DEFUNCT = new HashEntry<Key, Value>(null, null);

	HashEntry<Key, Value>[] table;

	public HashMap() {
		n = 0;
		collutionCount = 0;
		tableSize = 128;
		createTable();
	}

	public long getCollutionCount() {
		return collutionCount;
	}

	public int hashValue(Key key) { // Tablodaki konumunu buluyor.
		return (int) key % tableSize; // Creating a hash function that performs mod by table size
	}

	protected int findHashEntry(int hash, Key key, Value value) { // key could be added at index.
		int temp = hash;
		int avail = -1;

		do {
			if (table[temp] == null || table[temp] == DEFUNCT) {
				if (avail == -1) {
					avail = temp;
				}
				if (table[temp] == null)
					break;
			} else if (table[temp].getValue().toString().equals(value.toString())) {
				return temp;
			}
			temp = (temp + increment(key)) % tableSize;
			collutionCount++;
		} while (temp != hash);

		return -(avail + 1); // search has failed
	}

	protected abstract int increment(Key key);

	@Override
	public void put(Key key, Value value, String folderName) {

		bucketPut(key, value, folderName);

		if (n > (tableSize / 10) * 8) // keep load factor <= 0.5
			resize(2 * tableSize - 1); // (or find a nearby prime)
	}

	@Override
	public int size() {
		return n;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	public void resize(int newCap) { // resize yapýlýp hashtable'a deðerlerin yeniden eklenmesi
		ArrayList<HashEntry<Key, Value>> buffer = new ArrayList<>();
		for (int i = 0; i < table.length; i++) {
			buffer.add(table[i]);
		}
		tableSize = newCap;
		createTable();
		n = 0;
		for (int i = 0; i < buffer.size(); i++) {
			if (buffer.get(i) != null) {
				int newLocation = findHashEntry(hashValue(buffer.get(i).getKey()), buffer.get(i).getKey(),
						buffer.get(i).getValue());
				table[-(newLocation + 1)] = buffer.get(i);
			}
		}
	}

	public void get(Key key, Value value) {
		int temp = findHashEntry(hashValue(key), key, value);
		try {
			table[temp].get();

		} catch (Exception e) {
			System.out.println("there is not " + value + "in the hasahtable");
		}
	}

	public void remove(Key key, Value value) {
		int temp = findHashEntry(hashValue(key), key, value);
		if (temp < 0) {
			System.out.println("There is not " + value + " in the hashtable");
		}
		table[temp] = DEFUNCT;
		n--;
	}

	protected abstract void createTable();

	protected abstract void bucketPut(Key key, Value value, String folderName);

}

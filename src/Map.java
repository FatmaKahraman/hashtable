
public interface Map<Key, Value> {
	int size();

	boolean isEmpty();

	void put(Key key,Value value,String folderName);

	void get(Key key,Value value);

	void remove(Key key, Value value);

	void resize(int tableSize);

}

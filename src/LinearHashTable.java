
public class LinearHashTable<Key, Value> extends HashMap<Key, Value> {

	@Override
	protected void createTable() {
		table = new HashEntry[tableSize];
		for (int i = 0; i < tableSize; i++)
			table[i] = null;
	}

	@Override
	protected int increment(Key key) {  //�ak��ma oldu�unda de�erin gelece�i indexin bir sonraki hashe yerle�tirilmesi.
		return 1;
	}

	@Override
	public void bucketPut(Key key, Value value, String folderName) {  //hastableda daha �nce eklenmi� olan value 
		                                                              //de�eri hangi folderda ge�iyorsa ona ekle.
		int temp = findHashEntry(hashValue(key), key, value);

		if (temp >= 0) {
			table[temp].put(folderName);

		} else {  //hashtabla eklenecek de�er hashtabla yoksa hashtablela yerle�tirilir.
			table[-(temp + 1)] = new HashEntry<Key, Value>(key, value);
			table[-(temp + 1)].put(folderName);
			n++;
		}

	}

}

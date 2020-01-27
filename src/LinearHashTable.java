
public class LinearHashTable<Key, Value> extends HashMap<Key, Value> {

	@Override
	protected void createTable() {
		table = new HashEntry[tableSize];
		for (int i = 0; i < tableSize; i++)
			table[i] = null;
	}

	@Override
	protected int increment(Key key) {  //çakýþma olduðunda deðerin geleceði indexin bir sonraki hashe yerleþtirilmesi.
		return 1;
	}

	@Override
	public void bucketPut(Key key, Value value, String folderName) {  //hastableda daha önce eklenmiþ olan value 
		                                                              //deðeri hangi folderda geçiyorsa ona ekle.
		int temp = findHashEntry(hashValue(key), key, value);

		if (temp >= 0) {
			table[temp].put(folderName);

		} else {  //hashtabla eklenecek deðer hashtabla yoksa hashtablela yerleþtirilir.
			table[-(temp + 1)] = new HashEntry<Key, Value>(key, value);
			table[-(temp + 1)].put(folderName);
			n++;
		}

	}

}

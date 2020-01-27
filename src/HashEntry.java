import java.util.ArrayList;

public class HashEntry<Key, Value> {
	private Key key;
	private Value value; // cat

	ArrayList<Entry> textList;

	HashEntry(Key key, Value value) {
		this.key = key;
		this.value = value;
		textList = new ArrayList<Entry>();
	}

	public Key getKey() {
		return key;
	}

	public Value getValue() {
		return value;
	}

	public ArrayList<Entry> getTextList() {
		return textList;
	}

	public void put(String folderName) {
		if (folderName == null) {
			return;
		}

		if (textList.isEmpty() == true) {
			textList.add(new Entry(folderName));
		} else {
			if (textList.get(textList.size() - 1).getFolderName().equalsIgnoreCase(folderName)) {
				textList.get(textList.size() - 1).setCount(textList.get(textList.size() - 1).getCount() + 1); // foldernamede ayn� valuden bir daha
																												//daha geldi�inde count artt�r�l�yor.
																												
			} else {
				textList.add(new Entry(folderName)); // Yeni entry eklenmesi
			}
		}
	}

	public void get() {
		for (int i = 0; i < textList.size(); i++) {
			System.out.println(textList.get(i).toString());
		}
	}
}


public class Entry {
	private String folderName;
	private int count;
	

	public Entry(String value) {
		this.folderName = value;
		this.count = 1;
		
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String value) {
		this.folderName = value;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		String str = "FolderName: "+folderName + " -> count: " + count;
		return str;
	}

}

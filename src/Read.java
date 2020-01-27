import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Read {

	private ArrayList<String> stopWords = new ArrayList<>();
	private ArrayList<String> bintxt=new ArrayList<>();
	private final String DELIMITERS = "[-+=" + " " + // space
			"\r\n " + // carriage return line fit
			"1234567890" + // numbers
			"’'\"" + // apostrophe
			"(){}<>\\[\\]" + // brackets
			":" + // colon
			"," + // comma
			"‒–—―" + // dashes
			"…" + // ellipsis
			"!" + // exclamation mark
			"." + // full stop/period
			"«»" + // guillemets
			"-‐" + // hyphen
			"?" + // question mark
			"‘’“”" + // quotation marks
			";" + // semicolon
			"/" + // slash/stroke
			"⁄" + // solidus
			"␠" + // space?
			"·" + // interpunct
			"&" + // ampersand
			"@" + // at sign
			"*" + // asterisk
			"\\" + // backslash
			"•" + // bullet
			"^" + // caret
			"¤¢$€£¥₩₪" + // currency
			"†‡" + // dagger
			"°" + // degree
			"¡" + // inverted exclamation point
			"¿" + // inverted question mark
			"¬" + // negation
			"#" + // number sign (hashtag)
			"№" + // numero sign ()
			"%‰‱" + // percent and related signs
			"¶" + // pilcrow
			"′" + // prime
			"§" + // section sign
			"~" + // tilde/swung dash
			"¨" + // umlaut/diaeresis
			"_" + // underscore/understrike
			"|¦" + // vertical/pipe/broken bar
			"⁂" + // asterism
			"☞" + // index/fist
			"∴" + // therefore sign
			"‽" + // interrobang
			"※" + // reference mark
			"]";

	private HashMap<Integer, String> hashTable;
	private int type;
	private long indexTime=0;
	private long max=0;
	private long min=99999999;
	
	
	public Read(int type, int tableType) {
		this.type = type;
		readStopWord();

		if (tableType == 1) {
			hashTable = new LinearHashTable<>();
			System.out.println("Linear Hashtable is created!");
			listTest();
			System.out.println("The words were placed hashtable.!");
		} else if (tableType == 2) {
			hashTable = new DoubleHashTable<>();
			System.out.println("Double Hashtable is created!");
			listTest();
			System.out.println("The words were placed hashtable.!");
		}
		
		read1000Txt();
	}
	
	public long getIndexTime() {
		return indexTime;
	}
	
	public ArrayList<String> getBintxt(){
		return bintxt;
	}
	
	public long getMax() {
		return max;
	}
	
	public long getMin() {
		return min;
	}
	
	

	public int SSF(String value) {
		int sum = 0;
		for (int i = 0; i < value.length(); i++) {
			sum = sum + ((int)(value.toUpperCase().charAt(i))-64);
		}
		return sum;
	}

	public int PAF(String value) {
		int sum = 0;
		int prime = 11;
		for (int i = 0; i < value.length(); i++) {
			sum = (int) (sum
					+ ((((int) value.toUpperCase().charAt(i)) - 64) * (Math.pow(prime, value.length() - i - 1))));
		}
		return sum;
	}

	public void readStopWord() {
		File file = new File("stop_words_en.txt");
		Scanner scanner, line;

		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				line = new Scanner(scanner.nextLine());
				while (line.hasNext()) {
					String word = line.next();
					stopWords.add(word);
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void listTest() { // Dosyaları buluyor
		
		long startTime=System.currentTimeMillis();
		
		File file = new File("bbc");
		File[] list = file.listFiles();
		String fileName;
		for (int i = 0; i < list.length; i++) {
			File[] txtList = list[i].listFiles();
			for (int j = 0; j < txtList.length; j++) {
				fileName = file.getName() + "/" + list[i].getName() + "/" + txtList[j].getName();
				readFile(fileName); // dosyayı okumaya başlıyo
			}
		}
		
		long estimatedTime=System.currentTimeMillis()-startTime;
		indexTime= estimatedTime;
	}
	

	public void readFile(String fileName) { // dosyadan inputtxt dosyası
		// okunuyor
		boolean flag = true;
		File file = new File(fileName);
		Scanner scanner, line;

		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) { // read line by line
				line = new Scanner(scanner.nextLine());
				while (line.hasNext()) { // read word by word
					String word = line.next(); // kelime kelime okuyor
					//
					String[] splitted = word.split(DELIMITERS);

					for (int i = 0; i < splitted.length; i++) {
						if (!(splitted[i].equals(""))) {
							for (int j = 0; j < stopWords.size(); j++) {
								if (stopWords.get(j).equalsIgnoreCase(splitted[i])) {
									flag = false;

									break;
								}
							}

							if (flag == true) {
								if (type == 1) {
									hashTable.put(SSF(splitted[i]), splitted[i], fileName); // okunan değer hemen table'a yerleştiriliyor.
																							
								} else if (type == 2) {
									hashTable.put(PAF(splitted[i]), splitted[i], fileName);
								}
							}
							flag = true;
						}
					}
				}
			}
			scanner.close();
	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void read1000Txt() //1000txt dosyası okunuyor.
	{
		
		File file = new File("1000.txt");
		Scanner scanner, line;

		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				line = new Scanner(scanner.nextLine());
				while (line.hasNext()) {
					String word = line.next();
					bintxt.add(word);
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public long calculate1000txtTime() {   
		long startTime=System.nanoTime();
		
		for (int i = 0; i < bintxt.size(); i++) {
			long startTimeWord=System.nanoTime();
			find(bintxt.get(i));
			long estimatedTimeWord=System.nanoTime()-startTimeWord;
			if(estimatedTimeWord>= max) {
				max=estimatedTimeWord;
			}
			if(estimatedTimeWord<= min) {
				min=estimatedTimeWord;
			}
		}	
		long estimatedTime=System.nanoTime()-startTime;
		return estimatedTime;   //1000txtyi okuma süresi
	}
	
	public void printCollutionCount() {
		System.out.println("Colution count is: "+hashTable.getCollutionCount());
		
	}
	public void find(String input) {
		if (type == 1) {
			hashTable.findHashEntry(hashTable.hashValue(SSF(input)), SSF(input), input);
		} else if (type == 2) {
			hashTable.findHashEntry(hashTable.hashValue(PAF(input)), PAF(input), input);
		}
		
	}

	public void search(String input) {
		if (type == 1) {
			hashTable.get(SSF(input), input);
		} else if (type == 2) {
			hashTable.get(PAF(input), input);
		}
	}
}

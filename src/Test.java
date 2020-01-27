import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		System.out.println("Please enter the type of key: \n" 
												+ "1: SSF\n" 
												+ "2: PAF\n");
		Scanner scanner = new Scanner(System.in);
		Scanner scanner2=new Scanner(System.in);
		int type = scanner.nextInt();
		
		System.out.println("Please enter the type of hashtable: \n" 
				+ "1: Linear Hashtable\n" 
				+ "2: Double Hashtable\n");
		
		int hashType = scanner.nextInt();
		
		Read read = new Read(type,hashType);
		
		
		
		while(true) {
			System.out.println("\nHangi iþlemi yapacaðýnýzý menüden seçiniz\n");
			System.out.println("1) Search \n2) 1000.txt search \n3)Collution Count\n4)Index Time\n5)Average Time\n6)Minimum Time and Maximum Time");
			int mode = scanner.nextInt();
			
			if(mode==1) {
				System.out.println("Please enter a word ");
				String input=scanner2.nextLine();
				read.search(input);
			}
			else if(mode==2) {
				System.out.println("1000txt search time is: "+read.calculate1000txtTime()+" ns");
			}
			else if(mode==3){
				read.printCollutionCount();
			}
			else if(mode==4) {
				System.out.println("Indext time is: "+read.getIndexTime()+" ms");
			}
			else if(mode==5) {
				System.out.println("Average time is: "+read.calculate1000txtTime()/read.getBintxt().size()+" ns");
			}
			else if(mode==6) {
				read.calculate1000txtTime();
				System.out.println("Minimum time is: "+read.getMin()+" ns");
				System.out.println("Maximum time is: "+read.getMax()+" ns");
			}
			else {
				System.out.println("Error! Please enter the right mode.");
			}		
		}	
	}

}

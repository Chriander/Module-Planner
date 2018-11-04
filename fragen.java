package module_planer;


public class fragen {
	static String[] fragenüberfragen = new String[9];
	
	
	
	public fragen() {
		
		fragenüberfragen[0]= ("How many modules?");
		fragenüberfragen[1]= ("What is the module called?");
		fragenüberfragen[2]= ("What priority does the module have? (0-10) ");
		fragenüberfragen[3]= ("Number of VORLESUNGEN per week? (no negative numbers)");
		fragenüberfragen[4]= ("What day is the module (1-7)?");
		fragenüberfragen[5]= ("What time is the module (1-24)?");
		fragenüberfragen[6]= ("Number of UEBUNGEN per week? (no negatives)");
		fragenüberfragen[7]= ("What day is the UEBUNG for the module (1-7)?");
		fragenüberfragen[8]= ("What time is the UEBUNG for the module (1-24)?");
		
		
	}
	public static String changequestion(int i) {
		
		return fragenüberfragen[i];
	}
	
}

package module_planer;


public class Module {
	public String modulename;
	public date_class vorlesungszeit;
	public date_class uebung;
	public int priority;
	
	
	
	public Module() {
		
	}

	public Module (String modulename, int[] dayVorlesung, int[] timeVorlesung,
			int[] dayUebung, int[] timeUebung, int priority) {
		
		this.modulename = modulename;
		this.vorlesungszeit = new date_class(dayVorlesung, timeVorlesung);
		this.uebung = new date_class(dayUebung, timeUebung) ;
		this.priority = priority;
		
	}
	
	

	

	

}

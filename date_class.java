package module_planer;


public class date_class {
	public int[] day;
	public int[] time;

	public date_class() {

	}

	public date_class(int[] day, int[] time) {
		this.day = day;
		this.time = time;

	}

	public boolean compareVorlesungen(date_class b) {

		for (int i = 0; i < this.day.length; i++) {
			for (int z = 0; z < b.day.length; z++) {

				if (this.day[i] == b.day[z] && this.time[i] == b.time[z]) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean compareUebungen(date_class b) {

		for (int i = 0; i < this.day.length; i++) {
			for (int z = 0; z < b.day.length; z++) {
				if (this.day[i] != b.day[z] || this.time[i] != b.time[z]) {
					return true;
				}
			}
		}

		return false;
	}

}


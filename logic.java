package module_planer;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class logic {
	String r;

	int anzahlrausgenommen = 0;
	int sw;
	int max_index = 0;
	int a = 0;

	Module compare1 = new Module();
	Module compare2 = new Module();
	date_class compareA = new date_class();
	date_class compareB = new date_class();

	int amountVorlesung = -1;
	int amountUebung = -1;
	int currentmaxpriority = 0;
	boolean truth = false;
	boolean truthu = true;
	List<Module> input = new ArrayList<Module>();
	List<Module> finallist = new ArrayList<Module>();
	static int[] indexlist = new int[50];
	Scanner scanIn = new Scanner(System.in);
	static String s;

	public void framesonframes(logic mainthread) throws InterruptedException {

		synchronized (this) {

			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					new Input_main(mainthread);

				}
			});
			wait();
			wait();
			wait();

		}

	}

	public void main_algorithm() throws InterruptedException {
		synchronized (this) {
			for (int i = 0; i < 50; i++) {
				indexlist[i] = -1;
			}
			wait();
			int loop = Input_main.index;
			int prio = -1;

			Input_main.stringsubmit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					s = Input_main.jt.getText();
					Input_main.jt.setText("");

					Thread t4 = new Thread(new Runnable() {

						public void run() {
							try {
								notifier2();
							} catch (Exception e) {

							}

						}
					});
					t4.start();
					try {
						t4.join();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			});

			for (int i = 0; i < loop; i++) {

				sw = i + 1;

				Input_main.changeframe(fragen.changequestion(1));

				Input_main.buttonSubmit.setVisible(false);

				Input_main.stringsubmit.setVisible(true);
				wait();
				Input_main.currentModule.setText("Modulename: " + s);

				// implement priority with old button

				Input_main.stringsubmit.setVisible(false);
				Input_main.buttonSubmit.setVisible(true);
				Input_main.changeframe(fragen.changequestion(2));

				while (prio > 10 || prio < 0) {

					wait();
					prio = Input_main.index;

				}
				Input_main.jt.requestFocus();
				
				Input_main.changeframe(fragen.changequestion(3));

				while (amountVorlesung < 0) {
					wait();
					amountVorlesung = Input_main.index;
				}

				int[] dayVorlesung = new int[amountVorlesung];
				int[] timeVorlesung = new int[amountVorlesung];
				// creates Vorlesung array

				for (int q = 0; q < amountVorlesung; q++) {

					Input_main.changeframe(fragen.changequestion(4));
					Input_main.jt.requestFocus();
					wait();
					dayVorlesung[q] = Input_main.index;
					Input_main.changeframe(fragen.changequestion(5));
					Input_main.jt.requestFocus();
					wait();
					timeVorlesung[q] = Input_main.index;
				}

				Input_main.changeframe(fragen.changequestion(6));
				Input_main.jt.requestFocus();

				while (amountUebung < 0) {
					wait();
					amountUebung = Input_main.index;

				}

				int[] dayUebung = new int[amountUebung];
				int[] timeUebung = new int[amountUebung];

				// creates Uebung array

				for (int q = 0; q < amountUebung; q++) {
					Input_main.changeframe(fragen.changequestion(7));
					Input_main.jt.requestFocus();
					wait();
					dayUebung[q] = Input_main.index;
					Input_main.changeframe(fragen.changequestion(8));
					Input_main.jt.requestFocus();
					wait();
					timeUebung[q] = Input_main.index;
				}

				if (amountUebung == 0) {
					int[] dayUebung1 = new int[1];
					int[] timeUebung1 = new int[1];
					dayUebung1[0] = -1;
					timeUebung1[0] = -1;

					Module moduler = new Module(s, dayVorlesung, timeVorlesung, dayUebung1, timeUebung1, prio);
					input.add(moduler);
					// reset variables
					prio = -1;
					amountVorlesung = -1;
					amountUebung = -1;
				} else {
					Module moduler = new Module(s, dayVorlesung, timeVorlesung, dayUebung, timeUebung, prio);
					input.add(moduler);
					// reset variables
					prio = -1;
					amountVorlesung = -1;
					amountUebung = -1;

				}
				Input_main.currentModule.setText("");

			}
			int counter = input.size();
			// algorithm start

			// finds case, if module doesn't overlap at all

			for (int z = 0; z < counter; z++) {

				for (int v = 0; v < counter; v++) {
					compare1 = input.get(z);
					compareA.day = compare1.vorlesungszeit.day;
					compareA.time = compare1.vorlesungszeit.time;
					compare2 = input.get(v);
					compareB.day = compare2.vorlesungszeit.day;
					compareB.time = compare2.vorlesungszeit.time;

					if (z != v && truth == false && truthu == true) {
						truth = compareA.compareVorlesungen(compareB);
						compareA.day = compare1.uebung.day;
						compareA.time = compare1.uebung.time;
						compareB.day = compare2.uebung.day;
						compareB.time = compare2.uebung.time;
						if (compareA.day[0] != -1 && compareB.day[0] != -1) {
							truthu = compareA.compareUebungen(compareB);

						}
						if (compareA.day[0] == -1) {
							truthu = true;
						}
					}

				}

				if (truth == false && truthu == true) {

					indexlist[z] = z;

				}
				truth = false;
				truthu = true;

			}

			for (int i = 0; i < 50; i++) {
				if (indexlist[i] > -1) {

					finallist.add(input.get(i - anzahlrausgenommen));
					input.remove(i - anzahlrausgenommen);
					anzahlrausgenommen += 1;

				}
			}

			// finds modules with highes priorities and inserts them

			while (true) {

				if (input.isEmpty()) {
					break;
				}

				// loop to find currenmaximum
				for (int z = 0; z < input.size(); z++) {
					compare1 = input.get(z);

					if (currentmaxpriority < compare1.priority) {
						currentmaxpriority = compare1.priority;

					}

				}

				for (int z = 0; z < input.size(); z++) {
					compare1 = input.get(z);

					if (currentmaxpriority == compare1.priority) {
						compareA.day = compare1.vorlesungszeit.day;
						compareA.time = compare1.vorlesungszeit.time;
						max_index = z;
					}
				}
				for (int t = 0; t < finallist.size(); t++) {

					compareA.day = compare1.vorlesungszeit.day;
					compareA.time = compare1.vorlesungszeit.time;
					compare2 = finallist.get(t);
					compareB.day = compare2.vorlesungszeit.day;
					compareB.time = compare2.vorlesungszeit.time;

					if (truth == false) {

						truth = compareA.compareVorlesungen(compareB);
					}

					compareA.day = compare1.uebung.day;
					compareA.time = compare1.uebung.time;
					compareB.day = compare2.uebung.day;
					compareB.time = compare2.uebung.time;
					if (truthu == true && compareA.day[0] != -1 && compareB.day[0] != -1) {
						truthu = compareA.compareUebungen(compareB);
					}

				}

				if (compareA.day[0] == -1) {
					truthu = true;
				}

				if (truth == false && truthu == true) {
					finallist.add(input.get(max_index));

				}

				truth = false;
				truthu = true;

				input.remove(max_index);

				currentmaxpriority = 0;
				max_index = 0;

			}

			compare1 = finallist.get(0);
			String results = "<html>" + compare1.modulename;

			for (int read = 1; read < finallist.size(); read++) {
				compare1 = finallist.get(read);
				results = results + ";" + "<br/>" + compare1.modulename;
			}
			results = results + ".</html>";

			JLabel res = new JLabel("", SwingConstants.LEFT);
			res.setVerticalAlignment(SwingConstants.TOP);
			res.setBounds(10, 10, 290, 790);

			res.setText(results);
			JFrame w2 = new JFrame();

			w2.setSize(300, 800);
			w2.setLocation(0, 0);
			w2.setTitle("Results");
			w2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			w2.setLayout(null);

			w2.add(res);
			w2.setVisible(true);

			// work for next time: create string parser + add new panel for
			// results

		}

	}

	public void notifier() throws InterruptedException {
		synchronized (this) {
			notifyAll();
		}

	}

	public void notifier2() throws InterruptedException {
		synchronized (this) {
			notifyAll();
		}

	}

}

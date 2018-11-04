package module_planer;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Input_main extends JFrame {

	
	private static final long serialVersionUID = 1L;
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	static JLabel Frage1;
	JButton prenext[] = new JButton[2];
	static JFrame w1;
	static JTextField jt;
	static String input_from_box;
	static int index;
	static boolean truthm = false;
	static JButton buttonSubmit;
	static JButton stringsubmit;
	static JLabel currentModule;
	
	
	
	
	

	public Input_main(logic mainthread) {

		w1 = new JFrame();
		w1.setSize(800, 600);
		w1.setLocation(0, 0);
		w1.setTitle("Module Scheduler");
		w1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w1.setLayout(null);
		createView(mainthread);
		w1.setVisible(true);

	}

	public void createView(logic mainthread) {
		prenext[0] = new JButton("previous");
		prenext[0].setBounds(75, 450, 100, 50);
		w1.add(prenext[0]);
		prenext[1] = new JButton("next");
		prenext[1].setBounds(175, 450, 100, 50);
		w1.add(prenext[1]);
		new fragen();
		Frage1 = new JLabel(fragen.changequestion(0));
		Frage1.setBounds(50, 150, 500, 100);
		w1.add(Frage1);
		jt = new JTextField();
		jt.setBounds(50, 300, 150, 40);
		w1.add(jt);
		currentModule = new JLabel("");
		currentModule.setBounds(450, 75, 150, 150);
		w1.add(currentModule);
		
		
		

		buttonSubmit = new JButton("submit");
		buttonSubmit.setBounds(225, 300, 100, 40);
		buttonSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index = Integer.parseInt(jt.getText());
				truthm = true;
				jt.setText("");
				Thread t3 = new Thread(new Runnable() {

					public void run() {
						try {
							mainthread.notifier();
						} catch (Exception e) {

						}

					}
				});
				t3.start();
				
				try {
					t3.join();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		w1.add(buttonSubmit);
		stringsubmit = new JButton("submit");
		stringsubmit.setBounds(225, 300, 100, 40);
		w1.add(stringsubmit);
		stringsubmit.setVisible(false);
		
		
		
	
	}

	public static void main(String[] args) throws InterruptedException {

	

		final logic mainthread = new logic();

		Thread t1 = new Thread(new Runnable() {

			public void run() {
				try {
					mainthread.framesonframes(mainthread);
				} catch (Exception e) {

				}

			}
		});

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					mainthread.main_algorithm();
				} catch (InterruptedException e) {

				}

			}
		});

		t1.start();
		t2.start();
		
		
		t1.join();
		t2.join();
		
		

	}

	public static void changeframe(String s) {
		Frage1.setText(s);
	}

}

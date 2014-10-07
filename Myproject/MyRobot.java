import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class MyRobot extends JFrame {

	private Robot robot;

	public MyRobot() {

		setTitle("Robot");
		JPanel panel = new JPanel();

		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice screen = env.getDefaultScreenDevice();

		try {
			robot = new Robot(screen);
		}
		catch(AWTException ex) { }

		JButton bt1 = new JButton("Start");
		panel.add(bt1);
		bt1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				for(;;) {
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.keyRelease(KeyEvent.VK_ENTER);
				}
			}
		} );

		Container pane = getContentPane();
		pane.add(panel);
		pack();
	}

	public static class Test {
		public static void main(String[] args) {
			MyRobot r = new MyRobot();
			r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			r.show();
		}
	}
} 


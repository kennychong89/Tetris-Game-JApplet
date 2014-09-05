package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {
	private JButton moveLeftButton;
	private JButton moveRightButton;
	private JButton rotateButton;
	private TetrisApplet applet;
	
	public ControlPanel(TetrisApplet applet) {
		moveLeftButton = new JButton("Left");
		moveRightButton = new JButton("Right");
		rotateButton = new JButton("Rotate");
		this.applet = applet;
		
		rotateButton.addActionListener(new RotateListener());
		moveLeftButton.addActionListener(new MoveLeftListener());
		moveRightButton.addActionListener(new MoveRightListener());
		add(moveLeftButton);
		add(moveRightButton);
		add(rotateButton);
	}
	
	private class RotateListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			applet.rotatePiece();
		}
	}
	
	private class MoveLeftListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			applet.movePieceLeft();
		}
	}
	
	private class MoveRightListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			applet.movePieceRight();
		}
	}
}

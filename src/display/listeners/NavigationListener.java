package display.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import display.Start;
import display.components.LabeledTextField;
import display.components.TreeFrame;
import display.components.TreeMenu;

public class NavigationListener implements ActionListener, MouseListener {

	public static final String PREVIOUS = "PREVIOUS";
	public static final String NEXT = "NEXT";

	private LabeledTextField ltfIndex = null;
	
	public NavigationListener(LabeledTextField ltf){
		ltfIndex = ltf;
	}
	
	private void nextTree(){
		int indexVal;
		indexVal = Integer.parseInt(ltfIndex.getText()) + 1;
		redraw(indexVal);
	}
	
	private void prevTree(){
		int indexVal;
		indexVal = Integer.parseInt(ltfIndex.getText()) - 1;
		redraw(indexVal);
	}
	
	private void redraw(int indexVal){
		if(indexVal >= 0 && indexVal < Start.getTreeNodes().size()){
			ltfIndex.setText(indexVal + "");
//			Start.setRoot(Start.getTreeNodes().get(indexVal));
			TreeMenu.drawTree(Start.getTreeNodes().get(indexVal));
		}else{
			String errString = "Error! Index out of range!  I only have " + Start.getTreeNodes().size() + " trees.";
			JOptionPane.showMessageDialog(TreeFrame.getInstance(), errString); 
		}		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals(PREVIOUS)){
			prevTree();
		}else if(arg0.getActionCommand().equals(NEXT)){
			nextTree();
		}else{
			System.err.println("Command: " + arg0.getActionCommand() + " not recognized!");
			return;
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getButton() == MouseEvent.BUTTON1){
			// move backwards
			prevTree();
		}else if(arg0.getButton() == MouseEvent.BUTTON3){
			// move forwards
			nextTree();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

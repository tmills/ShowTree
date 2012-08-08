package display.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import display.Start;
import display.components.LabeledTextField;
import display.components.TreeFrame;
import display.components.TreeMenu;

public class NavigationListener implements ActionListener {

	public static final String PREVIOUS = "PREVIOUS";
	public static final String NEXT = "NEXT";

	private LabeledTextField ltfIndex = null;
	
	public NavigationListener(LabeledTextField ltf){
		ltfIndex = ltf;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		int indexVal;
		if(arg0.getActionCommand().equals(PREVIOUS)){
			indexVal = Integer.parseInt(ltfIndex.getText()) - 1;
		}else if(arg0.getActionCommand().equals(NEXT)){
			indexVal = Integer.parseInt(ltfIndex.getText()) + 1;
		}else{
			System.err.println("Command: " + arg0.getActionCommand() + " not recognized!");
			return;
		}
		
		if(indexVal >= 0 && indexVal < Start.getTreeNodes().size()){
			ltfIndex.setText(indexVal + "");
			Start.setRoot(Start.getTreeNodes().get(indexVal));
			TreeMenu.drawTree(Start.getTreeNodes().get(indexVal));
		}else{
			// TODO add some kind of jpopup to notify user instead of ignoring input
			String errString = "Error! Index out of range!  I only have " + Start.getTreeNodes().size() + " trees.";
			JOptionPane.showMessageDialog(TreeFrame.getInstance(), errString); 
//			System.err.println(errString);
		}
	}

}

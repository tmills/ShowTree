package display.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import display.Start;
import display.components.LabeledTextField;
import display.components.TreeMenu;

public class IndexListener implements KeyListener {

	private LabeledTextField ltfIndex = null;
	
	public IndexListener(LabeledTextField ltf){
		ltfIndex = ltf;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// do nothing... wait until "typed" event
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// do nothing... wait until "typed" event
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
			int indexVal = Integer.parseInt(ltfIndex.getText());
			TreeMenu.drawTree(Start.getTreeNodes().get(indexVal));
		}
	}

}

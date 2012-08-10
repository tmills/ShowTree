//ShowTree Tree Visualization System
//Copyright (C) 2009 Yuvi Masory
//
//This program is free software; you can redistribute it and/or
//modify it under the terms of the GNU General Public License
//as published by the Free Software Foundation, version 3 only.
//
//This program is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//GNU General Public License for more details.
//
//You should have received a copy of the GNU General Public License
//along with this program; if not, write to the Free Software
//Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.

package display;

import java.awt.event.InputEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import util.Load;

import logic.Node;
import display.components.TreeFrame;
import display.listeners.NavigationListener;
import display.components.TreeMenu;

/*
 * This class contains the program's start point.
 * All constants and many other program-wide variables are stored here.
 * The root of the tree being displayed is also stored here.
 */
//IMPROVE add additional star to comments to allow for javadocs creation
public class Start {
	//added to bottom of Node rectangle to compensate for FontMetrics.getStringBounds adding top padding
	public static final int BOTTOM_NODE_PADDING = 5; 
	public static final int Y_SEPARATION = 50;
		
	public static final int DEFAULT_NUM_NODES = 20;
	public static final int DEFAULT_ARITY = 3;
	public static final int DEFAULT_MIN_X_SEPARAION = 20;
	
	//Command on Mac, control on other platforms
	private static int commandKey;
	
	//Root of the currently displayed tree
	private static Node root;
	//Saved arity value, not necessarily the one currently being displayed
	private static int arity;
	//Saved minSeparation value, not necessarily the one currently being displayed
	private static int minSeparation = DEFAULT_MIN_X_SEPARAION;
	// set of trees read in
	private static ArrayList<Node> treeNodes = null;
	
	private static boolean showGrid;
	private static boolean showShiftSums;
	private static boolean showNodeBounds;
	
	//Path to directory containing PTB-format mrg files
	private static String ptbDirPath;
	
	//Size of boundaries around TreePane where nothing will be drawn.
	public static final int PADDING = 40;
	public static final int WIDTH = 820;
	public static final int HEIGHT = 760;
	
	public static int i = 1;
	private static NavigationListener navListener;
	
    private Start(String[] args) throws IOException {
    	if(System.getProperty("os.name").startsWith("Mac")) {
    		commandKey = InputEvent.META_MASK;
    	}
    	else {
    		commandKey = InputEvent.CTRL_MASK;
    	}
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
        	System.out.println(e);
        }
        TreeFrame frame = TreeFrame.getInstance();
        frame.setVisible(true);
        
        if(args.length > 0){
        	treeNodes = Load.loadPTBTrees(new FileReader(new File(args[0])));
        }else{
        	// read in from standard input...
        	treeNodes = Load.loadPTBTrees(new InputStreamReader(System.in));
        }
        
        Start.setRoot(treeNodes.get(0));
        TreeMenu.drawTree(treeNodes.get(0));
    }
    
    /*
     * @returns root of currently displayed tree
     */
    public static Node getRoot() {
    	return root;
    }
    
    public static void setRoot(Node n) {
    	root = n;
    }
    
    /*
     * @returns currently saved arity value, not necessarily the one displayed
     */
    public static int getArity() {
    	return arity;
    }
    
    public static void setArity(int ar) {
    	arity = ar;
    } 

    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
					new Start(args);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
    }

	public static void setMinSeparation(int minSeparation) {
		Start.minSeparation = minSeparation;
	}

    /*
     * @returns currently saved minSeparation value, not necessarily the one displayed
     */
	public static int getMinXSeparation() {
		return minSeparation;
	}

	public static void setShowGridOption(boolean showGrid) {
		Start.showGrid = showGrid;
	}

	public static boolean isShowGrid() {
		return showGrid;
	}

	public static void setShowNodeFieldOption(boolean showShiftSums) {
		Start.showShiftSums = showShiftSums;
	}
	
	public static boolean isShowNodeBounds() {
		return showNodeBounds;
	}
	
	public static void setShowNodeBoundsOption(boolean showNodeBounds) {
		Start.showNodeBounds = showNodeBounds;
	}

	public static boolean isShowNodeField() {
		return showShiftSums;
	}

	public static void setPtbDirPath(String ptbDirPath) {
		Start.ptbDirPath = ptbDirPath;
	}

	public static String getPtbDirPath() {
		return ptbDirPath;
	}

	public static int getCommandKey() {
		return commandKey;
	}

	public static ArrayList<Node> getTreeNodes() {
		return treeNodes;
	}

	public static void setTreeNodes(ArrayList<Node> treeNodes) {
		Start.treeNodes = treeNodes;
	}

	public static NavigationListener getNavigationListener() {
		return navListener;
	}

	public static void setNavigationListener(NavigationListener nav) {
		navListener = nav;
	}
}

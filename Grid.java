//https://youtu.be/uJ61CTf-UDk
// Please remove the "//" or "/*" or "*/" or any comment if testing the application out
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Scanner; 
import javax.swing.*;
import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class Grid /*extends JFrame implements ActionListener*/{
    private boolean[][] bombGrid;
    private int[][] countGrid;
    private int numRows;
    private int numColumns;
    private int numBombs;
    
    private JButton[][] buttons;
    private int numRevealed;
    
    

    public Grid() {
        this.numRows = 10;
        this.numColumns = 10;
        this.numBombs = 25;
        createBombGrid();
        createCountGrid();
        //s();
    }
    
    public Grid(int rows, int columns) {
        this.numRows = rows;
        this.numColumns = columns;
        this.numBombs = 25;
        createBombGrid();
        createCountGrid();
        //s();
    }
    

    public Grid(int rows, int columns, int numBombs) {
        this.numRows = rows;
        this.numColumns = columns;
        this.numBombs = numBombs;
        createBombGrid();
        createCountGrid();
        //s();
    }
    
    public int getNumRows() {
        return numRows;
    }
    
    public int getNumColumns() {
        return numColumns;
    }
    
    public int getNumBombs() {
        return numBombs;
    }
    
    public boolean[][] getBombGrid() {
        boolean[][] Bcopy = new boolean[numRows][numColumns];
        int i,j;
        for(i = 0; i < numRows; i++) {
            for(j = 0; j < numColumns; j++) {
                Bcopy[i][j] = bombGrid[i][j];
            }
        }
        return Bcopy;
    }
    
    public int[][] getCountGrid() {
        int[][] Ccopy = new int[numRows][numColumns];
        int i,j;
        for(i = 0; i < numRows; i++) {
            for(j = 0; j < numColumns; j++) {
                Ccopy[i][j] = countGrid[i][j];
            }
        }
        return Ccopy;
    }
    
    public boolean isBombAtLocation(int row, int column) {
        return bombGrid[row][column];
    }
    
    public int getCountAtLocation(int row, int column) {
        return countGrid[row][column];
    }
    
    private void createBombGrid() {
        bombGrid = new boolean[numRows][numColumns];
        int bomb = 0;
        
        while (bomb < numBombs) {
            int ranRow = (int) (Math.random() * numRows);
            int ranCol = (int) (Math.random() * numColumns);
            
            if (!bombGrid[ranRow][ranCol]) {
                bombGrid[ranRow][ranCol] = true;
                bomb += 1;
            }
        }
    }
    
    private void createCountGrid() {
    	int rowes = 0;
    	int colus = 0;
    	int i = 0;
    	int j = 0;
    	int rs = 0;
    	int cr = 0;
        countGrid = new int[numRows][numColumns];
        
        for (rowes = 0; rowes < numRows; rowes++) {
            for (colus = 0; colus < numColumns; colus++) {
                    int count = 0;
                    for (i = -1; i <= 1; i++) {
                        for (j = -1; j <= 1; j++) {
                            rs = rowes + i;
                            cr = colus + j;
                            
                            if (rs >= 0) {
                            	if(rs < numRows) {
                            		if(cr >= 0) {
                            			if(cr < numColumns) {
                            				if(bombGrid[rs][cr]) {
                            					count++;
                            				}
                            			}
                            		}
                            	}
                            }
                        }
                    }
                    
                countGrid[rowes][colus] = count;
            }
        }
    }

    public void s() {
    	for(int i = 0; i <= 9; ++i) {
    		for(int j = 0; j <= 9; ++j) {
    			//cheating
    			System.out.print(bombGrid[i][j] + " ");
    		}
    		System.out.println("");
    	}
    	
    	
    	
    	JFrame frame = new JFrame();
    	JPanel panel = new JPanel(new GridLayout(getNumRows(), getNumColumns()));
        buttons = new JButton[getNumRows()][getNumColumns()]; 
        
        
        for (int row = 0; row < getNumRows(); row++) {
        	
            for (int col = 0; col < getNumColumns(); col++) {
            	
           
                buttons[row][col] = new JButton("?");
                buttons[row][col].addActionListener(this);
                panel.add(buttons[row][col]); 
            }
        }
        
        frame.add(panel); 
        frame.setSize(300, 300); 
        frame.setVisible(true); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent e) {
    	
    	 Scanner scrn = new Scanner(System.in); 
        JButton button = (JButton) e.getSource();
        JFrame frame = new JFrame();
        
  
       
    	System.out.println(numRevealed);
        for (int row = 0; row < getNumRows(); row++) {
            for (int col = 0; col < getNumColumns(); col++) {
            	if(button == buttons[row][col]) {
	            	if(isBombAtLocation(row,col)) {
	            		String newName = "Bomb";
                        buttons[row][col].setText(newName);
	            	
	            		System.out.println("BOOM");
	            		for (int i = 0; i < getNumRows(); i++) {
	                        for (int j = 0; j < getNumColumns(); j++) {
	                        	
	                        	
	                        	
	                        	if(isBombAtLocation(i, j)) {
	                        		
	                        	
	                        			newName = "Bomb";
		                            	buttons[i][j].setText(newName);
		                            	button.setEnabled(false);
	                        		
	                        	}
	                        	
	                        	else {
	                        		String newName1 = "" + getCountAtLocation(i,j);
		                            buttons[i][j].setText(newName1);
		                            button.setEnabled(false);
		                            
	                        	}
	                        	
	                        }
	                        
	            		
	            		}
	            		
	            		int option = JOptionPane.showOptionDialog(frame, "Game Over! You Lost Play again?", "Game Over",
	            		JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

	            		if (option == JOptionPane.YES_OPTION) {
	            			Window[] windows = Window.getWindows();
	            			for (int i = 0; i < windows.length; i++) {
	            			    Window window = windows[i];
	            			    window.dispose();
	            			}
	            		
	            		System.out.println("Choose Your Mode");
	            	    System.out.println("Put '1': if you want to play the default");
	            	    System.out.println("Put '2': if you want to play with custom grid");
	            	    System.out.println("Put '3': if you want to play with custom grid and bombs");
	            	    String s = scrn.next();
	            	    if(s.equals("1")) {
	            	    	new Grid();
	            	    }
	            	    else if(s.equals("2")) {
	            	    	System.out.println("Choose the number of rows");
	            	    	int getr = scrn.nextInt();
	            	    	System.out.println("Choose the number of columns");
	            	    	int getc = scrn.nextInt();
	            	    	new Grid(getr, getc);
	            	    }
	            	    else {
	            	    	System.out.println("Choose the number of rows");
	            	    	int getr = scrn.nextInt();
	            	    	System.out.println("Choose the number of columns");
	            	    	int getc = scrn.nextInt();
	            	    	System.out.println("Choose the number of bombs");
	            	    	int getb = scrn.nextInt();
	            	    	new Grid(getr, getc, getb);
	            	    }

	            	}
	            		else if (option == JOptionPane.NO_OPTION) {
	            			
	            		    Window[] windows = Window.getWindows();
	            		    for (int i = 0; i < windows.length; i++) {
	            		        Window window = windows[i];
	            		        window.dispose();
	            		    }
	            		      
	            		}
	            		}
	            	
	            	else {
	            		button.setEnabled(false);
	            		numRevealed++;
	            	}
	            	
            	}
                if (button == buttons[row][col]) {
                	
          
                    String newName = "" + getCountAtLocation(row,col);
                    button.setText(newName);
                }
            }
        
        
    	
        if (numRevealed == (getNumRows() * getNumColumns() - getNumBombs())) {
    		int option = JOptionPane.showOptionDialog(frame, "You Won! Play Again?", "Game Over",
        		    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

        		
        		if (option == JOptionPane.YES_OPTION) {
        			Window[] windows = Window.getWindows();
        			for (int i = 0; i < windows.length; i++) {
        			    Window window = windows[i];
        			    window.dispose();
        			}
        		    
        		    
        		    
        		    System.out.println("Choose Your Mode");
        		    System.out.println("Put '1': if you want to play the default");
        		    System.out.println("Put '2': if you want to play with custom grid");
        		    System.out.println("Put '3': if you want to play with custom grid and bombs");
        		    String s = scrn.next();
        		    if(s.equals("1")) {
        		    	new Grid();
        		    }
        		    else if(s.equals("2")) {
        		    	System.out.println("Choose the number of rows");
        		    	int getr = scrn.nextInt();
        		    	System.out.println("Choose the number of columns");
        		    	int getc = scrn.nextInt();
        		    	new Grid(getr, getc);
        		    }
        		    else {
        		    	System.out.println("Choose the number of rows");
        		    	int getr = scrn.nextInt();
        		    	System.out.println("Choose the number of columns");
        		    	int getc = scrn.nextInt();
        		    	System.out.println("Choose the number of bombs");
        		    	int getb = scrn.nextInt();
        		    	new Grid(getr, getc, getb);
        		    }
        		    
        		}
        		
        		else if (option == JOptionPane.NO_OPTION) {
        		    Window[] windows = Window.getWindows();
        		    for (int i = 0; i < windows.length; i++) {
        		        Window window = windows[i];
        		        window.dispose();
        		    }
        		}
        }
        }
    
        
  
    }
   
    public static void main(String[] args) {
    	Scanner scrn = new Scanner(System.in); 
    	System.out.println("Choose Your Mode");
	    System.out.println("Put '1': if you want to play the default");
	    System.out.println("Put '2': if you want to play with custom grid");
	    System.out.println("Put '3': if you want to play with custom grid and bombs");
	    String s = scrn.next();
	    if(s.equals("1")) {
	    	new Grid();
	    }
	    else if(s.equals("2")) {
	    	System.out.println("Choose the number of rows");
	    	int getr = scrn.nextInt();
	    	System.out.println("Choose the number of columns");
	    	int getc = scrn.nextInt();
	    	new Grid(getr, getc);
	    }
	    else {
	    	System.out.println("Choose the number of rows");
	    	int getr = scrn.nextInt();
	    	System.out.println("Choose the number of columns");
	    	int getc = scrn.nextInt();
	    	System.out.println("Choose the number of bombs");
	    	int getb = scrn.nextInt();
	    	new Grid(getr, getc, getb);
	    }
    	
    
    }

	
}

package aknakereso;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import javax.swing.*;

public class GameInstance extends JPanel /*Egy konkret jatekmenetet reprezentalo osztaly*/
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3366195078379560135L;
	private int n;
	private int k;
	private Minefield minefield;
	private List<List<Integer>> displayField;
	private List<List<JButton>> board; /*A palya gombtablajat tarolo struktura*/
	private boolean firstClick;
	private boolean gameOver;
	private GameFrame frame;
	private int markedCells;
	private boolean isMarkerActive;
	private StatusBar bar;
	private int uncoveredCells;
	private boolean GameWon;
	
	public void markCell(int x, int y) /*Mezo jelolesenek kezelese*/
	{
		if(!firstClick)
		{
			if(displayField.get(x).get(y) == 10)
			{
				displayField.get(x).set(y, 9);
				markedCells++;
			}
			else if(getDisplayFieldValue(x,y) == 9)
			{
				displayField.get(x).set(y, 10);
				markedCells--;
			}
			board.get(x).get(y).setVisible(false);
			board.get(x).get(y).setVisible(true);
			bar.updateMines();
			updateDisplay();
		}
	}
	
	public void uncover(int x, int y) /*Mezo felfedese*/
	{
		int uncovered = minefield.getFieldValue(x,y);
		if(uncovered == -1 && getDisplayFieldValue(x,y) != 9) /*Ha akna, osszes akna felfedese, jatek vege*/
		{
			for(int i = 0;i < n;i++)
			{
				for(int j = 0; j<n; j++)
				{
					int c = minefield.getFieldValue(i,j);
					if(c == -1 && getDisplayFieldValue(i,j) !=9) displayField.get(i).set(j, -1);
				}
			}
			setGameOver(true);
			frame.GameOver();
		}
		else if(uncovered == 0 && getDisplayFieldValue(x,y) != 9) /*Ures mezo eseten szomszedok rekurziv felfedese*/
		{
			displayField.get(x).set(y, uncovered);
			uncoveredCells++;
			for(int i = x-1;i <= x+1;i++)
			{
				for(int j = y-1; j<=y+1; j++)
				{
					if(!(i == x && j == y) && i >=0 && j >=0 && i < n && j < n)
						if(minefield.getFieldValue(i, j) != getDisplayFieldValue(i,j))
						{
							{
								uncover(i,j);
							}
						}
				}
			}
		}
		else if(getDisplayFieldValue(x,y) != 9) /*Egyszeru mezo felfedese*/
		{
			displayField.get(x).set(y, uncovered);
			uncoveredCells++;
		}
		board.get(x).get(y).setVisible(false);
		board.get(x).get(y).setVisible(true);
		if(uncoveredCells == n*n-k) /*Ha a felfedett mezok szama eleri az osszes mezo es az aknak szamanak a kulonbseget, a jatekos nyert*/
		{
				setGameWon(true);
				setGameOver(true);
				frame.GameOver();
		}
		updateDisplay();
	}
	
	public void createMinefield(int x,int y) /*Aknamezo letrehozatasa, feltoltve*/
	{
		minefield = new Minefield(n,k,x,y);
		minefield.updateBoardValues();
	}
	
	public GameInstance(int meret, int akna, GameFrame f)
	{
		/*Attributumok inicializalasa*/
		
		firstClick = true;
		markedCells = 0;
		uncoveredCells = 0;
		n = meret;
		k = akna;
		frame = f;
		
		this.setLayout(new GridLayout(n,n));
		
		/*Megjelenitest tarolo struktura inicializalasa*/
		
		displayField = new ArrayList<List<Integer>>();
		for(int i = 0;i < n;i++)
		{
			displayField.add(i, new ArrayList<Integer>());
			for(int j = 0; j<n; j++)
			{
				displayField.get(i).add(j,10);
			}
		}
		
		
		createBoard(); /*Gombtabla letreheozasa*/
		@SuppressWarnings("unused")
		FieldListener EventHandler = new FieldListener(this); /*Esemenykezelo peldanyositasa, nem szukseges tarolni, a konstruktora hozzaadja a kezelot az osszes gombhoz*/
		updateDisplay();
		bar = new StatusBar(this); /*Statuszsav letrehozasa*/
		
	}
	
	public void startNew() /*Instant uj jatek*/
	{
		frame.newGame(n, k);
	}
	
	public void createBoard() /*Gombtabla letrehozasa*/
	{
		board = new ArrayList<List<JButton>>();
		
		
		for(int i = 0;i < n;i++)
		{
			board.add(i,new ArrayList<JButton>());
			for(int j = 0; j<n; j++)
			{
				/*Aktualis gomb letrehozasa es parameterezese, hozzaadasa*/
				JButton currentButton = new JButton("10");
				currentButton.setSize(new Dimension(50, 50));
				currentButton.setPreferredSize(new Dimension(50, 50));
				board.get(i).add(j, currentButton);
				this.add(board.get(i).get(j));
			}
		}
	}
	
	public void updateDisplay() /*A gombokon megjelenitett kepek frissitese*/
	{
		for(int i = 0;i < n;i++)
		{
			for(int j = 0; j<n; j++)
			{
				JButton currentButton = board.get(i).get(j);
				int val = getDisplayFieldValue(i,j);
				try /*Kep betoltese*/
				{
					Icon ico;
					if(val == -1) ico = new ImageIcon(getClass().getResource("/mine.png"));
					else if(val == 0) ico = new ImageIcon(getClass().getResource("/empty.png"));
					else if(val == 1) ico = new ImageIcon(getClass().getResource("/n1.png"));
					else if(val == 2) ico = new ImageIcon(getClass().getResource("/n2.png"));
					else if(val == 3) ico = new ImageIcon(getClass().getResource("/n3.png"));
					else if(val == 4) ico = new ImageIcon(getClass().getResource("/n4.png"));
					else if(val == 5) ico = new ImageIcon(getClass().getResource("/n5.png"));
					else if(val == 6) ico = new ImageIcon(getClass().getResource("/n6.png"));
					else if(val == 7) ico = new ImageIcon(getClass().getResource("/n7.png"));
					else if(val == 8) ico = new ImageIcon(getClass().getResource("/n8.png"));
					else if(val == 9) ico = new ImageIcon(getClass().getResource("/flag.png"));
					else ico = new ImageIcon(getClass().getResource("/cover.png"));
					
					JLabel iLabel = new JLabel(ico);
					iLabel.setPreferredSize(new Dimension(50, 50));
					currentButton.setMargin(new Insets(0, 0, 0, 0));
					currentButton.removeAll();
					currentButton.add(iLabel);
				}
				catch(Exception e) /*Helyettesito szoveg*/
				{
					String ico;
					if(val == -1) ico = "¤";
					else if(val == 0) ico = "0";
					else if(val == 1) ico = "1";
					else if(val == 2) ico = "2";
					else if(val == 3) ico = "3";
					else if(val == 4) ico = "4";
					else if(val == 5) ico = "5";
					else if(val == 6) ico = "6";
					else if(val == 7) ico = "7";
					else if(val == 8) ico = "8";
					else if(val == 9) ico = "F";
					else ico = "?";
					
					currentButton.removeAll();
					currentButton.setText(ico);
				}
			}
		}
	}	
	
	/*Az alabbi getter/setter fuggvenyeket nem ismertetnem reszletesebben, kiveve nehanyat*/
	
	public List<List<JButton>> getBoard()
	{
		return board;
	}
	
	public int getN()
	{
		return n;
	}
	
	public int getDisplayFieldValue(int x, int y)
	{
		return displayField.get(x).get(y);
	}
	
	public boolean isMarker()
	{
		return isMarkerActive;
	}
	
	public void toggleMarker() /*Akciovaltas*/
	{
		if(isMarkerActive) isMarkerActive = false;
		else isMarkerActive = true;
	}
	
	public int getMinesMarked() /*Megmondja hany akna felett van jelolo*/
	{
		int x = 0;
		for(int i = 0;i < n;i++)
		{
			for(int j = 0; j<n; j++)
			{
				if(getDisplayFieldValue(i,j) == 9 && minefield.getFieldValue(i,j) == -1) x++;
			}
		}
		return x;
	}
	
	public String getDiff() /*Szovegesen reprezentalja a nehezsegi szintet*/
	{
		if(n == 6 && k == 10) return "Konnyu";
		else if(n == 8 && k == 18) return "Kozepes";
		else if(n == 10 && k == 25) return "Nehez";
		else return "Egyedi";
	}
	
	public boolean isFirstClick()
	{
		return firstClick;
	}
	
	public void setFirstClick(boolean a)
	{
		firstClick = a;
	}
	
	public boolean isGameOver()
	{
		if(gameOver) return true;
		else return false;
	}
	
	public void setGameWon(boolean a)
	{
		GameWon = a;
	}
	
	public String getPlayerName()
	{
		return frame.getPlayerName();
	}
	
	public void setGameOver(boolean a)
	{
		gameOver = a;
	}
	
	public GameFrame getFrame()
	{
		return frame;
	}
	
	public int getK()
	{
		return k;
	}
	
	public int getMarkedCells()
	{
		return markedCells;
	}
	
	public boolean isGameWon()
	{
		return GameWon;
	}
	
	public StatusBar getBar()
	{
		return bar;
	}
}

package aknakereso;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class StatusBar extends JPanel /*A jatek feletti statuszsavot felepito es kezelo osztaly*/
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3859529662715100658L;
	private JTextField minesLeft;
	private JTextField displayTimer;
	private JButton toggleMarker;
	private GameInstance Game;
	private Timer timer;
	int mines;
	
	public StatusBar(GameInstance g)
	{
		/*Kulso objektumokbol szarmazo attributumok inicializalasa*/
		
		Game = g;
		mines = Game.getK();
		
		/*Komponensek leterehozasa es parameterezese*/
		
		toggleMarker = new JButton();
		toggleMarker.addActionListener(new ToggleButtonListener());
		toggleMarker.setSize(new Dimension(50,50));
		try /*Kepbetoltes proba*/
		{
			JLabel iLabel = new JLabel(new ImageIcon(getClass().getResource("/mine.png")));
			iLabel.setPreferredSize(new Dimension(50, 50));
			toggleMarker.setMargin(new Insets(0, 0, 0, 0));
			toggleMarker.add(iLabel);
		}
		catch(Exception e) /*Helyettesito szoveg*/
		{
			toggleMarker.setText("Keres");
		}
		JButton newGame = new JButton("Uj jatek");
		newGame.addActionListener(new InstantRestartButtonListener());
		minesLeft = new JTextField(String.valueOf(mines),3);
		Font f = new Font(String.valueOf(minesLeft.getFont()),Font.BOLD, 20);
		minesLeft.setHorizontalAlignment(SwingConstants.RIGHT);
		minesLeft.setEditable(false);
		minesLeft.setBackground(Color.BLACK);
		minesLeft.setForeground(Color.RED);
		minesLeft.setFont(f);
		JLabel spacer1 = new JLabel();
		spacer1.setPreferredSize(new Dimension(10,10));
		JLabel spacer2 = new JLabel();
		spacer2.setPreferredSize(new Dimension(10,10));
		JLabel spacer3 = new JLabel();
		spacer3.setPreferredSize(new Dimension(10,10));
		displayTimer = new JTextField("0",3);
		displayTimer.setEditable(false);
		displayTimer.setHorizontalAlignment(SwingConstants.RIGHT);
		displayTimer.setBackground(Color.BLACK);
		displayTimer.setForeground(Color.RED);
		displayTimer.setFont(f);
		
		/*Statuszsav felepitese*/
		
		this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		this.add(minesLeft);
		this.add(spacer1);
		this.add(newGame);
		this.add(spacer2);
		this.add(toggleMarker);
		this.add(spacer3);
		this.add(displayTimer);
		setBorder(new EmptyBorder(10, 10, 10, 10));
	}
	
	public void startTmr() /*A szamlalo inditasa*/
	{
		timer = new Timer(1000,new TimerListener());
		timer.start();
	}
	
	public void updateMines() /*Fennmarado aknak szamanak frissitese*/
	{
		mines = Game.getK() - Game.getMarkedCells();
		minesLeft.setText(String.valueOf(mines));
	}
	
	public int getTime()
	{
		return Integer.valueOf(displayTimer.getText());
	}
	
	class InstantRestartButtonListener implements ActionListener /*Az uj jatek gomb esemenykezeloje*/
	{
		public void actionPerformed(ActionEvent ae) /*Instant uj jatek kerese*/
		{
				Game.startNew();
		}
	}
	
	class ToggleButtonListener implements ActionListener /*Akciovalto gomb esemenykezeloje*/
	{
		public void actionPerformed(ActionEvent ae)
		{
				if(!Game.isFirstClick())
				{
					Game.toggleMarker(); /*Modvaltas*/
					
					if(Game.isMarker())
					{
						try /*Jelolo modhoz kepbetoltes*/
						{
							JLabel iLabel = new JLabel(new ImageIcon(getClass().getResource("/flag.png")));
							iLabel.setPreferredSize(new Dimension(50, 50));
							toggleMarker.setMargin(new Insets(0, 0, 0, 0));
							toggleMarker.removeAll();
							toggleMarker.add(iLabel);
						}
						catch(Exception e) /*Helyettesito szoveg*/
						{
							toggleMarker.setText("Jelol");
						}
						toggleMarker.setVisible(false);
						toggleMarker.setVisible(true);
					}
					else
					{
						try /*Felderito modhoz kepbetoltes*/
						{
							JLabel iLabel = new JLabel(new ImageIcon(getClass().getResource("/mine.png")));
							iLabel.setPreferredSize(new Dimension(50, 50));
							toggleMarker.setMargin(new Insets(0, 0, 0, 0));
							toggleMarker.removeAll();
							toggleMarker.add(iLabel);
						}
						catch(Exception e) /*Helyettesito szoveg*/
						{
							toggleMarker.setText("Keres");
						}
						toggleMarker.setVisible(false);
						toggleMarker.setVisible(true);
					}
				}
		}
	}
	
	class TimerListener implements ActionListener /*A szamlalo esemenykezeloje*/
	{
		int time;
		
		public TimerListener() /*Ido inicializalasa*/
		{
			time = 0;
		}
		
		public void actionPerformed(ActionEvent ae) /*Szamlalo noveles*/
		{
			if(!Game.isGameOver())
			{
				time++;
				displayTimer.setText(String.valueOf(time));
			}
		}
	}
}
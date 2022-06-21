package aknakereso;

import java.awt.Font;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GameOverBar extends JPanel /*Jatek vegen statisztikat megjelenito panel*/
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7820668104237871678L;
	private GameInstance game;
	
	public GameOverBar(GameInstance g)
	{
		/*Kulso szarmazasu attributumok inicializalasa*/
		
		game = g;
		int n  = game.getN();
		int k = game.getK();
		
		/*Komponensek letrehozasa es parameterezese*/
		
		JLabel gameOver = new JLabel("JATEK VEGE");
		gameOver.setFont(new Font("",Font.BOLD,20));
		JLabel win;
		if(game.isGameWon()) win = new JLabel("Sikeres jatek");
		else win = new JLabel("Sikertelen jatek");
		JLabel mines = new JLabel("Az aknak szama a mezon: " + k);
		JLabel size = new JLabel("Az aknamezo merete: " + n + "*" + n);
		JLabel diff = new JLabel("Nehezsegi szint: " + game.getDiff());
		JLabel name = new JLabel("Jatekosnev: " + game.getPlayerName());
		JLabel time = new JLabel("A jatekido: " + game.getBar().getTime() + " masodperc");
		JLabel minesMarked = new JLabel("Megjelolt aknak szama: " + game.getMinesMarked());
		JLabel markersUsed = new JLabel("Felhasznalt jelolok szama: " + game.getMarkedCells());
		
		/*Panel felepitese*/
		
		GridLayout l = new GridLayout(9,1);
		this.setLayout(l);
		this.add(gameOver);
		this.add(win);
		this.add(size);
		this.add(mines);
		this.add(name);
		this.add(diff);
		this.add(minesMarked);
		this.add(markersUsed);
		this.add(time);
		setBorder(new EmptyBorder(10, 10, 10, 10));
	}
}

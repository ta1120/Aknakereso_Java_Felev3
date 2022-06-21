package aknakereso;

import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.*;

public class GameFrame extends JFrame /*A programot tartalmazo ablak, ami kezeli a feluleteket*/
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3844975074829849729L;
	private GameInstance currentGame;
	private JPanel GamePanel;
	private JPanel statPanel;
	private String playerName;
	private boolean inGame;
	
	@SuppressWarnings("static-access")
	public GameFrame()
	{
		/*Menusav elemeinek letrehozasa, inicializalasa es parameterezese*/
		
		MenuListener ml = new MenuListener();
		GamePanel = new JPanel(new BorderLayout());
		statPanel = new JPanel();
		JMenuBar menubar = new JMenuBar();
		JMenu newgame = new JMenu("Uj jatek");
		JMenu score = new JMenu("Eredmenyek");
		JMenuItem scoreboard = new JMenuItem("Eredmenytabla megjelenitese");
		scoreboard.setActionCommand("scoreboard");
		scoreboard.addActionListener(ml);
		JMenuItem exitgame = new JMenuItem("Kilepes");
		exitgame.setActionCommand("exit");
		exitgame.addActionListener(ml);
		scoreboard.addActionListener(ml);
		JMenuItem changename = new JMenuItem("Nev megvaltoztatasa");
		changename.setActionCommand("startscr");
		changename.addActionListener(ml);
		JMenu diff = new JMenu("Gyorsinditas");
		JMenuItem custom = new JMenuItem("Palyaszerkeszto");
		custom.setActionCommand("editor");
		custom.addActionListener(ml);
		JMenuItem easy = new JMenuItem("Konnyu 6x6 tabla, 10 akna");
		easy.setActionCommand("easy");
		easy.addActionListener(ml);
		JMenuItem hard = new JMenuItem("Nehez 10x10 tabla, 25 akna");
		hard.setActionCommand("hard");
		hard.addActionListener(ml);
		JMenuItem medium = new JMenuItem("Kozepes 8x8 tabla, 18 akna");
		medium.setActionCommand("medium");
		medium.addActionListener(ml);
		
		/*Egyeb valtozok inicializalasa*/
		
		playerName = "Player";
		inGame = false;
		
		/*Menusav felepitese*/
		
		score.add(scoreboard);
		diff.add(easy);
		diff.add(medium);
		diff.add(hard);
		newgame.add(changename);
		newgame.add(diff);
		newgame.add(custom);
		menubar.add(newgame);
		menubar.add(score);
		menubar.add(exitgame);
		
		/*Ablak felepitese*/
		
		setLayout(new FlowLayout());
		this.add(GamePanel);
		this.setJMenuBar(menubar);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setTitle("Akanakereso");
		this.setResizable(false);
		this.setVisible(true);
		
		/*Kezdokepernyo megjelenitese*/
		
		startScr();
	}
	
	public void setName(String n)
	{
		playerName = n;
	}
	
	public String getPlayerName()
	{
		return playerName;
	}
	
	public void startScr() /*Kezdokepernyo megjelenitese*/
	{
		this.remove(statPanel);
		GamePanel.removeAll();
		StartScreen uj = new StartScreen(this);
		GamePanel.add(uj);
		this.pack();
	}
	
	public void GameOver() /*Jatek vegen szukseges feladatok vegrehajtasa, statisztika megjelenitese*/
	{
		if(inGame)
		{
			saveScore();
			statPanel.removeAll();
			statPanel.add(new GameOverBar(currentGame));
			this.add(statPanel);
			this.pack();
			inGame = false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public void saveScore() /*Jatek eredmenyenek mentese*/
	{
		ScoreInstance uj = new ScoreInstance(currentGame);
		ScoresData sData;
		try {
            
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("scoreBoard.data"));
            sData = new ScoresData((ArrayList<ScoreInstance>)ois.readObject());
            ois.close();
        }
		catch(Exception ex)
		{
			sData = new ScoresData(new ArrayList<ScoreInstance>());
        }
		sData.addNew(uj);
		try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("scoreBoard.data"));
            oos.writeObject(sData.getResults());
            oos.close();
        }
		catch(Exception ex)
		{
            System.err.println("Sikertelen adatmentes.");
        }
	}
	
	public void Editor() /*Palyaszerkeszto megjeléenitese*/
	{
		this.remove(statPanel);
		GamePanel.removeAll();
		Editor uj = new Editor(this);
		GamePanel.add(uj);
		this.pack();
		
	}
	
	public void newGame(int n,int k)/*Uj jatek inditasa, (n,k) parameterekkel*/
	{
		inGame = true;
		this.remove(statPanel);
		GamePanel.removeAll();
		currentGame = new GameInstance(n,k,this);
		GamePanel.add(currentGame.getBar(),BorderLayout.NORTH);
		GamePanel.add(currentGame,BorderLayout.CENTER);
		this.pack();
	}
	
	public void showScoreBoard() /*Eredmenytabla megjelenitese*/
	{
		this.remove(statPanel);
		GamePanel.removeAll();
		GamePanel.add(new ScoreBoard());
		this.pack();
	}
	
	public class MenuListener implements ActionListener /*Menu esemenykezelo*/
	{
		public void actionPerformed(ActionEvent e)
		{
			String c = e.getActionCommand();
			if(c.equals("exit")) System.exit(0);
			else if(c.equals("easy")) newGame(6,10);
			else if(c.equals("medium")) newGame(8,18);
			else if(c.equals("hard")) newGame(10,25);
			else if(c.equals("startscr")) startScr();
			else if(c.equals("editor")) Editor();
			else if(c.equals("scoreboard")) showScoreBoard();
		}
	}
}

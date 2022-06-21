package aknakereso;

import java.io.Serializable;

public class ScoreInstance implements Serializable /*Egy jatekmenet eredmenyet tarolja*/
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4566797294149626739L;
	private String name;
	private String diff;
	private int time;
	private int n;
	private int k;
	private String won;
	private int marked;
	
	public ScoreInstance(GameInstance game) /*Ertekek kiolvasasa a kapott jatekmentbol*/
	{
		name = game.getFrame().getPlayerName();
		diff = game.getDiff();
		time = game.getBar().getTime();
		n = game.getN();
		k = game.getK();
		won = game.isGameWon()? "Igen" : "Nem";
		marked = game.getMinesMarked();
	}
	
	/*Egyszeru getter fuggvenyek*/
	
	public String getName()
	{
		return name;
	}
	
	public String getDiff()
	{
		return diff;
	}
	
	public int getTime()
	{
		return time;
	}
	
	public int getN()
	{
		return n;
	}
	
	public int getK()
	{
		return k;
	}
	
	public int getMarked()
	{
		return marked;
	}
	
	public String getWon()
	{
		return won;
	}
}

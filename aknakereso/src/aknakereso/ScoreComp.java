package aknakereso;

import java.util.Comparator;

public class ScoreComp implements Comparator<ScoreInstance> /*Ket eredmeny osszehasonlitasa*/
{
	
		public int compare(ScoreInstance a, ScoreInstance b)
		{
			if(a.getWon().equals("Igen") && b.getWon().equals("Nem")) return -1;
			if(a.getWon().equals("Nem") && b.getWon().equals("Igen")) return 1;
			if(a.getN() > b.getN()) return -1;
			if(a.getN() < b.getN()) return 1;
			if(a.getK() > b.getK()) return -1;
			if(a.getK() < b.getK()) return 1;
			if(a.getMarked() > b.getMarked()) return -1;
			if(a.getMarked() < b.getMarked()) return 1;
			if(a.getTime() > b.getTime()) return 1;
			if(a.getTime() < b.getTime()) return -1;
			else return 0;
		}
}

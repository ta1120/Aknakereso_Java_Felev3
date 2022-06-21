package aknakereso;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Minefield /*Aknamezot tarolo es kezelo osztaly*/
{

	private int n;
	private int k;
	private List<List<Integer>> field;
	
	public void placeMines(int a, int b) /*Aknak elhelyezese, a,b koordinataju mezo es szomszedjain kivul*/
	{
		Random r = new Random();
		for(int m = 0;m<k;m++)
		{
			int x = r.nextInt(n);
			int y = r.nextInt(n);
			while(field.get(x).get(y) == -1 || (a-1 <= x && a+1 >= x) && (b-1 <= y && b+1 >= y)) /*Mezo ellenorzese, nem lehet mar akna, vagy a parameterkent kapott mezo vagy kornyezete*/
			{
				x = r.nextInt(n);
				y = r.nextInt(n);
			}
			field.get(x).set(y,-1);
		}
	}
	
	public boolean checkIndex(int a) /*Koordinata valossganak ellenorzese (legalabb 0, kisebb mint n)*/
	{
		if(a >= 0 && a < n) return true;
		else return false;
	}
	
	public void updateBoardValues() /*Az aknamezo mezoinek ertekenek kiszamolasa*/
	{
		for(int x = 0;x<n;x++)
		{
			for(int y = 0;y<n;y++)
			{
				if(field.get(x).get(y) != -1)
				{
					int count = 0;
					for(int a = x-1;a <= x+1;a++)
					{
						for(int b = y-1;b <= y+1;b++)
						{
							if(checkIndex(a) && checkIndex(b))
							{
								if(field.get(a).get(b) == -1) count++;
							}
						}
					}
					field.get(x).set(y,count);
				}
			}
		}
	}
	
	public void createStructure(int meret,int akna) /*Adatstruktura es valtozok inicializalasa*/
	{
		n = meret;
		k = akna;
		field = new ArrayList<List<Integer>>();
		for(int i = 0;i < n;i++)
		{
			field.add(i, new ArrayList<Integer>());
			for(int j = 0; j<n; j++)
			{
				field.get(i).add(j,0);
			}
		}
	}
	
	public Minefield(int a, int b, int x, int y) /*A konstruktor megkapja a meretet, az aknak szamat, es a kezdo mezot*/
	{
		createStructure(a,b);
		placeMines(x,y);
	}
	
	public int getFieldValue(int x, int y) /*Adott koordinataju mezo ertekenek visszadasa*/
	{
		return field.get(x).get(y);
	}
}

package aknakereso;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ScoresData extends AbstractTableModel /*Eredmenyeket tarolo es kezelo struktura, tablazat jellegu modellezeseel*/
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2467829653738756499L;
	private ArrayList<ScoreInstance> results;

	public void addNew(ScoreInstance s) /*Uj eredmeny felvetele*/
	{
		results.add(s);
		this.fireTableDataChanged();
	}
	
	public void sortScores() /*Eredmenyhalmaz sorbarendezese*/
	{
		results.sort(new ScoreComp());
	}
	
	public ScoresData(ArrayList<ScoreInstance> s) /*Egyszeru eredmenytombbol hoz letre ScoresData tipust*/
	{
		results = s;
	}
	
	public ArrayList<ScoreInstance> getResults()
	{
		return results;
	}
	
	public String getColumnName(int column) /*A tablazatmodellhez visszaadja az oszlopneveket*/
    {
    	if (column == 0) return "Nev";
    	else if (column == 2) return "Nehezseg";
    	else if (column == 3) return "Jatekido";
    	else if (column == 4) return "Palya";
    	else if (column == 5) return "Aknak";
    	else if (column == 6) return "Eltalalt";
    	else return "Nyert";
    }
	
	public int getRowCount() /*Visszaadja a sorok szamat*/
	{
		return results.size();
	}

	public int getColumnCount() /*Visszadaja az oszlopok szamat*/
	{
		return 7;
	}

	public Object getValueAt(int rowIndex, int columnIndex) /*Visszaadja egy cella erteket*/
	{
		if(columnIndex == 0) return results.get(rowIndex).getName();
		else if(columnIndex == 2) return results.get(rowIndex).getDiff();
		else if(columnIndex == 3) return results.get(rowIndex).getTime() + " s";
		else if(columnIndex == 4) return results.get(rowIndex).getN() + "*" +results.get(rowIndex).getN();
		else if(columnIndex == 5) return results.get(rowIndex).getK();
		else if(columnIndex == 6) return results.get(rowIndex).getMarked();
		else  return results.get(rowIndex).getWon();
	}
}

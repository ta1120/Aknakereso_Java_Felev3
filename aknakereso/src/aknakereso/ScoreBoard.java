package aknakereso;

import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ScoreBoard extends JPanel /*Eredmenytabla panelja*/
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5116974796702111603L;
	private ScoresData sData;
	
	@SuppressWarnings("unchecked")
	public ScoreBoard()
	{
		boolean ReadSuccess = false;
		try /*Eredmenyfajl beolvasasa*/
		{    
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("scoreBoard.data"));
            sData = new ScoresData((ArrayList<ScoreInstance>)ois.readObject());
            ois.close();
            ReadSuccess = true;
        }
		catch(Exception ex) /*Felhasznalo ertesitese a sikertelensegrol*/
		{
            this.add(new JLabel("Nem talalhato, vagy hibas az eredmenyfajl."));
            this.setBorder(new EmptyBorder(30,30,30,30));
        }
		if(ReadSuccess) /*Siker eseten eredmenyek rendezese es megjelenitese*/
		{
			sData.sortScores();
			JTable table = new JTable(sData);
	        table.setFillsViewportHeight(true);
	        JScrollPane sPane = new JScrollPane(table);
	        this.add(sPane);
		}
	}
}

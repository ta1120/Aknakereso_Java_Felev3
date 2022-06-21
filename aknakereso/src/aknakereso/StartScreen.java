package aknakereso;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;

public class StartScreen extends JPanel /*Kezdokepernyo panelja*/
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4833206919101689030L;
	private JTextField nevMezo;
	private JButton start;
	private JComboBox<String> diffBox;
	private GameFrame frame;
	
	public StartScreen(GameFrame f)
	{
		frame = f; /*Jatekmenet regisztralasa*/
		
		/*Komponensek letrehozasa es parameterezese*/
		
		JLabel title = new JLabel("Uj jatek inditasahoz add meg a kovetkezo parametereket:");
		JLabel nameLabel = new JLabel("Add meg a neved:");
		JLabel diffLabel = new JLabel("Valassz nehezsegi szintet:");
		nevMezo = new JTextField("Player",20);
		start = new JButton("Jatek inditasa");
		String[] lev = {"Nehez","Kozepes","Konnyu","Egyedi"};
		diffBox = new JComboBox<String>(lev);
		start.addActionListener(new StartScrButtonListener());
		JPanel nevp = new JPanel();
		JPanel diffp = new JPanel();
		
		/*Panel felepitese*/
		
		this.setLayout(new BorderLayout());
		nevp.add(nameLabel);
		nevp.add(nevMezo);
		diffp.add(diffLabel);
		diffp.add(diffBox);
		this.add(title,BorderLayout.NORTH);
		this.add(nevp,BorderLayout.CENTER);
		this.add(diffp,BorderLayout.SOUTH);
		this.add(start,BorderLayout.EAST);
		setBorder(new EmptyBorder(10, 10, 10, 10));
	}
	
	class StartScrButtonListener implements ActionListener /*Az uj jatek gomb esemenykezeloje*/
	{
		
		public void actionPerformed(ActionEvent ae) 
		{
			if(!nevMezo.getText().equals(""))
			{
				String sel = (String) diffBox.getSelectedItem();
				frame.setName(nevMezo.getText());
				if(sel.equals("Konnyu"))
				{
					frame.newGame(6, 10);
				}
				else if(sel.equals("Egyedi"))
				{
					frame.Editor();
				}
				else if(sel.equals("Kozepes"))
				{
					frame.newGame(8, 18);
				}
				else
				{
					frame.newGame(10, 25);
				}
			}
		}
	}
}
package aknakereso;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Editor extends JPanel /*A palyaszerkesztot felepito es kezelo osztaly*/
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2857395980721368483L;
	GameFrame frame;
	JSlider sizeSlider;
	JSlider densitySlider;
	
	public Editor(GameFrame f)
	{
		/*Ablak regisztralasa*/
		
		frame = f;
		
		/*Komponensek letrehozasa, parameterezese*/
		
		JLabel title = new JLabel("Allitsa be a kivant parametereket:");
		JLabel densityLabel = new JLabel("Aknak surusege a mezon (%):");
		JLabel sizeLabel = new JLabel("A mezo merete (n*n):");
		JButton start = new JButton("Jatek inditasa");
		sizeSlider = new JSlider(5,15);
		densitySlider = new JSlider(15,60);
		sizeSlider.setPaintTrack(true); 
        sizeSlider.setPaintTicks(true); 
        sizeSlider.setPaintLabels(true);  
        sizeSlider.setMajorTickSpacing(5); 
        sizeSlider.setMinorTickSpacing(1);
        JPanel densityPanel = new JPanel();
        JPanel sizePanel = new JPanel();
        
        
        /*Felulet felepitese*/
        
        sizePanel.add(sizeLabel);
        sizePanel.add(sizeSlider);
        densityPanel.add(densityLabel);
        densityPanel.add(densitySlider);
        this.setLayout(new BorderLayout());
        this.add(title, BorderLayout.NORTH);
        this.add(sizePanel,BorderLayout.CENTER);
        this.add(densityPanel,BorderLayout.SOUTH);
        this.add(start,BorderLayout.EAST);
        start.addActionListener(new EditorButtonListener());
        sizeSlider.addChangeListener(new SliderListener());
        setBorder(new EmptyBorder(10, 10, 10, 10));
	}
	
	class EditorButtonListener implements ActionListener /*Inditogomb esemenykezeloje*/
	{
		public void actionPerformed(ActionEvent ae) /*(n,k) szamitasa, utana jatekinditas*/
		{
				int n = sizeSlider.getValue();
				int k = densitySlider.getValue();
				double x = (double)k / (double)100;
				k = (int)(n*n*x);
				
				frame.newGame(n, k);
		}
	}
	
	class SliderListener implements ChangeListener /*Csuszka esemenykezelo*/
	{
		public void stateChanged(ChangeEvent e) /*A beallitott meret fuggvenyben kiszamolja az aknak aranyanak felso korlatjat, arra allitja a csuszka maximumat*/
		{
			int n = sizeSlider.getValue();
			int maxK = n*n-10;
			densitySlider.setMaximum((int)((double)maxK/(double)(n*n)*100));
		}
	}
}


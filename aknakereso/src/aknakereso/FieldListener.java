package aknakereso;

import java.awt.event.*;
import java.util.List;
import javax.swing.*;

public class FieldListener implements ActionListener /*A palya esemenykezeloje*/
{
	
	GameInstance currentGame;
	List<List<JButton>> board;
	
	FieldListener(GameInstance game) /*Az esemenykezelo letrejottekor a jatek osszes gombjanak beallitja az esemenykezelojet sajat magara*/
	{
		currentGame = game;
		board = currentGame.getBoard();
		for(int i=0;i<currentGame.getN();i++)
		{
			for(int j=0;j<currentGame.getN();j++)
			{
				board.get(i).get(j).addActionListener(this);
			}
		}
	}
	    
	public void actionPerformed(ActionEvent e)
	{
		if(!currentGame.isGameOver()) /*Meghatarozza a megnyomott gombok koordinatajat*/
		{
			int x = 0;
			int y = 0;
			for(int i=0;i<currentGame.getN();i++)
			{
				for(int j=0;j<currentGame.getN();j++)
				{
					if(board.get(i).get(j) == e.getSource())
					{
						x = i;
						y = j;
					}
				}
			}
			if(currentGame.isFirstClick() && !currentGame.isMarker()) /*Elinditja a jatekot*/
			{
				currentGame.createMinefield(x, y);
				currentGame.setFirstClick(false);
				currentGame.getBar().startTmr();
			}
			else if(currentGame.isMarker() && !currentGame.isFirstClick()) /*Megjeloli a cellat*/
			{
				currentGame.markCell(x,y);
			}
			if(!currentGame.isMarker()) /*Feloldja a mezot*/
			{
				currentGame.uncover(x,y);
			}
		}	
	}
}
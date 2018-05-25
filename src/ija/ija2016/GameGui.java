/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ija.ija2016.proj;

import ija.ija2016.*;
import ija.ija2016.homework1.cardpack.*;
import ija.ija2016.homework2.model.board.*;
import ija.ija2016.homework2.model.cards.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.*;






public class GameGui extends JPanel {
	private Game game;
	private int nr;			// index hry v JFrame


	public GameGui(int cnt) throws IOException{

		nr = cnt;
		game = new Game();
        System.out.println("GameGui constructor");
		setMinimumSize(new Dimension(700,500));
		setBackground(Color.BLUE);
		setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.BLACK));
		setLayout(new GridBagLayout());
        System.out.println(game.toString());


		drawGui();


	}



	public int getNr(){
		return nr;
	}




	public void drawGui() throws IOException{
		System.out.println("drawig... ");
		this.removeAll();
		this.repaint();
		this.revalidate();

		if(!game.getDeck().isEmpty())
			drawCard(game.getDeck().get(), 0, 0, 0);

		CardStack waste = game.getWaste();
		if(!waste.isEmpty())
			for (int i = waste.size()-1 ; i>=0;i--) {
				drawCard(waste.get(i), 1, 0, 0);

			}
			


		CardDeck foundation;
		for(int i=0;i<4;i++){
			foundation = game.getFoundation(i);
			if(!foundation.isEmpty())
				drawCard(foundation.get(), 3+i , 0, 0);
		}


		CardStack stack,base;
		for (int i=0;i<7;i++) {

			stack = game.getStack(i);
			base = game.getBase(i);
			if(!stack.isEmpty())
				for (int j = stack.size() - 1; j >= 0 ; j-- )
					drawCard(stack.get(j), i, 1, base.size() + j);
			if(!base.isEmpty())
				for (int j = base.size() - 1 ; j >= 0 ; j-- )
					drawCard(base.get(j), i, 1,  j);
		}


		for (int i=0;i<7;i++)
			drawInterface(i,1);
		for (int i=3;i<7;i++)
			drawInterface(i,0);
		drawInterface(0,0);
		drawInterface(1,0);


	}


	public Game getGame(){
		return game;
	}


	public void drawInterface(int posx, int posy){
		JLabel label = new JLabel();
		label.setName(posx + ":" + posy + ":" + "0:0");
		label.setMinimumSize(new Dimension(78,106));
		label.setPreferredSize(new Dimension(78,106));
		label.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.GREEN));

		GridBagConstraints c = new GridBagConstraints();

		c.insets = new Insets(2,2,2,2);
		if(posy == 0)
			c.weighty = 0.15;
		else
			c.weighty = 1.0;
		c.weightx = 0.5;

		c.gridx = posx;
		c.gridy = posy;
		c.anchor = GridBagConstraints.PAGE_START;
		add(label,c);
	}




	public void drawCard(Card k, int posx, int posy, int offset) throws IOException{
	
		BufferedImage pic;
		JLabel label;

		if(k.isTurnedFaceUp())
			pic = ImageIO.read(new File("./src/ija/ija2016/cards/"+k.toString()+".png"));
		else
			pic = ImageIO.read(new File("./src/ija/ija2016/cards/rub.png"));


		label = new JLabel(new ImageIcon(pic));
		label.setName(posx + ":" + posy + ":" + k.value() + ":" + k.color());
		GridBagConstraints c = new GridBagConstraints();

		c.insets = new Insets(2 + 26*offset,2,2,2);
		if(posy == 0)
			c.weighty = 0.15;
		else
			c.weighty = 1.0;
		c.weightx = 0.5;

		c.gridx = posx;
		c.gridy = posy;
		c.anchor = GridBagConstraints.PAGE_START;
		add(label,c);

	}


}
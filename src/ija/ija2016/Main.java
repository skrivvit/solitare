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


/**
 *
 * @author Vaclav Dokoupil, xdokou12
 */
public class Main {

    public static void main(String [] args)throws IOException{

    	new Okno();
/*
        JFrame okno = new JFrame("Solitare - Master");
        okno.setMinimumSize(new Dimension(600, 500));

		//okno.getContentPane().setLayout(new GridLayout(2,2));
		

		GameGui jp = new GameGui();


		jp.addMouseListener(new MouseAdapter() { 
          @Override
          public void mousePressed(MouseEvent e) { 
            Component com = jp.findComponentAt(e.getX(), e.getY());
            if (!(com instanceof JLabel) || com == jp) return;
            System.out.println("click, "+com.getName());

            String str[] = com.getName().split(":");  //ze jmena vezme souradnice
            int x = Integer.parseInt(str[0]);
            int y = Integer.parseInt(str[1]);
            Card.Color col = null;
			System.out.println("---"+str[3]+"---");
            if(str[3].equals("C"))
	            col = Card.Color.CLUBS;
	        if(str[3].equals("S"))
	            col = Card.Color.SPADES;
	        if(str[3].equals("D"))
	            col = Card.Color.DIAMONDS;
	        if(str[3].equals("H"))
	            col = Card.Color.HEARTS;
	        Card c = null;
	        if(col != null)
            	c = new Karta(col , Integer.parseInt(str[2]));


            jp.getGame().select(x,y,c);
          }

          @Override
          public void mouseReleased(MouseEvent e) { 
            Component com = jp.findComponentAt(e.getX(), e.getY());
            if (!(com instanceof JLabel)){jp.getGame().deselect(); return;}
            System.out.println("unclick, "+com.getName());

            String str[] = com.getName().split(":");  //ze jmena vezme souradnice
            int x = Integer.parseInt(str[0]);
            int y = Integer.parseInt(str[1]);
            Card.Color col = null;
            Card c = null;
            if(str[3].equals("C"))
	            col = Card.Color.CLUBS;
	        if(str[3].equals("S"))
	            col = Card.Color.SPADES;
	        if(str[3].equals("D"))
	            col = Card.Color.DIAMONDS;
	        if(str[3].equals("H"))
	            col = Card.Color.HEARTS;
            if(col != null)
            	c = new Karta(col , Integer.parseInt(str[2]));
            jp.getGame().transfer(x,y,c);
            if(jp.getGame().checkVictory())
            	System.out.println("cg nigga, you own!");

            try{ jp.drawGui();}catch(IOException ex){

            }
            okno.repaint();
          }

        });      

		okno.getContentPane().add(jp);
		okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		okno.setVisible(true);
		okno.pack();
		*/

    }
}

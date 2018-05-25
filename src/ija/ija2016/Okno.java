

package ija.ija2016.proj;

import ija.ija2016.*;
import ija.ija2016.homework1.cardpack.*;
import ija.ija2016.homework2.model.board.*;
import ija.ija2016.homework2.model.cards.*;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.*;
import java.io.FileReader;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;






public class Okno  implements ActionListener {
	private int cnt;
	private GameGui[] games = new GameGui[4];
	private int focus;
	private JFrame okno;
	AbstractFactorySolitaire factory;
	

	public Okno()throws IOException{
		focus = 0;
		factory = new FactoryKlondike();
		cnt = 0;
		okno = new JFrame("Solitare - Master");
		okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		okno.setVisible(true);
		createMenu();
		newGame();
	}

	private void createMenu()throws IOException{
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem;


		menuBar = new JMenuBar();
		menu = new JMenu("Game");
		menuBar.add(menu);
		menuItem = new JMenuItem("New Game", KeyEvent.VK_T);
		menuItem.setActionCommand("new");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuItem = new JMenuItem("Exit", KeyEvent.VK_T);
		menuItem.setActionCommand("exit");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuItem = new JMenuItem("Undo", KeyEvent.VK_T);
		menuItem.setActionCommand("undo");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuItem = new JMenuItem("Save", KeyEvent.VK_T);
		menuItem.setActionCommand("save");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuItem = new JMenuItem("Load", KeyEvent.VK_T);
		menuItem.setActionCommand("load");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		okno.setJMenuBar(menuBar);
	}
	@Override
	public void actionPerformed(ActionEvent e){
	    if ("new".equals(e.getActionCommand())) {
	    	try{newGame();}catch (IOException x){}
	    }
	    if ("exit".equals(e.getActionCommand()))
	    	System.exit(0);
	    if ("save".equals(e.getActionCommand()))
	    	saveGame();
	    if ("load".equals(e.getActionCommand()))
	    	loadGame();
	    if ("undo".equals(e.getActionCommand())){
	    	games[focus].getGame().undo();
	    	try{games[focus].drawGui();}catch(IOException f){}
		}
	} 
	

	private void saveGame(){

		String saved = games[focus].getGame().toString();
		Path path = Paths.get("examples/saved.txt").toAbsolutePath();
		try{
	        Files.write(path, new byte[0], StandardOpenOption.TRUNCATE_EXISTING);
	        Files.write(path, saved.getBytes(), StandardOpenOption.APPEND);
	    }
	        catch (IOException ex) {
	        	System.out.println("nope");
        }
	}

	private void loadGame(){
		try{
		String saved;
		FileReader fileReader = new FileReader(new File("examples/saved.txt"));
        BufferedReader br = new BufferedReader(fileReader);
        String line;
        if((line = br.readLine()) != null){
        	games[0].getGame().loadGame(line);
        }}
        catch (IOException e){} 
		try{games[focus].drawGui();}catch(IOException e){}   
	}



	public void resetFrame(){
		if(cnt == 1){
			okno.setMinimumSize(new Dimension(700, 600));
			okno.getContentPane().setLayout(new GridLayout(1,1));
			okno.getContentPane().add(games[0]);
		}

		else{
			okno.setMinimumSize(new Dimension(1400, 1200));
			okno.getContentPane().setLayout(new GridLayout(2,2));
			for(int i = 0; i<cnt; i++){
			System.out.println("wutt? " + i);
				okno.getContentPane().add(games[i]);
			}
		}
	}

	public boolean newGame()throws IOException{
		if(cnt == 4)
			return false;
		

		games[cnt] = new GameGui(cnt);
		System.out.println(games[cnt].getNr());
		games[cnt].addMouseListener(new MouseAdapter() { 
			@Override
			public void mouseEntered(MouseEvent e) {
            	Component com = okno.findComponentAt(e.getX(), e.getY());
          		if (com instanceof GameGui){
          			GameGui g = (GameGui)com;
          			focus = g.getNr();

          		}

       			
    		}
          @Override
          public void mousePressed(MouseEvent e) { 
            Component com = games[focus].findComponentAt(e.getX(), e.getY());
            System.out.println("našel jsem " + com.getName());
          	if (!(com instanceof JLabel)) return;

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
            games[focus].getGame().select(x,y,c);
          }


          @Override
          public void mouseReleased(MouseEvent e) { 
            Component com = okno.findComponentAt(e.getX(), e.getY());
            if (!(com instanceof JLabel)){games[focus].getGame().deselect(); return;}
					System.out.println("drawwwww");				

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
            games[focus].getGame().transfer(x,y,c);
            if(games[focus].getGame().checkVictory())
            	System.out.println("cg nigga, you own!");
            try{ games[focus].drawGui();

					System.out.println("drawwwww");				
            }catch(IOException ex){}
            games[focus].getGame().saveUndo();
          }
      	});


		okno.getContentPane().add(games[cnt]);
		cnt++;
		resetFrame();
		return true;
	}

	public void endGame(int nr){
		okno.remove(games[nr]);
		for(int i = nr; i < cnt; i++){
			games[i] = games[i+1];
		}
		cnt--;
		resetFrame();
	}


}
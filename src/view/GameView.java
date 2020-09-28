package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import exceptions.InvalidPowerDirectionException;
import exceptions.InvalidPowerTargetException;
import exceptions.OccupiedCellException;
import exceptions.PowerAlreadyUsedException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Cell;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;
import model.pieces.heroes.ActivatablePowerHero;
import model.pieces.heroes.Armored;
import model.pieces.heroes.Medic;
import model.pieces.heroes.Ranged;
import model.pieces.heroes.Speedster;
import model.pieces.heroes.Super;
import model.pieces.heroes.Tech;
import model.pieces.sidekicks.SideKick;

@SuppressWarnings("serial")
public class GameView extends JFrame implements ActionListener {
	private Game game;
	private ArrayList<JButton> btnsPieces;
	private Player player1;
	private Player player2;
	private JPanel pnlCell;
	private JPanel pnlMove;
	private JPanel payLoadPlayerP1;
	private JPanel payLoadPlayerP2;
	private JPanel KilledCharackter;
	private Piece trigger;
	private Piece target;
	private int payLoadPlayer1;
	private int payLoadPlayer2;
	private Direction d;
	private Boolean ps=false;
	private Point newPo;
	private ArrayList<Game> oldGame;

	public GameView(String player1name, String player2name) {
		setTitle("Heros");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(0, 0, 1000, 1400);
		this.setLayout(new BorderLayout());
		player1=new Player(player1name);
		player2=new Player(player2name);
	 	game = new Game(player1,player2);
		oldGame=new ArrayList<Game>();
	 	payLoadPlayerP1 = new JPanel();
		payLoadPlayerP1.setPreferredSize(new Dimension(100, getHeight()));
   	    payLoadPlayerP1.setLayout(new GridLayout(7,1));
   	    String Playername1=game.getPlayer1().getName()+"'S PayLoad";
   	    JButton Playername =new JButton(Playername1);
   	    Playername.setToolTipText(Playername1);
    	 Playername.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));
   	    Playername.setBackground(Color.white);
    	Playername.setForeground(Color.red);
    	 payLoadPlayerP1.add(Playername);
		add(payLoadPlayerP1,BorderLayout.WEST);		
		payLoadPlayerP1.setBackground(Color.white);
		
		payLoadPlayerP2 = new JPanel();
		payLoadPlayerP2.setPreferredSize(new Dimension(100, getHeight()));
   	    payLoadPlayerP2.setLayout(new GridLayout(7,1));
   	    String Playername2=game.getPlayer2().getName()+"'S PayLoad";
   	    Playername =new JButton(Playername2);
        Playername.setToolTipText(Playername2);
        Playername.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));
     	Playername.setBackground(Color.white);
     	Playername.setForeground(Color.red);
        payLoadPlayerP2.add(Playername);
		add(payLoadPlayerP2,BorderLayout.EAST);
		payLoadPlayerP2.setBackground(Color.white);

		
		    pnlMove = new JPanel();
		    pnlMove.setBackground(Color.WHITE);
		    pnlMove.setLayout(new GridLayout(3,3));
		    setpnlMove();
		    this.add(pnlMove,BorderLayout.NORTH);
		
		
		      
	    pnlCell = new JPanel();
	    setpnlCell();
	    
		
		KilledCharackter=new JPanel();
		KilledCharackter.setPreferredSize(new Dimension(getWidth(), 100));
		KilledCharackter.setLayout(new GridLayout(1,0));
		KilledCharackter.setBackground(Color.black);
		add(KilledCharackter, BorderLayout.SOUTH);
   	    String Playername02=game.getCurrentPlayer().getName()+"'S Turn";
        JButton PlayerNameTurn = new JButton(Playername02);
        PlayerNameTurn.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 26));
        PlayerNameTurn.setBackground(Color.white);
  	    PlayerNameTurn.setForeground(Color.BLACK);
        KilledCharackter.add(PlayerNameTurn);
		this.add(KilledCharackter,BorderLayout.SOUTH);
		this.setVisible(true);

	}	
	public void setpnlMove() {
        pnlMove.removeAll();
        if(ps) {
        	
            JTextArea x = new JTextArea();
            x.setEditable(false);
    	    x.setPreferredSize(new Dimension(40,40));
             x.setBackground(Color.white);
    	    pnlMove.add(x);
    	    
        JButton button1 = new JButton();
	    button1.setIcon(new ImageIcon("up left 1.jpg"));
	    button1.setBackground(Color.white);
	    button1.setPreferredSize(new Dimension(40,40));
	    button1.setActionCommand("UPLEFT");
	    button1.addActionListener(this);
	    pnlMove.add(button1);
	    
	    JButton button2 = new JButton();
	    button2.setBackground(Color.white);
	    button2.setIcon(new ImageIcon("up.jpg"));
	    button2.setPreferredSize(new Dimension(40,40));
	    button2.setActionCommand("UP");
	    button2.addActionListener(this);
	    pnlMove.add(button2);
	    
	    JButton button3 = new JButton();
	    button3.setBackground(Color.white);
	    button3.setIcon(new ImageIcon("up right.jpg"));
	    button3.setPreferredSize(new Dimension(40,40));
	    button3.setActionCommand("UPRIGHT");
	    button3.addActionListener(this);
	    pnlMove.add(button3);
	    
        JTextArea x1 = new JTextArea();
        x1.setEditable(false);
	    x1.setPreferredSize(new Dimension(40,40));
        x1.setBackground(Color.white);
	    pnlMove.add(x1);
	    
	    JButton Back = new JButton();
	    Back.setBackground(Color.white);
	    Back.setPreferredSize(new Dimension(40,40));
	    Back.setActionCommand("BackWard");
	    Back.addActionListener(this);
	    pnlMove.add(Back);
	    
	    JButton button4 = new JButton();
	    button4.setBackground(Color.white);
	    button4.setIcon(new ImageIcon("right.jpg"));
	    button4.setPreferredSize(new Dimension(40,40));
	    button4.setActionCommand("LEFT");
	    button4.addActionListener(this);
	    pnlMove.add(button4);
	    
	    JButton button5 = new JButton();
	    button5.setBackground(Color.white);
	    button5.setIcon(new ImageIcon("activate.jpg"));
	    button5.setPreferredSize(new Dimension(40,40));
	    button5.setActionCommand("Activate");
	    button5.addActionListener(this);
	    pnlMove.add(button5);
	    
	    JButton button6 = new JButton();
	    button6.setBackground(Color.white);
	    button6.setIcon(new ImageIcon("left.jpg"));
	    button6.setActionCommand("RIGHT");
	    button6.setPreferredSize(new Dimension(40,40));
	    button6.addActionListener(this);
	    pnlMove.add(button6);
	    
        JTextArea x3 = new JTextArea();
        x3.setEditable(false);
        x3.setPreferredSize(new Dimension(40,40));
        x3.setBackground(Color.white);
	    pnlMove.add(x3);
	    

        JTextArea x4 = new JTextArea();
        x4.setEditable(false);
	    x4.setPreferredSize(new Dimension(40,40));
        x4.setBackground(Color.white);
	    pnlMove.add(x4);
	    
	    JButton button7 = new JButton();
	    button7.setBackground(Color.white);
	    button7.setIcon(new ImageIcon("down left.jpg"));
	    button7.setPreferredSize(new Dimension(40,40));
	    button7.setActionCommand("DOWNLEFT");
	    button7.addActionListener(this);
	    pnlMove.add(button7);
	    
	    JButton button8 = new JButton();
	    button8.setBackground(Color.white);
	    button8.setIcon(new ImageIcon("down.jpg"));
	    button8.setPreferredSize(new Dimension(40,40));
	    button8.setActionCommand("DOWN");
	    button8.addActionListener(this);
	    pnlMove.add(button8);
	    
	    JButton button9 = new JButton();
	    button9.setBackground(Color.white);
	    button9.setIcon(new ImageIcon("down right.jpg"));
	    button9.setPreferredSize(new Dimension(40,40));
	    button9.setActionCommand("DOWNRIGHT");
	    button9.addActionListener(this);
	    pnlMove.add(button9);
	    
        JTextArea x5 = new JTextArea();
        x5.setEditable(false);
	    x5.setPreferredSize(new Dimension(40,40));
         x5.setBackground(Color.white);
	    pnlMove.add(x5);
	}else {
        JTextArea x = new JTextArea();
        x.setEditable(false);
	    x.setPreferredSize(new Dimension(40,40));;
        x.setBackground(Color.white);
	    pnlMove.add(x);
	    
	    JButton button1 = new JButton();
	    button1.setBackground(Color.WHITE);

	    button1.setIcon(new ImageIcon("up left 1.jpg"));
	    button1.setPreferredSize(new Dimension(40,40));
	    button1.setActionCommand("UPLEFT");
	    button1.addActionListener(this);
	    pnlMove.add(button1);
	    
	    JButton button2 = new JButton();
	    button2.setBackground(Color.WHITE);

	    button2.setIcon(new ImageIcon("up.jpg"));
	    button2.setPreferredSize(new Dimension(40,40));
	    button2.setActionCommand("UP");
	    button2.addActionListener(this);
	    pnlMove.add(button2);
	    
	    JButton button3 = new JButton();
	    button3.setBackground(Color.WHITE);

	    button3.setIcon(new ImageIcon("up right.jpg"));
	    button3.setPreferredSize(new Dimension(40,40));
	    button3.setActionCommand("UPRIGHT");
	    button3.addActionListener(this);
	    pnlMove.add(button3);
	    
        JTextArea x1 = new JTextArea();
        x1.setEditable(false);
	    x1.setPreferredSize(new Dimension(40,40));
        x1.setBackground(Color.white);
	    pnlMove.add(x1);
	    
	    JButton Back = new JButton("BackWard");
	    Back.setBackground(Color.WHITE);

	    Back.setPreferredSize(new Dimension(50,50));
	    Back.setActionCommand("BackWard");
	    Back.addActionListener(this);
	    pnlMove.add(Back);
	    
	    JButton button4 = new JButton();
	    button4.setBackground(Color.WHITE);
	    button4.setIcon(new ImageIcon("right.jpg"));

	    button4.setPreferredSize(new Dimension(40,40));
	    button4.setActionCommand("LEFT");
	    button4.addActionListener(this);
	    pnlMove.add(button4);
	    
	    JButton button5 = new JButton();
	    button5.setBackground(Color.WHITE);
	    button5.setIcon(new ImageIcon("use power.jpg"));
	    button5.setPreferredSize(new Dimension(40,40));
	    button5.setActionCommand("UsePower");
	    button5.addActionListener(this);
	    pnlMove.add(button5);
	    
	    JButton button6 = new JButton();
	    button6.setBackground(Color.WHITE);
	    button6.setIcon(new ImageIcon("left.jpg"));
	    button6.setActionCommand("RIGHT");
	    button6.setPreferredSize(new Dimension(40,40));
	    button6.addActionListener(this);
	    pnlMove.add(button6);
	    
	    
        JTextArea x3 = new JTextArea();
        x3.setEditable(false);
        x3.setPreferredSize(new Dimension(40,40));
        x3.setBackground(Color.white);
	    pnlMove.add(x3);
	    

        JTextArea x4 = new JTextArea();
        x4.setEditable(false);
	    x4.setPreferredSize(new Dimension(40,40));
        x4.setBackground(Color.white);
	    pnlMove.add(x4);

	    JButton button7 = new JButton();
	    button7.setBackground(Color.WHITE);

	    button7.setIcon(new ImageIcon("down left.jpg"));
	    button7.setPreferredSize(new Dimension(40,40));
	    button7.setActionCommand("DOWNLEFT");
	    button7.addActionListener(this);
	    pnlMove.add(button7);
	    
	    JButton button8 = new JButton();
	    button8.setBackground(Color.WHITE);

	    button8.setIcon(new ImageIcon("down.jpg"));
	    button8.setPreferredSize(new Dimension(40,40));
	    button8.setActionCommand("DOWN");
	    button8.addActionListener(this);
	    pnlMove.add(button8);
	    
	    JButton button9 = new JButton();
	    button9.setBackground(Color.WHITE);

	    button9.setIcon(new ImageIcon("down right.jpg"));
	    button9.setPreferredSize(new Dimension(40,40));
	    button9.setActionCommand("DOWNRIGHT");
	    button9.addActionListener(this);
	    pnlMove.add(button9);
	    

        JTextArea x5 = new JTextArea();
        x5.setEditable(false);
	    x5.setPreferredSize(new Dimension(40,40));
        x5.setBackground(Color.white);
	    pnlMove.add(x5);
  
	      this.add(pnlMove,BorderLayout.NORTH);
	}
	}
	
	public void setpnlCell() {
		pnlCell.removeAll();
		pnlCell.setLayout(new GridLayout(0, 6));
		pnlCell.setPreferredSize(new Dimension(100,200));
		add(pnlCell,BorderLayout.CENTER);	
		btnsPieces = new ArrayList<>();
		for(int i=0;i<game.getBoardHeight();i++) {
			for(int j=0 ;j<game.getBoardWidth();j++) {
				final Cell cell=game.getCellAt(i, j);
			JButton btnofCell = new JButton();
				btnofCell.setBackground(Color.white);
				btnsPieces.add(btnofCell);
			if(cell.toString()!="n") {
				if(cell.getPiece() instanceof Super) {	
					Super s=((Super)cell.getPiece());
					String x="";
					if(s.isPowerUsed())
						x="Used";
					else
						x="Available";
					
					String Detials="PieceName:"+cell.getPiece().getName()+" ";
					 Detials+="Type:Super  ";

					Detials+="PlayerName:"+cell.getPiece().getOwner().getName()+"  ";
					Detials+="Power: "+x;
					btnofCell.setToolTipText(Detials);
					btnofCell.setBackground(Color.white);
			        if(game.getCellAt(i, j).getPiece().getOwner()==player1)
			        	btnofCell.setIcon(new ImageIcon("super1.jpg"));
					        else
					        	btnofCell.setIcon(new ImageIcon("Super2.jpg"));	
			        btnofCell.setActionCommand(cell.getPiece().getPosI()+" "+cell.getPiece().getPosJ());
					if(game.getPlayer1().getPayloadPos()==6||game.getPlayer2().getPayloadPos()==6)
					btnofCell.addActionListener(null);
					else
						btnofCell.addActionListener(this);				
				}else {
				if(cell.getPiece() instanceof Armored) {
					Armored s=((Armored)cell.getPiece());
					String x="";
					if(s.isArmorUp())
						x="ArmorUp";
					else
						x="ArmorDown";
					String Detials="PieceName:"+cell.getPiece().getName()+" ";
					 Detials+="Type:Armored  ";
					Detials+="PlayerName:"+cell.getPiece().getOwner().getName()+"  ";
					Detials+="Power: "+x;
					btnofCell.setToolTipText(Detials);
					btnofCell.setBackground(Color.white);
			        if(game.getCellAt(i, j).getPiece().getOwner()==player1)
			        	btnofCell.setIcon(new ImageIcon("Armored.jpg"));	
					        else
					        	btnofCell.setIcon(new ImageIcon("Armored2.jpg"));		
			        btnofCell.setActionCommand(cell.getPiece().getPosI()+" "+cell.getPiece().getPosJ());
					if(game.getPlayer1().getPayloadPos()==6||game.getPlayer2().getPayloadPos()==6)
					btnofCell.addActionListener(null);
					else
						btnofCell.addActionListener(this);				
				}else {
				if(cell.getPiece() instanceof Medic) {
					Medic s=((Medic)cell.getPiece());
					String x="";
					if(s.isPowerUsed())
						x="Used";
					else
						x="Available";
					String Detials="PieceName:"+cell.getPiece().getName()+" ";
					 Detials+="Type:Medic  ";
					Detials+="PlayerName:"+cell.getPiece().getOwner().getName()+"  ";
					Detials+="Power: "+x;
					btnofCell.setToolTipText(Detials);
					btnofCell.setBackground(Color.white);
			        if(game.getCellAt(i, j).getPiece().getOwner()==player1)
			        	btnofCell.setIcon(new ImageIcon("Medic.jpg"));
					        else
					        	btnofCell.setIcon(new ImageIcon("Medic2.jpg"));	
			        btnofCell.setActionCommand(cell.getPiece().getPosI()+" "+cell.getPiece().getPosJ());
					if(game.getPlayer1().getPayloadPos()==6||game.getPlayer2().getPayloadPos()==6)
					btnofCell.addActionListener(null);
					else
						btnofCell.addActionListener(this);
				}else {
				if(cell.getPiece() instanceof Ranged) {
					Ranged s=((Ranged)cell.getPiece());
					String x="";
					if(s.isPowerUsed())
						x="Used";
					else
						x="Available";
					String Detials="PieceName:"+cell.getPiece().getName()+" ";
					 Detials+="Type:Ranged  ";
					Detials+="PlayerName:"+cell.getPiece().getOwner().getName()+"  ";
					Detials+="Power: "+x;
					btnofCell.setToolTipText(Detials);
					btnofCell.setBackground(Color.white);
			        if(game.getCellAt(i, j).getPiece().getOwner()==player1)
						btnofCell.setIcon(new ImageIcon("ranged.jpg"));
					        else
								btnofCell.setIcon(new ImageIcon("Ranged2.jpg"));
				btnofCell.setActionCommand(cell.getPiece().getPosI()+" "+cell.getPiece().getPosJ());
				if(game.getPlayer1().getPayloadPos()==6||game.getPlayer2().getPayloadPos()==6)
				btnofCell.addActionListener(null);
				else
					btnofCell.addActionListener(this);
				}else {
				if(cell.getPiece() instanceof Speedster) {	
					String Detials="PieceName:"+cell.getPiece().getName()+" ";
				 Detials+="Type:Speedster  ";
					Detials+="PlayerName:"+cell.getPiece().getOwner().getName()+"  ";
					btnofCell.setToolTipText(Detials);
					btnofCell.setBackground(Color.white);
			        if(game.getCellAt(i, j).getPiece().getOwner()==player1)
			        	btnofCell.setIcon(new ImageIcon("Speedster1.jpg"));
					        else
					        	btnofCell.setIcon(new ImageIcon("Speedster2.jpg"));
			        btnofCell.setActionCommand(cell.getPiece().getPosI()+" "+cell.getPiece().getPosJ());
					if(game.getPlayer1().getPayloadPos()==6||game.getPlayer2().getPayloadPos()==6)
					btnofCell.addActionListener(null);
					else
						btnofCell.addActionListener(this);
				}else {
				if(cell.getPiece() instanceof Tech) {
					Tech s=((Tech)cell.getPiece());
					String x="";
					if(s.isPowerUsed())
						x="Used";
					else
						x="Available";
					String Detials="PieceName:"+s.getName()+" ";
					 Detials+="Type:Tech  ";
					Detials+="PlayerName:"+cell.getPiece().getOwner().getName()+"  ";
					Detials+="Power: "+x;
					btnofCell.setToolTipText(Detials);
					btnofCell.setBackground(Color.white);
			        if(game.getCellAt(i, j).getPiece().getOwner()==player1)
						btnofCell.setIcon(new ImageIcon("Tech1.jpg"));
					        else
								btnofCell.setIcon(new ImageIcon("Tech2.jpg"));
			        btnofCell.setActionCommand(cell.getPiece().getPosI()+" "+cell.getPiece().getPosJ());
					if(game.getPlayer1().getPayloadPos()==6||game.getPlayer2().getPayloadPos()==6)
					btnofCell.addActionListener(null);
					else
						btnofCell.addActionListener(this);
				}else {
					btnofCell.setBackground(Color.white);
				
					String Detials="PieceName:"+game.getCellAt(i, j).getPiece().getName()+" ";
					 Detials+="Type:Sidekick  ";
					Detials+="PlayerName:"+game.getCellAt(i, j).getPiece().getOwner().getName()+"  ";
					btnofCell.setToolTipText(Detials);
			        if(game.getCellAt(i, j).getPiece()!=null&&game.getCellAt(i, j).getPiece().getOwner()==player1)
						btnofCell.setIcon(new ImageIcon("Sidekick.jpg"));
					        else
								btnofCell.setIcon(new ImageIcon("Sidekick2.jpg"));
			        btnofCell.setActionCommand(cell.getPiece().getPosI()+" "+cell.getPiece().getPosJ());
					if(game.getPlayer1().getPayloadPos()==6||game.getPlayer2().getPayloadPos()==6)
					btnofCell.addActionListener(null);
					else
						btnofCell.addActionListener(this);
				}
				}
				}
				}
				}
				}
			}	else {
				btnofCell.setText("");
		        btnofCell.setActionCommand(i+" "+j);
		        btnofCell.addActionListener(this);
                   }
			pnlCell.setPreferredSize(new Dimension(500,40));
		    pnlCell.add(btnofCell);

			}
		}
	}
	
	public void updateBoard() {
		setpnlCell();
		payLoadPlayerP1.removeAll();
		payLoadPlayerP1.setPreferredSize(new Dimension(100, getHeight()));
   	    payLoadPlayerP1.setLayout(new GridLayout(7,1));
   	    String Playername1=game.getPlayer1().getName()+"'S PayLoad";
   	    JButton Playername =new JButton(Playername1);
   	    Playername.setToolTipText(Playername1);
    	 Playername.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));
   	    Playername.setBackground(Color.white);
    	Playername.setForeground(Color.red);
    	 payLoadPlayerP1.add(Playername);
		add(payLoadPlayerP1,BorderLayout.WEST);		
		payLoadPlayerP1.setBackground(Color.white);
		payLoadPlayer1=0;

       while(payLoadPlayer1!=game.getPlayer1().getPayloadPos()) {
     if(payLoadPlayer1<game.getPlayer1().getPayloadPos()) {
    	 payLoadPlayer1++;			
    	 JButton payLoadButton = new JButton();
    	 payLoadButton.setBackground(Color.RED);
    	 payLoadPlayerP1.add(payLoadButton);
    	 
     }
     }
        payLoadPlayerP2.removeAll();
		payLoadPlayerP2.setPreferredSize(new Dimension(100, getHeight()));
   	    payLoadPlayerP2.setLayout(new GridLayout(7,1));
  	    String Playername2=game.getPlayer2().getName()+"'S PayLoad";
  	    Playername =new JButton(Playername2);
       Playername.setToolTipText(Playername2);
       Playername.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));
    	Playername.setBackground(Color.white);
    	Playername.setForeground(Color.red);
       payLoadPlayerP2.add(Playername);
		add(payLoadPlayerP2,BorderLayout.EAST);
		payLoadPlayerP2.setBackground(Color.white);
		payLoadPlayer2=0;
    while(payLoadPlayer2!=game.getPlayer2().getPayloadPos()) {
     if(payLoadPlayer2<game.getPlayer2().getPayloadPos()) {
    	 payLoadPlayer2++;			
    	 JButton payLoadButton = new JButton();
    	 payLoadButton.setBackground(Color.RED);
    	 payLoadPlayerP2.add(payLoadButton);
     } 
     }
        KilledCharackter.removeAll();
        KilledCharackter.setLayout(new GridLayout(1,0));
   	   Playername2=game.getCurrentPlayer().getName()+"'S Turn";
    	 JButton PlayerNameTurn = new JButton(Playername2);
  	   PlayerNameTurn.setBackground(Color.white);
  	   PlayerNameTurn.setForeground(Color.red);
        KilledCharackter.add(PlayerNameTurn);

        for(int i=0; i<game.getCurrentPlayer().getDeadCharacters().size();i++) {
        	Piece  Dead=game.getCurrentPlayer().getDeadCharacters().get(i);
			JButton btnofCell = new JButton();
			btnofCell.setBackground(Color.white);
			btnofCell.setActionCommand(i+"12");
			KilledCharackter.add(btnofCell);
			if(Dead.toString()=="S"){
				if(Dead.getOwner()==player1) 
		        	btnofCell.setIcon(new ImageIcon("Speedster1.jpg"));
			        else
			        	btnofCell.setIcon(new ImageIcon("Speedster2.jpg"));
				if(game.getPlayer1().getPayloadPos()==6||game.getPlayer2().getPayloadPos()==6)
				btnofCell.addActionListener(null);
				else
					btnofCell.addActionListener(this);			   
			}else {
					if(Dead.toString()=="P"){
						if(Dead.getOwner()==player1) 
				        	btnofCell.setIcon(new ImageIcon("super1.jpg"));
					        else
					        	btnofCell.setIcon(new ImageIcon("Super2.jpg"));
						if(game.getPlayer1().getPayloadPos()==6||game.getPlayer2().getPayloadPos()==6)
						btnofCell.addActionListener(null);
						else
							btnofCell.addActionListener(this);						
					}else {
						if(Dead.toString()=="M"){
							if(Dead.getOwner()==player1) 
					        	btnofCell.setIcon(new ImageIcon("Medic.jpg"));
						        else
						        	btnofCell.setIcon(new ImageIcon("Medic2.jpg"));	
							if(game.getPlayer1().getPayloadPos()==6||game.getPlayer2().getPayloadPos()==6)
							btnofCell.addActionListener(null);
							else
								btnofCell.addActionListener(this);							
						}else {
								if(Dead.toString()=="T") {
									if(Dead.getOwner()==player1) 
										btnofCell.setIcon(new ImageIcon("Tech1.jpg"));
								        else
											btnofCell.setIcon(new ImageIcon("Tech2.jpg"));
									if(game.getPlayer1().getPayloadPos()==6||game.getPlayer2().getPayloadPos()==6)
									btnofCell.addActionListener(null);
									else
										btnofCell.addActionListener(this);									
								}else {
									if(Dead.toString()=="A"){
										if(Dead.getOwner()==player1) 
								        	btnofCell.setIcon(new ImageIcon("Armored.jpg"));	
									        else
									        	btnofCell.setIcon(new ImageIcon("Armored2.jpg"));
										if(game.getPlayer1().getPayloadPos()==6||game.getPlayer2().getPayloadPos()==6)
										btnofCell.addActionListener(null);
										else
											btnofCell.addActionListener(this);
									}else {
											if(Dead.toString()=="R"){
												if(Dead.getOwner()==player1) 
													btnofCell.setIcon(new ImageIcon("ranged.jpg"));
											        else
														btnofCell.setIcon(new ImageIcon("Ranged2.jpg"));
												if(game.getPlayer1().getPayloadPos()==6||game.getPlayer2().getPayloadPos()==6)
												btnofCell.addActionListener(null);
												else
													btnofCell.addActionListener(this);												
											}else {
													if(Dead.toString()=="K"){
														if(Dead.getOwner()==player1) 
															btnofCell.setIcon(new ImageIcon("Sidekick.jpg"));
													        else
																btnofCell.setIcon(new ImageIcon("Sidekick2.jpg"));
														
														if(game.getPlayer1().getPayloadPos()==6||game.getPlayer2().getPayloadPos()==6)
														btnofCell.addActionListener(null);
														else
															btnofCell.addActionListener(this);

													}
					}
											}
					}
								}
        }}
			
        }
        
        if(payLoadPlayer2==6) {
        	new GameOverWindow(player2,this);
        	
        }
        if(payLoadPlayer1==6) {
        	new GameOverWindow(player1,this);
        }
        setpnlMove();
		SwingUtilities.updateComponentTreeUI(this);

	    
	}
	public boolean isDirection(String s) {
		if(s=="UP"||s=="UPRIGHT"||s=="UPLEFT"||s=="DOWN"||s=="DOWNRIGHT"||s=="DOWNLEFT"||s=="RIGHT"||s=="LEFT")
	    return true;
		else
			return false;
	}
	
	public Direction getDirection(String s) {
		if(s=="UP")
	    return Direction.UP;
		if(s=="UPLEFT")
		    return Direction.UPLEFT;
	         
		if(s=="UPRIGHT")
		    return Direction.UPRIGHT;
	         
		if(s=="DOWN")
		    return Direction.DOWN;
	         
		if(s=="DOWNLEFT")
		    return Direction.DOWNLEFT;
	         
		if(s=="DOWNRIGHT")
		    return Direction.DOWNRIGHT;
	         
		if(s=="RIGHT")
		    return Direction.RIGHT;
	         
		if(s=="LEFT")
		    return Direction.LEFT;
		return null;
	         
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		JButton btnCell = (JButton) e.getSource();
		//System.out.println(btnCell.getActionCommand());
		if(btnCell.getActionCommand()=="BackWard") {
			if(!oldGame.isEmpty()) {
				if(game.getPlayer1()==game.getCurrentPlayer()) {
				game=oldGame.remove(oldGame.size()-1);
				player1=game.getPlayer1();
				player2=game.getPlayer2();
				game.setCurrentPlayer(player2);
				}else {
					game=oldGame.remove(oldGame.size()-1);
					player1=game.getPlayer1();
					player2=game.getPlayer2();
				}
				ps=false;
				System.out.println(game);
				updateBoard();	
				trigger=null;
				d=null;
				target=null;
				newPo=null;
				
			}else {
				ps=false;
				updateBoard();
				
				trigger=null;
				d=null;
				target=null;
				newPo=null;
				new ExecptionView("You Are the intial Game");	
			}
			return;
		}
		

		if(!isDirection(btnCell.getActionCommand())&&btnCell.getActionCommand()!="UsePower"&&btnCell.getActionCommand()!="Activate") {
			int i=Integer.parseInt(btnCell.getActionCommand().charAt(0)+"");
			int j=Integer.parseInt(btnCell.getActionCommand().charAt(2)+"");

			if(game.getCellAt(i, j).getPiece()!=null&&!ps) {
			 trigger=game.getCellAt(i, j).getPiece();}
			else {
			if(trigger!=null&&trigger.getName()=="M"&&!btnCell.getActionCommand().isEmpty()) {
				target=game.getCurrentPlayer().getDeadCharacters().get(Integer.parseInt(btnCell.getActionCommand().charAt(0)+""));
			}
			else {
			if(target==null)
			  target=game.getCellAt(i, j).getPiece();
				else
					newPo=new Point(i,j);
			}
			  }
		}else {
			if(isDirection(btnCell.getActionCommand()))
			d=getDirection(btnCell.getActionCommand());
			if(d!=null&&trigger==null) {
				new ExecptionView("Select Piece Y Beh");
			}
			if(btnCell.getActionCommand()=="UsePower") {
				ps=true;	
				updateBoard();
                }
				if(ps) {
			if(btnCell.getActionCommand()=="Activate") {
				ps=false;
				if(trigger instanceof ActivatablePowerHero) {
					try {
						oldGame.add(game.copyGame());
						((ActivatablePowerHero) trigger).usePower(d, target, newPo);
						game=trigger.getGame();
					} catch (PowerAlreadyUsedException | InvalidPowerTargetException | InvalidPowerDirectionException
							| WrongTurnException e1) {
						new ExecptionView(e1.getMessage());
					}finally {
						updateBoard();
						trigger=null;
						d=null;
						target=null;
						newPo=null;
					}
					
					}else {
						new ExecptionView("Non ActivatablePowerHero Rakz");
						trigger=null;
						d=null;
						target=null;
						newPo=null;
						updateBoard();
					}
				}
			}else {
			try {
				if(trigger!=null) {
				oldGame.add(game.copyGame());
				System.out.println(oldGame.get(0));
				trigger.move(d);
				game=trigger.getGame();
				updateBoard();
				}
			} catch (UnallowedMovementException | OccupiedCellException | WrongTurnException e1) {
				new ExecptionView(e1.getMessage());
			}finally {
				trigger=null;
				d=null;
				target=null;
				newPo=null;
				ps=false;
			}
		}
		}
		
		
	}
	public void restartGame() {
		player1=new Player(player1.getName());
		player2=new Player(player2.getName());
		game=new Game(player1,player2);
		payLoadPlayer1=0;
		payLoadPlayer2=0;
		payLoadPlayerP1.removeAll();
	    String Playername1=game.getPlayer1().getName()+"'S PayLoad";
	    JButton Playername =new JButton(Playername1);
	    Playername.setToolTipText(Playername1);
	 
	    Playername.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));
	   	
	    Playername.setBackground(Color.white);
	    
	    Playername.setForeground(Color.red);
	    
	    payLoadPlayerP1.add(Playername);
		
	    add(payLoadPlayerP1,BorderLayout.WEST);		
		
	    payLoadPlayerP1.setBackground(Color.white);
		
	    payLoadPlayerP2.removeAll();
	   	
	    String Playername2=game.getPlayer2().getName()+"'S PayLoad";
	   	
	    Playername =new JButton(Playername2);
	    
	    Playername.setToolTipText(Playername2);
	    
	    Playername.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));
	    
	    Playername.setBackground(Color.white);
	    
	    Playername.setForeground(Color.red);
	    
	    payLoadPlayerP2.add(Playername);
		
	    add(payLoadPlayerP2,BorderLayout.EAST);
		
	    payLoadPlayerP2.setBackground(Color.white);
		
	    KilledCharackter.removeAll();
	    
	    Playername2=game.getCurrentPlayer().getName()+"'S Turn";
	    
	    JButton PlayerNameTurn = new JButton(Playername2);
	  	
	    PlayerNameTurn.setBackground(Color.white);
	  	
	    PlayerNameTurn.setForeground(Color.red);
	    
	    KilledCharackter.add(PlayerNameTurn);
		
	    updateBoard();
		
	}
public Game getGame() {
	return game;
}
}

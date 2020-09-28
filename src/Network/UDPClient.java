package Network;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import Network.GameOverWindow;
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
import view.ExecptionView;

public class UDPClient extends JFrame implements ActionListener {
    private DatagramSocket udpSocket;
    private InetAddress serverAddress;
    private int port;
    private Scanner scanner;
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
	String in="";
	private int c;
    public UDPClient(String destinationAddr, int port) throws IOException {
        this.serverAddress = InetAddress.getByName(destinationAddr);
        this.port = port;
        udpSocket = new DatagramSocket();
setTitle("ClientViewHeros");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(0, 0, 1000, 1400);
		this.setLayout(new BorderLayout());
		player1=new Player("Client");
		player2=new Player("Server");
	 	game = new Game(player1,player2,1);
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
        
    }public void setpnlMove() {
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
	    
	    
	    JButton x3 = new JButton("EndTurn");
	    x3.setBackground(Color.WHITE);
	    x3.setActionCommand("EndTurn");
	    x3.setPreferredSize(new Dimension(40,40));
	    x3.addActionListener(this);
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
	
	public void updateBoard() throws Exception {
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
        
        if(payLoadPlayer1==6) {
        	new GameOverWindow(player1,this,"3la Wad3ak ya ");
        	this.start();
        	
        }
        if(payLoadPlayer2==6) {
        	new GameOverWindow(player1,this,"Hwa 2ly kasb ya looser hhhhh");
        }
        setpnlMove();
		SwingUtilities.updateComponentTreeUI(this);

	    
	}
	public void doData(String s) throws Exception {
		String [] info=s.split(" ");
		if(info[0].equals("M")) {
			Piece p=game.getCellAt(Integer.parseInt(info[1]),Integer.parseInt(info[2])).getPiece();
			Direction d=getDirection(info[3]);
			p.move(d);
			
		}
		else {
			if(info[0].equals("P")) {
				Point newp=null;
			Piece p=game.getCellAt(Integer.parseInt(info[1]),Integer.parseInt(info[2])).getPiece();
			Direction d=getDirection(info[3]);
			Piece p1=null;
			if(info[4].equals("M")) {
			 p1=game.getCurrentPlayer().getDeadCharacters().get(Integer.parseInt(info[5]));
			}
			else {
				if(!info[4].equals("null")) {
					 p1=game.getCellAt(Integer.parseInt(info[4]), Integer.parseInt(info[5])).getPiece();
					}
			}
			if(!info[6].equals("null")) {
				double x=Double.parseDouble(info[6]);
				double y=Double.parseDouble(info[7]);
				newp=new Point((int)x,(int)y);
			}
			((ActivatablePowerHero)p).usePower(d, p1, newp);
			}
		}
		this.updateBoard();
		if(info.length>8) {
		if(info[8].equals("M")) {
			Piece p=game.getCellAt(Integer.parseInt(info[9]),Integer.parseInt(info[10])).getPiece();
			Direction d=getDirection(info[11]);
			p.move(d);
			
		}
		else {
			if(info[8].equals("P")) {
				Point newp=null;
			Piece p=game.getCellAt(Integer.parseInt(info[9]),Integer.parseInt(info[10])).getPiece();
			Direction d=getDirection(info[11]);
			Piece p1=null;
			if(info[12].equals("M")) {
			 p1=game.getCurrentPlayer().getDeadCharacters().get(Integer.parseInt(info[13]));
			}
			else {
				if(!info[12].equals("null")) {
					 p1=game.getCellAt(Integer.parseInt(info[12]), Integer.parseInt(info[13])).getPiece();
					}
			}
			if(!info[14].equals("null")) {
				newp=new Point(Integer.parseInt(info[14]),Integer.parseInt(info[15]));
			}
			((ActivatablePowerHero)p).usePower(d, p1, newp);
			}
		}
		this.updateBoard();
		}
		
	}
	public boolean isDirection(String s) {
		if(s=="UP"||s=="UPRIGHT"||s=="UPLEFT"||s=="DOWN"||s=="DOWNRIGHT"||s=="DOWNLEFT"||s=="RIGHT"||s=="LEFT")
	    return true;
		else
			return false;
	}
	
	public Direction getDirection(String s) {
		if(s.equalsIgnoreCase("UP"))
	    return Direction.UP;
		if(s.equalsIgnoreCase("UPLEFT"))
		    return Direction.UPLEFT;
	         
		if(s.equalsIgnoreCase("UPRIGHT"))
		    return Direction.UPRIGHT;
	         
		if(s.equalsIgnoreCase("DOWN"))
		    return Direction.DOWN;
	         
		if(s.equalsIgnoreCase("DOWNLEFT"))
		    return Direction.DOWNLEFT;
	         
		if(s.equalsIgnoreCase("DOWNRIGHT"))
		    return Direction.DOWNRIGHT;
	         
		if(s.equalsIgnoreCase("RIGHT"))
		    return Direction.RIGHT;
	         
		if(s.equalsIgnoreCase("LEFT"))
		    return Direction.LEFT;
		return null;
	         
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		JButton btnCell = (JButton) e.getSource();
		if(btnCell.getActionCommand().equalsIgnoreCase("EndTurn")) {
			try {
				this.start();
				in="";
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}else {
		if(!isDirection(btnCell.getActionCommand())&&btnCell.getActionCommand()!="UsePower"&&btnCell.getActionCommand()!="Activate"&&btnCell.getActionCommand().charAt(0)+""!="E") {
			int i=Integer.parseInt(btnCell.getActionCommand().charAt(0)+"");
			int j=Integer.parseInt(btnCell.getActionCommand().charAt(2)+"");

			if(game.getCellAt(i, j).getPiece()!=null&&!ps) {
			 trigger=game.getCellAt(i, j).getPiece();}
			else {
			if(trigger!=null&&trigger instanceof Medic&&!btnCell.getActionCommand().isEmpty()) {
				c=Integer.parseInt(btnCell.getActionCommand().charAt(0)+"");
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
				in="";
			}
			if(btnCell.getActionCommand()=="UsePower") {
				ps=true;	
				try {
					updateBoard();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                }
				if(ps) {
			if(btnCell.getActionCommand()=="Activate") {
				ps=false;
				if(trigger instanceof ActivatablePowerHero) {
					try {
						if(d!=null)
						in+="P"+" "+trigger.getPosI()+" "+trigger.getPosJ()+" "+d;
						else
							in+="P"+" "+trigger.getPosI()+" "+trigger.getPosJ()+" "+"null";
						if(trigger instanceof Medic)
							in+=" "+"M"+" "+c;
						else
							if(trigger instanceof Tech)
							in+=" "+target.getPosI()+" "+target.getPosJ();
							else
								in+=" "+"null"+" "+"null";
						if(newPo!=null)
							in+=" "+newPo.getX()+" "+newPo.getY()+" ";
						else
							in+=" "+"null"+" "+"null"+" ";
						((ActivatablePowerHero) trigger).usePower(d, target, newPo);
						game=trigger.getGame();

					} catch (PowerAlreadyUsedException | InvalidPowerTargetException | InvalidPowerDirectionException
							| WrongTurnException e1) {
						try {
							this.updateBoard();
						} catch (Exception e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						new ExecptionView(e1.getMessage());
						in="";
					}finally {
						try {
							if(trigger instanceof Tech)
							updateBoard();
							else
								updatedisable();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
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
						try {
							updateBoard();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}else {
			try {
				if(trigger!=null) {
				in+="M"+" "+trigger.getPosI()+" "+trigger.getPosJ()+" "+d;
				trigger.move(d);
				game=trigger.getGame();
				updatedisable();
				
				
				}
			} catch (UnallowedMovementException | OccupiedCellException | WrongTurnException e1) {
				try {
					this.updateBoard();
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				new ExecptionView(e1.getMessage());
				in="";
			} catch (Exception e1) {
				try {
					this.updateBoard();
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				// TODO Auto-generated catch block
				e1.printStackTrace();
				in="";
			}finally {
				trigger=null;
				d=null;
				target=null;
				newPo=null;
				ps=false;
				this.repaint();
				this.revalidate();

			}
		}
		}
		
		}
	}
    public static void main(String[] args) throws Exception {        
    	UDPClient client =new UDPClient("localhost",5555);
    }
    public void listen() throws Exception {
        System.out.println("-- Running CLient " + InetAddress.getLocalHost() + "--");
        String msg;
        while (true) {
            byte[] buf = new byte[256];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            udpSocket.receive(packet);
            msg = new String(packet.getData()).trim();
            doData(msg);
            System.out.print("Server"+ ": ");
            for(String a:msg.split(" "))
            	System.out.print(a+" ");
            System.out.println("");
            break;
        }
        //this.start();
    }
    public void start() throws Exception {
            DatagramPacket p = new DatagramPacket(in.getBytes(), in.getBytes().length, serverAddress, port); 
            this.udpSocket.send(p); 
            System.out.println("Client"+ ": " + in);
            this.updateBoard();
            if( payLoadPlayer1!=6)
                this.listen();
       
    }
    private void updatedisable() throws Exception {
		setpnlCell1();
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
        setpnlMove();
        SwingUtilities.updateComponentTreeUI(this);
        if(payLoadPlayer1==6) {
        	new GameOverWindow(player1,this,"3la Wad3ak ya ");
        	this.start();
        	
        }
        if(payLoadPlayer2==6) {
        	new GameOverWindow(player1,this,"Hwa 2ly kasb ya looser hhhhh");
        }
       
		

	    
		
	}
	private void setpnlCell1() {
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
						btnofCell.addActionListener(null);	
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
						btnofCell.addActionListener(null);				
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
						btnofCell.addActionListener(null);
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
					btnofCell.addActionListener(null);
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
						btnofCell.addActionListener(null);
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
						btnofCell.addActionListener(null);
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
						btnofCell.addActionListener(null);
				}
				}
				}
				}
				}
				}
			}	else {
				btnofCell.setText("");
		        btnofCell.setActionCommand(i+" "+j);
		        btnofCell.addActionListener(null);
                   }
			pnlCell.setPreferredSize(new Dimension(500,40));
		    pnlCell.add(btnofCell);

			}
		}
		
	}

}
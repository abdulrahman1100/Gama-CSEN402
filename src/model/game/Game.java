package model.game;

import java.util.ArrayList;

import model.pieces.Piece;
import model.pieces.heroes.Armored;
import model.pieces.heroes.Medic;
import model.pieces.heroes.Ranged;
import model.pieces.heroes.Speedster;
import model.pieces.heroes.Super;
import model.pieces.heroes.Tech;
import model.pieces.sidekicks.SideKickP1;
import model.pieces.sidekicks.SideKickP2;

public class Game {

	private final int payloadPosTarget = 6;
	private final int boardWidth = 6;
	private final int boardHeight = 7;
	private Player player1;
	private Player player2;
	private Player currentPlayer;
	private Cell[][] board;
	private int c;
	public Game(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		currentPlayer = player1;
		board = new Cell[boardHeight][boardWidth];
		assemblePieces();
	}
	public Game(Player player1, Player player2,int c) {
		this.player1 = player1;
		this.player2 = player2;
		currentPlayer = player1;
		board = new Cell[boardHeight][boardWidth];
		assemblePieces();
		this.c=c;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public int getPayloadPosTarget() {
		return payloadPosTarget;
	}

	@Override
	public String toString() {
		String s = "";
		System.out.println("      " + getPlayer2().getName());
		System.out.print("| ");
		for (int i = 0; i < board[0].length; i++)
			System.out.print("--");
		System.out.println("|");
		for (int i = 0; i < board.length; i++) {
			System.out.print("| ");
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == null)
					System.out.print("n ");
				else
					System.out.print(board[i][j] + " ");
			}
			System.out.println("|");
		}
		System.out.print("| ");
		for (int i = 0; i < board[0].length; i++)
			System.out.print("--");
		System.out.println("|");
		System.out.println("    " + getPlayer1().getName());
		return s;
	}

	public int getBoardWidth() {
		return boardWidth;
	}

	public int getBoardHeight() {
		return boardHeight;
	}
	
   	public ArrayList<Integer> randomHero(){
		  boolean flag=true;
		   ArrayList<Integer> x=new ArrayList<Integer>();
		   while(flag) {
		   int j=(int)(Math.random()*6);
		   if(x.size()==6) 
		     {
			   flag=false;
		      }
		      if(flag&&(!(x.contains(j)))) 
		      {
		    	  x.add(j);
		       }
		      }
		   return x;
	}
	public void assemblePieces() {
		   
		       for(int i=0;i<7;i++) 
		    	   for(int j=0;j<6;j++)
		    		   board[i][j]=new Cell();
		       
			  for(int j=0;j<6;j++)
			  {    
				  Piece p=new SideKickP2(this,"K2");
				  board[2][j]=new Cell(p);
				  p.setPosI(2);
                  p.setPosJ(j);				  
			  }
		   
					  
			  for(int j=0;j<6;j++)
			    {
				          Piece p=new SideKickP1(this,"K1");
						  board[4][j]=new Cell(p);
						  p.setPosI(4);
		                  p.setPosJ(j);
					  }
			   
			  ArrayList<Integer> x = new ArrayList<Integer>();
		           if(c>0)
		           x=randomHero();
		           else {
		        	    x .add(0);
		        	    x.add(1);
		        	    x .add(3);
		        	    x.add(4);
		        	    x .add(2);
		        	    x.add(5);
		        	    }
				   Armored p=new Armored(player1,this,"A1");
				   Medic p1=new Medic(player1,this,"M1");
				   Ranged p2=new Ranged(player1,this,"R1");
				   Speedster p3=new Speedster(player1,this,"S1");
				   Super p4=new Super(player1,this,"P1");
				   Tech p5=new Tech(player1,this,"T1");

				   board[5][x.get(0)]=new Cell(p);
				   board[5][x.get(1)]=new Cell(p1);
				   board[5][x.get(2)]=new Cell(p2);
				   board[5][x.get(3)]=new Cell(p3);
				   board[5][x.get(4)]=new Cell(p4);
				   board[5][x.get(5)]=new Cell(p5);
				   p.setPosI(5);
	               p.setPosJ(x.get(0));
	               p1.setPosI(5);
	               p1.setPosJ(x.get(1));
	               p2.setPosI(5);
	               p2.setPosJ(x.get(2));
	               p3.setPosI(5);
	               p3.setPosJ(x.get(3));
	               p4.setPosI(5);
	               p4.setPosJ(x.get(4));
	               p5.setPosI(5);
	               p5.setPosJ(x.get(5));
				   
			   
				   
					   
	 			  ArrayList<Integer> x1 = new ArrayList<Integer>();
		           if(c>0)
		           x1=randomHero();
		           else {
		        	    x1.add(0);
		        	    x1.add(1);
		        	    x1.add(2);
		        	    x1.add(3);
		        	    x1.add(5);
		        	    x1.add(4);
		           }
					   Armored px=new Armored(player2,this,"A2");
					   Medic px1=new Medic(player2,this,"M2");
					   Ranged px2=new Ranged(player2,this,"R2");
					   Speedster px3=new Speedster(player2,this,"S2");
					   Super px4=new Super(player2,this,"P2");
					   Tech px5=new Tech(player2,this,"T2");
					 
					   board[1][x1.get(0)]=new Cell(px);
					   board[1][x1.get(1)]=new Cell(px1);
					   board[1][x1.get(2)]=new Cell(px2);
					   board[1][x1.get(3)]=new Cell(px3);
					   board[1][x1.get(4)]=new Cell(px4);
					   board[1][x1.get(5)]=new Cell(px5);
					   px.setPosI(1);
		               px.setPosJ(x1.get(0));
		               px1.setPosI(1);
		               px1.setPosJ(x1.get(1));
		               px2.setPosI(1);
		               px2.setPosJ(x1.get(2));
		               px3.setPosI(1);
		               px3.setPosJ(x1.get(3));
		               px4.setPosI(1);
		               px4.setPosJ(x1.get(4));
		               px5.setPosI(1);
		               px5.setPosJ(x1.get(5));
					   
				  
			   
		
		  
		   }
	public Cell getCellAt(int i, int j) {
			  return board[i][j];
		  }	   
	public void switchTurns() {
		   if(currentPlayer ==player1) {
			   this.setCurrentPlayer(player2);	   }
		   else {
			   this.setCurrentPlayer(player1);	}
		
		   
	   }
	public boolean checkWinner() {
		   return currentPlayer.getPayloadPos()==payloadPosTarget;
		   
	   }
	
	
	public Game copyGame() {
		Game a=new Game(new Player(player1.getName()),new Player(player2.getName()));
		a.getPlayer1().setPayloadPos(player1.getPayloadPos());
		a.getPlayer1().setDeadCharacters(player1.getDeadCharacters());
		a.getPlayer2().setDeadCharacters(player2.getDeadCharacters());
		a.getPlayer2().setPayloadPos(player2.getPayloadPos());
		for(int i=0;i<boardHeight;i++) {
			for(int j=0;j<boardWidth;j++) {
				Cell temp=this.getCellAt(i, j);
				if(temp.toString()=="n") {
					a.getCellAt(i, j).setPiece(null);
				}else {
					if(temp.toString()=="A") {
						Armored A;
						if(temp.getPiece().getOwner()==this.getPlayer1()) {
						 A=new Armored(a.getPlayer1(),a,"A1");
						}else {
						 A=new Armored(a.getPlayer2(),a,"A2");
						}
						//Armored A=new Armored(temp.getPiece().getOwner(),a,"A");
						A.setPosI(i);
						A.setPosJ(j);
						a.getCellAt(i, j).setPiece(A);
						}
					if(temp.toString()=="M") {
						Medic A;
						if(temp.getPiece().getOwner()==this.getPlayer1()) {
						 A=new Medic(a.getPlayer1(),a,"M1");
						}else {
						 A=new Medic(a.getPlayer2(),a,"M2");
						}
						//Medic A=new Medic(temp.getPiece().getOwner(),a,"M");
						A.setPosI(i);
						A.setPosJ(j);
						a.getCellAt(i, j).setPiece(A);
						}
					if(temp.toString()=="R") {
						Ranged A;
						if(temp.getPiece().getOwner()==this.getPlayer1()) {
						 A=new Ranged(a.getPlayer1(),a,"R1");
						}else {
						 A=new Ranged(a.getPlayer2(),a,"R2");
						}
						//Ranged A=new Ranged(temp.getPiece().getOwner(),a,"R");
						A.setPosI(i);
						A.setPosJ(j);
						a.getCellAt(i, j).setPiece(A);
						}
					if(temp.toString()=="T") {
						Tech A;
						if(temp.getPiece().getOwner()==this.getPlayer1()) {
						 A=new Tech(a.getPlayer1(),a,"T1");
						}else {
						 A=new Tech(a.getPlayer2(),a,"T2");
						}
						//Tech A=new Tech(temp.getPiece().getOwner(),a,"T");
						A.setPosI(i);
						A.setPosJ(j);
						a.getCellAt(i, j).setPiece(A);
						}
					if(temp.toString()=="S") {
						Speedster A;
						if(temp.getPiece().getOwner()==this.getPlayer1()) {
						 A=new Speedster(a.getPlayer1(),a,"S1");
						}else {
						 A=new Speedster(a.getPlayer2(),a,"S2");
						}
					//	Speedster A=new Speedster(temp.getPiece().getOwner(),a,"S");
						A.setPosI(i);
						A.setPosJ(j);
						a.getCellAt(i, j).setPiece(A);
						}
					if(temp.toString()=="P") {
						Super A;
						if(temp.getPiece().getOwner()==this.getPlayer1()) {
						 A=new Super(a.getPlayer1(),a,"P1");
						}else {
						 A=new Super(a.getPlayer2(),a,"P2");
						}
						A.setPosI(i);
						A.setPosJ(j);
						a.getCellAt(i, j).setPiece(A);
						}
					if(temp.toString()=="K") {
						if(temp.getPiece().getOwner()==temp.getPiece().getGame().getPlayer1()) {
						SideKickP1 A=new SideKickP1(a,"K1");
						A.setPosI(i);
						A.setPosJ(j);
						a.getCellAt(i, j).setPiece(A);
						}
						else {
							SideKickP2 A=new SideKickP2(a,"K2");
							A.setPosI(i);
							A.setPosJ(j);
							a.getCellAt(i, j).setPiece(A);
							}

						}
				}


			}
		}
		return a;
	} 

}

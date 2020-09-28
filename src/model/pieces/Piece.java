package model.pieces;

import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.heroes.Armored;
import model.pieces.heroes.Hero;
import model.pieces.heroes.Medic;
import model.pieces.heroes.Ranged;
import model.pieces.heroes.Speedster;
import model.pieces.heroes.Super;
import model.pieces.heroes.Tech;
import model.pieces.sidekicks.SideKick;

public abstract class Piece implements Movable {

	private String name;
	private Player owner;
	private Game game;
	private int posI;
	private int posJ;

	public Piece(Player p, Game g, String name) {
		this.owner = p;
		this.game = g;
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
	this.name=name;
	}
	public int getPosI() {
		return posI;
	}

	public void setPosI(int i) {
		posI = i;
	}

	public int getPosJ() {
		return posJ;
	}

	public void setPosJ(int j) {
		posJ = j;
	}

	public Game getGame() {
		return game;
	}

	public Player getOwner() {
		return owner;
	}
	public void attack(Piece target) {
	    if(target !=null) {
		if(target instanceof Hero) {
			if(target instanceof Armored) {
				if(((Armored)target).isArmorUp()) {
					((Armored) target).setArmorUp(false);
				}
				else {
					
					   owner.setPayloadPos(owner.getPayloadPos()+1);
					   target.getOwner().getDeadCharacters().add(target);
					   game.getCellAt(target.getPosI(), target.getPosJ()).setPiece(null);
				       game.checkWinner();
                     }
				
			}else {
			   owner.setPayloadPos(owner.getPayloadPos()+1);
			   target.getOwner().getDeadCharacters().add(target);
			   game.getCellAt(target.getPosI(), target.getPosJ()).setPiece(null);
		       game.checkWinner();
               }
		}
       if(target instanceof SideKick) {
			
			target.getOwner().getDeadCharacters().add(target);
			owner.setSideKilled(owner.getSideKilled()+1); 
			if(owner.getSideKilled()%2==0) {
				owner.setPayloadPos(owner.getPayloadPos()+1);}
			 getGame().getCellAt(target.getPosI(), target.getPosJ()).setPiece(null);
		       game.checkWinner();

       }
      
	 
}

}
public abstract  void move(Direction r) throws WrongTurnException, UnallowedMovementException, OccupiedCellException ;


public void moveDown() {
	   if(getPosI()==6) {
			 setPosI(0); 
		 }
		 else 
		 {
		 setPosI(getPosI()+1); 
		 }
		 
	 
	 
}
public void  moveLeft() {

		 if(getPosJ()==0) {
			 setPosJ(5);
		 }
		 else {
	   setPosJ(getPosJ()-1); 
	       }
		 

}
public void moveRight() {
	
		 if(getPosJ()==5) {
			 setPosJ(0);
		 }else {
	 setPosJ(getPosJ()+1); }

			 }
public void moveUp() {

		 if(getPosI()==0)
		 {
			 setPosI(6); 
		 }
		 else
		 {
	       setPosI(getPosI()-1); 
	     }
}
public  void moveDownLeft() 
{
	moveDown();
	moveLeft();
}
public void moveDownRight()
{	moveDown();
    moveRight();
}
public void moveUpLeft() 
{
	moveUp();
	moveLeft();
}
public void moveUpRight() 
{
	moveUp();
	moveRight();
}
}

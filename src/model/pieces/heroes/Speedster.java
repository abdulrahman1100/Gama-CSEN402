package model.pieces.heroes;

import exceptions.OccupiedCellException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;
import model.pieces.sidekicks.SideKick;

public class Speedster extends NonActivatablePowerHero {

	public Speedster(Player player, Game game, String name) {
		super(player, game, name);
	}

	@Override
	public String toString() {
		return "S";
	}//
	public void move(Direction r) throws WrongTurnException, OccupiedCellException {
		
		if(this.getOwner()==getGame().getCurrentPlayer()) {
			if(r==Direction.UP) {
				int x=getPosI();
				int y=getPosJ();
				moveUp();
				moveUp();
		       Piece target=getGame().getCellAt(getPosI(), getPosJ()).getPiece();
		       if(target==null) {
            	   getGame().getCellAt(getPosI(), getPosJ()).setPiece(this);
	    			   getGame().getCellAt(x, y).setPiece(null);
               }else {
    		       if(target.getOwner()==this.getOwner()) {
		    			   moveDown();
		    			   moveDown();
   			    	throw new OccupiedCellException ("Feha el Piece mn bto3ak anta at3met",this,r);
   		       }else {
   		    	   if(target instanceof Armored&&((Armored) target).isArmorUp()) {
   		    			   attack(target);
   		    			   moveDown();
   		    			   moveDown();
   		    	   }else {
   	    			   attack(target);
   	    			   getGame().getCellAt(getPosI(), getPosJ()).setPiece(this);
   	    			   getGame().getCellAt(x, y).setPiece(null);
   		    	   }
   		       }
               }
			}
			if(r==Direction.DOWN) {
				int x=getPosI();
				int y=getPosJ();
				moveDown();
				moveDown();
		       Piece target=getGame().getCellAt(getPosI(), getPosJ()).getPiece();
		       if(target==null) {
            	   getGame().getCellAt(getPosI(), getPosJ()).setPiece(this);
	    			   getGame().getCellAt(x, y).setPiece(null);
               }else {
    		       if(target.getOwner()==this.getOwner()) {
		    			   moveUp();
		    			   moveUp();
   			    	throw new OccupiedCellException ("Feha el Piece mn bto3ak anta at3met",this,r);
   		       }else {
   		    	   if(target instanceof Armored&&((Armored) target).isArmorUp()) {
   		    			   attack(target);
   		    			   moveUp();
   		    			   moveUp();
   		    	   }else {
   	    			   attack(target);
   	    			   getGame().getCellAt(getPosI(), getPosJ()).setPiece(this);
   	    			   getGame().getCellAt(x, y).setPiece(null);
   		    	   }
   		       }
               }

			}
			if(r==Direction.RIGHT) {
				int x=getPosI();
				int y=getPosJ();
				moveRight();
				moveRight();
		       Piece target=getGame().getCellAt(getPosI(), getPosJ()).getPiece();
		       if(target==null) {
            	   getGame().getCellAt(getPosI(), getPosJ()).setPiece(this);
	    			   getGame().getCellAt(x, y).setPiece(null);
               }else {
    		       if(target.getOwner()==this.getOwner()) {
		    			   moveLeft();
		    			   moveLeft();
   			    	throw new OccupiedCellException ("Feha el Piece mn bto3ak anta at3met",this,r);
   		       }else {
   		    	   if(target instanceof Armored&&((Armored) target).isArmorUp()) {
   		    			   attack(target);
   		    			   moveLeft();
   		    			   moveLeft();
   		    	   }else {
   	    			   attack(target);
   	    			   getGame().getCellAt(getPosI(), getPosJ()).setPiece(this);
   	    			   getGame().getCellAt(x, y).setPiece(null);
   		    	   }
   		       }
               }

			}
			if(r==Direction.LEFT) {
				int x=getPosI();
				int y=getPosJ();
				moveLeft();
				moveLeft();
		       Piece target=getGame().getCellAt(getPosI(), getPosJ()).getPiece();
               if(target==null) {
            	   getGame().getCellAt(getPosI(), getPosJ()).setPiece(this);
	    			   getGame().getCellAt(x, y).setPiece(null);
               }else {
    		       if(target.getOwner()==this.getOwner()) {
		    			   moveRight();
		    			   moveRight();
   			    	throw new OccupiedCellException ("Feha el Piece mn bto3ak anta at3met",this,r);
   		       }else {
   		    	   if(target instanceof Armored&&((Armored) target).isArmorUp()) {
   		    			   attack(target);
   		    			   moveRight();
   		    			   moveRight();
   		    			   }else {
   	    			   attack(target);
   	    			   getGame().getCellAt(getPosI(), getPosJ()).setPiece(this);
   	    			   getGame().getCellAt(x, y).setPiece(null);
   		    	   }
   		       }
               }

			}
			if(r==Direction.UPRIGHT) {
				int x=getPosI();
				int y=getPosJ();
				moveUpRight();
				moveUpRight();
		       Piece target=getGame().getCellAt(getPosI(), getPosJ()).getPiece();
               if(target==null) {
            	   getGame().getCellAt(getPosI(), getPosJ()).setPiece(this);
	    			   getGame().getCellAt(x, y).setPiece(null);
               }else {
    		       if(target.getOwner()==this.getOwner()) {
		    			   moveDownLeft();
		    			   moveDownLeft();
   			    	throw new OccupiedCellException ("Feha el Piece mn bto3ak anta at3met",this,r);
   		       }else {
   		    	   if(target instanceof Armored&&((Armored) target).isArmorUp()) {
   		    			   attack(target);
   		    			   moveDownLeft();
   		    			   moveDownLeft();
   		    	   }else {
   	    			   attack(target);
   	    			   getGame().getCellAt(getPosI(), getPosJ()).setPiece(this);
   	    			   getGame().getCellAt(x, y).setPiece(null);
   		    	   }
   		       }
               }

			}
			if(r==Direction.UPLEFT) {
				int x=getPosI();
				int y=getPosJ();
				moveUpLeft();
				moveUpLeft();
		       Piece target=getGame().getCellAt(getPosI(), getPosJ()).getPiece();
               if(target==null) {
            	   getGame().getCellAt(getPosI(), getPosJ()).setPiece(this);
	    			   getGame().getCellAt(x, y).setPiece(null);
               }else {
    		       if(target.getOwner()==this.getOwner()) {
		    			   moveDownRight();
		    			   moveDownRight();
   			    	throw new OccupiedCellException ("Feha el Piece mn bto3ak anta at3met",this,r);
   		       }else {
   		    	   if(target instanceof Armored&&((Armored) target).isArmorUp()) {
   		    			   attack(target);
   		    			   moveDownRight();
   		    			   moveDownRight();
   		    	   }else {
   	    			   attack(target);
   	    			   getGame().getCellAt(getPosI(), getPosJ()).setPiece(this);
   	    			   getGame().getCellAt(x, y).setPiece(null);
   		    	   }
   		       }
               }

			}
			if(r==Direction.DOWNLEFT) {
				int x=getPosI();
				int y=getPosJ();
				moveDownLeft();
				moveDownLeft();
		       Piece target=getGame().getCellAt(getPosI(), getPosJ()).getPiece();
               if(target==null) {
            	   getGame().getCellAt(getPosI(), getPosJ()).setPiece(this);
	    			   getGame().getCellAt(x, y).setPiece(null);
               }else {
    		       if(target.getOwner()==this.getOwner()) {
		    			   moveUpRight();
		    			   moveUpRight();
   			    	throw new OccupiedCellException ("Feha el Piece mn bto3ak anta at3met",this,r);
   		       }else {
   		    	   if(target instanceof Armored&&((Armored) target).isArmorUp()) {
   		    			   attack(target);
   		    			   moveUpRight();
   		    			   moveUpRight();
   		    	   }else {
   	    			   attack(target);
   	    			   getGame().getCellAt(getPosI(), getPosJ()).setPiece(this);
   	    			   getGame().getCellAt(x, y).setPiece(null);
   		    	   }
   		       }
               }

			}
			if(r==Direction.DOWNRIGHT) {
				int x=getPosI();
				int y=getPosJ();
				moveDownRight();
				moveDownRight();
		       Piece target=getGame().getCellAt(getPosI(), getPosJ()).getPiece();
               if(target==null) {
            	   getGame().getCellAt(getPosI(), getPosJ()).setPiece(this);
	    			   getGame().getCellAt(x, y).setPiece(null);
               }else {
    		       if(target.getOwner()==this.getOwner()) {
		    			   moveUpLeft();
		    			   moveUpLeft();
   			    	throw new OccupiedCellException ("Feha el Piece mn bto3ak anta at3met",this,r);
   		       }else {
   		    	   if(target instanceof Armored&&((Armored) target).isArmorUp()) {    
   		    			   attack(target);
   		    			   moveUpLeft();
   		    			   moveUpLeft();
   		    	   }else {
   	    			   attack(target);
   	    			   getGame().getCellAt(getPosI(), getPosJ()).setPiece(this);
   	    			   getGame().getCellAt(x, y).setPiece(null);
   		    	   }
   		       }
               }
			}
		
		getGame().switchTurns();
		}else {
			throw new WrongTurnException("Not Your Turn",this);
		}
	}
		}
	



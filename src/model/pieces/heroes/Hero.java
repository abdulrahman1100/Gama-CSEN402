package model.pieces.heroes;

import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;

public abstract class Hero extends Piece {

	public Hero(Player player, Game game, String name) {
		super(player, game, name);
	}
	
	
	public void move(Direction r) throws WrongTurnException, UnallowedMovementException, OccupiedCellException {
		if(this.getOwner()==getGame().getCurrentPlayer()) {
		     int x=getPosI();
		     int y=getPosJ();
		     if(r==Direction.DOWN) {
		    	 moveDown();
		    	 Piece target=getGame().getCellAt(getPosI(), getPosJ()).getPiece();
		    	 if(target==null) {
		    		 getGame().getCellAt(getPosI(), getPosJ()).setPiece(this);
		    		 getGame().getCellAt(x, y).setPiece(null);
		    	 }else {
		    		 if(target.getOwner()==this.getOwner()) {
		    			 moveUp();
		    			 throw new OccupiedCellException  ("This Cell is Occupied",this,r);
		    		 }else {
		    				 if(target instanceof Armored&& ((Armored) target).isArmorUp()) {
	    					 attack(target);
		    				 moveUp();
		    				 }else {
		    				 attack(target);
	    					 getGame().getCellAt(getPosI(), getPosJ()).setPiece(this);
	    					 getGame().getCellAt(x, y).setPiece(null); 
	    					 }
		    		 }
		    	 }
		     }
		     if(r==Direction.UP) {
		    	 moveUp();
		    	 Piece target=getGame().getCellAt(getPosI(), getPosJ()).getPiece();
		    	 if(target==null) {
		    		 getGame().getCellAt(getPosI(), getPosJ()).setPiece(this);
		    		 getGame().getCellAt(x, y).setPiece(null);
		    	 }else {
		    		 if(target.getOwner()==this.getOwner()) {
		    			 moveDown();
		    			 throw new OccupiedCellException  ("This Cell is Occupied",this,r);
		    		 }else {
		    				 if(target instanceof Armored&& ((Armored) target).isArmorUp()) {
	    					 attack(target);
	    					 moveDown();
		    				 }else {
		    				 attack(target);
	    					 getGame().getCellAt(getPosI(), getPosJ()).setPiece(this);
	    					 getGame().getCellAt(x, y).setPiece(null); 
	    					 }
		    		 }
		    	 }
		     }
		     if(r==Direction.UPRIGHT) {
		    	 moveUpRight();
		    	 Piece target=getGame().getCellAt(getPosI(), getPosJ()).getPiece();
		    	 if(target==null) {
		    		 getGame().getCellAt(getPosI(), getPosJ()).setPiece(this);
		    		 getGame().getCellAt(x, y).setPiece(null);
		    	 }else {
		    		 if(target.getOwner()==this.getOwner()) {
		    			 moveDownLeft();
		    			 throw new OccupiedCellException  ("This Cell is Occupied",this,r);
		    		 }else {
		    				 if(target instanceof Armored&& ((Armored) target).isArmorUp()) {
	    					 attack(target);
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
		    	 moveUpLeft();
		    	 Piece target=getGame().getCellAt(getPosI(), getPosJ()).getPiece();
		    	 if(target==null) {
		    		 getGame().getCellAt(getPosI(), getPosJ()).setPiece(this);
		    		 getGame().getCellAt(x, y).setPiece(null);
		    	 }else {
		    		 if(target.getOwner()==this.getOwner()) {
		    			 moveDownRight();
		    			 throw new OccupiedCellException  ("This Cell is Occupied",this,r);
		    		 }else {
		    				 if(target instanceof Armored&& ((Armored) target).isArmorUp()) {
	    					 attack(target);
	    					 moveDownRight();
		    				 }else {
		    				 attack(target);
	    					 getGame().getCellAt(getPosI(), getPosJ()).setPiece(this);
	    					 getGame().getCellAt(x, y).setPiece(null); 
	    					 }
		    		 }
		    	 }
		     }
		     if(r==Direction.RIGHT) {
		    	 moveRight();
		    	 Piece target=getGame().getCellAt(getPosI(), getPosJ()).getPiece();
		    	 if(target==null) {
		    		 getGame().getCellAt(getPosI(), getPosJ()).setPiece(this);
		    		 getGame().getCellAt(x, y).setPiece(null);
		    	 }else {
		    		 if(target.getOwner()==this.getOwner()) {
		    			 moveLeft();
		    			 throw new OccupiedCellException  ("This Cell is Occupied",this,r);
		    		 }else {
		    				 if(target instanceof Armored&& ((Armored) target).isArmorUp()) {
	    					 attack(target);
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
		    	 moveLeft();
		    	 Piece target=getGame().getCellAt(getPosI(), getPosJ()).getPiece();
		    	 if(target==null) {
		    		 getGame().getCellAt(getPosI(), getPosJ()).setPiece(this);
		    		 getGame().getCellAt(x, y).setPiece(null);
		    	 }else {
		    		 if(target.getOwner()==this.getOwner()) {
		    			 moveRight();
		    			 throw new OccupiedCellException  (this,r);
		    		 }else {
		    				 if(target instanceof Armored&& ((Armored) target).isArmorUp()) {
	    					 attack(target);
	    					 moveRight();
		    				 }else {
		    				 attack(target);
	    					 getGame().getCellAt(getPosI(), getPosJ()).setPiece(this);
	    					 getGame().getCellAt(x, y).setPiece(null); 
	    					 }
		    		 }
		    	 }
		     }
		     if(r==Direction.DOWNRIGHT) {
		    	 moveDownRight();
		    	 Piece target=getGame().getCellAt(getPosI(), getPosJ()).getPiece();
		    	 if(target==null) {
		    		 getGame().getCellAt(getPosI(), getPosJ()).setPiece(this);
		    		 getGame().getCellAt(x, y).setPiece(null);
		    	 }else {
		    		 if(target.getOwner()==this.getOwner()) {
		    			 moveUpLeft();
		    			 throw new OccupiedCellException  ("This Cell is Occupied",this,r);
		    		 }else {

		    				 if(target instanceof Armored&& ((Armored) target).isArmorUp()) {
	    					 attack(target);
	    					 moveUpLeft();
		    				 }else {
		    				 attack(target);
	    					 getGame().getCellAt(getPosI(), getPosJ()).setPiece(this);
	    					 getGame().getCellAt(x, y).setPiece(null); 
	    					 }	 
		    		 }
		    	 }
		     }
		     if(r==Direction.DOWNLEFT) {
		    	 moveDownLeft();
		    	 Piece target=getGame().getCellAt(getPosI(), getPosJ()).getPiece();
		    	 if(target==null) {
		    		 getGame().getCellAt(getPosI(), getPosJ()).setPiece(this);
		    		 getGame().getCellAt(x, y).setPiece(null);
		    	 }else {
		    		 if(target.getOwner()==this.getOwner()) {
		    			 moveUpRight();
		    			 throw new OccupiedCellException  ("This Cell is Occupied",this,r);
		    		    }else {
		    		
		    				 if(target instanceof Armored&& ((Armored) target).isArmorUp()) {
		    					 attack(target);
		    					 moveUpRight();
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
			throw new WrongTurnException ("Not Your Turn",this);
		}
		
	}

}
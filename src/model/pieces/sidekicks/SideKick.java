package model.pieces.sidekicks;

import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;
import model.pieces.heroes.Armored;
import model.pieces.heroes.Hero;
import model.pieces.heroes.Medic;
import model.pieces.heroes.Ranged;
import model.pieces.heroes.Speedster;
import model.pieces.heroes.Super;
import model.pieces.heroes.Tech;

public abstract class SideKick extends Piece {

	public SideKick(Player player, Game game, String name) {
		super(player, game, name);
	}

	@Override
	public String toString() {
		return "K";
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
		    			 throw new OccupiedCellException  ("Feha Picec mn Bto#ak anta at3met",this,r);
		    		 }else {
		    				 if(target instanceof Hero) {
		    					 if(target instanceof Armored && ((Armored) target).isArmorUp()) {
		    						 attack(target);
		    						 moveUp();
		    					 }else {
		    						 attack(target);
		    						 if(target instanceof Armored){
		    			        		 getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Armored(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	 if(target instanceof Medic){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Medic(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	 if(target instanceof Ranged){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Ranged(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	 if(target instanceof Super){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Super(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	 if(target instanceof Tech){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Tech(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	  if(target instanceof Speedster){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Speedster(this.getOwner(),getGame(),this.getName() ));
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());
		    			        	    	}
		    			        	  getGame().getCellAt(x, y).setPiece(null);
		    					 }
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
		    			 throw new OccupiedCellException  ("Feha Picec mn Bto3ak anta at3met",this,r);
		    		 }else {
		    				 if(target instanceof Hero) {
		    					 if(target instanceof Armored && ((Armored) target).isArmorUp()) {
		    						 attack(target);
		    						 moveDown();
		    					 }else {
		    						 attack(target);
		    						 if(target instanceof Armored){
		    			        		 getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Armored(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	 if(target instanceof Medic){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Medic(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	 if(target instanceof Ranged){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Ranged(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	 if(target instanceof Super){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Super(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	 if(target instanceof Tech){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Tech(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	  if(target instanceof Speedster){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Speedster(this.getOwner(),getGame(),this.getName() ));
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	  getGame().getCellAt(x, y).setPiece(null);
		    					 }
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
		    			 throw new OccupiedCellException  ("Feha Picec mn Bto#ak anta at3met",this,r);
		    		 }else {
		    				 if(target instanceof Hero) {
		    					 if(target instanceof Armored && ((Armored) target).isArmorUp()) {
		    						 attack(target);
		    						 moveDownLeft();
		    					 }else {
		    						 attack(target);
		    						 if(target instanceof Armored){
		    			        		 getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Armored(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	 if(target instanceof Medic){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Medic(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	 if(target instanceof Ranged){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Ranged(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	 if(target instanceof Super){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Super(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	 if(target instanceof Tech){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Tech(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	  if(target instanceof Speedster){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Speedster(this.getOwner(),getGame(),this.getName() ));
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	  getGame().getCellAt(x, y).setPiece(null);
		    					 }
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
		    			 throw new OccupiedCellException  ("Feha Picec mn Bto#ak anta at3met",this,r);
		    		 }else {
		    				 if(target instanceof Hero) {
		    					 if(target instanceof Armored && ((Armored) target).isArmorUp()) {
		    						 attack(target);
		    						 moveDownRight();
		    					 }else {
		    						 attack(target);
		    						 if(target instanceof Armored){
		    			        		 getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Armored(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	 if(target instanceof Medic){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Medic(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	 if(target instanceof Ranged){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Ranged(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	 if(target instanceof Super){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Super(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	 if(target instanceof Tech){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Tech(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	  if(target instanceof Speedster){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Speedster(this.getOwner(),getGame(),this.getName() ));
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	  getGame().getCellAt(x, y).setPiece(null);
		    					 }
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
		    			 throw new OccupiedCellException  ("Feha Picec mn Bto#ak anta at3met",this,r);
		    		 }else {
		    				 if(target instanceof Hero) {
		    					 if(target instanceof Armored && ((Armored) target).isArmorUp()) {
		    						 attack(target);
		    						 moveLeft();
		    					 }else {
		    						 attack(target);
		    						 if(target instanceof Armored){
		    			        		 getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Armored(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	 if(target instanceof Medic){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Medic(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	 if(target instanceof Ranged){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Ranged(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	 if(target instanceof Super){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Super(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	 if(target instanceof Tech){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Tech(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	  if(target instanceof Speedster){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Speedster(this.getOwner(),getGame(),this.getName() ));
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	  getGame().getCellAt(x, y).setPiece(null);
		    					 }
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
		    				 if(target instanceof Hero) {
		    					 if(target instanceof Armored && ((Armored) target).isArmorUp()) {
		    						 attack(target);
		    						 moveRight();
		    					 }else {
		    						 attack(target);
		    						 if(target instanceof Armored){
		    			        		 getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Armored(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	 if(target instanceof Medic){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Medic(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	 if(target instanceof Ranged){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Ranged(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	 if(target instanceof Super){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Super(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	 if(target instanceof Tech){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Tech(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	  if(target instanceof Speedster){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Speedster(this.getOwner(),getGame(),this.getName() ));
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	  getGame().getCellAt(x, y).setPiece(null);
		    					 }
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
		    			 throw new OccupiedCellException  ("Feha Picec mn Bto#ak anta at3met",this,r);
		    		 }else {
		    				 if(target instanceof Hero) {
		    					 if(target instanceof Armored && ((Armored) target).isArmorUp()) {
		    						 attack(target);
		    						 moveUpLeft();
		    					 }else {
		    						 attack(target);
		    						 if(target instanceof Armored){
		    			        		 getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Armored(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	 if(target instanceof Medic){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Medic(this.getOwner(),getGame(),this.getName()) );
		    			        	    	
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	 if(target instanceof Ranged){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Ranged(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	 if(target instanceof Super){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Super(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	 if(target instanceof Tech){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Tech(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	  if(target instanceof Speedster){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Speedster(this.getOwner(),getGame(),this.getName() ));
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	  getGame().getCellAt(x, y).setPiece(null);
		    					 }
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
		    			 throw new OccupiedCellException  ("Feha Picec mn Bto#ak anta at3met",this,r);
		    		 }else {
		    				 if(target instanceof Hero) {
		    					 if(target instanceof Armored && ((Armored) target).isArmorUp()) {
		    						 attack(target);
		    						 moveUpRight();
		    					 }else {
		    						 attack(target);
		    						 if(target instanceof Armored){
		    			        		 getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Armored(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	 if(target instanceof Medic){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Medic(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	 if(target instanceof Ranged){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Ranged(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	 if(target instanceof Super){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Super(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	 if(target instanceof Tech){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Tech(this.getOwner(),getGame(),this.getName()) );
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());}
		    			        	  if(target instanceof Speedster){
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(new Speedster(this.getOwner(),getGame(),this.getName() ));
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosI(this.getPosI());
		    			        	    	getGame().getCellAt(this.getPosI(),this.getPosJ()).getPiece().setPosJ(this.getPosJ());
		    			        	    	}
		    			        	  getGame().getCellAt(x, y).setPiece(null);
		    					 }
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

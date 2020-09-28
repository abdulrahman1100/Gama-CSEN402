package model.pieces.heroes;

import java.awt.Point;

import exceptions.InvalidPowerDirectionException;
import exceptions.OccupiedCellException;
import exceptions.PowerAlreadyUsedException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;
import model.pieces.sidekicks.SideKick;
import model.pieces.sidekicks.SideKickP1;
import model.pieces.sidekicks.SideKickP2;

public class Super extends ActivatablePowerHero {

	public Super(Player player, Game game, String name) {
		super(player, game, name);
	}

	@Override
	public String toString() {
		return "P";
	}
	public void move(Direction r) throws WrongTurnException , UnallowedMovementException, OccupiedCellException {
		
		
		if((r==Direction.DOWN)||(r==Direction.LEFT)||(r==Direction.RIGHT)||(r==Direction.UP)) {
			super.move(r);
		}
		else {
			throw new UnallowedMovementException("Read The Description",this, r);
		}

		
	}
	
	public void usePower(Direction d, Piece target, Point newPos) throws WrongTurnException, PowerAlreadyUsedException, InvalidPowerDirectionException {
		if(this.getOwner()==getGame().getCurrentPlayer()) {
               if(!(this.isPowerUsed())) {
             	   if((d==Direction.DOWN)||(d==Direction.LEFT)||(d==Direction.RIGHT)||(d==Direction.UP)) {
            		   if((d==Direction.DOWN)) {
            			   if(this.getPosI()!=6) {
                         moveDown();
              		   int x=this.getPosI();
              		   int y=this.getPosJ();
                         Piece P=getGame().getCellAt(x, y).getPiece();
                         if(P!=null&&P.getOwner()!=this.getOwner())
                        	 attack(P);
                         if(this.getPosI()!=6) {
                         moveDown();
              		    x=this.getPosI();
              		    y=this.getPosJ();
                         Piece P1=getGame().getCellAt(x, y).getPiece();
                         if(P1!=null&&P1.getOwner()!=this.getOwner())
                        	 attack(P1);
                         moveUp();
                         }
                         moveUp();
            		   }
            			   }
            		   if((d==Direction.RIGHT)) {
            			   if(this.getPosJ()!=5) {
                           moveRight();
                  		   int x=this.getPosI();
                  		   int y=this.getPosJ();
                             Piece P=getGame().getCellAt(x, y).getPiece();
                             if(P!=null&&P.getOwner()!=this.getOwner())
                            	 attack(P);
                             if(this.getPosJ()!=5) {
                             moveRight();
                  		    x=this.getPosI();
                  		    y=this.getPosJ();
                             Piece P1=getGame().getCellAt(x, y).getPiece();
                             if(P1!=null&&P1.getOwner()!=this.getOwner())
                            	 attack(P1);
                             moveLeft();
                             }
                             moveLeft();
                             }
            		   }
            		   if((d==Direction.UP)) {
            			   if(this.getPosI()!=0) {
            			   moveUp();
                  		   int x=this.getPosI();
                  		   int y=this.getPosJ();
                             Piece P=getGame().getCellAt(x, y).getPiece();
                             if(P!=null&&P.getOwner()!=this.getOwner())
                            	 attack(P);
                             if(this.getPosI()!=0) {
                             moveUp();
                  		    x=this.getPosI();
                  		    y=this.getPosJ();
                             Piece P1=getGame().getCellAt(x, y).getPiece();
                             if(P1!=null&&P1.getOwner()!=this.getOwner())
                            	 attack(P1);
                             moveDown();
                             }
                             moveDown();
                    		   }
            			   }
            		   if((d==Direction.LEFT)) {
            			   if(this.getPosJ()!=0) {
            			   moveLeft();
                  		   int x=this.getPosI();
                  		   int y=this.getPosJ();
                             Piece P=getGame().getCellAt(x, y).getPiece();
                             if(P!=null&&P.getOwner()!=this.getOwner())
                            	 attack(P);
                            if(this.getPosJ()!=0) {
                             moveLeft();
                  		    x=this.getPosI();
                  		    y=this.getPosJ();
                             Piece P1=getGame().getCellAt(x, y).getPiece();
                             if(P1!=null&&P1.getOwner()!=this.getOwner())
                            	 attack(P1);
                             moveRight();
                             }
                             moveRight();
            		   }
            		   }
            	   }else {
            		   throw new InvalidPowerDirectionException ("this direction is not allowed by the game rules",this,d);
            	   }
             	  this.setPowerUsed(true);
				   getGame().switchTurns();  

               }else {
            	   throw new PowerAlreadyUsedException("Anta ast5dmt El power Rakz",this);
               }

		 }else {
			 throw  new WrongTurnException("Not Your Turn",this);
		 }
	}
	
	
	
	
	public void usePower2(Direction d, Piece target, Point newPos) throws WrongTurnException, PowerAlreadyUsedException, InvalidPowerDirectionException {
		if(this.getOwner()==getGame().getCurrentPlayer()) {
               if(!(this.isPowerUsed())) {
             	   if((d==Direction.DOWN)||(d==Direction.LEFT)||(d==Direction.RIGHT)||(d==Direction.UP)) {
            		   int x=this.getPosI();
            		   int y=this.getPosJ();
            		   if((d==Direction.DOWN)) {
            			   if(x<6) {
            				   Piece P=getGame().getCellAt(++x, y).getPiece();
            				   if(P==null){
            					   if(x<6) {
            					 Piece P2=getGame().getCellAt(++x, y).getPiece();
            					   if(P2==null) {
            						   //may throw execption un useful power or direction
            						   
            					   }else {
                                       if(P2.getOwner()==this.getOwner()) {
                                       	//may exxcepton oocu
                                       }else {		
                     							 attack(P2);
                                       }
            					   }
            					 
            					   }else {
            						   //may exception out board
            					   }
            				   }else {//first attack my piece or armored or hero 
            					   if(this.getOwner()==P.getOwner()) {
            						   //may exception occiuped exception 
                					   if(x<6) {
                      					 Piece P2=getGame().getCellAt(++x, y).getPiece();
                      					   if(P2==null) {
                      						   //may throw execption un useful power or direction
                      						   
                      					   }else {//second attack my piece or aromred or  piece
                                                 if(P2.getOwner()==this.getOwner()) {
                                                 	//may exxcepton oocu
                                                 }else {
                               							 attack(P2);		
                                                 }
                      					   }
                      					 
                      					   }
            					   }else {
            							   attack(P);
                    					   if(x<6) {
                          					 Piece P2=getGame().getCellAt(++x, y).getPiece();
                          					   if(P2==null) {
                          						   //may throw execption un useful power or direction
                          						   
                          					   }else {//second attack my piece or aromred or  piece
                                                if(P2.getOwner()==this.getOwner()) {
                                                	//may exxcepton oocu
                                                }else {
                              							 attack(P2);
                                                }
                          					   }
                          					 
                          					   }else {
                          						   //may exception out board
                          					   }
            							   
            						   
            					   }
            					   
            				   }
            				   
            			   }else {
            				   //may execeptionoutnoard
            			   }
            		   }
            		   if((d==Direction.RIGHT)) {
            			   if(y<5) {
            				   Piece P=getGame().getCellAt(x, ++y).getPiece();
            				   if(P==null){
            					   if(y<5) {
            					 Piece P2=getGame().getCellAt(x, ++y).getPiece();
            					   if(P2==null) {
            						   //may throw execption un useful power or direction
            						   
            					   }else {//second attack my piece or aromred or  piece
                                       if(P2.getOwner()==this.getOwner()) {
                                       	//may exxcepton oocu
                                       }else {               						 
                     							 attack(P2);
                                       }
            					   }
            					 
            					   }else {
            						   //may exception out board
            					   }
            				   }else {//first attack my piece or armored or hero 
            					   if(this.getOwner()==P.getOwner()) {
            						   //may exception occiuped exception 
                					   if(y<5) {
                      					 Piece P2=getGame().getCellAt(x, ++y).getPiece();
                      					   if(P2==null) {
                      						   //may throw execption un useful power or direction
                      						   
                      					   }else {//second attack my piece or aromred or  piece
                                                 if(P2.getOwner()==this.getOwner()) {
                                                 	//may exxcepton oocu
                                                 }else {
                               							 attack(P2);
                                                 }
                      					   }
                      					 
                      					   }
            					   }else {
            							   attack(P);
                    					   if(y<5) {
                          					 Piece P2=getGame().getCellAt(x, ++y).getPiece();
                          					   if(P2==null) {
                          						   //may throw execption un useful power or direction
                          						   
                          					   }else {//second attack my piece or aromred or  piece
                                                if(P2.getOwner()==this.getOwner()) {
                                                	//may exxcepton oocu
                                                }else {
                              							 attack(P2);
                                                }
                          					   }
                          					 
                          					   }else {
                          						   //may exception out board
                          					   }
            							   
            						   
            					   }
            					   
            				   }
            				   
            			   }else {
            				   //may execeptionoutnoard
            			   }
            		   }
            		   if((d==Direction.UP)) {
            			   if(x>0) {
            				   Piece P=getGame().getCellAt(--x, y).getPiece();
            				   if(P==null){
            					   if(x>0) {
            					 Piece P2=getGame().getCellAt(--x, y).getPiece();
            					   if(P2==null) {
            						   //may throw execption un useful power or direction
            						   
            					   }else {//second attack my piece or aromred or  piece
                                       if(P2.getOwner()==this.getOwner()) {
                                       	//may exxcepton oocu
                                       }else {
                     							 attack(P2);
                                       }
            					   }
            					 
            					   }else {
            						   //may exception out board
            					   }
            				   }else {//first attack my piece or armored or hero 
            					   if(this.getOwner()==P.getOwner()) {
            						   //may exception occiuped exception 
                					   if(x>0) {
                      					 Piece P2=getGame().getCellAt(--x, y).getPiece();
                      					   if(P2==null) {
                      						   //may throw execption un useful power or direction
                      						   
                      					   }else {//second attack my piece or aromred or  piece
                                                 if(P2.getOwner()==this.getOwner()) {
                                                 	//may exxcepton oocu
                                                 }else {
                              							 attack(P2);                    							
                                                 }
                      					   }
                      					 
                      					   }
            					   }else {
            							   attack(P);
                    					   if(x>0) {
                          					 Piece P2=getGame().getCellAt(--x, y).getPiece();
                          					   if(P2==null) {
                          						   //may throw execption un useful power or direction

                          					   }else {//second attack my piece or aromred or  piece
                                                if(P2.getOwner()==this.getOwner()) {
                                                	//may exxcepton oocu
                                                }else {            						  
                              							 attack(P2);
                              		
                                                }
                          					   }
                          					 
                          					   }else {
                          						   //may exception out board
                          					   }
            							   
            						   
            					   }
            					   
            				   }
                    		
                    				   
                    			   }else {
                    				   //may execeptionoutnoard
                    			   }
                    		   }
            		   if((d==Direction.LEFT)) {
            			   if(y>0) {
            				   Piece P=getGame().getCellAt(x, --y).getPiece();
            				   if(P==null){
            					   if(y>0) {
            					 Piece P2=getGame().getCellAt(x, --y).getPiece();
            					   if(P2==null) {
            						   //may throw execption un useful power or direction
            						   
            					   }else {//second attack my piece or aromred or  piece
                                       if(P2.getOwner()==this.getOwner()) {
                                       	//may exxcepton oocu
                                       }else {
                                    	   attack(P2);
                                       }
            					   }
            					 
            					   }else {
            						   //may exception out board
            					   }
            				   }else {//first attack my piece or armored or hero 
            					   if(this.getOwner()==P.getOwner()) {
            						   //may exception occiuped exception 
            						   if(y>0) {
                      					 Piece P2=getGame().getCellAt(x, --y).getPiece();
                      					   if(P2==null) {
                      						   //may throw execption un useful power or direction
                      						   
                      					   }else {//second attack my piece or aromred or  piece
                                                 if(P2.getOwner()==this.getOwner()) {
                                                 	//may exxcepton oocu
                                                 }else {
                                                	 attack(P2);
                                                 }
                      					   }
                      					 
                      					   }
            					   }else {
            						   
            							   attack(P);
                    					   if(y>0) {
                          					 Piece P2=getGame().getCellAt(x, --y).getPiece();
                          					   if(P2==null) {
                          						   //may throw execption un useful power or direction
                          						   
                          					   }else {//second attack my piece or aromred or  piece
                                                if(P2.getOwner()==this.getOwner()) {
                                                	//may exxcepton oocu
                                                }else {
                                                	attack(P2);
                                                }
                          					   }
                          					 
                          					   }else {
                          						   //may exception out board
                          					   }
            							   
            						   }
            					   
            					   
            				   }

            				   
            				   
            			   }else {
            				   //may execeptionoutnoard
            			   }
            		   }
    				   
            	   }else {
            		   throw new InvalidPowerDirectionException ("this direction is not allowed by the game rules",this,d);
            	   }
             	  this.setPowerUsed(true);
				   getGame().switchTurns();  

               }else {
            	   throw new PowerAlreadyUsedException(this);
               }

		 }else {
			 throw  new WrongTurnException(this);
		 }
	}

	}

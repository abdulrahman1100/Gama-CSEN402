package model.pieces.heroes;

import java.awt.Point;

import exceptions.InvalidPowerTargetException;
import exceptions.OccupiedCellException;
import exceptions.PowerAlreadyUsedException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;

public class Medic extends ActivatablePowerHero {

	public Medic(Player player, Game game, String name) {
		super(player, game, name);
	}

	@Override
	public String toString() {
		return "M";
	}
	public void move(Direction r) throws WrongTurnException , UnallowedMovementException, OccupiedCellException {
		
		
		if((r==Direction.DOWN)||(r==Direction.LEFT)||(r==Direction.RIGHT)||(r==Direction.UP)) {
			super.move(r);
		}
		else {
			throw new UnallowedMovementException("Read the Game Description ",this, r);
		}

		
	}


	@Override
	public void usePower(Direction d, Piece target, Point newPos) throws WrongTurnException, PowerAlreadyUsedException, InvalidPowerTargetException {
		if(this.getOwner()==getGame().getCurrentPlayer()) {
	    	if(!(this.isPowerUsed())) {

	    		if(d==Direction.DOWN) {
	    			moveDown();
	    			int x=getPosI();
	    			int y=getPosJ();			
	    			moveUp();
	    			if(target.getOwner()==this.getOwner()) {
	    				
	    				if(this.getOwner().getDeadCharacters().contains(target)) {
	    				Piece p=getGame().getCellAt(x, y).getPiece();
	    				if(p==null) {
	    					if(target instanceof ActivatablePowerHero) {
	    						((ActivatablePowerHero) target).setPowerUsed(false);
	    					}else {
	    						if(target instanceof Armored) {
	    							((Armored) target).setArmorUp(true);
	    						}
	    					}
	    					  getGame().getCellAt(x, y).setPiece(target);
	    					  target.setPosI(x);
	    					  target.setPosJ(y);
	    					  this.getOwner().getDeadCharacters().remove(target);
	    				}else {
	    				  throw new InvalidPowerTargetException ("the target location is occupied",this,target);
	    				}
	    				}else {
	    					throw new InvalidPowerTargetException ("the target piece has not been eliminated before, so it cannot be revived",this,target);
	    				}
	    				
	    			}else {
	    				throw new InvalidPowerTargetException ("the target piece belongs to the enemy team",this,target);
	    			}
	    		}
	    		if(d==Direction.UP) {
	    			moveUp();
	    			int x=getPosI();
	    			int y=getPosJ();
	    			moveDown();
	    			if(target.getOwner()==this.getOwner()) {
	    				if(this.getOwner().getDeadCharacters().contains(target)) {
	    				Piece p=getGame().getCellAt(x, y).getPiece();
	    				if(p==null) {
	    					if(target instanceof ActivatablePowerHero) {
	    						((ActivatablePowerHero) target).setPowerUsed(false);
	    					}else {
	    						if(target instanceof Armored) {
	    							((Armored) target).setArmorUp(true);
	    						}
	    					}
	    					getGame().getCellAt(x, y).setPiece(target);
	    					  target.setPosI(x);
	    					  target.setPosJ(y);
		    					this.getOwner().getDeadCharacters().remove(target);
	    				}else {
	    				  throw new InvalidPowerTargetException ("the target location is occupied",this,target);
	    				}
	    				}else {
	    					throw new InvalidPowerTargetException ("the target piece has not been eliminated before, so it cannot be revived",this,target);
	    				}
	    				
	    			}else {
	    				throw new InvalidPowerTargetException ("the target piece belongs to the enemy team",this,target);
	    			}
	    		}
	    		if(d==Direction.DOWNLEFT) {
	    			moveDownLeft();
	    			int x=getPosI();
	    			int y=getPosJ();
	    			moveUpRight();
	    			if(target.getOwner()==this.getOwner()) {
	    				if(this.getOwner().getDeadCharacters().contains(target)) {
	    				Piece p=getGame().getCellAt(x, y).getPiece();
	    				if(p==null) {
	    					if(target instanceof ActivatablePowerHero) {
	    						((ActivatablePowerHero) target).setPowerUsed(false);
	    					}else {
	    						if(target instanceof Armored) {
	    							((Armored) target).setArmorUp(true);
	    						}
	    					}
	    					getGame().getCellAt(x, y).setPiece(target);
	    					  target.setPosI(x);
	    					  target.setPosJ(y);
	    					  this.getOwner().getDeadCharacters().remove(target);
	    				}else {
	    				  throw new InvalidPowerTargetException ("the target location is occupied",this,target);
	    				}
	    				}else {
	    					throw new InvalidPowerTargetException ("the target piece has not been eliminated before, so it cannot be revived",this,target);
	    				}
	    				
	    			}else {
	    				throw new InvalidPowerTargetException ("the target piece belongs to the enemy team",this,target);
	    			}
	    		}
	    		if(d==Direction.DOWNRIGHT) {
	    			moveDownRight();
	    			int x=getPosI();
	    			int y=getPosJ();
	    			moveUpLeft();
	    			if(target.getOwner()==this.getOwner()) {
	    				if(this.getOwner().getDeadCharacters().contains(target)) {
	    				Piece p=getGame().getCellAt(x, y).getPiece();
	    				if(p==null) {
	    					if(target instanceof ActivatablePowerHero) {
	    						((ActivatablePowerHero) target).setPowerUsed(false);
	    					}else {
	    						if(target instanceof Armored) {
	    							((Armored) target).setArmorUp(true);
	    						}
	    					}
	    					getGame().getCellAt(x, y).setPiece(target);
	    					  target.setPosI(x);
	    					  target.setPosJ(y);
	    					  this.getOwner().getDeadCharacters().remove(target);
	    				}else {
	    				  throw new InvalidPowerTargetException ("the target location is occupied",this,target);
	    				}
	    				}else {
	    					throw new InvalidPowerTargetException ("the target piece has not been eliminated before, so it cannot be revived",this,target);
	    				}
	    				
	    			}else {
	    				throw new InvalidPowerTargetException ("the target piece belongs to the enemy team",this,target);
	    			}
	    		}
	    		if(d==Direction.UPRIGHT) {
	    			moveUpRight();
	    			int x=getPosI();
	    			int y=getPosJ();
	    			moveDownLeft();
	    			if(target.getOwner()==this.getOwner()) {
	    				if(this.getOwner().getDeadCharacters().contains(target)) {
	    				Piece p=getGame().getCellAt(x, y).getPiece();
	    				if(p==null) {
	    					if(target instanceof ActivatablePowerHero) {
	    						((ActivatablePowerHero) target).setPowerUsed(false);
	    					}else {
	    						if(target instanceof Armored) {
	    							((Armored) target).setArmorUp(true);
	    						}
	    					}
	    					getGame().getCellAt(x, y).setPiece(target);
	    					  target.setPosI(x);
	    					  target.setPosJ(y);
	    					  this.getOwner().getDeadCharacters().remove(target);
	    				}else {
	    				  throw new InvalidPowerTargetException ("the target location is occupied",this,target);
	    				}
	    				}else {
	    					throw new InvalidPowerTargetException ("the target piece has not been eliminated before, so it cannot be revived",this,target);
	    				}
	    				
	    			}else {
	    				throw new InvalidPowerTargetException ("the target piece belongs to the enemy team",this,target);
	    			}
	    		} 
	    		if(d==Direction.UPLEFT) {
	    			moveUpLeft();
	    			int x=getPosI();
	    			int y=getPosJ();
	    			moveDownRight();
	    			if(target.getOwner()==this.getOwner()) {
	    				if(this.getOwner().getDeadCharacters().contains(target)) {
	    				Piece p=getGame().getCellAt(x, y).getPiece();
	    				if(p==null) {
	    					if(target instanceof ActivatablePowerHero) {
	    						((ActivatablePowerHero) target).setPowerUsed(false);
	    					}else {
	    						if(target instanceof Armored) {
	    							((Armored) target).setArmorUp(true);
	    						}
	    					}
	    					getGame().getCellAt(x, y).setPiece(target);
	    					  target.setPosI(x);
	    					  target.setPosJ(y);
	    					  this.getOwner().getDeadCharacters().remove(target);
	    				}else {
	    				  throw new InvalidPowerTargetException ("the target location is occupied",this,target);
	    				}
	    				}else {
	    					throw new InvalidPowerTargetException ("the target piece has not been eliminated before, so it cannot be revived",this,target);
	    				}
	    				
	    			}else {
	    				throw new InvalidPowerTargetException ("the target piece belongs to the enemy team",this,target);
	    			}
	    		}
	    		if(d==Direction.RIGHT) {
	    			moveRight();
	    			int x=getPosI();
	    			int y=getPosJ();
	    			moveLeft();
	    			if(target.getOwner()==this.getOwner()) {
	    				if(this.getOwner().getDeadCharacters().contains(target)) {
	    				Piece p=getGame().getCellAt(x, y).getPiece();
	    				if(p==null) {
	    					if(target instanceof ActivatablePowerHero) {
	    						((ActivatablePowerHero) target).setPowerUsed(false);
	    					}else {
	    						if(target instanceof Armored) {
	    							((Armored) target).setArmorUp(true);
	    						}
	    					}
	    					getGame().getCellAt(x, y).setPiece(target);
	    					  target.setPosI(x);
	    					  target.setPosJ(y);
	    					  this.getOwner().getDeadCharacters().remove(target);
	    				}else {
	    				  throw new InvalidPowerTargetException ("the target location is occupied",this,target);
	    				}
	    				}else {
	    					throw new InvalidPowerTargetException ("the target piece has not been eliminated before, so it cannot be revived",this,target);
	    				}
	    				
	    			}else {
	    				throw new InvalidPowerTargetException ("the target piece belongs to the enemy team",this,target);
	    			}
	    		} 
	    		if(d==Direction.LEFT) {
	    			moveLeft();
	    			int x=getPosI();
	    			int y=getPosJ();
	    			moveRight();
	    			if(target.getOwner()==this.getOwner()) {
	    				if(this.getOwner().getDeadCharacters().contains(target)) {
	    				Piece p=getGame().getCellAt(x, y).getPiece();
	    				if(p==null) {
	    					if(target instanceof ActivatablePowerHero) {
	    						((ActivatablePowerHero) target).setPowerUsed(false);
	    					}else {
	    						if(target instanceof Armored) {
	    							((Armored) target).setArmorUp(true);
	    						}
	    					}
	    					getGame().getCellAt(x, y).setPiece(target);
	    					  target.setPosI(x);
	    					  target.setPosJ(y);
	    					  this.getOwner().getDeadCharacters().remove(target);
	    				}else {
	    				  throw new InvalidPowerTargetException ("the target location is occupied",this,target);
	    				}
	    				}else {
	    					throw new InvalidPowerTargetException ("the target piece has not been eliminated before, so it cannot be revived",this,target);
	    				}
	    				
	    			}else {
	    				throw new InvalidPowerTargetException ("the target piece belongs to the enemy team",this,target);
	    			}
	    		} 
	    		this.setPowerUsed(true);
	    		getGame().switchTurns();
	    		
	    	} else {
	    		throw new PowerAlreadyUsedException ("Anta ast5dmt el power rkaz",this);
	    	}
	    } else {
	  	  throw new WrongTurnException("Not Your Turn",this);
              }
	    }
	}
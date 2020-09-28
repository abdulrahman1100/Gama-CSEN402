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

public class Tech extends ActivatablePowerHero {

	public Tech(Player player, Game game, String name) {
		super(player, game, name);
	}

	@Override
	public String toString() {
		return "T";
	}
	public void move(Direction r) throws WrongTurnException , UnallowedMovementException, OccupiedCellException {
		
		
		if((r==Direction.DOWNRIGHT)||(r==Direction.DOWNLEFT)||(r==Direction.UPLEFT)||(r==Direction.UPRIGHT)) {
			super.move(r);
		}
		else {
			throw new UnallowedMovementException("Read The Description",this, r);
		}

		
	}


	@Override
	public void usePower(Direction d, Piece target, Point newPos) throws WrongTurnException, PowerAlreadyUsedException, InvalidPowerTargetException {
		if(this.getOwner()==getGame().getCurrentPlayer()) {
			if(!(this.isPowerUsed())) {
				this.setPowerUsed(true);
				if(newPos!=null) {
				Piece p=getGame().getCellAt((int)newPos.getX(), (int)newPos.getY()).getPiece();
				if(p==null) {
					System.out.println(target.getOwner()+" "+this.getOwner());
					if(this.getOwner()==target.getOwner()) {
						getGame().getCellAt(target.getPosI(), target.getPosJ()).setPiece(null);
						getGame().getCellAt((int)newPos.getX(), (int)newPos.getY()).setPiece(target);
						target.setPosI((int)newPos.getX());
						target.setPosJ((int)newPos.getY());
					}else {
						throw new InvalidPowerTargetException("the target piece doesn’t belong to the same team",this, target);
					}
				}else {
					throw new InvalidPowerTargetException("the target location is occupied",this, target);
				}
				
				}else {
					if(this.getOwner()==target.getOwner()) {
						if(target instanceof ActivatablePowerHero) {
							if(((ActivatablePowerHero) target).isPowerUsed()) {
								((ActivatablePowerHero) target).setPowerUsed(false);
							}else {
								throw new InvalidPowerTargetException("the target piece did not use its power yet",this, target);
							}
						}
						if(target instanceof Armored) {
							if(!((Armored) target).isArmorUp()) {
								((Armored) target).setArmorUp(true);
							}else {
								throw new InvalidPowerTargetException("the target piece did not use its power yet",this, target);	
							}
						}
						
						
					}else {
						if(target instanceof ActivatablePowerHero) {
							if(!((ActivatablePowerHero) target).isPowerUsed()) {
								((ActivatablePowerHero) target).setPowerUsed(true);
							}else {
								throw new InvalidPowerTargetException("the enemy has already used its power and cannot be hacked",this, target);
							}
						}
						if(target instanceof Armored) {
							if(((Armored) target).isArmorUp()) {
								((Armored) target).setArmorUp(false);
							}else {
								throw new InvalidPowerTargetException("the enemy has already used its power and cannot be hacked",this, target);	
							}
						}
					}
					
				}
				////
			}else {
				throw new PowerAlreadyUsedException ("Anta ast5dmt el poweer Rakz",this);
			}
			}else {
			throw new WrongTurnException("Not Your Turn",this);
		}
}
}

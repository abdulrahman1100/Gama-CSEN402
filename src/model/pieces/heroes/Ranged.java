package model.pieces.heroes;

import java.awt.Point;

import exceptions.InvalidPowerDirectionException;
import exceptions.PowerAlreadyUsedException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;

public class Ranged extends ActivatablePowerHero {

	public Ranged(Player player, Game game, String name) {
		super(player, game, name);
	}

	public String toString() {
		return "R";
	}
//getGame().switchTurns();
	@Override
	public void usePower(Direction d, Piece target, Point newPos) throws WrongTurnException, PowerAlreadyUsedException, InvalidPowerDirectionException {
	
		if(this.getOwner()==getGame().getCurrentPlayer()) {
			if(!(this.isPowerUsed())){
			  if((d==Direction.DOWN)||(d==Direction.UP)||(d==Direction.RIGHT)||(d==Direction.LEFT)) {
				  int x=this.getPosI();
				  int y=this.getPosJ();
				  if(d==Direction.DOWN) {
					  if(x!=6) {
					  while(x<6) {
						  Piece p=getGame().getCellAt(++x, y).getPiece();
						  if(p==null&&x==6) {
							  throw new InvalidPowerDirectionException ("this direction will result in hitting no enemies ",this,d);
						  }else {
							  if(p==null) {
								  continue;
							  }else {
								  if(p.getOwner()==this.getOwner()) {
									  //occupied
									  throw new InvalidPowerDirectionException (" this direction will result in hitting a friendly piece",this,d);
								  }else {
									  attack(p);
									  break;
								  }
							  }
						  }
					  }
					  }else {
						  throw new InvalidPowerDirectionException ("this direction will result in hitting no enemies ",this,d);
					  }
				  }
				  if(d==Direction.UP) {
					  if(x!=0) {
					  while(x>0) {
						  Piece p=getGame().getCellAt(--x, y).getPiece();
						  if(p==null&&x==0) {
							  throw new InvalidPowerDirectionException ("this direction will result in hitting no enemies ",this,d);
						  }else {
							  if(p==null) {
								  continue;
							  }else {
								  if(p.getOwner()==this.getOwner()) {
									  //occupied
									  throw new InvalidPowerDirectionException (" this direction will result in hitting a friendly piece",this,d);
								  }else {
									  attack(p);
									  break;
								  }
							  }
						  }
					  }
					  }else {
						  throw new InvalidPowerDirectionException ("this direction will result in hitting no enemies ",this,d);
					  }
				  }
				  if(d==Direction.LEFT) {
					  if(y!=0) {
					  while(y>0) {
						  Piece p=getGame().getCellAt(x, --y).getPiece();
						  if(p==null&&y==0) {
							  throw new InvalidPowerDirectionException ("this direction will result in hitting no enemies ",this,d);
						  }else {
							  if(p==null) {
								  continue;
							  }else {
								  if(p.getOwner()==this.getOwner()) {
									  //occupied
									  throw new InvalidPowerDirectionException (" this direction will result in hitting a friendly piece",this,d);
								  }else {
									  attack(p);
									  break;
								  }
							  }
						  }
					  }
					  }else {
						  throw new InvalidPowerDirectionException ("this direction will result in hitting no enemies ",this,d);
					  }
				  }
				  if(d==Direction.RIGHT) {
					  if(y!=5) {
					  while(y<5) {
						  Piece p=getGame().getCellAt(x, ++y).getPiece();
						  if(p==null&&y==5) {
							  throw new InvalidPowerDirectionException ("this direction will result in hitting no enemies ",this,d);
						  }else {
							  if(p==null) {
								  continue;
							  }else {
								  if(p.getOwner()==this.getOwner()) {
									  //occupied
									  throw new InvalidPowerDirectionException (" this direction will result in hitting a friendly piece",this,d);
								  }else {
									  attack(p);
									  break;
								  }
							  }
						  }
					  }
					  }else {
						  throw new InvalidPowerDirectionException (" this direction will result in hitting no enemies ",this,d);
					  }
				  }
				  getGame().switchTurns();
				  this.setPowerUsed(true);
			 }else {
				throw new InvalidPowerDirectionException (" this direction is not allowed",this,d);
			 }
				
				
			}else {
				throw new PowerAlreadyUsedException("Anta Ast5dmt El Power Rakz",this);
			}
		}else {
			throw new WrongTurnException("Not Your Turn",this);
		}
	}
}

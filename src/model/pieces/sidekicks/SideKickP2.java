package model.pieces.sidekicks;

import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;

public class SideKickP2 extends SideKick {

	public SideKickP2(Game game, String name) {
		super(game.getPlayer2(), game, name);
	}
	public void move(Direction r) throws WrongTurnException , UnallowedMovementException, OccupiedCellException {
		
		
			if((r==Direction.DOWNRIGHT)||(r==Direction.DOWNLEFT)||(r==Direction.LEFT)||(r==Direction.RIGHT)||(r==Direction.DOWN)) {
				super.move(r);
			}
			else {
				throw new UnallowedMovementException("Read The Description",this, r);
			}
	
			
		}
				  
	}


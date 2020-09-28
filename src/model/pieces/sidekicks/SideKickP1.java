package model.pieces.sidekicks;

import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;

public class SideKickP1 extends SideKick {

	public SideKickP1(Game game, String name) {
		super(game.getPlayer1(), game, name);
	}

	public void move(Direction r) throws WrongTurnException , UnallowedMovementException, OccupiedCellException {
		
		
		if((r==Direction.UP)||(r==Direction.UPLEFT)||(r==Direction.LEFT)||(r==Direction.RIGHT)||(r==Direction.UPRIGHT)) {
			super.move(r);
		}
		else {
			throw new UnallowedMovementException("Read The Description",this, r);
		}

		
	}

}

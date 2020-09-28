package exceptions;

import model.game.Direction;
import model.pieces.Piece;
import model.pieces.heroes.ActivatablePowerHero;

@SuppressWarnings("serial")
public class WrongTurnException extends GameActionException {

	public WrongTurnException(Piece trigger) {
		super(trigger);
	}

	public WrongTurnException(String s, Piece trigger) {
		super(s, trigger);
	}

}

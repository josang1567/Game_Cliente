package models.responses;

import java.io.Serializable;

import models.Game;
import models.User;

public class Response_Buy extends Response implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean accepted;
	private User user;
	private Game game;
	
	public Response_Buy() {
		super("buy");
	}

	public Response_Buy(boolean accepted, User user, Game game) {
		super("buy");
		this.accepted = accepted;
		this.user = user;
		this.game = game;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Response_Buy other = (Response_Buy) obj;
		if (accepted != other.accepted)
			return false;
		if (game == null) {
			if (other.game != null)
				return false;
		} else if (!game.equals(other.game))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Response_Buy [accepted=" + accepted + ", user=" + user + ", game=" + game + "]";
	}
	
	
	
}


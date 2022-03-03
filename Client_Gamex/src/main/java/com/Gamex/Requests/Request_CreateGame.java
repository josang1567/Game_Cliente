package com.Gamex.Requests;

import com.Gamex.Model.Game;

public class Request_CreateGame extends Request{
	
	Game game;

	public Request_CreateGame(Game game) {
		super("register");
		this.game = game;
	}
	
	public Request_CreateGame() {
		super("register");
		this.game = new Game();
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((game == null) ? 0 : game.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request_CreateGame other = (Request_CreateGame) obj;
		if (game == null) {
			if (other.game != null)
				return false;
		} else if (!game.equals(other.game))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Request_CreateGame [game=" + game + "]";
	}
	
	
}
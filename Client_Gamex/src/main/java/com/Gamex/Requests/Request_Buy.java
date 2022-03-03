package com.Gamex.Requests;

import com.Gamex.Model.Game;
import com.Gamex.Model.User;

public class Request_Buy extends Request{
	
	Game game;
	User user;
	public Request_Buy(Game game, User user) {
		super("request");
		this.game = game;
		this.user = user;
	}
	public Request_Buy() {
		super("request");
		this.game = new Game();
		this.user = new User();
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((game == null) ? 0 : game.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Request_Buy other = (Request_Buy) obj;
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
		return "Request_Buy [game=" + game + ", user=" + user + "]";
	}
	
	
	
}
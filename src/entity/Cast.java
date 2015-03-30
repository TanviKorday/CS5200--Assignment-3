package entity;

public class Cast {
	
	private int id;
	
	private String characterName;
	
	private Movie movie;
	
	private Actor actor;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public Cast(String characterName, Movie movie, Actor actor) {
		super();
		this.characterName = characterName;
		this.movie = movie;
		this.actor = actor;
	}

	public Cast() {
		super();
	}
	
	

}

package entity;

import java.sql.Date;
import java.util.List;

public class Movie {
	
	private int id;
	
	private String title;
	
	private String posterImage;
	
	private Date releaseDate;
	
	private List<Comment> comments;
	
	private List<Cast> casts;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPosterImage() {
		return posterImage;
	}

	public void setPosterImage(String posterImage) {
		this.posterImage = posterImage;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Cast> getCasts() {
		return casts;
	}

	public void setCasts(List<Cast> casts) {
		this.casts = casts;
	}

	public Movie(int id, String title, String posterImage, Date releaseDate,
			List<Comment> comments, List<Cast> casts) {
		super();
		this.id = id;
		this.title = title;
		this.posterImage = posterImage;
		this.releaseDate = releaseDate;
		this.comments = comments;
		this.casts = casts;
	}

	public Movie() {
		super();
	}
	
	

}

package entity;

import java.sql.Date;

public class Comment {
	
	private int id;
	
	private String comment;
	
	private Date commentDate;
	
	private User user;
	
	private Movie movie;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Comment(int id, String comment, Date commentDate, User user,
			Movie movie) {
		super();
		this.id = id;
		this.comment = comment;
		this.commentDate = commentDate;
		this.user = user;
		this.movie = movie;
	}

	public Comment() {
		super();
	}
	
	

}

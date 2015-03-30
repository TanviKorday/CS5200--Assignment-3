package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Comment;

public class CommentManager {
	
	public static Connection getConnection()
	{
		try {
			Connection conn =
				       DriverManager.getConnection("jdbc:mysql://localhost/cs5200", "root" , "root");
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public void createComment(Comment comment){
		Connection connection = getConnection();
		String sql = "Insert into comment (comment, commentDate, username, movieId) VALUES (?, ?, ?, ?)";
		PreparedStatement statement = null;
			try {
				statement = connection.prepareStatement(sql);
				statement.setString(1, comment.getComment());
				statement.setDate(2, new Date(comment.getCommentDate().getTime()));
				statement.setString(3, comment.getUser().getUsername());
				statement.setInt(4, comment.getMovie().getId());
				statement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				
					try {
						if (statement != null)
							statement.close();
						closeConnection(connection);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
			
		
	}
	
	public List<Comment> readAllComments()
	{
		Connection connection = getConnection();
		String sql = "Select * from comment";
		Comment comment = null;
		List<Comment> comments = new ArrayList<Comment>();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next()){
				comment = new Comment();
				comment.setId(results.getInt("id"));
				comment.setComment(results.getString("comment"));
				comment.setCommentDate(results.getDate("commentDate"));
				comment.setUser(UserManager.readUser(results.getString("username")));
				comment.setMovie(MovieManager.readMovie(results.getInt("movieId")));
				comments.add(comment);
				//System.out.println(firstName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			closeConnection(connection);
		}
		return comments;
	}
	
	private static void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public static List<Comment> readAllCommentsForUsername(String username) {
		Connection connection = getConnection();
		String sql = "Select * from comment where username = ?";
		Comment comment = null;
		List<Comment> comments = new ArrayList<Comment>();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next()){
				comment = new Comment();
				comment.setId(results.getInt("id"));
				comment.setComment(results.getString("comment"));
				comment.setCommentDate(results.getDate("commentDate"));
				comment.setUser(UserManager.readUser(username));
				comment.setMovie(MovieManager.readMovie(results.getInt("movieId")));
				comments.add(comment);
				//System.out.println(firstName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			closeConnection(connection);
		}
		return comments;
	}

	public static List<Comment> readAllCommentsForMovie(int movieId) {
		Connection connection = getConnection();
		String sql = "Select * from comment where movieId = ?";
		Comment comment = null;
		List<Comment> comments = new ArrayList<Comment>();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next()){
				comment = new Comment();
				comment.setId(results.getInt("id"));
				comment.setComment(results.getString("comment"));
				comment.setCommentDate(results.getDate("commentDate"));
				comment.setUser(UserManager.readUser(results.getString("username")));
				comment.setMovie(MovieManager.readMovie(movieId));
				comments.add(comment);
				//System.out.println(firstName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			closeConnection(connection);
		}
		return comments;
	}
	
	public Comment readCommentForId(int commentId)
	{
		Connection connection = getConnection();
		String sql = "Select * from comment";
		Comment comment = null;
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next()){
				comment = new Comment();
				comment.setId(results.getInt("id"));
				comment.setComment(results.getString("comment"));
				comment.setCommentDate(results.getDate("commentDate"));
				comment.setUser(UserManager.readUser(results.getString("username")));
				comment.setMovie(MovieManager.readMovie(results.getInt("movieId")));
				//System.out.println(firstName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			closeConnection(connection);
		}
		return comment;
	}
	public void updateComment(int commentId, Comment comment){
		Connection connection = getConnection();
		String sql = "Update comment set comment = ?, commentDate = ? where id = ?";
		PreparedStatement statement = null;
			try {
				statement = connection.prepareStatement(sql);
				statement.setString(1, comment.getComment());
				statement.setDate(2, comment.getCommentDate());
				statement.setInt(3, commentId);
				statement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				
					try {
						if (statement != null)
							statement.close();
						closeConnection(connection);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
		
	}
	
public void deleteComment(int commentId){
	Connection connection = getConnection();
	String sql1 = "Delete from comment where id = ?";
	PreparedStatement statement = null;
	try {
		statement = connection.prepareStatement(sql1);
		statement.setInt(1, commentId);
		statement.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		
			try {
				if (statement != null)
					statement.close();
				closeConnection(connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	
	}

}

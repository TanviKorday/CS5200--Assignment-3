package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Actor;
import entity.Movie;

public class MovieManager {
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
	

	
	public void createMovie(Movie movie){
		Connection connection = getConnection();
		String sql = "Insert into movie (title, posterImage, releaseDate) VALUES (?, ?, ?)";
		PreparedStatement statement = null;
			try {
				statement = connection.prepareStatement(sql);
				statement.setString(1, movie.getTitle());
				statement.setString(2, movie.getPosterImage());
				statement.setDate(3, new Date(movie.getReleaseDate().getTime()));
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
	
	public List<Movie> readAllMovies()
	{
		Connection connection = getConnection();
		String sql = "Select * from movie";
		Movie movie = null;
		List<Movie> movies = new ArrayList<Movie>();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next()){
				movie = new Movie();
				movie.setTitle(results.getString("title"));
				movie.setPosterImage(results.getString("posterImage"));
				movie.setReleaseDate(results.getDate("releaseDate"));
				movie.setCasts(CastManager.readAllCastForActor(movie.getId()));
				movie.setComments(CommentManager.readAllCommentsForMovie(movie.getId()));
				movies.add(movie);
				//System.out.println(firstName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			closeConnection(connection);
		}
		return movies;
	}
	
	public static Movie readMovie(int movieId)
	{
		Connection connection = getConnection();
		String sql = "Select * from movie where id = ?";
		Movie movie = null;
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, movieId);
			ResultSet results = statement.executeQuery();
			while(results.next()){
				movie = new Movie();
				movie.setTitle(results.getString("title"));
				movie.setPosterImage(results.getString("posterImage"));
				movie.setReleaseDate(results.getDate("releaseDate"));
				movie.setCasts(CastManager.readAllCastForActor(movie.getId()));
				movie.setComments(CommentManager.readAllCommentsForMovie(movie.getId()));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			closeConnection(connection);
		}
		return movie;
	}
	
	public void updateMovie(int movieId, Movie movie){
		Connection connection = getConnection();
		String sql = "Update movie set title = ?, posterImage = ?, releaseDate = ? where id = ?";
		PreparedStatement statement = null;
			try {
				statement = connection.prepareStatement(sql);
				statement.setString(1, movie.getTitle());
				statement.setString(2, movie.getPosterImage());
				statement.setDate(3, movie.getReleaseDate());
				statement.setInt(4, movieId);
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
	
public void deleteMovie(int movieId){
	Connection connection = getConnection();
	String sql1 = "Delete from cast where movieId = ?";
	String sql2 = "Delete from comment where movieid = ?";
	String sql3 = "Delete from movie where id = ?";
	PreparedStatement statement = null;
	try {
		statement = connection.prepareStatement(sql1);
		statement.setInt(1, movieId);
		statement.executeUpdate();
		statement.close();
		statement = connection.prepareStatement(sql2);
		statement.setInt(1, movieId);
		statement.executeUpdate();
		statement.close();
		statement = connection.prepareStatement(sql3);
		statement.setInt(1, movieId);
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
	
	
	
	private static void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

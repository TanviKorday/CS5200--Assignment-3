package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Cast;
import entity.Comment;

public class CastManager {
	
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
	

	public void createCast(Cast cast){
		Connection connection = getConnection();
		String sql = "Insert into cast (characterName, actorId, movieId) VALUES (?, ?, ?)";
		PreparedStatement statement = null;
			try {
				statement = connection.prepareStatement(sql);
				statement.setString(1, cast.getCharacterName());
				statement.setInt(2, cast.getActor().getId());
				statement.setInt(3, cast.getMovie().getId());
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
	
	public List<Cast> readAllCast()
	{
		Connection connection = getConnection();
		String sql = "Select * from cast";
		Cast cast = null;
		List<Cast> casts = new ArrayList<Cast>();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next()){
				cast = new Cast();
				cast.setId(results.getInt("id"));
				cast.setCharacterName(results.getString("characterName"));
				cast.setActor(ActorManager.readActor(results.getInt("actorId")));
				cast.setMovie(MovieManager.readMovie(results.getInt("movieId")));
				casts.add(cast);
				//System.out.println(firstName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			closeConnection(connection);
		}
		return casts;
	}
	
	public static List<Cast> readAllCastForActor(int actorId)
	{
		Connection connection = getConnection();
		String sql = "Select * from cast where actorId = ?";
		Cast cast = null;
		List<Cast> casts = new ArrayList<Cast>();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next()){
				cast = new Cast();
				cast.setId(results.getInt("id"));
				cast.setCharacterName(results.getString("characterName"));
				cast.setActor(ActorManager.readActor(actorId));
				cast.setMovie(MovieManager.readMovie(results.getInt("movieId")));
				casts.add(cast);
				//System.out.println(firstName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			closeConnection(connection);
		}
		return casts;
	}
	
	public List<Cast> readAllCastForMovie(int movieId)
	{
		Connection connection = getConnection();
		String sql = "Select * from cast where movieId = ?";
		Cast cast = null;
		List<Cast> casts = new ArrayList<Cast>();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next()){
				cast = new Cast();
				cast.setId(results.getInt("id"));
				cast.setCharacterName(results.getString("characterName"));
				cast.setActor(ActorManager.readActor(results.getInt("actorId")));
				cast.setMovie(MovieManager.readMovie(movieId));
				casts.add(cast);
				//System.out.println(firstName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			closeConnection(connection);
		}
		return casts;
	}
	
	public void updateCast(int castId, Cast cast){
		Connection connection = getConnection();
		String sql = "Update cast set characterName = ?, movieId = ?, actorId = ? where id = ?";
		PreparedStatement statement = null;
			try {
				statement = connection.prepareStatement(sql);
				statement.setString(1, cast.getCharacterName());
				statement.setInt(2, cast.getMovie().getId());
				statement.setInt(3, cast.getActor().getId());
				statement.setInt(4, castId);
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
	
public void deleteComment(int castId){
	Connection connection = getConnection();
	String sql1 = "Delete from cast where id = ?";
	PreparedStatement statement = null;
	try {
		statement = connection.prepareStatement(sql1);
		statement.setInt(1, castId);
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

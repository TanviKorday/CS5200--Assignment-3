package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import entity.Actor;



public class ActorManager {
	
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
	
	public void createActor(Actor actor){
		Connection connection = getConnection();
		String sql = "Insert into actor (firstName, lastName, dateOfBirth) VALUES (?, ?, ?)";
		PreparedStatement statement = null;
			try {
				statement = connection.prepareStatement(sql);
				statement.setString(1, actor.getFirstName());
				statement.setString(2, actor.getLastName());
				statement.setDate(3, new Date(actor.getDateOfBirth().getTime()));
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
	
	public List<Actor> readAllActors()
	{
		Connection connection = getConnection();
		String sql = "Select * from actor";
		Actor actor = null;
		List<Actor> actors = new ArrayList<Actor>();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next()){
				actor = new Actor();
				actor.setId(results.getInt("id"));
				actor.setFirstName(results.getString("firstName"));
				actor.setLastName(results.getString("lastName"));
				actor.setDateOfBirth(results.getDate("dateOfBirth"));
				actor.setCasts(CastManager.readAllCastForActor(actor.getId()));
				actors.add(actor);
				//System.out.println(firstName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			closeConnection(connection);
		}
		return actors;
	}
	
	public static Actor readActor(int actorId)
	{
		Connection connection = getConnection();
		String sql = "Select * from actor where id = ?";
		Actor actor = null;
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, actorId);
			ResultSet results = statement.executeQuery();
			while(results.next()){
				actor = new Actor();
				actor.setId(results.getInt("id"));
				actor.setFirstName(results.getString("firstName"));
				actor.setLastName(results.getString("lastName"));
				actor.setDateOfBirth(results.getDate("dateOfBirth"));
				actor.setCasts(CastManager.readAllCastForActor(actor.getId()));
				//System.out.println(firstName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			closeConnection(connection);
		}
		return actor;
	}
	
	
	public void updateActor(int actorId, Actor actor){
		Connection connection = getConnection();
		String sql = "Update actor set firstname = ?, lastName = ?, dateOfBirth = ? where id = ?";
		PreparedStatement statement = null;
			try {
				statement = connection.prepareStatement(sql);
				statement.setString(1,actor.getFirstName());
				statement.setString(2, actor.getLastName());
				statement.setDate(3, new Date(actor.getDateOfBirth().getTime()));
				statement.setInt(4, actorId);
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
	
public void deleteActor(int actorId){
	Connection connection = getConnection();
	String sql1 = "Delete from cast where actorId = ?";
	String sql2 = "Delete from actor where id = ?";
	PreparedStatement statement = null;
	try {
		statement = connection.prepareStatement(sql1);
		statement.setInt(1, actorId);
		statement.executeUpdate();
		statement.close();
		statement = connection.prepareStatement(sql2);
		statement.setInt(1, actorId);
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

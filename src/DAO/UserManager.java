package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.User;



public class UserManager {
	
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
	public void createUser(User user){
		Connection connection = getConnection();
		String sql = "Insert into user (username, password, firstName, lastName, email, dateOfBirth) VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = null;
			try {
				statement = connection.prepareStatement(sql);
				statement.setString(1, user.getUsername());
				statement.setString(2, user.getPassword());
				statement.setString(3, user.getFirstName());
				statement.setString(4, user.getLastName());
				statement.setString(5, user.getEmail());
				statement.setDate(6, new Date(user.getDateOfBirth().getTime()));
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
	
	public List<User> readAllUsers()
	{
		Connection connection = getConnection();
		String sql = "Select * from user";
		User user = null;
		List<User> users = new ArrayList<User>();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next()){
				user = new User();
				user.setUsername(results.getString("username"));
				user.setPassword(results.getString("password"));
				user.setFirstName(results.getString("firstName"));
				user.setLastName(results.getString("lastName"));
				user.setEmail(results.getString("email"));
				user.setDateOfBirth(results.getDate("dateOfBirth"));
				user.setComments(CommentManager.readAllCommentsForUsername(user.getUsername()));
				users.add(user);
				//System.out.println(firstName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			closeConnection(connection);
		}
		return users;
	}
	
	public static User readUser(String username)
	{
		Connection connection = getConnection();
		String sql = "Select * from user where username = ?";
		User user = null;
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet results = statement.executeQuery();
			while(results.next()){
				user = new User();
				user.setUsername(results.getString("username"));
				user.setPassword(results.getString("password"));
				user.setFirstName(results.getString("firstName"));
				user.setLastName(results.getString("lastName"));
				user.setEmail(results.getString("email"));
				user.setDateOfBirth(results.getDate("dateOfBirth"));
				user.setComments(CommentManager.readAllCommentsForUsername(user.getUsername()));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			closeConnection(connection);
		}
		return user;
	}
	
	
	public void updateUser(String username, User user){
		Connection connection = getConnection();
		String sql = "Update user set username = ?, password = ?, firstname = ?, lastName = ?, email = ?, dateOfBirth = ? where username = ?";
		PreparedStatement statement = null;
			try {
				statement = connection.prepareStatement(sql);
				statement.setString(1, user.getUsername());
				statement.setString(2, user.getPassword());
				statement.setString(3, user.getFirstName());
				statement.setString(4, user.getLastName());
				statement.setString(5, user.getEmail());
				statement.setDate(6, new Date(user.getDateOfBirth().getTime()));
				statement.setString(7, username);
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
	
public void deleteUser(String username){
	Connection connection = getConnection();
	String sql1 = "Delete from comment where username = ?";
	String sql2 = "Delete from user where username = ?";
	PreparedStatement statement = null;
	try {
		statement = connection.prepareStatement(sql1);
		statement.setString(1, username);
		statement.executeUpdate();
		statement.close();
		statement = connection.prepareStatement(sql2);
		statement.setString(1, username);
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

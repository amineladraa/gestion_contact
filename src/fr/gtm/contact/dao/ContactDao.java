package fr.gtm.contact.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.gtm.contact.entities.Contact;

public class ContactDao {
	
	private String url;
	private String user;
	private String pswd;
	
	public ContactDao(String driver, String url, String user, String pswd) throws ClassNotFoundException {
		Class.forName(driver);
		this.url = url;
		this.user = user;
		this.pswd = pswd;
	}
	
	public Contact findContactById(long id) throws SQLException {
		Connection connection = DriverManager.getConnection(url, user, pswd);
		String sql = "SELECT * FROM personnes WHERE pk LIKE '"+id+"%'";
		Statement statement = connection.createStatement();
		ResultSet rs= statement.executeQuery(sql);
		
			Contact c =createContact(rs);
		
		connection.close();
		return c;
	}
	
	public Contact findContactByCivilite(String civilite) throws SQLException {
		Connection connection = DriverManager.getConnection(url, user, pswd);
		String sql = "SELECT * FROM personnes WHERE civilite LIKE '"+civilite+"%'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet rs= statement.executeQuery(sql);
		
			Contact c =createContact(rs);

		connection.close();
		return c;
	}
	
	private Contact createContact(ResultSet rs) throws SQLException {
		Contact c = new Contact();
		c.setPrenom(rs.getString("prenom"));
		c.setCivilite(rs.getString("civilite"));
		c.setNom(rs.getString("nom"));
		c.setId(rs.getLong("pk"));
		return c;
	}
	
	private boolean save(Contact c) throws SQLException {
		Connection connection = DriverManager.getConnection(url, user, pswd);
		String sql = "INSERT INTO personnes (civilite,nom,prenom) VALUES (?,?,?)";
		PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		{
		statement.setString(1,c.getCivilite());
		statement.setString(2,c.getNom());
		statement.setString(3,c.getPrenom());
		}
		statement.executeUpdate();
		ResultSet generatedKeys = statement.getGeneratedKeys();
			if(generatedKeys.next()) {
				c.setId(generatedKeys.getLong(1));
		}
		return false;
		
	}
	
	private boolean update(Contact c) throws SQLException {
		Connection connection = DriverManager.getConnection(url, user, pswd);
		String sql = "UPDATE personnes SET civilite= ?, nom= ?, prenom= ?";
		PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		{
		statement.setString(1,c.getCivilite());
		statement.setString(2,c.getNom());
		statement.setString(3,c.getPrenom());
		}
		statement.executeUpdate();
		ResultSet generatedKeys = statement.getGeneratedKeys();
			if(generatedKeys.next()) {
				c.setId(generatedKeys.getLong(1));
		}
		return false;
		
	}
	private boolean remove(Contact c) throws SQLException {
		Connection connection = DriverManager.getConnection(url, user, pswd);
		String sql = "DELETE from personnes where pk LIKE '"+c.getId()+"%'";
		PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		{
		statement.setString(1,c.getCivilite());
		statement.setString(2,c.getNom());
		statement.setString(3,c.getPrenom());
		}
		statement.executeUpdate();
		ResultSet generatedKeys = statement.getGeneratedKeys();
			if(generatedKeys.next()) {
				c.setId(generatedKeys.getLong(1));
		}
		return false;
		
	}
}

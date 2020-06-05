package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import domaine.Utilisateur;

public class UtilisateurDAO
{

	private String url = "jdbc:mysql://localhost/gestionformations?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String sql_login = "root";
	private String sql_password = "";
	
	
	public void createUtilisateur(String nom, String prenom, String mail, String mdp)
	{
		Connection cn = null;
		Statement st = null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection(url, sql_login, sql_password);
			st = cn.createStatement();
			
			String sql = "INSERT INTO user (nom, prenom, mail, mdp) VALUES ('"+ nom +"', '"+ prenom +"', '"+ mail +"', '"+ mdp +"')";
			
			st.executeUpdate(sql);
			
			cn.close();
			st.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean getUser(String login, String mdp)
	{
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(url, sql_login, sql_password);
			st = cn.createStatement();
			
			String sql = "SELECT * FROM user WHERE mail = '"+ login +"' AND mdp = '"+ mdp +"'";
			
			rs = st.executeQuery(sql);
			
			if(rs.next())
			{
				return true;
			}
			cn.close();
			st.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public Utilisateur getUtilisateur(String login)
	{
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(url, sql_login, sql_password);
			st = cn.createStatement();
			
			String sql = "SELECT * FROM user WHERE mail = '"+ login +"'";
			
			rs = st.executeQuery(sql);
			
			if(rs.next())
			{
				return new Utilisateur(rs.getString("mail"), rs.getString("mdp"), rs.getString("nom"), rs.getString("prenom"));
			}
			cn.close();
			st.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Utilisateur> getUtilisateurs()
	{
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Utilisateur> users = new ArrayList<Utilisateur>();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(url, sql_login, sql_password);
			st = cn.createStatement();
			
			String sql = "SELECT * FROM user";
			rs = st.executeQuery(sql);
			
			while(rs.next())
			{
				users.add(new Utilisateur(rs.getString("mail"), rs.getString("mdp"), rs.getString("nom"), rs.getString("prenom")));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e )
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				cn.close();
				st.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return users;
	}
	
	public void deleteUtilisateur(String mail)
	{
		Connection cn = null;
		Statement st = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(url, sql_login, sql_password);
			st = cn.createStatement();
			
			String sql = "DELETE FROM user WHERE mail = '"+ mail +"'";
			
			st.executeUpdate(sql);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				cn.close();
				st.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void updateUtilisateur(String last_mail, String name, String first_name, String new_mail, String password)
	{
		Connection cn = null;
		Statement st = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(url, sql_login, sql_password);
			st = cn.createStatement();
			
			String sql = "UPDATE user(nom, prenom, mail, mdp)  VALUES('"+ name +"', '"+ first_name +"', '"+ new_mail +"', '"+ password +"') WHERE mail = '"+ last_mail +"'";
			
			st.executeUpdate(sql);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				cn.close();
				st.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
}

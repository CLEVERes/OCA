package es.studium;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

public class ModeloOCA
{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/oca";
	String user = "userOCA";
	String password = "passwordOCA";
	String sentenciaSQL = "";

	Connection connection = null;
	Statement statement = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	public ModeloOCA()
	{
		try
		{
			Class.forName(driver);
		} catch (ClassNotFoundException cnfe)
		{
			cnfe.printStackTrace();
		}
	}

	public void ranking(String nombreGanador, int tiradasGanador)
	{
		try
		{
			connection = DriverManager.getConnection(url, user, password);

			sentenciaSQL = "insert into jugadores values(null, ? , ? )";
			preparedStatement = connection.prepareStatement(sentenciaSQL);
			preparedStatement.setString(1, nombreGanador);
			preparedStatement.setInt(2, tiradasGanador);
			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.close();
		} 
		
		catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}

	}
	
	public ArrayList<String> consultarRanking()
	{
		ArrayList<String> lista = new ArrayList<String>();
		
		try
		{
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
					
			sentenciaSQL = "Select * from jugadores order by tiradasJugador asc limit 10";
			resultSet = statement.executeQuery(sentenciaSQL);
			
			while(resultSet.next())
			{
				lista.add(resultSet.getString("nombreJugador") + " --- Tiradas: " + resultSet.getString("tiradasJugador"));
			}

			resultSet.close();
			statement.close();
			connection.close();
		} 
		
		catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}
		
		return lista;
	}

	public int lanzarDado()
	{
		Random aleatorio = new Random();
		int dado = aleatorio.nextInt(1, 7);
		return dado;
	}
}

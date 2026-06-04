package es.studium;

import java.awt.Button;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
	
	Button[] btnList;
	
	public ModeloOCA()
	{
		try
		{
			Class.forName(driver);
		}
		catch (ClassNotFoundException cnfe)
		{
			cnfe.printStackTrace();
		}
	}
	
	public int lanzarDado()
	{
		Random aleatorio = new Random();
		int dado = aleatorio.nextInt(1,7);
		return dado;
	}
}

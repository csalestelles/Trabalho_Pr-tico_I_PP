package BibliotecaSIS;

import java.sql.*;

public class BancoDeDados {
	
	private static String url = "jdbc:mysql://localhost:3306/Biblioteca?useSSL=false";
	private static String user = "biblioteca_admin";
	private static String pass = "senha";
	protected static Connection conexao = null;
	
	public static final byte INCLUSAO = 1;
	public static final byte ALTERACAO = 2;
	public static final byte EXCLUSAO = 3;
	public static final byte EMPRESTIMO = 4;
	public static int CODIGO = 0;
	
	public BancoDeDados()
	{
		if(conexao==null)
			conecta();
	}
	
	private static boolean conecta()
	{
		try
		{
			conexao = DriverManager.getConnection(url, user, pass);
		    return true;
		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
		    return false;
		}
	}
	
	public static boolean desconecta()
	{
		try
		{
			conexao.close();
			return true;
		}
		catch (SQLException e)
		{
			return false;
		}
	}
	
	public int acessaCodigo(String nome) throws SQLException
	{
		String sql;
		PreparedStatement statement;
		ResultSet resultSet;
		int valor;
		BancoDeDados bd = new BancoDeDados();
		
		sql = "INSERT INTO Codigos VALUES (NULL, ?)";
		statement = bd.conexao.prepareStatement(sql);
		(statement).setString(1, nome);
		statement.executeUpdate();
		sql = "SELECT * FROM Codigos WHERE tituloCod=?";
		statement = BancoDeDados.conexao.prepareStatement(sql);
		statement.setString(1, nome);
		resultSet = statement.executeQuery();
		resultSet.next();
		valor = resultSet.getInt(1);
		
		return valor;
	}

}

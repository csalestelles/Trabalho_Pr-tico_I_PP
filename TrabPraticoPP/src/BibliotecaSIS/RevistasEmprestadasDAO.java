package BibliotecaSIS;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class RevistasEmprestadasDAO
{
	public TituloEmprestado revistaEmprestada;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private String men, sql;
	public BancoDeDados bd;
	private Date data;
	
	public RevistasEmprestadasDAO()
	{
		bd = new BancoDeDados();
		revistaEmprestada = new TituloEmprestado();
	}
	
	public boolean localizar() throws SQLException
	{
		sql = "SELECT * FROM TitulosEmprestados WHERE codigoUser=?";
		statement = BancoDeDados.conexao.prepareStatement(sql);
		statement.setInt(1, revistaEmprestada.getCodigoUser());
		resultSet = statement.executeQuery();
		if (resultSet.next())
		{
			revistaEmprestada.setCodigoLivro(resultSet.getInt(2));
			revistaEmprestada.setCodigoUser(resultSet.getInt(1));
			
			return true;
		}
		return false;
	}
	
	public String insercao(TituloEmprestado revista)
	{
		try
		{
			men = "Empréstimo realizado com sucesso!";
			sql = "INSERT INTO TitulosEmprestados VALUES (?, ?, ?)";
			statement = bd.conexao.prepareStatement(sql);
			statement.setInt(1, revista.getCodigoUser());
			statement.setInt(2, revista.getCodigoLivro());
			statement.setDate(3, (java.sql.Date) revistaEmprestada.getDataDevolucao());
			if (statement.executeUpdate() == 0)
			{
				men = "Não foi possível fazer o empréstimo!";
			}
		}
		catch(SQLException error){men = "Não foi possível fazer o empréstimo!"; error.printStackTrace();}
		return men;
	}
	
	public String devolucao()
	{
		try
		{
			men = "Devolução realizada!";
			sql = "DELETE FROM TitulosEmprestados WHERE codigoUser=?";
			statement = bd.conexao.prepareStatement(sql);
			statement.setInt(1, revistaEmprestada.getCodigoUser());
		}
		catch(SQLException error){men = "Não foi possível fazer o empréstimo!"; error.printStackTrace();}
		return men;
	}
}

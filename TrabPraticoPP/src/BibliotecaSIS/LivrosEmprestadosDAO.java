package BibliotecaSIS;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LivrosEmprestadosDAO
{
	public TituloEmprestado livroEmprestado;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private String men, sql;
	public BancoDeDados bd;
	private Date data;
	
	public LivrosEmprestadosDAO()
	{
		bd = new BancoDeDados();
		livroEmprestado = new TituloEmprestado();
	}
	
	public boolean localizar() throws SQLException
	{
		sql = "SELECT * FROM TitulosEmprestados WHERE codigoUser=?";
		statement = BancoDeDados.conexao.prepareStatement(sql);
		statement.setInt(1, livroEmprestado.getCodigoUser());
		resultSet = statement.executeQuery();
		if (resultSet.next())
		{
			livroEmprestado.setCodigoLivro(resultSet.getInt(2));
			livroEmprestado.setCodigoUser(resultSet.getInt(1));
			
			return true;
		}
		return false;
	}
	
	public String insercao(TituloEmprestado livro)
	{
		try
		{
			men = "Empréstimo realizado com sucesso!";
			sql = "INSERT INTO TitulosEmprestados VALUES (?, ?, ?)";
			statement = bd.conexao.prepareStatement(sql);
			statement.setInt(1, livro.getCodigoUser());
			statement.setInt(2, livro.getCodigoLivro());
			statement.setDate(3, (java.sql.Date) livroEmprestado.getDataDevolucao());
			if (statement.executeUpdate() == 0)
			{
				men = "Não foi possível fazer o empréstimo!";
			}
		}
		catch(SQLException error){men = "Não foi possível fazer o empréstimo!"; error.printStackTrace();}
		return men;
	}
	
	public void devolucao(int codigoUser)
	{
		try
		{
			sql = "DELETE FROM TitulosEmprestados WHERE codigoUser=?";
			statement = bd.conexao.prepareStatement(sql);
			statement.setInt(1, codigoUser);
		}
		catch(SQLException error){error.printStackTrace();}
	}
}

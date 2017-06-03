package BibliotecaSIS;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MonografiasEmprestadasDAO
{
	public TituloEmprestado docEmprestado;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private String men, sql;
	public BancoDeDados bd;
	private Date data;
	
	public MonografiasEmprestadasDAO()
	{
		bd = new BancoDeDados();
		docEmprestado = new TituloEmprestado();
	}
	
	public boolean localizar() throws SQLException
	{
		sql = "SELECT * FROM TitulosEmprestados WHERE codigoUser=?";
		statement = BancoDeDados.conexao.prepareStatement(sql);
		statement.setInt(1, docEmprestado.getCodigoUser());
		resultSet = statement.executeQuery();
		if (resultSet.next())
		{
			docEmprestado.setCodigoLivro(resultSet.getInt(2));
			docEmprestado.setCodigoUser(resultSet.getInt(1));
			
			return true;
		}
		return false;
	}
	
	public String insercao(TituloEmprestado doc)
	{
		try
		{
			men = "Empréstimo realizado com sucesso!";
			sql = "INSERT INTO LivrosEmprestados VALUES (?, ?, ?)";
			statement = bd.conexao.prepareStatement(sql);
			statement.setInt(1, doc.getCodigoUser());
			statement.setInt(2, doc.getCodigoLivro());
			statement.setDate(3, (java.sql.Date) docEmprestado.getDataDevolucao());
			if (statement.executeUpdate() == 0)
			{
				men = "Não foi possível fazer o empréstimo!";
			}
		}
		catch(SQLException error){men = "Não foi possível fazer o empréstimo!"; error.printStackTrace();}
		return men;
	}
	
	public String devolucao(int codigoUser)
	{
		try
		{
			men = "Devolução realizada!";
			sql = "DELETE FROM TitulosEmprestados WHERE codigoUser=?";
			statement = bd.conexao.prepareStatement(sql);
			statement.setInt(1, codigoUser);
		}
		catch(SQLException error){men = "Não foi possível fazer o empréstimo!"; error.printStackTrace();}
		return men;
	}
}



package BibliotecaSIS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class PeriodicosEmprestadosDAO 
{	
	public TituloEmprestado periodicoEmprestado;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private String men, sql;
	public BancoDeDados bd;
	private Date data;
	
	public PeriodicosEmprestadosDAO()
	{
		bd = new BancoDeDados();
		periodicoEmprestado = new TituloEmprestado();
	}
	
	public boolean localizar() throws SQLException
	{
		sql = "SELECT * FROM TitulosEmprestados WHERE codigoUser=?";
		statement = BancoDeDados.conexao.prepareStatement(sql);
		statement.setInt(1, periodicoEmprestado.getCodigoUser());
		resultSet = statement.executeQuery();
		if (resultSet.next())
		{
			periodicoEmprestado.setCodigoLivro(resultSet.getInt(2));
			periodicoEmprestado.setCodigoUser(resultSet.getInt(1));
			
			return true;
		}
		return false;
	}
	
	public String insercao(TituloEmprestado periodico)
	{
		try
		{
			men = "Empréstimo realizado com sucesso!";
			sql = "INSERT INTO TitulosEmprestados VALUES (?, ?, ?)";
			statement = bd.conexao.prepareStatement(sql);
			statement.setInt(1, periodico.getCodigoUser());
			statement.setInt(2, periodico.getCodigoLivro());
			statement.setDate(3, (java.sql.Date) periodicoEmprestado.getDataDevolucao());
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

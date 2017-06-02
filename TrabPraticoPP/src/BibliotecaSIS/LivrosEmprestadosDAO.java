package BibliotecaSIS;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LivrosEmprestadosDAO
{
	public LivroEmprestado livroEmprestado;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private String men, sql;
	public BancoDeDados bd;
	private Date data;
	
	public LivrosEmprestadosDAO()
	{
		bd = new BancoDeDados();
		livroEmprestado = new LivroEmprestado();
	}
	
	public boolean localizar() throws SQLException
	{
		sql = "SELECT * FROM LivrosEmprestados WHERE codigoUser=?";
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
	
	public String insercao(LivroEmprestado livro)
	{
		try
		{
			men = "Empréstimo realizado com sucesso!";
			sql = "INSERT INTO LivrosEmprestados VALUES (?, ?, ?)";
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
	
	public String devolucao()
	{
		try
		{
			men = "Devolução realizada!";
			sql = "DELETE FROM LivrosEmprestados WHERE codigoUser=?";
			statement = bd.conexao.prepareStatement(sql);
			statement.setInt(1, livroEmprestado.getCodigoUser());
		}
		catch(SQLException error){men = "Não foi possível fazer o empréstimo!"; error.printStackTrace();}
		return men;
	}

}

class LivroEmprestado
{
	int codigoUser, codigoLivro;
	Date dataDevolucao;

	public int getCodigoUser() {
		return codigoUser;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public void setCodigoUser(int codigoUser) {
		this.codigoUser = codigoUser;
	}

	public int getCodigoLivro() {
		return codigoLivro;
	}

	public void setCodigoLivro(int codigoLivro) {
		this.codigoLivro = codigoLivro;
	}
	
	
}

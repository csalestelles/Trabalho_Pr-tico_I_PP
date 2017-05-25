package BibliotecaSIS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MonografiaDAO 
{
	public Monografia monografia;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private String men, sql;
	public BancoDeDados bd;
	
	public MonografiaDAO()
	{
		bd = new BancoDeDados();
		monografia = new Monografia();
	}
	
	public boolean localizar()
	{
		sql = "SELECT * FROM Monografias WHERE Titulo=? AND Autor=?";
		try
		{
			statement = bd.conexao.prepareStatement(sql);
			statement.setString(1, monografia.getTitulo());
			statement.setString(2, monografia.getAutor());
			resultSet = statement.executeQuery();
			resultSet.next();
			monografia.setTitulo(resultSet.getString(2));
			monografia.setAutor(resultSet.getString(3));
			monografia.setOrientador(resultSet.getString(4));
			monografia.setTema(resultSet.getString(5));
			monografia.setTipo(resultSet.getString(6));
			monografia.setInstituicao(resultSet.getString(7));
			monografia.setAno(resultSet.getInt(8));
			return true;
		}
		catch(SQLException e){return false;}
	}
	
	public String atualizar(int operacao)
	{
		men = "Operação realizada com sucesso!";
		
		try
		{
			if (operacao == BancoDeDados.INCLUSAO)
			{
				sql = "INSERT INTO Monografias values (NULL, ?, ?, ?, ?, ?, ?, ?)";
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, monografia.getTitulo());
				statement.setString(2, monografia.getAutor());
				statement.setString(3, monografia.getOrientador());
				statement.setString(4, monografia.getTema());
				statement.setString(5, monografia.getTipo());
				statement.setString(6, monografia.getInstituicao());
				statement.setInt(7, monografia.getAno());
			}
			else if(operacao == BancoDeDados.ALTERACAO)
			{
				sql = "UPDATE Monografias SET Orientador=?, Tema=?, Tipo=?, Instituicao=?, Ano=? WHERE Titulo=? AND Autor=?";
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(7, monografia.getAutor());
				statement.setString(1, monografia.getOrientador());
				statement.setString(2, monografia.getTema());
				statement.setString(3, monografia.getTipo());
				statement.setString(4, monografia.getInstituicao());
				statement.setInt(5, monografia.getAno());
				statement.setString(6, monografia.getTitulo());
			}
			else if (operacao == BancoDeDados.EXCLUSAO)
			{
				sql = "DELETE FROM Monografias WHERE Titulo=? AND Autor=?";
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, monografia.getTitulo());
				statement.setString(2, monografia.getAutor());
			}
			if(statement.executeUpdate() == 0)
			{
				men = "Falha na operacao!";
			}
		}
		catch(SQLException g){men = "Falha na operação";}
		return men;
	}
	

}
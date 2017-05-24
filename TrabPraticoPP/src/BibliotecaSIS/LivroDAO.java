package BibliotecaSIS;

import java.sql.*;

public class LivroDAO extends BancoDeDados {
	
	public Livro livro;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private String men, sql;
	public static final byte INCLUSAO = 1;
	public static final byte ALTERACAO = 2;
	public static final byte EXCLUSAO = 3;
	public BancoDeDados bd;
	
	public LivroDAO()
	{
		bd = new BancoDeDados();
		livro = new Livro();
	}
	
	public boolean localizar()
	{
		sql = "SELECT * FROM Livros WHERE Titulo=? AND Edicao=?";
		try
		{
			statement = bd.conexao.prepareStatement(sql);
			statement.setString(1, livro.getTitulo());
			statement.setInt(2, livro.getEdicao());
			resultSet = statement.executeQuery();
			resultSet.next();
			livro.setTitulo(resultSet.getString(2));
			livro.setAutor(resultSet.getString(3));
			livro.setEditora(resultSet.getString(4));
			livro.setIdioma(resultSet.getString(5));
			livro.setAno(resultSet.getInt(6));
			livro.setEdicao(resultSet.getInt(7));
			livro.setNumExemplaresDisponiveis(resultSet.getInt(8));
			return true;
		}
		catch(SQLException e){return false;}
	}
	
	public String atualizar(int operacao)
	{
		men = "Operação realizada com sucesso!";
		
		try
		{
			if (operacao == INCLUSAO)
			{
				sql = "INSERT INTO Livros values (NULL, ?, ?, ?, ?, ?, ?, ?)";
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, livro.getTitulo());
				statement.setString(2, livro.getAutor());
				statement.setString(3, livro.getEditora());
				statement.setString(4, livro.getIdioma());
				statement.setInt(5, livro.getAno());
				statement.setInt(6, livro.getEdicao());
				statement.setInt(7, livro.getNumExemplaresDisponiveis());
			}
			else if(operacao == ALTERACAO)
			{
				sql = "UPDATE Livros SET Autor=?, Editora=?, Idioma=?, Ano=?, Total de exemplares=? WHERE Titulo=? AND Edicao=?";
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, livro.getAutor());
				statement.setString(2, livro.getEditora());
				statement.setString(3, livro.getIdioma());
				statement.setInt(4, livro.getAno());
				statement.setInt(5, livro.getNumExemplaresDisponiveis());
				statement.setString(6, livro.getTitulo());
				statement.setInt(7, livro.getEdicao());
			}
			else if (operacao == EXCLUSAO)
			{
				sql = "DELETE FROM Livros WHERE Titulo=? AND Edicao=?";
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, livro.getTitulo());
				statement.setInt(2, livro.getEdicao());
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
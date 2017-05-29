package BibliotecaSIS;

import java.sql.*;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class LivroDAO extends BancoDeDados {
	
	public Livro livro;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private String men, sql;
	public BancoDeDados bd;
	
	private String campoTitulo, campoAutor, campoEditora, campoIdioma, campoAno, campoEdicao, campoExemplares;
	
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
			if (operacao == BancoDeDados.INCLUSAO)
			{
				sql = "INSERT INTO Livros VALUES (NULL, ?, ?, ?, ?, ?, ?, ?)";
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, livro.getTitulo());
				statement.setString(2, livro.getAutor());
				statement.setString(3, livro.getEditora());
				statement.setString(4, livro.getIdioma());
				statement.setInt(5, livro.getAno());
				statement.setInt(6, livro.getEdicao());
				statement.setInt(7, livro.getNumExemplaresDisponiveis());
			}
			else if(operacao == BancoDeDados.ALTERACAO)
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
			else if (operacao == BancoDeDados.EXCLUSAO)
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
	
	public void setTabela(JTable tabela, JScrollPane scroll) throws SQLException
	{
		String sql = "SELECT * FROM Livros";
		statement = bd.conexao.prepareStatement(sql);
		resultSet = statement.executeQuery();	
		
		@SuppressWarnings("serial")
		DefaultTableModel modelo = new DefaultTableModel(
				new String[]{}, 0) {	
				};
				
		
		int qtdColunas = resultSet.getMetaData().getColumnCount() - 1;		
		for(int indice = 1; indice <= qtdColunas; indice++)
		{
			modelo.addColumn(resultSet.getMetaData().getColumnName(indice+1));
		}
		
		tabela = new JTable(modelo);
		DefaultTableModel dtm = (DefaultTableModel) tabela.getModel();
		
		while(resultSet.next())
		{
			String[] dados = new String[qtdColunas];
			for(int i = 1; i <= qtdColunas; i++)
			{
				dados[i-1] = resultSet.getString(i+1);
			}
			
			dtm.addRow(dados);
			scroll.setViewportView(tabela);
		}
	}

}

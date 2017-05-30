package BibliotecaSIS;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.*;

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
	
	/*
	 * 
	 * OPERAÇÕES NA TABELA
	 * 
	 */
	
	private ArrayList<Livro> listarLivros() throws SQLException
	{
		ArrayList<Livro> list = new ArrayList<Livro>();
		
		sql = "SELECT * FROM Livros";
		statement = BancoDeDados.conexao.prepareStatement(sql);
		resultSet = statement.executeQuery();
		while(resultSet.next())
		{
			Livro livroAdd = new Livro();
			livroAdd.setTitulo(resultSet.getString(2));
			livroAdd.setAutor(resultSet.getString(3));
			livroAdd.setEditora(resultSet.getString(4));
			livroAdd.setIdioma(resultSet.getString(5));
			livroAdd.setAno(resultSet.getInt(6));
			livroAdd.setEdicao(resultSet.getInt(7));
			livroAdd.setNumExemplaresDisponiveis(resultSet.getInt(8));
			list.add(livroAdd);
		}
		
		return list;
	}
	
	public void showLivrosTable(JTable table)
	{
		ArrayList<Livro> listLivros = new ArrayList<Livro>();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
		table.setModel(new DefaultTableModel(
				new String[]{"Título", "Autor", "Editora", "Idioma", "Ano", "Edição", "Exemplares"}, 0) {
				public boolean isCellEditable(int row, int col)
				{	
					return false;
				}
				});
		try 
		{
			listLivros = listarLivros();
		} 
		catch (SQLException e) {e.printStackTrace();}
		
		DefaultTableModel model =(DefaultTableModel)table.getModel();
		model.setNumRows(0);
		Object[] row = new Object[7];
		for (int i=0; i<listLivros.size();i++)
		{
			row[0] = listLivros.get(i).getTitulo();
			row[1] = listLivros.get(i).getAutor();
			row[2] = listLivros.get(i).getEditora();
			row[3] = listLivros.get(i).getIdioma();
			row[4] = listLivros.get(i).getAno();
			row[5] = listLivros.get(i).getEdicao();
			row[6] = listLivros.get(i).getNumExemplaresDisponiveis();
			
			model.addRow(row);
		}
	}
	
	public void removeDaTabela(JTable table, LivroDAO livros) throws SQLException
	{
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		
		if (table.getSelectedRow() != -1)
		{
			int indice = table.getSelectedRow();
			livros.livro.setTitulo((String) table.getValueAt(indice, 0));
			livros.livro.setEdicao((int) table.getValueAt(indice, 5));
			dtm.removeRow(table.getSelectedRow());
			JOptionPane.showMessageDialog(null, livros.atualizar(BancoDeDados.EXCLUSAO));
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Selecione uma linha!");
		}
	}
	
	public void editarTabela()
	{
		
	}

}

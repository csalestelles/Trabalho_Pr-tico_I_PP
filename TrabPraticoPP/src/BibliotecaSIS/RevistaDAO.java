package BibliotecaSIS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class RevistaDAO 
{
	public Revista revista;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private String men, sql;
	public BancoDeDados bd;
	
	public RevistaDAO()
	{
		bd = new BancoDeDados();
		revista = new Revista();
	}
	
	public boolean localizar()
	{
		sql = "SELECT * FROM Revistas WHERE Nome=? AND Edicao=?";
		try
		{
			statement = bd.conexao.prepareStatement(sql);
			statement.setString(1, revista.getNome());
			statement.setString(2, revista.getEdicao());
			resultSet = statement.executeQuery();
			resultSet.next();
			revista.setNome(resultSet.getString(2));
			revista.setTema(resultSet.getString(4));
			revista.setEditora(resultSet.getString(3));
			revista.setAno(resultSet.getString(6));
			revista.setEdicao(resultSet.getString(5));
			revista.setExemplares(resultSet.getString(7));
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
				sql = "INSERT INTO Revistas VALUES (NULL, ?, ?, ?, ?, ?, ?, ?)";
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, revista.getNome());
				statement.setString(2, revista.getEditora());
				statement.setString(3, revista.getTema());
				statement.setString(5, revista.getEdicao());
				statement.setString(6, revista.getAno());
				statement.setString(7, revista.getExemplares());
			}
			else if(operacao == BancoDeDados.ALTERACAO)
			{
				sql = "UPDATE Revistas SET Editora=?, Tema=?, Ano=?, Exemplares=? WHERE Nome=? AND Edicao=?";
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, revista.getEditora());
				statement.setString(2, revista.getTema());
				statement.setString(3, revista.getAno());
				statement.setString(4, revista.getExemplares());
				statement.setString(5, revista.getNome());
				statement.setString(6, revista.getEdicao());
			}
			else if (operacao == BancoDeDados.EXCLUSAO)
			{
				sql = "DELETE FROM Livros WHERE Nome=? AND Edicao=?";
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, revista.getNome());
				statement.setString(2, revista.getEdicao());
			}
			if(statement.executeUpdate() == 0)
			{
				men = "Falha na operacao!";
			}
			else
			{
				if(operacao == BancoDeDados.INCLUSAO)
					RelatorioDAO.atualizarAdicao(5);
				else if (operacao == BancoDeDados.EXCLUSAO)
					RelatorioDAO.atualizarRemocao(5);
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
	
	public ArrayList<Revista> listarRevistas() throws SQLException
	{
		ArrayList<Revista> listRevistas = new ArrayList<Revista>();
		
		sql = "SELECT * FROM Revistas";
		statement = BancoDeDados.conexao.prepareStatement(sql);
		resultSet = statement.executeQuery();
		while(resultSet.next())
		{
			Revista revistaAdd = new Revista();
			revistaAdd.setNome(resultSet.getString(2));
			revistaAdd.setEdicao(resultSet.getString(5));
			revistaAdd.setEditora(resultSet.getString(3));
			revistaAdd.setTema(resultSet.getString(4));
			revistaAdd.setAno(resultSet.getString(6));
			revistaAdd.setExemplares(resultSet.getString(7));
			listRevistas.add(revistaAdd);
		}
		return listRevistas;
	}
	
	public void showRevistasTable(JTable table)
	{
		ArrayList<Revista> listRevistas = new ArrayList<Revista>();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
		table.setModel(new DefaultTableModel(
				new String[]{"Nome", "Edicao", "Editora", "Tema", "Ano", "Exemplares"}, 0) {
				public boolean isCellEditable(int row, int col)
				{	
					return false;
				}
				});
		try 
		{
			listRevistas = listarRevistas();
		} 
		catch (SQLException e) {e.printStackTrace();}
		
		DefaultTableModel model =(DefaultTableModel)table.getModel();
		model.setNumRows(0);
		Object[] row = new Object[7];
		for (int i=0; i<listRevistas.size();i++)
		{
			row[0] = listRevistas.get(i).getNome();
			row[1] = listRevistas.get(i).getEdicao();
			row[2] = listRevistas.get(i).getEditora();
			row[3] = listRevistas.get(i).getTema();
			row[4] = listRevistas.get(i).getAno();
			row[5] = listRevistas.get(i).getExemplares();
			model.addRow(row);
		}
	}
	
	public void removeDaTabela(JTable table, RevistaDAO revistas) throws SQLException
	{
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		
		if (table.getSelectedRow() != -1)
		{
			int indice = table.getSelectedRow();
			revistas.revista.setNome((String) table.getValueAt(indice, 0));
			revistas.revista.setEdicao((String) table.getValueAt(indice, 1));
			dtm.removeRow(table.getSelectedRow());
			JOptionPane.showMessageDialog(null, revistas.atualizar(BancoDeDados.EXCLUSAO));
//			Relatorio.somaExemplares(Relatorio.getExemplares(), -1);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Selecione uma linha!");
		}
	}
	
	public void editarTabela(JTable table, LivroDAO livros)
	{
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		
		if (table.getSelectedRow() != -1)
		{
			int indice = table.getSelectedRow();
			
//			EditarRevista editarRevista = new EditarRevista((String) table.getValueAt(indice, 0), (String) table.getValueAt(indice, 1), 
//						(String) table.getValueAt(indice, 2), (String) table.getValueAt(indice, 3), (String) table.getValueAt(indice, 4),
//						(String) table.getValueAt(indice, 5));
//			editarRevista.main(null);
		}
		
	}
}

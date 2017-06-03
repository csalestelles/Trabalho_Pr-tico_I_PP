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
			statement.setString(1, monografia.getNome());
			statement.setString(2, monografia.getAutor());
			resultSet = statement.executeQuery();
			resultSet.next();
			monografia.setCodigo(resultSet.getInt(1));
			monografia.setTitulo(resultSet.getString(2));
			monografia.setAutor(resultSet.getString(3));
			monografia.setOrientador(resultSet.getString(4));
			monografia.setTema(resultSet.getString(5));
			monografia.setTipo(resultSet.getString(6));
			monografia.setInstituicao(resultSet.getString(7));
			monografia.setAno(resultSet.getString(8));
			monografia.setExemplares(resultSet.getString(9));
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
				monografia.setCodigo(bd.acessaCodigo(monografia.getNome()));
				sql = "INSERT INTO Monografias values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
				statement = bd.conexao.prepareStatement(sql);
				statement.setInt(1, monografia.getCodigo());
				statement.setString(2, monografia.getNome());
				statement.setString(3, monografia.getAutor());
				statement.setString(4, monografia.getOrientador());
				statement.setString(5, monografia.getTema());
				statement.setString(6, monografia.getTipo());
				statement.setString(7, monografia.getInstituicao());
				statement.setString(8, monografia.getAno());
				statement.setString(9, monografia.getExemplares());
			}
			else if(operacao == BancoDeDados.ALTERACAO)
			{
				sql = "UPDATE Monografias SET Titulo=?, Autor=?, Orientador=?, Tema=?, Tipo=?, Instituicao=?, Ano=?, Exemplares=? WHERE Codigo=?";
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(2, monografia.getAutor());
				statement.setString(3, monografia.getOrientador());
				statement.setString(4, monografia.getTema());
				statement.setString(5, monografia.getTipo());
				statement.setString(6, monografia.getInstituicao());
				statement.setString(7, monografia.getAno());
				statement.setString(1, monografia.getNome());
				statement.setInt(9, monografia.getCodigo());
				statement.setString(8, monografia.getExemplares());
			}
			else if (operacao == BancoDeDados.EXCLUSAO)
			{
				sql = "DELETE FROM Monografias WHERE Titulo=? AND Autor=?";
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, monografia.getNome());
				statement.setString(2, monografia.getAutor());
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
	
	private ArrayList<Monografia> listarDocs() throws SQLException
	{
		ArrayList<Monografia> list = new ArrayList<Monografia>();
		
		sql = "SELECT * FROM Monografias";
		statement = BancoDeDados.conexao.prepareStatement(sql);
		resultSet = statement.executeQuery();
		while(resultSet.next())
		{
			Monografia docAdd = new Monografia();
			docAdd.setCodigo(resultSet.getInt(1));
			docAdd.setTitulo(resultSet.getString(2));
			docAdd.setAutor(resultSet.getString(3));
			docAdd.setOrientador(resultSet.getString(4));
			docAdd.setTema(resultSet.getString(5));
			docAdd.setTipo(resultSet.getString(6));
			docAdd.setInstituicao(resultSet.getString(7));
			docAdd.setAno(resultSet.getString(8));
			docAdd.setExemplares(resultSet.getString(9));
			list.add(docAdd);
		}
		return list;
	}
	
	public void showDocsTable(JTable table)
	{
		ArrayList<Monografia> listDocs = new ArrayList<Monografia>();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
		table.setModel(new DefaultTableModel(
				new String[]{"Código", "Título", "Autor", "Orientador", "Tema", "Tipo", "Instituição", "Ano", "Exemplares"}, 0) {
				public boolean isCellEditable(int row, int col)
				{	
					return false;
				}
				});
		try 
		{
			listDocs = listarDocs();
		} 
		catch (SQLException e) {e.printStackTrace();}
		
		DefaultTableModel model =(DefaultTableModel)table.getModel();
		model.setNumRows(0);
		Object[] row = new Object[9];
		for (int i=0; i<listDocs.size();i++)
		{
			row[0] = listDocs.get(i).getCodigo();
			row[1] = listDocs.get(i).getNome();
			row[2] = listDocs.get(i).getAutor();
			row[3] = listDocs.get(i).getOrientador();
			row[4] = listDocs.get(i).getTema();
			row[5] = listDocs.get(i).getTipo();
			row[6] = listDocs.get(i).getInstituicao();
			row[7] = listDocs.get(i).getAno();
			row[8] = listDocs.get(i).getExemplares();
			
			model.addRow(row);
		}
	}
	
	public void removeDaTabela(JTable table, MonografiaDAO docs) throws SQLException
	{
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		
		if (table.getSelectedRow() != -1)
		{
			int indice = table.getSelectedRow();
			docs.monografia.setTitulo((String) table.getValueAt(indice, 1));
			docs.monografia.setAutor((String) table.getValueAt(indice, 2));
			dtm.removeRow(table.getSelectedRow());
			JOptionPane.showMessageDialog(null, docs.atualizar(BancoDeDados.EXCLUSAO));
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Selecione uma linha!");
		}
	}
	
	public void editarTabela(JTable table, MonografiaDAO docs)
	{
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		
		if (table.getSelectedRow() != -1)
		{
			int indice = table.getSelectedRow();
			
			EditarMonografia editarMonografia = new EditarMonografia((int) table.getValueAt(indice, 0), (String) table.getValueAt(indice, 1), (String) table.getValueAt(indice, 2), 
						(String) table.getValueAt(indice, 3), (String) table.getValueAt(indice, 4), (String) table.getValueAt(indice, 5),
						(String) table.getValueAt(indice, 6), (String) table.getValueAt(indice, 7), (String) table.getValueAt(indice, 8));
			editarMonografia.main(null);
		}
		
	}


}

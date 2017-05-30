package BibliotecaSIS;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class PeriodicoDAO {
	
	public Periodico periodico;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private String men, sql;
	public BancoDeDados bd;
	
	public PeriodicoDAO()
	{
		bd = new BancoDeDados();
		periodico = new Periodico();
	}
	
	public boolean localizar()
	{
		sql = "SELECT * FROM Periodicos WHERE Nome=? AND Exemplar=?";
		try
		{
			statement = bd.conexao.prepareStatement(sql);
			statement.setString(1, periodico.getNome());
			statement.setInt(2, periodico.getExemplar());
			resultSet = statement.executeQuery();
			resultSet.next();
			periodico.setNome(resultSet.getString(2));
			periodico.setPeriodo(resultSet.getString(3));
			periodico.setEditora(resultSet.getString(4));
			periodico.setTema(resultSet.getString(5));
			periodico.setAno(resultSet.getInt(6));
			periodico.setExemplar(resultSet.getInt(7));
			periodico.setNumExemplares(resultSet.getInt(8));
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
				sql = "INSERT INTO Periodicos VALUES (NULL, ?, ?, ?, ?, ?, ?, ?)";
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, periodico.getNome());
				statement.setString(2, periodico.getPeriodo());
				statement.setString(3, periodico.getEditora());
				statement.setString(4, periodico.getTema());
				statement.setInt(5, periodico.getAno());
				statement.setInt(6, periodico.getExemplar());
				statement.setInt(7, periodico.getNumExemplares());
			}
			else if(operacao == BancoDeDados.ALTERACAO)
			{
				sql = "UPDATE Periodicos SET Periodo=?, Editora=?, Tema=?, Ano=?, Exemplar=?, Numero de exemplares=?"
						+ " WHERE Nome=? AND Exemplar=?;";
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, periodico.getPeriodo());
				statement.setString(2, periodico.getEditora());
				statement.setString(3, periodico.getTema());
				statement.setInt(4, periodico.getAno());
				statement.setInt(5, periodico.getNumExemplares());
				statement.setString(6, periodico.getNome());
				statement.setInt(7, periodico.getExemplar());
//				statement.executeUpdate();
			}
			else if (operacao == BancoDeDados.EXCLUSAO)
			{
				sql = "DELETE FROM Periodicos WHERE Nome=? AND Exemplar=?";
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, periodico.getNome());
				statement.setInt(2, periodico.getExemplar());
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
	
	public ArrayList<Periodico> listarPeriodicos() throws SQLException
	{
		ArrayList<Periodico> listPeriodicos = new ArrayList<Periodico>();
		
		sql = "SELECT * FROM Periodicos";
		statement = BancoDeDados.conexao.prepareStatement(sql);
		resultSet = statement.executeQuery();
		while(resultSet.next())
		{
			Periodico periodicoAdd = new Periodico();
			periodicoAdd.setNome(resultSet.getString(2));
			periodicoAdd.setPeriodo(resultSet.getString(3));
			periodicoAdd.setEditora(resultSet.getString(4));
			periodicoAdd.setTema(resultSet.getString(5));
			periodicoAdd.setAno(resultSet.getInt(6));
			periodicoAdd.setExemplar(resultSet.getInt(7));
			periodicoAdd.setNumExemplares(resultSet.getInt(8));
			listPeriodicos.add(periodicoAdd);
		}
		return listPeriodicos;
	}
	
	public void showPeriodicosTable(JTable table)
	{
		ArrayList<Periodico> listPeriodicos = new ArrayList<Periodico>();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
		table.setModel(new DefaultTableModel(
				new String[]{"Nome", "Período", "Editora", "Tema","Ano", "Exemplar", "Exemplares"}, 0) {
				public boolean isCellEditable(int row, int col)
				{	
					return false;
				}
				});
		try 
		{
			listPeriodicos = listarPeriodicos();
		} 
		catch (SQLException e) {e.printStackTrace();}
		
		DefaultTableModel model =(DefaultTableModel)table.getModel();
		model.setNumRows(0);
		Object[] row = new Object[7];
		for (int i=0; i<listPeriodicos.size();i++)
		{
			row[0] = listPeriodicos.get(i).getNome();
			row[1] = listPeriodicos.get(i).getPeriodo();
			row[2] = listPeriodicos.get(i).getEditora();
			row[3] = listPeriodicos.get(i).getTema();
			row[4] = listPeriodicos.get(i).getAno();
			row[5] = listPeriodicos.get(i).getExemplar();
			row[6] = listPeriodicos.get(i).getNumExemplares();
			model.addRow(row);
		}
	}
	
	public void removeDaTabela(JTable table, PeriodicoDAO periodicos) throws SQLException
	{
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		
		if (table.getSelectedRow() != -1)
		{
			int indice = table.getSelectedRow();
			periodicos.periodico.setNome((String) table.getValueAt(indice, 0));
			periodicos.periodico.setExemplar((int) table.getValueAt(indice, 5));
			dtm.removeRow(table.getSelectedRow());
			JOptionPane.showMessageDialog(null, periodicos.atualizar(BancoDeDados.EXCLUSAO));
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Selecione uma linha!");
		}
	}

}

package BibliotecaSIS;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

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
		sql = "SELECT * FROM Periodicos WHERE Codigo=?";
		try
		{
			statement = bd.conexao.prepareStatement(sql);
			statement.setInt(1, periodico.getCodigo());
			resultSet = statement.executeQuery();
			resultSet.next();
			periodico.setCodigo(resultSet.getInt(1));
			periodico.setNome(resultSet.getString(2));
			periodico.setPeriodo(resultSet.getString(3));
			periodico.setEditora(resultSet.getString(4));
			periodico.setTema(resultSet.getString(5));
			periodico.setAno(resultSet.getString(6));
			periodico.setExemplar(resultSet.getString(7));
			periodico.setExemplares(resultSet.getString(8));
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
				periodico.setCodigo(bd.acessaCodigo(periodico.getNome()));
				sql = "INSERT INTO Periodicos VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
				statement = bd.conexao.prepareStatement(sql);
				statement.setInt(1, periodico.getCodigo());
				statement.setString(2, periodico.getNome());
				statement.setString(3, periodico.getPeriodo());
				statement.setString(4, periodico.getEditora());
				statement.setString(5, periodico.getTema());
				statement.setString(6, periodico.getAno());
				statement.setString(7, periodico.getExemplar());
				statement.setString(8, periodico.getExemplares());
			}
			else if(operacao == BancoDeDados.ALTERACAO)
			{
				sql = "UPDATE Periodicos SET Nome=?, Periodo=?, Editora=?, Tema=?, Ano=?, Exemplar=?, Exemplares=?"
						+ " WHERE Codigo=?";
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(2, periodico.getPeriodo());
				statement.setString(3, periodico.getEditora());
				statement.setString(4, periodico.getTema());
				statement.setString(5, periodico.getAno());
				statement.setString(7, periodico.getExemplares());
				statement.setString(1, periodico.getNome());
				statement.setString(6, periodico.getExemplar());
				statement.setInt(8, periodico.getCodigo());
			}
			else if (operacao == BancoDeDados.EXCLUSAO)
			{
				sql = "DELETE FROM Periodicos WHERE Nome=? AND Exemplar=?";
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, periodico.getNome());
				statement.setString(2, periodico.getExemplar());
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
		catch(SQLException g){men = "Falha na operação";g.printStackTrace();}
		return men;
	}
	
	public String emprestar(int codigoUser)
	{
		sql = "UPDATE Periodicos SET Exemplares=? WHERE Codigo=?";
		int exemplaresFinal = Integer.parseInt(periodico.getExemplares()) - 1;
		String exempString = "" + exemplaresFinal;
		men = "";
		try {
			statement = bd.conexao.prepareStatement(sql);
			statement.setString(1, exempString);
			statement.setInt(2, periodico.getCodigo());
			if(statement.executeUpdate() != 0)
			{
				RelatorioDAO.atualizarAdicao(2); 
				RelatorioDAO.atualizarAdicao(3);
				PeriodicosEmprestadosDAO periodicosEmprestados = new PeriodicosEmprestadosDAO();
				
				periodicosEmprestados.periodicoEmprestado.setCodigoUser(codigoUser);
				periodicosEmprestados.periodicoEmprestado.setCodigoLivro(periodico.getCodigo());
				Date data = new Date();
				data.setDate(data.getDate()+7);
				java.sql.Date dataSQL = new java.sql.Date(data.getTime());
				periodicosEmprestados.periodicoEmprestado.setDataDevolucao(dataSQL);
				men = periodicosEmprestados.insercao(periodicosEmprestados.periodicoEmprestado);
			}
			else
			{
				men = "Falha na operação!";
			}
			
		} 
		catch (SQLException e) {e.printStackTrace();men = "Falha na operação!";}
		
		return men;
	}
	
	public String devolver(int codigoUser)
	{
		Date data = new Date();
		data.setDate(data.getDate());
		java.sql.Date dataSQL = new java.sql.Date(data.getTime());
		try
		{
			sql = "Select * FROM TitulosEmprestados WHERE codigoUser=?";
			statement = bd.conexao.prepareStatement(sql);
			statement.setInt(1, codigoUser);
			resultSet = statement.executeQuery();
			if(resultSet.first())
			{
				periodico.setCodigo(resultSet.getInt(2));
				PeriodicosEmprestadosDAO periodicosEmprestados = new PeriodicosEmprestadosDAO();
				periodicosEmprestados.devolucao(codigoUser);
				if(dataSQL.compareTo(resultSet.getDate(3)) > 0)
				{
					JOptionPane.showMessageDialog(null, "Sujeito à multa de R$2,50");
					RelatorioDAO.atualizarRemocao(4);
				}
				
				if(localizar())
				{
					sql = "SELECT * FROM Periodicos WHERE Codigo=?";
					statement = bd.conexao.prepareStatement(sql);
					statement.setInt(1, periodico.getCodigo());
					resultSet = statement.executeQuery();
					if(resultSet.first())
					{
						int exemplaresNovo = Integer.parseInt(resultSet.getString(8))+1;
						periodico.setExemplares(exemplaresNovo+"");
						atualizar(BancoDeDados.ALTERACAO);
						men = resultSet.getString(2) + " devolvido!";
						
						sql = "UPDATE Usuarios SET PodeEmprestar=? WHERE Codigo=?";
						statement = bd.conexao.prepareStatement(sql);
						statement.setInt(1, BancoDeDados.SIM);
						statement.setInt(2, codigoUser);
						statement.executeUpdate();
						RelatorioDAO.atualizarRemocao(3);
					}
				}
			}
		}
		catch(SQLException error){men = "Falha";error.printStackTrace();}
		
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
			periodicoAdd.setCodigo(resultSet.getInt(1));
			periodicoAdd.setNome(resultSet.getString(2));
			periodicoAdd.setPeriodo(resultSet.getString(3));
			periodicoAdd.setEditora(resultSet.getString(4));
			periodicoAdd.setTema(resultSet.getString(5));
			periodicoAdd.setAno(resultSet.getString(6));
			periodicoAdd.setExemplar(resultSet.getString(7));
			periodicoAdd.setExemplares(resultSet.getString(8));
			listPeriodicos.add(periodicoAdd);
		}
		return listPeriodicos;
	}
	
	public void showPeriodicosTable(JTable table)
	{
		ArrayList<Periodico> listPeriodicos = new ArrayList<Periodico>();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
		table.setModel(new DefaultTableModel(
				new String[]{"Código","Nome", "Período", "Editora", "Tema", "Ano", "Exemplar", "Exemplares"}, 0) {
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
		Object[] row = new Object[8];
		for (int i=0; i<listPeriodicos.size();i++)
		{
			row[0] = listPeriodicos.get(i).getCodigo();
			row[1] = listPeriodicos.get(i).getNome();
			row[2] = listPeriodicos.get(i).getPeriodo();
			row[3] = listPeriodicos.get(i).getEditora();
			row[4] = listPeriodicos.get(i).getTema();
			row[5] = listPeriodicos.get(i).getAno();
			row[6] = listPeriodicos.get(i).getExemplar();
			row[7] = listPeriodicos.get(i).getExemplares();
			model.addRow(row);
		}
	}
	
	public void removeDaTabela(JTable table, PeriodicoDAO periodicos) throws SQLException
	{
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		
		if (table.getSelectedRow() != -1)
		{
			int indice = table.getSelectedRow();
			periodicos.periodico.setNome((String) table.getValueAt(indice, 1));
			periodicos.periodico.setExemplar((String) table.getValueAt(indice, 6));
			dtm.removeRow(table.getSelectedRow());
			JOptionPane.showMessageDialog(null, periodicos.atualizar(BancoDeDados.EXCLUSAO));
//			Relatorio.somaExemplares(Relatorio.getExemplares(), -1);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Selecione uma linha!");
		}
	}
	
	public void editarTabela(JTable table, PeriodicoDAO periodicos)
	{
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		
		if (table.getSelectedRow() != -1)
		{
			int indice = table.getSelectedRow();
			
			EditarPeriodico editarPeriodico = new EditarPeriodico((int) table.getValueAt(indice, 0), (String) table.getValueAt(indice, 1), 
						(String) table.getValueAt(indice, 2), (String) table.getValueAt(indice, 3), (String) table.getValueAt(indice, 4),
						(String) table.getValueAt(indice, 5), (String) table.getValueAt(indice, 6), (String) table.getValueAt(indice, 7));
			editarPeriodico.main(null);
		}
		
	}

}

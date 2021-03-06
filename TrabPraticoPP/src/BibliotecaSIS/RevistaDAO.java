package BibliotecaSIS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

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
			revista.setCodigo(resultSet.getInt(1));
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
				revista.setCodigo(bd.acessaCodigo(revista.getNome()));
				sql = "INSERT INTO Revistas VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
				statement = bd.conexao.prepareStatement(sql);
				statement.setInt(1, revista.getCodigo());
				statement.setString(2, revista.getNome());
				statement.setString(3, revista.getEditora());
				statement.setString(4, revista.getTema());
				statement.setString(5, revista.getEdicao());
				statement.setString(6, revista.getAno());
				statement.setString(7, revista.getExemplares());
			}
			else if(operacao == BancoDeDados.ALTERACAO)
			{
				sql = "UPDATE Revistas SET Nome=?, Editora=?, Tema=?, Edicao=?, Ano=?, Exemplares=? WHERE Codigo=?";
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(2, revista.getEditora());
				statement.setString(3, revista.getTema());
				statement.setString(5, revista.getAno());
				statement.setString(6, revista.getExemplares());
				statement.setString(1, revista.getNome());
				statement.setString(4, revista.getEdicao());
				statement.setInt(7, revista.getCodigo());
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
	
	public String emprestar(int codigoUser)
	{
		sql = "UPDATE Livros SET Exemplares=? WHERE Codigo=?";
		int exemplaresFinal = Integer.parseInt(revista.getExemplares()) - 1;
		String exempString = "" + exemplaresFinal;
		men = "";
		try {
			statement = bd.conexao.prepareStatement(sql);
			statement.setString(1, exempString);
			statement.setInt(2, revista.getCodigo());
			if(statement.executeUpdate() != 0)
			{
				RelatorioDAO.atualizarAdicao(2); 
				RelatorioDAO.atualizarAdicao(3);
				RevistasEmprestadasDAO revistasEmprestadas = new RevistasEmprestadasDAO();
				
				revistasEmprestadas.revistaEmprestada.setCodigoUser(codigoUser);
				revistasEmprestadas.revistaEmprestada.setCodigoLivro(revista.getCodigo());
				Date data = new Date();
				data.setDate(data.getDate()+7);
				java.sql.Date dataSQL = new java.sql.Date(data.getTime());
				revistasEmprestadas.revistaEmprestada.setDataDevolucao(dataSQL);
				men = revistasEmprestadas.insercao(revistasEmprestadas.revistaEmprestada);
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
				revista.setCodigo(resultSet.getInt(2));
				RevistasEmprestadasDAO revistasEmprestadas = new RevistasEmprestadasDAO();
				revistasEmprestadas.devolucao(codigoUser);
				if(dataSQL.compareTo(resultSet.getDate(3)) > 0)
				{
					JOptionPane.showMessageDialog(null, "Sujeito à multa de R$2,50");
					RelatorioDAO.atualizarRemocao(4);
				}
				
				if(localizar())
				{
					sql = "SELECT * FROM Revistas WHERE Codigo=?";
					statement = bd.conexao.prepareStatement(sql);
					statement.setInt(1, revista.getCodigo());
					resultSet = statement.executeQuery();
					if(resultSet.first())
					{
						int exemplaresNovo = Integer.parseInt(resultSet.getString(7))+1;
						revista.setExemplares(exemplaresNovo+"");
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
	
	public ArrayList<Revista> listarRevistas() throws SQLException
	{
		ArrayList<Revista> listRevistas = new ArrayList<Revista>();
		
		sql = "SELECT * FROM Revistas";
		statement = BancoDeDados.conexao.prepareStatement(sql);
		resultSet = statement.executeQuery();
		while(resultSet.next())
		{
			Revista revistaAdd = new Revista();
			revistaAdd.setCodigo(resultSet.getInt(1));
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
				new String[]{"Código","Nome", "Edicao", "Editora", "Tema", "Ano", "Exemplares"}, 0) {
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
			row[0] = listRevistas.get(i).getCodigo();
			row[1] = listRevistas.get(i).getNome();
			row[2] = listRevistas.get(i).getEdicao();
			row[3] = listRevistas.get(i).getEditora();
			row[4] = listRevistas.get(i).getTema();
			row[5] = listRevistas.get(i).getAno();
			row[6] = listRevistas.get(i).getExemplares();
			model.addRow(row);
		}
	}
	
	public void removeDaTabela(JTable table, RevistaDAO revistas) throws SQLException
	{
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		
		if (table.getSelectedRow() != -1)
		{
			int indice = table.getSelectedRow();
			revistas.revista.setNome((String) table.getValueAt(indice, 1));
			revistas.revista.setEdicao((String) table.getValueAt(indice, 2));
			dtm.removeRow(table.getSelectedRow());
			JOptionPane.showMessageDialog(null, revistas.atualizar(BancoDeDados.EXCLUSAO));
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Selecione uma linha!");
		}
	}
	
	public void editarTabela(JTable table, RevistaDAO revistas)
	{
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		
		if (table.getSelectedRow() != -1)
		{
			int indice = table.getSelectedRow();
			
			EditarRevista editarRevista = new EditarRevista((int) table.getValueAt(indice, 0), (String) table.getValueAt(indice, 1), 
						(String) table.getValueAt(indice, 2), (String) table.getValueAt(indice, 3), (String) table.getValueAt(indice, 4),
						(String) table.getValueAt(indice, 5), (String) table.getValueAt(indice, 6));
			editarRevista.main(null);
		}
		
	}
}

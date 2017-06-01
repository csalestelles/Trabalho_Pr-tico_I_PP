package BibliotecaSIS;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class UsuarioDAO extends BancoDeDados {
	
	public Usuario usuario;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private String men, sql;
	public BancoDeDados bd;
	
	public UsuarioDAO()
	{
		bd = new BancoDeDados();
		usuario = new Usuario();
	}
	
	public boolean localizar()
	{
		sql = "SELECT * FROM Usuarios WHERE User=?";
		try
		{
			statement = BancoDeDados.conexao.prepareStatement(sql);
			statement.setString(1, usuario.getNomeDeUsuario());
			resultSet = statement.executeQuery();
			resultSet.next();
			usuario.setNomeCompleto(resultSet.getString(2));
			usuario.setNomeDeUsuario(resultSet.getString(3));
			usuario.setSenha(resultSet.getString(4));
			usuario.setAnoDeNascimento(resultSet.getString(5));
			return true;
		}
		catch(SQLException erro){return false;}
	}
	
	public String atualizar(int operacao)
	{
		men = "Operação realizada com sucesso!";
		
		try
		{
			if (operacao == BancoDeDados.INCLUSAO)
			{
				sql = "INSERT INTO Usuarios VALUES (NULL, ?, ?, ?, ?)";
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, usuario.getNomeCompleto());
				statement.setString(2, usuario.getNomeDeUsuario());
				statement.setString(3, usuario.getSenha());
				statement.setString(4, usuario.getAnoDeNascimento());
			}
			else if(operacao == BancoDeDados.ALTERACAO)
			{
				sql = "UPDATE Usuarios SET NomeCompleto=?, User=? , Pass=?, DataNascimento=? WHERE Codigo=?";
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, usuario.getNomeCompleto());
				statement.setString(2, usuario.getNomeDeUsuario());
				statement.setString(3, usuario.getSenha());
				statement.setString(4, usuario.getAnoDeNascimento());
				statement.setInt(5, usuario.getCodigo());
			}
			else if (operacao == BancoDeDados.EXCLUSAO)
			{
				sql = "DELETE FROM Usuarios WHERE User=?";
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, usuario.getNomeDeUsuario());
			}
			if(statement.executeUpdate() == 0)
			{
				men = "Falha na operacao!";
			}
			else
			{
				if(operacao == BancoDeDados.INCLUSAO)
					RelatorioDAO.atualizarAdicao(0);
				else if (operacao == BancoDeDados.EXCLUSAO)
					RelatorioDAO.atualizarRemocao(0);
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
	
	public ArrayList<Usuario> listarUsuarios() throws SQLException
	{
		ArrayList<Usuario> list = new ArrayList<Usuario>();
		
		sql = "SELECT * FROM Usuarios";
		statement = BancoDeDados.conexao.prepareStatement(sql);
		resultSet = statement.executeQuery();
		while(resultSet.next())
		{
			Usuario usuarioAdd = new Usuario();
			usuarioAdd.setCodigo(resultSet.getInt(1));
			usuarioAdd.setNomeCompleto(resultSet.getString(2));
			usuarioAdd.setNomeDeUsuario(resultSet.getString(3));
			usuarioAdd.setSenha(resultSet.getString(4));
			usuarioAdd.setAnoDeNascimento(resultSet.getString(5));
			list.add(usuarioAdd);
		}
		return list;
	}
	
	public void showUsuariosTable(JTable table)
	{
		ArrayList<Usuario> listUsuarios = new ArrayList<Usuario>();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
		table.setModel(new DefaultTableModel(
				new String[]{"Nome Completo", "Nome de Usuário", "Senha", "Data de nascimento", "Código"}, 0) {
				public boolean isCellEditable(int row, int col)
				{	
					return false;
				}
				});
		try 
		{
			listUsuarios = listarUsuarios();
		} 
		catch (SQLException e) {e.printStackTrace();}
		
		DefaultTableModel model =(DefaultTableModel)table.getModel();
		model.setNumRows(0);
		Object[] row = new Object[5];
		for (int i=0; i<listUsuarios.size();i++)
		{
			row[0] = listUsuarios.get(i).getNomeCompleto();
			row[1] = listUsuarios.get(i).getNomeDeUsuario();
			row[2] = listUsuarios.get(i).getSenha();
			row[3] = listUsuarios.get(i).getAnoDeNascimento();
			row[4] = listUsuarios.get(i).getCodigo();
			
			model.addRow(row);
		}
	}
	
	public void removeDaTabela(JTable table, UsuarioDAO usuarios) throws SQLException
	{
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		
		if (table.getSelectedRow() != -1)
		{
			int indice = table.getSelectedRow();
			usuarios.usuario.setNomeDeUsuario((String) table.getValueAt(indice, 1));
			dtm.removeRow(table.getSelectedRow());
			JOptionPane.showMessageDialog(null, usuarios.atualizar(BancoDeDados.EXCLUSAO));
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Selecione uma linha!");
		}
	}
	
	public void editarTabela(JTable table, UsuarioDAO usuarios)
	{
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		
		if (table.getSelectedRow() != -1)
		{
			int indice = table.getSelectedRow();
			EditarUsuario editarUsuario = new EditarUsuario((String) table.getValueAt(indice, 0), (String) table.getValueAt(indice, 1), 
						(String) table.getValueAt(indice, 2), (String) table.getValueAt(indice, 3));
			editarUsuario.main(null);
		}
		
	}
}

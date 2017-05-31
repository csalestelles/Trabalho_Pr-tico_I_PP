package BibliotecaSIS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RelatorioDAO 
{
	private static PreparedStatement statement;
	private static ResultSet resultSet;
	private String men;
	private static String sql;
	private static BancoDeDados bd;
	public static Relatorio relatorio;
	
	public static int[] valores; 
//	usuarios, administradores, empReal, empAnd, devAtra, exempCad;

	public RelatorioDAO()
	{
		relatorio = new Relatorio();
		bd = new BancoDeDados();
		sql = "SELECT * FROM Relatório";
		try {
			statement = bd.conexao.prepareStatement(sql);
			resultSet = statement.executeQuery();
			if(!resultSet.next())
			{
				int i = 0;
				while(i < 6)
				{
					sql = "INSERT INTO Relatório VALUES (?, 0)";
					try {
						statement = bd.conexao.prepareStatement(sql);
						statement.setInt(1, i);
						statement.executeUpdate();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					i++;
				}
			}
		} 
		catch (SQLException e1) {e1.printStackTrace();}
		valores = new int[6];
	}
	
	public static void acessarRelatorio() throws SQLException
	{
		sql = "SELECT * FROM Relatório";
		
		statement = bd.conexao.prepareStatement(sql);
		resultSet = statement.executeQuery();
		int i = 0;
		while(resultSet.next())
		{
			valores[i] = resultSet.getInt(2);
			i++;
		}
	}
	
	public static void atualizarAdicao(int tipo) throws SQLException
	{
		sql = "UPDATE Relatório SET Valor=? WHERE Tipo=?";
		statement = bd.conexao.prepareStatement(sql);
		statement.setInt(1, relatorio.getValor()+1);
		statement.setInt(2, tipo);
		statement.executeUpdate();
	}
	
	public static void atualizarRemocao(int tipo) throws SQLException
	{
		sql = "UPDATE Relatório SET Valor=? WHERE Tipo=?";
		statement = bd.conexao.prepareStatement(sql);
		statement.setInt(1, relatorio.getValor()-1);
		statement.setInt(2, tipo);
		statement.executeUpdate();
	}
	
}

package BibliotecaSIS;

import java.sql.*;

public class UsuarioDAO extends BancoDeDados {
	
	public boolean adicionarUsuario(Usuario user)
	{
		try 
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO Usuarios VALUES (NULL ,'" + user.getNomeDeUsuario() + "', '" 
					+ user.getSenha() + "', " + user.getAnoDeNascimento());
			return true;
		}
		catch (SQLException e){return false;}
	}

}

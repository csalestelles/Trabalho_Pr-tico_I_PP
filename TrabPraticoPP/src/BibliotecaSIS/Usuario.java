package BibliotecaSIS;

public class Usuario {

	private String nomeDeUsuario, senha, anoDeNascimento, nomeCompleto;

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getNomeDeUsuario() {
		return nomeDeUsuario;
	}

	public void setNomeDeUsuario(String nomeDeUsuario) {
		this.nomeDeUsuario = nomeDeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getAnoDeNascimento() {
		return anoDeNascimento;
	}

	public void setAnoDeNascimento(String anoDeNascimento) {
		this.anoDeNascimento = anoDeNascimento;
	}
	
}

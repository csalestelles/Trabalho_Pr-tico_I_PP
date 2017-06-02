package BibliotecaSIS;

import java.util.Date;

public class TituloEmprestado 
{	
	int codigoUser, codigoLivro;
	Date dataDevolucao;

	public int getCodigoUser() {
		return codigoUser;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public void setCodigoUser(int codigoUser) {
		this.codigoUser = codigoUser;
	}

	public int getCodigoLivro() {
		return codigoLivro;
	}

	public void setCodigoLivro(int codigoLivro) {
		this.codigoLivro = codigoLivro;
	}
	
}

package BibliotecaSIS;

public class Monografia extends Exemplar
{
	
	private String titulo, autor, orientador, tema, tipo, instituicao, ano, exemplares;
	
	public String getExemplares() {
		return exemplares;
	}

	public void setExemplares(String exemplares) {
		this.exemplares = exemplares;
	}

	private int codigo;
	
	public String getNome() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getOrientador() {
		return orientador;
	}

	public void setOrientador(String orientador) {
		this.orientador = orientador;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	

}

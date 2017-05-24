package BibliotecaSIS;

public class Livro extends LivroDAO {
	
	private String titulo, editora, autor, sinopse, idioma;
	private int ano, edicao, numExemplaresDisponiveis, codigo;
	
	
	
	public void setLivro(String titulo, String editora, String autor, String idioma, int ano, int edicao,
			int numExemplaresDisponiveis) {
		this.titulo = titulo;
		this.editora = editora;
		this.autor = autor;
		this.idioma = idioma;
		this.ano = ano;
		this.edicao = edicao;
		this.numExemplaresDisponiveis = numExemplaresDisponiveis;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getEditora() {
		return editora;
	}
	public void setEditora(String editora) {
		this.editora = editora;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getSinopse() {
		return sinopse;
	}
	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getEdicao() {
		return edicao;
	}
	public void setEdicao(int edicao) {
		this.edicao = edicao;
	}
	public int getNumExemplaresDisponiveis() {
		return numExemplaresDisponiveis;
	}
	public void setNumExemplaresDisponiveis(int numExemplaresDisponiveis) {
		this.numExemplaresDisponiveis = numExemplaresDisponiveis;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	
	

}

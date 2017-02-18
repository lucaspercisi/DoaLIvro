/**
 * Classe Livro: Para criação do objeto livro (somente atributos, getters e setters e seu construtor.
 */


package tk.doalivro.doalivro;

public class Livro {

    private String titulo, ano, autor;
    private Usuario doador;

    public Livro(String titulo, String ano, String autor, Usuario doador) {
        setTitulo(titulo);
        setAno(ano);
        setAutor(autor);
        setDoador(doador);
    }

    public String getTitulo() {return titulo;}
    public void setTitulo(String titulo) {this.titulo = titulo;}
    public String getAno() {return ano;}
    public void setAno(String ano) {this.ano = ano;}
    public String getAutor() {return autor;}
    public void setAutor(String autor) {this.autor = autor;}
    public Usuario getDoador() {return doador;}
    public void setDoador(Usuario doador) {this.doador = doador;}
}


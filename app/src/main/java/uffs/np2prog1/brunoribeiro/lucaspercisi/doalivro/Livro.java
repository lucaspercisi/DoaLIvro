package uffs.np2prog1.brunoribeiro.lucaspercisi.doalivro;

public class Livro {

    private String titulo, ano, autor, nomeDono, contato;

    public Livro(String titulo, String ano, String autor, String nomeDono, String contato) {

        setTitulo(titulo);
        setAno(ano);
        setAutor(autor);
        setNomeDono(nomeDono);
        setContato(contato);

    }

    public String getTitulo() {return titulo;}
    public void setTitulo(String titulo) {this.titulo = titulo;}
    public String getAno() {return ano;}
    public void setAno(String ano) {this.ano = ano;}
    public String getAutor() {return autor;}
    public void setAutor(String autor) {this.autor = autor;}
    public String getNomeDono() {return nomeDono;}
    public void setNomeDono(String dono) {this.nomeDono = nomeDono;}
    public String getContato() {return contato;}
    public void setContato(String contato) {this.contato = contato;}
}


/**
 * Classe para definir as características do objeto Usuário
 */

package tk.doalivro.doalivro;

public class Usuario {

    private String nome, email;
    private Boolean logado;
    private int doacoes, solicitacoes; //ACUMULADOR DE QUANTIDADE DE DOAÇOES E SOLICITAÇÃO

    public Usuario() {
        setDoacoes(0);
        setSolicitacoes(0);
        setLogado(false);
    };

    public Usuario(String nome, String email, Boolean logado){
        setNome(nome);
        setEmail(email);
        setLogado(logado);
        setDoacoes(0);
        setSolicitacoes(0);
    }

    //PERMITE A SOLICITAÇÃO DE LIVRO SE SALDO DE DOAÇÃO FOR POSITIVO
    public boolean solicitar(){
         return (getDoacoes() - getSolicitacoes()) >= 1;
    }

    //GETTERS E SETTERS
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public Boolean getLogado() {return logado;}
    public void setLogado(Boolean logado) {this.logado = logado;}
    public int getDoacoes() {return doacoes;}
    public void setDoacoes(int doacoes) {this.doacoes = doacoes;}
    public int getSolicitacoes() {return solicitacoes;}
    public void setSolicitacoes(int solicitacoes) {this.solicitacoes = solicitacoes;}

    public String toString() {
        return this.getNome() + " <" + this.getEmail() + ">";
    }

}

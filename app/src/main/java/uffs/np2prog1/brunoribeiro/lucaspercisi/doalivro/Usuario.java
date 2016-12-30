package uffs.np2prog1.brunoribeiro.lucaspercisi.doalivro;

public class Usuario {

    String nome, email, telefone;
    Boolean logado;
    int doacoes, solicitacoes; //ACUMULADOR DE QUANTIDADE DE DOAÇOES E SOLICITAÇÃO

    Usuario(String nome, String email, String telefone, Boolean logado){
        setNome(nome);
        setEmail(email);
        setTelefone(telefone);
        setLogado(logado);
    }

    //PERMITE A SOLICITAÇÃO DE LIVRO SE SALDO DE DOAÇÃO FOR POSITIVO
    public boolean solicitar(){
        if((getDoacoes() - getSolicitacoes()) >= 1) return true;
        else return false;
    }

    //GETTERS E SETTERS
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getTelefone() {return telefone;}
    public void setTelefone(String telefone) {this.telefone = telefone;}
    public Boolean getLogado() {return logado;}
    public void setLogado(Boolean logado) {this.logado = logado;}
    public int getDoacoes() {return doacoes;}
    public void setDoacoes(int doacoes) {this.doacoes = doacoes;}
    public int getSolicitacoes() {return solicitacoes;}
    public void setSolicitacoes(int solicitacoes) {this.solicitacoes = solicitacoes;}

}

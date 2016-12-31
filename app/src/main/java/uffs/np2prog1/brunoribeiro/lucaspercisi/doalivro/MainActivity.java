package uffs.np2prog1.brunoribeiro.lucaspercisi.doalivro;

import java.util.ArrayList;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
//import android.widget.ImageView;

public class MainActivity extends Activity {

    //ATRIBUTOS
    TelaLogin tela_login;
    TelaPrincipal tela_principal;
    TelaDoaLivro tela_doa_livro;
    TelaListaLivros tela_lista_livro;
    ArrayList<Livro> livrosDoacao;
    ArrayList<Livro> meusLivros;
    Usuario user;
    Livro livro1, livro2, livro3;

    /*
    ImageView capa;
    COMO CONSTRUIR O LIVRO COM UMA IMAGEM DO DRAWABLE?
    */

    //ESSE ONCREATE É O MAIN DO C;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iniciaApp();
        if(user.getLogado()) tela_principal.carregaTela();
        else tela_login.carregaTela();
    }

    //MÉTODOS...
    public void iniciaApp(){

        tela_principal = new TelaPrincipal(this);
        tela_login = new TelaLogin(this, tela_principal);
        tela_doa_livro = new TelaDoaLivro(this, tela_principal);
        tela_lista_livro = new TelaListaLivros(this, tela_principal);

        tela_principal.setTela_login(tela_login);
        tela_principal.setTela_doa_livro(tela_doa_livro);
        tela_principal.setTela_lista_livros(tela_lista_livro);

        livrosDoacao = new ArrayList<Livro>();
        meusLivros = new ArrayList<Livro>();

        user = new Usuario("Bruno", "bruno@doalivro.com", "(49) 9-9976-5432", true);//CRIA UM NOVO USUARIO E DEIXA LOGADO

        livro1 = new Livro("O Pequeno Príncipe", "Séc. XVII", "Anoine de Saint-Exupéry", "Luís Carlos", "luis@doalivro.com");//CRIA LIVRO
        livro2 = new Livro("O Hobbit", "1937", "J. R. R. Tolkien", "João Inácio", "joaoinacio@doalivro.com");//CRIA LIVRO
        livro3 = new Livro("Código da Vinci", "2004", "Dan Brown", "Marcos Gomes", "marcosgomes@doalivro.com");//CRIA LIVRO

        livrosDoacao.add(livro1);//ADICIONA LIVRO1
        livrosDoacao.add(livro2);//ADICIONA LIVRO2
        livrosDoacao.add(livro3);//ADICIONA LIVRO3

    }

    public void ExibirMensagem(String msg) {
        AlertDialog.Builder menssagem = new AlertDialog.Builder(MainActivity.this);
        menssagem.setTitle("AVISO");
        menssagem.setMessage(msg);
        menssagem.setNeutralButton("OK", null);
        menssagem.show();
    }

    //GETTERS E SETTERS
    public ArrayList<Livro> getLivrosDoacao() {return livrosDoacao;}
    public void setLivrosDoacao(ArrayList<Livro> livrosDoacao) {this.livrosDoacao = livrosDoacao;}
    public ArrayList<Livro> getMeusLivros() {return meusLivros;}
    public void setMeusLivros(ArrayList<Livro> meusLivros) {this.meusLivros = meusLivros;}
    public TelaPrincipal getTela_principal() {return tela_principal;}
    public void setTela_principal(TelaPrincipal tela_principal) {this.tela_principal = tela_principal;}
    public TelaLogin getTela_login() {return tela_login;}
    public void setTela_login(TelaLogin tela_login) {this.tela_login = tela_login;}
    public TelaDoaLivro getTela_doa_livro() {return tela_doa_livro;}
    public void setTela_doa_livro(TelaDoaLivro tela_doa_livro) {this.tela_doa_livro = tela_doa_livro;}
    public TelaListaLivros getTela_lista_livro() {return tela_lista_livro;}
    public void setTela_lista_livro(TelaListaLivros tela_lista_livro) {this.tela_lista_livro = tela_lista_livro;}

}
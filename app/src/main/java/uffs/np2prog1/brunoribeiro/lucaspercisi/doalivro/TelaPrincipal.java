package uffs.np2prog1.brunoribeiro.lucaspercisi.doalivro;

import android.view.View;
import android.widget.Button;

public class TelaPrincipal {

    MainActivity main;
    TelaDoaLivro tela_doa_livro;
    TelaListaLivros tela_lista_livros;
    TelaLogin tela_login;

    Button b_doar, b_livroDoacao;

    public TelaPrincipal(MainActivity main) {
        this.main = main;
    }

    public void carregaTela() {

        main.setContentView(R.layout.tela_principal);
        b_doar = (Button) main.findViewById(R.id.b_doar);
        b_livroDoacao = (Button) main.findViewById(R.id.b_livrosDoacao);

        b_doar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tela_doa_livro.CarregarTela();
            }
        });

        b_livroDoacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tela_lista_livros.CarregarTela();
            }
        });

    }

    public TelaLogin getTela_login() {return tela_login;}
    public void setTela_login(TelaLogin tela_login) {this.tela_login = tela_login;}
    public TelaDoaLivro getTela_doa_livro() {return tela_doa_livro;}
    public void setTela_doa_livro(TelaDoaLivro tela_doa_livro) {this.tela_doa_livro = tela_doa_livro;}
    public TelaListaLivros getTela_lista_livros() {return tela_lista_livros;}
    public void setTela_lista_livros(TelaListaLivros tela_lista_livros) {this.tela_lista_livros = tela_lista_livros;}

}


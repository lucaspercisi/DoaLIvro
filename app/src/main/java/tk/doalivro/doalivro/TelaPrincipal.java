package tk.doalivro.doalivro;

import android.view.View;
import android.widget.Toast;

public class TelaPrincipal implements View.OnClickListener {

    private MainActivity main;
    private TelaDoaLivro tela_doa_livro;
    private TelaListaLivros tela_lista_livros;
    private TelaLogin tela_login;

    public TelaPrincipal(MainActivity main) {
        this.main = main;
    }

    public void carregaTela() {

        main.setContentView(R.layout.tela_principal);
        setBtnClickListeners();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.botao_sair:
                Toast.makeText(main, "Saindo do Google", Toast.LENGTH_SHORT).show();
                main.gSignOut();
                break;
            case R.id.botao_revogar:
                Toast.makeText(main, "Revogando acesso do Google", Toast.LENGTH_SHORT).show();
                main.gRevokeAccess();
                break;
            case R.id.b_doar:
                tela_doa_livro.CarregarTela();
                break;
            case R.id.b_livrosDoacao:
                tela_lista_livros.CarregarTela();
                break;
        }
    }

    /*
        Seta o Listener do botão de sign-in.
    */
    private void setBtnClickListeners(){
        main.findViewById(R.id.botao_sair).setOnClickListener(this);
        main.findViewById(R.id.botao_revogar).setOnClickListener(this);
        main.findViewById(R.id.b_doar).setOnClickListener(this);
        main.findViewById(R.id.b_livrosDoacao).setOnClickListener(this);
    }

    public TelaLogin getTela_login() {return tela_login;}
    public void setTela_login(TelaLogin tela_login) {this.tela_login = tela_login;}
    public TelaDoaLivro getTela_doa_livro() {return tela_doa_livro;}
    public void setTela_doa_livro(TelaDoaLivro tela_doa_livro) {this.tela_doa_livro = tela_doa_livro;}
    public TelaListaLivros getTela_lista_livros() {return tela_lista_livros;}
    public void setTela_lista_livros(TelaListaLivros tela_lista_livros) {this.tela_lista_livros = tela_lista_livros;}

}


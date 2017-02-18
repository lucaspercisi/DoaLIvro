package tk.doalivro.doalivro;

import android.app.*;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

public class TelaDoaLivro {
    private MainActivity main;
    private TelaPrincipal tela_principal;
    private EditText et_titulo, et_ano, et_autor;
    private Button b_doar, b_cancelar;

    public TelaDoaLivro(MainActivity main, TelaPrincipal tela_principal) {
        setMain(main);
        setTela_principal(tela_principal);
    }

    public void CarregarTela() {

        main.setContentView(R.layout.doa_livro);
        et_titulo = (EditText) main.findViewById(R.id.et_titulo);
        et_ano = (EditText) main.findViewById(R.id.et_ano);
        et_autor = (EditText) main.findViewById(R.id.et_autor);

        b_doar = (Button) main.findViewById(R.id.b_doar);
        b_cancelar = (Button) main.findViewById(R.id.b_cancelar);

        b_doar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder dialogo = new AlertDialog.Builder(main);
                dialogo.setTitle("AVISO");
                dialogo.setMessage("Publicar livro para doação?");
                dialogo.setNegativeButton("NÃO", null);
                dialogo.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String titulo = null;
                        String ano = null;
                        String autor = null;

                        titulo = et_titulo.getText().toString();
                        ano = et_ano.getText().toString();
                        autor = et_autor.getText().toString();

                        //POSSIVEIS ERROS DE DIGITAÇÃO
                        if(titulo == null || titulo.trim().isEmpty()) {
                            main.ExibirMensagem("TITULO INCORRETO");
                            return;
                        }

                        if(ano == null || ano.trim().isEmpty()){
                            main.ExibirMensagem("ANO INCORRETO");
                            return;
                        }

                        if(autor == null || autor.trim().isEmpty()) {
                            main.ExibirMensagem("AUTOR INCORRETO");
                            return;
                        }

                        //main.getLivrosDoacao().add(new Livro(titulo, ano, autor, main.user));//CONSTRÓI NOVO LIVRO COM OS DADOS INFORMADOS NO CAMPO
                        main.getLivrosDoacao().add(new Livro(titulo, ano, autor, main.user));//CONSTRÓI NOVO LIVRO COM OS DADOS INFORMADOS NO CAMPO
                        main.user.setDoacoes(main.user.getDoacoes() + 1);//INCREMENTA ACUMULATIVO DE DOAÇÃOES
                        main.ExibirMensagem("Parabéns, você contribuiu para um mundo melhor.");

                        tela_principal.carregaTela();
                    }
                });
                dialogo.show();
            }
        });

        b_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogo = new AlertDialog.Builder(main);
                dialogo.setTitle("AVISO");
                dialogo.setMessage("Sair do cadastro?");
                dialogo.setNegativeButton("NÃO", null);
                dialogo.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tela_principal.carregaTela();
                    }
                });
                dialogo.show();
            }
        });
    }

    public MainActivity getMain() {return main;}
    public void setMain(MainActivity main) {this.main = main;}
    public TelaPrincipal getTela_principal() {return tela_principal;}
    public void setTela_principal(TelaPrincipal tela_principal) {this.tela_principal = tela_principal;}

}

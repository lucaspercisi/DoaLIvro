package uffs.np2prog1.brunoribeiro.lucaspercisi.doalivro;

import android.view.View;
import android.widget.Button;

public class TelaLogin{

    MainActivity main;
    TelaPrincipal tela_principal;
    Button b_entrar, b_criarConta, b_sair;

    public TelaLogin(MainActivity main, TelaPrincipal tela_principal) {
        setMain(main);
        setTela_principal(tela_principal);
    }

    public void carregaTela() {

        main.setContentView(R.layout.login);//INFORMA QUAL LAYOUT DEVE SER CARREGADO
        b_entrar = (Button) main.findViewById(R.id.b_entrar);//DECLARA UM BOTÃO PELO ID
        b_criarConta = (Button) main.findViewById(R.id.b_criarConta);//DECLARA UM BOTÃO PELO ID
        b_sair = (Button) main.findViewById(R.id.b_sair); //DECLARA UM BOTÃO PELO ID

        /*
        //AÇÃO DO BOTÃO
        b_entrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View tela){
                //tela_doar_livro.carregaTela();
            }
        });

        //AÇÃO DO BOTÃO
        b_criarConta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View tela){
                //tela_lista_doacao.carregaTela();
            }
        });

        //AÇÃO DO BOTÃO
        b_sair.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View tela){
                //tela_lista_doacao.carregaTela();
            }
        });
        */

    }

    //public MainActivity getMain() {return main;}
    public void setMain(MainActivity main) {this.main = main;}
    //public TelaPrincipal getTela_principal() {return tela_principal;}
    public void setTela_principal(TelaPrincipal tela_principal) {this.tela_principal = tela_principal;}

}
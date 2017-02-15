package tk.doalivro.doalivro;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TelaListaLivros {

    //ATRINUTOS
    private MainActivity main;
    private TelaPrincipal tela_principal;
    private Button b_solicitar, b_anterior, b_proximo, b_fechar;
    private TextView tv_titulo, tv_ano, tv_autor, tv_email, doador;
    //ImageView iv_capa;
    private int index;

    public TelaListaLivros(MainActivity main, TelaPrincipal tela_principal) {
        setMain(main);
        setTela_principal(tela_principal);
        index = 0;
    }

    public void CarregarTela() {

        //VERIFICA SE EXISTE LIVROS EM DOAÇÕES
        if(main.getLivrosDoacao().size() == 0) {
            main.ExibirMensagem("Nenhum livro em doação.");
            tela_principal.carregaTela();
        }

        main.setContentView(R.layout.lista_livros_doacao);
        b_solicitar = (Button) main.findViewById(R.id.b_solicitar);
        b_anterior = (Button) main.findViewById(R.id.b_anterior);
        b_proximo = (Button) main.findViewById(R.id.b_proximo);
        b_fechar = (Button) main.findViewById(R.id.b_fechar);
        tv_titulo = (TextView) main.findViewById(R.id.tv_titulo);
        tv_ano = (TextView) main.findViewById(R.id.tv_ano);
        tv_autor = (TextView) main.findViewById(R.id.tv_autor);

        doador = (TextView) main.findViewById(R.id.doador);
       // iv_capa = (ImageView) main.findViewById(R.id.iv_capa);
        atualizaCampos(index);
        //txtstatus = (TextView) act.findViewById(R.id.txtstatus);
        //AtualizaStatus(index);

        //BOTÃO SOLICITAR
        b_solicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(main.user.solicitar()){
                    main.user.setSolicitacoes(main.user.getSolicitacoes() + 1); //INCREMENTA ACUMULATIVO DE SOLICITAÇÕES
                    try {
                        main.getLivrosDoacao().remove(index);//REMOVE DA LISTA DE DOAÇÃO
                    }catch(Exception e){
                        e.getMessage();
                        main.ExibirMensagem(":/\nOcorreu um erro ao tentar solicitar o livro. Por favor tente mais terde.");
                }
                    main.ExibirMensagem("Entre em contato com o doador pelo e-mail.");
                    tela_principal.carregaTela();
                }else{
                    main.ExibirMensagem("Envie um email para: " + main.getLivrosDoacao().get(index).getDoador().getEmail() + "\nsolicitando este livro.");
                    tela_principal.carregaTela();
                }
            }
        });

        //BOTÃO PRÓXIMO
        b_proximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index++;
                if(index < main.getLivrosDoacao().size()) {
                    atualizaCampos(index);
                    //AtualizaStatus(index);
                }else{
                    index = 0;
                    atualizaCampos(index);
                }
            }
        });


        //BOTÃO ANTERIOR
        b_anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index--;
                if(index >= 0) {
                    atualizaCampos(index);
                    //AtualizaStatus(index);
                }else{
                    index = main.getLivrosDoacao().size() - 1;
                    atualizaCampos(index);
                }
            }
        });

        //BOTÃO FECHAR
        b_fechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tela_principal.carregaTela();
            }
        });
    }

    //ATUALIZA OS CAMPOS COM OS DADOS DO ARREYLIST LIVROS EM DOAÇÃO
    private void atualizaCampos(int index) {
        tv_titulo.setText(main.getLivrosDoacao().get(index).getTitulo());
        tv_ano.setText(main.getLivrosDoacao().get(index).getAno());
        tv_autor.setText(main.getLivrosDoacao().get(index).getAutor());
        doador.setText(main.getLivrosDoacao().get(index).getDoador().toString());
        //tv_email.setText(main.getLivrosDoacao().get(index).getDoador().getEmail());
        //iv_capa = main.getLivrosDoacao().get(index).getCapa();
    }

    //GETTERS E SETTERS
    public MainActivity getMain() {return main;}
    public void setMain(MainActivity main) {this.main = main;}
    public TelaPrincipal getTela_principal() {return tela_principal;}
    public void setTela_principal(TelaPrincipal tela_principal) {this.tela_principal = tela_principal;}

    /*
    private void AtualizaStatus(int idx) {
        int total = main.getRegistros().size();
        txtstatus.setText("LIVROS: " + (index+1) + "/" + total);
    }
*/
}
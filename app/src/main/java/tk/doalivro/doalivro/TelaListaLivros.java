/**
 * Classe para navegar e visualizar os livros na lista de livros em doação e realizar
 * solicitações de livros.
 *
 */

package tk.doalivro.doalivro;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TelaListaLivros {

    //ATRINUTOS
    private MainActivity main;
    private TelaPrincipal tela_principal;
    private Button b_solicitar, b_anterior, b_proximo, b_fechar;
    private TextView tv_titulo, tv_ano, tv_autor, doador;
    private int index;

    //Constrói a tela Lista de Livros
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

        //Define os atributos com os objetos na tela.
        main.setContentView(R.layout.lista_livros_doacao);
        b_solicitar = (Button) main.findViewById(R.id.b_solicitar);
        b_anterior = (Button) main.findViewById(R.id.b_anterior);
        b_proximo = (Button) main.findViewById(R.id.b_proximo);
        b_fechar = (Button) main.findViewById(R.id.b_fechar);
        tv_titulo = (TextView) main.findViewById(R.id.tv_titulo);
        tv_ano = (TextView) main.findViewById(R.id.tv_ano);
        tv_autor = (TextView) main.findViewById(R.id.tv_autor);

        doador = (TextView) main.findViewById(R.id.doador);
        atualizaCampos(index);

        //BOTÃO SOLICITAR
        b_solicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Se o usuário tentar pegar um livro que ele mesmo doou.
                if(main.getLivrosDoacao().get(index).getDoador() == main.user){
                    main.ExibirMensagem("Você não pode solicitar um livro seu.");
                    //Senão o usuário poderá retirar o livro se houver saldo positivo de doações.
                }else if(main.user.solicitar()){
                    main.user.setSolicitacoes(main.user.getSolicitacoes() + 1); //INCREMENTA ACUMULATIVO DE SOLICITAÇÕES
                    main.getLivrosDoacao().remove(index); //REMOVE DA LISTA DE DOAÇÃO
                    main.ExibirMensagem(":/\nOcorreu um erro ao tentar solicitar o livro. Por favor tente mais terde.");
                    main.ExibirMensagem("Enviamos um email para o doador solicitando este livro (mentira).\nAguarde o contato.");
                }else{
                    main.ExibirMensagem(String.format("Você só pode solicitar um livro para cada um que doar.\nAté agora você doou %d e recebeu %d livros.", main.user.getDoacoes(), main.user.getSolicitacoes()));
                }
                tela_principal.carregaTela();
            }
        });

        //BOTÃO PRÓXIMO
        b_proximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index++;
                if(index < main.getLivrosDoacao().size()) {
                    atualizaCampos(index);
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

    //Atualiza os campos da tela pelo id do xml da tela com os atributos correspondete de cada livro.
    private void atualizaCampos(int index) {
        tv_titulo.setText(main.getLivrosDoacao().get(index).getTitulo());
        tv_ano.setText(main.getLivrosDoacao().get(index).getAno());
        tv_autor.setText(main.getLivrosDoacao().get(index).getAutor());
        doador.setText(main.getLivrosDoacao().get(index).getDoador().toString());
    }

    //Gettres e Setters
    public MainActivity getMain() {return main;}
    public void setMain(MainActivity main) {this.main = main;}
    public TelaPrincipal getTela_principal() {return tela_principal;}
    public void setTela_principal(TelaPrincipal tela_principal) {this.tela_principal = tela_principal;}

}
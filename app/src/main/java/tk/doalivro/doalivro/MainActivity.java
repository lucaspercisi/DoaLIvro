/**=================================================================================================
 *
 *  UNIVERSIDADE FEDERAL DA FRONTEIRA SUL
 *
 *  TRABALHO FINAL DE PROGRAMAÇÃO I - 15/02/2017
 *
 *  PROFESSORA: PRISCILA DELABETHA
 *
 *  ALUNOS: BRUNO RIBEIRO
 *          LUCAS PERCISI
 *
 *  DESCRIÇÃO:      Aplicativo mobile para doações de livros. O usúario loga com sua conta e tem
 *              acesso a diversos livros que estão em doação, porém só pode retirar algum livro se
 *              tiver doado mais do que retirado. Ou seja, para retirar um livro da lista de doações
 *              o usuário deve doar no minimo uma vez antes de retirar novamente.
 *
 *  DESAFIOS:   - Compreender o funcionamento do Android.
 *              - Tentar criar uma interface amigável e intuitiva.
 *              - Inserção da API do Google.
 *
 *  CONCLUSÂO:      Android é incrível. O resultado final do trabalho ficou melhor que o esperado,
 *              o que fez valer a pena o estudo do mesmo, proporcionando uma ótima experiência
 *              com orientação à objetos utilizando a linguagem Java.
 *
 *==================================================================================================
 */

/**
 * Classe principal responsável para iniciar o ciclo de vida do app, definir as telas necessárias
 * e setar atributos pré configurados para usar o app.
 */

package tk.doalivro.doalivro;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {

    //Atributos usados
    protected TelaLogin tela_login;
    protected TelaPrincipal tela_principal;
    protected TelaDoaLivro tela_doa_livro;
    protected TelaListaLivros tela_lista_livro;
    protected ArrayList<Livro> livrosDoacao;
    protected ArrayList<Livro> meusLivros;
    protected Usuario user;

    /* Variáveis e constantes Google Sign-In início */
    private GoogleApiClient mGoogleApiClient;
    private GoogleSignInOptions gso;
    private static final int RC_SIGN_IN = 0;
    private ProgressDialog progress_dialog;
    /* Variáveis e constantes Google Sign-In fim */


    //Inicia o ciclo de vida do app e carrega a tela correspondente se o usuário estiver logado
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buidNewGoogleApiClient();
        progress_dialog = new ProgressDialog(this);
        progress_dialog.setMessage("Signing in....");

        iniciaApp();
        if(user.getLogado()) tela_principal.carregaTela();
        else tela_login.carregaTela();
    }

    //Configura o app para funcionamento (define as telas e atributos pré configurados).
    public void iniciaApp(){

        //Constrói as telas que tem ligação com da tela principal e constrói a Tela Principal.
        tela_principal = new TelaPrincipal(this);
        tela_login = new TelaLogin(this, tela_principal);
        tela_doa_livro = new TelaDoaLivro(this, tela_principal);
        tela_lista_livro = new TelaListaLivros(this, tela_principal);

        //Seta as telas que tem ligação com a tela principal.
        tela_principal.setTela_login(tela_login);
        tela_principal.setTela_doa_livro(tela_doa_livro);
        tela_principal.setTela_lista_livros(tela_lista_livro);

        //Array de livro em doação e livro que o usuário doou.
        livrosDoacao = new ArrayList<Livro>();
        //meusLivros = new ArrayList<Livro>();  NÃO IMPLEMENTADO.

        //Constrói o usuário do app.
        user = new Usuario();

        //Constrói 3 livros genéricos para iniciar o app.
        Livro livro1 = new Livro("O Pequeno Príncipe", "Séc. XVII", "Anoine de Saint-Exupéry", new Usuario("Luís Carlos", "luis@doalivro.com", false));//CRIA LIVRO
        Livro livro2 = new Livro("O Hobbit", "1937", "J. R. R. Tolkien",  new Usuario("João Inácio", "joaoinacio@doalivro.com", false));//CRIA LIVRO
        Livro livro3 = new Livro("Código da Vinci", "2004", "Dan Brown",  new Usuario("Marcos Gomes", "marcosgomes@doalivro.com", false));//CRIA LIVRO

        //Adiciona os 3 livros na lista de livros em doção.
        livrosDoacao.add(livro1);//ADICIONA LIVRO1
        livrosDoacao.add(livro2);//ADICIONA LIVRO2
        livrosDoacao.add(livro3);//ADICIONA LIVRO3

    }

    //Método genérioco para exibição de avisos.
    public void ExibirMensagem(String msg) {
        AlertDialog.Builder menssagem = new AlertDialog.Builder(MainActivity.this);
        menssagem.setTitle("AVISO");
        menssagem.setMessage(msg);
        menssagem.setNeutralButton("OK", null);
        menssagem.show();
    }

    /* Google Sign-In início */
    /*
        Configuração para pedir ID, e-mail e perfil básico do usuário. Essas informações estão em DEFAULT_SIGN_IN.
        Cria e incicializa um objeto GoogleApiClient para usar a API Google Sign-In API e as opções do GoogleSignInOptions
    */
    private void buidNewGoogleApiClient(){

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this )
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    /*
        Personalização do botão Google Sign In.
    */
    protected void customizeSignBtn(SignInButton signIn_btn){

        signIn_btn.setStyle(SignInButton.SIZE_WIDE, SignInButton.COLOR_LIGHT, gso.getScopeArray());

    }

    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            if (resultCode != RESULT_OK) {

                progress_dialog.dismiss();

            }
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            getSignInResult(result);
        }
    }

    protected void gSignIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
        progress_dialog.show();
    }

    protected void gSignOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        updateUI(false);
                    }
                });
    }

    protected void gRevokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        updateUI(false);
                    }
                });
    }

    private void getSignInResult(GoogleSignInResult result) {

        if (result.isSuccess()) {
            // Usuário entrou, exibe UI do usuário autenticado.
            GoogleSignInAccount acct = result.getSignInAccount();
            user.setNome(acct.getDisplayName());
            user.setEmail(acct.getEmail());
            user.setLogado(true);
            progress_dialog.dismiss();
            updateUI(true);
        } else {
            // Usuário saiu, exibe tela de usuário não autenticado.
            user.setLogado(false);
            updateUI(false);
        }
    }

    private void updateUI(boolean signedIn) {
        if (signedIn) {
            tela_principal.carregaTela();
        } else {
            tela_principal.carregaTela();
            //tela_login.carregaTela();
        }
    }

    @Override
    public void onConnected(Bundle bundle) {}

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {}
    /* Google Sign-In fim */

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
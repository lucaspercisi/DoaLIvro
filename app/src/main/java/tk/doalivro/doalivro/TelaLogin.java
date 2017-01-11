package tk.doalivro.doalivro;

import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;

public class TelaLogin implements View.OnClickListener {

    private MainActivity main;
    private TelaPrincipal tela_principal;
    private SignInButton signIn_btn;

    public TelaLogin(MainActivity main, TelaPrincipal tela_principal) {
        setMain(main);
        setTela_principal(tela_principal);
    }

    public void carregaTela() {

        main.setContentView(R.layout.login);
        signIn_btn = (SignInButton) main.findViewById(R.id.botao_entrar);
        main.customizeSignBtn(signIn_btn);
        setBtnClickListeners(signIn_btn);

    }

    // Definição das ações dos cliques dos botões
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.botao_entrar:
                Toast.makeText(main, "Entrando com o Google", Toast.LENGTH_SHORT).show();
                main.gSignIn();
                break;
        }
    }

    /*
        Seta o Listener do botão de sign-in.
    */
    private void setBtnClickListeners(SignInButton signIn_btn){
        signIn_btn.setOnClickListener(this);
    }

    //public MainActivity getMain() {return main;}
    public void setMain(MainActivity main) {this.main = main;}
    //public TelaPrincipal getTela_principal() {return tela_principal;}
    public void setTela_principal(TelaPrincipal tela_principal) {this.tela_principal = tela_principal;}

}
package saucebrune.tamagotchi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity{

    //private static final int REQUEST_CODE = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
    }

    public void onClickEnregistrer(View button){

    }

    public void onClickCreate(View button){
        if(valide()){
            EditText pseudo = (EditText)findViewById(R.id.etxtPseudo);
            EditText password = (EditText)findViewById(R.id.etxtPassword);
            Intent create = new Intent(this,MainActivity.class);
            create.putExtra("etxtPseudo",pseudo.getText().toString());
            create.putExtra("etxtPassword",password.getText().toString());
            setResult(Activity.RESULT_OK,create);
            finish();
        }
    }

    public void onClickRetour(View button){ finish(); }

    public boolean valide(){
        return true;
    }
}

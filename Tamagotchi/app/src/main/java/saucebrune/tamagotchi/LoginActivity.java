package saucebrune.tamagotchi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import static saucebrune.tamagotchi.MainActivity.accountData;
import static saucebrune.tamagotchi.MainActivity.myDB;

public class LoginActivity extends Activity{

    private static final String TAG = LoginActivity.class.getSimpleName();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
    }

    public void onClickLogin(View button){
        try{
            EditText pseudo = (EditText)findViewById(R.id.etxtPseudo);
            EditText password = (EditText)findViewById(R.id.etxtPassword);
            int id = myDB.selectId(pseudo.getText().toString());
            int[] info = myDB.selectExpMonstre(id);
            if(checkPseudo(pseudo.getText().toString())&&checkPass(password.getText().toString())){
                accountData.setPseudo(myDB.selectPseudo(id));
                accountData.setPassword(myDB.selectPassword(id));
                accountData.setNomMonsre(myDB.selectNomMonstre(id),id-1);
                accountData.setExpMonsre(info[id],id-1);
                Intent login = new Intent(this,MainActivity.class);
                login.putExtra("etxtPseudo",accountData.getPseudo());
                login.putExtra("etxtPassword",accountData.getPassword());
                setResult(Activity.RESULT_CANCELED,login);
                finish();
            }
        }catch (Exception e){
            CharSequence text = "Aucun utilisateur ne correspond aux données entrées!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        }
    }

    public void onClickCreate(View button){
        EditText pseudo = (EditText)findViewById(R.id.etxtPseudo);
        EditText password = (EditText)findViewById(R.id.etxtPassword);
        if(valide(pseudo.getText().toString())){
            Intent create = new Intent(this,MainActivity.class);
            create.putExtra("etxtPseudo",pseudo.getText().toString());
            create.putExtra("etxtPassword",password.getText().toString());
            setResult(Activity.RESULT_OK,create);
            finish();
        }
    }

    public void onClickRetour(View button){ finish(); }

    public boolean valide(String nom){
        ArrayList<String> pseudo;
        try{
            pseudo = myDB.selectPseudo();
            for(String s : pseudo){
                if(nom.equalsIgnoreCase(s))
                    return false;
            }
        }catch (Exception e){
            myDB = DatabaseHelper.getInstance(this);
        }
        return true;
    }

    public boolean checkPseudo(String nom){
        ArrayList<String> info = myDB.selectPseudo();
        for(String s : info){
            if(nom.equalsIgnoreCase(s))
                return true;
        }
        return false;
    }

    public boolean checkPass(String pass){
        ArrayList<String> info = myDB.selectPassword();
        for(String s : info){
            if(pass.equalsIgnoreCase(s))
                return true;
        }
        return false;
    }
}

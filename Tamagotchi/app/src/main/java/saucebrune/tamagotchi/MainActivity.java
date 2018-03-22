package saucebrune.tamagotchi;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickLogin(View button){
        Intent login = new Intent(this,LoginActivity.class);
        startActivityForResult(login, REQUEST_CODE);
    }

    public void onClickJouer(View button){
        Intent intent = new Intent(this,MonsterList.class);
        startActivity(intent);
    }

    public void onClickQuitter(View button){ finish(); }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Intent resultat = getIntent();
                Uri info = resultat.getData();
                TextView pseudo = (TextView) findViewById(R.id.txtPseudo);
                Button btnJouer = (Button)findViewById(R.id.btnJouer);
                btnJouer.isEnabled();
                pseudo.setAlpha(1);
                pseudo.setText(info.toString());
            }
        }
    }
}
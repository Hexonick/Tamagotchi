package saucebrune.tamagotchi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MonsterList extends AppCompatActivity{
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monsterlist_activity);
    }

    public void onClickChooseMonster(View button){
        Intent list = new Intent(this,EggListActivity.class);
        list.putExtra("id",button.getId());
        startActivityForResult(list,REQUEST_CODE);
    }

    public void onClickRetour(View button){ finish(); }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String nom = data.getStringExtra("nom");
                TextView pseudo = (TextView) findViewById(R.id.txtPseudo);
                Button btnJouer = (Button)findViewById(R.id.btnJouer);
            }
        }
    }
}
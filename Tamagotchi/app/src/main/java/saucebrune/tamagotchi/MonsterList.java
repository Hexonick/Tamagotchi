package saucebrune.tamagotchi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import static saucebrune.tamagotchi.MainActivity.accountData;
import static saucebrune.tamagotchi.MainActivity.myDB;

public class MonsterList extends AppCompatActivity{
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monsterlist_activity);
    }

    public void onClickChooseMonster(View button){
        Intent list = new Intent(this,EggListActivity.class);
        String bouton = String.valueOf(button.getId());
        list.putExtra("id",bouton);
        startActivityForResult(list,REQUEST_CODE);
    }

    public void onClickRetour(View button){ finish(); }

    public void onClickJouerMonstre(View button){
        Intent intent = new Intent(this,gameplayActivity.class);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String nom = data.getStringExtra("nom");
                TextView nomMonstre = null;
                TableLayout table = null;
                Button btnJouer = null;
                String btn = data.getStringExtra("id");
                int id = Integer.valueOf(btn);
                switch(id){
                    case R.id.btnMonster1:
                        nomMonstre = (TextView) findViewById(R.id.nomMonstre1);
                        table = (TableLayout) findViewById(R.id.tblMonstre1);
                        btnJouer = (Button)findViewById(R.id.btnMonster1);
                        break;
                    case R.id.btnMonster2:
                        nomMonstre = (TextView) findViewById(R.id.nomMonstre2);
                        table = (TableLayout) findViewById(R.id.tblMonstre2);
                        btnJouer = (Button)findViewById(R.id.btnMonster2);
                        break;
                    case R.id.btnMonster3:
                        nomMonstre = (TextView) findViewById(R.id.nomMonstre3);
                        table = (TableLayout) findViewById(R.id.tblMonstre3);
                        btnJouer = (Button)findViewById(R.id.btnMonster3);
                        break;
                    case R.id.btnMonster4:
                        nomMonstre = (TextView) findViewById(R.id.nomMonstre4);
                        table = (TableLayout) findViewById(R.id.tblMonstre4);
                        btnJouer = (Button)findViewById(R.id.btnMonster4);
                        break;
                }
                nomMonstre.setText(nom);
                btnJouer.setVisibility(View.GONE);
                table.setVisibility(View.VISIBLE);
                int[] niv = accountData.getNivMonstre();
                myDB.insertIntoTblMonstre(nom,accountData.getExpMonstre(0),niv[0],0,myDB.selectId(accountData.getPseudo()));
            }
        }
    }
}
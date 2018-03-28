package saucebrune.tamagotchi;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class EggListActivity extends AppCompatActivity{

    public Intent intent = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.egglist_activity);
        intent = getIntent();
    }

    public void onClickChooseEgg(View button){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.eggname_dialog, null))
                .setPositiveButton(R.string.diaChoisir, new DialogInterface.OnClickListener(){
        public void onClick(DialogInterface button, int id){
            Dialog dialogView = (Dialog) button;
            EditText edit = (EditText)dialogView.findViewById(R.id.etxtNom);
            intent.putExtra("nom",edit.getText().toString());
            setResult(Activity.RESULT_OK,intent);
            sendIntent();
        }})
                .setNegativeButton(R.string.diaCancel,new DialogInterface.OnClickListener(){
        public void onClick(DialogInterface button, int id){
               button.cancel();
        }});
        builder.show();
    }

    public void sendIntent(){
        finish();
    }

    public void onClickRetour(View button){ finish(); }
}
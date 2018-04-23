package saucebrune.tamagotchi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
    private SQLiteDatabase dataB;

    private String TABLE_Account = "tblAccount";
    private String idAccount = "ID";
    private String expTotal = "EXPTOTAL";
    private String nivAcc = "NIVACC";
    private String speed = "SPEED";
    private String gainExp = "GAINEXP";
    private String pseudo = "PSEUDO";
    private String pass = "PASSWORD";

    private String TABLE_Monstre = "tblMonstre";
    private String idMonstre = "ID";
    private String nomMonstre = "NOM";
    private String expMonstre = "EXP";
    private String nivMonstre = "NIVEAU";
    private String tempsVivant = "TEMPSVIVANT";

    public DatabaseHelper(Context context) {
        super(context, "dbTamagotchi", null, 1);
        dataB = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_Account + "(" +
                idAccount + " INT NOT NULL AUTO_INCREMENT," +
                pseudo + " VARCHAR(255)," +
                pass + " VARCHAR(255)," +
                expTotal + " INT," +
                nivAcc + " INT," +
                speed + " INT," +
                gainExp + " INT," +
                "PRIMARY KEY (" + idAccount + "));");

        db.execSQL("create table " + TABLE_Monstre + "(" +
                idMonstre + " INT NOT NULL AUTO_INCREMENT," +
                nomMonstre + " VARCHAR(255)," +
                expMonstre + " INT," +
                nivMonstre + " INT," +
                tempsVivant + " INT," +
                "PRIMARY KEY (" + idMonstre + "));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS dbTamagotchi");
        onCreate(db);
    }

    public void insertIntoTblAccount(String nom, String passW, int expT, int niv, int vitesse, int gain){
        dataB.execSQL("INSERT INTO tblMonstre (" + pseudo + "," + pass + "," + expTotal + "," + nivAcc + "," + speed + "," + gainExp + ")" +
                " VALUES ("+ nom + "," + passW + "," + expT + "," + niv + "," + vitesse + "," + gain + ")");
    }

    public void insertIntoTblMonstre(String nom, int exp, int niv, int temps){
        dataB.execSQL("INSERT INTO tblMonstre (" + nomMonstre + "," + expMonstre + "," + nivMonstre + "," + tempsVivant + ")" +
                " VALUES ("+ nom + "," + exp + "," + niv + "," + temps + ")");
    }

    public void updateNomMonstre(String NewName, String OldName){
        dataB.execSQL("UPDATE " + TABLE_Monstre + " SET " + nomMonstre + " = " + NewName + " WHERE " + nomMonstre + " = " + OldName + ";");
    }

    public void updateExpMonstre(int exp, String name){
        dataB.execSQL("UPDATE " + TABLE_Monstre + " SET " + expMonstre + " = " + exp + " WHERE " + nomMonstre + " = " + name + ";");
    }
}

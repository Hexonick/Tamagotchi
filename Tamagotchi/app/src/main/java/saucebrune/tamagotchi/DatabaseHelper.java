package saucebrune.tamagotchi;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper{
    private SQLiteDatabase dataB;
    private static DatabaseHelper instance = null;

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

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    private DatabaseHelper(Context context) {
        super(context, "dbTamagotchi2", null, 1);
        dataB = this.getWritableDatabase();
        dataB.execSQL("PRAGMA foreign_keys=ON;");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_Account + "(" +
                idAccount + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                pseudo + " TEXT," +
                pass + " TEXT," +
                expTotal + " INTEGER," +
                nivAcc + " INTEGER," +
                speed + " INTEGER," +
                gainExp + " INTEGER);");

        db.execSQL("CREATE TABLE " + TABLE_Monstre + "(" +
                idMonstre + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                nomMonstre + " TEXT," +
                expMonstre + " INTEGER," +
                nivMonstre + " INTEGER," +
                tempsVivant + " INTEGER," +
                idAccount + "Acc INTEGER);");

        /*db.execSQL("CREATE TABLE " + TABLE_Monstre + "(" +
                idMonstre + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                nomMonstre + " TEXT," +
                expMonstre + " INTEGER," +
                nivMonstre + " INTEGER," +
                tempsVivant + " INTEGER," +
                idAccount + "Acc INTEGER," +
                "FOREIGN KEY (" + idAccount + "Acc) REFERENCES " + TABLE_Account + "(" + idAccount + "));");*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS dbTamagotchi");
        onCreate(db);
    }

    public void insertIntoTblAccount(String nom, String passW, int expT, int niv, int vitesse, int gain){
        dataB.execSQL("INSERT INTO " + TABLE_Account + " (" + pseudo + "," + pass + "," + expTotal + "," + nivAcc + "," + speed + "," + gainExp + ")" +
                " VALUES (\'" + nom + "\',\'" + passW + "\'," + expT + "," + niv + "," + vitesse + "," + gain + ")");
    }

    public void insertIntoTblMonstre(String nom, int exp, int niv, int temps, int id){
        dataB.execSQL("INSERT INTO " + TABLE_Monstre + " (" + nomMonstre + "," + expMonstre + "," + nivMonstre + "," + tempsVivant + "," + idAccount + "Acc" + ")" +
                " VALUES (\'" + nom + "\'," + exp + "," + niv + "," + temps + "," + id + ")");
    }

    public void updateNomMonstre(String NewName, String OldName){
        dataB.execSQL("UPDATE " + TABLE_Monstre + " SET " + nomMonstre + " = \'" + NewName + "\' WHERE " + nomMonstre + " = \'" + OldName + "\';");
    }

    public void updateExpMonstre(int exp, int id){
        dataB.execSQL("UPDATE " + TABLE_Monstre + " SET " + expMonstre + " = " + exp + " WHERE " + idAccount + "Acc = " + id + ";");
    }

    public int selectId(String nom){
        ArrayList<Integer> values = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + idAccount + " FROM " + TABLE_Account + " WHERE " + pseudo + " = \'" + nom + "\'", null);

        int info = 0;
        if(cursor.moveToFirst()) {
            do {
                info = cursor.getInt(cursor.getColumnIndex(idAccount));
            }while(cursor.moveToNext());
        }
        return info;
    }

    public ArrayList<String> selectPseudo() {
        ArrayList<String> values = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + pseudo + " FROM " + TABLE_Account, null);

        if(cursor.moveToFirst()) {
            do {
                values.add(cursor.getString(cursor.getColumnIndex(pseudo)));
            }while(cursor.moveToNext());
        }
        return values;
    }

    public String selectPseudo(int account) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + pseudo + " FROM " + TABLE_Account + " WHERE " + idAccount + " = " + account, null);
        String values = "";
        if(cursor.moveToFirst()) {
            do {
                values = cursor.getString(cursor.getColumnIndex(pseudo));
            }while(cursor.moveToNext());
        }
        return values;
    }

    public ArrayList<String> selectPassword() {
        ArrayList<String> values = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + pass + " FROM " + TABLE_Account, null);

        if(cursor.moveToFirst()) {
            do {
                values.add(cursor.getString(cursor.getColumnIndex(pass)));
            }while(cursor.moveToNext());
        }
        return values;
    }

    public String selectPassword(int account) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + pass + " FROM " + TABLE_Account + " WHERE " + idAccount + " = " + account, null);
        String values = "";
        if(cursor.moveToFirst()) {
            do {
                values = cursor.getString(cursor.getColumnIndex(pass));
            }while(cursor.moveToNext());
        }
        return values;
    }

    public String selectNomMonstre(int account) {
        String values = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + nomMonstre + " FROM " + TABLE_Monstre + " WHERE " + idAccount + "Acc = " + account, null);

        if(cursor.moveToFirst()) {
            do {
                values = cursor.getString(cursor.getColumnIndex(nomMonstre));
            }while(cursor.moveToNext());
        }
        return values;
    }

    public int[] selectExpMonstre(int account) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + expMonstre + " FROM " + TABLE_Monstre + " WHERE " + idAccount + "Acc = " + account, null);
        int i = 0;
        int[] values = new int[4];
        if(cursor.moveToFirst()) {
            do {
                values[i] = cursor.getInt(cursor.getColumnIndex(expMonstre));
                i++;
            }while(cursor.moveToNext());
        }
        return values;
    }
}

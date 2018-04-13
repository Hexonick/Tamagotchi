package saucebrune.tamagotchi;

import android.app.Application;

public class AccountData extends Application{
    private static AccountData instance = null;
    private String pseudo = "";
    private String password = "";
    private String[] nomMonstre = {"","","",""};
    private int nivAccount = 0;
    private int[] tempsVivant = {0,0,0,0};
    private int[] nivMonstre = {0,0,0,0};
    private int[] expMonstre  = {-1,-1,-1,-1};
    private int[] speed = {3000,3000,3000,3000};
    private int[] gainExp = {1,1,1,1};

    private AccountData(){}

    public static AccountData getInstance(){
        if(instance == null){
            instance = new AccountData();
        }
        return instance;
    }

    public String getPseudo() { return pseudo; }
    public void setPseudo(String nom) { pseudo = nom; }

    public String getPassword() { return password; }
    public void setPassword(String pass) { password = pass; }

    public String getNomMonstre(int id) { return nomMonstre[id]; }
    public void setNomMonsre(String nom,int id) { nomMonstre[id] = nom; }

    public int getNivAccount() { return nivAccount; }
    public void setNivAccount(int niv) { nivAccount = niv; }

    public int getTempsVivant(int id) { return tempsVivant[id]; }
    public void setTempsVivant(int temps,int id) { tempsVivant[id] = temps; }

    public int[] getNivMonstre() { return nivMonstre; }
    public void setNivMonsre(int niv, int id) { nivMonstre[id] = niv; }

    public int getExpMonstre(int id) { return expMonstre[id]; }
    public void setExpMonsre(int exp,int id) { expMonstre[id] = exp; }

    public int getSpeed(int id) { return speed[id]; }
    public void setSpeed(int vit,int id) { speed[id] = vit; }

    public int getGainExp(int id) { return gainExp[id]; }
    public void setGainExp(int gain,int id) { gainExp[id] = gain; }

}

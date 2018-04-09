package saucebrune.tamagotchi;

import android.app.Application;


public class AccountData extends Application{
    private String[] nomMonstre;
    private int nivAccount;
    private int[] tempsVivant;
    private int[] nivMonstre;
    private int[] expMonstre;
    private int[] speed;
    private int[] gainExp;

    public String[] getNomMonstre() { return nomMonstre; }
    public void setNomMonsre(String nom,int id) { nomMonstre[id] = nom; }

    public int getNivAccount() { return nivAccount; }
    public void setNivAccount(int niv) { nivAccount = niv; }

    public int[] getTempsVivant() { return tempsVivant; }
    public void setTempsVivant(int temps,int id) { tempsVivant[id] += temps; }

    public int[] getNivMonstre() { return nivMonstre; }
    public void setNivMonsre(int niv, int id) { nivMonstre[id] = niv; }

    public int[] getExpMonstre() { return expMonstre; }
    public void setExpMonsre(int exp,int id) { expMonstre[id] += exp; }

    public int[] getSpeed() { return speed; }
    public void setSpeed(int vit,int id) { speed[id] = vit; }

    public int[] getGainExp() { return gainExp; }
    public void setGainExp(int gain,int id) { gainExp[id] = gain; }

}

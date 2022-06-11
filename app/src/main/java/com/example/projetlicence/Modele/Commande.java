package com.example.projetlicence.Modele;

import java.util.List;

public class Commande {
    String nr_commande,id_commande,id_user,date,prix;


    public Commande() {
    }

    public Commande(String nr_commande, String id_commande, String id_user, String date,String prix) {
        this.nr_commande = nr_commande;
        this.id_commande=id_commande;
        this.id_user = id_user;
        this.date = date;
        this.prix= prix;

    }

    public String getNr_commande() {
        return nr_commande;
    }

    public void setNr_commande(String nr_commande) {
        this.nr_commande = nr_commande;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getId_commande() {
        return id_commande;
    }

    public void setId_commande(String id_commande) {
        this.id_commande = id_commande;
    }
}

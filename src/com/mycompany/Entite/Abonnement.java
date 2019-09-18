/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.Entite;


public class Abonnement {
    private int id_abonne;
    private int id_source;
    private int etat;

    public Abonnement() {
    }

    public Abonnement(int id_abonne, int id_source) {
        this.id_abonne = id_abonne;
        this.id_source = id_source;
    }

    public Abonnement(int id_abonne, int id_source, int etat) {
        this.id_abonne = id_abonne;
        this.id_source = id_source;
        this.etat = etat;
    }

    
    
    
    public int getId_abonne() {
        return id_abonne;
    }

    public void setId_abonne(int id_abonne) {
        this.id_abonne = id_abonne;
    }

    public int getId_source() {
        return id_source;
    }

    public void setId_source(int id_source) {
        this.id_source = id_source;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
    
    
    
    
    
}

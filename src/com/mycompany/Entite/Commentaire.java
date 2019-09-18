/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

/**
 *
 * @author firas
 */

import java.util.Date;

public class Commentaire {
    private int id;
    private int idBonPlan;
    private int idAuteur;
    private String contenu;
    private String dateCreation;
            
    public Commentaire(int idBonPlan, int idAuteur, String contenu) {
        this.idBonPlan = idBonPlan;
        this.idAuteur = idAuteur;
        this.contenu = contenu;
    }
    
    
    public Commentaire(int idBonPlan, int idAuteur, String contenu,String dateCreation) {
        this.idBonPlan = idBonPlan;
        this.idAuteur = idAuteur;
        this.contenu = contenu;
        this.dateCreation=dateCreation;
    }

    public Commentaire(int id, int idBonPlan, int idAuteur, String contenu, String dateCreation) {
        this.id = id;
        this.idBonPlan = idBonPlan;
        this.idAuteur = idAuteur;
        this.contenu = contenu;
        this.dateCreation = dateCreation;
    }

    public Commentaire() {
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBonPlan() {
        return idBonPlan;
    }

    public void setIdBonPlan(int idBonPlan) {
        this.idBonPlan = idBonPlan;
    }

    public int getIdAuteur() {
        return idAuteur;
    }

    public void setIdAuteur(int idAuteur) {
        this.idAuteur = idAuteur;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }
    
    
    
}

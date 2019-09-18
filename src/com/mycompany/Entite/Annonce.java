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
public class Annonce {
   private String titre;


    String adresse;
    int createur;
    String photo;
    String description;

    public Annonce() {
    }

    public Annonce(int createur, String titre, String description, String adresse,String photo) {
        this.createur = createur;
        this.titre = titre;
        this.adresse=adresse;
        this.photo=photo;
          this.description=description;
       
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

  
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adress) {
        this.adresse = adress;
    }

    public int getCreateur() {
        return createur;
    }

    public void setCreateur(int createur) {
        this.createur = createur;
    }
   

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

   

    @Override
    public String toString() {
        return "Task{" + "auteur=" + createur + ", nom=" + titre + '}';
    }
           
}

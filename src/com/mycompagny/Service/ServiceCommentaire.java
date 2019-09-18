/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagny.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entite.Commentaire;
import com.mycompany.Entite.Utilisateur;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author firas
 */
public class ServiceCommentaire {
    ////////////////:pdf//////////////////////////////
//    public Reservation ReservationPdf(MultipartRequest con)
//    {
////         String Url = "http://127.0.0.1/bons_plans/crudCommentaire.php?type=reservationPdf";
////          con.setUrl(Url);
////        con.addResponseListener((e) -> {
////        String str = new String(con.getResponseData());
////                        System.out.println(str);
////
////            System.out.println("coco");
//////            if (str.trim().equalsIgnoreCase("OK")) {
//////                f2.setTitle(tlogin.getText());
//////             f2.show();
//////            }
//////            else{
//////            Dialog.show("error", "login ou pwd invalid", "ok", null);
//////            }
////        });
////        NetworkManager.getInstance().addToQueueAndWait(con);
//    
//    
//    
//    }
    
    
    
    
    
    
    
    
    ///////////////////////////:::
    
    
    
    
     public void ajoutCommentaire(Commentaire comment,MultipartRequest con) {
        String Url = "http://127.0.0.1/BonPlanMobile/crudCommentaire.php?type=ajout&idBonPlan="+comment.getIdBonPlan()+"&idAuteur="+comment.getIdAuteur()+"&contenu="+comment.getContenu();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
        String str = new String(con.getResponseData());
                        System.out.println(str);

            System.out.println("coco");
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
     
      public void supprimerCommentaire(int idComment,MultipartRequest con) {
        String Url = "http://127.0.0.1/BonPlanMobile/crudCommentaire.php?type=supprimer&idCommentaire="+idComment;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
        String str = new String(con.getResponseData());
                        System.out.println(str);

            System.out.println("coco");
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
     
     public ArrayList<Commentaire> getComments(int idBonPlan) 
     {
        ArrayList<Commentaire> listComments = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1/BonPlanMobile/crudCommentaire.php?type=afficher&idBonPlan="+idBonPlan);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;                
                jsonp = new JSONParser();
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> mapCpmments = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapCpmments.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        Commentaire comment = new Commentaire();
                        float id = Float.parseFloat(obj.get("id").toString());
                        float idAuteur = Float.parseFloat(obj.get("id_auteur").toString());
                        String contenu = obj.get("contenu").toString();
                        String date=obj.get("date_creation").toString();

                        comment.setId((int) id);
                        comment.setIdAuteur((int) idAuteur);
                        comment.setContenu(contenu);
                        comment.setDateCreation(date);
                        
                        listComments.add(comment);

                    }
                } catch (IOException ex) {
                    System.out.println(ex);
                } 

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listComments;
    }
     
     public Utilisateur getAuteurById(int idAuteur)
     {
    
    ArrayList<Utilisateur> auteurList = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
  String Url = "http://127.0.0.1/BonPlanMobile/crudCommentaire.php?type=getAuteur&idAuteur="+idAuteur; 
  con.setUrl(Url);
  con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;                
                jsonp = new JSONParser();
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> mapAuteur = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapAuteur.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        Utilisateur author = new Utilisateur();
                        String username = obj.get("username").toString();
                        author.setUsername(username);
                        String photoDeProfil=obj.get("photo_de_profil").toString();
                        author.setPhotoDeProfil(photoDeProfil);
                        auteurList.add(author);

                    }
                } catch (IOException ex) {
                    System.out.println(ex);
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        Utilisateur auteur=auteurList.get(0);
        return auteur;
     }
     //////////////pdf//////////////////////:
 
     
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagny.Service;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.facebook.User;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.mycompany.gui.UserForm;
import com.codename1.io.Util;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.URLImage.ImageAdapter;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.Entite.Abonnement;

import com.mycompany.Entite.Annonce;
import com.mycompany.gui.FBLogin6;

import com.mycompany.gui.SingleAnnonceGui;
import com.mycompany.gui.UserForm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author firas
 */
public class ServiceAnnonce {
     private Annonce currentAnnonce;
    private ConnectionRequest connectionRequest;
    
    public void ajoutAnnonce(Annonce ta,MultipartRequest con) {
      // final User me = new User();
           
              
String Url = "http://127.0.0.1/Ecosmartweb/web/app_dev.php/api/annonces/add?titre="+ ta.getTitre()+"&photo="+ ta.getPhoto()+"&description="+ ta.getDescription()+"&adresse="+ ta.getAdresse();
 //  String Url = "http://127.0.0.1/Ecosmartweb/web/app_dev.php/api/annonces/add?userid="+ me.getId()+"&photo="+ ta.getPhoto()+"&description="+ ta.getDescription()+"&adresse="+ ta.getAdresse();
  
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        


    }
    

    public Container getList2(Resources theme) {
                           
        
        Container container1All = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        ArrayList<Annonce> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1/ecosmartmobil/affichageannonce.php");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                ArrayList<Annonce> listTasks = new ArrayList<>();
                JSONParser jsonp = new JSONParser();
                
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                   // System.out.println("roooooot:" +tasks.get("root"));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

                    for (Map<String, Object> obj : list) {
                        Annonce task = new Annonce();
                         task.setDescription(obj.get("description").toString());
                         
                           task.setPhoto(obj.get("photo").toString());
                        float id = Float.parseFloat(obj.get("createur").toString());
                       
                        task.setCreateur((int) id);
                                 task.setTitre(obj.get("titre").toString());
                        task.setAdresse(obj.get("adresse").toString());
                      
               
                      
                        Label titre=new Label("Titre :"+obj.get("titre").toString());
                        Label lauteur=new Label(obj.get("createur").toString());
                        Label adresse=new Label("Adresse"+obj.get("adresse").toString());
         EncodedImage encImg  = EncodedImage.createFromImage(UIManager.initFirstTheme("/theme").getImage("load.gif")
                .fill(125, 175),false);
        URLImage imgUrl = URLImage.createToStorage(encImg, task.getPhoto(), "http://localhost/Ecosmartweb/web/uploads/annonce/"+task.getPhoto()
                ,URLImage.RESIZE_SCALE_TO_FILL);
        
        ImageViewer image = new ImageViewer(imgUrl);
                        System.out.println(task.getPhoto());
        

                       
                        Button editBtn = new Button("Edit");
        editBtn.getUnselectedStyle().setFgColor(5542241);
                        editBtn.addActionListener((e) -> {
                        new ServiceAnnonce().updateAnnonce(currentAnnonce);

        });
                        
                         Button abonnementButton= new Button ();
                        //System.out.println("------------------");
                        //System.out.println(id);
    
                        Abonnement abonnement=new Abonnement(1, (int) id);
                        ServiceAbonnement serviceAbonnement= new ServiceAbonnement();
                             
    
                            Container container = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
                            Container container1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                            Container container2 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                            serviceAbonnement.afficherButtonAbonnement(container1, abonnementButton, abonnement);
                              
container1.add(titre);
container1.add(adresse);
container1.add(abonnementButton);
container1.add(image);


container2.add(editBtn);
//container2.add(fbbtn);



        container.add(BorderLayout.CENTER, container1);

    container.add(BorderLayout.SOUTH, container2);
    
                        
//                        Container c=new Container();
//                        c.addAll(titre,lauteur,adresse,nbr);
                  container1All.add(container);
                        
                        
                        

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return container1All;
    }
 public void updateAnnonce(Annonce b){
             
    connectionRequest = new ConnectionRequest() {
            
            @Override
            protected void postResponse() { 
                Dialog d = new Dialog("Popup Title");
                TextArea popupBody = new TextArea("Book updated");
                popupBody.setUIID("PopupBody");
                popupBody.setEditable(false);
                d.setLayout(new BorderLayout());
                d.add(BorderLayout.CENTER, popupBody);
                d.show();
            }
        };
        connectionRequest.setUrl("http://localhost/shelfie/update.php?titre="+b.getTitre()+"&description="+b.getDescription()+
                                "&adresse="+b.getAdresse());
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
}

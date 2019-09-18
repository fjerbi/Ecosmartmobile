/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.InteractionDialog;
import com.codename1.components.ToastBar;
import com.codename1.components.WebBrowser;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.File;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.mycompagny.Service.ServiceAnnonce;
import com.mycompany.Entite.Annonce;
import com.sun.javafx.tk.FileChooserType;
import java.io.IOException;
import java.util.Hashtable;
import com.codename1.googlemaps.MapContainer.MapObject;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.messaging.Message;
import com.codename1.notifications.LocalNotification;
import com.codename1.processing.Result;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.list.DefaultListModel;

import com.mycompany.Entite.Utilisateur;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author firas
 */
public class AjoutForm {
String path;
String  filePath;
Container imgCtn;
static Annonce b=new Annonce();
    Form hi;
ImageViewer l = new ImageViewer();
MultipartRequest cr = new MultipartRequest();
    public AjoutForm() {
       hi = new Form("Ajouter une Annonce", new BoxLayout(BoxLayout.Y_AXIS));

        hi.setScrollVisible(true);
        hi.setSmoothScrolling(true);

        
        
        
            /*****************/
        
        
          Utilisateur utilisateuractif = new Utilisateur(1);

          
    
        
        TableLayout tl;
int spanButton = 2;
if(Display.getInstance().isTablet()) {
    tl = new TableLayout(7, 2);
} else {
    tl = new TableLayout(14, 1);
    spanButton = 1;
}
tl.setGrowHorizontally(true);
hi.setLayout(tl);

TextField Name = new TextField("", "Nom", 20, TextArea.ANY);
TextField Desc = new TextField("", "Description", 20, TextArea.ANY);


Button getimage = new Button("get image");
  getimage.addActionListener(e->{
            Display.getInstance().openGallery(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    if( evt!=null && evt.getSource()!=null){
                        path = (String)evt.getSource();
                        Image image = null;
                        try {
                            image = Image.createImage(FileSystemStorage.getInstance()
                                    .openInputStream(path)).fill(125, 175);
                        } catch (IOException ex) {
                            Dialog.show("Error", ex.getMessage(), "OK", null);
                        }
                        l = new ImageViewer(image);
                        l.getStyle().setMarginLeft(40);
                        imgCtn.add(l);
                    }
                }
            }, Display.GALLERY_IMAGE);
        });


/******************* autocomplete *******/

 final DefaultListModel<String> options = new DefaultListModel<>();
 AutoCompleteTextField adresse = new AutoCompleteTextField(options) {
     @Override
     protected boolean filter(String text) {
         if(text.length() == 0) {
             return false;
         }
         String[] l = searchLocations(text);
         if(l == null || l.length == 0) {
             return false;
         }

         options.removeAll();
         for(String s : l) {
             options.addItem(s);
         }
         return true;
     }

           private String[] searchLocations(String text) {
  try {
        if(text.length() > 0) {
            ConnectionRequest r = new ConnectionRequest();
            r.setPost(false);
            r.setUrl("https://maps.googleapis.com/maps/api/place/autocomplete/json");
            r.addArgument("key", "AIzaSyCy-fMWerzvXcPCV0FDI07hW2DAzs_mnpY");
            r.addArgument("input", text);
            NetworkManager.getInstance().addToQueueAndWait(r);
            Map<String,Object> result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(r.getResponseData()), "UTF-8"));
            
            String[] res = Result.fromContent(result).getAsStringArray("//description");
            return res;
        }
    } catch(Exception err) {
        Log.e(err);
    }
    return null;           }
 };
 adresse.setMinimumElementsShownInPopup(5);
 


        
/*********************Map**************************/


/********************/
   imgCtn = new Container();
Button submit = new Button("Submit");
Button map = new Button("Map");
Button cam = new Button("Open camera");

Button fbButton = new Button("Facebook");


if(b!=null)
{
    Name.setText(b.getTitre());
    
    adresse.setText(b.getAdresse());
   

}


TableLayout.Constraint cn = tl.createConstraint();
cn.setHorizontalSpan(spanButton);
cn.setHorizontalAlign(Component.RIGHT);
hi.add("Name").add(Name).
        add("Description").add(Desc).
        add("Adresse").add(adresse).add(map).
                add(getimage).add(cam).
        add(cn, submit).add(cn, fbButton).add(imgCtn);



        
        
        /*******************/
        
        
        
        
         cam.addActionListener((e) -> {
           
            cameraForm f=new cameraForm();
            f.init(cn);
            f.start();
        });
           //fbButton =new Button("Partager sur Facebook");
        fbButton.addActionListener(e->{
           String token="EAACAEBbvH2QBAJKXp9ZCa4FwStGlSrvWMvJlyZBfIzuyMwvx1XXZAlNUFUfLLkoLozsZCVYYd9mK28Qh7lbRzU2gG8L3mmkXktu5jUQzY0ZA9xwgkTzZBfsIOh3ExqMxBNAEU2ZAdHH2nRU98Faivo3Tfbpq1rCJkgYNDJIuc9tvgZDZD"; 
           FacebookClient facebookClient = new DefaultFacebookClient(token);
            FacebookType response= facebookClient.publish("me/feed", FacebookType.class, Parameter.with("message", Name.getText()+", Nouvelle Annonce!! Rendez-Vous a "+adresse.getText()));
            System.out.println("Partage avec succes");
        });
        submit.addActionListener((e) -> {
        
            ServiceAnnonce ser = new ServiceAnnonce();
          Annonce t = new Annonce(5, Desc.getText(),Name.getText(), adresse.getText(),path);
            ser.ajoutAnnonce(t,cr);
              


     ToastBar.Status status = ToastBar.getInstance().createStatus();
  status.setMessage("Hello world");
  status.setExpires(3000);  // only show the status for 3 seconds, then have it automatically clear
  status.show();         
Message m = new Message("Body of message");


        });
        
        map.addActionListener((e) -> { 
        b.setTitre(Name.getText());
        b.setDescription(Desc.getText());
        b.setAdresse(adresse.getText());
   
        MapForm mapf=new MapForm();
        });

        

    }

    public Form getF() {
        return hi;
    }

    public void setF(Form f) {
        this.hi = f;
    }



}

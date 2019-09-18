/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagny.Service;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanButton;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;

//import com.mycompany.Gui.AbonnementForm;
import com.codename1.ui.util.Resources;
import com.codename1.components.SpanLabel;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.mycompany.Entite.Abonnement;
import com.mycompany.Entite.Utilisateur;
import com.mycompany.gui.Affichage;
import com.mycompany.gui.AffichageAbonnement;
import com.mycompany.gui.AjoutForm;
import com.mycompany.myapp.MyApplication;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



/**
 *
 * @author amir
 */
public class ServiceAbonnement {
    public void ajoutAbonnement(Abonnement abonnement,MultipartRequest con) {
        String Url = "http://127.0.0.1/BonPlanMobile/crudAbonnement.php?type=ajout&idAbonne="+abonnement.getId_abonne()+"&idSource="+abonnement.getId_source();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
        String str = new String(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
    
    public void initEtat(int id,MultipartRequest con)
    {
        String Url = "http://127.0.0.1/BonPlanMobile/crudAbonnement.php?type=initEtat&idAbonne="+id;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
        String str = new String(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
    public ArrayList<String> getList(Abonnement abonnement) {
            ArrayList<String> listAbonnements = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        
        String Url = "http://127.0.0.1/BonPlanMobile/crudAbonnement.php?type=affichage&idAbonne="+abonnement.getId_abonne()+"&idSource="+abonnement.getId_source();
        con.setUrl(Url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                String  a;
                                try {
                    Map<String, Object> Abonnements = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Abonnements.get("result");
                    for (Map<String, Object> obj : list) {
                        Abonnement Abonnement = new Abonnement();
                       
                        
                        a=(obj.get("mawjoud").toString());
                        //System.out.println(a);
                        listAbonnements.add(a);

                        //System.out.println(listAbonnements);
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listAbonnements;
    }
    
    public void SupprimerAbonnement(Abonnement abonnement , MultipartRequest con){  
        String Url = "http://127.0.0.1/BonPlanMobile/crudAbonnement.php?type=supprimer&idAbonne="+abonnement.getId_abonne()+"&idSource="+abonnement.getId_source();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
        String str = new String(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    
    }
    
    public Container getAbonnements(Resources theme,Abonnement abonnement) {
         Container container1All = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        
            ArrayList<Utilisateur> listUtilisateur = new ArrayList<>();
            
            
            
            ConnectionRequest con = new ConnectionRequest();
            String Url = "http://127.0.0.1/BonPlanMobile/crudAbonnement.php?type=affichageUtilisateurByAbonne&idAbonne="+abonnement.getId_abonne();
            con.setUrl(Url);
            con.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    JSONParser jsonp = new JSONParser();
                    try {
                        Map<String, Object> utilisateurs = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                        //System.out.println(produits);
                        List<Map<String, Object>> list = (List<Map<String, Object>>) utilisateurs.get("result");
                        for (Map<String, Object> obj : list) {
                            Utilisateur utilisateur = new Utilisateur();
                            
                            utilisateur.setUsername(obj.get("username").toString());
                            utilisateur.setAdresse(obj.get("adresse").toString());
                            utilisateur.setPhotoDeProfil(obj.get("photoDeProfil").toString());
//                        prod.setImg(obj.get("photo").toString());

            Image img;
            ImageViewer imgv;
            EncodedImage enc = EncodedImage.create("/giphy.gif").scaledEncoded(100, 100);
            
                             String url="http://localhost/BonPlanMobile/Images/"+utilisateur.getPhotoDeProfil();
            img=URLImage.createToStorage(enc, url,url, URLImage.RESIZE_SCALE);
            
            imgv=new ImageViewer(img);

Container container = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
Container container1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
Container container2 = new Container(new BoxLayout(BoxLayout.X_AXIS));

container1.add(utilisateur.getUsername());
container1.add(utilisateur.getAdresse());
container2.add(imgv).add(container1);
        
        container1All.add(container2);
        
        }
                    } catch (IOException ex) {
                    }

                }
            });
            
        
            NetworkManager.getInstance().addToQueueAndWait(con);
        return container1All;
        
    }

    
    public ArrayList<Utilisateur> getNotifications(Utilisateur utilisateur){
        ArrayList<Utilisateur> listUtilisateur = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://127.0.0.1/BonPlanMobile/crudAbonnement.php?type=affichageNotificatons&idAbonne="+utilisateur.getId();
        con.setUrl(Url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                                try {
                    Map<String, Object> utilisateurs = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    //System.out.println(produits);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) utilisateurs.get("result");
                    for (Map<String, Object> obj : list) {
                        Utilisateur utilisateur = new Utilisateur();
                       
                      //  System.out.println(obj.get("id").toString());
                      
                        utilisateur.setId(Integer.parseInt(obj.get("id").toString()));
                        
                        utilisateur.setUsername(obj.get("username").toString());

//                        prod.setImg(obj.get("photo").toString());

                        listUtilisateur.add(utilisateur);
                        
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listUtilisateur;
    }
    
    public void afficherNotifications(Utilisateur utilisateur)
    {
        
            MultipartRequest request = new MultipartRequest();

         ArrayList<Utilisateur> listUtilisateur = new ArrayList<>();
       listUtilisateur= this.getNotifications(utilisateur);
       
       for (int i=0;i<listUtilisateur.size();i++)
    {
Dialog dlg = new Dialog("Notification !");
dlg.setLayout(new BoxLayout(600));
// span label accepts the text and the UIID for the dialog body
dlg.add(new SpanLabel(listUtilisateur.get(i).getUsername()+" a mis en ligne une publication"));
int h = Display.getInstance().getDisplayHeight();
dlg.setDisposeWhenPointerOutOfBounds(true);

dlg.show(150, 150, 0, 0);
        //System.out.println(listUtilisateur.get(i).getId());
    this.initEtat(utilisateur.getId(), request);
    }
    }
    
    public void afficherButtonAbonnement(Container container,Button button,Abonnement abonnement)
    {
        try {
            MultipartRequest request = new MultipartRequest();
            
            
            Image img1;
            Image img2;
            ImageViewer abonnerr;
            ImageViewer desabonner;
            EncodedImage encc=EncodedImage.create("/giphy.gif").scaledEncoded(100, 100);
            
            ArrayList<String> l=new ArrayList<>();
            l=this.getList(abonnement);
            System.out.println(l.get(0));
            Button abonner = new Button();
            
            
            
            String url1="http://localhost/BonPlanMobile/Images/coeur.png" ;
            img1=URLImage.createToStorage(encc, url1,url1, URLImage.RESIZE_SCALE);
            
            abonnerr=new ImageViewer(img1);
            
            String url2="http://localhost/BonPlanMobile/Images/coeur2.png" ;
            img2=URLImage.createToStorage(encc, url2,url2, URLImage.RESIZE_SCALE);
            
            desabonner=new ImageViewer(img2);
            
            
            if ( l.get(0).equals("0"))
            {
                //abonner.setIcon(img2);
                abonner.setText("Abonner");
                
                abonner.addActionListener((e) -> {
                    
                    this.ajoutAbonnement(abonnement,request);
                    Resources  theme = UIManager.initFirstTheme("/theme");
                    Affichage a=new Affichage(theme);
                    Container caff=a.getC();
                    
                    AffichageAbonnement ab=new AffichageAbonnement(theme);
                    Container abonnementContainer=ab.getC();
                    Form hi = new Form(new LayeredLayout());
                    
                    EncodedImage enc = EncodedImage.createFromImage(theme.getImage("addicon.png"),true);
                    
                    Image img=URLImage.createToStorage(enc, "ff", "dd");
                    hi.getToolbar().addCommandToRightBar("",img , (ev)->{      AjoutForm h = new AjoutForm();
                    h.getF().show();
                    });
                    
                    
                    
                    
                    Container container1 = BoxLayout.encloseY(new Label("Profile"),
                            new Label("You can put anything here"));
                    
                    
                    Tabs tb = new Tabs() {
                        @Override
                        protected Component createTab(String title, Image icon) {
                            SpanButton custom = new SpanButton(title);
                            custom.setIcon(icon);
                            custom.setUIID("Container");
                            custom.setTextUIID("Tab");
                            custom.setIconPosition(BorderLayout.NORTH);
                            custom.setIconUIID("Tab");
                            return custom;
                        }
                        
                        @Override
                        protected void setTabSelectedIcon(Component tab, Image icon) {
                            ((SpanButton)tab).setPressedIcon(icon);
                        }
                        
                        protected void selectTab(Component tab) {
                        }
                        
                        @Override
                        protected void bindTabActionListener(Component tab, ActionListener l) {
                            ((SpanButton)tab).addActionListener(l);
                        }
                    };
                    tb.setTabUIID(null);
                    tb.addTab("Acceuil",  caff);
                    tb.addTab("Profile", container1);
                    tb.addTab("xxx",new Label("T3"));
                    tb.addTab("Following", abonnementContainer);
                    
                    
                    tb.getTabsContainer().setScrollableX(false);
                    
                    hi.add(BorderLayout.CENTER, tb);
                    
                    hi.show();
                });
                final String value="1";
                l.set(0, value);
                container.add(abonner);
                
            }
            
            
            else if (l.get(0).equals("1"))
            {
                
                //abonner.setIcon(img1);
                abonner.setText("Desabonner");
                abonner.addActionListener((e) -> {
                    
                    this.SupprimerAbonnement(abonnement,request);
                    //abonner.setText("Abonner");
                    Resources  theme = UIManager.initFirstTheme("/theme");
                    Affichage a=new Affichage(theme);
                    Container caff=a.getC();
                    Form hi = new Form(new LayeredLayout());
                    
                    EncodedImage enc = EncodedImage.createFromImage(theme.getImage("addicon.png"),true);
                    
                    Image img=URLImage.createToStorage(enc, "ff", "dd");
                    hi.getToolbar().addCommandToRightBar("",img , (ev)->{      AjoutForm h = new AjoutForm();
                    h.getF().show();
                    });
                    
                    
                    
                    
                    Container container1 = BoxLayout.encloseY(new Label("Profile"),
                            new Label("You can put anything here"));
                    
                    
                    Tabs tb = new Tabs() {
                        @Override
                        protected Component createTab(String title, Image icon) {
                            SpanButton custom = new SpanButton(title);
                            custom.setIcon(icon);
                            custom.setUIID("Container");
                            custom.setTextUIID("Tab");
                            custom.setIconPosition(BorderLayout.NORTH);
                            custom.setIconUIID("Tab");
                            return custom;
                        }
                        
                        @Override
                        protected void setTabSelectedIcon(Component tab, Image icon) {
                            ((SpanButton)tab).setPressedIcon(icon);
                        }
                        
                        protected void selectTab(Component tab) {
                        }
                        
                        @Override
                        protected void bindTabActionListener(Component tab, ActionListener l) {
                            ((SpanButton)tab).addActionListener(l);
                        }
                    };
                    AffichageAbonnement ab=new AffichageAbonnement(theme);
                    Container abonnementContainer=ab.getC();
                    tb.setTabUIID(null);
                    tb.addTab("Acceuil",  caff);
                    tb.addTab("Profile", container1);
                    tb.addTab("xxx",new Label("T3"));
                    tb.addTab("Following", abonnementContainer);
                    
                    
                    tb.getTabsContainer().setScrollableX(false);
                    
                    hi.add(BorderLayout.CENTER, tb);
                    
                    hi.show();
                    
                });
                final String value="0";
                l.set(0, value);
                
                container.add(abonner);
                
            }   } catch (IOException ex) {
            //Logger.getLogger(ServiceAbonnement.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            }

    }
}

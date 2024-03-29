package com.ecosmart.app;


import com.codename1.components.SpanButton;
import static com.codename1.ui.CN.*;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.ui.Toolbar;
import java.io.IOException;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.io.NetworkEvent;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Tabs;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.LayeredLayout;

import com.mycompany.Entite.Utilisateur;
import com.mycompany.gui.Affichage;

import com.mycompany.gui.AjoutForm;
import com.mycompany.gui.Login;
import com.mycompany.gui.UserForm;

/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename One</a> for the purpose 
 * of building native mobile applications using Java.
 */
public class MyApplication {
   
    private Form current;
    private Resources theme;

    public void init(Object context) {
        // Enable Toolbar on all Forms by default
         theme = UIManager.initFirstTheme("/theme");
        Toolbar.setGlobalToolbar(true);

        // Pro only feature, uncomment if you have a pro subscription
        // Log.bindCrashProtection(true);
    }

    public void start() {
        if(current != null){
            current.show();
            return;
        }

Affichage a=new Affichage(theme);
       Container caff=a.getC();

        
   
    
Form hi = new Form(new LayeredLayout());

   EncodedImage enc = EncodedImage.createFromImage(theme.getImage("addicon.png"),true);

   Image img=URLImage.createToStorage(enc, "ff", "dd");
hi.getToolbar().addCommandToRightBar("",img , (ev)->{      AjoutForm h = new AjoutForm();
       h.getF().show();
          });


Container container1 = BoxLayout.encloseY(new Label("Profile"),
        new Label("Votre profil"));

                                Button fbbtn=new Button("Login Avec Facebook");
                                Button login=new Button("Login");
                                
                        fbbtn.addActionListener((e) -> {
                            UserForm   FBLogin6=new UserForm();
                            FBLogin6.show();

        });   
                                          login.addActionListener((e) -> {
                            Login log = new Login();
        log.start();

        }); 
container1.add(fbbtn);
container1.add(login);
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
tb.addTab("Top Annonce",new Label("Hello"));




tb.getTabsContainer().setScrollableX(false); 

hi.add(BorderLayout.CENTER, tb);

hi.show();


        Utilisateur utilisateuractif = new Utilisateur(1);

          
 
        
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
    }
    
    public void destroy() {
    }
}

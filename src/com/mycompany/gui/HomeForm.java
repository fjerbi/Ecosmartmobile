/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.mycompagny.Service.ServiceAnnonce;
import com.mycompany.Entite.Annonce;

/**
 *
 * @author firas
 */
public class HomeForm {

    Form f;
    TextField tnom;
    TextField tetat;
    Button btnajout,btnaff,env;

    public HomeForm(Resources theme) {
        f = new Form("home");
        btnajout = new Button("ajouter");
        btnaff=new Button("Affichage");
        btnaff=new Button("Affichage");
        env=new Button("envoyer");

        f.add(btnajout);
        f.add(btnaff);
        f.add(env);

        btnajout.addActionListener((e) -> {
            AjoutForm a=new AjoutForm();
        a.getF().show();

        });
        btnaff.addActionListener((e)->{
//        Affichage a=new Affichage(theme);
//        a.getF().show();
        });
        env.addActionListener((e)->{
        Message m = new Message("<html><body>Check out <a href=\"https://www.codenameone.com/\">Codename One</a></body></html>");
m.setMimeType(Message.MIME_HTML);

// notice that we provide a plain text alternative as well in the send method
boolean success = m.sendMessageViaCloudSync("Codename One", "destination@domain.com", "Name Of User", "Message Subject",
                            "Check out Codename One at https://www.codenameone.com/");
        });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public TextField getTnom() {
        return tnom;
    }

    public void setTnom(TextField tnom) {
        this.tnom = tnom;
    }

}

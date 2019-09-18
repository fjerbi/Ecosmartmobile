/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompagny.Service.ServiceAnnonce;
import com.mycompany.Entite.Utilisateur;

/**
 *
 * @author firas
 */
public class LoginForm extends Form{
  
   
    TextField login = new TextField("", "login");
    TextField password = new TextField("", "password");
    Button valider = new Button("valider");
    public LoginForm(Resources theme) {
        setTitle("welcome");
     
        add(login);
        add(password);
        add(valider);
    }
    }

 


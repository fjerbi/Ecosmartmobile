/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.MultipartRequest;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import static com.codename1.ui.events.ActionEvent.Type.Theme;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.ListModel;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.EventDispatcher;
import com.codename1.ui.util.Resources;

import com.mycompagny.Service.ServiceAnnonce;
import com.mycompany.Entite.Abonnement;
import com.mycompany.Entite.Annonce;
import com.mycompany.Entite.Utilisateur;
import java.io.IOException;
import java.util.ArrayList;




public class AffichageAbonnement {

    SpanLabel lb;
    Container container1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    MultipartRequest request = new MultipartRequest();
        Image img;
    ImageViewer imgv;
    EncodedImage enc;
    public AffichageAbonnement(Resources theme) {
                
        Container container1All = new Container(new BoxLayout(BoxLayout.Y_AXIS));

  
        Abonnement abonnement = new Abonnement(1, 5);

    


        Utilisateur utilisateuractif = new Utilisateur(1);
        

        
    }

    public Container getC() {
        return container1;
    }

    public void setF(Container c) {
        this.container1 = c;
    }
}  
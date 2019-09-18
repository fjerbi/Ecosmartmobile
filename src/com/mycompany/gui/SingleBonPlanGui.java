/*
 * To change tforms license header, choose License Headers in Project Properties.
 * To change tforms template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.MultipartRequest;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.ListModel;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.table.TableLayout;
import com.mycompagny.Service.ServiceCommentaire;
import com.mycompany.Entite.Commentaire;
import com.mycompany.Entite.Utilisateur;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author firas
 */
public class SingleBonPlanGui {
    
    Form form;
    MultipartRequest request = new MultipartRequest();
    private ImageViewer photoProfilViewer;
    private Image img;
    private int idBonPlan;

    public int getIdBonPlan() {
        return idBonPlan;
    }

    public void setIdBonPlan(int idBonPlan) {
        this.idBonPlan = idBonPlan;
    }
    EncodedImage placeholder;
    
    
    public SingleBonPlanGui(int idBonPlan) throws IOException {
        //Form
        
        this.idBonPlan=idBonPlan;
        form = new Form("Box Y Layout", new BoxLayout(BoxLayout.Y_AXIS));
        form.setScrollVisible(true);
        form.setSmoothScrolling(true);;
            /*****************/        
        TableLayout tableLayout;
        int spanButton = 2;
        if(Display.getInstance().isTablet()) {
        tableLayout = new TableLayout(7, 2);
} else {
    tableLayout = new TableLayout(14, 1);
    spanButton = 1;
}
tableLayout.setGrowHorizontally(true);
form.setLayout(tableLayout);


/********************/
ServiceCommentaire serviceCommentaire=new ServiceCommentaire();
ArrayList<Commentaire> listComments= new ArrayList<>();
listComments=serviceCommentaire.getComments(idBonPlan);
List<String> commentors=new ArrayList<>();
 
for(Commentaire comm:listComments)
{
 Utilisateur auteur=serviceCommentaire.getAuteurById(comm.getIdAuteur());
            if(!commentors.contains(auteur.getUsername()))
            commentors.add(auteur.getUsername());
}

ListModel<String> test=new DefaultListModel<String>();
for(String a:commentors)
    test.addItem(a);

 AutoCompleteTextField contenu = new AutoCompleteTextField(test);
// {
//     protected boolean filter(String text)
//     {
//          if(text.length() == 0) 
//             return false;     
//             
//             else if(!text.startsWith("@"))
////                 for(int i=0; i<test.getSize(); i++)
////                     test.removeItem(i);
//                 return false;
//          
//          return true;
//     }
// };     
         
         
 contenu.setStartsWithMode(true);
 contenu.setHint("Votre avis nous interesse !");  
 contenu.setMinimumElementsShownInPopup(3);
 
 //contenu.setInlineStylesTheme(inlineStylesTheme);
 //contenu.setGrowLimit(3);
 
Button publier = new Button("Publier");
TableLayout.Constraint cn = tableLayout.createConstraint();
cn.setHorizontalSpan(spanButton);
cn.setHorizontalAlign(Component.RIGHT);
form.add("Contenu").add(contenu).add(cn, publier);
        
        /*******************/
Dialog dlg = new Dialog("Authentication");
    Style dlgStyle = dlg.getDialogStyle();
    dlgStyle.setBorder(Border.createEmpty());
    dlgStyle.setBgTransparency(255);
    dlgStyle.setBgColor(0xffffff);

    Label title = dlg.getTitleComponent();
    title.getUnselectedStyle().setFgColor(0xff);
    title.getUnselectedStyle().setAlignment(Component.LEFT);

    dlg.setLayout(BoxLayout.y());
    Label blueLabel = new Label();
    blueLabel.setShowEvenIfBlank(true);
    blueLabel.getUnselectedStyle().setBgColor(0xff);
    blueLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
    blueLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
    dlg.add(blueLabel);
    
    TextArea ta = new TextArea("Commentaire vide");
    ta.setEditable(false);
    ta.setUIID("DialogBody");
    ta.getAllStyles().setFgColor(0);
    dlg.add(ta);

    Label grayLabel = new Label();
    grayLabel.setShowEvenIfBlank(true);
    grayLabel.getUnselectedStyle().setBgColor(0xcccccc);
    grayLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
    grayLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
    dlg.add(grayLabel);

    Button ok = new Button(new Command("OK"));
    ok.getAllStyles().setBorder(Border.createEmpty());
    ok.getAllStyles().setFgColor(0);
    dlg.add(ok);
    
publier.addActionListener((e) -> {
            Commentaire comment = new Commentaire(idBonPlan,1,contenu.getText());
            if(comment.getContenu().isEmpty())
                dlg.show();
            else {try {
                serviceCommentaire.ajoutCommentaire(comment,request);
                SingleBonPlanGui singleBonPlanGui= new SingleBonPlanGui(idBonPlan);
                singleBonPlanGui.getForm().show();
                } catch (IOException ex) {
                   System.out.println(ex);
                }
            }
        });

/******************************************************************************/
            //Affichage comments//
//      for(Commentaire comment:listComments){
//          
//            Container commentContainer=new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER));
//            Stroke borderStroke = new Stroke(10, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
//            RoundBorder border=RoundBorder.create().color(0x99CCCC).strokeColor(5).
//            strokeOpacity(120).stroke(borderStroke).rectangle(true);
//
//            Container authorDateContainer=new Container(BoxLayout.y());
//            Label dateLabel=new Label();
//            dateLabel.setText(comment.getDateCreation().substring(0,comment.getDateCreation().length()-3));
//            
//            Utilisateur auteur=serviceCommentaire.getAuteurById(comment.getIdAuteur());
//            placeholder=EncodedImage.create("/testos.png");
//            String url="http://localhost/bons_plans/image/"+auteur.getPhotoDeProfil();
//            img=URLImage.createToStorage(placeholder, url,url, URLImage.RESIZE_SCALE);
//            photoProfilViewer=new ImageViewer(img);
//            
//            Label auteurLabel=new Label();
//            auteurLabel.setText(auteur.getUsername());
//            
//            authorDateContainer.add(dateLabel).add(photoProfilViewer).add(auteurLabel);
//            Container avisContainer= new Container(BoxLayout.x());
//            
//            Label avisLabel=new Label();
//            avisLabel.setText(comment.getContenu());
//            avisContainer.add(avisLabel);
//            
//            commentContainer.addComponent(BorderLayout.WEST,authorDateContainer);
//            commentContainer.addComponent(BorderLayout.CENTER,avisContainer);
//
//            if(comment.getIdAuteur()==1){
//                Button supprimer=new Button("Supprimer");
//                commentContainer.add(BorderLayout.SOUTH,supprimer);
//                supprimer.addActionListener((e) -> {
//                    if(Dialog.show("Valider", "Voulez vous vraiment supprimer ce commentaire ? ", "Confirmer", "Annuler"))
//                serviceCommentaire.supprimerCommentaire(comment.getId(), request);
//        });
//            }
//            
//            //commentContainer.getAllStyles().setBorder(border);
//            form.add(commentContainer);
//
//        }  
//
//    }
    

for(Commentaire comment:listComments){
          
            Container commentContainer=new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER));
            Stroke borderStroke = new Stroke(10, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
            RoundBorder border=RoundBorder.create().color(0x99CCCC).strokeColor(5).
            strokeOpacity(120).stroke(borderStroke).rectangle(true);

            Container authorDateContainer=new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER));
            
            Label dateLabel=new Label();
            dateLabel.setText(comment.getDateCreation().substring(0,comment.getDateCreation().length()-3));
            Container dateContainer=new Container(BoxLayout.x());
            dateContainer.add(dateLabel);
            authorDateContainer.add(BorderLayout.NORTH,dateContainer);
            
            Utilisateur auteur=serviceCommentaire.getAuteurById(comment.getIdAuteur());
            placeholder=EncodedImage.create("/testos.png");
            String url="http://localhost/ecosmartweb/uploads/annonce/"+auteur.getPhotoDeProfil();
            img=URLImage.createToStorage(placeholder, url,url, URLImage.RESIZE_SCALE);
            photoProfilViewer=new ImageViewer(img);
            Container photoContainer=new Container(BoxLayout.x());
            photoContainer.add(photoProfilViewer);
            authorDateContainer.add(BorderLayout.WEST,photoContainer);
            
            Label auteurLabel=new Label();
            auteurLabel.setText(auteur.getUsername());
            Container auteurContainer=new Container(BoxLayout.x());
            auteurContainer.add(auteurLabel);
            authorDateContainer.add(BorderLayout.SOUTH,auteurContainer);
            
   
            SpanLabel avisLabel=new SpanLabel();
            avisLabel.setText(comment.getContenu());
            Container avisContainer= new Container(BoxLayout.x());
            avisContainer.add(avisLabel);
            
            commentContainer.addComponent(BorderLayout.WEST,authorDateContainer);
//            avisContainer.setX(1);
            commentContainer.addComponent(BorderLayout.CENTER,avisContainer);

            if(comment.getIdAuteur()==1){
                Button supprimer=new Button("Supprimer");
                commentContainer.add(BorderLayout.SOUTH,supprimer);
                supprimer.addActionListener((e) -> {
                    if(Dialog.show("Valider", "Voulez vous vraiment supprimer ce commentaire ? ", "Confirmer", "Annuler"))
                    {
                        try {
                            serviceCommentaire.supprimerCommentaire(comment.getId(), request);
                            SingleBonPlanGui singleBonPlanGui= new SingleBonPlanGui(idBonPlan);
                            singleBonPlanGui.getForm().show();
                        } catch (IOException ex) {
                            System.out.println(ex);
                        }
                    }
                    
        });
            }
            
            //commentContainer.getAllStyles().setBorder(border);
           
            form.add(commentContainer);

        }  

    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public MultipartRequest getRequest() {
        return request;
    }

    public void setRequest(MultipartRequest request) {
        this.request = request;
    }


    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

//    public EncodedImage getPlaceholder() {
//        return placeholder;
//    }
//
//    public void setPlaceholder(EncodedImage placeholder) {
//        this.placeholder = placeholder;
//    }

 
}

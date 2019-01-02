/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entite.Annonce;
import Entite.Category;
import Entite.SubCategory;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import static gui.AfficherTout.idAnnonce;
import java.util.ArrayList;
import Service.AnnonceService;
import Service.CategoryService;
import Service.SubCategoryService;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

/**
 *
 * @author Choubi
 */
public class ShowAnnonce 
{
    Form f;

    public ShowAnnonce() 
    {
        f = new Form("Details", new BorderLayout());
        f.getToolbar().addCommandToRightBar("Back", null , e->{
            AfficherTout afficher = new AfficherTout();
            afficher.getF().show();
        });
        
        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        AnnonceService ser = new AnnonceService();
        Annonce annonce = new Annonce();
        annonce = ser.afficheAnnonce(idAnnonce);

        Label titre = new Label("Name: " +annonce.getTitre());
        Label price = new Label("Price: " +Float.toString(annonce.getPrix()) + "$");
        Label adress = new Label("Address: " +annonce.getAdress());
        Label etat = new Label("State: " +annonce.getEtat());
        Label type = new Label("Type: " +annonce.getType());
        Label des = new Label("Description: " +annonce.getDescription());
        Button ajouterP = new Button("Add to cart");
        Button troquer = new Button("Exchange");
        Button affichCommandes = new Button("Show Orders");
        Button btnreclaim =new Button("Reclaim");

        CategoryService catSer = new CategoryService();
        SubCategoryService soucat = new SubCategoryService();
        
        ArrayList<Category> categories = catSer.getCategory();
        ArrayList<SubCategory> subcategories =  soucat.getSubCategory();
        
        String sou = subcategories.get(annonce.getIdSousCat()-1).getNom();
        String categ = categories.get(annonce.getIdCat()-1).getNomcat();
       Button btncomment=new Button("Comment");
        container.add(titre).add(price).add(adress).
                add(etat).add(type).add(des).add(categ).
                add(sou).add(troquer).add(affichCommandes).add(btnreclaim).add(btncomment);
        
        btnreclaim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
        ReclamationForm mr = new ReclamationForm();
        mr.getForm().show();
                
            }
        });
        
        f.add(BorderLayout.CENTER, container);     
      btncomment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  AddComment h = new AddComment();
                  h.getF().show();
            }
        });
      
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    
}

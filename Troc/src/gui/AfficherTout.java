/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entite.Annonce;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;
import Service.AnnonceService;
import Service.SubCategoryService;

import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;

/**
 *
 * @author Choubi
 */
public class AfficherTout 
{
    Form f;

    public static int idAnnonce= 0;
    public AfficherTout() 
    {
        f = new Form("Details", new BorderLayout());
        
        f.getToolbar().addCommandToRightBar("Back", null , e->{
            Acceuil afficher = new Acceuil();
            afficher.getF().show();
        });
        

        AnnonceService ser = new AnnonceService();
        ArrayList<Annonce> annonces = ser.getAllAnnonce();
        
        Container cont = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container contBtn = new Container(new GridLayout(1,2));
        Button btn_view = new Button("View Mine");
        
            
        btn_view.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    MyListGui show = new MyListGui();
                    show.getF().show();
                }
            });
        for(Annonce annonce : annonces)
        {
            //System.out.println(annonce);
            Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label titre = new Label("Name: " +annonce.getTitre());
            Label price = new Label("Price: " +Float.toString(annonce.getPrix()) + "$");
            Label adress = new Label("Address: " +annonce.getAdress());
            Label etat = new Label("State: " +annonce.getEtat());
            Label type = new Label("Type: " +annonce.getType());
            Label des = new Label("Description: " +annonce.getDescription());

            Button btnViewDetails = new Button("View details");
            
            btnViewDetails.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    idAnnonce = annonce.getId();
                    System.out.println("id : " + idAnnonce);
                    ShowAnnonce show = new ShowAnnonce();
                    show.getF().show();
                }
            });
            
            container.add(titre).add(price).add(adress).add(etat).add(type).add(des);//.add(categ).add(sou);
            
            container.setScrollableY(true);
            cont.add(container);
            cont.add(btnViewDetails);
            
            cont.setPreferredW((int) (Display.getInstance().getDisplayWidth() * 0.5));
            cont.setPreferredH((int) (Display.getInstance().getDisplayWidth() * 0.6));
            cont.getStyle().setMargin(Component.BOTTOM, 10);
        }
        
        contBtn.add(new Label("")).add(btn_view);
        
        
        cont.setScrollableY(true);
        f.add(BorderLayout.NORTH, contBtn);
        //cont.getStyle().setMargin(Component.LEFT, 30);
        f.add(BorderLayout.CENTER, cont);

         
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}

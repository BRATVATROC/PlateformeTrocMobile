/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename
 * One</a> for the purpose of building native mobile applications using Java.
 */

/*  
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.components.OnOffSwitch;
import com.codename1.components.SpanLabel;
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
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.List;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.Table;
import com.codename1.ui.table.TableModel;
import com.codename1.ui.util.UIBuilder;
import Entite.Commande;
import service.CommandeService;
import java.util.ArrayList;
import app.MyApplication;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import Entite.Annonce;

/**
 *
 * @author Hussam
 */
public class ModifCommande {

    private Form form;
    private Resources theme;
    private int idCommande;

    public ModifCommande(int id) {
        AffichageCom affCom = new AffichageCom();
        

        //form statique
        form = new Form("Update Order", BoxLayout.y());
        form.getToolbar().addCommandToSideMenu("Home", null , ev ->{  
        });
        
        form.getToolbar().addCommandToSideMenu("login", null , ev ->{ 
            
        });
        
        form.getToolbar().addCommandToSideMenu("Events", null , ev ->{  
            
            
        });
        
        form.getToolbar().addCommandToSideMenu("My Orders", null , ev ->{  
            affCom.getF().show();
            
        });
        
        form.getToolbar().addCommandToSideMenu("Events & Participants", null , ev ->{  
//            
            
        });
        
        form.getToolbar().addCommandToSideMenu("MY Events", null , ev ->{  
            
            
        });
        
        form.getToolbar().addCommandToSideMenu("Annonce", null , ev ->{  
            
        });
        form.getToolbar().addCommandToSideMenu("MyReclaims", null , ev ->{ 
//                         gui.MyReclaimsFrom rec = new gui.MyReclaimsFrom();
//                         rec.getForm().show();
                 
        });
        form.getToolbar().addCommandToSideMenu("Comments", null , ev ->{ 
            
            
        });
        
        CommandeService ser = new CommandeService();
        ArrayList<Commande> commandes = new ArrayList<>();
            commandes = ser.getList2();
            Commande c = new Commande();
            for(int i = 0 ; i<commandes.size();i++){
                if (commandes.get(i).getNumCommande()==id){
                    c=commandes.get(i);
                }
            }
            Component[] elements = new Component[5];
         if (c.getIdAnnonce1() != 0) {
                        
                        
                        Label typeLabel = new Label("Type :Exchange");
                        form.add(typeLabel);
                        if (String.valueOf(c.getLivraison()).equals("0")) {
                            Label deliveryLabel = new Label("Delivery Not Asked");
                            form.add(deliveryLabel);
                        } else {
                            Label deliveryLabel = new Label("Delivery Asked");
                            form.add(deliveryLabel);
                        }
                        
                        Label idA1Label = new Label(Integer.toString(c.getIdAnnonce1()));
                        Label idA2Label = new Label(Integer.toString(c.getIdAnnonce2()));
                        
                        form.add(idA1Label);
                        form.add(idA2Label);


                    } else {
                        

                        Label typeLabel = new Label("Type :Sale");
                        form.add(typeLabel);
                        if (String.valueOf(c.getLivraison()).equals("0")) {
                            Label deliveryLabel = new Label("Delivery Not Asked");
                            form.add(deliveryLabel);
                        } else {
                            Label deliveryLabel = new Label("Delivery Asked");
                            form.add(deliveryLabel);
                        }
                        
                        if (String.valueOf(c.getPaye()).equals("0")) {
                            Label deliveryLabel = new Label("Not Payed");
                            form.add(deliveryLabel);
                        } else {
                            Label deliveryLabel = new Label("Payed");
                            form.add(deliveryLabel);
                        }
                        
                        Label idA1Label = new Label("Ammount "+Double.toString(c.getMontant())+" DT");
                        
                        form.add(idA1Label);

                    }

         OnOffSwitch delivery = new OnOffSwitch();
         form.add(new Label("Delivery"));
         form.add(delivery);
         
         Button valider = new Button("Update");
         valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                CommandeService ser = new CommandeService();
                //System.out.println(delivery.isValue());
                ser.update(id, delivery.isValue());
                AffichageCom aff = new AffichageCom();
                aff.getF().show();
            }
        });
        form.add(valider);
       
        //form.add(ajouterP);
        //form.add(troquer);
        
        
        
        
        
        
        
        
        //form.add(affichCommandes);
        
    }

    public Form getF() {
        return form;
    }

    public void setF(Form f) {
        this.form = f;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    
    
}
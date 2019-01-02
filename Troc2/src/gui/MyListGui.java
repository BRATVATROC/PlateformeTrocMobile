/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entite.Annonce;
import Entite.Category;
import Entite.SubCategory;
import app.MyApplication;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import java.util.ArrayList;
import Service.AnnonceService;
import Service.CategoryService;
import Service.SubCategoryService;

import com.codename1.components.InteractionDialog;
import com.codename1.l10n.L10NManager;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.RoundBorder;
import java.util.Date;

/**
 *
 * @author Choubi
 */
public class MyListGui 
{
    int idCat;
    int idSouCat;
    Form f;

    public MyListGui() {
        f = new Form("My Annonces");
        
        
        f.getContentPane().addPullToRefresh(() -> {
            MyListGui gui = new MyListGui();
            gui.getF().show();
        });
        
        Container C = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container contBtn = new Container(new GridLayout(1,2));
        f.getToolbar().addCommandToRightBar("Back", null , e->{
            AfficherTout afficher = new AfficherTout();
            afficher.getF().show();
        });
        
        
        Button btn_new = new Button("Add New");
        
        btn_new.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AddAnnonce add = new AddAnnonce();
                add.getF().show();
            }
        });
        
        
        AnnonceService ser = new AnnonceService();
        ArrayList<Annonce> annonces = ser.getMyList(MyApplication.current_user.getId());
        
        contBtn.add(new Label("")).add(btn_new);
        C.add(BorderLayout.north(contBtn));
        
        
        if(annonces.size() > 0)
        {
            for(Annonce annonce : annonces)
            {
                Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                
                Label titre = new Label("Name: " +annonce.getTitre());
                Label price = new Label("Price: " +Float.toString(annonce.getPrix()) + "$");
                Label adress = new Label("Address: " +annonce.getAdress());
                Label etat = new Label("State: " +annonce.getEtat());
                Label type = new Label("Type: " +annonce.getType());
                Label des = new Label("Description: " +annonce.getDescription());

                container.add(titre).add(price).add(adress).add(etat).add(type).add(des).add(new Label(""));//.add(categ).add(sou);
                container.setScrollableY(true);
                container.getStyle().setMargin(Component.BOTTOM, 60);

                Button btnDetails = new Button();
                container.setLeadComponent(btnDetails);

                btnDetails.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    InteractionDialog dlg = new InteractionDialog("Annonce Update");
                    dlg.setLayout(new BorderLayout());
                    Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    Label Name = new Label("Name");
                    TextArea nameTxt = new TextArea();
                    nameTxt.setHint("Name");
                    nameTxt.setText(annonce.getTitre());
                    container.add(Name);
                    container.add(nameTxt);

                    Label price = new Label("Price");
                    TextArea priceTxt = new TextArea();
                    priceTxt.setHint("Price $");
                    priceTxt.setText(Float.toString(annonce.getPrix()));
                    priceTxt.setConstraint(TextArea.NUMERIC);
                    container.add(price);
                    container.add(priceTxt);


                    Label adress = new Label("Adress");
                    TextArea adressTxt = new TextArea();
                    adressTxt.setHint("Adress");
                    adressTxt.setText(annonce.getAdress());
                    container.add(adress);
                    container.add(adressTxt);


                    Label des = new Label("Description");
                    TextArea desTxt = new TextArea();
                    desTxt.setHint("Description");
                    desTxt.setText(annonce.getDescription());
                    container.add(des);
                    container.add(desTxt);

                    Label etat = new Label("Status");
                    ComboBox<String> stat = new ComboBox<>();
                    stat.addItem("Disponible");
                    stat.addItem("Not disponible");

                    stat.setSelectedItem(annonce.getEtat());

                    Label type = new Label("Type");
                    ComboBox<String> typecombo = new ComboBox<>();
                    typecombo.addItem("Annonced");
                    typecombo.addItem("Not Annonced");

                    typecombo.setSelectedItem(annonce.getType());

                    Label cat = new Label("Category");
                    ComboBox<String> categ = new ComboBox<>();
                    CategoryService ser = new CategoryService();

                    ArrayList<Category> categories = ser.getCategory();

                    for(int i=0; i<categories.size(); i++)
                    {
                         categ.addItem(categories.get(i).getNomcat());
                    }
                    Label subCat = new Label("Subcategory");
                    ComboBox<String> subcateg = new ComboBox<>();

                    SubCategoryService serSub = new SubCategoryService();
                    ArrayList<SubCategory> subCategories = serSub.getSubCategory();


                        categ.addSelectionListener( new SelectionListener() {
                        @Override
                        public void selectionChanged(int oldSelected, int newSelected) {
                            //String categorieSelected = categ.getSelectedItem();
                            //System.out.println(categorieSelected);
                            for(int i=0 ; i<categories.size(); i++)
                            {
                                if(categ.getSelectedItem().equalsIgnoreCase(categories.get(i).getNomcat()))
                                {

                                    DefaultListModel dlm=(DefaultListModel)subcateg.getModel();
                                    dlm.removeAll();
                                    for(int j=0; j<subCategories.size(); j++)
                                    {

                                        if(categories.get(i).getId() == subCategories.get(j).getIdSouCat())
                                        {
                                            idCat= categories.get(i).getId();
                                            idSouCat= subCategories.get(j).getId();
                                            subcateg.addItem(subCategories.get(j).getNom());

                                        }
                                    }
                                }
                            }
                        }
                    });

                    Button btn = new Button("Update");
                    btn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                           AnnonceService service = new AnnonceService();
                           service.updateAnnonce(annonce.getId(), nameTxt.getText(), Float.parseFloat(priceTxt.getText()), typecombo.getSelectedItem(), stat.getSelectedItem(), adressTxt.getText(), desTxt.getText(), idCat, idSouCat, MyApplication.current_user.getId());

                            if(Dialog.show("Success", "You have updated successflly the annonce", "OK", null))
                            {
                                dlg.dispose();
                                MyListGui gui = new MyListGui();
                                gui.getF().show();
                            }
                        }
                    });
                    
                    Button delete = new Button("Delete");
                    delete.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                           AnnonceService service = new AnnonceService();
                            if(Dialog.show("Success", "Do you really want to delete this annonce?", "Yes", "Cancel"))
                            {
                                service.deleteAnnonce(annonce.getId());
                                dlg.dispose();
                                MyListGui gui = new MyListGui();
                                gui.getF().show();
                            }
                        }
                    });

                    container.add(etat);
                    container.add(stat);
                    container.add(type);
                    container.add(typecombo);
                    container.add(cat);
                    container.add(categ);
                    container.add(subCat);
                    container.add(subcateg);
                    container.add(btn);
                    container.add(delete);
                    container.setScrollableY(true);
                    
                    dlg.add(BorderLayout.CENTER, container);
                    Button close = new Button("Close");
                    close.addActionListener((ee) -> dlg.dispose());
                    dlg.addComponent(BorderLayout.SOUTH, close);

                    dlg.show(2, 2, 2, 2);
                }
            });



                container.add(btnDetails);
                C.add(container);
                
                C.getUnselectedStyle().setAlignment(Component.CENTER);
                C.getUnselectedStyle().setBorder(
                        RoundBorder.create().rectangle(true)
                );
            }
        }
        
        else
        {
            Button create = new Button("Create new");
            create.addActionListener(new ActionListener<ActionEvent>() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    AddAnnonce add = new AddAnnonce();
                    add.getF().show();
                }
            });
            
            C.add(new Label("Would You please create your owen annonce"));
            C.add(create);
        }
        
        
        f.add(C);
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    
}

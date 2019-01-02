/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entite.Category;
import Entite.SubCategory;
import app.MyApplication;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import java.util.ArrayList;
import Service.AnnonceService;
import Service.CategoryService;
import Service.SubCategoryService;

/**
 *
 * @author Choubi
 */
public class AddAnnonce 
{
    public int idCat;
    public int idSouCat;
    Form f;

    public AddAnnonce() 
    {
        f = new Form("Add new Annonce", new BorderLayout());
        f.getToolbar().addCommandToRightBar("Back", null , e->{
            MyListGui afficher = new MyListGui();
            afficher.getF().show();
        });

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        Label Name = new Label("Name");
        TextArea nameTxt = new TextArea();
        nameTxt.setHint("Name");
        container.add(Name);
        container.add(nameTxt);
        
        Label price = new Label("Price");
        TextArea priceTxt = new TextArea();
        priceTxt.setHint("Price $");
        priceTxt.setConstraint(TextArea.NUMERIC);
        container.add(price);
        container.add(priceTxt);
        
        
        Label adress = new Label("Adress");
        TextArea adressTxt = new TextArea();
        adressTxt.setHint("Adress");
        container.add(adress);
        container.add(adressTxt);
        
        
        Label des = new Label("Description");
        TextArea desTxt = new TextArea();
        desTxt.setHint("Description");
        container.add(des);
        container.add(desTxt);
        
        Label etat = new Label("Status");
        ComboBox<String> stat = new ComboBox<>();
        stat.addItem("Disponible");
        stat.addItem("Not disponible");
        
        
        Label type = new Label("Type");
        ComboBox<String> typecombo = new ComboBox<>();
        typecombo.addItem("Annonced");
        typecombo.addItem("Not Annonced");
        

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
        
//        String categorieSelected = categ.getSelectedItem();
//        System.out.println(categorieSelected);

             
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
            
        Button btn = new Button("Add");

        
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               AnnonceService service = new AnnonceService();
               service.AddAnnonce(nameTxt.getText(), Float.parseFloat(priceTxt.getText()), typecombo.getSelectedItem(), stat.getSelectedItem(), adressTxt.getText(), desTxt.getText(), idCat, idSouCat, MyApplication.current_user.getId());
                
                if(Dialog.show("Success", "You have added successflly a new annonce", "OK", null))
                {
                    
                }
                //System.out.println("id Cat" + idCat);
                //System.out.println("id Sub Cat" + idSouCat);
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
        container.setScrollableY(true);
        
        f.setScrollableY(true);
        f.add(BorderLayout.CENTER, container);
 
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entite.Reclamation;
import Service.ServiceReclamation;
import static app.MyApplication.current_user;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import java.io.IOException;

/**
 *
 * @author SaidaniA
 */
public class MyReclaimsFrom {
 Form form;
 SpanLabel lb;

    public MyReclaimsFrom()  {
          if(current_user.getId()>0){
         form = new Form("MyReclaim");
         
         lb=new SpanLabel("");
         Button btndelete;
         Button btnupdate;
         ServiceReclamation ser=new ServiceReclamation();
        Container c ;
        Container c1 = new Container(BoxLayout.y());   
        Container cb ;
        
         
         
         for(Reclamation r :ser.affichReclamation(current_user.getId()))
         {
             c = new Container(BoxLayout.y());
             cb = new Container(BoxLayout.x());
             c.add(new SpanLabel(r.getDescription()+" sent on date : "+r.getDate()+" status : "+r.getEtat()));
            
             btndelete=new Button("Delete");
             btnupdate=new Button("Update");
             cb.add(btndelete);
             cb.add(btnupdate);
             c.add(cb);
             c1.add(c);
             
             btndelete.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent evt) {
                     if( Dialog.show("Alert!", "Do you want to delete it?", "OK", "Cancel")){
                    ser.deleteReclamation(r.getId_rec());
                    Dialog.show("Success", "Successfully deleted", "close", null);
                  
                    MyReclaimsFrom f = new MyReclaimsFrom();
                    f.form.show();
                            
                 }
                 }
             });
          btnupdate.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent evt) {
                     
                     ReclaimsUpdateForm ru= new ReclaimsUpdateForm(r.getId_rec(), r.getDescription());
                     ru.getForm().show();
                 }
             });
         }
         form.add(c1);
       Image img;
     try {
         img = Image.createImage("/icon.png");
   form.add(img);     } catch (IOException ex) {
         
     }
       form.add(new Label("ici"));
    
       
        
      
         
         
         form.getToolbar().addCommandToRightBar("back",null,(ev)->{
        
        Acceuil acceuil = new Acceuil();
        acceuil.getF().show();
         });
         }else{
               gui.Login login = new gui.Login();
                login.getF().show();
          }
       }

    public Form getForm() {
        return form;
    }

    public SpanLabel getLb() {
        return lb;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author SaidaniA
 */

import Entite.Reclamation;
import Service.ServiceReclamation;
import static app.MyApplication.current_user;
import com.codename1.messaging.Message;
import com.codename1.sendgrid.SendGrid;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import static com.codename1.ui.layouts.BoxLayout.y;
public class ReclaimsUpdateForm {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    Form form;
    TextArea description;
    ComboBox against;
    Button btnsend;
    Button btncancel;
    public ReclaimsUpdateForm(int id,String c) {
        form = new Form("Reclaim");
        description =  new TextArea();
        btnsend=new Button("update");
        btncancel=new Button("cancel");
        against=new ComboBox("","User","Ad");
        
        Container cx = new Container(BoxLayout.y());
        Container cxx = new Container(BoxLayout.x());
        
        cx.add(against);
        description.setText(c);
        cx.add(description);
        
        
        cxx.add(btnsend);
        cxx.add(btncancel);
        cx.add(cxx);
        form.add(cx);
         btncancel.addActionListener((e) ->{
         ServiceReclamation srec = new ServiceReclamation();
         srec.affichReclamation(current_user.getId());
         MyReclaimsFrom rec = new MyReclaimsFrom();
         rec.getForm().show();
                 
         
         });
        btnsend.addActionListener((e) -> {
            ServiceReclamation srec = new ServiceReclamation();
            Reclamation R = new Reclamation();
            if(against.getSelectedItem()==""){
                Dialog.show("Alert!", "Select against who is this Ad", "OK", null);
                
            } 
            else if(description.getText()=="")
            {
                Dialog.show("Alert!", "Description empty", "OK", null);
            }
            else{
       
                if(Dialog.show("Alert!", "Do you want to send this reclamation : Against:"+against.getSelectedItem()+" description : "+
                        description.getText(), "OK", "Cancel"))
                {
               R.setId_rec(id);
                R.setDescription(description.getText());
                R.setType_rec((String) against.getSelectedItem());
                
                srec.updateReclamation(R);
                Dialog.show("Success","Update with success","close",null);
                SendGrid s = SendGrid.create("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");
                s.sendSync("anis.saidani@esprit.tn", "anis.saidani@esprit.tn", "Bratva Reclaims",
                        "Your reclamation has been successfully update !");
                    MyReclaimsFrom mf=new MyReclaimsFrom();
                    mf.getForm().show();
                  }}            
        });
        
        
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public void setDescription(TextField description) {
        this.description = description;
    }
    
    public Form getForm() {
        return form;
    }

    public TextArea getDescription() {
        return description;
    }
    
    
}



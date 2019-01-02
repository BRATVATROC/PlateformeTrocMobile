/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import Entite.Commentaire;
import static app.MyApplication.current_user;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import service.ServiceCommentaire;
import java.util.List;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import static gui.AfficherTout.idAnnonce;



/**
 *
 * @author Nadou
 */
public class AddComment {
    Form f;
    TextField lcomment;
    TextField newcomment;
    Container c1;
    
      Container cu;
    Container c2;
    Button btndelete;
    Button btnedit;
    SpanLabel lb;
    Button btnajout;
    Button btncancel;
    Button btnupdate;
   
    public AddComment()
    {   ConnectionRequest con = new ConnectionRequest();
        ServiceCommentaire serc = new ServiceCommentaire();
          Commentaire c = new Commentaire();
            
    
        f = new Form("Comments");
       
        f.getToolbar().addCommandToRightBar("Back", null , e->{
           
            ShowAnnonce aff = new ShowAnnonce();
            aff.getF().show();
        });
        
      for (Commentaire t : serc.getListParAnnonce(idAnnonce)) {
                   

              f.add(new Label(t.getCommentaire()+ " InputContainerLabel")) ;
               f.add(new Label("Date" + t.getDateCommentaire(), " InputContainerLabel")) ;
              
       
              
        btndelete = new Button("Delete");
        btnedit = new Button("Edit");
        if(t.getId_user()==current_user.getId()){
         f.add(btndelete);
         f.add(btnedit);
        }

       
          btndelete.addActionListener((e) -> { 
         
               if(Dialog.show("ALERT!","do you want to delete comment "+t.getCommentaire(),"ok","Cancel"))
          
            { 
                serc.supprimerCommentaire(t.getIdCommentaire());
               if ( Dialog.show("success","comment successfuly deleted","close",null))
               {
                       AddComment h = new AddComment();
                        h.getF().show();    
               }
            }} );
          cu=new Container(BoxLayout.y());
          btnedit.addActionListener((e) -> { 
         
               if(Dialog.show("ALERT!","do you want to modify comment "+t.getCommentaire(),"yes","Cancel"))
          
            {   
                lcomment.setText(t.getCommentaire());
            
                 btnupdate = new Button("update comment");
                
//                btnajout.setVisible(false);
                    f.removeComponent(c1);
                    
                    f.removeComponent(cu);
                    cu=new Container(BoxLayout.y());
                    cu.add(btnupdate);
             f.add(cu);
             
              btnupdate.addActionListener((ea) -> { 
                  if("".equals(lcomment.getText()))
            {
            Dialog.show("Alert","you didn't write a comment","ok",null);
            }
            else {
                if( Dialog.show("checking","we will update your comment  "+ lcomment.getText(),"ok","Cancel"))
          
            { t.setCommentaire(lcomment.getText());
                serc.modifierCommentaire(t);
                Dialog.show("success","modified with success","close",null);

              AddComment h = new AddComment();
                h.getF().show();
      
            }}});
           
            }} );
   
       }
                   
        
        lcomment = new TextField(null,"write your comment here");
        btnajout = new Button("publish comment");
        btncancel=new Button ("cancel");
        c1 = new Container(BoxLayout.y());
        f.add(lcomment);
        c1.add(btnajout);
        f.add(c1);
      
        
                
        btnajout.addActionListener((e) -> { 
            if(lcomment.getText()=="")
            {
            Dialog.show("Alert","you didn't right a comment","ok",null);
            }
            else {
                if( Dialog.show("checking","we will publish your comment  "+ lcomment.getText(),"ok","Cancel"))
          
            { c.setCommentaire(lcomment.getText());
              c.setId_annonce(idAnnonce);
                serc.ajoutCommentaire(c);
                Dialog.show("success","published with success","close",null);
                
              String ACCOUNT_SID = "AC79a926581c6c2f7687238cb20bbde065";
     String AUTH_TOKEN = "908dbf90c9e8cc649c68c204868484b0";

   
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
       // String tosend="whatsapp:"+current_user.getPhonenumber();
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:+21654521172"),
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                "Your comment is added with success")
            .create();

        System.out.println(message.getSid());
        AddComment h = new AddComment();
        h.getF().show();
//          Twilio.init("AC26a9248ddf28dbe5091ba22b3444a409", "1ec50b14e9455600bfe3daae92062b4c");
// com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber("+21654521172"),
//       new PhoneNumber("+13303675547"),"your comment was added").create();             
//        Twilio.init("anis.saidani@esprit.tn", "anis1234567890","AC79a926581c6c2f7687238cb20bbde065");
//        com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message.
//                creator(new PhoneNumber("+21654521172"), new PhoneNumber("+21652290283"),"Coucou").create();
        
      
            }
                    
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

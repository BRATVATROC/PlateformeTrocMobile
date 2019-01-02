/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import app.MyApplication;
import com.codename1.ui.Form;

/**
 *
 * @author Hussam
 */
public class Acceuil {
    Form f;


/**
 *
 * @author Hussam
 */


    public Acceuil() {
        f = new Form("Acceuil");
        
        f.getToolbar().addCommandToSideMenu("Home", null , ev ->{  
        });
        
        f.getToolbar().addCommandToSideMenu("login", null , ev ->{ 
            Login login = new Login();
            login.getF().show();
        });
        
        f.getToolbar().addCommandToSideMenu("Events", null , ev ->{  
            if(MyApplication.current_user.getId() > 0)
            {
                ListEvent LE = new ListEvent();
                LE.getForm().show();
            }
            else
            {
                Login login = new Login();
                login.getF().show();
            }
            
        });
        
        f.getToolbar().addCommandToSideMenu("Events & Participants", null , ev ->{  
//            if(MyApplication.current_user.getId() > 0)
//            {
                ListParticipants LE = new ListParticipants();
                LE.getForm().show();
//            }
//            else
//            {
//                Login login = new Login();
//                login.getF().show();
//            }
            
        });
        
        f.getToolbar().addCommandToSideMenu("MY Events", null , ev ->{  
            if(MyApplication.current_user.getId() > 0)
            {
                MyEvent LE = new MyEvent();
                LE.getForm().show();
            }
            else
            {
                Login login = new Login();
                login.getF().show();
            }
            
        });
        
        f.getToolbar().addCommandToSideMenu("Ads", null , ev ->{  
            if(MyApplication.current_user.getId() > 0)
            {
                AfficherTout annonce = new AfficherTout();
                annonce.getF().show(); 
            }
            else
            {
                Login login = new Login();
                login.getF().show();
            }
        });
        f.getToolbar().addCommandToSideMenu("MyReclaims", null , ev ->{ 
                         gui.MyReclaimsFrom rec = new gui.MyReclaimsFrom();
                         rec.getForm().show();
                 
        });
//        f.getToolbar().addCommandToSideMenu("Comments", null , ev ->{ 
//            System.out.println(MyApplication.current_user);
//          if(MyApplication.current_user.getId() > 0)
//            {
//                        AddComment h = new AddComment();
//                        h.getF().show();
//            }
//            else
//            {              
//                Login login = new Login();
//                login.getF().show();
//            }
//            
//        });
    }

    
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}

    
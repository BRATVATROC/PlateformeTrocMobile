/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entite.User;
import app.MyApplication;
import com.codename1.ui.Form;

/**
 *
 * @author Hussam
 */
public class Acceuil {
    Form form;

    public Acceuil() {
        form = new Form("Home");
        form.getToolbar().addCommandToSideMenu("Home", null , ev ->{  
        });
        
        
        if( MyApplication.current_user.getId() <= 0)
        {
            form.getToolbar().addCommandToSideMenu("login", null , ev ->{ 
                Login login = new Login();
                login.getF().show();
            });
        }
        else
        {
            form.getToolbar().addCommandToSideMenu("logout", null , ev ->{ 
                MyApplication.current_user = new User();
                Acceuil gui = new Acceuil();
                gui.getF().show();
            });
        }
        
        
        form.getToolbar().addCommandToSideMenu("Events", null , ev ->{  
//            if(MyApplication.current_user.getId() > 0)
//            {
                HomeEvent LE = new HomeEvent();
                LE.getForm().show();
//            }
//            else
//            {
//                Login login = new Login();
//                login.getF().show();
//            }
        });
        
        if( MyApplication.current_user.getId() <= 0)
        {
            form.getToolbar().addCommandToSideMenu("Sign Up", null , ev ->{  
            
                SignUp LE = new SignUp();
                LE.getForm().show();
            
            });
        }
        
        

//        form.getToolbar().addCommandToSideMenu("Events & Participants", null , ev ->{         
//        });
//        
//        form.getToolbar().addCommandToSideMenu("My Events", null , ev ->{  
//        });
//        
//        form.getToolbar().addCommandToSideMenu("Going-To", null , ev ->{  
//        });
//        
//        form.getToolbar().addCommandToSideMenu("Create Event", null , ev ->{  
//        });
form.getToolbar().addCommandToSideMenu("Annonce", null , ev ->{  
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
        form.getToolbar().addCommandToSideMenu("MyReclaims", null , ev ->{ 
                         gui.MyReclaimsFrom rec = new gui.MyReclaimsFrom();
                         rec.getForm().show();
                 
        });
                form.getToolbar().addCommandToSideMenu("Orders", null , ev ->{  
                                if(MyApplication.current_user.getId() > 0)
            {
                AffichageCom affCom = new AffichageCom();
            affCom.getF().show();
                
            }
            else{
                Login login = new Login();
                login.getF().show();
                                }  
            
        });
        form.getToolbar().addCommandToSideMenu("My Shipments", null , ev ->{  
            
              if(MyApplication.current_user.getId() > 0)
            {
                 ListLivraison list=new ListLivraison() ;
                  list.getF().show();
             
            }
            else
            {
                Login login = new Login();
                login.getF().show();
                            
            }
            
        });
          form.getToolbar().addCommandToSideMenu("My AlloCoursier Shipments", null , ev ->{  
            
              if(MyApplication.current_user.getId() > 0)
            {
                 ListAlloCoursier list=new ListAlloCoursier() ;
                  list.getF().show();
             
            }
            else
            {
                Login login = new Login();
                login.getF().show();
                            
            }
            
        });
        
    }

    
    public Form getF() {
        return form;
    }

    public void setF(Form f) {
        this.form = f;
    }
    
}

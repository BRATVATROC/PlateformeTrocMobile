/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entite.Event;
import Entite.User;
import app.MyApplication;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import gui.Acceuil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Hussam
 */
public class serviceUser {
    
    public void login(String username, String password)
    {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PlateformeTROC/ProjetWebTroc/web/app_dev.php/loginm");
        con.addArguments("username", username);
        con.addArgument("password", password);
        //con.setPost(false);
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(con.getResponseData());
                JSONParser j = new JSONParser();
                
                try 
                {
                    Map<String, Object> loginMap = j.parseJSON(new CharArrayReader(json.toCharArray()));
                   
                    int id = (int) Float.parseFloat(loginMap.get("id").toString());
                    if(id == 0)
                    {
                        //username incorrect
                        Dialog.show("Error", "Incorrect username", "OK", null);
                    }
                    else if(id ==-1)
                    {
                        //password incorrect
                        Dialog.show("Error", "Incorrect password", "OK", null);
                    }
                    else
                    {
                        User u = new User();
                        u.setId(id);
                        u.setMail(loginMap.get("email").toString());
                        u.setUsername(loginMap.get("username").toString());
                        MyApplication.current_user = u;
                        System.out.println(MyApplication.current_user );
                        Acceuil acceuil = new Acceuil();
                        acceuil.getF().show();
                    }
                } catch (IOException ex) {}
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public ArrayList<User> getAllUsers()
    {
        ArrayList<User> users = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PlateformeTROC/ProjetWebTroc/web/app_dev.php/getAllUsers");
        con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(con.getResponseData());
                JSONParser j = new JSONParser();
                try {
                    System.out.println("try");
                    Map<String, Object> eventMap = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) eventMap.get("root");
                    for(Map<String, Object> obj : list)
                    {
                        User e = new User();
                        e.setId((int) Float.parseFloat(obj.get("id").toString()));
                        e.setUsername(obj.get("username").toString());
                        e.setMail(obj.get("email").toString());
                        users.add(e);
                    }
                } catch (IOException ex) {} 
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return users;
    }
    
   public void signUp(User u)
    {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PlateformeTROC/ProjetWebTroc/web/app_dev.php/signup?email="+u.getMail()+"&username="+u.getUsername()+"&password="+u.getPassword());
        User us = new User();
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(con.getResponseData());
                JSONParser j = new JSONParser();
                try {
                    //System.out.println("json" + json);
                    Map<String, Object> userMap = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    
                    int id=  (int) Float.parseFloat(userMap.get("id").toString());
                    if(id == -1)
                    {
                        //email already taken
                        Dialog.show("Error","This email: "+ u.getMail()+" is already taken", "OK", null);
                    }
                    else if(id == 0)
                    {
                        //username already taken
                        Dialog.show("Error", "This username: "+u.getUsername()+" is already taken", "OK", null);
                    }
                    else
                    {
                        us.setMail(u.getMail());

                        us.setUsername(u.getUsername());
                        MyApplication.current_user = us;
                       
                         if(Dialog.show("Success", "Account created successfully", "OK", null))
                         {
                            Acceuil gui = new Acceuil();
                            gui.getF().show();
                         }
                        
                    }
                    
                } catch (IOException ex) {
                } 
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}

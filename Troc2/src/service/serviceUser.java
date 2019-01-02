/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

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
import java.util.Map;


/**
 *
 * @author Hussam
 */
public class serviceUser {
    
  public void login(String username, String password)
    {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PlateformeTroc/ProjetWebTroc/web/app_dev.php/loginm");
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
}

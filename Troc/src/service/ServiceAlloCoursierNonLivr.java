/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entite.Livraison;
import app.MyApplication;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lenovo
 */
public class ServiceAlloCoursierNonLivr {
     public ArrayList<Livraison> ListToDeliberOrdersLivraison(String json)
    {
   
        ArrayList<Livraison> listLivraison = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            for (Map<String, Object> obj : list) {
                Livraison e = new Livraison();
                
               
                
                Map<String,Object> dateDes=(Map<String,Object>)(obj.get("dateenregistrement"));
                int temp=(int)Float.parseFloat(dateDes.get("timestamp").toString());
                Date mydate=new Date(temp*1000L);
                System.out.println(mydate);
                e.setDateEnregistrement(mydate);     
                
//                Map<String,Object> datel=(Map<String,Object>)(obj.get("datelivraison"));
//                int temp2=(int)Float.parseFloat(datel.get("timestamp").toString());
//                Date mydate2=new Date(temp*1000L);
//                System.out.println(mydate);
              //  e.setDateLivraison(mydate2);
                e.setFraisLivraison((int)Float.parseFloat(obj.get("fraislivraison").toString()));
                e.setAdresseLivraison(obj.get("adresselivraison").toString());
                e.setDescription(obj.get("description").toString());
                e.setNumLivraison((int) Float.parseFloat(obj.get("idlivraison").toString()));
                System.out.println(e);
                listLivraison.add(e);
            }

        } catch (IOException ex) {
        }
        return listLivraison;

    }
     ArrayList<Livraison> listOrders = new ArrayList<>();
    
    public ArrayList<Livraison> getListOrders1(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PlateformeTROC/ProjetWebTroc/web/app_dev.php/troc/listeToDeliv/"+MyApplication.current_user.getId());  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceAlloCoursierNonLivr ser = new ServiceAlloCoursierNonLivr();
                System.out.println("test  "+con.getResponseData());
                listOrders = ser.ListToDeliberOrdersLivraison(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println("icii"+listOrders.toString());
        return listOrders;
    }
}

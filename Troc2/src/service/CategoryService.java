/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite.Category;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Choubi
 */
public class CategoryService 
{

    public CategoryService() {
    }
    
    public ArrayList<Category> getCategory()
    {
        ArrayList<Category> categories = new ArrayList<>();
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PlateformeTROC/ProjetWebTroc/web/app_dev.php/annonce/get");
        con.setPost(false);
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(con.getResponseData());
                //System.out.println("json :" + json);
                JSONParser j = new JSONParser();
                
                
                try 
                {
                    Map<String, Object> categ = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    //System.out.println("map: " + categ);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) categ.get("cat");
                    //System.out.println("list cat: " + list);
                    for(Map<String, Object> obj : list)
                    {
                        Category c = new Category();
                        c.setId((int) Float.parseFloat(obj.get("idcat").toString()));
                        c.setNomcat(obj.get("nomcat").toString());
                        
                        categories.add(c);
                    }
                    
                } catch (IOException ex) {}
                
                
                
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(con);
        
        return categories;
    }
            
    
}

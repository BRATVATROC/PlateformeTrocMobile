/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite.SubCategory;
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
public class SubCategoryService 
{

    public SubCategoryService() {
    }
    
    public ArrayList<SubCategory> getSubCategory()
    {
        ArrayList<SubCategory> subCategories = new ArrayList<>();
        
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
                    Map<String, Object> subcateg = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    
                    List<Map<String, Object>> list = (List<Map<String, Object>>) subcateg.get("sub");
                    
                    //System.out.println("list" + list);
                    
                    for(Map<String, Object> obj : list)
                    {
                        SubCategory c = new SubCategory();
                        c.setId((int) Float.parseFloat(obj.get("idsouscat").toString()));
                        c.setNom(obj.get("nomsouscat").toString());
                        
                        Map<String, Object> cat = (Map<String, Object>) obj.get("idcat");
                        c.setIdSouCat((int)Float.parseFloat(cat.get("idcat").toString()));
                        //System.out.println("idcat" + (int)Float.parseFloat(cat.get("idcat").toString()));
                        
                        subCategories.add(c);
                    }
                    
//                    for(int i =0; i <subCategories.size(); i++)
//                    {
//                        System.out.println(subCategories.get(i));
//                    }
                    
                } catch (IOException ex) {}
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(con);
        
        //Collections.sort(subCategories);
        for(SubCategory S : subCategories)
            System.out.println(S);
        return subCategories;
    }
}

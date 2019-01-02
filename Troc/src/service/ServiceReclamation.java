/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite.Reclamation;
import gui.MyReclaimsFrom;
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
 * @author SaidaniA
 */
public class ServiceReclamation  {
    public void ajoutReclamation(Reclamation R)
    {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PlateformeTroc/ProjetWebTroc/web/app_dev.php/troc/addRM?description=" +
                R.getDescription()+"&type="+R.getType_rec()+"&idA="+R.getId_Ann()
                +"&idU="+MyApplication.current_user.getId();
        con.setUrl(Url);
        con.setPost(true);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void updateReclamation(Reclamation R)
    {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PlateformeTroc/ProjetWebTroc/web/app_dev.php/troc/editRM?description=" +
                R.getDescription()+"&type="+R.getType_rec()+"&id="+R.getId_rec();
        con.setUrl(Url);
        con.setPost(true);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
        public void deleteReclamation(int id)
    {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PlateformeTroc/ProjetWebTroc/web/app_dev.php/troc/deleteRM?id="+id;
        con.setUrl(Url);
        con.setPost(true);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    
    
    
    
    
   
    public ArrayList<Reclamation> getListReclaims(String json) 
    {ArrayList<Reclamation> reclaim = new ArrayList<>();
        try {
            
            JSONParser j=new JSONParser();
            Map <String,Object> reclaims=j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String,Object>> list=(List<Map<String,Object>>)reclaims.get("root");
            for(Map<String,Object> obj:list)
            {
                Reclamation r = new Reclamation();
                Map<String,Object> dateDes=(Map<String,Object>)(obj.get("date"));
                int temp=(int)Float.parseFloat(dateDes.get("timestamp").toString());
                Date mydate=new Date(temp*1000L);
                System.out.println(mydate);
                r.setDate(mydate);
                r.setDescription(obj.get("description").toString());
                r.setEtat((obj.get("etatReclamation").toString()));
                r.setId_rec((int) Float.parseFloat(obj.get("idRec").toString()));
                //r.setId_Ann(Integer.parseInt(obj.get("idAnnonce").toString()));
                System.out.println(r.getId_rec());
                reclaim.add(r);
            }
            
        } catch (IOException ex) {
            

    }
        return reclaim;
    }
    ArrayList<Reclamation> reclaims =new ArrayList<>();
    public ArrayList<Reclamation> affichReclamation(int id)
    {        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PlateformeTroc/ProjetWebTroc/web/app_dev.php/troc/showRM?id="+id;
        con.setUrl(Url);
       

        con.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {

               ServiceReclamation ser=new ServiceReclamation();
                reclaims=ser.getListReclaims(new String(con.getResponseData()));
                

            
        }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println(reclaims);
        return reclaims;
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import Entite.Commentaire;
import static app.MyApplication.current_user;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nadou
 */
public class ServiceCommentaire {
      int temp;
    public void ajoutCommentaire(Commentaire c)
    {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PlateformeTroc/ProjetWebTroc/web/app_dev.php/troc/newc?commentaire="+c.getCommentaire()
                +"&id="+current_user.getId()+"&idA="+c.getId_annonce();
        con.setUrl(Url);
        con.setPost(true);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void modifierCommentaire(Commentaire c)
    {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PlateformeTroc/ProjetWebTroc/web/app_dev.php/troc/editc/"+
                c.getIdCommentaire()+"?commentaire="+c.getCommentaire();
        con.setUrl(Url);
        con.setPost(true);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void supprimerCommentaire(int idCommentaire)
    {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PlateformeTroc/ProjetWebTroc/web/app_dev.php/troc/deletec/"+idCommentaire;
        con.setUrl(Url);
        con.setPost(true);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    //récupéere une liste de commentaire 
    public ArrayList<Commentaire> getListCommentaire(String json) {

        ArrayList<Commentaire> listCommentaire = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> comments = j.parseJSON(new CharArrayReader(json.toCharArray()));
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) comments.get("root");

            for(Map<String, Object> obj : list) {
                Commentaire e = new Commentaire();
                float id = Float.parseFloat(obj.get("idCommentaire").toString());
                System.out.println(id);
                Map<String, Object> data2 = (Map<String, Object>) (obj.get("dateCommentaire"));
                temp = (int) Float.parseFloat(data2.get("timestamp").toString());
                Date myDate = new Date(temp * 1000L);
                e.setDateCommentaire(myDate);
                e.setIdCommentaire((int) id);
                e.setCommentaire(obj.get("commentaire").toString());
                Map<String, Object> user = (Map<String, Object>) (obj.get("idUser"));
                int idU=(int)Float.parseFloat(user.get("id").toString());
              
               e.setId_user(idU);
               
                listCommentaire.add(e);
            }

        } catch (IOException ex) {
        }
        return listCommentaire;

    }
     ArrayList<Commentaire> listComments = new ArrayList<>();
    
    public ArrayList<Commentaire> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PlateformeTroc/ProjetWebTroc/web/app_dev.php/troc/showc");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                ServiceCommentaire ser = new ServiceCommentaire();
                listComments = ser.getListCommentaire(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listComments;
    }
     public ArrayList<Commentaire> getListParAnnonce(int idA){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PlateformeTroc/ProjetWebTroc/web/app_dev.php/troc/readByAnnonceM?id="+idA);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                ServiceCommentaire ser = new ServiceCommentaire();
                listComments = ser.getListCommentaire(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listComments;
    }

}

    
          


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite.Annonce;
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
public class AnnonceService 
{

    public AnnonceService() {
    }
    
    public void AddAnnonce(String titre, float prix, String type, String etat, String adress, String description, int idCat, int idSousCat, int idUser)
    {

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PlateformeTROC/ProjetWebTroc/web/app_dev.php/annonce/add");
        con.addArgument("titre", titre);
        con.addArgument("prix", Float.toString(prix));
        con.addArgument("type", type);
        con.addArgument("etat", etat);
        con.addArgument("adresse", adress);
        con.addArgument("description", description);
        con.addArgument("user", Integer.toString(idUser));
        con.addArgument("cat", Integer.toString(idCat));
        con.addArgument("subcat", Integer.toString(idSousCat));
        //con.setPost(false);
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(con.getResponseData());
                //System.out.println("json :" + json);
                JSONParser j = new JSONParser();

            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(con);
        
    }
    
    public Annonce afficheAnnonce(int id)
    {
        Annonce a = new  Annonce();
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PlateformeTROC/ProjetWebTroc/web/app_dev.php/annonce/affiche?id="+id);
        //con.addArgument("id", );
        con.setPost(false);
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(con.getResponseData());
                JSONParser j = new JSONParser();
                
                try {
                    Map<String, Object> ann = j.parseJSON(new CharArrayReader(json.toCharArray()));
                   // System.out.println(ann);
                    a.setId((int) Float.parseFloat(ann.get("idAnnonce").toString()));
                    a.setAdress(ann.get("adresse").toString());
                    a.setDescription(ann.get("description").toString());
                    a.setEtat(ann.get("etatAnnonce").toString());
                    a.setTitre(ann.get("titreAnnonce").toString());
                    a.setType(ann.get("typeAnnonce").toString());
                    a.setPrix(Float.parseFloat(ann.get("prix").toString()));
                    
                    
                    a.setIdUser(0);
                    
                    Map<String , Object> item = (Map<String , Object>) ann.get("iditems");
                    Map<String , Object> subCat = (Map<String , Object>) item.get("idsouscat");
                    Map<String , Object> cat = (Map<String , Object>) subCat.get("idcat");
                    Map<String , Object> user = (Map<String , Object>) item.get("idUser");
                    
                    a.setIdCat((int) Float.parseFloat(cat.get("idcat").toString()));
                    a.setIdSousCat((int) Float.parseFloat(subCat.get("idsouscat").toString()));
                    a.setIdUser((int) Float.parseFloat(user.get("id").toString()));
                    
                } catch (IOException ex) {
                }
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(con);
         return a;
    }
    
    public ArrayList<Annonce> getAllAnnonce()
    {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PlateformeTROC/ProjetWebTroc/web/app_dev.php/annonce/affichetout");
        con.setPost(false);
        
        ArrayList<Annonce> annonces = new ArrayList<>();
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(con.getResponseData());
                JSONParser j = new JSONParser();
                
                try {
                    
                    Map<String, Object> an = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    
                    List<Map<String, Object>> list = (List<Map<String, Object>>) an.get("root");
                    
                    for(Map<String, Object> ann : list )
                    {
                        Annonce a = new  Annonce();
                        // System.out.println(ann);
                        a.setId((int) Float.parseFloat(ann.get("idAnnonce").toString()));
                        a.setAdress(ann.get("adresse").toString());
                        a.setDescription(ann.get("description").toString());
                        a.setEtat(ann.get("etatAnnonce").toString());
                        a.setTitre(ann.get("titreAnnonce").toString());
                        a.setType(ann.get("typeAnnonce").toString());
                        a.setPrix(Float.parseFloat(ann.get("prix").toString()));


                        a.setIdUser(0);

                        //System.out.println(a);
                        /*
                        Map<String , Object> item = (Map<String , Object>) ann.get("iditems");
                        Map<String , Object> subCat = (Map<String , Object>) item.get("idsouscat");
                        Map<String , Object> cat = (Map<String , Object>) subCat.get("idcat");
                        Map<String , Object> user = (Map<String , Object>) item.get("idUser");

                        a.setIdCat((int) Float.parseFloat(cat.get("idcat").toString()));
                        a.setIdSousCat((int) Float.parseFloat(subCat.get("idsouscat").toString()));
                        a.setIdUser((int) Float.parseFloat(user.get("id").toString()));
*/
                        annonces.add(a);
                        System.out.println(a);
                    }
                    
                } catch (IOException ex) {}
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        
        //System.out.println("annonce " + annonces);
        return annonces;
    }

    public ArrayList<Annonce> getMyList(int idUser)
    {
        ArrayList<Annonce> annonces = new ArrayList<>();
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PlateformeTROC/ProjetWebTroc/web/app_dev.php/annonce/my?user="+idUser);
        con.setPost(false);
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(con.getResponseData());
                JSONParser j = new JSONParser();
                
                try {
                    
                    Map<String, Object> an = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    
                    
                    if(an.get("root") != null)
                    {
                        List<Map<String, Object>> list = (List<Map<String, Object>>) an.get("root");
                        for(Map<String, Object> ann : list )
                        {
                            Annonce a = new  Annonce();
                            // System.out.println(ann);
                            a.setId((int) Float.parseFloat(ann.get("idAnnonce").toString()));
                            a.setAdress(ann.get("adresse").toString());
                            a.setDescription(ann.get("description").toString());
                            a.setEtat(ann.get("etatAnnonce").toString());
                            a.setTitre(ann.get("titreAnnonce").toString());
                            a.setType(ann.get("typeAnnonce").toString());
                            a.setPrix(Float.parseFloat(ann.get("prix").toString()));
                            a.setIdUser(idUser);
                            annonces.add(a);
                            System.out.println(a);
                        }
                    }
                } catch (IOException ex) {}
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        
        return annonces;
    }
    
    
    public void deleteAnnonce(int id)
    {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PlateformeTROC/ProjetWebTroc/web/app_dev.php/annonce/deletem?annonce="+id);
        con.setPost(false);
        System.out.println("before entring try "+ id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(con.getResponseData());
                //System.out.println("json :" + json);
                JSONParser j = new JSONParser();
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    
    
     public void updateAnnonce(int id, String titre, float prix, String type, String etat, String adress, String description, int idCat, int idSousCat, int idUser)
    {

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PlateformeTROC/ProjetWebTroc/web/app_dev.php/annonce/updatem");
        con.addArgument("id", Integer.toString(id));
        con.addArgument("titre", titre);
        con.addArgument("prix", Float.toString(prix));
        con.addArgument("type", type);
        con.addArgument("etat", etat);
        con.addArgument("adresse", adress);
        con.addArgument("description", description);
        con.addArgument("user", Integer.toString(idUser));
        con.addArgument("cat", Integer.toString(idCat));
        con.addArgument("subcat", Integer.toString(idSousCat));
        //con.setPost(false);
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(con.getResponseData());
                //System.out.println("json :" + json);
                JSONParser j = new JSONParser();

            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(con);
        
    }
}

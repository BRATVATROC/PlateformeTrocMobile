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
import entity.Annonce;
import java.io.IOException;
import entity.Commande;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 *
 * @author CompuTech
 */
public class CommandeService {
    
    public ArrayList<Commande> getListCommande(String json) {

        ArrayList<Commande> listCommandes = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            for (Map<String, Object> obj : list) {
                Commande e = new Commande();
                if (obj.get("idannonce1")!=null){
                    e.setIdAnnonce1(Integer.parseInt(obj.get("idannonce1").toString().substring(11, obj.get("idannonce1").toString().indexOf('.'))));
                    //System.out.println("ok");
                }
                
                if (obj.get("idannonce2")!=null){
                    e.setIdAnnonce2(Integer.parseInt(obj.get("idannonce2").toString().substring(11, obj.get("idannonce2").toString().indexOf('.'))));
                    //System.out.println("ok");
                }
                
                e.setIdClient(Integer.parseInt(obj.get("idclient").toString().substring(4, obj.get("idclient").toString().indexOf('.'))));
                e.setNumCommande(Integer.parseInt((obj.get("idcommande").toString().substring(0, obj.get("idcommande").toString().indexOf('.')))));
                e.setMontant((double) obj.get("montant"));
                if (obj.get("paye").equals("true")){
                    e.setPaye(1);
                }
                else
                {
                    e.setPaye(0);
                }
                
                if (obj.get("livraison").equals("true")){
                    e.setLivraison(1);
                }
                else
                {
                    e.setLivraison(0);
                }
                
                Map<String,Object> dateDes=(Map<String,Object>)(obj.get("datecommande"));
                int temp=(int)Float.parseFloat(dateDes.get("timestamp").toString());
                Date mydate=new Date(temp*1000L);
                //System.out.println(mydate);
                e.setDateCommande(mydate);
                //System.out.println(e);
                listCommandes.add(e);
            }

        } catch (IOException ex) {
        }
        return listCommandes;

    }
    
    ArrayList<Commande> listTasks = new ArrayList<>();
    
    public ArrayList<Commande> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PlateformeTROC/ProjetWebTroc/web/app_dev.php/commande/findall");  
        //System.out.println("qsds");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                CommandeService ser = new CommandeService();
                //System.out.println("hné");
                listTasks = ser.getListCommande(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    
    public void addToCart(int i){
        ConnectionRequest con = new ConnectionRequest();
        String url = "http://localhost/PlateformeTROC/ProjetWebTroc/web/app_dev.php/commande/addCart/";
        url +=Integer.toString(i);
        con.setUrl(url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public void removeFromCart(int i){
        ConnectionRequest con = new ConnectionRequest();
        String url = "http://localhost/PlateformeTROC/ProjetWebTroc/web/app_dev.php/commande/removeCart/";
        url +=Integer.toString(i);
        con.setUrl(url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public void remove(int i){
        ConnectionRequest con = new ConnectionRequest();
        String url = "http://localhost/PlateformeTROC/ProjetWebTroc/web/app_dev.php/commande/removeMobile/";
        url +=Integer.toString(i);
        con.setUrl(url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    
    
    public ArrayList<Annonce> getListAnnonces(String json) {

        ArrayList<Annonce> listCommandes = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            for (Map<String, Object> obj : list) {
                Annonce e = new Annonce();
                e.setId((int) Float.parseFloat(obj.get("idAnnonce").toString()));
                    e.setAdress(obj.get("adresse").toString());
                    e.setDescription(obj.get("description").toString());
                    e.setEtat(obj.get("etatAnnonce").toString());
                    e.setTitre(obj.get("titreAnnonce").toString());
                    e.setType(obj.get("typeAnnonce").toString());
                    e.setPrix(Float.parseFloat(obj.get("prix").toString()));
               
                //System.out.println(e);
                listCommandes.add(e);
            }

        } catch (IOException ex) {
        }
        return listCommandes;

    }
    
    ArrayList<Annonce> listTasks2 = new ArrayList<>();
    
    public ArrayList<Annonce> getList4(int id){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PlateformeTROC/ProjetWebTroc/web/app_dev.php/commande/getOrder/"+Integer.toString(id));  
        //System.out.println("qsds");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                CommandeService ser = new CommandeService();
                //System.out.println("hné");
                listTasks2 = ser.getListAnnonces(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks2;
    }
    
    
    public void update(int i,boolean delivery){
        ConnectionRequest con = new ConnectionRequest();
        String url = "http://localhost/PlateformeTROC/ProjetWebTroc/web/app_dev.php/commande/updateMobile";
        con.setUrl(url);
        con.addArgument("id",Integer.toString(i));
        if (delivery){
            con.addArgument("delivery", "true");
            //url +="&delivery=true";
        }
        else{
            con.addArgument("delivery", "false");
            //url +="&delivery=false";
        }
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                
            }
        });
        //System.out.println(url);
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    
    public ArrayList<Annonce> getListAnnonces2(String json) {

        ArrayList<Annonce> listCommandes = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            for (Map<String, Object> obj : list) {
                Annonce e = new Annonce();
                e.setId((int) Float.parseFloat(obj.get("idAnnonce").toString()));
                    e.setAdress(obj.get("adresse").toString());
                    e.setDescription(obj.get("description").toString());
                    e.setEtat(obj.get("etatAnnonce").toString());
                    e.setTitre(obj.get("titreAnnonce").toString());
                    e.setType(obj.get("typeAnnonce").toString());
                    e.setPrix(Float.parseFloat(obj.get("prix").toString()));
               
                //System.out.println(e);
                listCommandes.add(e);
            }

        } catch (IOException ex) {
        }
        return listCommandes;

    }
    
    ArrayList<Annonce> listTasks3 = new ArrayList<>();
    
    public ArrayList<Annonce> getList5(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PlateformeTROC/ProjetWebTroc/web/app_dev.php/commande/getAnnonces");  
        //System.out.println("qsds");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                CommandeService ser = new CommandeService();
                //System.out.println("hné");
                listTasks3 = ser.getListAnnonces2(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks3;
    }
    
    
    public void troc(int idA1,int idA2){
        ConnectionRequest con = new ConnectionRequest();
        String url = "http://localhost/PlateformeTROC/ProjetWebTroc/web/app_dev.php/commande/validerTroc";
        con.setUrl(url);
        con.addArgument("idA1",Integer.toString(idA1));
        con.addArgument("idA2",Integer.toString(idA2));
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                
            }
        });
        //System.out.println(url);
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;


import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import Entite.Commande;
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
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
}

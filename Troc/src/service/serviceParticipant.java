/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entite.Participant;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hussam
 */
public class serviceParticipant {
    
    public void join(int idUser, int idEvent )
    {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ProjetWebTroc/web/app_dev.php/event/join?event="+idEvent +"&user="+idUser);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(con.getResponseData());
                
                JSONParser j = new JSONParser();
                try {
                    Map<String, Object> partMap = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    //System.out.println(partMap);
                    int id = (int) Float.parseFloat(partMap.get("id").toString());
                    if(id == 0)
                    {
                        //nombre de place insuffisant
                        Dialog.show("Error", "Sorry this Event is full","OK", null);
                    }
                    else if(id == -1)
                    {
                        //utilisateur deja inscrit
                        Dialog.show("Error", "You have already joined","OK", null);
                    }
                    else
                    {
                        Dialog.show("Joined!", "","OK", null);
                    }
                } catch (IOException ex) {}
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public ArrayList<Participant> getAllParticipants()
    {
        ArrayList<Participant> participants = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ProjetWebTroc/web/app_dev.php/event/listParticipantM");
        //con.setPost(false);
       /* con.addResponseCodeListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String (con.getResponseData());
                JSONParser j = new JSONParser();
               
            }
        });*/
       
       con.addResponseListener(a ->{
            String json = new String (con.getResponseData());
            JSONParser j = new JSONParser();
             try {
                    Map<String,Object> partMap = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    System.out.println(partMap);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) partMap.get("root");
                    for ( Map<String, Object> obj : list)
                    {
                        Participant p = new Participant();
                        p.setId((int) Float.parseFloat(obj.get("id").toString()));
                        Map<String,Object> eventMap = (Map<String,Object>) obj.get("idEvent");
                        p.setIdEvent((int) Float.parseFloat(eventMap.get("id").toString()));
                        p.setIdUser ((int) Float.parseFloat(obj.get("idUser" ).toString()));
                        participants.add(p);
                    }
                } catch (IOException ex) {}
       });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return participants;
    }

    public void deleteParticipant(int id) {
        System.out.println(" id supp : "+id);
        ConnectionRequest con = new ConnectionRequest();
        String url="http://localhost/ProjetWebTroc/web/app_dev.php/event/deleteParticipantM/"+id;
        con.setUrl(url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(con.getResponseData());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public int getParticipant(int idu, int ide)
    {
        int id = 0;
        ConnectionRequest con = new ConnectionRequest();
        String url="http://localhost/ProjetWebTroc/web/app_dev.php/event/getParticipant?idUser="+idu+"&idEvent="+ide;
        con.setUrl(url);
        con.setPost(false);
         Participant p = new Participant();
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(con.getResponseData());
                JSONParser j = new JSONParser();
                try {
                    Map<String, Object> partMap = j.parseJSON(new CharArrayReader(json.toCharArray()));
//                    
                    System.out.println("Map: "+partMap);
                    if (partMap.get("root")!= null)
                    {
                        List<Map<String, Object>> list = (List<Map<String, Object>>) partMap.get("root");
                        
                        for(Map<String, Object> obj : list)
                        {
                            System.out.println("id papaa " + (int)Float.parseFloat(obj.get("id").toString()));
                            p.setId((int)Float.parseFloat(obj.get("id").toString()));
                        }
                        
                    }
                } catch (IOException ex) {}
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println(p.getId());
        return p.getId();
    }
}


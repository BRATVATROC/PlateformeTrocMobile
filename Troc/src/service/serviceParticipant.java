/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entite.Event;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hussam
 */
public class serviceParticipant {
    
    public void join(int iduser, int idevent )
    {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ProjetWebTroc/web/app_dev.php/event/join?event=" + idevent +"&user="+iduser);
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
    
    
    public Map<Event, List<Participant>> getAllParticipants()
    {
        Map<Event,List<Participant>> participants = new HashMap<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ProjetWebTroc/web/app_dev.php/event/listParticipantm");
        con.setPost(false);
        con.addResponseCodeListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String (con.getResponseData());
                JSONParser j = new JSONParser();
                try {
                    Map<String,Object> partMap = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) partMap.get("root");
                    for ( Map<String, Object> obj : list)
                    {
                        Participant p = new Participant();
                        List <Participant> pl = new ArrayList<>();
                        Event e = new Event();
                        //getting Events:
                        e.setTitre(obj.get("titre").toString());
                        e.setLocation(obj.get("location").toString());
                        //getting Participants:
                        p.setId((int) Float.parseFloat(obj.get("id").toString()));
                        p.setUsername(obj.get("username").toString());
                        p.setEmail(obj.get("email").toString());
                        pl.add(p);
                        participants.put(e, pl);
                        System.out.println(e);
                    }
                } catch (IOException ex) {}
            }
        });
        return participants;
    }
//    public Map
}


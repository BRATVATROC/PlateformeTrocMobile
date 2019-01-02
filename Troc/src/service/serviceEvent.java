/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entite.Event;
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
 * @author Hussam
 */
public class serviceEvent {
    public ArrayList<Event> getAllEvents()
    {
        ArrayList<Event> events = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ProjetWebTroc/web/app_dev.php/event/listEventM");
        con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(con.getResponseData());
                JSONParser j = new JSONParser();
                try {
                    Map<String, Object> eventMap = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) eventMap.get("root");
                    for(Map<String, Object> obj : list)
                    {
                        Event e = new Event();
                        e.setId((int) Float.parseFloat(obj.get("id").toString()));
                        e.setTitre(obj.get("titre").toString());
                        e.setDescription(obj.get("description").toString());
                        e.setMax((int) (Float.parseFloat(obj.get("max").toString())));
                        e.setLocation(obj.get("location").toString());
                        e.setDate(obj.get("date").toString());
                        e.setImge(obj.get("image").toString());
                        e.setNbr((int) Float.parseFloat(obj.get("nbr").toString()));
                        //e.setCreatedBy((int) Float.parseFloat(obj.get("createdBy").toString()));
                        events.add(e);
                    }
                } catch (IOException ex) {}
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return events;
    }
    
    public ArrayList<Event> getMyEvents(int idUser)
    {
        ArrayList<Event> events = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        
        con.setUrl("http://localhost/ProjetWebTroc/web/app_dev.php/event/myEventM/"+idUser);
        con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(con.getResponseData());
                JSONParser j = new JSONParser();
                try {
                    Map<String, Object> eventMap = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) eventMap.get("root");
                    for(Map<String, Object> obj : list)
                    {
                        Event e = new Event();
                        e.setId((int) Float.parseFloat(obj.get("id").toString()));
                        e.setTitre(obj.get("titre").toString());
                        e.setDescription(obj.get("description").toString());
                        e.setMax((int) (Float.parseFloat(obj.get("max").toString())));
                        e.setLocation(obj.get("location").toString());
                        e.setDate(obj.get("date").toString());
                        e.setImge(obj.get("image").toString());
                        e.setNbr((int) Float.parseFloat(obj.get("nbr").toString()));
                        e.setCreatedBy((int) Float.parseFloat(obj.get("createdby").toString()));
                        events.add(e);
                    }
                } catch (IOException ex) {} 
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return events;
    }
    
    public ArrayList<Event> getGoing (int id)
    {
        ArrayList<Event> events = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ProjetWebTroc/web/app_dev.php/event/goingToM/"+id);
        con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(con.getResponseData());
                JSONParser j = new JSONParser();
                try {
                    Map<String, Object> eventMap = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    if (eventMap.get("root")!= null){
                        List<Map<String, Object>> list = (List<Map<String, Object>>) eventMap.get("root");
                        for(Map<String, Object> obj : list)
                        {
                            Map<String, Object> ev = (Map<String, Object>) obj.get("idEvent");
                            Event e = new Event();
                            e.setId((int) Float.parseFloat(ev.get("id").toString()));
                            e.setTitre(ev.get("titre").toString());
                            e.setDescription(ev.get("description").toString());
                            e.setMax((int) (Float.parseFloat(ev.get("max").toString())));
                            e.setLocation(ev.get("location").toString());
                            e.setDate(ev.get("date").toString());
                            e.setImge(ev.get("image").toString());
                            e.setNbr((int) Float.parseFloat(ev.get("nbr").toString()));
                            e.setCreatedBy((int) Float.parseFloat(ev.get("createdby").toString()));
                            events.add(e);
                        }
                    }
                    
                } catch (IOException ex) {}
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return events;
    }
    
    public void createEvent(Event e)
    {
        ConnectionRequest con = new ConnectionRequest();
        String url="http://localhost/ProjetWebTroc/web/app_dev.php/event/newCreate"
                +"?titre="+e.getTitre()+"&description="+e.getDescription()+"&max="+e.getMax()+
                "&nbr="+e.getNbr()+"&location="+e.getLocation()+"&date="+e.getDate()+
                "&image="+e.getImge()+"&createdby="+e.getCreatedBy();
        con.setUrl(url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(con.getResponseData());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public void deleteEvent(int id) {
        ConnectionRequest con = new ConnectionRequest();
        String url="http://localhost/ProjetWebTroc/web/app_dev.php/event/deleteEventM/"+id;
        con.setUrl(url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(con.getResponseData());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public void updateEvent(int idEvent,Event e) {
        ConnectionRequest con = new ConnectionRequest();
        String url="http://localhost/ProjetWebTroc/web/app_dev.php/event/updateEventM/"+idEvent+"?titre="+e.getTitre()+"&description="+e.getDescription()+"&max="+e.getMax()+"&location="+e.getLocation()+"&date="+e.getDate()+"&image="+e.getImge();
        con.setUrl(url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(con.getResponseData());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public void updateNbrEvent(int idEvent) 
    {
        ConnectionRequest con = new ConnectionRequest();
        String url="http://localhost/ProjetWebTroc/web/app_dev.php/event/updateEventNbr/"+idEvent;
        con.setUrl(url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(con.getResponseData());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    
    public void updateNbrEvent1(int idEvent) 
    {
        ConnectionRequest con = new ConnectionRequest();
        String url="http://localhost/ProjetWebTroc/web/app_dev.php/event/updateEventNbr1/"+idEvent;
        con.setUrl(url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(con.getResponseData());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}

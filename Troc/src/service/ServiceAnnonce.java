///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package service;
//
//import Entite.Annonce;
//import Entite.Commentaire;
//import com.codename1.io.CharArrayReader;
//import com.codename1.io.ConnectionRequest;
//import com.codename1.io.JSONParser;
//import com.codename1.io.NetworkEvent;
//import com.codename1.io.NetworkManager;
//import com.codename1.ui.events.ActionListener;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
///**
// *
// * @author Nadou
// */
//public class ServiceAnnonce { 
//        int temp;
//        Annonce a= new Annonce();
//       public Annonce afficherAnnonce(int id_annonce)
//       {  
//           ConnectionRequest con = new ConnectionRequest();
//        String Url = "http://localhost/PlateformeTroc/ProjetWebTroc/web/app_dev.php/annonce/showByannonce/"+id_annonce;
//         con.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                
//                ServiceAnnonce ser = new ServiceAnnonce();
//                a= ser.getAnnonce(new String(con.getResponseData()));
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(con);
//        return a;
//        }
//        public Annonce getAnnonce(String json) {
//
//     
//
//        try {
//            JSONParser j = new JSONParser();
//
//            Map<String, Object> annonces = j.parseJSON(new CharArrayReader(json.toCharArray()));
//           
//            List<Map<String, Object>> list = (List<Map<String, Object>>) annonces.get("root");
//
//            for(Map<String, Object> obj : list) {
//                Annonce e = new Annonce();
//                float id = Float.parseFloat(obj.get("id_annonce").toString());
//                System.out.println(id);
//                Map<String, Object> data2 = (Map<String, Object>) (obj.get("date"));
//                temp = (int) Float.parseFloat(data2.get("timestamp").toString());
//                Date myDate = new Date(temp * 1000L);
//                e.setDate(myDate);
//                e.setId_annonce((int) id);
//               
//                Map<String, Object> user = (Map<String, Object>) (obj.get("idUser"));
//                int idU=(int)Float.parseFloat(user.get("id").toString());
//              
//              return e;
//            }
//
//        } catch (IOException ex) {
//        }
//        return null;
//
//    }
//    
//}

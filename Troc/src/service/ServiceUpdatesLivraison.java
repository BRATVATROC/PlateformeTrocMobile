/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entite.Livraison;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;

/**
 *
 * @author Lenovo
 */
public class ServiceUpdatesLivraison {
     public void UpdateAdresse (Livraison ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PlateformeTROC/ProjetWebTroc/web/app_dev.php/troc/AdresseClient/" + ta.getNumLivraison() + "/" + ta.getAdresseLivraison();
        con.setUrl(Url);
        con.setPost(true);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
      public void UpdateCancel (Livraison ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PlateformeTROC/ProjetWebTroc/web/app_dev.php/troc/CancelClient/" + ta.getNumLivraison() ;
        con.setUrl(Url);
        con.setPost(true);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
      public void ajoutAlloCoursier(Livraison ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PlateformeTROC/ProjetWebTroc/web/app_dev.php/troc/serviceAllo/new" + ta.getDescription() + "/" + ta.getIdClient()+ "/" + ta.getAdresseLivraison();
        con.setUrl(Url);
        con.setPost(true);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}

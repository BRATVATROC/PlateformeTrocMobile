/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.util.Date;



/**
 *
 * @author Lenovo
 */
public class AlloCoursier {
   private int id_user ;
   private int idAlloService; 
   private String adresseClient;
   private String adresseLivraison ;
   private String description ;
   private Date dateEnregistrementService ;
   private int idCoursier ;

    public AlloCoursier(int id_user, int idAlloService, String adresseClient, String adresseLivraison, String description, Date dateEnregistrementService, int idCoursier) {
        this.id_user = id_user;
        this.idAlloService = idAlloService;
        this.adresseClient = adresseClient;
        this.adresseLivraison = adresseLivraison;
        this.description = description;
        this.dateEnregistrementService = dateEnregistrementService;
        this.idCoursier = idCoursier;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getIdAlloService() {
        return idAlloService;
    }

    public void setIdAlloService(int idAlloService) {
        this.idAlloService = idAlloService;
    }

    public String getAdresseClient() {
        return adresseClient;
    }

    public void setAdresseClient(String adresseClient) {
        this.adresseClient = adresseClient;
    }

    public String getAdresseLivraison() {
        return adresseLivraison;
    }

    public void setAdresseLivraison(String adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateEnregistrementService() {
        return dateEnregistrementService;
    }

    public void setDateEnregistrementService(Date dateEnregistrementService) {
        this.dateEnregistrementService = dateEnregistrementService;
    }

    public int getIdCoursier() {
        return idCoursier;
    }

    public void setIdCoursier(int idCoursier) {
        this.idCoursier = idCoursier;
    }

  
   
    
}

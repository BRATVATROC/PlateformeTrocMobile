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
public class Livraison {
    private int numLivraison ;
    private int numCommande ;
    private int idClient ;
    private int idCoursier ;
    private Date  dateEnregistrement ;
    private Date   dateLivraison ;
    private float fraisLivraison ;
    private int livraisonRecue ;
    private int etatLivraison ;
    private String adresseLivraison ;
    private String description ;
    public Livraison(){}

    public Livraison(int numLivraison, int numCommande, int idClient, int idCoursier, Date  dateEnregistrement,Date  dateLivraison, float fraisLivraison,int livraisonRecue ) {
        this.numLivraison = numLivraison;
       
        this.numCommande = numCommande;
        this.idClient = idClient;
        this.idCoursier = idCoursier;
        this.dateEnregistrement = dateEnregistrement;
        this.dateLivraison=dateLivraison ;
        this.fraisLivraison = fraisLivraison;
        this.livraisonRecue=livraisonRecue ;
    }

  public Livraison(int numLivraison)
  {        this.numLivraison = numLivraison;
}
  
   
   public Livraison(int numLivraison, int numCommande, int idClient, int idCoursier, Date dateEnregistrement, float fraisLivraison,int livraisonRecue) {
        this.numLivraison = numLivraison;
       
        this.numCommande = numCommande;
        this.idClient = idClient;
        this.idCoursier = idCoursier;
        this.dateEnregistrement = dateEnregistrement;
        this.fraisLivraison = fraisLivraison;
        this.livraisonRecue=livraisonRecue ;
        
    }

    public Livraison(int idClient, String description, String adresseLivraison) {
        this.idClient = idClient;
        this.adresseLivraison = adresseLivraison;
        this.description = description;
    }


    public Livraison(int numCommande, int idClient, int idCoursier, Date dateLivraison, float fraisLivraison, int livraisonRecue) {
  this.numCommande = numCommande;
        this.idClient = idClient;
        this.idCoursier = idCoursier;
        this.dateLivraison = dateLivraison;
        this.fraisLivraison = fraisLivraison;
        this.livraisonRecue=livraisonRecue ;    }

    public Livraison(long numLivraison, String adresseLivraison) {
      this.numLivraison=(int) numLivraison;
      this.adresseLivraison=adresseLivraison;
    }

    public Livraison(long numLivraison) {
      this.numLivraison=(int) numLivraison;
    } 
  
   
    public long getNumLivraison() {
        return numLivraison;
    }

    public void setNumLivraison(int numLivraison) {
        this.numLivraison = numLivraison;
    }

   

    public long getNumCommande() {
        return numCommande;
    }

    public void setNumCommande(int numCommande) {
        this.numCommande = numCommande;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getIdCoursier() {
        return idCoursier;
    }

    public void setIdCoursier(int idCoursier) {
        this.idCoursier = idCoursier;
    }

    public Date  getDateEnregistrement() {
        return dateEnregistrement;
    }

    public void setDateEnregistrement(Date  dateEnregistrement) {
        this.dateEnregistrement = dateEnregistrement;
    }

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }
    
    public float getFraisLivraison() {
        return fraisLivraison;
    }

    public void setFraisLivraison(float fraisLivraison) {
        this.fraisLivraison = fraisLivraison;
    }

    public int getLivraisonRecue() {
        return livraisonRecue;
    }

    public void setLivraisonReçue(int livraisonRecue) {
        this.livraisonRecue = livraisonRecue;
    }

    public int getEtatLivraison() {
        return etatLivraison;
    }

    public void setEtatLivraison(int etatLivraison) {
        this.etatLivraison = etatLivraison;
    }

    public String getAdresseLivraison() {
        return adresseLivraison;
    }

    public void setAdresseLivraison(String adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }

    @Override
    public String toString() {
        return "Livraison{" + "numLivraison=" + numLivraison + ", numCommande=" + numCommande
                + ", idClient=" + idClient + ", idCoursier=" + idCoursier + ", dateEnregistrement=" 
                + dateEnregistrement + ", dateLivraison=" + dateLivraison + ", fraisLivraison=" + fraisLivraison
                + ", livraisonRecue=" + livraisonRecue + ", etatLivraison=" + etatLivraison + ", adresseLivraison=" 
                + adresseLivraison + ", description=" + description + '}'+"\n";
    }

    


    



  
  
    
    

}

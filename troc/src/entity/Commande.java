/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author CompuTech
 */
public class Commande {
    protected int numCommande;
    protected Date dateCommande;
    protected int idClient;
    protected double montant;
    protected int paye;
    protected int livraison;
    protected String nom;
    protected int idAnnonce1;
    protected int idAnnonce2;

    public int getIdAnnonce1() {
        return idAnnonce1;
    }

    public void setIdAnnonce1(int idAnnonce1) {
        this.idAnnonce1 = idAnnonce1;
    }

    public int getIdAnnonce2() {
        return idAnnonce2;
    }

    public void setIdAnnonce2(int idAnnonce2) {
        this.idAnnonce2 = idAnnonce2;
    }

    public Commande(int numCommande, Date dateCommande, int idClient, double montant,int livraison) {
        this.numCommande = numCommande;
        this.dateCommande = dateCommande;
        this.idClient = idClient;
        this.montant = montant;
        this.paye=0;
        this.livraison=livraison;
    }

    public Commande() {
        this.paye=0;
    }

    public Commande(int numCommande) {
        this.numCommande = numCommande;
        this.paye=0;
    }

    
    public Commande(Date dateCommande, int idClient, double montant,int livraison) {
        this.dateCommande = dateCommande;
        this.idClient = idClient;
        this.montant = montant;
        this.paye=0;
        this.livraison = livraison;
    }

    public Commande(int numCommande, Date dateCommande, int idClient, double montant, int paye, int livraison) {
        this.numCommande = numCommande;
        this.dateCommande = dateCommande;
        this.idClient = idClient;
        this.montant = montant;
        this.paye = paye;
        this.livraison = livraison;
    }
    
    public Commande(Date dateCommande,  int idClient, double montant, int paye, int livraison) {
        this.dateCommande = dateCommande;
        this.idClient = idClient;
        this.montant = montant;
        this.paye = paye;
        this.livraison = livraison;
    }

    public int getPaye() {
        return paye;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    
    
    public void setPaye(int paye) {
        this.paye = paye;
    }

    public int getLivraison() {
        return livraison;
    }

    public void setLivraison(int livraison) {
        this.livraison = livraison;
    }


    
    
    public int getNumCommande() {
        return numCommande;
    }

    public void setNumCommande(int numCommande) {
        this.numCommande = numCommande;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }


    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    @Override
    public String toString() {
        boolean paye,livre;
        if (this.paye==0){paye = false;}else {paye = true;}
        if (this.livraison==0){livre = false;} else{livre = true;}
        if (this.idAnnonce1!=0){
         return "Commande{" + "numCommande=" + numCommande + ", dateCommande=" + dateCommande +
        ", idClient=" + idClient + ", montant=" + montant + ", payé=" + paye +
        ", livré=" + livre + " IdAnnonce1="+idAnnonce1+ " IdAnnonce2="+idAnnonce2 +'}' ;   
        }
        return "Commande{" + "numCommande=" + numCommande + ", dateCommande=" + dateCommande +
        ", idClient=" + idClient + ", montant=" + montant + ", payé=" + paye +
        ", livré=" + livre + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj !=null)&&(obj instanceof Commande)){
            Commande c = (Commande) obj;
            if (c.getNumCommande()==this.numCommande){
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }
    
    
}

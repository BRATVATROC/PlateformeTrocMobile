/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author Choubi
 */
public class Item 
{
    private int id;
    private String nom;
    private String img;
    private int idSubCat;
    private int idUser;

    public Item() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getIdSubCat() {
        return idSubCat;
    }

    public void setIdSubCat(int idSubCat) {
        this.idSubCat = idSubCat;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", nom=" + nom + ", img=" + img + ", idSubCat=" + idSubCat + ", idUser=" + idUser + '}';
    }
    
    
    
}

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
public class SubCategory implements Comparable<SubCategory>
{
    private int id;
    private String nom;
    private int idSouCat;

    public SubCategory() {
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

    public int getIdSouCat() {
        return idSouCat;
    }

    public void setIdSouCat(int idSouCat) {
        this.idSouCat = idSouCat;
    }

    @Override
    public String toString() {
        return "SubCategory{" + "id=" + id + ", nom=" + nom + ", idSouCat=" + idSouCat + '}';
    }

    @Override
    public int compareTo(SubCategory o) {
        return this.id - o.getId();
    }

   
    
    
}

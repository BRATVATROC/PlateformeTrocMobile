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
public class Category 
{
    private int id;
    private String nomcat;

    public Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomcat() {
        return nomcat;
    }

    public void setNomcat(String nomcat) {
        this.nomcat = nomcat;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", nomcat=" + nomcat + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Category)
        {
            Category cat = (Category) obj;
            return cat.getNomcat().equals(this.getNomcat());
        }
        return false;
    }
    
    
    
}

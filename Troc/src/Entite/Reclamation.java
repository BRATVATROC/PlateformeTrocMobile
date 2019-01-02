/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.util.Date;










/**
 *
 * @author SaidaniA
 */
public class Reclamation {
    int id_rec;
    String type_rec;
    String description;
    String etat;
    Date date;
    int Id_Ann;
    int Id_User;//Reporter
    int id_RUser;//Reported

    public Reclamation() {
    }

    public int getId_rec() {
        return id_rec;
    }

    public void setId_rec(int id_rec) {
        this.id_rec = id_rec;
    }

    public String getType_rec() {
        return type_rec;
    }

    public void setType_rec(String type_rec) {
        this.type_rec = type_rec;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public int getId_Ann() {
        return Id_Ann;
    }

    public void setId_Ann(int Id_Ann) {
        this.Id_Ann = Id_Ann;
    }

    public int getId_User() {
        return Id_User;
    }

    public void setId_User(int Id_User) {
        this.Id_User = Id_User;
    }

    public int getId_RUser() {
        return id_RUser;
    }

    public void setId_RUser(int id_RUser) {
        this.id_RUser = id_RUser;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id_rec=" + id_rec + ", type_rec=" + type_rec + ", description=" + description + ", etat=" + etat + ", date=" + date + ", Id_Ann=" + Id_Ann + ", Id_User=" + Id_User + ", id_RUser=" + id_RUser + '}';
    }
    
}

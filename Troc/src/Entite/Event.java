/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.util.Date;

/**
 *
 * @author Hussam
 */
public class Event {
    
    private int id;
    private String titre;
    private String description;
    private int max;
    private String location;
    private String date;
    private String imge;
    private int createdBy;
    private int nbr;

    public Event(int id, String titre, String description, int max, String location, String date, String imge, int createdBy, int nbr) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.max = max;
        this.location = location;
        this.date = date;
        this.imge = imge;
        this.createdBy = createdBy;
        this.nbr = nbr;
    }

    public Event() {
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImge() {
        return imge;
    }

    public void setImge(String imge) {
        this.imge = imge;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", description=" + description + ", max=" + max + ", location=" + location + ", date=" + date + ", imge=" + imge + ", createdBy=" + createdBy + ", nbr=" + nbr + '}';
    }
    
    
}

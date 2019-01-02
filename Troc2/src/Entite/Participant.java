/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author Hussam
 */
public class Participant {
    
    private int id;
    private String username;
    private String email;

    public Participant() {
    }

    public Participant(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Participant{" + "id=" + id + ", username=" + username + ", email=" + email + '}';
    }
}
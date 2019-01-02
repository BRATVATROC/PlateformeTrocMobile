/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entite.Event;
import Entite.Participant;
import Entite.User;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import service.serviceEvent;
import service.serviceParticipant;
import service.serviceUser;

/**
 *
 * @author Hussam
 */
public class ListParticipants {
    private Form form;
    private Resources theme;
    
    serviceParticipant sp = new service.serviceParticipant();
    serviceEvent se = new serviceEvent();
    serviceUser su = new serviceUser();
    ArrayList<Participant> participant ;
    ArrayList<Event> events;
    ArrayList<User> users = su.getAllUsers();
    
    public ListParticipants(){
        form = new Form("Participant", new BoxLayout(BoxLayout.Y_AXIS));
        form.getToolbar().addCommandToRightBar("Back", null, ev->{
           Acceuil a = new Acceuil();
           a.getF().show();
        });
        participant = sp.getAllParticipants();
        events = se.getAllEvents();
        users = su.getAllUsers();
        if(participant.size() > 0)
        {
            Container list = new InfiniteContainer() {
                @Override
                public Component[] fetchComponents(int index, int amount) {
                    if (index == 0) {
                        participant = sp.getAllParticipants();
                        events = se.getAllEvents();
                        users = su.getAllUsers();
                    }
                    if (index + amount > participant.size()) {
                        amount = participant.size() - index;
                    }
                    if (amount <= 0) {
                        return null;
                    }
                    Component[] elements = new Component[amount];
                    int i =0;

                    for (Event  evt : events)
                    {
                        Container element = new Container(BoxLayout.y());
                        Label EventTitle = new Label("Title: "+evt.getTitre());
                        Label separate1 = new Label("-----------------------------------------------------------------------------------");
                        Label separate2 = new Label("-----------------------------------------------------------------------------------");
                        element.add(separate1).add(EventTitle).add(separate2);
                        for (User use : users)
                        {
                            for(Participant part : participant)
                            {
                                if (part.getIdUser() == use.getId())
                                {
                                    //System.out.println("use " + use);
                                    Label name = new Label("Person: "+use.getUsername()+" : "+use.getMail());
                                    //Label lastname = new Label();
                                    
                                    element.addAll(name);
                                    break;
                                }
                            }
                        }
                        elements[i] = element;
                        i++;
                    }
                    return elements;
                }
            };
            list.setScrollableY(false);
            form.add(list);
        }
        
        form.show();
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }
    
}

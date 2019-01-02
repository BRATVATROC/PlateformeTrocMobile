/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entite.Event;
import Entite.Participant;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import service.serviceParticipant;

/**
 *
 * @author Hussam
 */
public class ListParticipants {
    private Form form;
    private Resources theme;
    
    serviceParticipant sp = new service.serviceParticipant();
    Map<Event, List<Participant>> participant = sp.getAllParticipants();
    
    public ListParticipants(){
        form = new Form("Participant", new BoxLayout(2));
        form.getToolbar().addCommandToRightBar("Back", null, ev->{
           Acceuil a = new Acceuil();
           a.getF().show();
        });
        Container list = new InfiniteContainer() {
            @Override
            public Component[] fetchComponents(int index, int amount) {
                if (index == 0) {
                    participant = sp.getAllParticipants();
                }
                if (index + amount > participant.size()) {
                    amount = participant.size() - index;
                }
                if (amount <= 0) {
                    return null;
                }
                Component[] elements = new Component[amount];
                int i =0;
                for (Event evt : participant.keySet())
                {
                    Container element = new Container(BoxLayout.y());
                    Container cn1 = new Container(BoxLayout.x());
                    Container cn2 = new Container(BoxLayout.x());
                    Label titre = new Label(evt.getTitre());
                    Label location = new Label(evt.getLocation());
                    cn1.add(titre);
                    cn2.add(location);
                    titre.setUIID("CenterLAbel"); 
                    element.addAll(cn1,cn2);
                    for ( List<Participant> prt : participant.values())
                    {
                        for (Participant p: prt)
                        {
                            Container cn11 = new Container(BoxLayout.x());
                            Container cn22 = new Container(BoxLayout.x());
                            Label username = new Label (p.getUsername());
                            Label mail = new Label (p.getEmail());
                            cn11.add(username);
                            cn22.add(mail);
                            username.setUIID("GroupElementFirst");
                            mail.setUIID("GroupElementLast");
                            element.addAll(cn11,cn22);
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
        form.show();
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }
    
}

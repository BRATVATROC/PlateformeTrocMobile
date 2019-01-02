/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entite.Event;
import app.MyApplication;
import com.codename1.charts.util.ColorUtil;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import service.serviceEvent;
import service.serviceParticipant;

/**
 *
 * @author Hussam
 */
public class MyEvent {
    private Form form;
    private Resources theme;
    
    serviceEvent ser = new serviceEvent();
    ArrayList<Event> event = ser.getAllEvents();
   // int id = MyApplication.current_user.getId();
    int id=5;
    public MyEvent()
            
    {
        form = new Form("Events List", new BoxLayout(2));
//        form.setUIID("formID");
        form.getStyle().setBgColor(0xcccccc);
        form.getStyle().setBgTransparency(255);
        form.getToolbar().addCommandToRightBar("Back", null, ev->{
           Acceuil a = new Acceuil();
           a.getF().show();
        });
//        final FontImage placeholderImage = FontImage.createMaterial(FontImage.MATERIAL_EVENT, "Label", 6);
        Container list = new InfiniteContainer() {
            @Override
            public Component[] fetchComponents(int index, int amount) {
                if (index == 0) {
                    event = ser.getMyEvents(id);
                }
                if (index + amount > event.size()) {
                    amount = event.size() - index;
                }
                if (amount <= 0) {
                    return null;
                }
                Component[] elements = new Component[amount];

                int i = 0;
                
                for (Event e: event)
                {
                    Container element = new Container(BoxLayout.y());
                    Container title = new Container(BoxLayout.x());
                    Container description = new Container(BoxLayout.x());
                    Container location = new Container(BoxLayout.x());
                    Container max = new Container(BoxLayout.x());
                    Container date = new Container(BoxLayout.x());
                    ////////////////:::::
                    Label label1 = new Label(e.getTitre());
                    Label label2 = new Label(e.getDescription());
                    Label label3 = new Label(e.getLocation());
                    Label label4 = new Label(Integer.toString(e.getMax()));
                    Label label5 = new Label(e.getDate());
                    
                    label3.setUIID("centerLabel");
                    /////////////
                    title.add(label1);
                    description.add(label2);
                    location.add(label3);
                    max.add(label4);
                    date.add(label5);
                    /////////////
                    title.getAllStyles().set3DText(true, true);
                    title.getAllStyles().setFgColor(ColorUtil.rgb(255, 125, 125));
                    title.getStyle().setBgTransparency(255);
                    
                    
                    element.addAll(title,description,location,max);
//                    System.out.println(date);
                    
                    Button btn = new Button();
                    btn.addActionListener((et) -> Log.p("Clicked"));
                    
                    btn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(com.codename1.ui.events.ActionEvent evt) {
                            if(Dialog.show(e.getTitre(), e.getDescription(), "Join", "Cancel"))
                            {
                                serviceParticipant ser = new serviceParticipant();
                                ser.join(6, e.getId());
                            }
                        }
                    });
                    Button joinBtn = new Button("join");
                    joinBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            //////// JOIN ACTION ////////
                        }
                    });    
                    element.setLeadComponent(btn);
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

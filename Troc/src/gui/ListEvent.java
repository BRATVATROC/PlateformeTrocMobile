/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entite.Event;
import app.MyApplication;
import com.codename1.charts.util.ColorUtil;
import com.codename1.components.InteractionDialog;
import com.codename1.components.ScaleImageLabel;
import com.codename1.gif.GifImage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.getResourceAsStream;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import service.serviceEvent;
import service.serviceParticipant;

/**
 *
 * @author Hussam
 */
public class ListEvent {
    
    private Form form;
    private Resources theme;
    
    serviceEvent ser = new serviceEvent();
    ArrayList<Event> event = ser.getAllEvents();
    
    public ListEvent()
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
        if(event.size() > 0)
        {
        Container list = new InfiniteContainer() {
            @Override
            public Component[] fetchComponents(int index, int amount) {
                if (index == 0) {
                    event = ser.getAllEvents();
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
                    //////////////:::::
                    Label label1 = new Label(e.getTitre());
                    Label label2 = new Label(e.getDescription());
                    Label label3 = new Label(e.getLocation());
                    Label label4 = new Label(Integer.toString(e.getMax()));
                    Label label5 = new Label(e.getDate());
                    Label label6 = new Label("------------------------------------------");
                    
                    label3.setUIID("centerLabel");
                    ///////////
                    title.add(label1);
                    description.add(label2);
                    location.add(label3);
                    max.add(label4);
                    date.add(label5);
                    /////////////
                    label1.getAllStyles().set3DText(true, true);
                    label1.getAllStyles().setFgColor(ColorUtil.rgb(255, 125, 125));
                    label1.getStyle().setBgTransparency(255);
                    element.addAll(title,description,location,max,date,label6);
                    /////////////
                    Button btn = new Button();
                    btn.addActionListener((et) -> Log.p("Clicked"));
                    
                    btn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(com.codename1.ui.events.ActionEvent evt) {
                            InteractionDialog dlg = new InteractionDialog("Actions for "+e.getTitre());
                            dlg.setLayout(new BorderLayout());
                            Container cn = new Container(BoxLayout.y());
                            Button unjoin = new Button("Disjoin");
                            Button Join = new Button("Join");
                            serviceEvent sv = new serviceEvent();
                            serviceParticipant ser = new serviceParticipant();
                            unjoin.addActionListener(x -> {
                                serviceEvent se = new serviceEvent();
                                if (Dialog.show("Confirmation", "You are about to withdraw your participation", "Okay", "Cancel")){
                                    System.out.println(MyApplication.current_user.getId()+" "+e.getId());
                                    ser.deleteParticipant(ser.getParticipant(MyApplication.current_user.getId(),e.getId()));
                                    sv.updateNbrEvent(e.getId());
                                }
                            });
                            Join.addActionListener(j ->{
                                
                                ser.join(MyApplication.current_user.getId() , e.getId());
                            });
                            cn.addAll(unjoin,Join);
                            dlg.add(BorderLayout.CENTER,cn);
                            Button close = new Button("Close");
                            close.addActionListener((ee) -> dlg.dispose());
                            dlg.addComponent(BorderLayout.SOUTH, close);
                            dlg.show(200,500,0,0);
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
    }
        else{
            try {
                form.add(CENTER, new ScaleImageLabel(GifImage.decode(getResourceAsStream("/empty.gif"), 1177720)));
            } catch (IOException ex) {
            }
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

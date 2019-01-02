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
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import service.serviceEvent;

/**
 *
 * @author Hussam
 */
public class MyEvent {
    private Form form;
    private Resources theme;
    
    serviceEvent ser = new serviceEvent();
    
    int id = MyApplication.current_user.getId();
    ArrayList<Event> event = ser.getMyEvents(id);
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
        if(event.size() > 0)
        {
            
            System.out.println("n");
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
                            InteractionDialog dlg = new InteractionDialog("Actions for "+e.getTitre());
                            dlg.setLayout(new BorderLayout());
                            Container cn = new Container(BoxLayout.y());
                            Button delete = new Button("Delete");
                            Button update = new Button("Update");
                            delete.addActionListener(x -> {
                                serviceEvent se = new serviceEvent();
                                if (Dialog.show("confirm", "Are you sure you want to delete this event", "Okay", "Cancel")){
                                    se.deleteEvent(e.getId());
                                    dlg.dispose();
                                    MyEvent tf = new MyEvent();
                                    tf.getForm().show();
                                }
                                
                            });
                            update.addActionListener((ActionEvent w) -> {
                                Form f = new Form("Update Event",BoxLayout.y());
                                f.getToolbar().addCommandToRightBar("Back", null, ev->{
                                    MyEvent evnt = new MyEvent();;
                                    evnt.getForm().show();
                                });
                                Container cont = new Container(BoxLayout.y());
                                TextField Title = new TextField();
                                TextArea Description = new TextArea();
                                TextField Location = new TextField();
                                TextField NbrMax = new TextField();
                                TextField oldDate = new TextField();
                                Picker newdate = new Picker();
                                newdate.setType(Display.PICKER_TYPE_DATE_AND_TIME);
                                /////////////////// hints ////////////::
                                Title.setHint("Title");
                                Description.setHint("What's your Event about ?");
                                Location.setHint("Location");
                                NbrMax.setHint("Maximum participants number");
                                oldDate.setDisabledStyle(getStyle());
                                //////// setting parameters in fields //////
                                oldDate.setText(e.getDate());
                                Title.setText(e.getTitre());
                                Description.setMaxSize(255);
                                Description.setText(e.getDescription());
                                Location.setText(e.getLocation());
                                NbrMax.setText(Integer.toString(e.getMax()));
                                System.out.println("date :" + e.getDate());
                                Button evtUpdate = new Button("Update");
                                evtUpdate.addActionListener(r ->{
                                    serviceEvent se = new serviceEvent();
                                    Event ev = new Event();
                                    //ev.setId(e.getId());
                                    ev.setTitre( Title.getText());
                                    ev.setDescription(Description.getText());
                                    ev.setLocation(Location.getText());
                                    ev.setDate(newdate.getText());
                                    ev.setMax(Integer.parseInt(NbrMax.getText()));
                                    ev.setImge("image.jpg");
                                    se.updateEvent(e.getId(),ev);
                                    Dialog.show("Event has been Updated", null,null,"Okay");
                                    MyEvent tf = new MyEvent();
                                    tf.getForm().show();
                                });
                                
                                cont.addAll(Title,Description,oldDate,newdate,Location,NbrMax,evtUpdate);
                                f.add(cont);
                                f.show();
                            });
                            cn.addAll(update,delete);
                            dlg.add(BorderLayout.CENTER, cn);
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
            System.out.println("empty");
            try {
                form.add(BorderLayout.center(new ScaleImageLabel(GifImage.decode(getResourceAsStream("/empty.gif"), 1177720))));
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

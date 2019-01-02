/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entite.Event;
import app.MyApplication;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import java.util.Date;
import service.serviceEvent;

/**
 *
 * @author Hussam
 */
public class createEvent {
    Form form;
    TextField Title = new TextField();
    TextField Description = new TextField();
    Picker Date = new Picker();
    TextField Location = new TextField();
    TextField NbrMax = new TextField();
    
    public createEvent(){
        form = new Form ("Create Event",BoxLayout.y());
        form.getToolbar().addCommandToRightBar("Back", null, ev->{
           Acceuil a = new Acceuil();
           a.getF().show();
        });
        Date.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        Title.setHint("Title");
        Description.setHint("What's your Event about ?");
        Location.setHint("Location");
        NbrMax.setHint("Maximum participants number");
        Button createBTN = new Button("Create the event");
        Button listEvent = new Button("Events List");
        
        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c4 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c5 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container mainC = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        c1.add(Title);
        c2.add(Description);
        c3.add(Date);
        c4.add(Location);
        c5.add(NbrMax);
        mainC.add(c1);
        mainC.add(c2);
        mainC.add(c3);
        mainC.add(c4);
        mainC.add(c5);
        mainC.add(createBTN);
        form.add(mainC);
        createBTN.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if( Title.getText().equals("")|| Description.getText().equals("")||
                    Date.getText().equals("")||Location.getText().equals("")||NbrMax.getText().equals("")){
                    Dialog.show("Unfileld Fields", "You must fill all fields to porceed", "okay",null);
                }
                else{
                    serviceEvent se = new serviceEvent();
                    Event ev =  new Event();
                    ev.setTitre( Title.getText());
                    ev.setDescription(Description.getText());
                    ev.setLocation(Location.getText());
                    ev.setDate(Date.getText());
                    ev.setMax(Integer.parseInt(NbrMax.getText()));
                    ev.setCreatedBy(MyApplication.current_user.getId());
                    ev.setNbr(0);
                    ev.setImge("image.jpg");
                    se.createEvent(ev);
                    
                    Dialog.show("Add", "Added Successfully", "Okay",null);
                    
                    Title.clear();
                    Description.clear();
                    Location.clear();
                    NbrMax.clear();
                    
                }
            }
        });
        listEvent.addActionListener((e)->{
        ListEvent a=new ListEvent();
        a.getForm().show();
        });
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }
}

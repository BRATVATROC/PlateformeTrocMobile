/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import app.MyApplication;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author SaidaniA
 */
public class HomeEvent {
    Form form;
    Container cont = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    public HomeEvent() {
        form = new Form("Home");
        
        Button EventPart = new Button("Events  & Participants");
        Button MyEvents = new Button("My events");
        Button GoingTO = new Button("Event's you're going to");
        Button CreateEvent = new Button("Create an event");
        Button ListEvents = new Button("List's Events");
        
        EventPart.addActionListener(ep ->{
//            if(MyApplication.current_user.getId() > 0)
//            {
                ListParticipants LE = new ListParticipants();
                LE.getForm().show();
//            }
//            else
//            {
//                Login login = new Login();
//                login.getF().show();
//            }
        });
        MyEvents.addActionListener(me ->{
            if(MyApplication.current_user.getId() > 0)
            {
                MyEvent LE = new MyEvent();
                LE.getForm().show();
            }
            else
            {
                Login login = new Login();
                login.getF().show();
            }
        });
        GoingTO.addActionListener(gt->{
            if(MyApplication.current_user.getId() > 0)
            {
                GoingTo LE = new GoingTo();
                LE.getForm().show(); 
            }
            else
            {
                Login login = new Login();
                login.getF().show();
            }
        });
        CreateEvent.addActionListener(ce->{
            if(MyApplication.current_user.getId() > 0)
            {
                createEvent LE = new createEvent();
                LE.getForm().show();
            }
            else
            {
                Login login = new Login();
                login.getF().show();
            }
        });
        ListEvents.addActionListener(le->{
            if(MyApplication.current_user.getId() > 0)
            {
                ListEvent LE = new ListEvent();
                LE.getForm().show();
            }
            else
            {
                Login login = new Login();
                login.getF().show();
            }
        });
        cont.add(EventPart);
        cont.add(MyEvents);
        cont.add(GoingTO);
        cont.add(CreateEvent);
        cont.add(ListEvents);
        form.add(cont);
        form.show();
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }
    
    
}

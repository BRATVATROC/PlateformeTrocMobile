/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import service.serviceUser;

/**
 *
 * @author Hussam
 */
public class Login {
    Form f;

    public Login() 
    {
        f = new Form("Login", new BorderLayout());
        Container loginConaContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        TextField username = new TextField();
        username.setHint("username");

        TextField password = new TextField();
        password.setHint("password");
        password.setConstraint(TextField.PASSWORD);

        Button login = new Button("Login");
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                serviceUser ser = new serviceUser();
                ser.login(username.getText(), password.getText());
            }
        });

        loginConaContainer.add(username).add(password).add(login);

        f.add(Component.CENTER, loginConaContainer);
        f.show();
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    
}

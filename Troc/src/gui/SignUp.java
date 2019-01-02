/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entite.User;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import service.serviceUser;

/**
 *
 * @author Hussam
 */
public class SignUp {
    Form form ;
    TextField username = new TextField();
    TextField email = new TextField();
    TextField password = new TextField();
    Button create = new Button("Sign Up");
    
    public SignUp ()
    {
        form = new Form("SignUp",BoxLayout.y());
        form.getToolbar().addCommandToRightBar("Back", null, ev->{
            Acceuil gui = new Acceuil();
            gui.getF().show(); 
        });
        Container cont = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        password.setConstraint(TextField.PASSWORD);
        username.setHint("Username");
        email.setHint("example@mail.com");
        password.setHint("Password");
        create.addActionListener(cr ->{
            User user = new User();
            serviceUser ser = new serviceUser();
            if (! username.getText().equals("")&& !email.getText().equals("")&& !password.getText().equals(""))
            {
                user.setUsername(username.getText());
                user.setMail(email.getText());
                user.setPassword(password.getText());
                ser.signUp(user);
            }
            else 
            {
                Dialog.show("EmptyFields!", "Fill all fields", "Okay",null);
            }
        });
        cont.addAll(username,email,password,create);
        form.add(cont);
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entite.Event;
import Entite.Livraison;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import static com.codename1.ui.Component.TOP;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import java.util.ArrayList;
import service.ServiceLivraisonReçue;

/**
 *
 * @author Lenovo
 */
public class ListLivraison {
                     Form t = new Form();
                     Form f = new Form(new FlowLayout(CENTER,TOP));
                     
                      
                        public ListLivraison(){
                            
                            f.getToolbar().addCommandToRightBar("Back", null, ev->{
                                        Acceuil a = new Acceuil();
                                         a.getF().show();
                             });
                             Label msg1= new Label("WeTroc Delevery Service offers you a ");
                             Label msg2= new Label(" solution to save you time and facilitate");
                              Label msg3= new Label("  the management of your shipments! ");
                              f.add(msg1).add(msg2).add(msg3);
                            Button list1= new Button(" Orders Received          " );
                            f.add(list1);
                            list1.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    LivraisonDeliv li=new LivraisonDeliv() ;
                                    li.getT().show();
                                                                  }
                            });
                                
                            Button list2= new Button("Orders not shipped    ");
                            f.add(list2);
                            list2.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    LivraisonToDeliv lis=new LivraisonToDeliv();
                                    lis.getT().show();
                                }
                            });
                             Button list3= new Button("Orders Canceled          ");
                            f.add(list3);
                            list3.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    LivraisonCanceled lis= new LivraisonCanceled() ;
                                    lis.getT().show();
                                }
                            });
                
                        }
    public Form getF() {
        return f;
    }

    public void setT(Form f) {
        this.f = f;
    }

                }
    
    


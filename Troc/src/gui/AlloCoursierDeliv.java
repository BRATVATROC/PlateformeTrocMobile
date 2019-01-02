/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entite.Livraison;
import com.codename1.charts.util.ColorUtil;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
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
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.BoxLayout;

import java.util.ArrayList;
import service.ServiceAlloCoursierReçue;
import service.ServiceLivraisonNonLivr;
import service.ServiceLivraisonReçue;

/**
 *
 * @author Lenovo
 */
public class AlloCoursierDeliv {
Form t = new Form(new FlowLayout(CENTER,TOP));
Form s = new Form(new FlowLayout(CENTER,TOP));
     
  public AlloCoursierDeliv(){
     
        t.getToolbar().addCommandToRightBar("Back", null, ev->{
                                        Acceuil a = new Acceuil();
                                         a.getF().show();        
                                      });
                                      t.getToolbar().addCommandToLeftBar("My Orders", null, ev->{
                                        ListAlloCoursier a = new ListAlloCoursier();
                                         a.getF().show();
                                      });
                                    Label msg1= new Label("Your Orders will be shipped as soon as possible !");
                                    Label msg2= new Label("Here is your list of Orders Received :");
                                    t.add(msg1).add(msg2);
                                       Container nbr = new Container(BoxLayout.x());
                                        Container v1 = new Container(BoxLayout.x());
                                        Container des = new Container(BoxLayout.x());
                                        Container v2 = new Container(BoxLayout.x());  
                                        Container btn = new Container(BoxLayout.x());
                                  
                                    Button show =new Button("More Details");
                                     
                                        btn.add(show);
                                          ServiceAlloCoursierReçue list= new ServiceAlloCoursierReçue();
                                     ArrayList<Livraison> orders= list.getListOrders1();
                                  Label d=new Label();
                              
                                     for (Livraison l: orders)
                                     {
//                                       
                                      nbr = new Container(BoxLayout.y());
                                         nbr.add(new Label(l.getDescription()));
                                          show=new Button("More details");
                                             show.addActionListener(new ActionListener() {
                               @Override
                               public void actionPerformed(ActionEvent evt) {
                                      s.getToolbar().addCommandToRightBar("Back", null, ev->{
                                        Acceuil a = new Acceuil();
                                         a.getF().show();        
                                      });
                                      s.getToolbar().addCommandToLeftBar("My List", null, ev->{
                                        AlloCoursierDeliv a = new AlloCoursierDeliv();
                                         a.getT().show();
                                      });                                         

                                     Container frais = new Container(BoxLayout.x(),"Shipping cost");
                                       Container l1 = new Container(BoxLayout.x());
                                      Container descrip = new Container(BoxLayout.x(),"Order");
                                       Container l0 = new Container(BoxLayout.x());
                                     Container dateEn = new Container(BoxLayout.x(),"Registration Date");
                                      Container l2 = new Container(BoxLayout.x());
                                     Container dateLiv = new Container(BoxLayout.x(),"Delivery Date");
                                      Container l4 = new Container(BoxLayout.x());
                                     Container adresse = new Container(BoxLayout.x(),"Client Adress");
                                      Container l3 = new Container(BoxLayout.x());
                                     
                                    
                                    
                                     Label a = new Label("Shipping cost :");
                                     Label z = new Label("Registration Date :");
                                     Label r = new Label("Delivery Date :");
                                     Label e = new Label("Your Adress :");
                                     Label t = new Label("Your Order :");
                                     
                                     Label label1 = new Label();
                                     Label label4 = new Label();
                                     Label label2 = new Label();
                                     Label label3 = new Label();
                                     Label label0=new Label();
                                      label1.setText(Integer.toString((int) l.getFraisLivraison()));
                                      label4.setText(l.getAdresseLivraison());
                                      label2.setText(l.getDateEnregistrement().toString());
                                      label3.setText(l.getDateLivraison().toString());
                                      label0.setText(l.getDescription());
                                     
                                     descrip.add(t);
                                     frais.add(a);
                                     dateEn.add(z);
                                     dateLiv.add(e);
                                     adresse.add(r);
                                     l0.add(label0);
                                     l1.add(label1);
                                     l2.add(label2);
                                     l3.add(label3);
                                     l4.add(label4);
                                      Container vide = new Container(BoxLayout.y());
                                      Label v=new Label("                  ");
                                      vide.add(v);
                                     s.add(descrip).add(l0).add(frais).add(l1).add(vide).add(adresse).add(l3).add(dateEn).add(l2).add(dateLiv).add(l4);
                                     s.show();
                                     

                               }
                           });
                                          nbr.add(show);
                                          
                                       t.add(nbr);
                                      
                                       
                                       
                                       
                                     }
                                    
                                     t.show();
}                         
                                   
public Form getT() {
        return t;
    }                   
 } 
    
    
                                               


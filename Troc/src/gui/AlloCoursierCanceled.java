/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entite.Livraison;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import static com.codename1.ui.Component.TOP;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import java.util.ArrayList;
import service.ServiceAlloCoursierCanceled;
import service.ServiceLivraisonCanceled;
import service.ServiceLivraisonNonLivr;
import service.ServiceLivraisonReçue;

/**
 *
 * @author Lenovo
 */
public class AlloCoursierCanceled {
    Form t = new Form(new FlowLayout(CENTER,TOP));
    Form s = new Form(new FlowLayout(CENTER,TOP));

    public AlloCoursierCanceled() {
         t.getToolbar().addCommandToRightBar("Back", null, ev->{
                                        Acceuil a = new Acceuil();
                                         a.getF().show();
                                         });
                                      t.getToolbar().addCommandToLeftBar("My Orders", null, ev->{
                                        ListAlloCoursier a = new ListAlloCoursier();
                                         a.getF().show();
                                      });
                                      Label msg1= new Label("Some how you canceled some orders .");
                                      Label msg2= new Label("Here is your list of Orders canceled :");
                                    t.add(msg1).add(msg2);
                                            Container nbr = new Container(BoxLayout.x());
                                        Container v1 = new Container(BoxLayout.y());
                                        Container des = new Container(BoxLayout.x());
                                        Container v2 = new Container(BoxLayout.y());  
                                        Container btn = new Container(BoxLayout.x());
                                        Label id = new Label("Order N° :");
                                        nbr.add(id);
                                        Label descrip = new Label("Your Order :");
                                        des.add(descrip);
                                        Button show =new Button("More Details");
                                        btn.add(show);
                                 
                                     ServiceAlloCoursierCanceled list= new ServiceAlloCoursierCanceled();
                                     ArrayList<Livraison> orders= list.getListOrders2();
                                      for (Livraison l: orders)
                                     {
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
                                       
                                          AlloCoursierCanceled c=new AlloCoursierCanceled();
                                        c.getT().show();
                                      });
                                   Container c = new Container(BoxLayout.y());
                                   

                                     Container frais = new Container(BoxLayout.x(),"Shipping cost");
                                       Container l1 = new Container(BoxLayout.x());
                                     Container dateEn = new Container(BoxLayout.x(),"Registration Date");
                                      Container l2 = new Container(BoxLayout.x());
                                     Container descrip = new Container(BoxLayout.x(),"Order");
                                       Container l0 = new Container(BoxLayout.x());
                                     Container adresse = new Container(BoxLayout.x(),"Your Adress");
                                      Container l4 = new Container(BoxLayout.x());
                                    
                                    
                                     Label a = new Label("Shipping cost :");
                                     Label z = new Label("Registration Date :");
                                     Label e = new Label("Order :");
                                     Label r = new Label("Your Adress :");
                                     
                                     Label label1 = new Label(Integer.toString((int) l.getFraisLivraison()));
                                     Label label4 = new Label(l.getAdresseLivraison());
                                     Label label0 = new Label(l.getDescription());
                                     Label label2 = new Label(l.getDateEnregistrement().toString());
                                     frais.add(a);
                                     dateEn.add(z);
                                   //  dateLiv.add(e);
                                     adresse.add(r);
                                     descrip.add(e);
                                     l1.add(label1);
                                     l2.add(label2);
                                   //  l3.add(label3);
                                     l4.add(label4);
                                     l0.add(label0);
                                      Container vide = new Container(BoxLayout.y());
                                      Label v=new Label("        ");
                                      vide.add(v);
                                     s.add(descrip).add(l0).add(frais).add(l1).add(vide).add(adresse).add(l4).add(dateEn).add(l2);
                                     s.show();

                               }
                           });    nbr.add(show);
                                          
                                       t.add(nbr);
                                      
                                       
                                       
                                       
                                     }
                                    
                                     t.show();
    }
     public Form getT() {
        return t;
    } 
}

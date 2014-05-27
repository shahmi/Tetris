/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author Martin
 */
public class Case extends JPanel {
    public Case() {
        super();
        setBackground(Color.black);
    }
    
    public void setColor(int color){
                  switch (color){
                      case 0:  
                          this.setBackground(Color.black);
                          break;
                      case 1: //Barre
                          this.setBackground(Color.cyan);
                          break;
                      case 2: //Carre
                          this.setBackground(Color.yellow);
                          break;
                      case 3: //LDroit 
                         this.setBackground(Color.orange);
                          break;
                      case 4: //LGauche
                          this.setBackground(Color.blue);
                          break;
                      case 5: //T
                          this.setBackground(Color.pink);
                          break;
                      case 6: //ZDroit
                          this.setBackground(Color.green);
                          break;
                      case 7: //ZGauche
                          this.setBackground(Color.red);
                          break;
                      default:
                          break;
                  }
    }
}

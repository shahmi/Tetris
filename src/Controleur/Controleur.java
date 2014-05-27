/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controleur;

import Model.Grille;

/**
 *
 * @author Martin
 */
public class Controleur {
/*mettre en relation le controlleur et le modele*/
    public Grille _grille;
    
    public Controleur(Grille g){
        _grille = g;
        
    }
    
    public void miseaJour(){
        if(!_grille.pause){
            _grille.start();
        }else{
            _grille.restart();
        }
    }
    
    public void pause(){
        _grille.pause();
    }
    
    public void deplace_droite(){
        _grille.deplacerDroite();
    }
    public void deplace_gauche(){
        _grille.deplaceGauche();
    }
    public void rotationGauche(){
        _grille.rotationGauche();
    }
    public void rotationDroite(){
        _grille.rotationDroite();
    }
    public void accelerationTrue(){
        _grille.activerAcceleration();
    }
    public void accelerationFalse(){
        _grille.desactiverAcceleration();
    }
    public void descenteImediate(){
        _grille.descente();
    }
    public void arret(){
        _grille.fin();
    }
    public void sauvegarde(){
        _grille.sauvegarder_piece();
    }
   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

/**
 *
 * @author Martin
 */
public class Carre extends Piece{

    public Carre(){
        super();
        int j;
        int i;
        for(i=1;i<=2;i++){
            for(j=0;j<=1;j++){
            this.matricePiece[i][j] = 2;
            }
        }
    }
    
    @Override
    public void rotationDroite(){
        System.out.println("rotation carre nul");
    }
    @Override
    public void rotation_Gauche(){
                System.out.println("rotation carre nul");

    }
}


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
public class Barre extends Piece{
    
    public Barre(){
        super();
        
        this.matricePiece[1][0] = 1;
        this.matricePiece[1][1] = 1;
        this.matricePiece[1][2] = 1;
        this.matricePiece[1][3] = 1;
    }
    
}

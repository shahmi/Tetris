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
public class Lgauche extends Piece{
    public Lgauche(){
        super();
        
        this.matricePiece[2][0] = 4;
        this.matricePiece[2][1] = 4;
        this.matricePiece[2][2] = 4;
        this.matricePiece[1][2] = 4;
    }
}

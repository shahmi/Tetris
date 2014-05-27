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
public class Piece {
    protected int[][] matricePiece;
    
    public Piece(){
        int i;
        int j;
        matricePiece = new int[4][4];
        for(i=0;i<3;i++){
            for(j=0;j<3;j++){
            this.matricePiece[i][j] = 0;
            }
        }
    }
    
    public int getPiece(int i, int j){
        return (matricePiece[i][j]);
    }
    public void setPiece(int i, int j, int couleur){
        this.matricePiece[i][j] = couleur;
    }
    
    public void rotationDroite(){
        int[][] matriceTravail;
        matriceTravail = new int[4][4];
        
       matriceTravail[0][0] = matricePiece[0][3];
       matriceTravail[1][0] = matricePiece[0][2];
       matriceTravail[2][0] = matricePiece[0][1];
       matriceTravail[3][0] = matricePiece[0][0];
       
       matriceTravail[0][1] = matricePiece[1][3];
       matriceTravail[1][1] = matricePiece[1][2];
       matriceTravail[2][1] = matricePiece[1][1];
       matriceTravail[3][1] = matricePiece[1][0];
       
       matriceTravail[0][2] = matricePiece[2][3];
       matriceTravail[1][2] = matricePiece[2][2];
       matriceTravail[2][2] = matricePiece[2][1];
       matriceTravail[3][2] = matricePiece[2][0];
       
       matriceTravail[0][3] = matricePiece[3][3];
       matriceTravail[1][3] = matricePiece[3][2];
       matriceTravail[2][3] = matricePiece[3][1];
       matriceTravail[3][3] = matricePiece[3][0];
       
       matricePiece = matriceTravail;
    }
    
    public void rotation_Gauche(){
        
        int[][] matriceTravail;
        matriceTravail = new int[4][4];
        
       matriceTravail[0][0] = matricePiece[3][0];
       matriceTravail[1][0] = matricePiece[3][1];
       matriceTravail[2][0] = matricePiece[3][2];
       matriceTravail[3][0] = matricePiece[3][3];
       
       matriceTravail[0][1] = matricePiece[2][0];
       matriceTravail[1][1] = matricePiece[2][1];
       matriceTravail[2][1] = matricePiece[2][2];
       matriceTravail[3][1] = matricePiece[2][3];
       
       matriceTravail[0][2] = matricePiece[1][0];
       matriceTravail[1][2] = matricePiece[1][1];
       matriceTravail[2][2] = matricePiece[1][2];
       matriceTravail[3][2] = matricePiece[1][3];
       
       matriceTravail[0][3] = matricePiece[0][0];
       matriceTravail[1][3] = matricePiece[0][1];
       matriceTravail[2][3] = matricePiece[0][2];
       matriceTravail[3][3] = matricePiece[0][3];
       
       matricePiece = matriceTravail;
    }
}

/*'=
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import interface_MVC.Observable;
import interface_MVC.Observer;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;




/**
 *
 * @author Martin
 */
public class Grille extends Thread implements Observable, Runnable{
    
    
    public Grille(){
        super();
        
        nombreDeLigne = 0;
        level = 1;
        listObserver = new ArrayList<>();
        
        grille_Jeu = new int[Grillelarge + 1][Grillelong + 1];
        grille_affichage = new int[Grillelarge][Grillelong];
        
        neednewpiece = true;
        findepartie = false;
        
        pieceActuel = new Piece();
        pieceSuivante = new  Piece();
        piece_sauvegarde = new Piece();
        
        premiere = true;
        fast = false;
        debut = false;
        pause = false;
        save_piece = false;
        
        
        int i;
        int j;
        for(i=0;i<Grillelarge;i++){
            for(j=0;j<Grillelong;j++){
                this.grille_Jeu[i][j] = 0;
                this.grille_affichage[i][j] = 0;
            }
        }
        for(i=0;i<Grillelarge;i++){
            this.grille_Jeu[i][Grillelong] = 1;
        }
        for(j = 0; j<Grillelong + 1;j++){
            this.grille_Jeu[Grillelarge][j] = 1;
        }
        pospiecex = 3;
        pospiecey = 0;
    }
    
    public int getCase( int i, int j){
        return (grille_Jeu[i][j]);
    }
    
    public void setCase(int i, int j, int b){
        grille_Jeu[i][j] = b;
    }
    
    public int getAffichage( int i, int j){
        return (grille_affichage[i][j]);
    }
    
    public void setAffichage(int i, int j, int b){
        grille_affichage[i][j] = b;
    }
    
    public void CloneAfichage(){
        for(int i = 0; i < Grillelarge; i++){
            for(int j = 0; j < Grillelong; j++){
                setCase(i, j, getAffichage(i, j));
            }
        }
    }
    
    public void insert(){
        if(neednewpiece == true){
            pospiecex = 3;
            pospiecey = 0;
            supprimerLigne();
            GenererPieceAleatoire();
            if(testProchainePosition(3, 0) == true){
                for(int i = 0; i<Grillelarge; i++){
                    for(int j = 0; j<Grillelong; j++){
                        if(i >= 3 && i<7 && j >= 0 && j< 4){
                           if(pieceActuel.getPiece(i-3, j) > 0){
                           this.setAffichage(i ,j , pieceActuel.getPiece(i-3, j));
                           }
                        }

                    }
                }
                neednewpiece = false;
                notifyObserver(this);
            }
            else{
                findepartie = true;
                this.interrupt();
            }
        }
    }
    
    public void descendrePiece(){
        if(findepartie != true){
            if(neednewpiece == false && testProchainePosition(pospiecex,pospiecey + 1) == true){
                
                for(int i = 0; i <=3; i++){
                    for(int j = 0; j <= 3;j++){
                        if(pieceActuel.getPiece(i, j) > 0){
                            this.setAffichage(i + pospiecex, j + pospiecey, 0);
                        }
                    }
                }
                
                pospiecey++;

                for(int i = 0; i <=3; i++){
                    for(int j = 0; j <= 3; j++){
                        if(pieceActuel.getPiece(i, j) > 0){
                            this.setAffichage(i + pospiecex, j + pospiecey, pieceActuel.getPiece(i, j));
                        }
                    }
                }
            }
            else{
                CloneAfichage();
                neednewpiece = true;
                insert();
                System.out.println("fin du descend piece1");
            }
            notifyObserver(this);
        }
    }
    
    public boolean testProchainePosition(int x_suiv, int y_suiv){
        int x1,x2;
        for(int m = 0; m < 16; m++){ // matrice 4 * 4 donc 16 possibilité
            x1 = m % 4;  // Colonne de la pièce
            x2 = m / 4;  // Ligne de la pièce
            if(x_suiv + x1 > 11 || x_suiv + 2<= 0){
                return false;
            }else{
                if((pieceActuel.getPiece(x1, x2) > 0 && getCase(x_suiv + x1,y_suiv + x2) > 0)){
                    return false;
                }
            }
        }
        return true;/*la piece peut bouger a l'emplacement suivant*/
    }
    
    public void deplacerDroite(){
        if(debut && !pause){
            if(testProchainePosition(pospiecex + 1, pospiecey)== true){
                for(int i = 0; i <=3; i++){
                        for(int j = 0; j <= 3;j++){
                            if(pieceActuel.getPiece(i, j) > 0){
                                this.setAffichage(i + pospiecex, j + pospiecey, 0);
                            }
                        }
                    }

                    pospiecex++;

                    for(int i = 0; i <=3; i++){
                        for(int j = 0; j <= 3; j++){
                            if(pieceActuel.getPiece(i, j) > 0){
                                this.setAffichage(i + pospiecex, j + pospiecey, pieceActuel.getPiece(i, j));
                            }
                        }
                    }
                notifyObserver(this);
            }
        }
    }
    
    public void deplaceGauche(){
        if(debut && !pause){
            if(testProchainePosition(pospiecex - 1, pospiecey)== true){
                for(int i = 0; i <=3; i++){
                        for(int j = 0; j <= 3;j++){
                            if(pieceActuel.getPiece(i, j) > 0){
                                this.setAffichage(i + pospiecex, j + pospiecey, 0);
                            }
                        }
                    }

                    pospiecex--;

                    for(int i = 0; i <=3; i++){
                        for(int j = 0; j <= 3; j++){
                            if(pieceActuel.getPiece(i, j) > 0){
                                this.setAffichage(i + pospiecex, j + pospiecey, pieceActuel.getPiece(i, j));
                            }
                        }
                    }
                notifyObserver(this);
            }
        }
    }
    
    public void rotationDroite(){
        if(debut && !pause){
            Piece temp = new Piece();
            boolean rotation_possible = true;
            int x1,x2;
            for(int m = 0; m < 16; m++){
                x1 = m % 4;  // colonne de la pièce
                x2 = m / 4;  // Ligne de la pièce
                temp.setPiece(x1, x2, pieceActuel.getPiece(x1, x2));
            }
            temp.rotationDroite();
            for(int m = 0; m < 16; m++){
                x1 = m % 4;  // colonne de la pièce
                x2 = m / 4;  // Ligne de la pièce
                if(pospiecex + x1 > 10 || pospiecex + 2<= 0 || pospiecey + x2 > 19){
                        rotation_possible = false;
                }
                else{
                    if((temp.getPiece(x1, x2) > 0 && getCase(pospiecex + x1,pospiecey + x2) > 0)){
                            rotation_possible = false;
                        }
                    else{
                        if(rotation_possible != false){
                            rotation_possible = true;
                            
                        }
                    }
                }
            }
            if(rotation_possible){
                for(int i = 0; i <=3; i++){
                    for(int j = 0; j <= 3;j++){
                        if(pieceActuel.getPiece(i, j) > 0){
                            this.setAffichage(i + pospiecex, j + pospiecey, 0);
                        }
                    }
                }
                pieceActuel.rotationDroite();
                for(int i = 0; i <=3; i++){
                    for(int j = 0; j <= 3; j++){
                        if(pieceActuel.getPiece(i, j) > 0){
                            this.setAffichage(i + pospiecex, j + pospiecey, pieceActuel.getPiece(i, j));
                        }
                    }
                }
            }
            notifyObserver(this);
        }
    }
    
    public void rotationGauche(){
        if(debut && !pause){
            Piece temp = new Piece();
            boolean rotation_possible = true;
            int x1,x2;
            for(int m = 0; m < 16; m++){
                x1 = m % 4;  // colonne de la pièce
                x2 = m / 4;  // Ligne de la pièce
                temp.setPiece(x1, x2, pieceActuel.getPiece(x1, x2));
            }
            temp.rotation_Gauche();
            for(int m = 0; m < 16; m++){
                x1 = m % 4;  // colonne de la pièce
                x2 = m / 4;  // Ligne de la pièce
                if(pospiecex + x1 > 10 || pospiecex + 2<= 0 || pospiecey + x2 > 19){
                        rotation_possible = false;
                }
                else{
                    if(temp.getPiece(x1, x2) > 0 && getCase(pospiecex + x1,pospiecey + x2) > 0){
                        rotation_possible = false;
                    }
                    else{
                        if(rotation_possible != false){
                            rotation_possible = true;
                        }
                    }
                }
            }
            if(rotation_possible){
                for(int i = 0; i <=3; i++){
                    for(int j = 0; j <= 3;j++){
                        if(pieceActuel.getPiece(i, j) > 0){
                            this.setAffichage(i + pospiecex, j + pospiecey, 0);
                        }
                    }
                }
                pieceActuel.rotation_Gauche();
                for(int i = 0; i <=3; i++){
                    for(int j = 0; j <= 3; j++){
                        if(pieceActuel.getPiece(i, j) > 0){
                            this.setAffichage(i + pospiecex, j + pospiecey, pieceActuel.getPiece(i, j));
                        }
                    }
                }
            }
            notifyObserver(this);
        }
    }
    
    public void GenererPieceAleatoire(){
        if(premiere){
            premiere = false;
            Random rand = new Random();
            int nombreAleatoire1 = rand.nextInt(7) + 1;
            switch (nombreAleatoire1){
                case 1:
                    pieceSuivante = new Carre();
                    break;
                case 2:
                    pieceSuivante = new Barre();
                    break;
                case 3:
                    pieceSuivante = new Ldroite();
                    break;
                case 4:
                    pieceSuivante = new Lgauche();
                    break;
                case 5:
                    pieceSuivante = new PieceT();
                    break;
                case 6:
                    pieceSuivante = new Zgauche();
                    break;
                case 7:
                    pieceSuivante = new Zdroite();
                    break;
            }
        }
            Random rand = new Random();
            int nombreAleatoire2 = rand.nextInt(7) + 1;
            pieceActuel = pieceSuivante;
            switch (nombreAleatoire2){
                case 1:
                    pieceSuivante = new Carre();
                    break;
                case 2:
                    pieceSuivante = new Barre();
                    break;
                case 3:
                    pieceSuivante = new Ldroite();
                    break;
                case 4:
                    pieceSuivante = new Lgauche();
                    break;
                case 5:
                    pieceSuivante = new PieceT();
                    break;
                case 6:
                    pieceSuivante = new Zgauche();
                    break;
                case 7:
                    pieceSuivante = new Zdroite();
                    break;
            }
    }

    public void supprimerLigne(){
        boolean test_ligne;
        for(int i = 0; i < 20; i++){// parcours des differentes lignes
            test_ligne = true;
            for(int j = 0; j < 10; j++){
                    if(false == getCase(j,i) > 0){
                        test_ligne = false;
                }
            }
            if(test_ligne){
                nombreDeLigne++;
                for(int m = i; m > 0; m--){
                    for(int z = 0; z < 10; z++){
                        setCase(z, m, getCase(z, m-1));
                        setAffichage(z, m, getAffichage(z, m-1));
                    }
                }
                for (int y=0;y<10;y++) setCase(y, 0, 0);
            }
        }
    }
   
    public void activerAcceleration(){
        fast = true;
    }
   
    public void desactiverAcceleration(){
        fast = false;
    }
    
    public void descente(){
        if(debut && !pause){
            int posy = pospiecey;
            while(testProchainePosition(pospiecex, posy)){
                descendrePiece();
                posy++;
            }
        }
    }
    
    @Override
    public void run() {
        debut = true;
        int timer = 0;
        while ((Thread.currentThread()==this) && (! this.isInterrupted()))
	{
            if(!pause){
		descendrePiece();
		
		try {
                    if(fast){
			Thread.sleep(50);
                    }
                    else{
                        switch (nombreDeLigne){
                            case 10:
                                level = 2;
                                timer = 75;
                                break;
                            case 20:
                                level = 3;
                                timer = 150;
                                break;
                            case 30:
                                level = 4;
                                timer = 225;
                                break;
                            case 40:
                                level = 5;
                                timer = 300;
                                break;
                            case 50:
                                level = 6;
                                timer = 375;
                                break;
                            case 60:
                                level = 7;
                                timer = 450;
                                break;
                            case 70:
                                level = 8;
                                timer = 525;
                                break;
                            case 80:
                                level = 9;
                                timer = 600;
                                break;
                            case 90:
                                level = 10;
                                timer = 675;
                                break;
                            case 100:
                                level = 11;
                                timer = 750;
                                break;
                            case 110:
                                level = 11;
                                timer = 825;
                                break;
                            case 120:
                                level = 12;
                                timer = 900;
                        }
                        Thread.sleep(1000 - timer);
                    }
		}
		catch (InterruptedException e) {
			return;
		}
            }
            else{
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Grille.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
	}
    }
    
    public void pause(){
        pause = true;
        
    }
   
    public void fin(){
        findepartie = true;
        
    }
    
    public void restart(){
        pause = false;
        
    }
    
    public void sauvegarder_piece(){
        if(debut && !pause){
            Piece temp;

            if(save_piece){
                for(int i = 0; i <=3; i++){
                    for(int j = 0; j <= 3;j++){
                        if(pieceActuel.getPiece(i, j) > 0){
                            this.setAffichage(i + pospiecex, j + pospiecey, 0);
                        }
                    }
                }
                temp = pieceActuel;
                pieceActuel = piece_sauvegarde;
                piece_sauvegarde = temp;

                pospiecex = 3;
                pospiecey = 0;
            }else{
                for(int i = 0; i <=3; i++){
                    for(int j = 0; j <= 3;j++){
                        if(pieceActuel.getPiece(i, j) > 0){
                            this.setAffichage(i + pospiecex, j + pospiecey, 0);
                        }
                    }
                }
                save_piece = true;
                piece_sauvegarde = pieceActuel;
                neednewpiece = true;
            }
        }
    }
    
    @Override
    public void addObserver(Observer obs) {
            this.listObserver.add(obs);
           System.out.println("taille du list : " + this.listObserver.size());
    }

    @Override
    public void removeObserver() {
        listObserver = new ArrayList<>();   
    }

    @Override
    public void notifyObserver(Grille g) {
        for(Observer obs : listObserver){
            obs.update(g);
        }
    }
    
    final int Grillelarge = 10;
    final int Grillelong = 20;
    
    protected int[][] grille_Jeu;
    protected int[][] grille_affichage;
    private ArrayList<Observer> listObserver;
    private Piece pieceActuel;
    public Piece pieceSuivante;
    public Piece piece_sauvegarde;
    
    private int pospiecex;
    private int pospiecey;
    
    private boolean neednewpiece;
    private boolean premiere;
    public boolean findepartie;
    
    public int nombreDeLigne;
    public int level;
    
    public boolean fast;
    public boolean debut;
    public boolean pause;
    private boolean save_piece;
}

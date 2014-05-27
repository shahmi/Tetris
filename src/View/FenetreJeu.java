/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import Controleur.*;
import Model.Grille;
import interface_MVC.Observer;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Martin
 */
public class FenetreJeu extends JFrame implements Observer {
    
    public Controleur controleur;
    
    public FenetreJeu(Controleur c){
        super();
        
        matrice_Jpanel = new Case[200];
        piece_suiv = new Case[16];
        piece_sauvegarder = new Case[16];
        score = new  JTextField();
        level = new JTextField();
        fenetre = new Dimension(700, 700);
                       
        controleur = c;
        
        build();
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                super.windowClosing(arg0);
                controleur.arret();
                //System.exit(0);
            }
        });
    }
    
    public void build() {
        
 
        //Parametrage de la fenetre principale
        setTitle("TETRIS");
        setSize(fenetre);
        setResizable(true);
        
        //Menu
        JMenuBar jm = new JMenuBar();
        JMenu m = new JMenu("Jeu");
        JMenuItem m2 = new JMenuItem("Quitter");
        m2.addActionListener(new Buttonfin());
        
        //Ajout Menu à la Fenetre de Jeu
        jm.add(m);
        m.add(m2);
        setJMenuBar(jm);
        
        //Définition de BorderLine
        Border whiteline = BorderFactory.createLineBorder(Color.black,1);
        Border blackline = BorderFactory.createLineBorder(Color.black,2);
        
        //taille des panMilieu
        Dimension dim1 = new Dimension (new Dimension(360,600));
        Dimension dim2 = new Dimension (new Dimension(150,600));
        
        //JPanel
        //Panel pricipal
        JComponent panPrincipal = new JPanel(new FlowLayout()) ; 
        panPrincipal.setBackground(Color.black);
        
        //Panel de jeu 
        JComponent panMilieu = new JPanel (new GridLayout(20,10));
        panMilieu.setBackground(Color.BLACK);
        panMilieu.setBorder(BorderFactory.createLineBorder(Color.white,1));
        panMilieu.setPreferredSize(dim1);
        
        //Panel Droit pour affichage pièce suivante
        JComponent panDroit = new JPanel ();
        panDroit.setPreferredSize(dim2);
        panDroit.setBackground(Color.black);
        panDroit.setBorder(blackline);
        JComponent panelAffichagePieceSuivante = new JPanel (new GridLayout(4, 4));  
        panelAffichagePieceSuivante.setPreferredSize(new Dimension(125,125));
        
        //Panel Gauche pour affichage commande
        JComponent panGauche = new JPanel();
        panGauche.setPreferredSize(dim2);
        panGauche.setBackground(Color.black);
        
        
        //Initialisation des difféerents boutons
        boutonStart = new JButton("Start");
        boutonPause = new JButton("Pause");
        boutonFin = new JButton("Fin");
        boutonPause.setEnabled(false);
        boutonStart.setEnabled(true);
        boutonFin.setVisible(false);
        boutonStart.setPreferredSize(new Dimension(125,30));
        boutonPause.setPreferredSize(new Dimension(125,30));
        boutonFin.setPreferredSize(new Dimension(125,30));
        score.setPreferredSize(new Dimension(150,30));
        level.setPreferredSize(new Dimension(150,30));
        panMilieu.setMaximumSize(dim1);
        panDroit.setMaximumSize(dim2);
        panGauche.setMaximumSize(dim2);
        
        //Ajout piece
        for(int j = 0; j<16; j++){
            Case ptest = new Case();
            ptest.setBorder(whiteline);
            panelAffichagePieceSuivante.add(ptest);
            piece_suiv[j] = ptest;
        }
        
        for(int j = 0; j<16; j++){
            Case ptest = new Case();
            ptest.setBorder(whiteline);
            piece_sauvegarder[j] = ptest;
        }
        
        
        //ajout grille
        for(int i = 0; i<200;i++){
            Case ptest = new Case();
            ptest.setBorder(whiteline);
            panMilieu.add(ptest);
            matrice_Jpanel[i] = ptest;
        }
        
        //Ajout des différents Panel au Panel principale
        panDroit.add(panelAffichagePieceSuivante);
        panPrincipal.add(panGauche);
        panPrincipal.add(panMilieu);
        panPrincipal.add(panDroit);
        
        
        //Ajout des actions
        this.addKeyListener(new clavier()); 
        Buttonstart start = new Buttonstart();
        Buttonpause pause = new Buttonpause();
        Buttonfin fin = new Buttonfin();
        boutonStart.addActionListener(start);
        boutonPause.addActionListener(pause);
        boutonFin.addActionListener(fin);
        
        
        //Intérieur Panel Gauche
        titre = new JLabel("TETRIS");
        Font font2 = new Font("Quartz MS", Font.BOLD, 40);
        titre.setFont(font2);
        titre.setForeground(Color.white);
        touches = new JTextArea("\n\n\n\n\nTouches :\n\nHaut : Rotation droite\nBas : Rotation gauche\nDroite : Aller droite\nGauche : Aller Gauche\nEspace : Accélérer\nN : Descente directe\nS : Sauvgarder\nP : Pause\nEntrée : Démarrer");
        Font font = new Font("Verdana", Font.BOLD, 12);
        touches.setFont(font);
        touches.setForeground(Color.white);
        touches.setBackground(Color.black);
        touches.setBorder(blackline);
        panGauche.add(titre);
        panGauche.add(touches);
        
        //Intérieur Panel Droit
        boutonPause.setFocusable(false);
        boutonStart.setFocusable(false);
        score.setFocusable(false);
        score.setForeground(Color.white);
        score.setBackground(Color.DARK_GRAY);
        score.setBorder(blackline);
        level.setFocusable(false);
        level.setForeground(Color.white);
        level.setBackground(Color.DARK_GRAY);
        level.setBorder(blackline);
        panDroit.add(boutonStart);
        panDroit.add(boutonPause);
        panDroit.add(level);
        panDroit.add(score);
        panDroit.add(boutonFin);
        setContentPane(panPrincipal);
        setMinimumSize(fenetre);
        setVisible(true);
        setFocusable(true);
    }
    
   class Buttonstart implements ActionListener{ 
    @Override
    public void actionPerformed(ActionEvent e) {
       controleur.miseaJour();
       boutonPause.setEnabled(true);
       boutonStart.setEnabled(false);
    }                
  }
   class Buttonfin implements ActionListener{ 
    @Override
    public void actionPerformed(ActionEvent e) {
       controleur.arret();
       setVisible(false);
    }                
  }
   class Buttonpause implements ActionListener{ 
    @Override
    public void actionPerformed(ActionEvent e) {
       controleur.pause();
       boutonPause.setEnabled(false);
       boutonStart.setEnabled(true);
    }                
  }
   
   
   class clavier implements KeyListener{
        @Override
        public void keyPressed(KeyEvent ke) {
            int keycode = ke.getKeyCode();
           
            switch (keycode) {
                case KeyEvent.VK_LEFT:
                    controleur.deplace_gauche();
                    break;
                case KeyEvent.VK_RIGHT:
                    controleur.deplace_droite();
                    break;
                case KeyEvent.VK_UP:
                    controleur.rotationGauche();
                    break;
                case KeyEvent.VK_DOWN:
                    controleur.rotationDroite();
                    break;
                case KeyEvent.VK_SPACE:
                    controleur.accelerationTrue();
                    break;
                case KeyEvent.VK_N:
                    controleur.descenteImediate();
                    break;
                case KeyEvent.VK_P:
                    controleur.pause();
                    boutonPause.setEnabled(false);
                    boutonStart.setEnabled(true);
                    break;
                case KeyEvent.VK_ENTER:
                    controleur.miseaJour();
                    boutonPause.setEnabled(true);
                    boutonStart.setEnabled(false);
                    break;
                case KeyEvent.VK_S:
                    controleur.sauvegarde();
                    break;
                default:
                    System.out.println("touche non reconnu");
                    break;
            }
        }       

        @Override
        public void keyTyped(KeyEvent ke) {
        }

        @Override
        public void keyReleased(KeyEvent ke) {
            int keycode = ke.getKeyCode();
            
            switch (keycode) {
                case KeyEvent.VK_SPACE:
                    controleur.accelerationFalse();
                    break;
            }
        }
   }
   

    public void setfond(int i, int j, int color){
        int indice = i+(j*10);          /*indice calculer avec les coordonné x et y d'une matrice*/
        
        matrice_Jpanel[indice].setColor(color);
    }
    
    public void game_over(){
        boutonFin.setVisible(true);
        String retour;
        Object[] options = { "OK" };
        JOptionPane.showOptionDialog(null, "Fin de patire ! \n" + score.getText(), "Vous avez perdu !",JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,
        null, options, options[0]);
    }
    
    
    @Override
    public void update(Grille g) {
        if(g.findepartie != true){
            for(int i=0; i<10;i++){
                for(int j=0; j<20; j++){
                    setfond(i, j, g.getAffichage(i, j));
                    repaint();
                }
            }
            for(int i=0; i<4;i++){
                for(int j=0; j<4; j++){
                    piece_suiv[i+(j*4)].setColor(g.pieceSuivante.getPiece(i, j));
                    repaint();
                }
            }
            for(int i=0; i<4;i++){
                for(int j=0; j<4; j++){
                    piece_sauvegarder[i+(j*4)].setColor(g.piece_sauvegarde.getPiece(i, j));
                    repaint();
                }
            }
            score.setText("Nombre de lignes : " + g.nombreDeLigne);
            level.setText("Niveau : " + g.level);
        }
        else{
            game_over();
        }
    }
    
    
    public Case[] matrice_Jpanel;
    public Case[] piece_suiv;
    public Case[] piece_sauvegarder;
    public JTextField score;
    public JTextField level;
    public Dimension fenetre;
    public JButton boutonStart;
    public JButton boutonPause;
    public JButton boutonFin;
    public JTextArea touches;
    public JLabel titre;
    
}

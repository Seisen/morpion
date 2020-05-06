import MG2D.*;
import MG2D.geometrie.*;
import java.awt.Font;

class interfaceGraphique {
    public static void main(String[] args){
        int largeur =1000;
        int hauteur = 1000;
        
        

        Fenetre fenetre = new Fenetre("Morpion", largeur, hauteur);
        Souris souris= fenetre.getSouris();
        
       
        
    
        Texte texte = new Texte(Couleur.BLEU, "Choisissez la taille de la grille", new Font("Calibri", Font.TYPE1_FONT,30), new Point(500, 500));
        Texte texte2 = new Texte(Couleur.BLEU, " Choisissez le nombre de pions à aligner pour gagner", new Font("Calibri", Font.TYPE1_FONT,30), new Point(500, 250));
        Texte texte3 = new Texte(Couleur.BLEU, " MORPION", new Font("Calibri", Font.TYPE1_FONT, 70), new Point(500, 900));

        Rectangle rec = new Rectangle(Couleur.NOIR,new Point(0,350),1000,100);
        Rectangle rec1 = new Rectangle(Couleur.NOIR,new Point(0,100),1000,100);

        for (int i =0;i<10;i++){
            
            Texte texte5 = new Texte(Couleur.NOIR, String.valueOf(i+2), new Font("Calibri", Font.TYPE1_FONT, 70), new Point((i*100)+25, 390));
            Texte texte6 = new Texte(Couleur.NOIR, String.valueOf(i+2), new Font("Calibri", Font.TYPE1_FONT, 70), new Point((i*100)+25, 140));
            fenetre.ajouter(texte5);
            fenetre.ajouter(texte6);
        }
        
        
        fenetre.ajouter(texte);
        fenetre.ajouter(texte2);
        fenetre.ajouter(texte3);
        fenetre.ajouter(rec);
        fenetre.ajouter(rec1);
        fenetre.rafraichir();

        fenetre.setAffichageFPS(true);

        int taille = -1;
        int xCoupsGagnant = -1;
        while(taille<0 || xCoupsGagnant<0){
            
            Point cooSouris = new Point(souris.getPosition());
            int sourisX = cooSouris.getX();
            int sourisY = cooSouris.getY();
            int xSouris=sourisX-sourisX%100-20;
            Rectangle rec10 = new Rectangle(Couleur.GRIS,new Point(xSouris,350),100,100);
            Rectangle rec20 = new Rectangle(Couleur.GRIS,new Point(xSouris,100),100,100);
            

            if(sourisY<450 && sourisY>350 && souris.getClicGauche() && taille<0){
                fenetre.ajouter(rec10);
                fenetre.rafraichir();
                taille=(sourisX/100)+1;
            }
            if(sourisY<200 && sourisY>100 && souris.getClicGauche() && xCoupsGagnant<0){
                fenetre.ajouter(rec20);
                fenetre.rafraichir();
                xCoupsGagnant=(sourisX/100)+2;
            }
        }
        taille++;//petite correction
       

        fenetre.effacer();
        
        int espaceEntreLigne=largeur/taille;
        for (int i =1;i<=taille;i++ ){
            Ligne ligneH = new Ligne(new Point(0, espaceEntreLigne*i), new Point(largeur, espaceEntreLigne*i));
            Ligne ligneV = new Ligne(new Point(espaceEntreLigne*i,0), new Point( espaceEntreLigne*i,hauteur));
            fenetre.ajouter(ligneH);
            fenetre.ajouter(ligneV);
        }
        fenetre.rafraichir();
         taille++;//deuxième correction
       

       

        int cptTour=0;
        int numJoueurTour =1;
        Plateau plateauMorpion= new Plateau(taille,xCoupsGagnant);
        

        for(int i=0;i<taille;i++){
            for(int j=0;j<taille;j++){
                if(plateauMorpion.getXY(i,j,taille).equals(new Case(0))){
                    Texte caseVide = new Texte(Couleur.NOIR, "-", new Font("Calibri", Font.TYPE1_FONT, 70), new Point((i*espaceEntreLigne)-espaceEntreLigne/2,(j*espaceEntreLigne)-espaceEntreLigne/2));
                    fenetre.ajouter(caseVide);
                    fenetre.rafraichir();
                }
            }
        }
        
        while( (plateauMorpion.plein(taille))==false && plateauMorpion.gagne(taille,xCoupsGagnant) == 0){

            if(cptTour % 2 ==0){//si c'est pair le joueur 1 joue
                numJoueurTour = 1;
            }else{
                numJoueurTour = 2;
            }
        
            //on recupére les coordonnées de la souris
            Point cooSouris = new Point(souris.getPosition());
            int sourisX = cooSouris.getX();
            int sourisY = cooSouris.getY();
            
            
            
            if(souris.getClicGauche()){

                fenetre.effacer();

                int ligne =(int)(sourisX/espaceEntreLigne);
                int colonne =(int)(sourisY/espaceEntreLigne);
                ligne++;//correction
                colonne++;
                System.out.println(plateauMorpion);
                if(plateauMorpion.getXY(ligne,colonne,taille).getJoueur()==0){
                     plateauMorpion.getXY(ligne, colonne,taille).setJoueur(numJoueurTour);
                 
                        for(int i=taille-1;i>=0;i--){
                            for(int j=taille-1;j>=0;j--){
                                if(plateauMorpion.getXY(i,j,taille).getJoueur()==0){
                                    Texte caseVide = new Texte(Couleur.NOIR, "-", new Font("Calibri", Font.TYPE1_FONT, 70), new Point((i*espaceEntreLigne)-espaceEntreLigne/2,(j*espaceEntreLigne)-espaceEntreLigne/2));
                                    fenetre.ajouter(caseVide);
                                    fenetre.rafraichir();
                                }else if(plateauMorpion.getXY(i,j,taille).getJoueur()==1){
                                    Texte caseCroix = new Texte(Couleur.BLEU, "X", new Font("Calibri", Font.TYPE1_FONT, 70), new Point((i*espaceEntreLigne)-espaceEntreLigne/2,(j*espaceEntreLigne)-espaceEntreLigne/2));
                                    fenetre.ajouter(caseCroix);
                                    fenetre.rafraichir();
                                }else if(plateauMorpion.getXY(i,j,taille).getJoueur()==2){
                                    Texte caseRond = new Texte(Couleur.ROUGE, "O", new Font("Calibri", Font.TYPE1_FONT, 70), new Point((i*espaceEntreLigne)-espaceEntreLigne/2,(j*espaceEntreLigne)-espaceEntreLigne/2));
                                    fenetre.ajouter(caseRond);
                                    fenetre.rafraichir();
                                }
                            }
                        }

                        for (int i =1;i<=taille-1;i++ ){
                            Ligne ligneH = new Ligne(new Point(0, espaceEntreLigne*i), new Point(largeur, espaceEntreLigne*i));
                            Ligne ligneV = new Ligne(new Point(espaceEntreLigne*i,0), new Point( espaceEntreLigne*i,hauteur));
                            fenetre.ajouter(ligneH);
                            fenetre.ajouter(ligneV);
                        }
                

                        cptTour++;
                    }
                }
                
            

            
        


        }
        
        int gagnant = plateauMorpion.gagne(taille,xCoupsGagnant);
        if (gagnant==0){
            Texte texteResultat0 = new Texte(Couleur.NOIR, "Égalité", new Font("Calibri", Font.TYPE1_FONT, 100), new Point(500,900));
            fenetre.ajouter(texteResultat0);
        }else{
            String resultat =" Le gagnant est le joueur"+String.valueOf(gagnant);
            Texte texteResultat1 = new Texte(Couleur.NOIR, resultat, new Font("Calibri", Font.TYPE1_FONT, 60), new Point(500,900));
            fenetre.ajouter(texteResultat1);
        }
        fenetre.rafraichir();

        
        
        
        
        
        
    } 




    }

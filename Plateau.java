import javax.swing.text.TabSet;


class Plateau{
    private Case tabPlateau[][] ;
    //constructeur creer un tableau de la taille entré
    public Plateau(int taille,int xCoupsGagnant){
        tabPlateau=new Case[taille][taille];
        for(int i = 0; i<taille;i++){
            for(int j = 0; j<taille;j++){
                tabPlateau[i][j]=new Case();
            }
        }
    }
   //si i ou j sont en dehors des bornes on arrete le programme et on print "erreur"
    public Case getXY(int i, int j,int taille){
        if( !(i>=0 && i<taille && j>=0 && j<taille)) {      
            System.out.println("Erreur sortie de tableau");
         System.exit (0); }
        return  tabPlateau[i][j];
        
    }
    //verifie qu'il reste au moins  une case de libre
    public boolean plein(int taille){
        boolean rempli=true;
        while(rempli){
            for(int i = 0; i < taille; i++){
                for(int j = 0; j < taille; j++){
                    if(tabPlateau[i][j].equals(new Case()))
                        rempli=false;
                }
            }
        }
        return rempli;
    }
    
    public int gagne(int taille,int xCoupsGagnant){
        
        int cptHj1;//compteur pour le joueur 1  qui compte pour l'axe horizontal
        int cptVj1;//compteur pour le joueur 1 qui compte pour l'axe vertical
        int cptHj2;//compteur pour le joueur 2 qui compte pour l'axe horizontal
        int cptVj2;//compteur pour le joueur 2 qui compte pour l'axe vertical
        for(int i =0;i<taille;i++){
            //on renisialise a zero
            cptHj1=0;
            cptVj1=0;
            cptHj2=0;
            cptVj2=0;
            for(int j=0;j<taille;j++){
                if(tabPlateau[i][j].equals(new Case(1))){cptHj1++;cptHj2=0;}
                if(tabPlateau[j][i].equals(new Case(1))){cptVj1++;cptVj2=0;}

                if(tabPlateau[i][j].equals(new Case(0))){cptHj1=0;cptHj2=0;}
                if(tabPlateau[j][i].equals(new Case(0))){cptVj1=0;cptVj2=0;}

                if(tabPlateau[i][j].equals(new Case(2))){cptHj2++;cptHj1=0;}
                if(tabPlateau[j][i].equals(new Case(2))){cptVj2++;cptVj1=0;}

                if(cptHj1==xCoupsGagnant || cptVj1==xCoupsGagnant){return 1;}
                if(cptHj2==xCoupsGagnant || cptVj2==xCoupsGagnant){return 2;}
            }
        }
        //diagonle haut gauche vers bas droit
        int cptDj1 = 0;
        int cptDj2 = 0;
        for( int i=0;i<taille;i++){
            if(tabPlateau[i][i].equals(new Case(1))){cptDj1++;cptDj2=0;}
            if(tabPlateau[i][i].equals(new Case(2))){cptDj2++;cptDj1=0;}
            if(tabPlateau[i][i].equals(new Case(0))){cptDj2=0;cptDj1=0;}

            if(cptDj1==xCoupsGagnant ){return 1;}
            if(cptDj2==xCoupsGagnant ){return 2;}
        }
        //diagonle haut droite vers bas gauche
        cptDj1 = 0;
        cptDj2 = 0;
        for( int i=0 , j=taille-1;i<taille && j>=0;i++,j--){
            if(tabPlateau[i][j].equals(new Case(1))){cptDj1++;}
            if(tabPlateau[i][j].equals(new Case(2))){cptDj2++;}

            if(cptDj1==xCoupsGagnant ){return 1;}
            if(cptDj2==xCoupsGagnant ){return 2;}
            //if xCoupsGagnants<taille
           
        }
        
        //Retourne zéro si aucune des conditions de victoires sont remplies
        return 0;
        //sinon retourne zero
        
        
    }
    
    public String toString(){
        String resultat = "";
        //boucle imbrique (2for dans 1 for)
        for(int i = 0;i<tabPlateau.length-1;i++){
            resultat+="|"; //imprime le bord gauche
            for(int k = 0;k<(tabPlateau.length*2)-3;k++){//imprime la ligne entre
                resultat+="-";
            }
            resultat+="|\n";
            for(int j = 0;j<tabPlateau.length-1;j++){//imprime la ligne du contenu
                resultat+="|";
                resultat+=tabPlateau[i][j].toString();
                
            }
            resultat+="|\n";//imprime le bord droit
        }
        resultat+="|";
        for(int k = 0;k<(tabPlateau.length*2)-3;k++){
            resultat+="-";//imprime la dernière ligne 
        }
        resultat+="|";
        return resultat;

    }

    }
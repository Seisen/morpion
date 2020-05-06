import java.util.Scanner;

class Main {
    

    
    public static void main(String args[]){


       

        //initialisation du plateau
        
        Scanner sc = new Scanner(System.in);
        int taille;
        int xCoupsGagnant;
        System.out.println("Entrez la taille du plateau");
        taille=sc.nextInt();
        taille++;
        System.out.println("Entrez le nombre de coups gagnants");
        xCoupsGagnant=sc.nextInt();
        Plateau plateauMorpion= new Plateau(taille,xCoupsGagnant);

        int cptTour = 0;
        int numJoueurTour;
        int ligne;
        int colonne;
        int gagnant;

        while( (plateauMorpion.plein(taille))==false && plateauMorpion.gagne(taille,xCoupsGagnant) == 0){

            System.out.println(plateauMorpion);
            System.out.println("---------------------------");
            System.out.println("les lignes et colonnes vont de 0 à 2");
            System.out.println("---------------------------");
            
            if(cptTour % 2 ==0){//si c'est pair le joueur 1 joue
                System.out.println("Au joueur 1 de jouer");
                numJoueurTour = 1;
            }else{
                System.out.println("Au joueur 2 de jouer");
                numJoueurTour = 2;
            }
            System.out.println("---------------------------");
            
            
                System.out.println("Entrez le numéro de ligne ");
                System.out.println("---------------------------");
                ligne = sc.nextInt();
                if(ligne < 0 || ligne>taille){
                    while(ligne<0 || ligne>taille){
                    System.out.println("Entrez le numéro de ligne  ");
                    System.out.println("---------------------------");
                    ligne = sc.nextInt();
                    }
                }
                System.out.println("Ligne entré avec succès !");
            
                System.out.println("---------------------------");
                
                System.out.println("Entrez le numéro de colonne ");
                System.out.println("---------------------------");
                colonne = sc.nextInt();  
        
                if(colonne < 0 || colonne> taille){
                    while(colonne<0 || colonne>taille){
                    System.out.println("Entrez le numéro de colonne  ");
                    System.out.println("---------------------------");
                    colonne = sc.nextInt();
                    }
                }
                System.out.println("Ligne entré avec succès !");

                if(plateauMorpion.getXY(ligne,colonne,taille).getJoueur() != 0){
                    System.out.println("Case déjà prise! :( ");
                    while(plateauMorpion.getXY(ligne,colonne,taille).getJoueur() != 0){

                        System.out.println("Entrez le numéro de ligne ");
                        System.out.println("---------------------------");
                        ligne = sc.nextInt();
                        if(ligne < 0 || ligne>taille){
                            while(ligne<0 || ligne>taille){
                            System.out.println("Entrez le numéro de ligne  ");
                            System.out.println("---------------------------");
                            ligne = sc.nextInt();
                            }
                        }
                        System.out.println("Ligne entré avec succès !");
                    
                        System.out.println("---------------------------");
                        
                        System.out.println("Entrez le numéro de colonne ");
                        System.out.println("---------------------------");
                        colonne = sc.nextInt();  

                        if(colonne < 0 || colonne> taille){
                            while(colonne<0 || colonne>taille){
                            System.out.println("Entrez le numéro de colonne  ");
                            System.out.println("---------------------------");
                            colonne = sc.nextInt();
                            }
                        }

                        System.out.println("Ligne entré avec succès !");

                    }
                }
                plateauMorpion.getXY(ligne, colonne,taille).setJoueur(numJoueurTour);
                System.out.println("---------------------------");
                cptTour++;
                
            
            
        }
        System.out.println(plateauMorpion);
        gagnant = plateauMorpion.gagne(taille,xCoupsGagnant);
        if (gagnant==0){
            System.out.println("Égalité!");
        }else{System.out.println("Le gagnant est le joueur " + gagnant);}
    }    

    
}
/**/
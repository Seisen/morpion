class Case {


    private int joueur;



    public boolean equals(Object objet){
        if (this == objet){return true;}
        if (objet == null){return false;}
        if (getClass() != objet.getClass()){return false;}
        final Case other = (Case) objet;{return (this.joueur == other.joueur);}
    }
    public int getJoueur(){
        return this.joueur;
    }
    public void setJoueur(int new_joueur){
        this.joueur=new_joueur;
    }
    //constructeur par defaut
    public Case(){
        joueur = 0;
    }
    //copie
    public Case(Case casecopie){
        this.joueur = casecopie.joueur;
    }
    //constructeur
    public Case(int joueur){
        this.joueur = joueur;
    }
    public String toString(){
        return (Integer.toString(getJoueur()));
    }

}
public abstract class Pokemon {
    protected final String nom;
    protected int hp;
    protected final int atk;

    public Pokemon(String nom, int hp, int atk) {
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom du Pokémon ne peut pas être vide.");
        }
        if (hp <= 0) {
            throw new IllegalArgumentException("Les HP doivent être > 0.");
        }
        if (atk <= 0) {
            throw new IllegalArgumentException("L'ATK doit être > 0.");
        }
        this.nom = nom.trim();
        this.hp = hp;
        this.atk = atk;
    }

    public boolean isDead() {
        return hp <= 0;
    }

    public String getNom() { return nom; }
    public int getHp() { return hp; }
    public int getAtk() { return atk; }

    // Le polymorphisme est ici : chaque type calcule son multiplicateur
    protected abstract double multiplicateurContre(Pokemon cible);

    public final void attaquer(Pokemon cible) {
        if (cible == null) {
            throw new IllegalArgumentException("La cible ne peut pas être null.");
        }

        if (this.isDead()) {
            System.out.println(this.nom + " est KO et ne peut pas attaquer !");
            return;
        }
        if (cible.isDead()) {
            System.out.println(cible.nom + " est déjà KO. Attaque inutile.");
            return;
        }

        double mult = multiplicateurContre(cible);

        int degats = (int) Math.round(this.atk * mult);

        // Si l'attaque est "peu efficace" (0.5) ou normale (1) ou super (2),
        // on évite degats=0 à cause d'arrondi (sauf si un jour tu ajoutes x0).
        if (mult > 0 && degats < 1) degats = 1;

        cible.subirDegats(degats);

        System.out.printf("%s attaque %s : ATK=%d, x%.1f => %d dégâts. (%s HP=%d)%n",
                this.nom, cible.nom, this.atk, mult, degats, cible.nom, cible.hp);

        if (cible.isDead()) {
            System.out.println(">>> " + cible.nom + " est KO !");
        }
    }

    protected void subirDegats(int degats) {
        if (degats < 0) {
            throw new IllegalArgumentException("Les dégâts ne peuvent pas être négatifs.");
        }
        this.hp -= degats;
        if (this.hp < 0) this.hp = 0;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) HP:%d ATK:%d",
                nom, getClass().getSimpleName(), hp, atk);
    }
}

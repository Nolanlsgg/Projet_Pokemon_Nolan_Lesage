import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Pokemon salameche = new PokemonFeu("Salameche", 100, 20);
        Pokemon carapuce  = new PokemonEau("Carapuce", 100, 20);
        Pokemon bulbizarre = new PokemonPlante("Bulbizarre", 100, 20);
        Pokemon roucool = new PokemonNormal("Roucool", 90, 15);

        // Démo du scénario demandé
        System.out.println("=== Démo scénario ===");
        System.out.println(salameche);
        System.out.println(carapuce);
        salameche.attaquer(carapuce);   // feu -> eau = x0.5 => 10
        carapuce.attaquer(salameche);   // eau -> feu = x2 => 40
        System.out.println();

        // Petit combat auto (tour par tour)
        System.out.println("=== Combat: Bulbizarre vs Salameche ===");
        duel(bulbizarre, salameche);

        System.out.println("\n=== Combat: Roucool vs Carapuce ===");
        duel(roucool, carapuce);

        // Exemple gestion KO / erreurs
        System.out.println("\n=== Tests KO / sécurité ===");
        Pokemon weak = new PokemonFeu("MiniFeu", 1, 5);
        Pokemon strong = new PokemonEau("MegaEau", 100, 50);
        System.out.println(weak);
        System.out.println(strong);
        strong.attaquer(weak); // KO
        weak.attaquer(strong); // ne peut pas attaquer
        strong.attaquer(weak); // cible déjà KO
    }

    private static void duel(Pokemon a, Pokemon b) {
        if (a == null || b == null) throw new IllegalArgumentException("Pokémon null dans le duel.");
        System.out.println("Début :");
        System.out.println(" - " + a);
        System.out.println(" - " + b);

        Random rnd = new Random();

        // Qui commence ?
        Pokemon attaquant = rnd.nextBoolean() ? a : b;
        Pokemon defenseur = (attaquant == a) ? b : a;

        int tour = 1;
        while (!a.isDead() && !b.isDead()) {
            System.out.println("\nTour " + tour + " :");
            System.out.println("Attaquant: " + attaquant.getNom());
            attaquant.attaquer(defenseur);

            // swap
            Pokemon tmp = attaquant;
            attaquant = defenseur;
            defenseur = tmp;

            tour++;
        }

        System.out.println("\nFin du combat :");
        System.out.println(" - " + a);
        System.out.println(" - " + b);

        Pokemon gagnant = a.isDead() ? b : a;
        System.out.println(">>> Vainqueur : " + gagnant.getNom());
    }
}

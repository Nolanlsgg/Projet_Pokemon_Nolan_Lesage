public class PokemonNormal extends Pokemon {
    public PokemonNormal(String nom, int hp, int atk) {
        super(nom, hp, atk);
    }

    @Override
    protected double multiplicateurContre(Pokemon cible) {
        return 1.0; // normal = toujours x1 dans cet exercice
    }
}

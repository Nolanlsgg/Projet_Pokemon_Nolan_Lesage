public class PokemonFeu extends Pokemon {
    public PokemonFeu(String nom, int hp, int atk) {
        super(nom, hp, atk);
    }

    @Override
    protected double multiplicateurContre(Pokemon cible) {
        if (cible instanceof PokemonPlante) return 2.0;
        if (cible instanceof PokemonEau) return 0.5;
        if (cible instanceof PokemonFeu) return 0.5;
        return 1.0; // normal / autres
    }
}

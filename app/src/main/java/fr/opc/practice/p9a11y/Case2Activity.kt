package fr.opc.practice.p9a11y

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fr.opc.practice.p9a11y.databinding.ActivityCase2Binding

class Case2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityCase2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCase2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var isFavourite = false

        // Configuration initiale
        updateFavouriteButtonState(isFavourite)

        // Gestion du clic sur le bouton "Ajouter/Retirer des favoris"
        binding.favouriteButton.setOnClickListener {
            // Inverser l'état actuel
            isFavourite = !isFavourite

            // Mettre à jour l'état (icône + description pour le survol)
            updateFavouriteButtonState(isFavourite)

            // Annonce explicite avec un léger délai pour éviter les conflits
            binding.favouriteButton.postDelayed({
                val announcement = if (isFavourite) {
                    getString(R.string.favourite_added) // "Ajouté aux favoris"
                } else {
                    getString(R.string.favourite_removed) // "Retiré des favoris"
                }
                binding.root.announceForAccessibility(announcement)
            }, 100) // Délai pour garantir une annonce unique
        }

        // Gestion du clic sur "Ajouter la recette au panier"
        binding.addRecipeToBasket.setOnClickListener {
            Toast.makeText(this, getString(R.string.recette_ajout_au_panier), Toast.LENGTH_SHORT)
                .show()
        }

        // Gestion du clic sur la carte de recette
        binding.recipeCard.setOnClickListener {
            // TODO: Naviguer vers l'écran de recette
            Toast.makeText(this, "Navigation vers la recette", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Met à jour l'icône et la description pour le survol, sans déclencher une annonce automatique.
     */
    private fun updateFavouriteButtonState(isFavourite: Boolean) {
        if (isFavourite) {
            binding.favouriteButton.setImageResource(R.drawable.ic_favourite_on)

            // Définir la description pour le survol uniquement
            binding.favouriteButton.contentDescription = getString(R.string.favourite_remove_action) // "Retirer des favoris"
        } else {
            binding.favouriteButton.setImageResource(R.drawable.ic_favourite_off)

            // Définir la description pour le survol uniquement
            binding.favouriteButton.contentDescription = getString(R.string.favourite_add_action) // "Ajouter aux favoris"
        }
    }
}

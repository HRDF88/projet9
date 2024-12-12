package fr.opc.practice.p9a11y

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fr.opc.practice.p9a11y.databinding.ActivityCase2Binding

/**
 * This activity represents a screen where users can manage their favorite recipes, add recipes to the cart,
 * and navigate to a detailed recipe view. The class provides functionality to dynamically update the UI
 * and offer accessibility support for users with screen readers.
 *
 * The screen includes:
 * - A "Favorite" button that toggles the favorite state of a recipe and provides feedback.
 * - A button to add the recipe to the shopping cart.
 * - A recipe card that navigates to the recipe's detailed view.
 *
 * Accessibility support:
 * - Prevents duplicate announcements by managing content descriptions and accessibility properties.
 * - Provides explicit feedback to screen readers about actions taken.
 *
 * @class Case2Activity
 * @extends AppCompatActivity
 */
class Case2Activity : AppCompatActivity() {

    /**
     * Binding instance for accessing and modifying the UI components defined in the XML layout file.
     */
    private lateinit var binding: ActivityCase2Binding

    /**
     * Called when the activity is created. Sets up the view, initializes UI components, and defines
     * click listeners for user interactions with the "Favorite" button, recipe card, and add-to-cart button.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     * this Bundle contains the most recent data. Otherwise, it is null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCase2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var isFavourite = false

        // Init config
        updateFavouriteButtonState(isFavourite)

        //Managing the click on the “Add/Remove favorites” button
        binding.favouriteButton.setOnClickListener {

            //Reverse current state
            isFavourite = !isFavourite

            // Update status (icon + description for hover)
            updateFavouriteButtonState(isFavourite)

            //Explicit announcement with a slight delay to avoid conflicts
            binding.favouriteButton.postDelayed({
                val announcement = if (isFavourite) {
                    getString(R.string.favourite_added)
                } else {
                    getString(R.string.favourite_removed)
                }
                binding.root.announceForAccessibility(announcement)
            }, 100)
        }

        // Managing the click on “Add recipe to cart”
        binding.addRecipeToBasket.setOnClickListener {
            Toast.makeText(this, getString(R.string.recette_ajout_au_panier), Toast.LENGTH_SHORT)
                .show()
        }

        // Management of clicks on the recipe card
        binding.recipeCard.setOnClickListener {
            Toast.makeText(this, getString(R.string.navigation_to_recipe), Toast.LENGTH_SHORT)
                .show()
        }
    }

    /**
     * Updates the state of the "Favorite" button. This includes changing the button's icon and
     * updating its content description to reflect the current action (add/remove favorite).
     *
     * To prevent duplicate accessibility announcements, accessibility is temporarily disabled
     * while the button's state is updated and then re-enabled afterward.
     *
     * @param isFavourite Boolean indicating whether the recipe is currently marked as favorite.
     */
    private fun updateFavouriteButtonState(isFavourite: Boolean) {
        binding.favouriteButton.run {
            // Temporarily disable accessibility for the button to prevent automatic announcements
            importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_NO

            // Update the icon and content description
            setImageResource(if (isFavourite) R.drawable.ic_favourite_on else R.drawable.ic_favourite_off)
            contentDescription = if (isFavourite) {
                getString(R.string.favourite_remove_action)
            } else {
                getString(R.string.favourite_add_action)
            }

            // Re-enable accessibility after the update
            post {
                importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_YES
            }
        }
    }
}

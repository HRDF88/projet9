package fr.opc.practice.p9a11y

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fr.opc.practice.p9a11y.databinding.ActivityCase1Binding

/**
 * This activity provides functionality for managing the quantity of a product.
 * Users can increase or decrease the quantity, and the app provides real-time
 * feedback for accessibility purposes.
 *
 * The activity features:
 * - A display for the current quantity.
 * - Buttons to increase or decrease the quantity.
 * - Accessibility features such as content descriptions and announcements for screen readers.
 *
 * Accessibility Support:
 * - Content descriptions are dynamically updated to reflect the quantity.
 * - Screen reader announcements are triggered whenever the quantity changes.
 * - Prevents users from setting a negative quantity and provides appropriate feedback.
 *
 * @class Case1Activity
 * @extends AppCompatActivity
 */
class Case1Activity : AppCompatActivity() {

    /**
     * Binding instance for accessing and modifying the UI components defined in the XML layout file.
     */
    private lateinit var binding: ActivityCase1Binding

    /**
     * Called when the activity is created. Sets up the view, initializes the quantity, and defines
     * click listeners for increasing or decreasing the quantity.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     * this Bundle contains the most recent data. Otherwise, it is null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCase1Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Variable to track the quantity
        var quantity = 0

        //adds the content description of the number of product (quantity)
        binding.quantityText.contentDescription = getString(R.string.quantity_description, quantity)

        // Set the initial quantity text
        binding.quantityText.text = "$quantity"

        // Listener for the "Add" button
        binding.addButton.setOnClickListener {
            // Increment the quantity
            quantity++
            binding.quantityText.text = "$quantity"

            //adds the content description of the number of product (quantity)
            binding.quantityText.contentDescription =
                getString(R.string.quantity_description, quantity)

            //automatically states the number of product (quantity) once the button is clicked
            binding.root.announceForAccessibility(
                getString(
                    R.string.quantity_description,
                    quantity
                )
            )
        }

        // Listener for the "Remove" button
        binding.removeButton.setOnClickListener {
            // Decrease the quantity if it's greater than 0
            if (quantity > 0) {
                quantity--
                binding.quantityText.text = "$quantity"

                //adds the content description of the number of product (quantity)
                binding.quantityText.contentDescription =
                    getString(R.string.quantity_description, quantity)

                //automatically states the number of product (quantity) once the button is clicked
                binding.root.announceForAccessibility(
                    getString(
                        R.string.quantity_description,
                        quantity
                    )
                )

            } else {
                // Show a toast message if the user attempts to go below 0
                Toast.makeText(
                    this,
                    getString(R.string.impossible_d_avoir_une_quantit_n_gative),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
    }
}

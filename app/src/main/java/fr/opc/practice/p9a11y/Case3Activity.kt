package fr.opc.practice.p9a11y

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import fr.opc.practice.p9a11y.databinding.ActivityCase3Binding

/**
 * This activity represents a screen where users can input a pseudonym into a text field.
 * The application dynamically validates the input and provides real-time feedback to the user
 * about the validity of their pseudonym based on specific conditions.
 *
 * The screen features:
 * - A text field for entering a pseudonym.
 * - A button for validation that becomes enabled or disabled based on the input's validity.
 * - Dynamic error and success messages to guide the user.
 * - Accessibility support for screen readers via content descriptions and visibility toggles.
 *
 * @class Case3Activity
 * @extends AppCompatActivity
 */
class Case3Activity : AppCompatActivity() {

    /**
     * Binding instance for accessing and modifying the UI components defined in the XML layout file.
     */
    private lateinit var binding: ActivityCase3Binding

    /**
     * Called when the activity is starting. Initializes the view, binds the UI elements, and sets up
     * listeners for handling user input in the pseudonym text field.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     * this Bundle contains the most recent data. Otherwise, it is null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCase3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.pseudoEdit.doOnTextChanged { text, _, _, _ ->
            text?.length?.let { textLength ->
                val isValid = textLength > 2


                // Modify the button according to validity
                binding.validateButton.isEnabled = isValid


                // Modify the field color
                binding.pseudoEdit.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        this,
                        if (isValid) R.color.green400 else R.color.red400
                    )
                )

                // Manage error message
                if (isValid) {
                    binding.errorMessage.visibility = View.GONE
                    binding.successMessage.visibility = View.VISIBLE
                } else {
                    binding.errorMessage.visibility = View.VISIBLE
                    binding.successMessage.visibility = View.GONE
                    binding.errorMessage.importantForAccessibility =
                        View.IMPORTANT_FOR_ACCESSIBILITY_YES
                }

                // Provide guidance for TalkBack
                binding.pseudoEdit.contentDescription = if (isValid) {
                    getString(R.string.valid_entry)
                } else {
                    getString(R.string.no_valid_entry)
                }
            }
        }
    }
}


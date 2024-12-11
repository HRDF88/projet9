package fr.opc.practice.p9a11y

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fr.opc.practice.p9a11y.databinding.ActivityCase1Binding

class Case1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityCase1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCase1Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var quantity = 0

        //adds the content description of the number of product (quantity)
        binding.quantityText.contentDescription = getString(R.string.quantity_description, quantity)



        binding.quantityText.text = "$quantity"
        binding.addButton.setOnClickListener {
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

        binding.removeButton.setOnClickListener {
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

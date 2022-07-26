package com.ddtinfotech.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ddtinfotech.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        it specifies the root of the views / parent
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setting click listener to the calculate button
        binding.btnCalculate.setOnClickListener { calculateTip() }

    }

    private fun calculateTip() {
        val stringInput = binding.editText.text.toString()
        val cost = stringInput.toDoubleOrNull()
        if (cost == null || cost == 0.0) {
            displayTip(0.0)
            return
        }

        //        val selectedTip = binding.optionsForTip.checkedRadioButtonId
        val percentageTip = when (binding.optionsForTip.checkedRadioButtonId) {
            R.id.optionTenPercent -> 0.10
            R.id.optionFifteenPercent -> 0.15
            else -> 0.20
        }

        var tip = cost * percentageTip

//        val roundUp = binding.roundOfSwitch.isChecked
        if (binding.roundOfSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }
        displayTip(tip)
    }

    private fun displayTip(tip: Double) {
        val formattedCurrency = NumberFormat.getCurrencyInstance().format(tip)
        binding.txtFinalTip.text = getString(R.string.tip_amount, formattedCurrency)
    }

}

/*
The name of the binding class is generated by converting the name
of the XML file to Pascal case and adding the word "Binding" to the end.
 Similarly, the reference for each view is generated by removing
  underscores and converting the view name to camel case.
  For example, in Pascal case activity_main.xml becomes
   ActivityMainBinding, and you can access @id/text_view as
   binding.textView.
 */
package com.msong.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    // vars for calculation
    var amount = 0.0
    var tipPercent = 0.15
    var tip = 0.0
    var total = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // When amount is changed
        editTextAmount.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                amount = if (editTextAmount.text.isNotEmpty()) editTextAmount.text.toString().toDouble() else 0.0
                calculate()

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })

        // when tip % is changed
        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if (editTextAmount.text.isEmpty()) {
                    return
                }
                textViewTipPercent.setText(p1.toString() + "%")
                tipPercent = (seekBar / 100.00)
                calculate()
            }

            override fun onStartTrackingTouch(p1: SeekBar?) {

            }

            override fun onStopTrackingTouch(p1: SeekBar?) {

            }
        })
    }
    fun calculate() {
        // calc & display tip and total amounts
        tip = amount * tipPercent
        total = amount * tip

        val currencyFormat = NumberFormat.getCurrencyInstance()

        // display in the text views
        textViewTipAmount.setText(currencyFormat.format(tip).toString())
        textViewTotalAmount.setText(currencyFormat.format(total).toString())
    }
}
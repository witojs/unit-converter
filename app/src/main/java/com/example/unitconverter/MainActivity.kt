package com.example.unitconverter

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    private val buttonCelcius by lazy { findViewById<Button>(R.id.btn_celcius) }
    private val buttonFahrenheit by lazy { findViewById<Button>(R.id.btn_fahrenheit) }
    private val tvInput by lazy { findViewById<TextView>(R.id.tv_input) }
    private val tvOutput by lazy { findViewById<TextView>(R.id.tv_output) }
    private val buttonSwap by lazy { findViewById<Button>(R.id.btn_swap) }

    private val buttonOne by lazy { findViewById<Button>(R.id.btn_1) }
    private val buttonTwo by lazy { findViewById<Button>(R.id.btn_2) }
    private val buttonThree by lazy { findViewById<Button>(R.id.btn_3) }
    private val buttonClear by lazy { findViewById<Button>(R.id.btn_clear) }

    private val buttonFour by lazy { findViewById<Button>(R.id.btn_4) }
    private val buttonFive by lazy { findViewById<Button>(R.id.btn_5) }
    private val buttonSix by lazy { findViewById<Button>(R.id.btn_6) }
    private val buttonPlus by lazy { findViewById<Button>(R.id.btn_plus) }

    private val buttonSeven by lazy { findViewById<Button>(R.id.btn_7) }
    private val buttonEight by lazy { findViewById<Button>(R.id.btn_8) }
    private val buttonNine by lazy { findViewById<Button>(R.id.btn_9) }
    private val buttonMinus by lazy { findViewById<Button>(R.id.btn_minus) }

    private val buttonZero by lazy { findViewById<Button>(R.id.btn_0) }
    private val buttonDot by lazy { findViewById<Button>(R.id.btn_dot) }
    private val buttonConvert by lazy { findViewById<Button>(R.id.btn_convert) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        observeInput()
        observeOutput()

        buttonSwap.setOnClickListener {
            animateInputSwap()
            viewModel.switchInputUnit()
            viewModel.resetInputOutput()
        }

        buttonOne.setButtonClickListener("1")
        buttonTwo.setButtonClickListener("2")
        buttonThree.setButtonClickListener("3")
        buttonFour.setButtonClickListener("4")
        buttonFive.setButtonClickListener("5")
        buttonSix.setButtonClickListener("6")
        buttonSeven.setButtonClickListener("7")
        buttonEight.setButtonClickListener("8")
        buttonNine.setButtonClickListener("9")
        buttonZero.setButtonClickListener("0")
        buttonDot.setButtonClickListener(".")

        buttonPlus.setButtonClickListener("+")
        buttonMinus.setButtonClickListener("-")
        buttonClear.setOnClickListener {
            viewModel.clearInput()
        }

        buttonConvert.setOnClickListener {
            viewModel.convertUnit()
        }

    }


    private fun observeInput() {
        viewModel.input.observe(this) { input ->
            tvInput.text = input
        }
    }

    private fun observeOutput() {
        viewModel.output.observe(this) { output ->
            tvOutput.text = output
        }
    }

    private fun animateInputSwap() {
        //get initial position
        val initialY1 = buttonCelcius.y
        val initialY2 = buttonFahrenheit.y

        //animate button celcius
        buttonCelcius.animate()
            .alpha(0f)
            .y(initialY2)
            .setDuration(300)
            .withEndAction {
                buttonCelcius.y = initialY2
                buttonCelcius.alpha = 1f
            }

        //animate button fahrenheit
        buttonFahrenheit.animate()
            .alpha(0f)
            .y(initialY1)
            .setDuration(300)
            .withEndAction {
                buttonFahrenheit.y = initialY1
                buttonFahrenheit.alpha = 1f
            }
    }

    //extension function for button
    private fun Button.setButtonClickListener(value: String) {
        setOnClickListener {
            viewModel.setInput(value)
        }
    }
}
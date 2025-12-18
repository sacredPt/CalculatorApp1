package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var currentInput = ""
    private var operator = ""
    private var firstNumber = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.display)

        val buttons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )

        for (id in buttons) {
            findViewById<Button>(id).setOnClickListener {
                currentInput += (it as Button).text
                display.text = currentInput
            }
        }

        findViewById<Button>(R.id.btnPlus).setOnClickListener { setOperator("+") }
        findViewById<Button>(R.id.btnMinus).setOnClickListener { setOperator("-") }
        findViewById<Button>(R.id.btnMul).setOnClickListener { setOperator("*") }
        findViewById<Button>(R.id.btnDiv).setOnClickListener { setOperator("/") }

        findViewById<Button>(R.id.btnClear).setOnClickListener {
            currentInput = ""
            operator = ""
            firstNumber = 0.0
            display.text = "0"
        }

        findViewById<Button>(R.id.btnEquals).setOnClickListener {
            val second = currentInput.toDoubleOrNull() ?: return@setOnClickListener
            val result = when (operator) {
                "+" -> firstNumber + second
                "-" -> firstNumber - second
                "*" -> firstNumber * second
                "/" -> if (second != 0.0) firstNumber / second else 0.0
                else -> 0.0
            }
            display.text = result.toString()
            currentInput = result.toString()
        }
    }

    private fun setOperator(op: String) {
        firstNumber = currentInput.toDoubleOrNull() ?: return
        operator = op
        currentInput = ""
    }
}

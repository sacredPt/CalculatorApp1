package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var input = ""
    private var operator = ""
    private var first = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.display)

        val ids = listOf(
            R.id.btn0,R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4,
            R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8,R.id.btn9
        )

        ids.forEach {
            findViewById<Button>(it).setOnClickListener { b ->
                input += (b as Button).text
                display.text = input
            }
        }

        findViewById<Button>(R.id.btnPlus).setOnClickListener { setOp("+") }
        findViewById<Button>(R.id.btnMinus).setOnClickListener { setOp("-") }
        findViewById<Button>(R.id.btnMul).setOnClickListener { setOp("*") }
        findViewById<Button>(R.id.btnDiv).setOnClickListener { setOp("/") }

        findViewById<Button>(R.id.btnEquals).setOnClickListener {
            val second = input.toDouble()
            val res = when (operator) {
                "+" -> first + second
                "-" -> first - second
                "*" -> first * second
                "/" -> first / second
                else -> 0.0
            }
            display.text = res.toString()
            input = res.toString()
        }

        findViewById<Button>(R.id.btnClear).setOnClickListener {
            input = ""
            operator = ""
            display.text = "0"
        }
    }

    private fun setOp(op: String) {
        first = input.toDouble()
        operator = op
        input = ""
    }
}

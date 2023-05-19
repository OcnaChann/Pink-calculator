package com.example.calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnAC.setOnClickListener {
            binding.tVInput.text = ""
            binding.tVOutput.text = ""
        }
        binding.btnCancel.setOnClickListener {
            val currentInput = binding.tVInput.text.toString()
            if (currentInput.isNotEmpty()) {
                binding.tVInput.text = currentInput.substring(0, currentInput.length - 1)
            }
        }
        binding.btnEqual.setOnClickListener {
            val input = binding.tVInput.text.toString()
            val result = evaluateOperation(input)
            binding.tVOutput.text = result.toString()
        }

        binding.btn1.setOnClickListener { appendToInput("1") }
        binding.btn2.setOnClickListener { appendToInput("2") }
        binding.btn3.setOnClickListener { appendToInput("3") }
        binding.btn4.setOnClickListener { appendToInput("4") }
        binding.btn5.setOnClickListener { appendToInput("5") }
        binding.btn6.setOnClickListener { appendToInput("6") }
        binding.btn7.setOnClickListener { appendToInput("7") }
        binding.btn8.setOnClickListener { appendToInput("8") }
        binding.btn9.setOnClickListener { appendToInput("9") }
        binding.btn0.setOnClickListener { appendToInput("0") }
        binding.btnDot.setOnClickListener { appendToInput(".") }
        binding.btnPlus.setOnClickListener { appendToInput("+") }
        binding.btnMinus.setOnClickListener { appendToInput("-") }
        binding.btnMultiply.setOnClickListener { appendToInput("*") }
        binding.btnDivide.setOnClickListener { appendToInput("/") }
    }

    private fun appendToInput(str: String) {
        val currentInput = binding.tVInput.text.toString()
        val newInput = currentInput + str
        binding.tVInput.text = newInput
    }

    private fun evaluateOperation(input: String): Double {
        var result = 0.0
        val numbers = input.split("[+\\-*/]".toRegex())
        val operators = input.split("\\d+(\\.\\d+)?".toRegex()).dropLastWhile { it.isEmpty() }

        if (numbers.isNotEmpty()) {
            result = numbers[0].toDouble()
            for (i in 1 until numbers.size) {
                when (operators[i]) {
                    "+" -> result += numbers[i].toDouble()
                    "-" -> result -= numbers[i].toDouble()
                    "*" -> result *= numbers[i].toDouble()
                    "/" -> result /= numbers[i].toDouble()
                }
            }
        }

        return result
    }
}

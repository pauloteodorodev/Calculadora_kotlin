package com.example.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var num1 = ""
    var num2 = ""
    var op = ""
    var executouCalculo = false;
    lateinit var resultado: TextView
    lateinit var valorDigitado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn0 = findViewById<Button>(R.id.botao_0)
        val btn1 = findViewById<Button>(R.id.botao_1)
        val btn2 = findViewById<Button>(R.id.botao_2)
        val btn3 = findViewById<Button>(R.id.botao_3)
        val btn4 = findViewById<Button>(R.id.botao_4)
        val btn5 = findViewById<Button>(R.id.botao_5)
        val btn6 = findViewById<Button>(R.id.botao_6)
        val btn7 = findViewById<Button>(R.id.botao_7)
        val btn8 = findViewById<Button>(R.id.btao_8)
        val btn9 = findViewById<Button>(R.id.botao_9)
        val btnAdicionar = findViewById<Button>(R.id.botao_adicao)
        val btnMultiplicacao = findViewById<Button>(R.id.botao_multiplicacao)
        val btnDivisao = findViewById<Button>(R.id.botao_divisao)
        val btnIgual = findViewById<Button>(R.id.botao_igual)
        val btnSubtracao = findViewById<Button>(R.id.botao_subtracao)
        resultado = findViewById<TextView>(R.id.resultadoTela)
        valorDigitado = findViewById<TextView>(R.id.valorDigitadoTela)



        btn0.setOnClickListener(this)
        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
        btn4.setOnClickListener(this)
        btn5.setOnClickListener(this)
        btn6.setOnClickListener(this)
        btn7.setOnClickListener(this)
        btn8.setOnClickListener(this)
        btn9.setOnClickListener(this)
        btnAdicionar.setOnClickListener(this)
        btnSubtracao.setOnClickListener(this)
        btnMultiplicacao.setOnClickListener(this)
        btnDivisao.setOnClickListener(this)
        btnIgual.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.botao_0 -> formatarNumero("0")
            R.id.botao_1 -> formatarNumero("1")
            R.id.botao_2 -> formatarNumero("2")
            R.id.botao_3 -> formatarNumero("3")
            R.id.botao_4 -> formatarNumero("4")
            R.id.botao_5 -> formatarNumero("5")
            R.id.botao_6 -> formatarNumero("6")
            R.id.botao_7 -> formatarNumero("7")
            R.id.btao_8 -> formatarNumero("8")
            R.id.botao_9 -> formatarNumero("9")
            R.id.botao_adicao -> setOperacao("+")
            R.id.botao_subtracao -> setOperacao("-")
            R.id.botao_multiplicacao -> setOperacao("*")
            R.id.botao_divisao -> setOperacao("/")
            R.id.botao_igual -> calcular()
        }
    }

    private fun calcular() {
        if (op.isNotEmpty() && num2 != "") {
            var resultadoCalculo = 0.0
            when(op) {
                "+" -> resultadoCalculo = num1.toDouble() + num2.toDouble()
                "-" -> resultadoCalculo = num1.toDouble() - num2.toDouble()
                "*" -> resultadoCalculo = num1.toDouble() * num2.toDouble()
                "/" -> {
                    if (num2.toDouble() != 0.0) {
                        resultadoCalculo = num1.toDouble() / num2.toDouble()
                    } else {
                        resultado.text = "Erro: divisão por zero"
                        limparDados()
                        return
                    }
                }
            }
            if (num1.toIntOrNull() != null && num2.toIntOrNull() != null) {
                resultado.text = resultadoCalculo.toInt().toString()
                num1 = resultadoCalculo.toInt().toString()
            } else {
                resultado.text = resultadoCalculo.toString()
                num1 = resultadoCalculo.toString()
            }
            num2 = ""
            op = ""
            executouCalculo = true
        } else {
            resultado.text = "Erro"
        }
    }

    private fun formatarNumero(number: String) {
        if (executouCalculo) {
            limparDados()
        }
        if (op.isEmpty()) {
            if (num1.isEmpty()) {
                num1 = number
            } else {
                num1 += number
            }
        } else {
            num2 += number
        }
        valorDigitado.text = "$num1$op$num2"
    }


    private fun setOperacao(operator: String) {
        op = operator
        valorDigitado.text = "$num1$op"
    }



    private fun limparDados() {
        num1 = ""
        num2 = ""
        op = ""
        valorDigitado.text = ""
        executouCalculo = false
    }
}
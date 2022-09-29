package com.teste.drinkwater.model

import android.widget.Switch

class CalcularIngestaoDiaria {

    private val ML_JOVEM = 40.0
    private val ML_ADULTO = 35.0
    private val ML_MEIA_IDADE = 30.0
    private val ML_IDOSO = 25.0

    private var resultadoMl = 0.0

    fun CalcularTotalMl(peso: Double, idade: Int) {

        val idadePessoa = when (idade) {
            in 0..17 -> resultadoMl = peso * ML_JOVEM
            in 18..55 -> resultadoMl = peso * ML_ADULTO
            in 56..65 -> resultadoMl = peso * ML_MEIA_IDADE
            else -> resultadoMl = peso * ML_IDOSO
        }
    }

    fun ResultMl(): Double {
        return resultadoMl
    }

}
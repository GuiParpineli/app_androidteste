package com.teste.drinkwater

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.teste.drinkwater.model.CalcularIngestaoDiaria
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var editPeso: EditText
    private lateinit var editIdade: EditText
    private lateinit var btCalcular: Button
    private lateinit var txtResultadoml: TextView
    private lateinit var icRedefinirDados: ImageView

    private lateinit var calcularIngestaoDiaria: CalcularIngestaoDiaria

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()
        iniciarComponentes()
        calcularIngestaoDiaria = CalcularIngestaoDiaria()

        btCalcular.setOnClickListener {
            if (editPeso.text.toString().isEmpty()) {
                Toast.makeText(this, R.string.toast_informe_peso, Toast.LENGTH_SHORT).show()
            } else if (editIdade.text.toString().isEmpty()) {
                Toast.makeText(this, R.string.toast_informe_idade, Toast.LENGTH_SHORT).show()
            } else {
                val peso = editPeso.text.toString().toDouble()
                var idade = editIdade.text.toString().toInt()
                calcularIngestaoDiaria.CalcularTotalMl(peso, idade)
                val formatar = NumberFormat.getNumberInstance(Locale("pt", "BR"))
                formatar.isGroupingUsed = false
                txtResultadoml.text =
                    "${formatar.format(calcularIngestaoDiaria.ResultMl())} ml"
            }
        }

        icRedefinirDados.setOnClickListener {

            var alertDialog = AlertDialog.Builder(this)
            alertDialog
                .setTitle(R.string.dialog_title)
                .setMessage(R.string.dialog_desc)
                .setPositiveButton("Ok",
                    { _, _ ->
                        editPeso.setText("")
                        editIdade.setText("")
                        txtResultadoml.text = ""
                    })
            alertDialog.setNegativeButton("Cancelar", { _, _ -> })
            val dialog = alertDialog.create()
            dialog.show()
        }

    }

    private fun iniciarComponentes() {
        editPeso = findViewById(R.id.edit_peso)
        editIdade = findViewById(R.id.edit_idade)
        btCalcular = findViewById(R.id.bt_calcular)
        txtResultadoml = findViewById(R.id.txt_tesultado_ml)
        icRedefinirDados = findViewById(R.id.ic_redefinir)
    }

}
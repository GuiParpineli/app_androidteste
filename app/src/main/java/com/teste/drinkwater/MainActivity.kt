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

    private lateinit var edit_peso: EditText
    private lateinit var edit_idade: EditText
    private lateinit var bt_calcular: Button
    private lateinit var txt_resultadoml: TextView
    private lateinit var ic_redefinir_dados: ImageView

    private lateinit var calcularIngestaoDiaria: CalcularIngestaoDiaria

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()
        IniciarComponentes()
        calcularIngestaoDiaria = CalcularIngestaoDiaria()

        bt_calcular.setOnClickListener {
            if (edit_peso.text.toString().isEmpty()) {
                Toast.makeText(this, R.string.toast_informe_peso, Toast.LENGTH_SHORT).show()
            } else if (edit_idade.text.toString().isEmpty()) {
                Toast.makeText(this, R.string.toast_informe_idade, Toast.LENGTH_SHORT).show()
            } else {
                val peso = edit_peso.text.toString().toDouble()
                var idade = edit_idade.text.toString().toInt()
                calcularIngestaoDiaria.CalcularTotalMl(peso, idade)
                val formatar = NumberFormat.getNumberInstance(Locale("pt", "BR"))
                formatar.isGroupingUsed = false
                txt_resultadoml.text =
                    "${formatar.format(calcularIngestaoDiaria.ResultMl()).toString()} ml"
            }
        }

        ic_redefinir_dados.setOnClickListener {

            var alertDialog = AlertDialog.Builder(this)
            alertDialog
                .setTitle(R.string.dialog_title)
                .setMessage(R.string.dialog_desc)
                .setPositiveButton("Ok", { dialogInterface, i ->
                    edit_peso.setText("")
                    edit_idade.setText("")
                    txt_resultadoml.text = ""
                })
            alertDialog.setNegativeButton("Cancelar", { dialogInterface, i -> })
            val dialog = alertDialog.create()
            dialog.show()
        }

    }

    private fun IniciarComponentes() {
        edit_peso = findViewById(R.id.edit_peso)
        edit_idade = findViewById(R.id.edit_idade)
        bt_calcular = findViewById(R.id.bt_calcular)
        txt_resultadoml = findViewById(R.id.txt_tesultado_ml)
        ic_redefinir_dados = findViewById(R.id.ic_redefinir)
    }

}
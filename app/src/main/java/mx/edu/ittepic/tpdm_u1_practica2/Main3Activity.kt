package mx.edu.ittepic.tpdm_u1_practica2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import java.io.BufferedReader
import java.io.InputStreamReader

class Main3Activity : AppCompatActivity() {
    var txtPrueba : TextView ?= null
    var file1 : RadioButton ?= null
    var file2 : RadioButton ?= null
    var file3 : RadioButton ?= null

    var abrir : Button ?= null

    var gridLay : GridLayout ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        txtPrueba = findViewById(R.id.textPrueba)
        file1 = findViewById(R.id.file1)
        file2 = findViewById(R.id.file2)
        file3 = findViewById(R.id.file3)

        abrir = findViewById(R.id.abrir)

        gridLay = findViewById(R.id.showGL)

        abrir?.setOnClickListener { abrirArchivo() }

    }

    fun abrirArchivo(){
        val abrirArchivo = BufferedReader( InputStreamReader( openFileInput("archivo1.txt")))
        var cadena = ""
        cadena = abrirArchivo.readLine()
        txtPrueba?.setText(cadena)
        var header = cadena.split("-")[0].toCharArray()

        if(header[0] == 'V'){
            gridLay?.columnCount = header[1].toInt()
            gridLay?.rowCount = 0
        }
        if(header[0] == 'M'){
            gridLay?.columnCount = header[1].toInt()
            gridLay?.rowCount = header[3].toInt()
        }

        var arrayData = cadena.split("-")[1].split(",")
        Toast.makeText(this, "size $arrayData.size", Toast.LENGTH_SHORT).show()
        /*(0..arrayData.size).forEach {
            var etiqueta = TextView(this)
            etiqueta.setText(arrayData[it].toString());
            var parametrosGrid = GridLayout.LayoutParams(
                GridLayout.spec(0,GridLayout.CENTER),
                GridLayout.spec(it,GridLayout.CENTER)
            )
            gridLay?.addView(etiqueta,parametrosGrid)
        }*/



    }
}

package mx.edu.ittepic.tpdm_u1_practica2

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class Main3Activity : AppCompatActivity() {
    var txtPrueba : TextView ?= null
    var file1 : RadioButton ?= null
    var file2 : RadioButton ?= null
    var file3 : RadioButton ?= null

    var abrir : Button ?= null

    var gridLay : GridLayout ?= null
    var btnExportar : Button ?= null

    var mensaje = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        txtPrueba = findViewById(R.id.textPrueba)
        file1 = findViewById(R.id.file1)
        file2 = findViewById(R.id.file2)
        file3 = findViewById(R.id.file3)

        abrir = findViewById(R.id.abrir)

        gridLay = findViewById(R.id.showGL)
        btnExportar = findViewById(R.id.exportar)
        abrir?.setOnClickListener { abrirArchivo() }
        btnExportar?.setOnClickListener { export() }
    }

    fun abrirArchivo(){
        var cadena = ""
        if(file1?.isChecked == true){
            val abrirArchivo = BufferedReader( InputStreamReader( openFileInput("archivo1.txt")))
            cadena = abrirArchivo.readLine()
            txtPrueba?.setText(cadena)
        }
        if(file2?.isChecked == true){
            val abrirArchivo = BufferedReader( InputStreamReader( openFileInput("archivo2.txt")))
            cadena = abrirArchivo.readLine()
            txtPrueba?.setText(cadena)
        }
        if(file3?.isChecked == true){
            val abrirArchivo = BufferedReader( InputStreamReader( openFileInput("archivo3.txt")))
            cadena = abrirArchivo.readLine()
            txtPrueba?.setText(cadena)
        }

        var header = cadena.split("-")[0].toCharArray()
        //Toast.makeText(this,header[0]+" , "+header[1]+" , "+header[2]+" , "+header[3], Toast.LENGTH_SHORT).show()
        if(header[0] == 'V'){
            gridLay?.columnCount = header[1].toInt()
            gridLay?.rowCount = 1
            llenarPorVector(cadena)
        }
        if(header[0] == 'M'){
            gridLay?.columnCount = header[1].toInt()
            gridLay?.rowCount = header[3].toInt()
            llenarPorMatriz(cadena,header[1].toInt(),header[3].toInt())
        }





    }

    fun llenarPorMatriz(cadena : String,i : Int, j: Int){
        var arrayData = cadena.split("-")[1].split(",")
        mensaje = cadena.split("-")[1]
        var tam = arrayData.size
        tam--
        var col = 0
        var row = 0
        Toast.makeText(this,"i: $i and j: $j", Toast.LENGTH_SHORT).show()
        (0..tam).forEach {
            var etiqueta = TextView(this)

            etiqueta.setText(arrayData[it].toString())
            row++
            if(row == j){
                row = 0
                col++
            }
            var parametrosGrid = GridLayout.LayoutParams(
                GridLayout.spec(row,GridLayout.CENTER),
                GridLayout.spec(col,GridLayout.CENTER)
            )
            gridLay?.addView(etiqueta,parametrosGrid)
        }
    }

    fun llenarPorVector(cadena : String){
        var arrayData = cadena.split("-")[1].split(",")
        var tam = arrayData.size
        tam--
        (0..tam).forEach {
            var etiqueta = TextView(this)
            etiqueta.setText(arrayData[it].toString());
            var parametrosGrid = GridLayout.LayoutParams(
                GridLayout.spec(0,GridLayout.CENTER),
                GridLayout.spec(it,GridLayout.CENTER)
            )
            gridLay?.addView(etiqueta,parametrosGrid)
        }
    }

    fun export(){
        var guardarArchivo = OutputStreamWriter(openFileOutput("exportado.csv", Activity.MODE_PRIVATE))
        guardarArchivo.write(mensaje)
        guardarArchivo.flush()
        guardarArchivo.close()
        Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show()
        Toast.makeText(this,"Datos guardados con exito, exportado.csv generado.", Toast.LENGTH_SHORT).show()
    }
}

package mx.edu.ittepic.tpdm_u1_practica2

import android.app.ActionBar
import android.app.Activity
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.updateLayoutParams
import java.io.OutputStreamWriter

class Main2Activity : AppCompatActivity() {

    var celdillas : Int ?= 0
    var renglones : Int ?= 0

    var valor : String ?= null
    var celdilla : Int ?= 0
    var renglon : Int ?= 0

    var opcion  : String ?= ""
    var gridLay : GridLayout ?= null
    var txtV : EditText ?= null
    var txtC : EditText ?= null
    var txtR : EditText ?= null
    var lblR : TextView ?= null

    var btnRegresar : Button ?= null
    var btnCapturar : Button ?= null
    var btnArchivar : Button ?= null

    var rb1 : RadioButton ?= null
    var rb2 : RadioButton ?= null
    var rb3 : RadioButton ?= null

    var cadena : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        gridLay = findViewById(R.id.gridLayout1)
        lblR = findViewById(R.id.lblDatoRen)

        txtR = findViewById(R.id.txtPosR)
        txtV = findViewById(R.id.txtValor)
        txtC = findViewById(R.id.txtPosC)

        btnCapturar = findViewById(R.id.capturar)
        btnRegresar = findViewById(R.id.regresar)
        btnArchivar = findViewById(R.id.archivar)

        rb1 = findViewById(R.id.rb1)
        rb2 = findViewById(R.id.rb2)
        rb3 = findViewById(R.id.rb3)

        opcion = intent.extras?.getString("opcion")

        if(opcion == "vector") {
            celdillas = intent.extras?.getInt("numCell")
            lblR?.visibility = View.GONE
            txtR?.visibility = View.GONE
            gridLay?.rowCount = 1
            gridLay?.columnCount = celdillas!!
            cadena += "V$celdillas-"
            Toast.makeText(this, "Vector de tamaño $celdillas ", Toast.LENGTH_SHORT).show()
        }

        if(opcion == "matriz"){
            celdillas = intent.extras?.getInt("numCell")
            renglones = intent.extras?.getInt("numRen")
            gridLay?.rowCount = renglones!!
            gridLay?.columnCount = celdillas!!
            cadena += "M$celdillas"+"x$renglones-"
            Toast.makeText(this,"Matriz de tamaño $celdillas x $renglones ", Toast.LENGTH_SHORT).show()
        }

        btnRegresar?.setOnClickListener { finish() }

        btnCapturar?.setOnClickListener {
            Toast.makeText(this,"boton2", Toast.LENGTH_SHORT).show()
            if(opcion == "vector"){
                Toast.makeText(this,"insertando vector", Toast.LENGTH_SHORT).show()

                valor = txtV?.text.toString()
                celdilla = txtC?.text.toString().toInt()

                generarLabel(valor,celdilla,0)
            }

            if(opcion == "matriz"){
                valor = txtV?.text.toString()
                celdilla = txtC?.text.toString().toInt()
                renglon = txtR?.text.toString().toInt()

                generarLabel(valor,celdilla,renglon)
            }



        }

        btnArchivar?.setOnClickListener { archivar() }

    }

    fun generarLabel(v: String?, c :Int?, r :Int?){
        var etiqueta = TextView(this)
        etiqueta.setText(v);

        var parametrosGrid = GridLayout.LayoutParams(
            GridLayout.spec(r!!,GridLayout.CENTER),
            GridLayout.spec(c!!,GridLayout.CENTER)
        )
        cadena += v+","
        gridLay?.addView(etiqueta,parametrosGrid)
    }

    fun archivar(){
        Toast.makeText(this,cadena, Toast.LENGTH_SHORT).show()
        if(rb1?.isChecked == true){
            guardarDatos("archivo1.txt",cadena)
        }
        if(rb2?.isChecked == true){
            guardarDatos("archivo2.txt",cadena)
        }
        if(rb3?.isChecked == true){
            guardarDatos("archivo3.txt",cadena)
        }
    }

    fun guardarDatos(nombreArchivo: String, texto : String ){
        var guardarArchivo = OutputStreamWriter(openFileOutput(nombreArchivo, Activity.MODE_PRIVATE))
        guardarArchivo.write(texto)
        guardarArchivo.flush()
        guardarArchivo.close()
        Toast.makeText(this,"Datos guardados con exito", Toast.LENGTH_SHORT).show()
    }

}

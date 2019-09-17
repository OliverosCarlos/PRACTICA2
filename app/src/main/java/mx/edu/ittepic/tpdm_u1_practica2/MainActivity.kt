package mx.edu.ittepic.tpdm_u1_practica2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    var lista : ListView ?= null
    var lblRen : TextView ?= null
    var txtCell : TextView ?= null
    var txtRen : EditText ?= null
    var rbVec : RadioButton ?= null
    var rbMat : RadioButton ?= null
    var rbGro : RadioGroup ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lblRen = findViewById(R.id.lblRenglon)
        txtCell = findViewById(R.id.txtCeldilla)
        txtRen = findViewById(R.id.txtRenglon)
        rbGro = findViewById(R.id.rbGroup)
        rbVec = findViewById(R.id.rbVector)
        rbMat = findViewById(R.id.rbMatriz)
        lista = findViewById(R.id.listViewOption)
        
        rbVec?.setOnCheckedChangeListener { compoundButton, b ->
            lblRen?.visibility = View.VISIBLE
            txtRen?.visibility = View.VISIBLE
        }

        rbMat?.setOnCheckedChangeListener { compoundButton, b ->
            lblRen?.visibility = View.GONE
            txtRen?.visibility = View.GONE
        }

        lista?.setOnItemClickListener { adapterView, view, i, l ->

            when(i){
                0 -> capturarDatos()
                1 -> mostrarValores()
                2 -> showInfo()
                3 -> salir()
            }
        }
    }

    fun capturarDatos(){

        var otraACtivity = Intent(this, Main2Activity::class.java)


        if(rbVec?.isChecked==true){
            if(notifiVector()) {
                var celdillas = txtCell?.text.toString().toInt()
                otraACtivity.putExtra("opcion","vector")
                otraACtivity.putExtra("numCell",celdillas)
                startActivity(otraACtivity)

            }

            else Toast.makeText(this,"insertar dato", Toast.LENGTH_SHORT).show()
        }
        if(rbMat?.isChecked==true){
            if(notifiMatriz()){
                var celdillas = txtCell?.text.toString().toInt()
                var renglones = txtRen?.text.toString().toInt()
                otraACtivity.putExtra("opcion","matriz")
                otraACtivity.putExtra("numCell",celdillas)
                otraACtivity.putExtra("numRen",renglones)
                startActivity(otraACtivity)
            }

            else Toast.makeText(this,"insertar dato", Toast.LENGTH_SHORT).show()
        }

    }

    fun mostrarValores(){
        var otraACtivity = Intent(this, Main3Activity::class.java)

        startActivity(otraACtivity)

    }
    fun showInfo(){

    }
    fun salir(){
        finish()

    }

    fun notifiVector():Boolean{
        var numCelldillas = txtCell?.text.toString()
        if(numCelldillas != "") return true
        else return false
    }
    fun notifiMatriz():Boolean{
        var numCelldillas = txtCell?.text.toString()
        var numRenglones = txtRen?.text.toString()
        if(numCelldillas != "")
            if(numRenglones != "") return true
            else return false
        else return false


    }
}

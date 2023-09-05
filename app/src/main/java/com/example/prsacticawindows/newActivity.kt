package com.example.prsacticawindows

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewParent
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.annotation.Nullable
import androidx.core.net.toUri
import com.example.prsacticawindows.databinding.ActivityNewBinding
import com.example.prsacticawindows.modelo.Estudiantes
import com.example.prsacticawindows.modelo.Paises
import com.example.prsacticawindows.modelo.Saludos
import com.example.prsacticawindows.modelo.Canciones
import org.json.JSONObject
import java.net.URL
import java.net.URLEncoder

class newActivity : AppCompatActivity() {
    private lateinit var binding :ActivityNewBinding
    private lateinit var ListaPaises : List<Paises>
    private lateinit var ListaSaludos : List<Saludos>
    private lateinit var ListaEstudiantes:List<Estudiantes>
    private lateinit var ListaCanciones: List<Canciones>

    data class songInfo(val Date: String, val category: String, val avarage_rating: Float)

    override fun onCreate(savedInstanceState: Bundle?) {

        ListaSaludos = loadSaoludosAct()
        ListaPaises = loadPaisesAct()
        ListaEstudiantes = loadEstudiantesAct()
        ListaCanciones = loadCancionesAct()
        super.onCreate(savedInstanceState)
        binding = ActivityNewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = Intent(baseContext, finalmechi::class.java)
         val spinner : Spinner = findViewById(R.id.spinner3)
         val spinner2: Spinner = findViewById(R.id.spinner2)
         val cancionesList = ListaCanciones.map { it.name }
         val menuSubMenu = ListaCanciones.map{songInfo(it.date, it.category, it.avarage_rating)}
         val paisMenu = ListaPaises.map{it.nombre_pais}
         val saludosMenu = ListaSaludos.map{it.name}
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, cancionesList)
        val adapter2 = ArrayAdapter(this, android.R.layout.simple_list_item_2, menuSubMenu)
        var adapter4 = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,paisMenu)
        var adapter5 = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,saludosMenu)
        binding.imageView4.adapter = adapter
        spinner.adapter = adapter4
        spinner2.adapter = adapter5
        binding.imageView4.setOnItemClickListener(){parent, view, position, id ->
            val selectedCancion = ListaCanciones[position]
            binding.textView5.text = "${selectedCancion.category}"
            binding.textView6.text = "${selectedCancion.date}"

            intent.putExtra("date",selectedCancion.date)
            intent.putExtra("category", selectedCancion.category)
        }

        binding.button2.setOnClickListener(){

            startActivity(intent)
        }

        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                val selectedSaludos = ListaSaludos[p2]
                binding.textView5.text = "${selectedSaludos.greeting}"
                binding.textView6.text = "${selectedSaludos.name}"
                intent.putExtra("idioma", selectedSaludos.name)
                intent.putExtra("saludo", selectedSaludos.greeting)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {


            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedPais = ListaPaises[p2]
                binding.textView5.text = "${selectedPais.sigla}"
                binding.textView6.text = "${selectedPais.capital}"
                
                intent.putExtra("campoNuevo", selectedPais.sigla)
                intent.putExtra("campoNuevo2", selectedPais.capital)
            }

            override fun onNothingSelected(p0: AdapterView<*>?){

            }
        }

    }

     private fun loadSaoludosAct(): List<Saludos>{

        val opcionSaludos = mutableListOf<Saludos>()

        var json_string = baseContext.assets.open("saludos.json").bufferedReader().use { it.readText() }
        var json = JSONObject(json_string)
        var saludosJSONArray = json.getJSONArray("saludos")
        for(i in 0 until saludosJSONArray.length()){

            var jsonObject = saludosJSONArray.getJSONObject(i)
            var name = jsonObject.getString("name")
            var greeting = jsonObject.getString(("greeting"))
            var saludo = Saludos(name, greeting)
            opcionSaludos.add(saludo)
        }

        return opcionSaludos
    }

    fun loadPaisesAct(): List<Paises>{

        val opcionPaises = mutableListOf<Paises>()

        var json_string = baseContext.assets.open("paises.json").bufferedReader().use {it.readText()}
        var json = JSONObject(json_string)
        var saludosJSONArray = json.getJSONArray("paises")
        for(i in 0 until saludosJSONArray.length()){

            var jsonObject = saludosJSONArray.getJSONObject(i)
            var capital = jsonObject.getString("capital")
            var nombre_pais = jsonObject.getString("nombre_pais")
            var nombre_pais_int = jsonObject.getString("nombre_pais_int")
            var sigla = jsonObject.getString("sigla")
            var pais = Paises(capital, nombre_pais, nombre_pais_int, sigla)

            opcionPaises.add(pais)

        }

        return opcionPaises
    }

    fun loadEstudiantesAct(): List<Estudiantes>{

        val opcionEstudiantes = mutableListOf<Estudiantes>()

        var json_string = baseContext.assets.open("estudiantes.json").bufferedReader().use { it.readText() }
        var json = JSONObject(json_string)
        var cancionesJSONArray = json.getJSONArray("estudiantes")
        for(i in 0 until cancionesJSONArray.length()){

            var jsonObject = cancionesJSONArray.getJSONObject(i)
            var nombre = jsonObject.getString("nombre")
            var nota = jsonObject.getString("nota").toInt()
            var estudiante = Estudiantes(nombre, nota)

            opcionEstudiantes.add(estudiante)
        }

        return opcionEstudiantes
    }

    fun loadCancionesAct(): List<Canciones>{

        val opcionCanciones = mutableListOf<Canciones>()

        var json_string = baseContext.assets.open("canciones.json").bufferedReader().use{it.readText()}
        var json = JSONObject(json_string)
        var cancionesJson = json.getJSONArray("canciones")
        for(i in 0 until cancionesJson.length()){

            var jsonObject = cancionesJson.getJSONObject(i)
            var artist = jsonObject.getString("Artist")
            var category = jsonObject.getString("Category")
            var name = jsonObject.getString("Name")
            var Stringurl = jsonObject.getString("URL")
            var url = URL(Stringurl)
            var date = jsonObject.getString("Date")
            var year = jsonObject.getString("Year").toInt()
            var avarage_Rating = jsonObject.getString("Average Rating").toFloat()
            var rating = jsonObject.getString("Ratings").toInt()
            var reviews = jsonObject.getString("Reviews")
            var cancion=Canciones(artist,category,name,url,date,year,avarage_Rating,rating,reviews)
            opcionCanciones.add(cancion)

        }

        return opcionCanciones
    }
}



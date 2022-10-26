package com.example.practica1.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.practica1.R
import com.example.practica1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var operacion: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        //binding.cardFormulario.isVisible = false
        setContentView(binding.root)

        val operacionesArray = resources.getStringArray(R.array.operaciones)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, operacionesArray)
        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                operacion = operacionesArray[position]
                setImage()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }

    fun setImage(){
        when(operacion){
            "Triangulo" -> {
                binding.imageFunction.setImageResource(R.drawable.triangulo)
            }
            "Elipse" -> {
                binding.imageFunction.setImageResource(R.drawable.elipse)
            }
            "Rectangulo" -> {
                binding.imageFunction.setImageResource(R.drawable.rectangulo)
            }

        }

    }



        fun click(view: View) {
            with(binding) {
                val h: Float
                val b: Float

                when {
                    eTNumberB.text.isEmpty() -> {
                        eTNumberB.error = "Inserta un valor"
                        Toast.makeText(
                            this@MainActivity,
                            "Por favor llene todos los campos",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    eTNumberB.text.isEmpty() -> {
                        eTNumberB.error = "Inserta un valor"
                        Toast.makeText(
                            this@MainActivity,
                            "Por favor llene todos los campos",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    else -> {
                        h = binding.eTNumberH.text.toString().toFloat()
                        b = binding.eTNumberB.text.toString().toFloat()
                        calcularAreaElipse(h, b)

                        when(operacion){
                            "Triangulo" -> {
                                calularAreaTriangulo(h,b)
                            }
                            "Elipse" -> {
                                calularAreaTriangulo(h,b)
                            }
                            "Rectangulo" -> {
                                cacularAreaRectangulo(h,b)
                            }

                        }

                    }
                }
            }
        }

        fun calularAreaTriangulo(a: Float, b: Float) {
            val area = (a * b) / 2
            val bundle = Bundle()

            bundle.apply {
                putString("figura", resources.getString(R.string.triangulo))
                bundle.putFloat("result", area)
            }

            val intent = Intent(this@MainActivity, ResultActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        fun cacularAreaRectangulo(a: Float, b: Float) {
            val area = a * b
            val bundle = Bundle()
            bundle.apply {
                putString("figura", resources.getString(R.string.rectangulo))
                bundle.putFloat("result", area)
            }

            val intent = Intent(this@MainActivity, ResultActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        fun calcularAreaElipse(a: Float, b: Float) {
            val area = a * b * Math.PI
            val bundle = Bundle()

            bundle.apply {
                putString("figura", resources.getString(R.string.elipse))
                bundle.putFloat("result", area.toFloat())
            }

            val intent = Intent(this@MainActivity, ResultActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

package com.example.onverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    fun convert10to16(text10 : String) : String{
        var int10 = text10.toLong()

        var text16 = ""

        while(int10 > 0){
            val t = (int10 % 16)

            if(t < 10){
                text16 = t.toString() + text16
            }
            else{
                when(t){
                    10.toLong() -> text16 = 'A' + text16
                    11.toLong() -> text16 = 'B' + text16
                    12.toLong() -> text16 = 'C' + text16
                    13.toLong() -> text16 = 'D' + text16
                    14.toLong() -> text16 = 'E' + text16
                    15.toLong() -> text16 = 'F' + text16
                }
            }
            int10 = int10/16
        }
        return text16
    }

    fun convert16to10(text16 : String) : String{
        var _text16 = text16
        var int10 = 0.0

        var i = 0
        while (_text16 != ""){
            val t = _text16.substring(_text16.length - 1)

            val regex = Regex("[0-9]")
            if(t.matches(regex)){
                int10 += t.toLong() * (16.0).pow(i)
            }
            else{
                when(t){
                    "A" -> int10 += 10 * (16.0).pow(i)
                    "B" -> int10 += 11 * (16.0).pow(i)
                    "C" -> int10 += 12 * (16.0).pow(i)
                    "D" -> int10 += 13 * (16.0).pow(i)
                    "E" -> int10 += 14 * (16.0).pow(i)
                    "F" -> int10 += 15 * (16.0).pow(i)
                }
            }
            _text16 = _text16.substring(0, _text16.length - 1)
            i++
        }

        if(int10.toFloat() <= Long.MAX_VALUE) {
            return int10.toLong().toString()
        }
        else{
            return ""
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val et10 = findViewById<EditText>(R.id.et10)
        val et16 = findViewById<EditText>(R.id.et16)

        val butConvert10to16 = findViewById<Button>(R.id.buttonConvert10to16)
        val butConvert16to10 = findViewById<Button>(R.id.buttonConvert16to10)

        val test = true
        if(test) {
            et10.setText(Long.MAX_VALUE.toString(), TextView.BufferType.EDITABLE)
            et16.setText(convert10to16(Long.MAX_VALUE.toString()), TextView.BufferType.EDITABLE)
        }

        butConvert10to16.setOnClickListener { view ->
            val text10 = et10.text.toString()
            val regex = Regex("([0-9])+")

            if(text10.matches(regex) && text10.toFloat() <= Long.MAX_VALUE ) {
                et16.setText(convert10to16(text10), TextView.BufferType.EDITABLE)
            }
            else if(text10.toFloat() > Long.MAX_VALUE){
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder
                    .setMessage("Введено большое число")
                    .setTitle("Ошибка")
                    .setPositiveButton("ОК", { dialog, id ->  dialog.cancel() })
                val dialog: AlertDialog = builder.create()
                dialog.show()

                Log.d("","Введено большое число")
            }
            else{
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder
                    .setMessage("Введено некорректное число")
                    .setTitle("Ошибка")
                    .setPositiveButton("ОК", { dialog, id ->  dialog.cancel() })
                val dialog: AlertDialog = builder.create()
                dialog.show()

                Log.d("","Введено некорректное число")
            }
        }

        butConvert16to10.setOnClickListener { view ->
            val text16 = et16.text.toString().uppercase()
            val regex = Regex("([0-9ABCDEF])+")

            if(text16.matches(regex)) {
                val text10 = convert16to10(text16)
                if(text10 != "") {
                    et10.setText(convert16to10(text16), TextView.BufferType.EDITABLE)
                }
                else{
                    val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                    builder
                        .setMessage("Введено большое число")
                        .setTitle("Ошибка")
                        .setPositiveButton("ОК", { dialog, id ->  dialog.cancel() })
                    val dialog: AlertDialog = builder.create()
                    dialog.show()

                    Log.d("","Введено большое число")
                }
            }
            else {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder
                    .setMessage("Введено некорректное число")
                    .setTitle("Ошибка")
                    .setPositiveButton("ОК", { dialog, id ->  dialog.cancel() })
                val dialog: AlertDialog = builder.create()
                dialog.show()

                Log.d("","Введено некорректное число")
            }
        }
    }
}
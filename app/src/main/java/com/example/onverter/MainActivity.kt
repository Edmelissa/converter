package com.example.onverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    private fun convert10to16(long10 : Long) : String{
        return long10.toString(16).uppercase()
    }

    private fun convert16to10(text16 : String) : Long?{
        return  text16.toLongOrNull(16)
    }

    private fun createAlertDialog(text : String){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder
            .setMessage(text)
            .setTitle("Ошибка")
            .setPositiveButton("ОК", { dialog, id ->  dialog.cancel() })
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText10 = findViewById<EditText>(R.id.editText10)
        val editText16 = findViewById<EditText>(R.id.editText16)

        val buttonConvert10To16 = findViewById<Button>(R.id.buttonConvert10To16)
        val buttonConvert16To10 = findViewById<Button>(R.id.buttonConvert16To10)

        val errorIncorrectValue = getResources().getString(R.string.error_incorrect_value)

        val test = true
        if(test) {
            editText10.setText(Long.MAX_VALUE.toString())
            editText16.setText(convert10to16(Long.MAX_VALUE))
        }

        buttonConvert10To16.setOnClickListener {
            val long10 = editText10.text.toString().toLongOrNull()

            if(long10 != null) {
                editText16.setText(convert10to16(long10))
            }
            else {
                createAlertDialog(errorIncorrectValue)
                Log.d("IncorrectValue",errorIncorrectValue)
            }
        }

        buttonConvert16To10.setOnClickListener {
            val text16 = editText16.text.toString().uppercase()

            val long10 = convert16to10(text16)

            if(long10 != null) {
                editText10.setText(long10.toString())
            }
            else {
                createAlertDialog(errorIncorrectValue)
                Log.d("IncorrectValue",errorIncorrectValue)
            }
        }
    }
}
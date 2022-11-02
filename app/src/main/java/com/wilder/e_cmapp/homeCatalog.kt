package com.wilder.e_cmapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar

class homeCatalog : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_catalog)

        val fab:View = findViewById(R.id.fab41)
        fab.setOnClickListener{View->

            val positiveButton = { dialog: DialogInterface, int: Int ->
                val goPay = Intent(this, toPayActivity::class.java)
                startActivity(goPay)
            }

            val negativeButton={dialog:DialogInterface, int:Int->
                Toast.makeText(this, "Enjoy you purchase", Toast.LENGTH_LONG).show()
            }


            val alertGoToPay = AlertDialog.Builder(this)
                .setTitle(R.string.title_alrt_shopcar)
                .setMessage(R.string.msj_alrt_shopcar)
                .setPositiveButton(R.string.btnposi_alrt_shopcar, positiveButton)
                .setNegativeButton(R.string.btnnega_alrt_shopcar, negativeButton)
                .create().show()

        }


    }
}


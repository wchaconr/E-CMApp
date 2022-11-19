package com.wilder.e_cmapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class homeCatalog : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_catalog)

        val cardTitle : Array<String> = resources.getStringArray(R.array.cardTitles)
        val cardImage : Array<String> = resources.getStringArray(R.array.cardImages)

        val adapter = GridItemAdapter(cardTitle, cardImage)
        val gridLayout = GridLayoutManager(this,2)
        val gridItems = findViewById<RecyclerView>(R.id.gridItems)
        gridItems.layoutManager= gridLayout
        gridItems.adapter = adapter


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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bar, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId)  {
        R.id.ayudar->{
            val goHelpActivity = Intent(this, HelpActivity::class.java)
            startActivity(goHelpActivity)
            true
        }
        R.id.cuenta->{
            val getUsername = intent
            val userNameS: String? = getUsername?.getStringExtra("getnickname")
            val intent: Intent = Intent(this, Account2Activity::class.java)
            intent.putExtra("getnickname", userNameS)
            startActivity(intent)
            true
        }
        R.id.tema->{
            Toast.makeText(this, "Day / Nigth", Toast.LENGTH_SHORT).show()
            true
        }
        R.id.configuracion->{
            Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show()
            true
        }
        R.id.home->{
            val redirecHome = Intent(this, homeCatalog::class.java)
            startActivity(redirecHome)
            true
        }
        else->{
            super.onOptionsItemSelected(item)
        }


    }
}


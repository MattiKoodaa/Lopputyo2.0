package com.example.lopputyo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lopputyo.databinding.ActivityDoneBinding
import com.example.lopputyo.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class Done : AppCompatActivity() {

    lateinit var binding: ActivityDoneBinding
    lateinit var toggle: ActionBarDrawerToggle

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDoneBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            toggle= ActionBarDrawerToggle(this@Done, drawerLayout, R.string.open, R.string.close)
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()


            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            navView.setNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.firstItem->{
                        Toast.makeText(this@Done,"Avataan muistio", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@Done, MainActivity::class.java)
                        startActivity(intent)
                    }
                    R.id.secondItem->{
                        Toast.makeText(this@Done,"Avataan valmiit", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@Done, Done::class.java)
                        startActivity(intent)
                    }
                    R.id.thirdItem->{
                        Toast.makeText(this@Done,"Avataan ilmoitukset", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@Done, Notify::class.java)
                        startActivity(intent)
                    }
                }
                true
            }

        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            true
        }
        return super.onOptionsItemSelected(item)
    }
}
package com.example.lopputyo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lopputyo.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            toggle= ActionBarDrawerToggle(this@MainActivity, drawerLayout, R.string.open, R.string.close)
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()


            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            navView.setNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.firstItem->{
                        Toast.makeText(this@MainActivity,"Avataan muistio",Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@MainActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
                    R.id.secondItem->{
                        Toast.makeText(this@MainActivity,"Avataan valmiit",Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@MainActivity, Done::class.java)
                        startActivity(intent)
                    }
                    R.id.thirdItem->{
                        Toast.makeText(this@MainActivity,"Avataan ilmoitukset",Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@MainActivity, Notify::class.java)
                        startActivity(intent)
                    }
                }
                true
            }

        }
        todoAdapter = TodoAdapter(mutableListOf())

        rvTodoItems.adapter = todoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager (this)

        btnAddTodo.setOnClickListener{
            val todoTitle = etTodoTitle.text.toString()
            if(todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                etTodoTitle.text.clear()
            }
        }

        fun visibleBtn(isChecked: Boolean){
            btnDeleteDoneTodos.isVisible=isChecked
        }

        btnDeleteDoneTodos.setOnClickListener{
            todoAdapter.deleteDoneTodos()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            true
        }
        return super.onOptionsItemSelected(item)
    }
}
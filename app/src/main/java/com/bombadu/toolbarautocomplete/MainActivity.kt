package com.bombadu.toolbarautocomplete

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    private lateinit var list: MutableList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val appBarLayout = findViewById<AppBarLayout>(R.id.app_bar_layout)
        appBarLayout.tag = "Test"



        list = mutableListOf()
        list.add("Michael")
        list.add("Cate")
        list.add("Steve")
        list.add("Gwen")
        list.add("Carla")
        list.add("Charlie")

        setupToolbar(list)






        }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.main_menu, menu)
            return super.onCreateOptionsMenu(menu)
        }




}



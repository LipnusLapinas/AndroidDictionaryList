package com.hfad.listfiles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private val defns = ArrayList<String>()
    private lateinit var myAdapter : ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupList()
        definitions_list.setOnItemClickListener { _, _, index, _ ->
            //todo
            defns.removeAt(index)
            myAdapter.notifyDataSetChanged()
        }
    }

    private fun setupList() {

        // pick a random word
        val list = ArrayList<String>()
        list.add("Hello")
        list.add("Goodbye")
        list.add("Marty")

        val rand = Random()
        val index = rand.nextInt(list.size)
        val word = list[index]

        the_word.text = word

        // pick random definitions for the word
        defns.add("a greeting")
        defns.add("something you say when you are done talking")
        defns.add("a dude")
        defns.add("another name for a duck")
        defns.add("the President")
        defns.add("nothing at all")
        defns.shuffle()

        myAdapter = ArrayAdapter<String>(this,
            android.R.layout.simple_expandable_list_item_1, defns)

        definitions_list.adapter = myAdapter
    }
}

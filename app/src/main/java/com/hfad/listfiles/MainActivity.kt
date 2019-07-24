package com.hfad.listfiles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private var wordToDefn = HashMap<String, String>()
    private val words = ArrayList<String>()
    private val defns = ArrayList<String>()
    private lateinit var myAdapter : ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        readDictionaryFile()
        Log.e("pries setup", "po readDictionary")
        setupList()
        Log.e("pries on click", "po setuplist")

        definitions_list.setOnItemClickListener { _, _, index, _ ->
            //todo
            defns.removeAt(index)
            Log.e("onclick", "po remove")
            myAdapter.notifyDataSetChanged()
            Log.e("onclick", "po visko")
        }
    }

    private fun readDictionaryFile() {
        val reader = Scanner (resources.openRawResource(R.raw.grewords))
        while(reader.hasNextLine()) {
            val line = reader.nextLine()
            Log.e("Marty", "the line is $line")
            val pieces = line.split("-")
            if (pieces.size >= 2) {
                words.add(pieces[0])
                Log.e("word", "idejo kazka i word")
                wordToDefn.put(pieces[0], pieces[1])
            }
        }
    }

    private fun setupList() {

        Log.e("setuplist", "pradejo setuplist")

        // pick a random word
        if (words.size <= 2) {
            return
        }
        Log.e("setuplist", "po ifo")
        val rand = Random()
        Log.e("setuplist", "po Random")
        val index = rand.nextInt(words.size)
        val word = words[index]
        the_word.text = word
        Log.e("setuplist", "po the_word")
        // pick random definitions for the word
        defns.clear()
        defns.add(wordToDefn[word]!!)
        words.shuffle()
        Log.e("setuplist", "po words shuffle")
        for (otherWord in words.subList(0, 5)) {
            if (otherWord == word || defns.size == 5) {
                continue
            }
            defns.add(wordToDefn[otherWord]!!)
        }
        Log.e("setuplist", "po for")
        defns.shuffle()

        myAdapter = ArrayAdapter<String>(this,
            android.R.layout.simple_expandable_list_item_1, defns)

        Log.e("setuplist", "po myAdapter")

        definitions_list.adapter = myAdapter

        Log.e("setuplist", "setup galas")
    }
}

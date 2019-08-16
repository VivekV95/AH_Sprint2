package com.vivekvishwanath.ah_sprint2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

// Step 1: Make the Puppy model
// Step 2: Process raw puppy data
// Step 3: Design RV item layout
// Step 4: Created adapter for RecyclerView
// Step 5: Finish RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PuppyRepository.createPuppyList()

        puppy_list_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PuppyListAdapter(PuppyRepository.puppyList)
        }

        favorites_button.setOnClickListener {
            val favorites = getFavorites()
            val i = 0
        }
    }

    fun getFavorites(): String {
        var favoritesString = ""
        for (puppy in PuppyRepository.puppyList) {
            if (puppy.isFavorite) favoritesString += "${puppy.breed}, "
        }

        return favoritesString
    }
}

package com.example.junior

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.junior.until.Fruit
import com.example.junior.until.FruitListViewAdapter
import kotlinx.android.synthetic.main.activity_third.*

class ListViewActivity : AppCompatActivity() {

    private val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        //val data = listOf<String>("one", "two", "three", "four", "five", "six")
        //val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data)
        //listView.adapter = adapter

        initFruit()

        val adapter = FruitListViewAdapter(
            this,
            R.layout.fruit_item,
            fruitList
        )
        listView.adapter = adapter
        listView.setOnItemClickListener { parent, view, position, id ->
            val fruit = fruitList[position]
            Toast.makeText(this, fruit.name, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initFruit() {
        repeat(2) {
            fruitList.add(
                Fruit(
                    "Apple",
                    R.drawable.apple_pic
                )
            )
            fruitList.add(
                Fruit(
                    "Banana",
                    R.drawable.banana_pic
                )
            )
            fruitList.add(
                Fruit(
                    "Orange",
                    R.drawable.orange_pic
                )
            )
            fruitList.add(
                Fruit(
                    "Watermelon",
                    R.drawable.watermelon_pic
                )
            )
            fruitList.add(
                Fruit(
                    "Pear",
                    R.drawable.pear_pic
                )
            )
            fruitList.add(
                Fruit(
                    "Grape",
                    R.drawable.grape_pic
                )
            )
            fruitList.add(
                Fruit(
                    "Pineapple",
                    R.drawable.pineapple_pic
                )
            )
            fruitList.add(
                Fruit(
                    "Strawberry",
                    R.drawable.strawberry_pic
                )
            )
            fruitList.add(
                Fruit(
                    "Cherry",
                    R.drawable.cherry_pic
                )
            )
            fruitList.add(
                Fruit(
                    "Mango",
                    R.drawable.mango_pic
                )
            )
        }
    }
}
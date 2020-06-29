package com.example.junior

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.junior.until.Fruit
import com.example.junior.until.FruitRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : AppCompatActivity() {

    private val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        //initFruit()
        //val layoutManager = LinearLayoutManager(this)
        //layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        initFruit2()
        val layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        val adapter =
            FruitRecyclerViewAdapter(fruitList)
        recyclerView.adapter = adapter
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

    private fun initFruit2() {
        repeat(2) {
            fruitList.add(
                Fruit(
                    getRandomLengthName("Apple"),
                    R.drawable.apple_pic
                )
            )
            fruitList.add(
                Fruit(
                    getRandomLengthName("Banana"),
                    R.drawable.banana_pic
                )
            )
            fruitList.add(
                Fruit(
                    getRandomLengthName("Orange"),
                    R.drawable.orange_pic
                )
            )
            fruitList.add(
                Fruit(
                    getRandomLengthName("Watermelon"),
                    R.drawable.watermelon_pic
                )
            )
            fruitList.add(
                Fruit(
                    getRandomLengthName("Pear"),
                    R.drawable.pear_pic
                )
            )
            fruitList.add(
                Fruit(
                    getRandomLengthName("Grape"),
                    R.drawable.grape_pic
                )
            )
            fruitList.add(
                Fruit(
                    getRandomLengthName("Pineapple"),
                    R.drawable.pineapple_pic
                )
            )
            fruitList.add(
                Fruit(
                    getRandomLengthName("Strawberry"),
                    R.drawable.strawberry_pic
                )
            )
            fruitList.add(
                Fruit(
                    getRandomLengthName("Cherry"),
                    R.drawable.cherry_pic
                )
            )
            fruitList.add(
                Fruit(
                    getRandomLengthName("Mango"),
                    R.drawable.mango_pic
                )
            )
        }
    }

    private fun getRandomLengthName(name: String): String {
        val n = (1..20).random()
        val builder = StringBuilder()
        repeat(n) {
            builder.append(name)
        }
        return builder.toString()
    }
}
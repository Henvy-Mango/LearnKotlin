package com.example.junior.until

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.junior.R

/**
 * FileName: Fruit
 * Author: Naomi
 * Date: 2020/6/29 18:26
 * Description:
 */
class Fruit(val name: String, val imageId: Int) {

}

class FruitListViewAdapter(activity: Activity, val resourceId: Int, data: List<Fruit>) :
    ArrayAdapter<Fruit>(activity, resourceId, data) {

    inner class ViewHolder(val fruitName: TextView, val fruitImageView: ImageView)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder

        //使用converView进行性能优化
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(resourceId, parent, false)
            val fruitName: TextView = view.findViewById(R.id.fruitName)
            val fruitImage: ImageView = view.findViewById(R.id.fruitImage)
            viewHolder = ViewHolder(fruitName, fruitImage)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val fruit = getItem(position)

        fruit?.let {
            viewHolder.fruitName.text = fruit.name
            viewHolder.fruitImageView.setImageResource(fruit.imageId)
        }
        return view
    }
}
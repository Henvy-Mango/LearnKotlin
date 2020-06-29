package com.example.junior

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_first.*

class FirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        var i = 0
        button.setOnClickListener {
            //Toast.makeText(this, editText.text.toString(), Toast.LENGTH_SHORT).show()

            //when (i % 2) {
            //    0 -> image0.setImageResource(R.drawable.p1)
            //    1 -> image0.setImageResource(R.drawable.p0)
            //}

            //when (progress.visibility) {
            //    View.VISIBLE -> progress.visibility = View.GONE
            //    else -> progress.visibility = View.VISIBLE
            //}

            //progress.progress += 10

            AlertDialog.Builder(this).apply {
                setTitle("Dialog Info")
                setMessage("This is an Information!")
                setCancelable(false)
                setPositiveButton("OK") { dialog, which -> }
                setNegativeButton("CANCEL") { dialog, which -> }
                show()
            }
            i++
        }
    }
}

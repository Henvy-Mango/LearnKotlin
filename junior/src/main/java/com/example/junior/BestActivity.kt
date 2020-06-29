package com.example.junior

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.junior.until.Msg
import com.example.junior.until.MsgAdapter
import kotlinx.android.synthetic.main.activity_best.*

class BestActivity : AppCompatActivity() {

    private val msgList = ArrayList<Msg>()

    private var adapter: MsgAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_best)
        initMsg()
        val layoutManager = LinearLayoutManager(this)
        BestRecyclerView.layoutManager = layoutManager
        adapter = MsgAdapter(msgList)
        BestRecyclerView.adapter = adapter
        bt_send.setOnClickListener {
            val content = BestEditText.text.toString()
            if (content.isNotEmpty()) {
                val msg = Msg(content, Msg.TYPE_SENT)
                msgList.add(msg)
                adapter?.notifyItemInserted(msgList.size - 1)
                BestRecyclerView.scrollToPosition(msgList.size - 1)
                BestEditText.setText("")
            }
        }
    }

    private fun initMsg() {
        val msg1 = Msg("Hello guy.", Msg.TYPE_RECEIVED)
        msgList.add(msg1)
        val msg2 = Msg("Hello. Who is that?", Msg.TYPE_SENT)
        msgList.add(msg2)
        val msg3 = Msg("This is Tom. Nice talking to you. ", Msg.TYPE_RECEIVED)
        msgList.add(msg3)
        msgList.add(Msg("Hello! Naomi", Msg.TYPE_RECEIVED))
        msgList.add(Msg("Hello!", Msg.TYPE_SENT))
        msgList.add(Msg("So... Are u ok?", Msg.TYPE_RECEIVED))
    }
}
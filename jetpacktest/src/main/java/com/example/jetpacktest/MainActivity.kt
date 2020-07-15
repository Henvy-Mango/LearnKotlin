package com.example.jetpacktest

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.work.BackoffPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.jetpacktest.Entity.Book
import com.example.jetpacktest.Entity.User
import com.example.jetpacktest.WorkManager.SimpleWorker
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt("count_reserved", 0)

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(countReserved)
        ).get(MainViewModel::class.java)

        bt_add.setOnClickListener {
            //viewModel.counter++
            //refreshCounter()
            viewModel.add()
        }

        bt_clear.setOnClickListener {
            //viewModel.counter = 0
            //refreshCounter()
            viewModel.clear()
        }

        bt_getNum.setOnClickListener {
            val userId = (1..10000).random().toString()
            viewModel.getUser(userId)
        }

        refreshCounter()

        //viewModel.counter.observe(this, Observer { count ->
        //    infoText.text = count.toString()
        //})

        viewModel.counter.observe(this) { count ->
            infoText.text = count.toString()
        }

        viewModel.user.observe(this) { user ->
            infoText.text = user.name
        }

        lifecycle.addObserver(MyObserver(lifecycle))

        val userDao = AppDatabase.getDatabase(this).userDao()
        val bookDao = AppDatabase.getDatabase(this).bookDao()
        val user1 = User("Tom", 11, "nmsl")
        val user2 = User("Jerry", 32, "qqqq")
        val book1 = Book("Stepping", 600, "op")

        addUserBtn.setOnClickListener {
            thread {
                user1.id = userDao.insertUser(user1)
                user2.id = userDao.insertUser(user2)
                book1.id = bookDao.insertBook(book1)
            }
        }

        updateUserBtn.setOnClickListener {
            thread {
                user1.age = 46
                userDao.updateUser(user1)
            }
        }

        deleteUserBtn.setOnClickListener {
            thread {
                //userDao.deleteUserByName("Tom")
                userDao.deleteAllUser()
            }
        }

        queryUserBtn.setOnClickListener {
            thread {
                for (user in userDao.loadAllUsers()) {
                    Log.d("Naomi", user.toString())
                }

                for (book in bookDao.loadAllBook()) {
                    Log.d("Naomi", book.toString())
                }
            }
        }

        doWorkBtn.setOnClickListener {
            val request = OneTimeWorkRequest.Builder(SimpleWorker::class.java)
                .setInitialDelay(5, TimeUnit.SECONDS)
                .setBackoffCriteria(BackoffPolicy.LINEAR, 5, TimeUnit.SECONDS)
                .build()
            WorkManager.getInstance(this).enqueue(request)

            WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.id).observe(this) {
                if (it.state == WorkInfo.State.SUCCEEDED)
                    Log.d("Naomi", "Success in Simple Work")
            }
        }

    }

    private fun refreshCounter() {
        infoText.text = viewModel.counter.toString()
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            //putInt("count_reserved", viewModel.counter)
            putInt("count_reserved", viewModel.counter.value ?: 0)
        }
    }
}



package com.example.todoapp160419029.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.todoapp160419029.model.Todo
import com.example.todoapp160419029.model.TodoDatabase
import com.example.todoapp160419029.util.buildDb
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class DetailTodoViewModel(application:  Application):AndroidViewModel(application), CoroutineScope {
    val todoLD = MutableLiveData<Todo>()
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun addTodo(todolist: List<Todo>){
        launch {
//            val db = Room.databaseBuilder(
//                getApplication(), TodoDatabase::class.java,
//                "newtododb").build()
            val db = buildDb(getApplication())
            db.todoDao().insertAll(*todolist.toTypedArray())

        }
    }

    fun fetch(uuid:Int) {
        launch {
            val db = buildDb(getApplication())
            todoLD.value =  db.todoDao().selectTodo(uuid)
        }
    }

    fun update(title:String, notes:String, priority:Int, uuid:Int) {
        launch {
            val db = buildDb(getApplication())
            db.todoDao().update(title, notes, priority, uuid)
        }
    }



}
package com.example.todoapp160419029.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.todoapp160419029.model.Todo
import com.example.todoapp160419029.model.TodoDatabase
import com.example.todoapp160419029.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListTodoViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    val todoLD = MutableLiveData<List<Todo>>()
    val todoLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    //Coroutine
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun refresh(){
        loadingLD.value = true
        todoLoadErrorLD.value = false
        launch {
//            val db = Room.databaseBuilder(
//                getApplication(),
//                TodoDatabase::class.java, "newtododb").build()
            val db = buildDb(getApplication())
            todoLD.value = db.todoDao().selectAllTodo()
        }
    }

    //Untuk menghapus sebuah iten dari database
    fun clearTask(todo: Todo) {
        launch {
//            val db = Room.databaseBuilder(
//                getApplication(),
//                TodoDatabase::class.java, "newtododb").build()
            val db = buildDb(getApplication())
            db.todoDao().deleteTodo(todo)

            todoLD.value = db.todoDao().selectAllTodo()
        }
    }

    fun checkTask(id: Int){
        launch {
            val db = buildDb(getApplication())
            db.todoDao().checkTheTask(id)

            todoLD.value = db.todoDao().selectAllTodo()
        }
    }

}


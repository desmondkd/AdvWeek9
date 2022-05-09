package com.example.todoapp160419029.view

import android.view.View
import android.widget.CompoundButton
import com.example.todoapp160419029.model.Todo

interface TodoCheckedChangeListener {
    fun onCheckChanged(cb: CompoundButton, isChecked:Boolean, obj: Todo)
}

interface TodoEditClickListener {
    fun onEditClick(view: View)
}

interface TodoPriorityClickListener {
    fun onRadioPriorityClick(v:View, priority:Int, obj:Todo)
}
interface TodoSaveChangesClickListener {
    fun onTodoSaveChangesClick(v: View, obj: Todo)
}


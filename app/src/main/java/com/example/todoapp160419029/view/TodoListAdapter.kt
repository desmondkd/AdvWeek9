package com.example.todoapp160419029.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp160419029.R
import com.example.todoapp160419029.databinding.LayoutItemTodoBinding
import com.example.todoapp160419029.model.Todo
import kotlinx.android.synthetic.main.layout_item_todo.view.*

class TodoListAdapter(val todoList:ArrayList<Todo> , val adapterOnClick : (Todo) -> Unit):RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder>(),TodoCheckedChangeListener, TodoEditClickListener
{
    class TodoListViewHolder(var view:LayoutItemTodoBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.layout_item_todo, parent, false)
        val view = DataBindingUtil.inflate<LayoutItemTodoBinding>(inflater, R.layout.layout_item_todo, parent, false)
        return  TodoListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int)
    {
        val todo = todoList[position]
        holder.view.todo = todo
        holder.view.checkBoslistener = this
        holder.view.editListener = this

        with(holder.view){
            checkTask.text = todo.title
        }

        holder.view.checkTask.setOnCheckedChangeListener{ compoundButton, b ->
            adapterOnClick(todoList[position])
        }

        holder.view.buttonEdit.setOnClickListener {
            val action =
                TodoListFragmentDirections.actionEditTodoFragment(todoList[position].uuid)

            Navigation.findNavController(it).navigate(action)
        }

        holder.view.checkTask.setOnCheckedChangeListener { compoundButton, isChecked ->
            if(isChecked == true) {
                adapterOnClick(todoList[position])
            }
        }


    }

    override fun getItemCount() = todoList.size

    fun updateTodoList(newTodoList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }

    override fun onCheckChanged(cb: CompoundButton, isChecked: Boolean, obj: Todo) {
        if(isChecked) {
            adapterOnClick(obj)

        }
    }

    override fun onEditClick(view: View) {
        val uuid = view.tag.toString().toInt()
        val action = TodoListFragmentDirections.actionEditTodoFragment(uuid)

        Navigation.findNavController(view).navigate(action)
    }


}
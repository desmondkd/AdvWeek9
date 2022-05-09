package com.example.todoapp160419029.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.todoapp160419029.R
import com.example.todoapp160419029.model.Todo
import com.example.todoapp160419029.viewmodel.DetailTodoViewModel
import kotlinx.android.synthetic.main.fragment_create_todo.*

class CreateTodoFragment : Fragment() {
    private lateinit var viewModel:DetailTodoViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this).get(DetailTodoViewModel::class.java)

        buttonAdd.setOnClickListener {
            var radio =
                view.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId)

            var todo = Todo(textTitle.text.toString(),
                textNotes.text.toString(), radio.tag.toString().toInt())

            val list = listOf(todo)
            viewModel.addTodo(list)
            Toast.makeText(view.context, "Todo created", Toast.LENGTH_LONG).show()
            Navigation.findNavController(it).popBackStack()
        }
    }

}
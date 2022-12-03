package com.example.lopputyo

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter(
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewholder>(){

    class TodoViewholder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewholder {
        return TodoViewholder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos() {
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(
        tvTodoTitle: TextView,
        isChecked: Boolean,
    ) {
        if (isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewholder, position: Int) {
        val curTodo =todos[position]
        holder.itemView.apply {
            tvTodoTitle.text =curTodo.title
            cbDone.isChecked =curTodo.isChecked
            toggleStrikeThrough(tvTodoTitle, curTodo.isChecked,)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvTodoTitle, isChecked,)
                curTodo.isChecked = !curTodo.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}
package app.mkv.finalproject.adapter

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.mkv.finalproject.R
import app.mkv.finalproject.model.Data

class TodoAdapter(private var list: List<Data>) : RecyclerView.Adapter<TodoAdapter.VH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return VH(view)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: VH, position: Int) {
        val data = list[position]

        holder.apply {
            id.text = "ID: ${data.id}"
            userId.text = "User ID: ${data.userId}"
            todo.text = data.title
            if (data.completed) {
                check.isChecked = true
                todo.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            }


            check.setOnClickListener {
                if (check.isChecked) {
                    check.isChecked = true
                    todo.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                } else {
                    check.isChecked = false
                    todo.paintFlags = Paint.ANTI_ALIAS_FLAG
                }
            }


        }


    }

    override fun getItemCount(): Int = list.size


    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id: TextView = itemView.findViewById(R.id.txtId)
        val userId: TextView = itemView.findViewById(R.id.txtUserId)
        val check: CheckBox = itemView.findViewById(R.id.checkBox)
        val todo: TextView = itemView.findViewById(R.id.txtTodo)

    }


}
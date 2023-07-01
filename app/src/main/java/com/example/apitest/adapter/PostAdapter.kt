package com.example.apitest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.apitest.R
import com.example.apitest.RecyclerViewInterface
import com.example.apitest.models.DataListItem


@Suppress("UNREACHABLE_CODE")
class PostAdapter(
    private val postList: List<DataListItem>,
    private val recyclerViewInterface: RecyclerViewInterface
) : RecyclerView.Adapter<PostAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)

        val holder = MyViewHolder(itemView)
        itemView.setOnClickListener {
            val position = holder.adapterPosition
            recyclerViewInterface.onClick(position)
        }
        return MyViewHolder(itemView)

    }


    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val post = postList[position]
        holder.name.text = post.title

    }

    class MyViewHolder(itemView: View) : ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tvTitle)


    }
}


package com.example.apitest


import android.content.Intent
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


class PostDetailFragment(private val postTitle: String, private val postBody: String,
                         private val postId: String,private val postUserId: String  ) : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val title: TextView = view.findViewById(R.id.tvPostTitle)
        val body: TextView = view.findViewById(R.id.tvPostBody)
        val id: TextView = view.findViewById(R.id.tvId)
        val userId: TextView = view.findViewById(R.id.userId)

        title.text = postTitle
        body.text = postBody
        id.text = postId
        userId.text = postUserId

    }
}


package com.example.apitest

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apitest.adapter.PostAdapter
import com.example.apitest.models.DataListItem
import com.example.apitest.viewmodel.DataViewModel
import com.example.apitest.viewmodel.ViewModelFactory


class PostFragment(
    private val postApplication: PostApplication,
    private val supportFragmentManager: FragmentManager
) : Fragment(), RecyclerViewInterface {

    lateinit var viewModel: DataViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repo = postApplication.repo
        viewModel = ViewModelProvider(this, ViewModelFactory(repo))[DataViewModel::class.java]

        viewModel.data.observe(viewLifecycleOwner, Observer {
            setAdapter(it, view)
        })
        viewModel.dbData.observe(viewLifecycleOwner, Observer {
            setAdapter(it, view)
        })


    }

    private fun setAdapter(postList: List<DataListItem>, view: View) {
        val itemAdapter = PostAdapter(postList, this)
        val recyclerView: RecyclerView = view.findViewById(R.id.recycleView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        recyclerView.adapter = itemAdapter
    }

    private fun replaceFragment(title: String, body: String,id: String,userId: String) {
        val fragmentManager = supportFragmentManager

        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.postFragment, PostDetailFragment(title, body,id,userId))

        fragmentTransaction.commit()
    }

    override fun onClick(position: Int) {
        if (viewModel.data.value != null) {
            viewModel.data.value?.get(position + 1)?.let{
                replaceFragment(it.title,it.body,it.id.toString(),it.userId.toString())
            }

        } else {
            viewModel.dbData.value?.get(position + 1)?.let{
                replaceFragment(it.title,it.body,it.id.toString(),it.userId.toString())
            }
        }
    }
}


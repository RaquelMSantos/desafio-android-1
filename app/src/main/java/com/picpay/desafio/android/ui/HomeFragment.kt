package com.picpay.desafio.android.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.network.response.User
import com.picpay.desafio.android.UserListAdapter
import com.picpay.desafio.android.repository.LiveDataResult
import com.picpay.desafio.android.ui.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    private val homeViewModel: HomeViewModel by viewModel()
    private val adapter by lazy { UserListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        progressBar = view.findViewById(R.id.user_list_progress_bar)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserve()
    }

    private fun initView() {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun initObserve() {
        homeViewModel.user.observe(viewLifecycleOwner, Observer<LiveDataResult<MutableList<User>>>{
            when (it?.status) {
                LiveDataResult.STATUS.ERROR -> {
                    requestError()
                }

                LiveDataResult.STATUS.SUCCESS -> {
                    requestSuccess(it.data as ArrayList<User>)
                }

                LiveDataResult.STATUS.LOADING -> {
                    requestLoading()
                }
            }
        })
    }

    private fun requestSuccess(arrayList: ArrayList<User>) {
        adapter.users = arrayList
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }

    private fun requestLoading(){
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    private fun requestError() {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.GONE
    }
}
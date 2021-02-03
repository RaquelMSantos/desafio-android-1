package com.picpay.desafio.android.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.FragmentHomeBinding
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.util.LiveDataState
import com.picpay.desafio.android.ui.UserListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private val adapter: UserListAdapter = UserListAdapter()
    private lateinit var homeFragmentBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return homeFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserve()
    }

    private fun initView() {
        homeFragmentBinding.recyclerView.adapter = adapter
        homeFragmentBinding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initObserve() {
        homeViewModel.user.observe(viewLifecycleOwner, Observer{
            when (it?.status) {
                LiveDataState.STATUS.ERROR -> {
                    requestError(it.error)
                }

                LiveDataState.STATUS.SUCCESS -> {
                    requestSuccess(it.data as ArrayList<User>)
                }

                LiveDataState.STATUS.LOADING -> {
                    requestLoading()
                }
            }
        })
    }

    private fun requestSuccess(userList: ArrayList<User>) {
        adapter.updateList(userList)
        homeFragmentBinding.userListProgressBar.visibility = View.GONE
        homeFragmentBinding.recyclerView.visibility = View.VISIBLE
    }

    private fun requestError(error: Throwable?) {
        homeFragmentBinding.userListProgressBar.visibility = View.GONE
        homeFragmentBinding.recyclerView.visibility = View.GONE
        Toast.makeText(context, error?.message, Toast.LENGTH_LONG).show()
    }

    private fun requestLoading() {
        homeFragmentBinding.userListProgressBar.visibility = View.VISIBLE
        homeFragmentBinding.recyclerView.visibility = View.GONE
    }
}
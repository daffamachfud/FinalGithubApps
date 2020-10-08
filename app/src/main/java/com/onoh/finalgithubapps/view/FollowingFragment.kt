package com.onoh.finalgithubapps.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.onoh.finalgithubapps.R
import com.onoh.finalgithubapps.adapter.ListUserAdapter
import com.onoh.finalgithubapps.viewmodel.DetailUserViewModel
import kotlinx.android.synthetic.main.fragment_following.*

/**
 * A simple [Fragment] subclass.
 */
class FollowingFragment : Fragment() {

    private lateinit var adapter: ListUserAdapter
    private lateinit var detailViewModel : DetailUserViewModel

    companion object{
        private val ARG_USERNAME = "username"

        fun newInstance(username:String): FollowingFragment{
            val fragment = FollowingFragment()
            val bundle = Bundle()
            bundle.putString(ARG_USERNAME,username)
            fragment.arguments=bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ListUserAdapter()
        adapter.notifyDataSetChanged()
        rv_following.layoutManager = LinearLayoutManager(activity)
        rv_following.adapter =adapter

        val username = arguments?.getString(ARG_USERNAME)

        detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailUserViewModel::class.java)
        following_loading.visibility = View.VISIBLE
        username?.let { detailViewModel.setFollowingUser(it) }
        activity?.let {
            detailViewModel.getDataFollowUser().observe(it, Observer { userItems ->
                if (userItems != null) {
                    adapter.setData(userItems)
                    following_loading.visibility = View.INVISIBLE
                }
            })
        }
    }

}

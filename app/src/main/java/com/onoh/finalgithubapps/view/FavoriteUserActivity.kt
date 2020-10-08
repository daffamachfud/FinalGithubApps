package com.onoh.finalgithubapps.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.onoh.finalgithubapps.R
import com.onoh.finalgithubapps.adapter.FavoriteUserAdapter
import com.onoh.finalgithubapps.adapter.ListUserAdapter
import com.onoh.finalgithubapps.viewmodel.FavoriteUserViewModel
import kotlinx.android.synthetic.main.activity_detail_user.*
import kotlinx.android.synthetic.main.activity_favorite_user.*

class FavoriteUserActivity : AppCompatActivity() {

    private lateinit var mFavoriteUserViewModel: FavoriteUserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_user)

        setSupportActionBar(toolbar_detail)
        toolbar_favorite.navigationIcon = ContextCompat.getDrawable(this,R.drawable.ic_back)
        toolbar_favorite.setNavigationOnClickListener { onBackPressed() }

        val adapter = FavoriteUserAdapter()
        rv_favorite.adapter = adapter
        rv_favorite.layoutManager = LinearLayoutManager(this)

        mFavoriteUserViewModel = ViewModelProvider(this).get(FavoriteUserViewModel::class.java)
        mFavoriteUserViewModel.getAll.observe(this, Observer {
            adapter.setData(it)
        })

        adapter.setOnFavoriteClickCallback(object : FavoriteUserAdapter.OnFavoriteClickCallback{

            override fun onFavoriteClicked(username: String) {
                val detailIntent = Intent(this@FavoriteUserActivity, DetailUserActivity::class.java)
                detailIntent.putExtra(DetailUserActivity.EXTRA_USER,username)
                startActivity(detailIntent)
            }
        })
    }


}

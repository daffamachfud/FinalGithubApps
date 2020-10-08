package com.onoh.finalgithubapps.view

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.onoh.finalgithubapps.R
import com.onoh.finalgithubapps.adapter.ListUserAdapter
import com.onoh.finalgithubapps.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ListUserAdapter
    private lateinit var userViewModel : UserViewModel

    companion object{
        const val USERNAME = "daffamachfud"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        adapter = ListUserAdapter()
        adapter.notifyDataSetChanged()
        rv_user.layoutManager = LinearLayoutManager(this)
        rv_user.adapter =adapter

        userViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(UserViewModel::class.java)
        loading.visibility = View.VISIBLE
        userViewModel.setDataUser(USERNAME)
        userViewModel.getDataUser().observe(this, Observer {
                userItems->
            if(userItems != null){
                adapter.setData(userItems)
                loading.visibility = View.INVISIBLE
            }
        })

        adapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback{
            override fun onItemClicked(username: String) {
                showDetailUser(username)
            }
        })
    }

    private fun apiData(username:String){
        userViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(UserViewModel::class.java)
        loading.visibility = View.VISIBLE
        userViewModel.setDataSearchUser(username)
        userViewModel.getDataUser().observe(this, Observer {
                userItems->
            if(userItems != null){
                adapter.setData(userItems)
                loading.visibility = View.INVISIBLE
            }
        })
    }

    private fun apiDataFull(){
        userViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(UserViewModel::class.java)
        loading.visibility = View.VISIBLE
        userViewModel.setDataUser(USERNAME)
        userViewModel.getDataUser().observe(this, Observer {
                userItems->
            if(userItems != null){
                adapter.setData(userItems)
                loading.visibility = View.INVISIBLE
            }
        })
    }

    private fun showDetailUser(username : String){
        val detailIntent = Intent(this@MainActivity, DetailUserActivity::class.java)
        detailIntent.putExtra(DetailUserActivity.EXTRA_USER,username)
        startActivity(detailIntent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu,menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.menu_search).actionView as SearchView
        val menuSearch = menu.findItem(R.id.menu_search)

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.seach_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                apiData(query)
                return true
            }


            override fun onQueryTextChange(newText: String): Boolean {
                apiData(newText)
                return false
            }



        })
        searchView.setOnCloseListener(object : SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                apiData(USERNAME)
                return true
            }
        })

        menuSearch.setOnActionExpandListener(object : MenuItem.OnActionExpandListener{
            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                apiDataFull()
                return true
            }

            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
               apiDataFull()
                return true
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id == R.id.menu_favorite){
            val favoriteIntent = Intent(this@MainActivity,FavoriteUserActivity::class.java)
            startActivity(favoriteIntent)
        }else if(id == R.id.menu_settings){
            val settingINtent = Intent(this@MainActivity,SettingActivity::class.java)
            startActivity(settingINtent)
        }
        return super.onOptionsItemSelected(item)
    }
}

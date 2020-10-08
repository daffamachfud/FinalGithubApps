package com.onoh.finalgithubapps.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.onoh.finalgithubapps.R
import com.onoh.finalgithubapps.adapter.SectionPageAdapter
import com.onoh.finalgithubapps.model.FavoriteUser
import com.onoh.finalgithubapps.viewmodel.DetailUserViewModel
import com.onoh.finalgithubapps.viewmodel.FavoriteUserViewModel
import kotlinx.android.synthetic.main.activity_detail_user.*

class DetailUserActivity : AppCompatActivity() {

    companion object{ const val EXTRA_USER = "extra_user" }

    private lateinit var username: String
     private var usernames :String? =null
     private var avatar :String? =null
     private var company :String? =null
     private var location :String? = null
     private var statusFavorite :Boolean = false
    private lateinit var favoriteUser:FavoriteUser

    private lateinit var detailUserViewModel : DetailUserViewModel
    private lateinit var favoriteUserViewModel: FavoriteUserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        setSupportActionBar(toolbar_detail)
        toolbar_detail.navigationIcon = ContextCompat.getDrawable(this,R.drawable.ic_back)
        toolbar_detail.setNavigationOnClickListener { onBackPressed() }

        username = intent.getStringExtra(EXTRA_USER) as String
        favoriteUserViewModel = ViewModelProvider(this).get(FavoriteUserViewModel::class.java)

        val sectionPagerAdapter = SectionPageAdapter(this,supportFragmentManager)
        sectionPagerAdapter.username = username
        view_pager.adapter = sectionPagerAdapter
        tabs.setupWithViewPager(view_pager)

        supportActionBar?.elevation = 0f

        getDetailDataUser(username)

        favoriteUserViewModel.isExist(username).observe(this, {
            statusFavorite = it != null
            setStatus(statusFavorite)
        })

        fab_favorite.setOnClickListener{
            if(statusFavorite){
                favoriteUserViewModel.deleteFavoriteUser(username)
                Toast.makeText(this,"User berhasil di hapus dari favorite ! ",Toast.LENGTH_SHORT).show()
                finish()
            }else{
                favoriteUserViewModel.addFavorite(favoriteUser)
                Toast.makeText(this,"User berhasil di tambahkan menjadi favorite ! ",Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    private fun setStatus(status:Boolean){
        if(!status){
            fab_favorite.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.ic_unfavorite))
        }else{
            fab_favorite.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.ic_favorite))
        }
    }


    private fun getDetailDataUser(username:String) {
        detail_loading.visibility = View.VISIBLE
        layout_data_detail.visibility = View.INVISIBLE
        detailUserViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailUserViewModel::class.java)
        detailUserViewModel.setDetailUser(username)
        detailUserViewModel.getDataUser().observe(this, { detailData->
                if(detailData != null){
                    Glide.with(this)
                        .load(detailData[0].avatar)
                        .apply(RequestOptions().override(55,55))
                        .into(img_detail_avatar)
                     usernames = detailData[0].username!!
                     avatar = detailData[0].avatar!!
                     company = detailData[0].company!!
                     location = detailData[0].location!!

                favoriteUser = FavoriteUser(0, usernames!!, avatar!!, company!!, location!!)

                tv_detail_username.text = usernames
                if(company.equals("null")){
                    tv_detail_company.text = "-"
                }else{
                    tv_detail_company.text = company
                }
                if(location.equals("null")){
                    tv_detail_location.text = "-"
                }else{
                    tv_detail_location.text = location
                }

                tv_detail_repo.text = detailData[0].repository.toString()
                val followers = detailData[0].followers.toString()
                val following = detailData[0].following.toString()

                tv_detail_followers.text = resources.getString(R.string.followers,followers)
                tv_detail_following.text = resources.getString(R.string.following,following)


                detail_loading.visibility = View.INVISIBLE
                layout_data_detail.visibility = View.VISIBLE

            }
        })


    }


}

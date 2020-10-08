package com.onoh.finalgithubapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.onoh.finalgithubapps.R
import com.onoh.finalgithubapps.model.FavoriteUser
import kotlinx.android.synthetic.main.item_user.view.*

class FavoriteUserAdapter:RecyclerView.Adapter<FavoriteUserAdapter.MyViewHolder>() {

    private var favoriteList = emptyList<FavoriteUser>()

    private var onFavoriteClickCallback: FavoriteUserAdapter.OnFavoriteClickCallback? = null

    fun setOnFavoriteClickCallback(onFavoriteClickCallback: FavoriteUserAdapter.OnFavoriteClickCallback) {
        this.onFavoriteClickCallback = onFavoriteClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteUserAdapter.MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false))
    }

    override fun onBindViewHolder(holder: FavoriteUserAdapter.MyViewHolder, position: Int) {
        holder.bind(favoriteList[position])
    }

    override fun getItemCount(): Int = favoriteList.size

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(favoriteUser: FavoriteUser ){
            with(itemView){
                Glide.with(itemView.context)
                    .load(favoriteUser.columnAvatar)
                    .apply(RequestOptions().override(55,55))
                    .into(user_avatar)

                tv_username.text = favoriteUser.columnUsername

                itemView.setOnClickListener { onFavoriteClickCallback?.onFavoriteClicked(favoriteUser.columnUsername) }
            }
        }
    }

    fun setData(favoriteUser: List<FavoriteUser>){
        this.favoriteList = favoriteUser
        notifyDataSetChanged()
    }

    interface OnFavoriteClickCallback {
        fun onFavoriteClicked(username: String)
    }
}
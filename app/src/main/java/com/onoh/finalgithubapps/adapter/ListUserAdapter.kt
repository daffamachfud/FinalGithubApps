package com.onoh.finalgithubapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.onoh.finalgithubapps.R
import com.onoh.finalgithubapps.model.User
import kotlinx.android.synthetic.main.item_user.view.*

class ListUserAdapter : RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {
    private val mData = ArrayList<User>()

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setData(items: ArrayList<User>){
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUserAdapter.ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListUserAdapter.ListViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int = mData.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(user : User){
            with(itemView){
                Glide.with(itemView.context)
                    .load(user.avatar)
                    .apply(RequestOptions().override(55,55))
                    .into(user_avatar)

                tv_username.text = user.username

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(user.username) }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(username: String)
    }
}
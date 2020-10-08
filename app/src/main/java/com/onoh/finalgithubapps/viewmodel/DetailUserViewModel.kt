package com.onoh.finalgithubapps.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.onoh.finalgithubapps.model.DetailUser
import com.onoh.finalgithubapps.model.User
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception

class DetailUserViewModel : ViewModel() {

    val listUserData = MutableLiveData<ArrayList<User>>()
    val listDetailUser = MutableLiveData<ArrayList<DetailUser>>()

    fun setDetailUser(username:String){
        val asyncClient = AsyncHttpClient()
        val url = "https://api.github.com/users/$username"
        asyncClient.addHeader("Authorization", "token 01978fda0f4e1604f2d423e01bf0afd7311ec000")
        asyncClient.addHeader("User-Agent", "request")
        asyncClient.get(url, object : AsyncHttpResponseHandler() {

            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?) {
                val listUser = ArrayList<DetailUser>()
                val result = String(responseBody!!)
                Log.d("onGetData",result)
                try{
                    val items = JSONObject(result)
                        val usernamee = items.getString("login")
                        val avatar = items.getString("avatar_url")
                        val company = items.getString("company")
                        val location = items.getString("location")
                        val repo = items.getInt("public_repos")
                        val follower = items.getInt("followers")
                        val following = items.getInt("following")

                        val detailUser = DetailUser(
                            usernamee,
                            avatar,
                            company,
                            location,
                            repo,
                            follower,
                            following
                        )
                        listUser.add(detailUser)

                    listDetailUser.postValue(listUser)
                }catch(e: Exception){
                    e.printStackTrace()
                }
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable?) {
                Log.d("Exception", error?.message.toString())
            }
        })
    }

    fun setFollowingUser(username:String){
        val asyncClient = AsyncHttpClient()
        val url = "https://api.github.com/users/$username/following"
        asyncClient.addHeader("Authorization", "token 01978fda0f4e1604f2d423e01bf0afd7311ec000")
        asyncClient.addHeader("User-Agent", "request")
        asyncClient.get(url, object : AsyncHttpResponseHandler() {

            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?) {
                val listUser = ArrayList<User>()
                val result = String(responseBody!!)
                Log.d("onGetData",result)
                try{
                    val items = JSONArray(result)
                    for(i in 0 until items.length()) {
                        val item = items.getJSONObject(i)
                        val usernamee = item.getString("login")
                        val avatar = item.getString("avatar_url")

                        val user = User(
                            usernamee,
                            avatar
                        )
                        listUser.add(user)
                    }
                    listUserData.postValue(listUser)
                }catch(e: Exception){
                    e.printStackTrace()
                }
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable?) {
                Log.d("Exception", error?.message.toString())
            }
        })
    }

    fun setFollowerUser(username:String){
        val asyncClient = AsyncHttpClient()
        val url = "https://api.github.com/users/$username/followers"
        asyncClient.addHeader("Authorization", "token 01978fda0f4e1604f2d423e01bf0afd7311ec000")
        asyncClient.addHeader("User-Agent", "request")
        asyncClient.get(url, object : AsyncHttpResponseHandler() {

            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?) {
                val listUser = ArrayList<User>()
                val result = String(responseBody!!)
                Log.d("onGetData",result)
                try{
                    val items = JSONArray(result)
                    for(i in 0 until items.length()) {
                        val item = items.getJSONObject(i)
                        val usernamee = item.getString("login")
                        val avatar = item.getString("avatar_url")

                        val user = User(
                            usernamee,
                            avatar
                        )
                        listUser.add(user)
                    }
                    listUserData.postValue(listUser)
                }catch(e: Exception){
                    e.printStackTrace()
                }
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable?) {
                Log.d("Exception", error?.message.toString())
            }
        })

    }

    fun getDataUser(): LiveData<ArrayList<DetailUser>> {
        return listDetailUser
    }

    fun getDataFollowUser(): LiveData<ArrayList<User>>{
        return listUserData
    }


}
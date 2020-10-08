package com.onoh.finalgithubapps.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.onoh.finalgithubapps.model.User
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception

class UserViewModel : ViewModel() {

    val listUsers = MutableLiveData<ArrayList<User>>()

    fun setDataUser(username : String){
        val asyncClient = AsyncHttpClient()
        val url = "https://api.github.com/users?q=$username"
        asyncClient.addHeader("Authorization", "token 01978fda0f4e1604f2d423e01bf0afd7311ec000")
        asyncClient.addHeader("User-Agent", "request")
        asyncClient.get(url, object : AsyncHttpResponseHandler() {

            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?) {
                val listUser = ArrayList<User>()
                val result = String(responseBody!!)
                Log.d("onGetData",result)
                try{
                    val items = JSONArray(result)
                    for(i in 0 until items.length()){
                        val item = items.getJSONObject(i)
                        val usernamee = item.getString("login")
                        val avatar = item.getString("avatar_url")

                        val user = User(
                            usernamee,
                            avatar
                        )
                        listUser.add(user)
                    }
                    listUsers.postValue(listUser)
                }catch(e: Exception){
                    e.printStackTrace()
                }
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable?) {
                Log.d("Exception", error?.message.toString())
            }
        })
    }

    fun setDataSearchUser(username : String){
        val asyncClient = AsyncHttpClient()
        val url = "https://api.github.com/search/users?q=$username"
        asyncClient.addHeader("Authorization", "token 01978fda0f4e1604f2d423e01bf0afd7311ec000")
        asyncClient.addHeader("User-Agent", "request")
        asyncClient.get(url, object : AsyncHttpResponseHandler() {

            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?) {
                val listUser = ArrayList<User>()
                val result = String(responseBody!!)
                Log.d("onGetData",result)
                try{
                    val responObject = JSONObject(result)
                    val items = responObject.getJSONArray("items")

                    for(i in 0 until items.length()){
                        val item = items.getJSONObject(i)
                        val usernamee = item.getString("login")
                        val avatar = item.getString("avatar_url")

                        val user = User(
                            usernamee,
                            avatar
                        )
                        listUser.add(user)
                    }
                    listUsers.postValue(listUser)
                }catch(e: Exception){
                    e.printStackTrace()
                }
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable?) {
                Log.d("Exception", error?.message.toString())
            }
        })
    }

    fun getDataUser(): LiveData<ArrayList<User>> {
        return listUsers
    }

}
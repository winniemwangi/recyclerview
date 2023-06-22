package com.winnie.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.winnie.recyclerview.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding

    var postId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchComments()
        obtainComment()
    }

    fun obtainComment(){
        var extras=intent
        postId=extras.getIntExtra("POST_ID",0)
    }

    fun fetchComments(){
        var apiclient=APIClient.buildApiClient(ApiInterface::class.java)
        var request = apiclient.getComments()

        request.enqueue(object :Callback<List<Comment>>{
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                var comment = response.body()
                if (response.isSuccessful){
                    Log.d("TAG",comment.toString())
                    comment?.let { displayComments(comment) }
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {

            }

        })
    }

    fun displayComments(comment: List<Comment>){
        var adapter = CommentsAdapter(comment)
        binding.rvComments.layoutManager = LinearLayoutManager(this)
        binding.rvComments.adapter = adapter
    }


}
package com.example.gonzalezpauandroidstact19
import RetrofitClient
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Callback
import retrofit2.Response


class Exercici1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercici1)

        val recycler = findViewById<RecyclerView>(R.id.recyclerPosts)
        recycler.layoutManager = LinearLayoutManager(this)
        var call = RetrofitClient.instance.getPosts()

        call.enqueue(object : Callback<List<Post>> {

            override fun onResponse(call: retrofit2.Call<List<Post>>, response: Response<List<Post>>) {
                recycler.adapter = PostAdapter(response.body()!!)
            }

            override fun onFailure(call: retrofit2.Call<List<Post>>, t: Throwable) {
                Toast.makeText(this@Exercici1, "Error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
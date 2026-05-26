package com.example.gonzalezpauandroidstact19

import RetrofitClient
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Exercici2 : AppCompatActivity() {
    private companion object {
        const val TAG = "Exercici2"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercici2)

        val edtId = findViewById<EditText>(R.id.edtId)
        val txtPost = findViewById<TextView>(R.id.txtPost)
        val recycler = findViewById<RecyclerView>(R.id.recyclerComments)
        recycler.layoutManager = LinearLayoutManager(this)

        findViewById<Button>(R.id.btnBuscar).setOnClickListener {
            val id = edtId.text.toString().toInt()
            if (id == 0 || id> 100){
                edtId.error = "Introdueix un numero valid"
                return@setOnClickListener
            }

            // GET POST
            RetrofitClient.instance.getPostById(id).enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    if (response.isSuccessful) {
                        val p = response.body()
                        txtPost.text = "${p?.title}\n\n${p?.body}"
                    }else{
                        Log.e(TAG, "Response failed: ${response.code()}")
                    }

                }

                override fun onFailure(call: Call<Post>, t: Throwable) {
                    Log.e(TAG, "Crida api ha fallat : ${t.message}")
                }
            })
            // GET COMMENTS
            RetrofitClient.instance.getComments(id).enqueue(object : Callback<List<Comment>> {
                override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                    if (response.isSuccessful) {
                        val comments = response.body() ?: emptyList()
                        recycler.adapter = CommentAdapter(comments)
                    }else{
                        Log.e(TAG, "Response failed: ${response.code()}")
                    }

                }

                override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                    Log.e(TAG, "Crida api ha fallat : ${t.message}")
                }
            })
        }
    }
}

package com.example.gonzalezpauandroidstact19

import RetrofitClient
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Exercici2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercici2)

        val edtId = findViewById<EditText>(R.id.edtId)
        val txtPost = findViewById<TextView>(R.id.txtPost)
        val recycler = findViewById<RecyclerView>(R.id.recyclerComments)
        recycler.layoutManager = LinearLayoutManager(this)

        findViewById<Button>(R.id.btnBuscar).setOnClickListener {
            val id = edtId.text.toString().toInt()

            // GET POST
            RetrofitClient.instance.getPostById(id).enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    val p = response.body()!!
                    txtPost.text = "${p.title}\n\n${p.body}"
                }

                override fun onFailure(call: Call<Post>, t: Throwable) {}
            })
        }
    }
}

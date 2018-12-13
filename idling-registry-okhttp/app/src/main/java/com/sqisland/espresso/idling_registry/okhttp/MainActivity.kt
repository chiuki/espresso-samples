package com.sqisland.espresso.idling_registry.okhttp

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : Activity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val nameView = findViewById<TextView>(R.id.name)

    val client = OkHttpClient()

    if (BuildConfig.DEBUG) {
      IdlingResources.registerOkHttp(client)
    }

    val retrofit = Retrofit.Builder()
      .baseUrl("https://api.github.com/")
      .addConverterFactory(MoshiConverterFactory.create())
      .client(client)
      .build()

    val service = retrofit.create<GitHubService>(GitHubService::class.java)
    service.getUser("chiuki").enqueue(object : Callback<User> {
      override fun onResponse(call: Call<User>, response: Response<User>) {
        val user = response.body()
        nameView.text = user!!.name
      }

      override fun onFailure(call: Call<User>, t: Throwable) {
        nameView.text = t.message
      }
    })
  }
}

package com.sqisland.espresso.idling_registry.okhttp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
  @GET("users/{user}")
  fun getUser(@Path("user") user: String): Call<User>
}
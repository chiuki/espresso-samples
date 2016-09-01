package com.sqisland.espresso.idling_resource.okhttp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {
  @GET("users/{user}")
  Call<User> getUser(@Path("user") String user);
}
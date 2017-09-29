package com.sqisland.espresso.idling_resource.okhttp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MainActivity extends Activity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final TextView nameView = findViewById(R.id.name);

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(OkHttpProvider.getOkHttpInstance())
        .build();

    GitHubService service = retrofit.create(GitHubService.class);
    service.getUser("chiuki").enqueue(new Callback<User>() {
      @Override
      public void onResponse(Call<User> call, Response<User> response) {
        User user = response.body();
        nameView.setText(user.name);
      }

      @Override
      public void onFailure(Call<User> call, Throwable t) {
        nameView.setText(t.getMessage());
      }
    });
  }
}
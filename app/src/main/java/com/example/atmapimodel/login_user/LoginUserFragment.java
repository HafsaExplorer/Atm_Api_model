package com.example.atmapimodel.login_user;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.atmapimodel.R;
import com.example.atmapimodel.databinding.FragmentLoginUserBinding;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginUserFragment extends Fragment {

    FragmentLoginUserBinding binding;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      binding = FragmentLoginUserBinding.inflate(getLayoutInflater());

      binding.register.setOnClickListener(view -> {
          Navigation.findNavController(view).navigate(R.id.registerUserFragment);
      });

      sharedPreferences = getActivity().getSharedPreferences("UserDetails", Context.MODE_PRIVATE);

      binding.button.setOnClickListener(view -> {
          String key = "abc#$";
          String email = binding.email.getText().toString().trim();
          String password = binding.password.getText().toString().trim();

          OkHttpClient client = ProvideOkHttpClient();
          Retrofit retrofit = provideRetrofit(client);
          LoginUserService loginUserService = retrofit.create(LoginUserService.class);
          loginUserService.loginUser(key, email , password).enqueue(new Callback<LoginUserResponse>() {
              @Override
              public void onResponse(Call<LoginUserResponse> call, Response<LoginUserResponse> response) {

                  LoginUserResponse loginUserResponse = response.body();
                  sharedPreferences.edit()
                          .putInt("user_id", loginUserResponse.data.id)
                          .putString("user_first_name", loginUserResponse.data.firstName)
                          .putString("user_last_name", loginUserResponse.data.lastName)
                          .putString("user_email", loginUserResponse.data.email)
                          .putString("user_password", loginUserResponse.data.password)
                          .putString("user_amount", loginUserResponse.data.amount)
                          .apply();

                  Toast.makeText(getContext(), loginUserResponse.toString(), Toast.LENGTH_SHORT).show();
                  Navigation.findNavController(view).navigate(R.id.dashboardFragment);
              }
              @Override
              public void onFailure(Call<LoginUserResponse> call, Throwable t) {
                  Toast.makeText(getContext(), "Something went wrong...", Toast.LENGTH_SHORT).show();

              }
          });


      });

        return binding.getRoot();
    }
    public OkHttpClient ProvideOkHttpClient() {
        //to show log statement correctly

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        //headers are use to show less data here according to needs, you can use here Body to show sent and received data
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        return new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.MINUTES)
                .connectTimeout(5, TimeUnit.MINUTES)
                .addInterceptor(logging)
                .build();
    }

    //this function takes the okHttpClient add the url and other details again build it and return retrofit
    public Retrofit provideRetrofit(OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .baseUrl("http://192.168.0.102:8888/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }
}
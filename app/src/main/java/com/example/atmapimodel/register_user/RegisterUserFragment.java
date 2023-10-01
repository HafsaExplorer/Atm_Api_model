package com.example.atmapimodel.register_user;


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
import com.example.atmapimodel.databinding.FragmentRegisterUserBinding;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RegisterUserFragment extends Fragment {

     FragmentRegisterUserBinding binding;
     SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentRegisterUserBinding.inflate(getLayoutInflater());
        sharedPreferences = getActivity().getSharedPreferences("UserDetails", Context.MODE_PRIVATE);


          binding.button.setOnClickListener(view -> {

              String key = "abc#$";
              String firstName = binding.firstName.getText().toString().trim();
              String lastName =   binding.lastName.getText().toString().trim();
              String email = binding.email.getText().toString().trim();
              String password = binding.password.getText().toString().trim();
              String amount =  binding.amount.getText().toString().trim();

              OkHttpClient client = ProvideOkHttpClient();
              Retrofit retrofit = provideRetrofit(client);
              RegisterUserService registerUserService = retrofit.create(RegisterUserService.class);
              registerUserService.calRegister(key, firstName,lastName,email,password,amount).enqueue(new Callback<RegisterUserResponse>() {
                  @Override
                  public void onResponse(Call<RegisterUserResponse> call, Response<RegisterUserResponse> response) {

                      if (response.isSuccessful() && response.body() != null) {
                          RegisterUserResponse registerUserResponse = response.body();
                          if (registerUserResponse.data != null) {
                              sharedPreferences.edit()
                                      .putInt("user_id", registerUserResponse.data.id)
                                      .putString("user_first_name", registerUserResponse.data.firstName)
                                      .putString("user_last_name", registerUserResponse.data.lastName)
                                      .putString("user_email", registerUserResponse.data.email)
                                      .putString("user_password", registerUserResponse.data.password)
                                      .putString("user_amount", registerUserResponse.data.amount)
                                      .apply();
                          }
                              Toast.makeText(getContext(), registerUserResponse.toString(), Toast.LENGTH_SHORT).show();
                          }
                      Navigation.findNavController(view).navigate(R.id.dashboardFragment);
                  }

                  @Override
                  public void onFailure(Call<RegisterUserResponse> call, Throwable t) {

                      Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();

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

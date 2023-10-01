package com.example.atmapimodel.delete_user;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.atmapimodel.R;
import com.example.atmapimodel.databinding.FragmentDeleteUserBinding;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DeleteUserFragment extends Fragment {

    FragmentDeleteUserBinding binding;

    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDeleteUserBinding.inflate(getLayoutInflater());

        sharedPreferences = getActivity().getSharedPreferences("UserDetails", Context.MODE_PRIVATE);

        binding.button.setOnClickListener(view -> {

            String key = "abc#$";
            String id = String.valueOf(sharedPreferences.getInt("user_id", -1));

            OkHttpClient client = ProvideOkHttpClient();
            Retrofit retrofit = provideRetrofit(client);
            DeleteUserService deleteUserService = retrofit.create(DeleteUserService.class);
            deleteUserService.delete(key, id).enqueue(new Callback<DeleteUserResponse>() {
                @Override
                public void onResponse(Call<DeleteUserResponse> call, Response<DeleteUserResponse> response) {
                    DeleteUserResponse deleteUserResponse = response.body();
                    Toast.makeText(getContext(), deleteUserResponse.toString(), Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(view).navigate(R.id.registerUserFragment);
                }

                @Override
                public void onFailure(Call<DeleteUserResponse> call, Throwable t) {
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
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl("http://192.168.0.102:8888/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }
}
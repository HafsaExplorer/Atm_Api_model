package com.example.atmapimodel.withdraw_money;

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
import com.example.atmapimodel.databinding.FragmentWithdrawMoneyBinding;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WithdrawMoneyFragment extends Fragment {

    FragmentWithdrawMoneyBinding binding;
    SharedPreferences sharedPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentWithdrawMoneyBinding.inflate(getLayoutInflater());

        sharedPreferences = getActivity().getSharedPreferences("UserDetails", Context.MODE_PRIVATE);

        binding.button.setOnClickListener(view -> {
            String key = "abc#$";
            String amount = binding.depositAmount.getText().toString().trim();
            String id = String.valueOf(sharedPreferences.getInt("user_id", -1));

            OkHttpClient client = ProvideOkHttpClient();
            Retrofit retrofit = provideRetrofit(client);
            WithdrawMoneyService withdrawMoneyService = retrofit.create(WithdrawMoneyService.class);
            withdrawMoneyService.withdrawMoney(key, id, amount).enqueue(new Callback<WithdrawMoneyResponse>() {
                @Override
                public void onResponse(Call<WithdrawMoneyResponse> call, Response<WithdrawMoneyResponse> response) {

                    WithdrawMoneyResponse withdrawMoneyResponse = response.body();
                    sharedPreferences.edit()
                            .putString("user_amount" , withdrawMoneyResponse.data.amount).apply();

                    Toast.makeText(getContext(), withdrawMoneyResponse.toString(), Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(binding.getRoot()).popBackStack();
                }

                @Override
                public void onFailure(Call<WithdrawMoneyResponse> call, Throwable t) {

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
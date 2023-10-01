package com.example.atmapimodel.withdraw_money;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WithdrawMoneyService {

    @GET("withdraw.php")
    Call<WithdrawMoneyResponse> withdrawMoney(
            @Query("api_key") String key,
            @Query("id") String id,
            @Query("amount") String amount
    );
}

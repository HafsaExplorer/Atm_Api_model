package com.example.atmapimodel.deposit_money;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DepositMoneyService {

    @GET("deposit.php")
    Call<DepositMoneyResponse> depositMoney(
            @Query("api_key") String key,
            @Query("id") String id,
            @Query("amount") String amount
    );
}

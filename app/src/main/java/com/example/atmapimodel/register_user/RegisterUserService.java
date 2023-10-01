package com.example.atmapimodel.register_user;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RegisterUserService {
    @GET("register_users.php")
    Call<RegisterUserResponse> calRegister(
            @Query("api_key") String key,
            @Query("first_name") String firstName,
            @Query("last_name") String lastName,
            @Query("email") String email,
            @Query("password") String password,
            @Query("amount") String amount
    );
}

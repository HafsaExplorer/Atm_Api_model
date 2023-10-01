package com.example.atmapimodel.login_user;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LoginUserService {

    @GET("login_users.php")
    Call<LoginUserResponse> loginUser(
            @Query("api_key") String key,
        @Query("email") String email,
        @Query("password")  String password
    );
}

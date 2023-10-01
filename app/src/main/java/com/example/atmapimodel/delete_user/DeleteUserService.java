package com.example.atmapimodel.delete_user;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DeleteUserService {
    @GET("delete_users.php")
    Call<DeleteUserResponse> delete(
            @Query("api_key") String key,
            @Query("id") String id
    );
}

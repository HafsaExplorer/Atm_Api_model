package com.example.atmapimodel.login_user;

import com.example.atmapimodel.register_user.RegisterUserResponse;
import com.google.gson.annotations.SerializedName;

public class LoginUserResponse {

    @SerializedName("status")
    public Integer status;

    @SerializedName("message")
    public String message;

    @SerializedName("data")
    public Data data;

    @Override
    public String toString() {
        return "RegisterUserResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class Data{

        @SerializedName("id")
        public Integer id;
        @SerializedName("first_name")
        public String firstName;
        @SerializedName("last_name")
        public String lastName;
        @SerializedName("email")
        public String email;
        @SerializedName("password")
        public String password;
        @SerializedName("amount")
        public String amount;


        @Override
        public String toString() {
            return "Data{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", email='" + email + '\'' +
                    ", password='" + password + '\'' +
                    ", amount='" + amount + '\'' +
                    '}';
        }
    }

}

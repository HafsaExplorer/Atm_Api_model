package com.example.atmapimodel.register_user;

import com.google.gson.annotations.SerializedName;

public class RegisterUserResponse {

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
                    "id=" + id +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", email='" + email + '\'' +
                    ", password='" + password + '\'' +
                    ", amount='" + amount + '\'' +
                    '}';
        }
    }

}

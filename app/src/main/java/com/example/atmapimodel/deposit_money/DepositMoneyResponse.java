package com.example.atmapimodel.deposit_money;

import com.example.atmapimodel.login_user.LoginUserResponse;
import com.google.gson.annotations.SerializedName;

public class DepositMoneyResponse {

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
        @SerializedName("amount")
        public String amount;


        @Override
        public String toString() {
            return "Data{" +
                    "amount='" + amount + '\'' +
                    '}';
        }
    }
}

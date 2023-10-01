package com.example.atmapimodel.withdraw_money;

import com.example.atmapimodel.deposit_money.DepositMoneyResponse;
import com.google.gson.annotations.SerializedName;

public class WithdrawMoneyResponse {
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

package com.example.atmapimodel.delete_user;
import com.google.gson.annotations.SerializedName;

public class DeleteUserResponse {
    @SerializedName("status")
    public Integer status;

    @SerializedName("message")
    public String message;


    @Override
    public String toString() {
        return "DeleteUserResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}

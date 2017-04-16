package com.example.kunalsingh.busmgmt.api.services;

import com.example.kunalsingh.busmgmt.models.Student;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

import retrofit2.*;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by kunalsingh on 13/04/17.
 */

public interface APIService {

        @GET("sign_in")
        Call<Student> signInStudent(@Query("username") String username , @Query("password")  String password) ;

        @FormUrlEncoded
        @POST("update")
        Call<Student> updateStudent(@Field("access_token") String access_token , @Field("name") String name , @Field("fathers_name") String fathers_name ,
                                    @Field("mothers_name") String mothers_name , @Field("address") String address , @Field ("contact") String contact);

}

package com.baran.webservis4.RestApi;

import com.baran.webservis4.Models.CommentsModel;
import com.baran.webservis4.Models.ResultModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface RestApi {

    //https://jsonplaceholder.typicode.com/comments

    @GET("/comments")
    Call<List<CommentsModel>> commentsGetir();

    @GET("/comments")
    Call<List<ResultModel>> resultGetir(@Query("postId") String postId,@Query("id") String id);


}


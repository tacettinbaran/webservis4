package com.baran.webservis4.RestApi;

import com.baran.webservis4.Models.CommentsModel;
import com.baran.webservis4.Models.ResultModel;
import retrofit2.Call;
import java.util.List;

public class ManagerAll extends BaseManager {


    private static ManagerAll ourInstance = new ManagerAll();

    public static synchronized ManagerAll getInstance() {
        return ourInstance;
    }

    public Call<List<CommentsModel>> getComments(){
        Call<List<CommentsModel>> call = getRestApiClient().commentsGetir();
        return call;
    }

    public Call<List<ResultModel>> getResults(String postId, String id){
        Call<List<ResultModel>> call = getRestApiClient().resultGetir(postId,id);
        return call;
    }

}

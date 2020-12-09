package gap.com.mvvmsample.retrofit;

import java.util.List;

import gap.com.mvvmsample.response.ArticleResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IServices {

    @GET("v2/everything/")
    Call<ArticleResponse> getArticle(@Query("q") String q, @Query("apikey") String key);
}

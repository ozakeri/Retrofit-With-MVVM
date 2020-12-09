package gap.com.mvvmsample.repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import gap.com.mvvmsample.response.ArticleResponse;
import gap.com.mvvmsample.retrofit.IServices;
import gap.com.mvvmsample.retrofit.RetrofitRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepository extends AndroidViewModel {

    private static final String TAG = ArticleRepository.class.getSimpleName();
    private IServices services;
    private MutableLiveData<ArticleResponse> listMutableLiveData;

    public ArticleRepository(@NonNull Application application) {
        super(application);
        services = RetrofitRequest.getRetrofitInstance().create(IServices.class);
    }

    public LiveData<ArticleResponse> getArticleList(String q, String key) {
        if (listMutableLiveData == null) {
            listMutableLiveData = new MutableLiveData<>();
            loadData(q, key);
        }
        return listMutableLiveData;
    }

    public void loadData(String q, String key) {
        services.getArticle(q, key).enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                listMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {

            }
        });
    }
}

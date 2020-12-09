package gap.com.mvvmsample;

import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import gap.com.mvvmsample.adapter.ArticleAdapter;
import gap.com.mvvmsample.repository.ArticleRepository;
import gap.com.mvvmsample.response.ArticleResponse;

import static gap.com.mvvmsample.constant.AppConstant.API_KEY;
import static gap.com.mvvmsample.constant.AppConstant.ARTICLE_QUERY;

public class MainActivity extends AppCompatActivity {

    private RecyclerView my_recycler_view;
    private ProgressBar progress_circular_movie_article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_recycler_view = findViewById(R.id.my_recycler_view);
        progress_circular_movie_article = findViewById(R.id.progress_circular_movie_article);
        my_recycler_view.setHasFixedSize(true);
        my_recycler_view.setLayoutManager(new LinearLayoutManager(this));

        ArticleRepository model = ViewModelProviders.of(MainActivity.this).get(ArticleRepository.class);
        model.getArticleList(ARTICLE_QUERY, API_KEY).observe(this, new Observer<ArticleResponse>() {
            @Override
            public void onChanged(ArticleResponse articleResponse) {

                System.out.println("size=====" + articleResponse.getArticles().size());
                my_recycler_view.setAdapter(new ArticleAdapter(MainActivity.this, articleResponse.getArticles()));
            }
        });
    }
}
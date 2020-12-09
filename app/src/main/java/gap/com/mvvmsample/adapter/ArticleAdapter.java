package gap.com.mvvmsample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import gap.com.mvvmsample.R;
import gap.com.mvvmsample.model.Article;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.CustomView> {
    private Context context;
    private List<Article> list;

    public ArticleAdapter(Context context, List<Article> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_each_row_movie_article, parent, false);
        return new CustomView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomView holder, int position) {
        Article article = list.get(position);
        holder.tvTitle.setText(article.getTitle());
        holder.tvAuthorAndPublishedAt.setText("-" + article.getAuthor() + " | " + "Published At: " + article.getPublishedAt());
        holder.tvDescription.setText(article.getDescription());
        Glide.with(context)
                .load(article.getUrlToImage())
                .into(holder.imgViewCover);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CustomView extends RecyclerView.ViewHolder {
        private ImageView imgViewCover;
        private TextView tvTitle;
        private TextView tvAuthorAndPublishedAt;
        private TextView tvDescription;

        public CustomView(@NonNull View itemView) {
            super(itemView);
            imgViewCover = (ImageView) itemView.findViewById(R.id.imgViewCover);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvAuthorAndPublishedAt = (TextView) itemView.findViewById(R.id.tvAuthorAndPublishedAt);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
        }
    }
}

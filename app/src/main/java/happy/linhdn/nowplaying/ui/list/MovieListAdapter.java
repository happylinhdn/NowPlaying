package happy.linhdn.nowplaying.ui.list;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import happy.linhdn.nowplaying.R;
import happy.linhdn.nowplaying.data.model.Movie;
import happy.linhdn.nowplaying.util.Constant;


public class MovieListAdapter extends BaseAdapter {
    private List<Movie> data;
    private Context context;
    private LayoutInflater layoutInflater;

    @Inject
    public MovieListAdapter(ListFragment fragment, ListViewModel viewModel) {
        this.context = fragment.getActivity();
        this.layoutInflater = LayoutInflater.from(context);
        this.data = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Movie getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = this.layoutInflater.inflate(R.layout.movie_list_row, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        bindViewHolder(viewHolder, position);
        return convertView;
    }

    private void bindViewHolder(final ViewHolder holder, int position) {
        Movie movie = data.get(position);
        holder.txtMovieVote.setText(movie.getVoteFormat());
        Picasso.with(context)
                .load(Constant.IMG_API_URL + movie.poster_path)
                .placeholder(R.drawable.ic_interface)
//                .centerCrop()
//                .resize(200, 200)
                .into(holder.ivMoviePoster, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError() {
                        holder.ivMoviePoster.setImageResource(R.drawable.ic_interface);
                    }
                });
    }

    class ViewHolder {
        private ImageView ivMoviePoster;
        private TextView txtMovieVote;

        ViewHolder(View itemView) {
            ivMoviePoster = itemView.findViewById(R.id.iv_poster);
            txtMovieVote = itemView.findViewById(R.id.tv_vote_average);
        }
    }

    public void addData(List<Movie> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }
}

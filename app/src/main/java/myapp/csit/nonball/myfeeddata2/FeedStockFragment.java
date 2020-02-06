package myapp.csit.nonball.myfeeddata2;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import myapp.csit.nonball.myfeeddata2.Bean.ProductBean;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static myapp.csit.nonball.myfeeddata2.Bean.ProductBean.BASE_URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedStockFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ArrayList<ProductBean> mData;

    public FeedStockFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View _view = inflater.inflate(R.layout.fragment_feed_stock, container, false);
        mRecyclerView = (RecyclerView) _view.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration
                (new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        return _view;
    }

    @Override
    public void onResume() {
        super.onResume();
        feedData();
    }

    private void feedData() {
        new FeedAsyn().execute(BASE_URL + "query.php");
    }

    private class CustomRecycleView extends RecyclerView.Adapter<ViewHolder> {


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stock, parent, false);
            return new ViewHolder(_view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            ProductBean _data = mData.get(position);
            holder.title.setText(_data.getName());
            Glide.with(getContext())
                    .load(_data.getImage_url())
                    .into(holder.imgYoutupe);
            holder.edit.setTag(R.id.editBTN, _data);

        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imgYoutupe;
        Button edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            title = itemView.findViewById(R.id.nameTV);
            imgYoutupe = itemView.findViewById(R.id.imageProductIMV);
            edit = itemView.findViewById(R.id.editBTN);

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), EditActivity.class);
                    intent.putExtra("product_bean", "" + (ProductBean) edit.getTag(R.id.editBTN));
                    startActivity(intent);
                }
            });
        }
    }

    private class FeedAsyn extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                OkHttpClient _OkHttpClient = new OkHttpClient();
                Request _request = new Request.Builder().url(strings[0]).get().build();
                Response _response = _OkHttpClient.newCall(_request).execute();
                String _result = _response.body().string();
                Gson _gson = new Gson();
                Type type = new TypeToken<List<ProductBean>>() {
                }.getType();
                mData = _gson.fromJson(_result, type);
                return "successfully";
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            super.onPostExecute(result);
            if (result != null) {
                mRecyclerView.setAdapter(new CustomRecycleView());
            } else {
                Toast.makeText(getActivity(), "feed data fail", Toast.LENGTH_SHORT).show();
            }

        }
    }
}

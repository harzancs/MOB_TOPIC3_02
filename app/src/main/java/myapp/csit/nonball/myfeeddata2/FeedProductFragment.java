package myapp.csit.nonball.myfeeddata2;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import myapp.csit.nonball.myfeeddata2.Bean.ProductBean;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static myapp.csit.nonball.myfeeddata2.Bean.ProductBean.BASE_URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedProductFragment extends Fragment {

    private RecyclerView mRecyclerView;

    public ArrayList<ProductBean> mData;

    public FeedProductFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.fragment_feed_product, container, false);
        mRecyclerView = (RecyclerView) _view.findViewById(R.id.recycleview);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        //mRecyclerView.setAdapter(new CustomRecyclerView());

        feedData();

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

    private class CustomRecyclerView extends RecyclerView.Adapter<ViewHolder> {
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View _view=LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_product,parent,false);

            return new ViewHolder(_view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            ProductBean _data=mData.get(position);
            holder.title.setText(_data.getName());
            holder.detail.setText(_data.getDetail());
            holder.price.setText(_data.getPrice()+"฿");
            Glide.with(getContext())
                    .load(_data.getImage_url())
                    .into(holder.imgProduct);

        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView detail;
        TextView price;
        ImageView imgProduct;


        public ViewHolder(@NonNull View view) {
            super(view);

            //bind var to layout
            title=view.findViewById(R.id.nameTV);
            detail=view.findViewById(R.id.detailTV);
            price=view.findViewById(R.id.priceTV);
            imgProduct=view.findViewById(R.id.imageProductIMV);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProductBean _data = mData.get((getAdapterPosition()));
                    Toast.makeText(getContext(),_data.getName(),Toast.LENGTH_LONG).show();
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
                Type type = new TypeToken<List<ProductBean>>() {}.getType();
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
            if(result!=null){
                mRecyclerView.setAdapter(new CustomRecyclerView());
            }else{
                Toast.makeText(getActivity(),
                        "feed data fail",Toast.LENGTH_LONG).show();
            }
        }
    }
}
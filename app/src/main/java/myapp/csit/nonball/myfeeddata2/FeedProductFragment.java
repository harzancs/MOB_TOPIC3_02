package myapp.csit.nonball.myfeeddata2;


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

import static myapp.csit.nonball.myfeeddata2.Bean.ProductBean.BASE_URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedProductFragment extends Fragment {

    private RecyclerView mRecycleview;

    public FeedProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.fragment_feed_product, container, false);
        mRecycleview = (RecyclerView) _view.findViewById(R.id.recycleview);
        mRecycleview.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mRecycleview.setAdapter(new CustomRecyclerView());

        feedData();

        return _view;
    }

    private void feedData() {
        new FeedAsyn().execute(BASE_URL + "query.php");
    }

    private class CustomRecyclerView extends RecyclerView.Adapter<ViewHolder> {
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View _view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_product, parent, false);


            return new ViewHolder(_view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            holder.title.setText("csit tsu");

        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, detail, price;
        ImageView imgProduct;

        public ViewHolder(@NonNull View view) {
            super(view);

            //bind var to layout
            title = view.findViewById(R.id.nameTV);
            detail = view.findViewById(R.id.detailTV);
            price = view.findViewById(R.id.priceTV);
            imgProduct = view.findViewById(R.id.imageProductIMV);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}

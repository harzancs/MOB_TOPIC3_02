package myapp.csit.nonball.myfeeddata2;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedStockFragment extends Fragment {

    private RecyclerView mRecyclerView;

    public FeedStockFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View _view = inflater.inflate(R.layout.fragment_feed_stock, container, false);
        mRecyclerView = (RecyclerView) _view.findViewById(R.id.recycleview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new CustomReclyclerView());

        return _view;
    }

    private class CustomReclyclerView extends RecyclerView.Adapter<ViewHolder> {
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stock, parent, false);

            return new ViewHolder(_view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.title.setText("CSIT TSU");

        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imgYoutube;
        Button edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.nameTV);
            imgYoutube = itemView.findViewById(R.id.imageProductIMV);
            edit = itemView.findViewById(R.id.editBTN);

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}

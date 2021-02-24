package tj.iskandar.roomdemo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class CustomRecyclerview extends RecyclerView.Adapter<CustomRecyclerview.ViewHolder> {

    Context context;
    List<Repo> arrayList;

    public CustomRecyclerview(Context context, List<Repo> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        final ViewHolder detailHolder=new ViewHolder(view);
        detailHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailsActivity detailsActivity=new DetailsActivity();
                detailsActivity.show(((AppCompatActivity)context).getSupportFragmentManager(), "exampleBottomSheet");

                Bundle bundle = new Bundle();
                bundle.putString("name", arrayList.get(detailHolder.getAdapterPosition()).getName());
                bundle.putString("url", arrayList.get(detailHolder.getAdapterPosition()).getUrl());
                bundle.putString("startDate", arrayList.get(detailHolder.getAdapterPosition()).getStartDate());
                bundle.putString("endDate", arrayList.get(detailHolder.getAdapterPosition()).getEndDate());
                bundle.putString("objType", arrayList.get(detailHolder.getAdapterPosition()).getObjType());
                bundle.putString("loginRequired", arrayList.get(detailHolder.getAdapterPosition()).getLoginRequired());

                detailsActivity.setArguments(bundle);
                Log.e("press", arrayList.get(detailHolder.getAdapterPosition()).getName());


            }
        });

        return detailHolder;

//        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Repo repo = arrayList.get(position);

        holder.name.setText(repo.getName());
        holder.chef.setText("By " + repo.getEndDate());
        holder.description.setText(repo.getUrl());
        holder.timestamp.setText(repo.getIcon());

        Glide.with(context)
                .load(repo.getIcon())
                .into(holder.thumbnail);


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name, description, price, chef, timestamp;
        public ImageView thumbnail;
        RelativeLayout view_container;
        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            chef = itemView.findViewById(R.id.chef);
            description = itemView.findViewById(R.id.description);
            price = itemView.findViewById(R.id.price);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            timestamp = itemView.findViewById(R.id.timestamp);
            view_container = itemView.findViewById(R.id.relative);

        }
    }
}

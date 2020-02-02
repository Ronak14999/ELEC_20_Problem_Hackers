package com.example.testing_1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<RoomviewHolder>{

    private Context mcontext;
    private List<RoomData> myRoomList;

    public MyAdapter(Context mcontext, List<RoomData> myRoomList) {
        this.mcontext = mcontext;
        this.myRoomList = myRoomList;
    }

    @NonNull
    @Override
    public RoomviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View mview= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_raw_items,viewGroup,false);
        return new RoomviewHolder(mview);
    }

    @Override
    public void onBindViewHolder(@NonNull final RoomviewHolder holder, int i) {
        Glide.with(mcontext).load(myRoomList.get(i).getImageid())
                .into(holder.imageview);

        holder.mcity.setText(myRoomList.get(i).getCity());
        holder.marea.setText(myRoomList.get(i).getArea());
        holder.mpriority.setText(myRoomList.get(i).getPriority());
        holder.mcontact.setText(myRoomList.get(i).getContact());
        //holder.imageview.setImageResource(myRoomList.get(i).getImageid());

        holder.mcardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mcontext,DetailsActivity.class);
                intent.putExtra("Image",myRoomList.get(holder.getAdapterPosition()).getImageid());
                intent.putExtra("House-No",myRoomList.get(holder.getAdapterPosition()).getHouseno());
                intent.putExtra("City",myRoomList.get(holder.getAdapterPosition()).getCity());
                intent.putExtra("Area",myRoomList.get(holder.getAdapterPosition()).getArea());
                intent.putExtra("State",myRoomList.get(holder.getAdapterPosition()).getState());
                intent.putExtra("Contact",myRoomList.get(holder.getAdapterPosition()).getContact());
                intent.putExtra("Priority",myRoomList.get(holder.getAdapterPosition()).getPriority());
                intent.putExtra("Key",myRoomList.get(holder.getAdapterPosition()).getKey());

                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return myRoomList.size();
    }

    public void filteredList(ArrayList<RoomData> filterList) {
        myRoomList=filterList;
        notifyDataSetChanged();


    }
}

class RoomviewHolder extends RecyclerView.ViewHolder {

    ImageView imageview;
    TextView mpriority,mcontact,mcity,marea;
    CardView mcardview;



    public RoomviewHolder(@NonNull View itemView) {
        super(itemView);

        imageview=itemView.findViewById(R.id.ivImage);
        mpriority=itemView.findViewById(R.id.tvpriority);
        mcontact=itemView.findViewById(R.id.tvcontact);
        mcity=itemView.findViewById(R.id.tvcity);
        marea=itemView.findViewById(R.id.tvarea);
        mcardview=itemView.findViewById(R.id.myCardView);

    }
}

package com.example.whatsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.whatsapp.R;
import com.example.whatsapp.R;
import com.example.whatsapp.models.messegesModels;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class chatAdapter extends RecyclerView.Adapter{

    ArrayList<messegesModels> messegesModels;
    Context context;
    int SENDER_VIEW_TYPE=1;
    int RECEIVER_VIEW_TYPE=2;

    public chatAdapter(ArrayList<messegesModels> messegesModels, Context context) {
        this.messegesModels = messegesModels;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==SENDER_VIEW_TYPE)
        {

            View view= LayoutInflater.from(context).inflate(R.layout.sampleender,parent,false);
            return new senderViewHolder(view);
        }

        else {

            View view= LayoutInflater.from(context).inflate(R.layout.samplereciever,parent,false);
            return new ReviverViewHolder(view);

        }
    }

    @Override
       public int getItemViewType(int position) {

         if(messegesModels.get(position).getuId().equals(FirebaseAuth.getInstance().getUid()))
         {
             return SENDER_VIEW_TYPE;
         }
         else {
             return RECEIVER_VIEW_TYPE;
         }
}


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
     messegesModels messegesModel=messegesModels.get(position);
  if(holder.getClass()== senderViewHolder.class)
{

((senderViewHolder)holder).senderMsg.setText(messegesModel.getMessage());

}
else {
      ((ReviverViewHolder)holder).reciverMsg.setText(messegesModel.getMessage());
  }

    }

    @Override
    public int getItemCount() {
        return messegesModels.size();
    }

    public class ReviverViewHolder extends RecyclerView.ViewHolder {

        TextView reciverMsg,recieverTime;

        public ReviverViewHolder(@NonNull View itemView) {
            super(itemView);
            reciverMsg=itemView.findViewById(R.id.recimessegeBox);
            recieverTime=itemView.findViewById(R.id.recitimeBox);

        }
    }

    public class senderViewHolder  extends RecyclerView.ViewHolder{

        TextView senderMsg,senderTime;

        public senderViewHolder(@NonNull View itemView) {
            super(itemView);
            senderMsg=itemView.findViewById(R.id.sendersmsbox);
            senderTime=itemView.findViewById(R.id.sendertimebox);


        }
    }

}

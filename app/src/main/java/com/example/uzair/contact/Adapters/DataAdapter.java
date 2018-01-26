package com.example.uzair.contact.Adapters;

/**
 * Created by Shahzad on 1/26/2018.
 */

import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import android.widget.Toast;

import com.example.uzair.contact.Details;
import com.example.uzair.contact.Models.ContactInfo;
import com.example.uzair.contact.R;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    ArrayList<ContactInfo> contactInfos;
    private  ClickListener clicklistener = null;
    Context context;

    public DataAdapter(Context context, ArrayList<ContactInfo> contactInfos) {
        this.contactInfos = contactInfos;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_list_row_layout,parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        ContactInfo ui = contactInfos.get(position);
        holder.id.setText(ui.getId()+"");
        holder.name.setText(ui.getName()+"");
        char ch = Character.toUpperCase(ui.getEmail().charAt(0));
        holder.first.setText(ch+"");
       // setColor(holder.first,ch);
    }

    void setColor(TextView view, char ch)
    {
        if(ch == 'A' || ch == 'H' || ch == 'Z' || ch == 'U')
        {
            view.setBackgroundColor(Color.BLUE);
        }
        else if(ch == 'B' || ch == 'I' || ch == 'Y' || ch == 'V')
        {
            view.setBackgroundColor(Color.GRAY);
        }
        else if(ch == 'C' || ch == 'J' || ch == 'W' || ch == 'S')
        {
            view.setBackgroundColor(Color.GREEN);
        }
        else if(ch == 'D' || ch == 'K' || ch == 'T' || ch == 'N')
        {
            view.setBackgroundColor(Color.YELLOW);
        }
        else
        {
            view.setBackgroundColor(Color.LTGRAY);
        }
    }

    @Override
    public int getItemCount() {
        return contactInfos.size();
    }

    public void setClickListener(ClickListener clickListener){
        this.clicklistener = clickListener;
    }

    /**
     * DataAdapter.ViewHolder Class is below It will be used for designing
     * and setting the data entries in the adapter for recyclerview
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView id;
        public TextView name;
        public TextView first;
        public LinearLayout ll_ui;

        public ViewHolder(View view) {
            super(view);


            id = (TextView) view.findViewById(R.id.text_view_contact_list_id);
            first = (TextView) view.findViewById(R.id.text_view_contact_list_Alphabet);
            name = (TextView) view.findViewById(R.id.text_view_contact_list_name);
            ll_ui = (LinearLayout) view.findViewById(R.id.LL_contact_list_main);

            id.setVisibility(view.INVISIBLE);

            ll_ui.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();



            Intent i = new Intent(context,Details.class);
            i.putExtra("id",contactInfos.get(position).getId());
            i.putExtra("name",contactInfos.get(position).getName());
            i.putExtra("phone",contactInfos.get(position).getPhone());
            i.putExtra("email",contactInfos.get(position).getEmail());

            ((Activity)context).startActivity(i);


        }

    }

    public interface ClickListener {
        public void itemClicked(View view ,int position);
    }



}

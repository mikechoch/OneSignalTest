package com.onesignal.sdktest.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.onesignal.sdktest.R;
import com.onesignal.sdktest.type.Notification;

public class NotificationRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private Notification[] notifs;


    public NotificationRecyclerViewAdapter(Context context, Notification[] notifications) {
        this.context = context;
        this.notifs = notifications;
    }


    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_notifications_recycler_view_item_layout, parent, false);
        view.setHasTransientState(true);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((NotificationViewHolder) holder).setData(position, notifs[position]);
    }

    @Override
    public int getItemCount() {
        return notifs.length;
    }


    public class NotificationViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout categoryRelativeLayout;
        private ImageView categoryImageView;
        private TextView categoryTextView;

        private Notification notif;

        NotificationViewHolder(View itemView) {
            super(itemView);

            categoryRelativeLayout = itemView.findViewById(R.id.notification_recycler_view_item_relative_layout);
            categoryImageView = itemView.findViewById(R.id.notification_recycler_view_item_image_view);
            categoryTextView = itemView.findViewById(R.id.notification_recycler_view_item_text_view);
        }

        private void setData(int position, Notification notification) {
            this.notif = notification;
            populateInterfaceElements(position);
        }

        private void populateInterfaceElements(int position) {
            categoryRelativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            categoryTextView.setText(notif.getTitle());

            Glide.with(context)
                    .asBitmap()
                    .load(notif.getIconUrl())
                    .into(new BitmapImageViewTarget(categoryImageView) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            categoryImageView.setImageBitmap(resource);
                        }
                    });
        }

    }

}

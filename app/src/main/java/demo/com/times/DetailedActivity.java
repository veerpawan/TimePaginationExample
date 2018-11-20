package demo.com.times;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import demo.com.times.model.ResponseObjects;

public class DetailedActivity extends AppCompatActivity {
    public String slug;
    public String p, ibanner;
    @BindView(R.id.img_banner)
    ImageView imgBanner;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    long when = System.currentTimeMillis();
    Bitmap bitmap;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        ButterKnife.bind(this);

        bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_launcher_background);
        Intent i = getIntent();
        slug = i.getStringExtra("slug");
        id = i.getIntExtra("id", 0);
        p = i.getStringExtra("p");
        ibanner = i.getStringExtra("banner");
        //Toast.makeText(getApplicationContext(), ibanner, Toast.LENGTH_LONG).show();
        tvPrice.setText(String.valueOf(p));
        tvId.setText(String.valueOf(id));
        Glide.with(DetailedActivity.this)
                .load(ibanner)
                .into(imgBanner);
        addNotification();
    }

    private void addNotification() {
        Intent intent = new Intent(this, DetailedActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        // Build notification
        // Actions are just fake
        Notification noti = new Notification.Builder(this)
                .setContentTitle("New mail from " + "test@gmail.com")
                .setContentText("Subject").setSmallIcon(R.drawable.ic_launcher_background)
                .setWhen(when)

                .setContentIntent(pIntent)
                .addAction(R.drawable.ic_thumb_up_black_24dp, "Like", pIntent).build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0, noti);

    }


}


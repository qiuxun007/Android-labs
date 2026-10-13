package com.example.experiment3;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "animal_channel";

    private final String[] names = {"Lion", "Tiger", "Monkey", "Dog", "Cat", "Elephant"};
    private final int[] images = {
            R.drawable.lion, R.drawable.tiger, R.drawable.monkey,
            R.drawable.dog, R.drawable.cat, R.drawable.elephant
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS)
                    != android.content.pm.PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 100);
            }
        }
        createNotificationChannel();

        // ---------- 任务1：ListView + SimpleAdapter ----------
        ListView listView = findViewById(R.id.listView);

        List<Map<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", names[i]);
            map.put("image", images[i]);
            data.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                data,
                R.layout.list_item,
                new String[]{"name", "image"},
                new int[]{R.id.tv_name, R.id.iv_animal}
        );
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selected = names[position];
            Toast.makeText(MainActivity.this, "选中: " + selected, Toast.LENGTH_SHORT).show();
            sendNotification(selected);
        });

        //任务2：自定义 AlertDialog
        findViewById(R.id.btn_dialog).setOnClickListener(v -> showCustomDialog());

        //任务3：使用XML定义菜单
        findViewById(R.id.btn_menu).setOnClickListener(v -> {
            androidx.appcompat.widget.PopupMenu popup =
                    new androidx.appcompat.widget.PopupMenu(MainActivity.this, v);
            popup.getMenuInflater().inflate(R.menu.menu_main, popup.getMenu());
            popup.setOnMenuItemClickListener(item -> onOptionsItemSelected(item));
            popup.show();
        });

        //任务4：创建上下文操作模式(ActionMode)的上下文菜单
        findViewById(R.id.btn_action_mode).setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ActionModeActivity.class)));

        //任务5：使用RecyclerView实现列表视图效果
        findViewById(R.id.btn_recycler).setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, RecyclerActivity.class)));
    }

    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_login, null);
        builder.setView(view);

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.setPositiveButton("Sign in", (dialog, which) -> {
            EditText etUser = view.findViewById(R.id.et_username);
            EditText etPass = view.findViewById(R.id.et_password);
            Toast.makeText(this,
                    "Username: " + etUser.getText() + "\nPassword: " + etPass.getText(),
                    Toast.LENGTH_SHORT).show();
        });

        builder.create().show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        TextView tvTest = findViewById(R.id.tv_test);
        int id = item.getItemId();

        if (id == R.id.font_small) {
            tvTest.setTextSize(10);
        } else if (id == R.id.font_medium) {
            tvTest.setTextSize(16);
        } else if (id == R.id.font_large) {
            tvTest.setTextSize(20);
        } else if (id == R.id.menu_normal) {
            Toast.makeText(this, "点击了普通菜单项", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.color_red) {
            tvTest.setTextColor(Color.RED);
        } else if (id == R.id.color_black) {
            tvTest.setTextColor(Color.BLACK);
        } else {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID, "Animal Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    private void sendNotification(String title) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText("你选中了 " + title + "，这是通知内容")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManager manager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(1, builder.build());
    }
}
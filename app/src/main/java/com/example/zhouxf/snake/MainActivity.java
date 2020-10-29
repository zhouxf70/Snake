package com.example.zhouxf.snake;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final int width = 20;
    private final int height = 20;
    private final int[] data = new int[width * height];
    private final Snake snake = new Snake(width, height);

    private int interval = 500;
    private boolean start = false;

    private MyAdapter adapter;
    private Button btnStart;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                boolean meet = snake.forward();
                refresh();
                if (meet) {
                    Toast.makeText(MainActivity.this, "over", Toast.LENGTH_LONG).show();
                } else {
                    handler.sendEmptyMessageDelayed(0, interval);
                }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_top).setOnClickListener(this);
        findViewById(R.id.btn_bottom).setOnClickListener(this);
        findViewById(R.id.btn_left).setOnClickListener(this);
        findViewById(R.id.btn_right).setOnClickListener(this);

        btnStart = findViewById(R.id.btn_start);
        btnStart.setOnClickListener(this);

        RecyclerView recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new GridLayoutManager(this, width));
        adapter = new MyAdapter(this, data);
        recycler.setAdapter(adapter);
        refresh();
    }

    private void refresh() {
        Arrays.fill(data, 0);
        Snake.Node[] listNode = snake.listNode();
        for (int i = 0; i < listNode.length; i++) {
            Snake.Node node = listNode[i];
            int position = (node.x - 1) + (node.y - 1) * width;
            Log.d("----", node + ",position = " + position);
            if (i == 0) {
                data[position] = -1;
            } else {
                data[position] = 1;
            }
        }
        adapter.notifyDataSetChanged();
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        Snake.Direction newDir = null;
        switch (v.getId()) {
            case R.id.btn_top:
                newDir = Snake.Direction.TOP;
                break;
            case R.id.btn_bottom:
                newDir = Snake.Direction.BOTTOM;
                break;
            case R.id.btn_left:
                newDir = Snake.Direction.LEFT;
                break;
            case R.id.btn_right:
                newDir = Snake.Direction.RIGHT;
                break;
            case R.id.btn_start:
                start = !start;
                btnStart.setText(start ? "STOP" : "START");
                if (start) {
                    handler.sendEmptyMessage(0);
                } else {
                    handler.removeMessages(0);
                }
                break;
        }
        if (newDir != null && snake.checkDir(newDir)) {
            snake.setDir(newDir);
            handler.removeMessages(0);
            handler.sendEmptyMessage(0);
        }
    }
}

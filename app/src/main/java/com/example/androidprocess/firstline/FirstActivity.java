package com.example.androidprocess.firstline;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.androidprocess.R;

public class FirstActivity extends BaseLineActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("cds", this.toString());
        Log.d("cds", "FirstActivity TaskId: " + getTaskId());
        setContentView(R.layout.activity_first);
        if (savedInstanceState != null) {
            String data = savedInstanceState.getString("data_key");
        }

        Button button = findViewById(R.id.button_1);
        button.setOnClickListener(this);

        Button btPhone = findViewById(R.id.bt_phone);
        btPhone.setOnClickListener(this);

        Button btJumpSecond = findViewById(R.id.bt_jump_second);
        btJumpSecond.setOnClickListener(this);

        Button btSelf = findViewById(R.id.bt_self);
        btSelf.setOnClickListener(this);

        Button btThird = findViewById(R.id.bt_jump_third);
        btThird.setOnClickListener(this);

        Button btBest = findViewById(R.id.bt_best_start);
        btBest.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //给当前活动创建菜单
        getMenuInflater().inflate(R.menu.first, menu);
        //返回true表示允许菜单显示，false表示不显示
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this, "You click Add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "You click Remove", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
        //返回值表示点击事件是否向下传递
        return true;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("cds", "FirstActivity onRestart: ");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_1:
//                Intent intent = new Intent("com.example.activitytest.ACTION_START");
//                intent.addCategory("com.example.activitytest.MY_CATEGORY");
//                startActivity(intent);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
                break;
            case R.id.bt_phone:
                Intent intentPhone = new Intent(Intent.ACTION_DIAL);
                intentPhone.setData(Uri.parse("tel:10086"));
                startActivity(intentPhone);
                break;
            case R.id.bt_jump_second:
                Intent iJump = new Intent(this, SecondActivity.class);
                iJump.putExtra("extra_data", "This is Data");
                startActivityForResult(iJump, 1);
                break;
            case R.id.bt_self:
                Intent itnSelf = new Intent(this, FirstActivity.class);
                startActivity(itnSelf);
                break;
            case R.id.bt_jump_third:
                Intent itnThird = new Intent(this, ThirdActivity.class);
                startActivity(itnThird);
                break;
            case R.id.bt_best_start:
                BestStartActivity.actionStart(this, "第一行", "代码");
                break;
            default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                if (data != null) {
                    String str = data.getStringExtra("result_data");
                    Log.d("first", "requestCode: " + requestCode + "  " + str);
                }
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        String data = "Save important data";
        outState.putString("data_key", data);
    }
}
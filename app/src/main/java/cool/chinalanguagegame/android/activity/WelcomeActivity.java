package cool.chinalanguagegame.android.activity;

import android.os.Bundle;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cool.chinalanguagegame.android.R;
import cool.chinalanguagegame.android.base.BaseActivity;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //第一：默认初始化
        Bmob.initialize(this, "d9298669ee86258190d40ec2f2a9349b");
        if (BmobUser.isLogin()) {
            toActivity(MainActivity.class);
        } else {
            toActivity(LoginActivity.class);
        }
        finish();
    }
}

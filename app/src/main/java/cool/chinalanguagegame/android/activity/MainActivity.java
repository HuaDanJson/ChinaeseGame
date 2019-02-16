package cool.chinalanguagegame.android.activity;

import android.os.Bundle;
import android.widget.Button;

import com.blankj.utilcode.util.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cool.chinalanguagegame.android.R;
import cool.chinalanguagegame.android.base.BaseActivity;
import cool.chinalanguagegame.android.bean.CurrentUser;

public class MainActivity extends BaseActivity {

    @BindView(R.id.btn_game) Button btnGame;
    @BindView(R.id.btn_store) Button btnStore;
    @BindView(R.id.btn_logout) Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Bmob.initialize(this, "d9298669ee86258190d40ec2f2a9349b");
        CurrentUser user = BmobUser.getCurrentUser(CurrentUser.class);
        LogUtils.d("MainActivity CurrentUser : " + user);
    }

    @OnClick(R.id.btn_game)
    public void enterGameClicked() {
        toActivity(SelectGameActivity.class);
    }

    @OnClick(R.id.btn_store)
    public void enterStoreClicked() {
        toActivity(StoreActivity.class);
    }

    @OnClick(R.id.btn_logout)
    public void logoutClicked() {
        BmobUser.logOut();
        toActivity(LoginActivity.class);
        finish();
    }
}

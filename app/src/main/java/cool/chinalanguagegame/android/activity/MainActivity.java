package cool.chinalanguagegame.android.activity;

import android.os.Bundle;
import android.widget.Button;

import com.blankj.utilcode.util.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FetchUserInfoListener;
import cool.chinalanguagegame.android.R;
import cool.chinalanguagegame.android.base.BaseActivity;
import cool.chinalanguagegame.android.bean.CurrentUser;
import cool.chinalanguagegame.android.utils.CurrentUserHelper;
import cool.chinalanguagegame.android.utils.ToastHelper;

public class MainActivity extends BaseActivity {

    @BindView(R.id.btn_game) Button btnGame;
    @BindView(R.id.btn_store) Button btnStore;
    @BindView(R.id.btn_logout) Button btnLogout;
    private long firstBack = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Bmob.initialize(this, "d9298669ee86258190d40ec2f2a9349b");
        BmobUser.fetchUserInfo(new FetchUserInfoListener<BmobUser>() {
            @Override
            public void done(BmobUser user, BmobException e) {
                if (e == null) {
                    final CurrentUser currentUser = BmobUser.getCurrentUser(CurrentUser.class);
                    LogUtils.d("MainActivity getCurrent 000 User Success CurrentUser : " + currentUser);
                    if (currentUser == null) {
                        fetchCurrentUser();
                    } else {
                        CurrentUserHelper.getInstance().updateCurrentUser(currentUser);
                    }

                } else {
                    LogUtils.d("MainActivity getCurrent User 11 failed exception : " + e.getMessage());
                    fetchCurrentUser();
                }
            }
        });
    }

    public void fetchCurrentUser() {
        BmobUser.fetchUserJsonInfo(new FetchUserInfoListener<String>() {
            @Override
            public void done(String json, BmobException e) {
                if (e == null) {
                    CurrentUserHelper.getInstance().updateCurrentUser(json);
                    LogUtils.d("MainActivity fetchUserJsonInfo  22 User Success CurrentUser : " + json);
                } else {
                    LogUtils.d("MainActivity fetchUserJsonInfo 333 User failed exception : " + e.getMessage());
                }
            }
        });
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

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - firstBack < 2000) {
            super.onBackPressed();
        } else {
            firstBack = System.currentTimeMillis();
            ToastHelper.showShortMessage(R.string.back_btn_exit_pop);
        }
    }
}

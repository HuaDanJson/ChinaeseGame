package cool.chinalanguagegame.android.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cool.chinalanguagegame.android.R;
import cool.chinalanguagegame.android.base.BaseActivity;
import cool.chinalanguagegame.android.constants.AppConstant;
import cool.chinalanguagegame.android.utils.ActivityUtil;

public class SelectGameInfoActivity extends BaseActivity {

    @BindView(R.id.tv_title) TextView mTitle;
    @BindView(R.id.btn_input) Button mInput;
    @BindView(R.id.btn_select) Button mSelect;
    private int mType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary_select_game);
        ButterKnife.bind(this);
        mType = getIntent().getIntExtra(AppConstant.IntentKey.EXTRA_TYPE, 0);
        if (mType <= 0) {
            onBackPressed();
        }
        switch (mType) {
            case 1:
                mTitle.setText("小学成语");
                break;
            case 2:
                mTitle.setText("小学诗词");
                break;
            case 3:
                mTitle.setText("中学成语");
                break;
            case 4:
                mTitle.setText("中学诗词");
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.btn_input)
    public void selectInputClicked() {
        ActivityUtil.startSelectInputGameActivityy(this, mType);
    }

    @OnClick(R.id.btn_select)
    public void selectSelectClicked() {
        ActivityUtil.startSelectSelectActivity(this, mType);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.exit_stop_original_place, R.anim.exit_to_right);
    }
}

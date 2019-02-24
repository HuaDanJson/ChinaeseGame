package cool.chinalanguagegame.android.activity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cool.chinalanguagegame.android.R;
import cool.chinalanguagegame.android.base.BaseActivity;
import cool.chinalanguagegame.android.constants.AppConstant;
import cool.chinalanguagegame.android.utils.ActivityUtil;

public class SelectSelectActivity extends BaseActivity {

    @BindView(R.id.ll_select_one_star) LinearLayout llSelectOneStar;
    @BindView(R.id.ll_select_two_star) LinearLayout llSelectTwoStar;
    @BindView(R.id.ll_select_three_star) LinearLayout llSelectThreeStar;
    @BindView(R.id.tv_title) TextView mTitle;

    private int mType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selcet_select);
        ButterKnife.bind(this);
        mType = getIntent().getIntExtra(AppConstant.IntentKey.EXTRA_TYPE, 0);
        if (mType <= 0) {
            onBackPressed();
        }

        switch (mType) {
            case 1:
                mTitle.setText("小学成语选择");
                break;
            case 2:
                mTitle.setText("小学诗词选择");
                break;
            case 3:
                mTitle.setText("初中成语选择");
                break;
            case 4:
                mTitle.setText("初中诗词选择");
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.ll_select_one_star)
    public void selectOneStartClicked() {
        ActivityUtil.startPlaySelectGameActivity(this, mType, 1);
    }

    @OnClick(R.id.ll_select_two_star)
    public void selectTwoStartClicked() {
        ActivityUtil.startPlaySelectGameActivity(this, mType, 2);
    }

    @OnClick(R.id.ll_select_three_star)
    public void selectThreeClicked() {
        ActivityUtil.startPlaySelectGameActivity(this, mType, 3);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.exit_stop_original_place, R.anim.exit_to_right);
    }
}

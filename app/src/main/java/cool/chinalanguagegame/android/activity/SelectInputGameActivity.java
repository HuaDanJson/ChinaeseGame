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

public class SelectInputGameActivity extends BaseActivity {


    @BindView(R.id.ll_select_one_star) LinearLayout llSelectOneStar;
    @BindView(R.id.ll_select_two_star) LinearLayout llSelectTwoStar;
    @BindView(R.id.ll_select_three_star) LinearLayout llSelectThreeStar;
    @BindView(R.id.ll_select_four_star) LinearLayout llSelectFourStar;
    @BindView(R.id.ll_select_five_star) LinearLayout llSelectFiveStar;
    @BindView(R.id.tv_title) TextView mTitle;

    private int mType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_input_game);
        ButterKnife.bind(this);
        mType = getIntent().getIntExtra(AppConstant.IntentKey.EXTRA_TYPE, 0);
        if (mType <= 0) {
            onBackPressed();
        }

        switch (mType) {
            case 1:
                mTitle.setText("小学成语填空");
                break;
            case 2:
                mTitle.setText("小学诗词填空");
                break;
            case 3:
                mTitle.setText("初中成语填空");
                break;
            case 4:
                mTitle.setText("初中诗词填空");
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.ll_select_one_star)
    public void selectOneStartClicked() {
        ActivityUtil.startPlayInputGameActivity(this, mType, 1);
    }

    @OnClick(R.id.ll_select_two_star)
    public void selectTwoStartClicked() {
        ActivityUtil.startPlayInputGameActivity(this, mType, 2);
    }

    @OnClick(R.id.ll_select_three_star)
    public void selectThreeStartClicked() {
        ActivityUtil.startPlayInputGameActivity(this, mType, 3);
    }

    @OnClick(R.id.ll_select_four_star)
    public void selectFourStartClicked() {
        ActivityUtil.startPlayInputGameActivity(this, mType, 4);
    }

    @OnClick(R.id.ll_select_five_star)
    public void selectFiveStartClicked() {
        ActivityUtil.startPlayInputGameActivity(this, mType, 5);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.exit_stop_original_place, R.anim.exit_to_right);
    }
}

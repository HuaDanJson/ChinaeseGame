package cool.chinalanguagegame.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cool.chinalanguagegame.android.R;
import cool.chinalanguagegame.android.base.BaseActivity;
import cool.chinalanguagegame.android.constants.AppConstant;
import cool.chinalanguagegame.android.view.CustomViewPager;

public class PlaySelectGameActivity extends BaseActivity {

    @BindView(R.id.tv_title) TextView mTitle;
    @BindView(R.id.cvg_play_select_game_activity) CustomViewPager mCustomViewPager;
    private int mType;
    private int mStarCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_select);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent == null) {onBackPressed();}
        mType = intent.getIntExtra(AppConstant.IntentKey.EXTRA_TYPE, 0);
        mStarCount = intent.getIntExtra(AppConstant.IntentKey.EXTRA_STAR, 0);
        if (mType <= 0 || mStarCount == 0) {
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.exit_stop_original_place, R.anim.exit_to_right);
    }
}

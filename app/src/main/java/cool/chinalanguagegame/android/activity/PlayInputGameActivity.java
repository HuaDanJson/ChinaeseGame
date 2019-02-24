package cool.chinalanguagegame.android.activity;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cool.chinalanguagegame.android.R;
import cool.chinalanguagegame.android.base.BaseActivity;
import cool.chinalanguagegame.android.constants.AppConstant;

public class PlayInputGameActivity extends BaseActivity {

    @BindView(R.id.tv_title) TextView mTitle;
    private int mType;
    private int mStarCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_input_game);
        ButterKnife.bind(this);
        mType = getIntent().getIntExtra(AppConstant.IntentKey.EXTRA_TYPE, 0);
        mStarCount = getIntent().getIntExtra(AppConstant.IntentKey.EXTRA_STAR, 0);
        if (mType <= 0 || mStarCount <= 0) {
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.exit_stop_original_place, R.anim.exit_to_right);
    }
}

package cool.chinalanguagegame.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cool.chinalanguagegame.android.R;
import cool.chinalanguagegame.android.adapter.CheckAnswerAdapter;
import cool.chinalanguagegame.android.base.BaseActivity;
import cool.chinalanguagegame.android.bean.InputGameBean;
import cool.chinalanguagegame.android.constants.AppConstant;

public class GameOverActivity extends BaseActivity {

    @BindView(R.id.btn_check_all_data) Button btnCheckAllData;
    @BindView(R.id.tv_this_score) TextView mThisScore;
    @BindView(R.id.tv_all_score) TextView mAllScoreTextView;
    @BindView(R.id.rlv_game_over_activity) RecyclerView mRecyclerView;
    private List<InputGameBean> mInputGameBeanList = new ArrayList<>();
    private int mThisScoreCount;
    private CheckAnswerAdapter mCheckAnswerAdapter;
    private int clickedCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent == null) {onBackPressed();}
        mThisScoreCount = intent.getIntExtra(AppConstant.IntentKey.EXTRA_SCORE, 0);
        mInputGameBeanList = (ArrayList<InputGameBean>) intent.getSerializableExtra(AppConstant.IntentKey.EXTRA_DATA);
        mThisScore.setText("本关得分:" + String.valueOf(mThisScoreCount));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.exit_stop_original_place, R.anim.exit_to_right);
    }

    @OnClick(R.id.btn_check_all_data)
    public void checkAllClicked() {
        clickedCount++;
        if (clickedCount % 2 == 1) {
            initRecyclerView();
        } else {
            mRecyclerView.setVisibility(View.GONE);
        }
    }

    public void initRecyclerView() {
        if (mRecyclerView == null) {return;}
        if (mCheckAnswerAdapter == null) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mCheckAnswerAdapter = new CheckAnswerAdapter();
            mCheckAnswerAdapter.setDataSilently(mInputGameBeanList);
            mRecyclerView.setAdapter(mCheckAnswerAdapter);
        } else {
            mCheckAnswerAdapter.setData(mInputGameBeanList);
        }
        mRecyclerView.setVisibility(View.VISIBLE);
    }
}

package cool.chinalanguagegame.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cool.chinalanguagegame.android.R;
import cool.chinalanguagegame.android.base.BaseActivity;
import cool.chinalanguagegame.android.bean.CurrentUser;
import cool.chinalanguagegame.android.bean.InputGameBean;
import cool.chinalanguagegame.android.bean.SelectGameBean;
import cool.chinalanguagegame.android.constants.AppConstant;
import cool.chinalanguagegame.android.fragment.SelectGameFragment;
import cool.chinalanguagegame.android.utils.ActivityUtil;
import cool.chinalanguagegame.android.utils.CurrentUserHelper;
import cool.chinalanguagegame.android.utils.ResourceUtil;
import cool.chinalanguagegame.android.utils.ToastHelper;
import cool.chinalanguagegame.android.view.CustomViewPager;

public class PlaySelectGameActivity extends BaseActivity implements SelectGameFragment.SelectGameFragmentListener {

    @BindView(R.id.tv_title) TextView mTitle;
    @BindView(R.id.cvg_play_select_game_activity) CustomViewPager mCustomViewPager;
    @BindView(R.id.tv_score_play_select_game) TextView tvScorePlaySelectGame;
    @BindView(R.id.iv_star1) ImageView ivStar1;
    @BindView(R.id.iv_star2) ImageView ivStar2;
    @BindView(R.id.iv_star3) ImageView ivStar3;
    @BindView(R.id.iv_star4) ImageView ivStar4;
    @BindView(R.id.iv_star5) ImageView ivStar5;
    @BindView(R.id.ll_all_play_select_game_activity) LinearLayout mALLLinearLayout;
    private int mType;
    private int mStarCount;

    private PlaySelectGameViewPageAdapter mPlaySelectGameViewPageAdapter;
    private List<SelectGameBean> mSelectGameBeanList = new ArrayList<>();
    private List<Fragment> mSelectFragmentList = new ArrayList<>();
    private long firstBack = -1;
    private int mAllScore;
    private CurrentUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_select);
        ButterKnife.bind(this);
        mCurrentUser = CurrentUserHelper.getInstance().getCurrentUser();
        if (mCurrentUser == null) {finish();}
        Intent intent = getIntent();
        if (intent == null) {finish();}
        mType = intent.getIntExtra(AppConstant.IntentKey.EXTRA_TYPE, 0);
        mStarCount = intent.getIntExtra(AppConstant.IntentKey.EXTRA_STAR, 0);
        if (mType <= 0 || mStarCount == 0) {
            finish();
        }
        mSelectGameBeanList = (ArrayList<SelectGameBean>) intent.getSerializableExtra(AppConstant.IntentKey.EXTRA_DATA);
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

        switch (mStarCount) {
            case 1:
                ivStar1.setVisibility(View.VISIBLE);
                ivStar2.setVisibility(View.GONE);
                ivStar3.setVisibility(View.GONE);
                ivStar4.setVisibility(View.GONE);
                ivStar5.setVisibility(View.GONE);
                break;
            case 2:
                ivStar1.setVisibility(View.VISIBLE);
                ivStar2.setVisibility(View.VISIBLE);
                ivStar3.setVisibility(View.GONE);
                ivStar4.setVisibility(View.GONE);
                ivStar5.setVisibility(View.GONE);
                break;
            case 3:
                ivStar1.setVisibility(View.VISIBLE);
                ivStar2.setVisibility(View.VISIBLE);
                ivStar3.setVisibility(View.VISIBLE);
                ivStar4.setVisibility(View.GONE);
                ivStar5.setVisibility(View.GONE);
                break;
            case 4:
                ivStar1.setVisibility(View.VISIBLE);
                ivStar2.setVisibility(View.VISIBLE);
                ivStar3.setVisibility(View.VISIBLE);
                ivStar4.setVisibility(View.VISIBLE);
                ivStar5.setVisibility(View.GONE);
                break;
            case 5:
                ivStar1.setVisibility(View.VISIBLE);
                ivStar2.setVisibility(View.VISIBLE);
                ivStar3.setVisibility(View.VISIBLE);
                ivStar4.setVisibility(View.VISIBLE);
                ivStar5.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
        initStateTwoViewPager();
    }

    public void initStateTwoViewPager() {
        int inputGameBeanListSize = mSelectGameBeanList.size();
        if (inputGameBeanListSize > 0) {
            for (int i = 0; i < inputGameBeanListSize; i++) {
                SelectGameFragment SelectGameFragment = new SelectGameFragment();
                SelectGameFragment.setData(i, mSelectGameBeanList.get(i));
                SelectGameFragment.setSelectGameFragmentListener(this);
                mSelectFragmentList.add(SelectGameFragment);
            }
        }
        mPlaySelectGameViewPageAdapter = new PlaySelectGameViewPageAdapter(getSupportFragmentManager());
        mCustomViewPager.setAdapter(mPlaySelectGameViewPageAdapter);
        mCustomViewPager.setOffscreenPageLimit(3);
        mCustomViewPager.setCurrentItem(0);
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - firstBack < 2000) {
            super.onBackPressed();
        } else {
            firstBack = System.currentTimeMillis();
            ToastHelper.showShortMessage(R.string.back_btn_exit_game);
        }
    }

    @Override
    public void onInputGameFragmentNextClicked(int position) {
        LogUtils.d("PlayInputGameActivity onInputGameFragmentNextClicked position : " + position);
        int allData = mSelectFragmentList.size();
        if (position == (allData - 1)) {
            List<InputGameBean> inputGameBeanList = new ArrayList<>();
            for (SelectGameBean selectGameBean : mSelectGameBeanList) {
                InputGameBean inputGameBean = selectGameBean.getInputGameBean();
                inputGameBeanList.add(inputGameBean);
            }
            ActivityUtil.startGameOverActivity(this, mAllScore, inputGameBeanList);
            return;
        }
        if ((mCustomViewPager != null) && (position >= 0) && (position < (allData - 1))) {
            mCustomViewPager.setCurrentItem(position + 1);
        }
    }

    @Override
    public void onAnswerRight(boolean isClickDouble) {
        if (isClickDouble) {
            mAllScore = mAllScore + 4;
            mCurrentUser.setScore(mCurrentUser.getScore() + 4);
        } else {
            mAllScore = mAllScore + 2;
            mCurrentUser.setScore(mCurrentUser.getScore() + 2);
        }
        tvScorePlaySelectGame.setText("本关得分：" + String.valueOf(mAllScore));
        CurrentUserHelper.getInstance().updateCurrentUser(mCurrentUser);
        mCurrentUser.setScore(mCurrentUser.getScore());
        mCurrentUser.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    LogUtils.d("PlaySelectGameActivity onAnswerRight 33  update Score success");
                } else {
                    LogUtils.d("PlaySelectGameActivity onAnswerRight 44 pdate Score failed : " + e.getMessage());
                }
            }
        });
    }

    @Override
    public void onCoverCardClicked() {
        if (mALLLinearLayout == null) {return;}
        mALLLinearLayout.setBackground(ResourceUtil.getDrawable(R.drawable.icon_welcom));
    }

    class PlaySelectGameViewPageAdapter extends FragmentPagerAdapter {

        public PlaySelectGameViewPageAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int i) {
            return mSelectFragmentList.get(i);
        }

        @Override
        public int getCount() {
            return mSelectFragmentList.size();
        }
    }
}

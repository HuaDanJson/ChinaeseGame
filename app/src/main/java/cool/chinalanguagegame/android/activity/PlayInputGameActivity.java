package cool.chinalanguagegame.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cool.chinalanguagegame.android.R;
import cool.chinalanguagegame.android.base.BaseActivity;
import cool.chinalanguagegame.android.bean.InputGameBean;
import cool.chinalanguagegame.android.constants.AppConstant;
import cool.chinalanguagegame.android.fragment.InputGameFragment;
import cool.chinalanguagegame.android.utils.ToastHelper;
import cool.chinalanguagegame.android.view.CustomViewPager;

public class PlayInputGameActivity extends BaseActivity implements InputGameFragment.InputGameFragmentListener {

    @BindView(R.id.tv_title) TextView mTitle;
    @BindView(R.id.cvg_play_input_game_activity) CustomViewPager mCustomViewPager;

    private int mType;
    private int mStarCount;

    private PlayInputGameViewPageAdapter mPlayInputGameAdapter;
    private List<InputGameBean> mInputGameBeanList = new ArrayList<>();
    private List<Fragment> mInputFragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_input_game);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent == null) {onBackPressed();}
        mType = intent.getIntExtra(AppConstant.IntentKey.EXTRA_TYPE, 0);
        mStarCount = intent.getIntExtra(AppConstant.IntentKey.EXTRA_STAR, 0);
        if (mType <= 0 || mStarCount <= 0) {
            onBackPressed();
        }
        switch (mType) {
            case 1:
                mTitle.setText("小学成语填空");
                mInputGameBeanList = (ArrayList<InputGameBean>) intent.getSerializableExtra(AppConstant.IntentKey.EXTRA_DATA);
                LogUtils.d("PlayInputGameActivity 1111 mPrimaryWordsList :" + mInputGameBeanList);
                break;
            case 2:
                mTitle.setText("小学诗词填空");
                mInputGameBeanList = (ArrayList<InputGameBean>) intent.getSerializableExtra(AppConstant.IntentKey.EXTRA_DATA);
                LogUtils.d("PlayInputGameActivity 222 mPrimaryPoetryList :" + mInputGameBeanList);
                break;
            case 3:
                mTitle.setText("初中成语填空");
                mInputGameBeanList = (ArrayList<InputGameBean>) intent.getSerializableExtra(AppConstant.IntentKey.EXTRA_DATA);
                LogUtils.d("PlayInputGameActivity 333 mMiddleWordsList :" + mInputGameBeanList);
                break;
            case 4:
                mTitle.setText("初中诗词填空");
                mInputGameBeanList = (ArrayList<InputGameBean>) intent.getSerializableExtra(AppConstant.IntentKey.EXTRA_DATA);
                LogUtils.d("PlayInputGameActivity 444 mMiddlePoetryList :" + mInputGameBeanList);
                break;
            default:
                break;
        }

        initStateTwoViewPager();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.exit_stop_original_place, R.anim.exit_to_right);
    }

    public void initStateTwoViewPager() {
        int inputGameBeanListSize = mInputGameBeanList.size();
        if (inputGameBeanListSize > 0) {
            for (int i = 0; i < inputGameBeanListSize; i++) {
                InputGameFragment inputGameFragment = new InputGameFragment();
                inputGameFragment.setData(i, mInputGameBeanList.get(i));
                inputGameFragment.setInputGameFragmentListener(this);
                mInputFragmentList.add(inputGameFragment);
            }
        }
        mPlayInputGameAdapter = new PlayInputGameViewPageAdapter(getSupportFragmentManager());
        mCustomViewPager.setAdapter(mPlayInputGameAdapter);
        mCustomViewPager.setOffscreenPageLimit(3);
        mCustomViewPager.setCurrentItem(0);
    }

    @Override
    public void onInputGameFragmentBehindClicked(int position) {

    }

    @Override
    public void onInputGameFragmentNextClicked(int position) {
        LogUtils.d("PlayInputGameActivity onInputGameFragmentNextClicked position : " + position);
        int allData = mInputFragmentList.size();
        if (position == (allData - 1)) {
            ToastHelper.showShortMessage("这是最后一道题");
            return;
        }
        if ((mCustomViewPager != null) && (position >= 0) && (position < (allData - 1))) {
            mCustomViewPager.setCurrentItem(position + 1);
        }
    }

    class PlayInputGameViewPageAdapter extends FragmentPagerAdapter {

        public PlayInputGameViewPageAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int i) {
            return mInputFragmentList.get(i);
        }

        @Override
        public int getCount() {
            return mInputFragmentList.size();
        }
    }


}

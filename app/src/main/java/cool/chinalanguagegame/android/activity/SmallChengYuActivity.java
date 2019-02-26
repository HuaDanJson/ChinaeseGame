package cool.chinalanguagegame.android.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.blankj.utilcode.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cool.chinalanguagegame.android.R;
import cool.chinalanguagegame.android.base.BaseActivity;
import cool.chinalanguagegame.android.bean.PrimaryWords;
import cool.chinalanguagegame.android.bean.PrimaryWordsSelect;
import cool.chinalanguagegame.android.fragment.InputGameFragment;
import cool.chinalanguagegame.android.fragment.SelectGameFragment;
import cool.chinalanguagegame.android.utils.ToastHelper;

public class SmallChengYuActivity extends BaseActivity implements InputGameFragment.InputGameFragmentListener {

    @BindView(R.id.vp_small_cheng_yu) ViewPager mViewPager;

    private GameAdapter mGameAdapter;
    private List<Fragment> mExameFragmentList = new ArrayList<>();
    private List<PrimaryWords> mPrimaryWordsList = new ArrayList<>();
    private List<PrimaryWordsSelect> mPrimaryWordsSelectList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_small_cheng_yu);
        ButterKnife.bind(this);
        BmobQuery<PrimaryWords> query = new BmobQuery<>();
        query.setLimit(50).order("createdAt")
                .findObjects(new FindListener<PrimaryWords>() {
                    @Override
                    public void done(List<PrimaryWords> primaryWordsList, BmobException e) {
                        if (e == null) {
                            LogUtils.d("SmallChengYuActivity query successPrimaryWords:" + primaryWordsList.size());
                            LogUtils.d("SmallChengYuActivity query successPrimaryWords:" + primaryWordsList);
                            mPrimaryWordsList = primaryWordsList;
                            BmobQuery<PrimaryWordsSelect> primaryWordsSelectQuery = new BmobQuery<>();
                            primaryWordsSelectQuery.setLimit(50).order("createdAt")
                                    .findObjects(new FindListener<PrimaryWordsSelect>() {
                                        @Override
                                        public void done(List<PrimaryWordsSelect> primaryWordsSelectList, BmobException e) {
                                            if (e == null) {
                                                LogUtils.d("SmallChengYuActivity query PrimaryWordsSelect:" + primaryWordsSelectList.size());
                                                LogUtils.d("SmallChengYuActivity query PrimaryWordsSelect:" + primaryWordsSelectList);
                                                mPrimaryWordsSelectList = primaryWordsSelectList;
                                                initStateTwoViewPager();
                                            } else {
                                                LogUtils.d("SmallChengYuActivity query PrimaryWordsSelect failed");
                                            }
                                        }
                                    });
                        } else {
                            LogUtils.d("SmallChengYuActivity query PrimaryWords failed");
                        }
                    }
                });
    }

    public void initStateTwoViewPager() {
        int primaryWordSize = mPrimaryWordsList.size();
        int primaryWordsSelectSize = mPrimaryWordsSelectList.size();
        if (primaryWordSize > 0) {
            for (int i = 0; i < primaryWordSize; i++) {
                InputGameFragment inputGameFragment = new InputGameFragment();
                inputGameFragment.setData(i + 1, null);
                inputGameFragment.setInputGameFragmentListener(this);
                mExameFragmentList.add(inputGameFragment);
            }
        }
        if (primaryWordsSelectSize > 0) {
            for (int i = 0; i < primaryWordsSelectSize; i++) {
                SelectGameFragment selectGameActivity = new SelectGameFragment();
                selectGameActivity.setData(primaryWordSize + i + 1, null);
                mExameFragmentList.add(selectGameActivity);
            }
        }
        mGameAdapter = new GameAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mGameAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setCurrentItem(0);
    }


    @Override
    public void onAnswerRight(boolean isClickDouble) {

    }

    @Override
    public void onInputGameFragmentNextClicked(int position) {
        int allData = mExameFragmentList.size();
        if (position == allData) {
            ToastHelper.showShortMessage("这是最后一道题");
            return;
        }
        if (mViewPager != null && position > 0 && position < allData) {
            mViewPager.setCurrentItem(position);
        }
    }

    @Override
    public void onCoverCardClicked() {

    }

    class GameAdapter extends FragmentPagerAdapter {

        public GameAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int i) {
            return mExameFragmentList.get(i);
        }

        @Override
        public int getCount() {
            return mExameFragmentList.size();
        }
    }
}

package cool.chinalanguagegame.android.activity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cool.chinalanguagegame.android.R;
import cool.chinalanguagegame.android.base.BaseActivity;
import cool.chinalanguagegame.android.bean.MiddlePoetrySelect;
import cool.chinalanguagegame.android.bean.MiddleWordsSelect;
import cool.chinalanguagegame.android.bean.PrimaryPoetrySelect;
import cool.chinalanguagegame.android.bean.PrimaryWordsSelect;
import cool.chinalanguagegame.android.bean.SelectGameBean;
import cool.chinalanguagegame.android.constants.AppConstant;
import cool.chinalanguagegame.android.utils.ActivityUtil;

public class SelectSelectActivity extends BaseActivity {

    @BindView(R.id.ll_select_one_star) LinearLayout llSelectOneStar;
    @BindView(R.id.ll_select_two_star) LinearLayout llSelectTwoStar;
    @BindView(R.id.ll_select_three_star) LinearLayout llSelectThreeStar;
    @BindView(R.id.tv_title) TextView mTitle;

    private int mType;
    private List<PrimaryWordsSelect> mPrimaryWordsList = new ArrayList<>();
    private List<PrimaryPoetrySelect> mPrimaryPoetryList = new ArrayList<>();
    private List<MiddleWordsSelect> mMiddleWordsList = new ArrayList<>();
    private List<MiddlePoetrySelect> mMiddlePoetryList = new ArrayList<>();

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
                BmobQuery<PrimaryWordsSelect> query = new BmobQuery<>();
                query.setLimit(50).order("createdAt")
                        .findObjects(new FindListener<PrimaryWordsSelect>() {
                            @Override
                            public void done(List<PrimaryWordsSelect> primaryWordsList, BmobException e) {
                                if (e == null) {
                                    mPrimaryWordsList = primaryWordsList;
                                } else {
                                    LogUtils.d("SmallChengYuActivity query PrimaryWords failed");
                                }
                            }
                        });
                break;
            case 2:
                mTitle.setText("小学诗词选择");
                BmobQuery<PrimaryPoetrySelect> query2 = new BmobQuery<>();
                query2.setLimit(50).order("createdAt")
                        .findObjects(new FindListener<PrimaryPoetrySelect>() {
                            @Override
                            public void done(List<PrimaryPoetrySelect> primaryWordsList, BmobException e) {
                                if (e == null) {
                                    mPrimaryPoetryList = primaryWordsList;
                                } else {
                                    LogUtils.d("SmallChengYuActivity query PrimaryWords failed");
                                }
                            }
                        });
                break;
            case 3:
                mTitle.setText("中学成语选择");
                BmobQuery<MiddleWordsSelect> query3 = new BmobQuery<>();
                query3.setLimit(50).order("createdAt")
                        .findObjects(new FindListener<MiddleWordsSelect>() {
                            @Override
                            public void done(List<MiddleWordsSelect> primaryWordsList, BmobException e) {
                                if (e == null) {
                                    mMiddleWordsList = primaryWordsList;
                                } else {
                                    LogUtils.d("SmallChengYuActivity query PrimaryWords failed");
                                }
                            }
                        });
                break;
            case 4:
                mTitle.setText("中学诗词选择");
                BmobQuery<MiddlePoetrySelect> query4 = new BmobQuery<>();
                query4.setLimit(50).order("createdAt")
                        .findObjects(new FindListener<MiddlePoetrySelect>() {
                            @Override
                            public void done(List<MiddlePoetrySelect> primaryWordsList, BmobException e) {
                                if (e == null) {
                                    mMiddlePoetryList = primaryWordsList;
                                } else {
                                    LogUtils.d("SmallChengYuActivity query PrimaryWords failed");
                                }
                            }
                        });
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.ll_select_one_star)
    public void selectOneStartClicked() {
        List<SelectGameBean> selectGameBeanArrayList = new ArrayList<>();
        switch (mType) {
            case 1:
                if (mPrimaryWordsList == null || mPrimaryWordsList.size() < 5) {return;}
                selectGameBeanArrayList.add(mPrimaryWordsList.get(0).getSelectGameBean());
                selectGameBeanArrayList.add(mPrimaryWordsList.get(1).getSelectGameBean());
                selectGameBeanArrayList.add(mPrimaryWordsList.get(2).getSelectGameBean());
                selectGameBeanArrayList.add(mPrimaryWordsList.get(3).getSelectGameBean());
                selectGameBeanArrayList.add(mPrimaryWordsList.get(4).getSelectGameBean());
                ActivityUtil.startPlaySelectGameActivity(this, mType, 1, selectGameBeanArrayList);
                break;
            case 2:
                if (mPrimaryPoetryList == null || mPrimaryPoetryList.size() < 5) {return;}
                selectGameBeanArrayList.add(mPrimaryPoetryList.get(0).getSelectGameBean());
                selectGameBeanArrayList.add(mPrimaryPoetryList.get(1).getSelectGameBean());
                selectGameBeanArrayList.add(mPrimaryPoetryList.get(2).getSelectGameBean());
                selectGameBeanArrayList.add(mPrimaryPoetryList.get(3).getSelectGameBean());
                selectGameBeanArrayList.add(mPrimaryPoetryList.get(4).getSelectGameBean());
                ActivityUtil.startPlaySelectGameActivity(this, mType, 1, selectGameBeanArrayList);
                break;
            case 3:
                if (mMiddleWordsList == null || mMiddleWordsList.size() < 5) {return;}
                selectGameBeanArrayList.add(mMiddleWordsList.get(0).getSelectGameBean());
                selectGameBeanArrayList.add(mMiddleWordsList.get(1).getSelectGameBean());
                selectGameBeanArrayList.add(mMiddleWordsList.get(2).getSelectGameBean());
                selectGameBeanArrayList.add(mMiddleWordsList.get(3).getSelectGameBean());
                selectGameBeanArrayList.add(mMiddleWordsList.get(4).getSelectGameBean());
                ActivityUtil.startPlaySelectGameActivity(this, mType, 1, selectGameBeanArrayList);
                break;
            case 4:
                if (mMiddlePoetryList == null || mMiddlePoetryList.size() < 5) {return;}
                selectGameBeanArrayList.add(mMiddlePoetryList.get(0).getSelectGameBean());
                selectGameBeanArrayList.add(mMiddlePoetryList.get(1).getSelectGameBean());
                selectGameBeanArrayList.add(mMiddlePoetryList.get(2).getSelectGameBean());
                selectGameBeanArrayList.add(mMiddlePoetryList.get(3).getSelectGameBean());
                selectGameBeanArrayList.add(mMiddlePoetryList.get(4).getSelectGameBean());
                ActivityUtil.startPlaySelectGameActivity(this, mType, 1, selectGameBeanArrayList);
                break;
            default:
                break;

        }
    }

    @OnClick(R.id.ll_select_two_star)
    public void selectTwoStartClicked() {
        List<SelectGameBean> selectGameBeanArrayList = new ArrayList<>();
        switch (mType) {
            case 1:
                if (mPrimaryWordsList == null || mPrimaryWordsList.size() < 10) {return;}
                selectGameBeanArrayList.add(mPrimaryWordsList.get(5).getSelectGameBean());
                selectGameBeanArrayList.add(mPrimaryWordsList.get(6).getSelectGameBean());
                selectGameBeanArrayList.add(mPrimaryWordsList.get(7).getSelectGameBean());
                selectGameBeanArrayList.add(mPrimaryWordsList.get(8).getSelectGameBean());
                selectGameBeanArrayList.add(mPrimaryWordsList.get(9).getSelectGameBean());
                ActivityUtil.startPlaySelectGameActivity(this, mType, 2, selectGameBeanArrayList);
                break;
            case 2:
                if (mPrimaryPoetryList == null || mPrimaryPoetryList.size() < 10) {return;}
                selectGameBeanArrayList.add(mPrimaryPoetryList.get(5).getSelectGameBean());
                selectGameBeanArrayList.add(mPrimaryPoetryList.get(6).getSelectGameBean());
                selectGameBeanArrayList.add(mPrimaryPoetryList.get(7).getSelectGameBean());
                selectGameBeanArrayList.add(mPrimaryPoetryList.get(8).getSelectGameBean());
                selectGameBeanArrayList.add(mPrimaryPoetryList.get(9).getSelectGameBean());
                ActivityUtil.startPlaySelectGameActivity(this, mType, 2, selectGameBeanArrayList);
                break;
            case 3:
                if (mMiddleWordsList == null || mMiddleWordsList.size() < 10) {return;}
                selectGameBeanArrayList.add(mMiddleWordsList.get(5).getSelectGameBean());
                selectGameBeanArrayList.add(mMiddleWordsList.get(6).getSelectGameBean());
                selectGameBeanArrayList.add(mMiddleWordsList.get(7).getSelectGameBean());
                selectGameBeanArrayList.add(mMiddleWordsList.get(8).getSelectGameBean());
                selectGameBeanArrayList.add(mMiddleWordsList.get(9).getSelectGameBean());
                ActivityUtil.startPlaySelectGameActivity(this, mType, 2, selectGameBeanArrayList);
                break;
            case 4:
                if (mMiddlePoetryList == null || mMiddlePoetryList.size() < 10) {return;}
                selectGameBeanArrayList.add(mMiddlePoetryList.get(5).getSelectGameBean());
                selectGameBeanArrayList.add(mMiddlePoetryList.get(6).getSelectGameBean());
                selectGameBeanArrayList.add(mMiddlePoetryList.get(7).getSelectGameBean());
                selectGameBeanArrayList.add(mMiddlePoetryList.get(8).getSelectGameBean());
                selectGameBeanArrayList.add(mMiddlePoetryList.get(9).getSelectGameBean());
                ActivityUtil.startPlaySelectGameActivity(this, mType, 2, selectGameBeanArrayList);
                break;
            default:
                break;

        }
    }

    @OnClick(R.id.ll_select_three_star)
    public void selectThreeClicked() {
        List<SelectGameBean> selectGameBeanArrayList = new ArrayList<>();
        switch (mType) {
            case 1:
                if (mPrimaryWordsList == null || mPrimaryWordsList.size() < 15) {return;}
                selectGameBeanArrayList.add(mPrimaryWordsList.get(10).getSelectGameBean());
                selectGameBeanArrayList.add(mPrimaryWordsList.get(11).getSelectGameBean());
                selectGameBeanArrayList.add(mPrimaryWordsList.get(12).getSelectGameBean());
                selectGameBeanArrayList.add(mPrimaryWordsList.get(13).getSelectGameBean());
                selectGameBeanArrayList.add(mPrimaryWordsList.get(14).getSelectGameBean());
                ActivityUtil.startPlaySelectGameActivity(this, mType, 3, selectGameBeanArrayList);
                break;
            case 2:
                if (mPrimaryPoetryList == null || mPrimaryPoetryList.size() < 15) {return;}
                selectGameBeanArrayList.add(mPrimaryPoetryList.get(10).getSelectGameBean());
                selectGameBeanArrayList.add(mPrimaryPoetryList.get(11).getSelectGameBean());
                selectGameBeanArrayList.add(mPrimaryPoetryList.get(12).getSelectGameBean());
                selectGameBeanArrayList.add(mPrimaryPoetryList.get(13).getSelectGameBean());
                selectGameBeanArrayList.add(mPrimaryPoetryList.get(14).getSelectGameBean());
                ActivityUtil.startPlaySelectGameActivity(this, mType, 3, selectGameBeanArrayList);
                break;
            case 3:
                if (mMiddleWordsList == null || mMiddleWordsList.size() < 15) {return;}
                selectGameBeanArrayList.add(mMiddleWordsList.get(10).getSelectGameBean());
                selectGameBeanArrayList.add(mMiddleWordsList.get(11).getSelectGameBean());
                selectGameBeanArrayList.add(mMiddleWordsList.get(12).getSelectGameBean());
                selectGameBeanArrayList.add(mMiddleWordsList.get(13).getSelectGameBean());
                selectGameBeanArrayList.add(mMiddleWordsList.get(14).getSelectGameBean());
                ActivityUtil.startPlaySelectGameActivity(this, mType, 3, selectGameBeanArrayList);
                break;
            case 4:
                if (mMiddlePoetryList == null || mMiddlePoetryList.size() < 15) {return;}
                selectGameBeanArrayList.add(mMiddlePoetryList.get(10).getSelectGameBean());
                selectGameBeanArrayList.add(mMiddlePoetryList.get(11).getSelectGameBean());
                selectGameBeanArrayList.add(mMiddlePoetryList.get(12).getSelectGameBean());
                selectGameBeanArrayList.add(mMiddlePoetryList.get(13).getSelectGameBean());
                selectGameBeanArrayList.add(mMiddlePoetryList.get(14).getSelectGameBean());
                ActivityUtil.startPlaySelectGameActivity(this, mType, 3, selectGameBeanArrayList);
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

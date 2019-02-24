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
import cool.chinalanguagegame.android.bean.InputGameBean;
import cool.chinalanguagegame.android.bean.MiddlePoetry;
import cool.chinalanguagegame.android.bean.MiddleWords;
import cool.chinalanguagegame.android.bean.PrimaryPoetry;
import cool.chinalanguagegame.android.bean.PrimaryWords;
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

    private List<PrimaryWords> mPrimaryWordsList = new ArrayList<>();
    private List<PrimaryPoetry> mPrimaryPoetryList = new ArrayList<>();
    private List<MiddleWords> mMiddleWordsList = new ArrayList<>();
    private List<MiddlePoetry> mMiddlePoetryList = new ArrayList<>();

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
                BmobQuery<PrimaryWords> query = new BmobQuery<>();
                query.setLimit(50).order("createdAt")
                        .findObjects(new FindListener<PrimaryWords>() {
                            @Override
                            public void done(List<PrimaryWords> primaryWordsList, BmobException e) {
                                if (e == null) {
                                    mPrimaryWordsList = primaryWordsList;
                                } else {
                                    LogUtils.d("SmallChengYuActivity query PrimaryWords failed");
                                }
                            }
                        });
                break;
            case 2:
                mTitle.setText("小学诗词填空");
                BmobQuery<PrimaryPoetry> query2 = new BmobQuery<>();
                query2.setLimit(50).order("createdAt")
                        .findObjects(new FindListener<PrimaryPoetry>() {
                            @Override
                            public void done(List<PrimaryPoetry> primaryWordsList, BmobException e) {
                                if (e == null) {
                                    mPrimaryPoetryList = primaryWordsList;
                                } else {
                                    LogUtils.d("SmallChengYuActivity query PrimaryWords failed");
                                }
                            }
                        });
                break;
            case 3:
                mTitle.setText("初中成语填空");
                BmobQuery<MiddleWords> query3 = new BmobQuery<>();
                query3.setLimit(50).order("createdAt")
                        .findObjects(new FindListener<MiddleWords>() {
                            @Override
                            public void done(List<MiddleWords> primaryWordsList, BmobException e) {
                                if (e == null) {
                                    mMiddleWordsList = primaryWordsList;
                                } else {
                                    LogUtils.d("SmallChengYuActivity query PrimaryWords failed");
                                }
                            }
                        });
                break;
            case 4:
                mTitle.setText("初中诗词填空");
                BmobQuery<MiddlePoetry> query4 = new BmobQuery<>();
                query4.setLimit(50).order("createdAt")
                        .findObjects(new FindListener<MiddlePoetry>() {
                            @Override
                            public void done(List<MiddlePoetry> primaryWordsList, BmobException e) {
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
        List<InputGameBean> inputGameBeanList = new ArrayList<>();
        switch (mType) {
            case 1:
                if (mPrimaryWordsList == null || mPrimaryWordsList.size() < 5) {return;}
                inputGameBeanList.add(mPrimaryWordsList.get(0).getInputGameBean());
                inputGameBeanList.add(mPrimaryWordsList.get(1).getInputGameBean());
                inputGameBeanList.add(mPrimaryWordsList.get(2).getInputGameBean());
                inputGameBeanList.add(mPrimaryWordsList.get(3).getInputGameBean());
                inputGameBeanList.add(mPrimaryWordsList.get(4).getInputGameBean());
                ActivityUtil.startPlayInputGameActivity(this, mType, 1, inputGameBeanList);
                break;
            case 2:
                if (mPrimaryPoetryList == null || mPrimaryPoetryList.size() < 5) {return;}
                inputGameBeanList.add(mPrimaryPoetryList.get(0).getInputGameBean());
                inputGameBeanList.add(mPrimaryPoetryList.get(1).getInputGameBean());
                inputGameBeanList.add(mPrimaryPoetryList.get(2).getInputGameBean());
                inputGameBeanList.add(mPrimaryPoetryList.get(3).getInputGameBean());
                inputGameBeanList.add(mPrimaryPoetryList.get(4).getInputGameBean());
                ActivityUtil.startPlayInputGameActivity(this, mType, 1, inputGameBeanList);
                break;
            case 3:
                if (mMiddleWordsList == null || mMiddleWordsList.size() < 5) {return;}
                inputGameBeanList.add(mMiddleWordsList.get(0).getInputGameBean());
                inputGameBeanList.add(mMiddleWordsList.get(1).getInputGameBean());
                inputGameBeanList.add(mMiddleWordsList.get(2).getInputGameBean());
                inputGameBeanList.add(mMiddleWordsList.get(3).getInputGameBean());
                inputGameBeanList.add(mMiddleWordsList.get(4).getInputGameBean());
                ActivityUtil.startPlayInputGameActivity(this, mType, 1, inputGameBeanList);
                break;
            case 4:
                if (mMiddlePoetryList == null || mMiddlePoetryList.size() < 5) {return;}
                inputGameBeanList.add(mMiddlePoetryList.get(0).getInputGameBean());
                inputGameBeanList.add(mMiddlePoetryList.get(1).getInputGameBean());
                inputGameBeanList.add(mMiddlePoetryList.get(2).getInputGameBean());
                inputGameBeanList.add(mMiddlePoetryList.get(3).getInputGameBean());
                inputGameBeanList.add(mMiddlePoetryList.get(4).getInputGameBean());
                ActivityUtil.startPlayInputGameActivity(this, mType, 1, inputGameBeanList);
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.ll_select_two_star)
    public void selectTwoStartClicked() {
        List<InputGameBean> inputGameBeanList = new ArrayList<>();
        switch (mType) {
            case 1:
                if (mPrimaryWordsList == null || mPrimaryWordsList.size() < 10) {return;}
                inputGameBeanList.add(mPrimaryWordsList.get(5).getInputGameBean());
                inputGameBeanList.add(mPrimaryWordsList.get(6).getInputGameBean());
                inputGameBeanList.add(mPrimaryWordsList.get(7).getInputGameBean());
                inputGameBeanList.add(mPrimaryWordsList.get(8).getInputGameBean());
                inputGameBeanList.add(mPrimaryWordsList.get(9).getInputGameBean());
                ActivityUtil.startPlayInputGameActivity(this, mType, 2, inputGameBeanList);
                break;
            case 2:
                if (mPrimaryPoetryList == null || mPrimaryPoetryList.size() < 10) {return;}
                inputGameBeanList.add(mPrimaryPoetryList.get(5).getInputGameBean());
                inputGameBeanList.add(mPrimaryPoetryList.get(6).getInputGameBean());
                inputGameBeanList.add(mPrimaryPoetryList.get(7).getInputGameBean());
                inputGameBeanList.add(mPrimaryPoetryList.get(8).getInputGameBean());
                inputGameBeanList.add(mPrimaryPoetryList.get(9).getInputGameBean());
                ActivityUtil.startPlayInputGameActivity(this, mType, 2, inputGameBeanList);
                break;
            case 3:
                if (mMiddleWordsList == null || mMiddleWordsList.size() < 10) {return;}
                inputGameBeanList.add(mMiddleWordsList.get(5).getInputGameBean());
                inputGameBeanList.add(mMiddleWordsList.get(6).getInputGameBean());
                inputGameBeanList.add(mMiddleWordsList.get(7).getInputGameBean());
                inputGameBeanList.add(mMiddleWordsList.get(8).getInputGameBean());
                inputGameBeanList.add(mMiddleWordsList.get(9).getInputGameBean());
                ActivityUtil.startPlayInputGameActivity(this, mType, 2, inputGameBeanList);
                break;
            case 4:
                if (mMiddlePoetryList == null || mMiddlePoetryList.size() < 10) {return;}
                inputGameBeanList.add(mMiddlePoetryList.get(5).getInputGameBean());
                inputGameBeanList.add(mMiddlePoetryList.get(6).getInputGameBean());
                inputGameBeanList.add(mMiddlePoetryList.get(7).getInputGameBean());
                inputGameBeanList.add(mMiddlePoetryList.get(8).getInputGameBean());
                inputGameBeanList.add(mMiddlePoetryList.get(4).getInputGameBean());
                ActivityUtil.startPlayInputGameActivity(this, mType, 2, inputGameBeanList);
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.ll_select_three_star)
    public void selectThreeStartClicked() {
        List<InputGameBean> inputGameBeanList = new ArrayList<>();
        switch (mType) {
            case 1:
                if (mPrimaryWordsList == null || mPrimaryWordsList.size() < 10) {return;}
                inputGameBeanList.add(mPrimaryWordsList.get(10).getInputGameBean());
                inputGameBeanList.add(mPrimaryWordsList.get(11).getInputGameBean());
                inputGameBeanList.add(mPrimaryWordsList.get(12).getInputGameBean());
                inputGameBeanList.add(mPrimaryWordsList.get(13).getInputGameBean());
                inputGameBeanList.add(mPrimaryWordsList.get(14).getInputGameBean());
                ActivityUtil.startPlayInputGameActivity(this, mType, 3, inputGameBeanList);
                break;
            case 2:
                if (mPrimaryPoetryList == null || mPrimaryPoetryList.size() < 15) {return;}
                inputGameBeanList.add(mPrimaryPoetryList.get(10).getInputGameBean());
                inputGameBeanList.add(mPrimaryPoetryList.get(11).getInputGameBean());
                inputGameBeanList.add(mPrimaryPoetryList.get(12).getInputGameBean());
                inputGameBeanList.add(mPrimaryPoetryList.get(13).getInputGameBean());
                inputGameBeanList.add(mPrimaryPoetryList.get(14).getInputGameBean());
                ActivityUtil.startPlayInputGameActivity(this, mType, 3, inputGameBeanList);
                break;
            case 3:
                if (mMiddleWordsList == null || mMiddleWordsList.size() < 15) {return;}
                inputGameBeanList.add(mMiddleWordsList.get(10).getInputGameBean());
                inputGameBeanList.add(mMiddleWordsList.get(11).getInputGameBean());
                inputGameBeanList.add(mMiddleWordsList.get(12).getInputGameBean());
                inputGameBeanList.add(mMiddleWordsList.get(13).getInputGameBean());
                inputGameBeanList.add(mMiddleWordsList.get(14).getInputGameBean());
                ActivityUtil.startPlayInputGameActivity(this, mType, 3, inputGameBeanList);
                break;
            case 4:
                if (mMiddlePoetryList == null || mMiddlePoetryList.size() < 15) {return;}
                inputGameBeanList.add(mMiddlePoetryList.get(10).getInputGameBean());
                inputGameBeanList.add(mMiddlePoetryList.get(11).getInputGameBean());
                inputGameBeanList.add(mMiddlePoetryList.get(12).getInputGameBean());
                inputGameBeanList.add(mMiddlePoetryList.get(13).getInputGameBean());
                inputGameBeanList.add(mMiddlePoetryList.get(14).getInputGameBean());
                ActivityUtil.startPlayInputGameActivity(this, mType, 3, inputGameBeanList);
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.ll_select_four_star)
    public void selectFourStartClicked() {
        List<InputGameBean> inputGameBeanList = new ArrayList<>();
        switch (mType) {
            case 1:
                if (mPrimaryWordsList == null || mPrimaryWordsList.size() < 20) {return;}
                inputGameBeanList.add(mPrimaryWordsList.get(15).getInputGameBean());
                inputGameBeanList.add(mPrimaryWordsList.get(16).getInputGameBean());
                inputGameBeanList.add(mPrimaryWordsList.get(17).getInputGameBean());
                inputGameBeanList.add(mPrimaryWordsList.get(18).getInputGameBean());
                inputGameBeanList.add(mPrimaryWordsList.get(19).getInputGameBean());
                ActivityUtil.startPlayInputGameActivity(this, mType, 4, inputGameBeanList);
                break;
            case 2:
                if (mPrimaryPoetryList == null || mPrimaryPoetryList.size() < 20) {return;}
                inputGameBeanList.add(mPrimaryPoetryList.get(15).getInputGameBean());
                inputGameBeanList.add(mPrimaryPoetryList.get(16).getInputGameBean());
                inputGameBeanList.add(mPrimaryPoetryList.get(17).getInputGameBean());
                inputGameBeanList.add(mPrimaryPoetryList.get(18).getInputGameBean());
                inputGameBeanList.add(mPrimaryPoetryList.get(19).getInputGameBean());
                ActivityUtil.startPlayInputGameActivity(this, mType, 4, inputGameBeanList);
                break;
            case 3:
                if (mMiddleWordsList == null || mMiddleWordsList.size() < 20) {return;}
                inputGameBeanList.add(mMiddleWordsList.get(15).getInputGameBean());
                inputGameBeanList.add(mMiddleWordsList.get(16).getInputGameBean());
                inputGameBeanList.add(mMiddleWordsList.get(17).getInputGameBean());
                inputGameBeanList.add(mMiddleWordsList.get(18).getInputGameBean());
                inputGameBeanList.add(mMiddleWordsList.get(19).getInputGameBean());
                ActivityUtil.startPlayInputGameActivity(this, mType, 4, inputGameBeanList);
                break;
            case 4:
                if (mMiddlePoetryList == null || mMiddlePoetryList.size() < 20) {return;}
                inputGameBeanList.add(mMiddlePoetryList.get(15).getInputGameBean());
                inputGameBeanList.add(mMiddlePoetryList.get(16).getInputGameBean());
                inputGameBeanList.add(mMiddlePoetryList.get(17).getInputGameBean());
                inputGameBeanList.add(mMiddlePoetryList.get(18).getInputGameBean());
                inputGameBeanList.add(mMiddlePoetryList.get(19).getInputGameBean());
                ActivityUtil.startPlayInputGameActivity(this, mType, 4, inputGameBeanList);
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.ll_select_five_star)
    public void selectFiveStartClicked() {
        List<InputGameBean> inputGameBeanList = new ArrayList<>();
        switch (mType) {
            case 1:
                if (mPrimaryWordsList == null || mPrimaryWordsList.size() < 25) {return;}
                inputGameBeanList.add(mPrimaryWordsList.get(20).getInputGameBean());
                inputGameBeanList.add(mPrimaryWordsList.get(21).getInputGameBean());
                inputGameBeanList.add(mPrimaryWordsList.get(22).getInputGameBean());
                inputGameBeanList.add(mPrimaryWordsList.get(23).getInputGameBean());
                inputGameBeanList.add(mPrimaryWordsList.get(24).getInputGameBean());
                ActivityUtil.startPlayInputGameActivity(this, mType, 5, inputGameBeanList);
                break;
            case 2:
                if (mPrimaryPoetryList == null || mPrimaryPoetryList.size() < 25) {return;}
                inputGameBeanList.add(mPrimaryPoetryList.get(20).getInputGameBean());
                inputGameBeanList.add(mPrimaryPoetryList.get(21).getInputGameBean());
                inputGameBeanList.add(mPrimaryPoetryList.get(22).getInputGameBean());
                inputGameBeanList.add(mPrimaryPoetryList.get(23).getInputGameBean());
                inputGameBeanList.add(mPrimaryPoetryList.get(24).getInputGameBean());
                ActivityUtil.startPlayInputGameActivity(this, mType, 5, inputGameBeanList);
                break;
            case 3:
                if (mMiddleWordsList == null || mMiddleWordsList.size() < 25) {return;}
                inputGameBeanList.add(mMiddleWordsList.get(20).getInputGameBean());
                inputGameBeanList.add(mMiddleWordsList.get(21).getInputGameBean());
                inputGameBeanList.add(mMiddleWordsList.get(22).getInputGameBean());
                inputGameBeanList.add(mMiddleWordsList.get(23).getInputGameBean());
                inputGameBeanList.add(mMiddleWordsList.get(24).getInputGameBean());
                ActivityUtil.startPlayInputGameActivity(this, mType, 5, inputGameBeanList);
                break;
            case 4:
                if (mMiddlePoetryList == null || mMiddlePoetryList.size() < 25) {return;}
                inputGameBeanList.add(mMiddlePoetryList.get(20).getInputGameBean());
                inputGameBeanList.add(mMiddlePoetryList.get(21).getInputGameBean());
                inputGameBeanList.add(mMiddlePoetryList.get(22).getInputGameBean());
                inputGameBeanList.add(mMiddlePoetryList.get(23).getInputGameBean());
                inputGameBeanList.add(mMiddlePoetryList.get(24).getInputGameBean());
                ActivityUtil.startPlayInputGameActivity(this, mType, 5, inputGameBeanList);
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

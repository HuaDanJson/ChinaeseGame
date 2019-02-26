package cool.chinalanguagegame.android.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cool.chinalanguagegame.android.R;
import cool.chinalanguagegame.android.base.BaseActivity;
import cool.chinalanguagegame.android.bean.CurrentUser;
import cool.chinalanguagegame.android.utils.CurrentUserHelper;
import cool.chinalanguagegame.android.utils.ToastHelper;

public class StoreActivity extends BaseActivity {

    @BindView(R.id.tv_all_score) TextView mAllScore;
    @BindView(R.id.btn_buy_help_card) Button mBuyHelpCard;
    @BindView(R.id.btn_surplus_help_card) Button mSurplusHelpCard;
    @BindView(R.id.btn_buy_double_card) Button mBuyDoubleCard;
    @BindView(R.id.btn_surplus_double_card) Button mSurplusDoubleCard;
    @BindView(R.id.btn_buy_background_card) Button mBuyBackgroundCard;
    @BindView(R.id.btn_surplus_background_card) Button mSurplusBackgroundCard;

    private CurrentUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        ButterKnife.bind(this);
        mCurrentUser = CurrentUserHelper.getInstance().getCurrentUser();
        if (mCurrentUser == null) {finish();}
        mAllScore.setText("总分：" + mCurrentUser.getScore());
        mSurplusHelpCard.setText("拥有：" + mCurrentUser.getHelperCards());
        mSurplusDoubleCard.setText("拥有：" + mCurrentUser.getDoubleCards());
        mSurplusBackgroundCard.setText("拥有：" + mCurrentUser.getTextColor());
    }

    @OnClick(R.id.btn_buy_help_card)
    public void buyHelpCardClicked() {
        int score = mCurrentUser.getScore();
        if (score < 2) {
            ToastHelper.showShortMessage("积分不足");
        } else {
            mCurrentUser.setScore(score - 2);
            mCurrentUser.setHelperCards(mCurrentUser.getHelperCards() + 1);
            mAllScore.setText("总分：" + mCurrentUser.getScore());
            mSurplusHelpCard.setText("拥有：" + mCurrentUser.getHelperCards());
            CurrentUserHelper.getInstance().updateCurrentUser(mCurrentUser);
            mCurrentUser.setScore(mCurrentUser.getScore());
            mCurrentUser.update(new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        LogUtils.d("PlayInputGameActivity onAnswerRight update Score success");
                    } else {
                        LogUtils.d("PlayInputGameActivity onAnswerRight update Score failed : " + e.getMessage());
                    }
                }
            });
        }
    }

    @OnClick(R.id.btn_buy_double_card)
    public void buyDoubleClicked() {
        int score = mCurrentUser.getScore();
        if (score < 4) {
            ToastHelper.showShortMessage("积分不足");
        } else {
            mCurrentUser.setScore(score - 4);
            mCurrentUser.setDoubleCards(mCurrentUser.getDoubleCards() + 1);
            mAllScore.setText("总分：" + mCurrentUser.getScore());
            mSurplusDoubleCard.setText("拥有：" + mCurrentUser.getDoubleCards());
            CurrentUserHelper.getInstance().updateCurrentUser(mCurrentUser);
            mCurrentUser.setScore(mCurrentUser.getScore());
            mCurrentUser.update(new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        LogUtils.d("PlayInputGameActivity onAnswerRight update Score success");
                    } else {
                        LogUtils.d("PlayInputGameActivity onAnswerRight update Score failed : " + e.getMessage());
                    }
                }
            });
        }
    }

    @OnClick(R.id.btn_buy_background_card)
    public void buyBackgroundClicked() {
        int score = mCurrentUser.getScore();
        if (score < 6) {
            ToastHelper.showShortMessage("积分不足");
        } else {
            mCurrentUser.setScore(score - 6);
            mCurrentUser.setTextColor(mCurrentUser.getTextColor() + 1);
            mAllScore.setText("总分：" + mCurrentUser.getScore());
            mSurplusBackgroundCard.setText("拥有：" + mCurrentUser.getTextColor());
            CurrentUserHelper.getInstance().updateCurrentUser(mCurrentUser);
            mCurrentUser.setScore(mCurrentUser.getScore());
            mCurrentUser.update(new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        LogUtils.d("PlayInputGameActivity onAnswerRight update Score success");
                    } else {
                        LogUtils.d("PlayInputGameActivity onAnswerRight update Score failed : " + e.getMessage());
                    }
                }
            });
        }
    }

}

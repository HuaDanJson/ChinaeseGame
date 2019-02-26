package cool.chinalanguagegame.android.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cool.chinalanguagegame.android.R;
import cool.chinalanguagegame.android.base.BaseFragmentDialog;
import cool.chinalanguagegame.android.bean.CurrentUser;
import cool.chinalanguagegame.android.utils.CurrentUserHelper;
import cool.chinalanguagegame.android.utils.ToastHelper;

public class ToolsDialog extends BaseFragmentDialog {

    @BindView(R.id.tv_helper_card_dialog) TextView mHelperCard;
    @BindView(R.id.tv_double_card_dialog) TextView mDoubleCard;
    @BindView(R.id.tv_cover_card_dialog) TextView mCoverCard;
    @BindView(R.id.rl_tools_dialog) RelativeLayout mToolsDialog;
    Unbinder unbinder;
    public ToolsDialogListener mListener;
    private boolean mHasSavedInstanceState;
    private boolean mIsClickedDouble;
    private boolean mIsClickedCover;
    private boolean mIsClickedHelper;
    private CurrentUser mCurrentUser;

    @Override
    protected boolean hasSavedInstanceState() {
        return mHasSavedInstanceState;
    }

    public void setSavedInstanceState(boolean hasSavedInstanceState) {
        this.mHasSavedInstanceState = hasSavedInstanceState;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.dialog_tools;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mCurrentUser = CurrentUserHelper.getInstance().getCurrentUser();
        if (mCurrentUser == null) {
            ToastHelper.showShortMessage("获取用户信息失败");
            tryHide();
        }
        mHelperCard.setText("帮助卡 (剩余：" + mCurrentUser.getHelperCards() + ")");
        mDoubleCard.setText("双倍卡 (剩余：" + mCurrentUser.getDoubleCards() + ")");
        if (mCurrentUser.getTextColor() <= 0) {
            mCoverCard.setText("更换背景 (未购买)");
        } else {
            mCoverCard.setText("更换背景 (已购买)");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.tv_helper_card_dialog)
    public void onHelperCardClicked(View view) {
        if (mIsClickedHelper) {
            ToastHelper.showShortMessage("您已选择帮助卡");
            return;
        }
        if (mCurrentUser.getHelperCards() <= 0) {
            ToastHelper.showShortMessage("帮助卡不足");
            return;
        }
        if (mListener != null) {
            mListener.onHelperCardClicked();
            mCurrentUser.setHelperCards(mCurrentUser.getHelperCards() - 1);
            CurrentUserHelper.getInstance().updateCurrentUser(mCurrentUser);
            mHelperCard.setText("帮助卡 (剩余：" + mCurrentUser.getHelperCards() + ")");
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
            tryHide();
        }
    }

    @OnClick(R.id.tv_double_card_dialog)
    public void onDoubleCardClicked(View view) {
        if (mIsClickedDouble) {
            ToastHelper.showShortMessage("您已选择双倍卡");
            return;
        }
        if (mCurrentUser.getDoubleCards() <= 0) {
            ToastHelper.showShortMessage("双倍卡不足");
            return;
        }
        if (mListener != null) {
            mListener.onDoubleCardClicked();
            mCurrentUser.setDoubleCards(mCurrentUser.getDoubleCards() - 1);
            CurrentUserHelper.getInstance().updateCurrentUser(mCurrentUser);
            mDoubleCard.setText("双倍卡 (剩余：" + mCurrentUser.getDoubleCards() + ")");
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
            tryHide();
        }
    }

    @OnClick(R.id.tv_cover_card_dialog)
    public void onCoverCardClicked(View view) {
        if (mIsClickedCover) {
            ToastHelper.showShortMessage("您已更换背景");
            return;
        }

        if (mCurrentUser.getTextColor() <= 0) {
            ToastHelper.showShortMessage("未购买背景");
            return;
        }
        if (mListener != null) {
            mListener.onCoverCardClicked();
            tryHide();
        }
    }

    @OnClick(R.id.rl_tools_dialog)
    public void onCancelClicked(View view) {
        tryHide();
    }

    public interface ToolsDialogListener {

        void onHelperCardClicked();

        void onDoubleCardClicked();

        void onCoverCardClicked();

    }

    public void setToolsDialogListener(ToolsDialogListener listener) {
        this.mListener = listener;
    }

    public void setData(boolean isClickedHelper, boolean isClickedDouble, boolean isClickedCover) {
        mIsClickedHelper = isClickedHelper;
        mIsClickedDouble = isClickedDouble;
        mIsClickedCover = isClickedCover;
    }
}

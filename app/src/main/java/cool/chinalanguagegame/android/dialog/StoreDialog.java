package cool.chinalanguagegame.android.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cool.chinalanguagegame.android.R;
import cool.chinalanguagegame.android.base.BaseFragmentDialog;
import cool.chinalanguagegame.android.utils.ActivityUtil;

public class StoreDialog extends BaseFragmentDialog {

    @BindView(R.id.tv_title_store_dialog) TextView mStoreTitle;
    @BindView(R.id.rl_store_dialog) RelativeLayout mLogoutDialog;
    private int mType;
    Unbinder unbinder;

    private boolean mHasSavedInstanceState;

    @Override
    protected boolean hasSavedInstanceState() {
        return mHasSavedInstanceState;
    }

    public void setSavedInstanceState(boolean hasSavedInstanceState) {
        this.mHasSavedInstanceState = hasSavedInstanceState;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.dialog_store;
    }

    public void setData(int type){
        this.mType = type;
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.rl_store_dialog)
    public void onHidleClicked(View view) {
        tryHide();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mStoreTitle==null){return;}
        switch (mType){
            case 1:
                mStoreTitle.setText("使用帮助卡可以帮助用户选择当前题的正确答案，并记入相应分数，每张卡仅限一道题目有效");
                break;
            case 2:
                mStoreTitle.setText("使用双倍卡可以增加用户当前题目的分值，如回答正确，则记入本题的双倍分数，每张卡仅限一道题目有效");
                break;
            case 3:
                mStoreTitle.setText("使用更换背景可以更改当前游戏界面的背景图片，每次更换仅限一个游戏关卡有效");
                break;
                default:
                    break;
        }
    }
}

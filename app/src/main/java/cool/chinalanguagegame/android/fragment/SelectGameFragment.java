package cool.chinalanguagegame.android.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cool.chinalanguagegame.android.R;
import cool.chinalanguagegame.android.base.BaseFragment;
import cool.chinalanguagegame.android.bean.PrimaryWordsSelect;

public class SelectGameFragment extends BaseFragment implements View.OnTouchListener {

    Unbinder unbinder;
    @BindView(R.id.tv_select_game) TextView mSelectGame;
    private int mPosition;
    private PrimaryWordsSelect mPrimaryWordsSelect;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.select_game_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setOnTouchListener(this);
        mSelectGame.setText(String.valueOf(mPosition) + "\n" + mPrimaryWordsSelect);
    }

    public void setData(int position, PrimaryWordsSelect primaryWordsSelect) {
        this.mPosition = position;
        this.mPrimaryWordsSelect = primaryWordsSelect;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

package cool.chinalanguagegame.android.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cool.chinalanguagegame.android.R;
import cool.chinalanguagegame.android.base.BaseFragment;
import cool.chinalanguagegame.android.bean.PrimaryWords;

public class InputGameFragment extends BaseFragment implements View.OnTouchListener {

    @BindView(R.id.tv_question_input_game_fragment) TextView mQuestiont;
    @BindView(R.id.edt_input_answer_input_game_fragment) EditText mInputEditText;
    @BindView(R.id.tv_note_input_game_fragment) TextView mNote;
    @BindView(R.id.btn_behind_question) Button mBehindQuestion;
    @BindView(R.id.btn_next_question) Button mNextQuestion;
    public InputGameFragmentListener mListener;
    private int mPosition;
    private PrimaryWords mPrimaryWords;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.input_game_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setOnTouchListener(this);
        initView();
    }

    public void initView() {
        if (mPrimaryWords != null) {
            mQuestiont.setText(mPosition + "." + mPrimaryWords.getQuestion());
            mNote.setText("解释：" + mPrimaryWords.getNote());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void setData(int position, PrimaryWords primaryWords) {
        this.mPosition = position;
        this.mPrimaryWords = primaryWords;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    @OnClick(R.id.btn_behind_question)
    public void onBehindClicked() {
        if (mListener != null) {
            mListener.onInputGameFragmentBehindClicked(mPosition);
        }
    }

    @OnClick(R.id.btn_next_question)
    public void onNextClicked() {
        if (mListener != null) {
            mListener.onInputGameFragmentNextClicked(mPosition);
        }
    }

    public interface InputGameFragmentListener {

        void onInputGameFragmentBehindClicked(int position);

        void onInputGameFragmentNextClicked(int position);

    }

    public void setInputGameFragmentListener(InputGameFragmentListener listener) {
        this.mListener = listener;
    }
}

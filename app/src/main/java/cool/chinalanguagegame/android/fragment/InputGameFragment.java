package cool.chinalanguagegame.android.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cool.chinalanguagegame.android.R;
import cool.chinalanguagegame.android.base.BaseFragment;
import cool.chinalanguagegame.android.bean.InputGameBean;
import cool.chinalanguagegame.android.dialog.ToolsDialog;
import cool.chinalanguagegame.android.utils.ToastHelper;

public class InputGameFragment extends BaseFragment implements View.OnTouchListener, ToolsDialog.ToolsDialogListener {

    @BindView(R.id.tv_question_input_game_fragment) TextView mQuestiont;
    @BindView(R.id.edt_input_answer_input_game_fragment) EditText mInputEditText;
    @BindView(R.id.tv_note_input_game_fragment) TextView mNote;
    @BindView(R.id.tv_right_answer_input_game_fragment) TextView mRightAnswer;
    @BindView(R.id.btn_next_question) Button mNextQuestion;
    @BindView(R.id.btn_sure_question) Button mSure;
    @BindView(R.id.btn_tool_question) Button mTools;
    @BindView(R.id.ll_input_game_fragment) LinearLayout mInputGameFragment;
    public InputGameFragmentListener mListener;
    private int mPosition;
    private InputGameBean mInputGameBean;
    private ToolsDialog mToolsDialog;
    private boolean isClickedDouble;
    private boolean isClickedCover;
    private boolean isClickedHelper;
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
        if (mInputGameBean != null) {
            mQuestiont.setText((mPosition + 1) + "." + mInputGameBean.getQuestion());
            mNote.setText("解释：" + mInputGameBean.getNote());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void setData(int position, InputGameBean inputGameBean) {
        this.mPosition = position;
        this.mInputGameBean = inputGameBean;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    @OnClick(R.id.btn_sure_question)
    public void onSureClicked() {
        if (mInputEditText == null) { return; }
        if (mRightAnswer.getVisibility() == View.VISIBLE) {
            ToastHelper.showShortMessage("已经回答完该题目");
            return;
        }
        String answerText = mInputEditText.getText().toString();
        if (TextUtils.isEmpty(answerText)) {
            ToastHelper.showShortMessage("请填写答案后再点击确认");
            return;
        }

        if (answerText.equals(mInputGameBean.getAnswer())) {
            mListener.onAnswerRight(isClickedDouble);
            mRightAnswer.setText("恭喜您回答正确");
        } else {
            mRightAnswer.setText("回答错误 \n正确答案：" + mInputGameBean.getAnswer());
        }
        mRightAnswer.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btn_next_question)
    public void onNextClicked() {
        String answerText = mInputEditText.getText().toString();
        if (TextUtils.isEmpty(answerText)) {
            ToastHelper.showShortMessage("请填写答案后再进入下一题");
            return;
        }
        if (mRightAnswer.getVisibility() == View.GONE) {
            ToastHelper.showShortMessage("请点击确认后再进入下一题");
            return;
        }
        if (mListener != null) {
            mListener.onInputGameFragmentNextClicked(mPosition);
        }
    }

    @OnClick(R.id.ll_input_game_fragment)
    public void onHideSofKeyBoardtClicked() {
        LogUtils.d("InputGameFragment  onHideSofKeyBoardtClicked()");
        if (mInputEditText == null) {return;}
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(mInputEditText.getWindowToken(), 0);
        }
    }

    @OnClick(R.id.btn_tool_question)
    public void onToolsClicked() {
        if (mToolsDialog == null) {
            mToolsDialog = new ToolsDialog();
        }
        mToolsDialog.setToolsDialogListener(this);
        mToolsDialog.setData(isClickedHelper, isClickedDouble, isClickedCover);
        mToolsDialog.tryShow(getActivity().getSupportFragmentManager());
    }

    @Override
    public void onHelperCardClicked() {
        isClickedHelper = true;
        mInputEditText.setText(mInputGameBean.getAnswer());
        mListener.onAnswerRight(false);
        mRightAnswer.setText("恭喜您回答正确");
        mRightAnswer.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDoubleCardClicked() {
        isClickedDouble = true;
    }

    @Override
    public void onCoverCardClicked() {
        isClickedCover = true;
        if (mListener != null) {
            mListener.onCoverCardClicked();
        }
    }

    public interface InputGameFragmentListener {

        void onAnswerRight(boolean isClickDouble);

        void onInputGameFragmentNextClicked(int position);

        void onCoverCardClicked();

    }

    public void setInputGameFragmentListener(InputGameFragmentListener listener) {
        this.mListener = listener;
    }
}

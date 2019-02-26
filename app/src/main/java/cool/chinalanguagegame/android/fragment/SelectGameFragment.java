package cool.chinalanguagegame.android.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cool.chinalanguagegame.android.R;
import cool.chinalanguagegame.android.base.BaseFragment;
import cool.chinalanguagegame.android.bean.SelectGameBean;
import cool.chinalanguagegame.android.dialog.ToolsDialog;
import cool.chinalanguagegame.android.utils.ToastHelper;

public class SelectGameFragment extends BaseFragment implements View.OnTouchListener, ToolsDialog.ToolsDialogListener {

    @BindView(R.id.tv_question_select_game_fragment) TextView tvQuestionSelectGameFragment;
    @BindView(R.id.btn_answer_a) TextView btnAnswerA;
    @BindView(R.id.btn_answer_b) TextView btnAnswerB;
    @BindView(R.id.btn_answer_c) TextView btnAnswerC;
    @BindView(R.id.tv_note_select_game_fragment) TextView tvNoteSelectGameFragment;
    @BindView(R.id.btn_tool_question) Button btnToolQuestion;
    @BindView(R.id.btn_sure_question) Button btnSureQuestion;
    @BindView(R.id.btn_next_question) Button btnNextQuestion;
    @BindView(R.id.tv_right_answer_select_game_fragment) TextView mRightAnswer;

    private int mPosition;
    private SelectGameBean mSelectGameBean;
    public SelectGameFragmentListener mListener;
    private ToolsDialog mToolsDialog;
    private boolean isClickedDouble;
    private boolean isClickedCover;
    private boolean isClickedHelper;
    Unbinder unbinder;

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
        initView();
    }

    public void initView() {
        if (mSelectGameBean != null) {
            tvQuestionSelectGameFragment.setText((mPosition + 1) + "." + mSelectGameBean.getQuestion());
            tvNoteSelectGameFragment.setText("解释：" + mSelectGameBean.getNote());
            btnAnswerA.setText(mSelectGameBean.getSelectA());
            btnAnswerB.setText(mSelectGameBean.getSelectB());
            btnAnswerC.setText(mSelectGameBean.getSelectC());
        }
    }

    public void setData(int position, SelectGameBean selectGameBean) {
        this.mPosition = position;
        this.mSelectGameBean = selectGameBean;
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

    public interface SelectGameFragmentListener {

        void onInputGameFragmentNextClicked(int position);

        void onAnswerRight(boolean isClickDouble);

        void onCoverCardClicked();

    }

    public void setSelectGameFragmentListener(SelectGameFragmentListener listener) {
        this.mListener = listener;
    }

    @OnClick(R.id.btn_answer_a)
    public void selectAClicked() {
        boolean isSelected = btnAnswerA.isSelected();
        if (isSelected) {
            btnAnswerA.setSelected(false);
        } else {
            btnAnswerA.setSelected(true);
            btnAnswerB.setSelected(false);
            btnAnswerC.setSelected(false);
        }
    }

    @OnClick(R.id.btn_answer_b)
    public void selectBClicked() {
        boolean isSelected = btnAnswerB.isSelected();
        if (isSelected) {
            btnAnswerB.setSelected(false);
        } else {
            btnAnswerB.setSelected(true);
            btnAnswerC.setSelected(false);
            btnAnswerA.setSelected(false);
        }
    }

    @OnClick(R.id.btn_answer_c)
    public void selectCClicked() {
        boolean isSelected = btnAnswerC.isSelected();
        if (isSelected) {
            btnAnswerC.setSelected(false);
        } else {
            btnAnswerC.setSelected(true);
            btnAnswerB.setSelected(false);
            btnAnswerA.setSelected(false);
        }
    }

    @OnClick(R.id.btn_sure_question)
    public void onSureClicked() {
        if (btnAnswerA == null) { return; }
        if (mRightAnswer.getVisibility() == View.VISIBLE) {
            ToastHelper.showShortMessage("已经回答完该题目");
            return;
        }
        String answerText = null;
        if (btnAnswerA.isSelected()) {
            answerText = btnAnswerA.getText().toString();
        } else if (btnAnswerB.isSelected()) {
            answerText = btnAnswerB.getText().toString();
        } else if (btnAnswerC.isSelected()) {
            answerText = btnAnswerC.getText().toString();
        } else {
            ToastHelper.showShortMessage("请选择答案后再点击确认");
            return;
        }

        if (TextUtils.isEmpty(answerText)) {
            ToastHelper.showShortMessage("请选择答案后再点击确认");
            return;
        }

        if (answerText.equals(mSelectGameBean.getAnswer())) {
            mListener.onAnswerRight(isClickedDouble);
            mRightAnswer.setText("恭喜您回答正确");
        } else {
            mRightAnswer.setText("回答错误 \n正确答案：" + mSelectGameBean.getAnswer());
        }
        mRightAnswer.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btn_next_question)
    public void onNextClicked() {
        String answerText = null;
        if (btnAnswerA.isSelected()) {
            answerText = btnAnswerA.getText().toString();
        } else if (btnAnswerB.isSelected()) {
            answerText = btnAnswerB.getText().toString();
        } else if (btnAnswerC.isSelected()) {
            answerText = btnAnswerC.getText().toString();
        } else {
            ToastHelper.showShortMessage("请选择答案后再点击确认");
            return;
        }
        if (TextUtils.isEmpty(answerText)) {
            ToastHelper.showShortMessage("请选择答案后再点击确认");
            return;
        }
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
        String answer = mSelectGameBean.getAnswer();
        if (TextUtils.isEmpty(answer)) {return;}
        if (answer.equals(mSelectGameBean.getSelectA())) {
            btnAnswerA.setSelected(true);
            btnAnswerB.setSelected(false);
            btnAnswerC.setSelected(false);
        } else if (answer.equals(mSelectGameBean.getSelectB())) {
            btnAnswerB.setSelected(true);
            btnAnswerC.setSelected(false);
            btnAnswerA.setSelected(false);
        } else if (answer.equals(mSelectGameBean.getSelectC())) {
            btnAnswerC.setSelected(true);
            btnAnswerB.setSelected(false);
            btnAnswerA.setSelected(false);
        }
        mListener.onAnswerRight(false);
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
}

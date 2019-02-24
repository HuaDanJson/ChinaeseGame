package cool.chinalanguagegame.android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cool.chinalanguagegame.android.R;
import cool.chinalanguagegame.android.base.BaseRVAdapter;
import cool.chinalanguagegame.android.base.IViewHolder;
import cool.chinalanguagegame.android.bean.InputGameBean;


public class CheckAnswerAdapter extends BaseRVAdapter<InputGameBean, CheckAnswerAdapter.CheckAnswerAdapterHolder> {

    @Override
    protected CheckAnswerAdapterHolder doCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new CheckAnswerAdapterHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_check_answer, viewGroup, false));
    }

    @Override
    protected void bindItemData(CheckAnswerAdapterHolder viewHolder, InputGameBean listener, int position) {
        viewHolder.bindView(listener, position);
    }

    public class CheckAnswerAdapterHolder extends RecyclerView.ViewHolder implements IViewHolder<InputGameBean> {

        @BindView(R.id.tv_question)
        TextView mQuestion;
        @BindView(R.id.tv_answer)
        TextView mAnswer;
        @BindView(R.id.tv_note)
        TextView mNote;

        public CheckAnswerAdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindView(InputGameBean videoBean, int position) {
            mQuestion.setText("问题：" + videoBean.getQuestion());
            mAnswer.setText("答案：" + videoBean.getAnswer());
            mNote.setText(videoBean.getNote());
        }
    }
}


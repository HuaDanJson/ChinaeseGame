package cool.chinalanguagegame.android.activity;

import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cool.chinalanguagegame.android.R;
import cool.chinalanguagegame.android.base.BaseActivity;
import cool.chinalanguagegame.android.utils.ActivityUtil;

public class SelectGameActivity extends BaseActivity {

    @BindView(R.id.btn_primary_words) Button btnPrimaryWords;
    @BindView(R.id.btn_primary_poetry) Button btnPrimaryPoetry;
    @BindView(R.id.btn_middle_words) Button btnMiddleWords;
    @BindView(R.id.btn_middle_poetry) Button btnMiddlePoetry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_game);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_primary_words)
    public void primaryWordsClicked() {
        //小学成语
        ActivityUtil.startSelectGameInfoActivity(this, 1);
    }

    @OnClick(R.id.btn_primary_poetry)
    public void primaryPoetryClicked() {
        //小学诗词
        ActivityUtil.startSelectGameInfoActivity(this, 2);
    }

    @OnClick(R.id.btn_middle_words)
    public void middleWordsClicked() {
        //初中成语
        ActivityUtil.startSelectGameInfoActivity(this, 3);
    }

    @OnClick(R.id.btn_middle_poetry)
    public void middlePoetryClicked() {
        //初中诗词
        ActivityUtil.startSelectGameInfoActivity(this, 4);
    }
}

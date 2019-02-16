package cool.chinalanguagegame.android.activity;

import android.os.Bundle;

import com.blankj.utilcode.util.LogUtils;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cool.chinalanguagegame.android.R;
import cool.chinalanguagegame.android.base.BaseActivity;
import cool.chinalanguagegame.android.bean.PrimaryWords;
import cool.chinalanguagegame.android.bean.PrimaryWordsSelect;

public class SmallChengYuActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middle_cheng_yu);
        BmobQuery<PrimaryWords> query = new BmobQuery<>();
        query.setLimit(50).order("createdAt")
                .findObjects(new FindListener<PrimaryWords>() {
                    @Override
                    public void done(List<PrimaryWords> primaryWordsList, BmobException e) {
                        if (e == null) {
                            LogUtils.d("SmallChengYuActivity query successPrimaryWords:" + primaryWordsList);
                        } else {
                            LogUtils.d("SmallChengYuActivity query PrimaryWords failed");
                        }
                    }
                });

        BmobQuery<PrimaryWordsSelect> primaryWordsSelectQuery = new BmobQuery<>();
        primaryWordsSelectQuery.setLimit(50).order("createdAt")
                .findObjects(new FindListener<PrimaryWordsSelect>() {
                    @Override
                    public void done(List<PrimaryWordsSelect> primaryWordsSelectList, BmobException e) {
                        if (e == null) {
                            LogUtils.d("SmallChengYuActivity query PrimaryWordsSelect:" + primaryWordsSelectList);
                        } else {
                            LogUtils.d("SmallChengYuActivity query PrimaryWordsSelect failed");
                        }
                    }
                });
    }
}

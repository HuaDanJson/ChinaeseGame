package cool.chinalanguagegame.android.utils;

import android.app.Activity;
import android.content.Intent;

import java.io.Serializable;
import java.util.List;

import cool.chinalanguagegame.android.R;
import cool.chinalanguagegame.android.activity.GameOverActivity;
import cool.chinalanguagegame.android.activity.PlayInputGameActivity;
import cool.chinalanguagegame.android.activity.PlaySelectGameActivity;
import cool.chinalanguagegame.android.activity.SelectGameInfoActivity;
import cool.chinalanguagegame.android.activity.SelectInputGameActivity;
import cool.chinalanguagegame.android.activity.SelectSelectActivity;
import cool.chinalanguagegame.android.bean.InputGameBean;
import cool.chinalanguagegame.android.bean.SelectGameBean;
import cool.chinalanguagegame.android.constants.AppConstant;

public class ActivityUtil {

    public static boolean isFinishing(Activity activity) {
        return (activity == null || activity.isFinishing());
    }

    public static void startActivity(Activity activity, Class targetClass) {
        Intent intent = new Intent(activity, targetClass);
        activity.startActivity(intent);
    }

    public static void startSelectGameInfoActivity(Activity activity, int type) {
        if (activity == null) { return; }
        Intent intent = new Intent(activity, SelectGameInfoActivity.class);
        intent.putExtra(AppConstant.IntentKey.EXTRA_TYPE, type);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.enter_from_right, R.anim.slide_in_from_middle_to_middle);
    }

    public static void startSelectSelectActivity(Activity activity, int type) {
        if (activity == null) { return; }
        Intent intent = new Intent(activity, SelectSelectActivity.class);
        intent.putExtra(AppConstant.IntentKey.EXTRA_TYPE, type);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.enter_from_right, R.anim.slide_in_from_middle_to_middle);
    }

    public static void startSelectInputGameActivityy(Activity activity, int type) {
        if (activity == null) { return; }
        Intent intent = new Intent(activity, SelectInputGameActivity.class);
        intent.putExtra(AppConstant.IntentKey.EXTRA_TYPE, type);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.enter_from_right, R.anim.slide_in_from_middle_to_middle);
    }

    public static void startPlayInputGameActivity(Activity activity, int type, int starCount, List<InputGameBean> inputGameBeanList) {
        if (activity == null) { return; }
        Intent intent = new Intent(activity, PlayInputGameActivity.class);
        intent.putExtra(AppConstant.IntentKey.EXTRA_TYPE, type);
        intent.putExtra(AppConstant.IntentKey.EXTRA_STAR, starCount);
        intent.putExtra(AppConstant.IntentKey.EXTRA_DATA, (Serializable) inputGameBeanList);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.enter_from_right, R.anim.slide_in_from_middle_to_middle);
    }

    public static void startGameOverActivity(Activity activity, int score, List<InputGameBean> inputGameBeanList) {
        if (activity == null) { return; }
        Intent intent = new Intent(activity, GameOverActivity.class);
        intent.putExtra(AppConstant.IntentKey.EXTRA_SCORE, score);
        intent.putExtra(AppConstant.IntentKey.EXTRA_DATA, (Serializable) inputGameBeanList);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.enter_from_right, R.anim.slide_in_from_middle_to_middle);
        activity.finish();
    }

    public static void startPlaySelectGameActivity(Activity activity, int type, int starCount, List<SelectGameBean> selectGameBeanList) {
        if (activity == null) { return; }
        Intent intent = new Intent(activity, PlaySelectGameActivity.class);
        intent.putExtra(AppConstant.IntentKey.EXTRA_TYPE, type);
        intent.putExtra(AppConstant.IntentKey.EXTRA_STAR, starCount);
        intent.putExtra(AppConstant.IntentKey.EXTRA_DATA, (Serializable) selectGameBeanList);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.enter_from_right, R.anim.slide_in_from_middle_to_middle);
    }
}

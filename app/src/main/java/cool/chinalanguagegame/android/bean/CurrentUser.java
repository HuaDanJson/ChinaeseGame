package cool.chinalanguagegame.android.bean;

import cn.bmob.v3.BmobUser;

public class CurrentUser extends BmobUser {

    /**
     * 得分数
     */
    private Integer score;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "CurrentUser{" +
                "score=" + score +
                '}';
    }
}

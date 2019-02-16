package cool.chinalanguagegame.android.bean;

import cn.bmob.v3.BmobObject;

//初中诗词选择
public class MiddlePoetrySelect extends BmobObject {

    private String question;
    private String answer;
    private String note;
    private String selectA;
    private String selectB;
    private String selectC;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSelectA() {
        return selectA;
    }

    public void setSelectA(String selectA) {
        this.selectA = selectA;
    }

    public String getSelectB() {
        return selectB;
    }

    public void setSelectB(String selectB) {
        this.selectB = selectB;
    }

    public String getSelectC() {
        return selectC;
    }

    public void setSelectC(String selectC) {
        this.selectC = selectC;
    }

    @Override
    public String toString() {
        return "MiddlePoetrySelect{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", note='" + note + '\'' +
                ", selectA='" + selectA + '\'' +
                ", selectB='" + selectB + '\'' +
                ", selectC='" + selectC + '\'' +
                '}';
    }
}

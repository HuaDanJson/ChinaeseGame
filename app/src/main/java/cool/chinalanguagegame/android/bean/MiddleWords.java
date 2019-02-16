package cool.chinalanguagegame.android.bean;

import cn.bmob.v3.BmobObject;

//初中成语
public class MiddleWords extends BmobObject {

    private String question;
    private String answer;
    private String note;

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

    @Override
    public String toString() {
        return "MiddleWords{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}

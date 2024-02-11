package examform;

public class Question {
    
    private int type;
    private char answer;
    private String theQues;
    private String choices[] = new String[4];
    private String quesPic, quesPic1, quesPic2, quesPic3, quesPic4;
    private boolean selected[] = new boolean[4];
    
    public Question(int t, char a, String tq, String c[], String qp) {
        type = t;
        answer = a;
        theQues = tq;
        choices = c.clone();
        quesPic = qp;
        for (int k = 1; k < 4; k++)
            selected[k] = false;
    }
    
    public Question(int t, char a, String tq, String qp1, String qp2, String qp3, String qp4) {
        type = t;
        answer = a;
        theQues = tq;
        quesPic1 = qp1;
        quesPic2 = qp2;
        quesPic3 = qp3;
        quesPic4 = qp4;
        for (int k = 1; k < 4; k++)
            selected[k] = false;
    }
    
    public int getType() {
        return type;
    }

    public String getTheQues() {
        return theQues;
    }

    public String getQuesPic() {
        return quesPic;
    }
    
    public String getAnsPic1() {
        return quesPic1;
    }
    
    public String getAnsPic2() {
        return quesPic2;
    }
    
    public String getAnsPic3() {
        return quesPic3;
    }
    
    public String getAnsPic4() {
        return quesPic4;
    }
    
    public String getChoice(int no) {
        return choices[no];
    }

    public boolean getSelected(int no) {
        return selected[no];
    }

    public void setSelected(int no, boolean status) {
        selected[no] = status;
    }
}
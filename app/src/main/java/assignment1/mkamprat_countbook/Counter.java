package assignment1.mkamprat_countbook;

import java.util.Date;
/**
 * Created by Mark on 2017-10-01.
 */

public class Counter extends new_counter {
    private String counterName;
    private String counterComments;
    private String counterInitVal;
    private String counterCurrentVal;
    private Date counterDate;


    public Counter(String counterName, String counterComments,
                   String counterInitVal, String counterCurrentVal) {
        this.counterName = counterName;
        this.counterComments = counterComments;
        this.counterInitVal = counterInitVal;
        this.counterCurrentVal = counterCurrentVal;
        this.counterDate = new Date();
    }

    public String getName() { return counterName; }

    public String getComments() {
        return counterComments;
    }

    public String getInitVal() {
        return counterInitVal;
    }

    public String getCurrentVal() { return counterCurrentVal; }

    public Date getDate() {
        return counterDate;
    }
}

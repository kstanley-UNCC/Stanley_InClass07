// Stanley_InClass07
// Message.java
// Ken Stanley

package edu.uncc.inclass07;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Message implements Serializable {
    public final String message;
    private final Date date;

    public Message(String message) {
        this.message = message;
        this.date = new Date();
    }

    public String toFormat() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mma", Locale.US);
        return dateFormat.format(date);
    }
}

package org.tl.blog.common.Interceptor;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.util.StringUtils;
import org.tl.blog.common.utils.DateUtils;

/**
 *
 */
public class DateConvertEditor extends PropertyEditorSupport {
    private SimpleDateFormat datetimeFormat = new SimpleDateFormat(
            DateUtils.TIME_PATTERN);
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtils.DATE_FMT_3);

    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.hasText(text)) {
            try {
                if (text.indexOf(":") == -1 && text.length() == 10) {
                    setValue(this.dateFormat.parse(text));
                } else if (text.indexOf(":") > 0 && text.length() == 19) {
                    setValue(this.datetimeFormat.parse(text));
                } else if (text.indexOf(":") > 0 && text.length() == 21) {
                    text = text.replace(".0", "");
                    setValue(this.datetimeFormat.parse(text));

                } else if (text.indexOf(":") > 0 && text.indexOf(".") > 0 && text.length() > 21) {
                    text = text.substring(0, text.indexOf("."));
                    setValue(this.datetimeFormat.parse(text));
                } else {
                    throw new IllegalArgumentException(
                            "Could not parse date, date format is error ");
                }
            } catch (ParseException ex) {
                IllegalArgumentException iae = new IllegalArgumentException(
                        "Could not parse date: " + ex.getMessage());
                iae.initCause(ex);
                throw iae;
            }
        } else {
            setValue(null);
        }
    }
}

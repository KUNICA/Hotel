package com.ui;

/**
 * Created by IntelliJ IDEA.
 * User: Alexey
 * Date: Apr 15, 2008
 * Time: 12:07:38 PM
 */
public class JspConstants {
    /**
     * Path to app root dir.
     */
    public static final String APP_PATH = "";

    /**
     * Сообщение которое выводится вместо пустого поля в объекте.
     */
    public static final String NO_DATA_MESSAGE = "Сведения отсутствуют";

    static {
        System.out.print(APP_PATH);
        System.out.print(NO_DATA_MESSAGE);
    }
}

package com.qthegamep.snake.view.swing.setting;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

/**
 * @author Koliadin Nikita
 * @version 1.14
 */
public class Setting {

    private static volatile Setting ourInstance;

    @Getter @Setter private String color1 = "RED";
    @Getter @Setter private String color2 = "GREEN";
    @Getter @Setter private String color3 = "BLUE";
    @Getter @Setter private String color4 = "YELLOW";
    @Getter @Setter private String color5 = "GRAY";
    @Getter @Setter private String color6 = "BLACK";

    @Getter @Setter private String sizeListValue1 = "15";
    @Getter @Setter private String sizeListValue2 = "20";
    @Getter @Setter private String sizeListValue3 = "30";
    @Getter @Setter private String sizeListValue4 = "40";
    @Getter @Setter private String sizeListValue5 = "50";

    @Getter @Setter private int sizeOfGame = 15; // FIXME: MOVE TO view PACKAGE

    @Getter @Setter private Color colorFace = Color.RED;
    @Getter @Setter private Color colorMouse = Color.GRAY;
    @Getter @Setter private Color colorSnake = Color.GREEN;
    @Getter @Setter private Color colorHead = Color.BLACK;

    private Setting() {
    }

    public static Setting getInstance() {
        if (Setting.ourInstance == null) {
            synchronized (Setting.class) {
                if (Setting.ourInstance == null) {
                    Setting.ourInstance = new Setting();
                }
            }
        }
        return Setting.ourInstance;
    }
}

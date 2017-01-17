/*Justin Mendiguarin*/
package com.mycompany.a3;
import com.codename1.charts.util.ColorUtil;

public class ColorHelper {
    public ColorHelper(){}
    public static int getRed(int shade){
        int setColor;
        switch (shade){
            case 1:
                setColor = ColorUtil.rgb(255,165,165);
                break;
            case 2:
                setColor = (ColorUtil.rgb(255,150,150));
                break;
            case 3:
                setColor = (ColorUtil.rgb(255,100,100));
                break;
            case 4:
                setColor = (ColorUtil.rgb(255,50,50));
                break;
            case 5:
                setColor = (ColorUtil.rgb(255,0,0));
                break;
            default:
                setColor = getRed(1);
                break;
        }
        return setColor;
    }
    public int getYellow(){
        return ColorUtil.rgb(255,247,0);
    }
    public int getBlack(){
        return ColorUtil.rgb(0,0,0);
    }
}


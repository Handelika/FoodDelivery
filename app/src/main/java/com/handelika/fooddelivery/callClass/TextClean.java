package com.handelika.fooddelivery.callClass;

public class TextClean {
    public String Temizle(String metin)
    {
        String ifade = metin;

        ifade = ifade.replace("'", "");
        ifade = ifade.replace("%", "");
        ifade = ifade.replace("<", "");
        ifade = ifade.replace(">", "");
        ifade = ifade.replace("[", "");
        ifade = ifade.replace("]", "");
        ifade = ifade.replace("(", "");
        ifade = ifade.replace(")", "");
        return ifade;
    }

}

package com.moutamid.sra.models;

public class TranslateModel {
    String original, translated;

    public TranslateModel(String original, String translated) {
        this.original = original;
        this.translated = translated;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getTranslated() {
        return translated;
    }

    public void setTranslated(String translated) {
        this.translated = translated;
    }
}

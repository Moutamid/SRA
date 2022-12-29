package com.moutamid.sra.models;

public class CaptchaModel {
    String answer;
    int image;

    public CaptchaModel(String answer, int image) {
        this.answer = answer;
        this.image = image;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}

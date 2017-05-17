package com.jedihkrz.server.responses;

/**
 * Created by steven.donnelly on 5/17/17.
 */
public class VerifyResponse {
    private boolean isIdentical;
    private double confidence;

    public boolean getIsIdentical() {
        return isIdentical;
    }

    public void setIsIdentical(boolean identical) {
        isIdentical = identical;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }
}

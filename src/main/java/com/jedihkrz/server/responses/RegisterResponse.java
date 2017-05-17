package com.jedihkrz.server.responses;

/**
 * Created by steven.donnelly on 5/17/17.
 */
public class RegisterResponse {
    private String status;
    private String faceId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFaceId() {
        return faceId;
    }

    public void setFaceId(String faceId) {
        this.faceId = faceId;
    }
}

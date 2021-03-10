package com.ironhack.edgeservice.controller.dtos;

public class InvitationCodeDTO {

    private String code;
    private String tocken;

    public InvitationCodeDTO() {
    }

    public InvitationCodeDTO(String code, String tocken) {
        this.code = code;
        this.tocken = tocken;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTocken() {
        return tocken;
    }

    public void setTocken(String tocken) {
        this.tocken = tocken;
    }
}

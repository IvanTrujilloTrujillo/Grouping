package com.ironhack.edgeservice.controller.dtos;

import javax.validation.constraints.NotNull;

public class Tocken {

    @NotNull
    private String tocken;

    public Tocken() {
    }

    public Tocken(String tocken) {
        this.tocken = tocken;
    }

    public String getTocken() {
        return tocken;
    }

    public void setTocken(String tocken) {
        this.tocken = tocken;
    }
}

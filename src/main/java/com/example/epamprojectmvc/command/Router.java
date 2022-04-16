package com.example.epamprojectmvc.command;

public class Router {

    private String page = "index.jsp";
    private Type type = Type.FORWADR;

    enum Type {
        FORWADR, REDIRECT;
    }

    public void setRedirect() {
        this.type = Type.REDIRECT;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}

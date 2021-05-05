package com.robertlevonyan.views.expandablesample;

/**
 * Created by robertlevonyan on 10/17/17.
 */

public class ExpandableModel {

    private String headerText;
    private String headerSubText;
    private String contentText;

    public ExpandableModel() {
    }

    public ExpandableModel(String headerText, String contentText) {
        this.headerText = headerText;
        this.contentText = contentText;
    }

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    public String getHeaderSubText() {
        return headerSubText;
    }

    public void setHeaderSubText(String headerSubText) {
        this.headerSubText = headerSubText;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    @Override
    public String toString() {
        return "ExpandableModel{" +
                "headerText='" + headerText + '\'' +
                ", headerSubText='" + headerSubText + '\'' +
                ", contentText='" + contentText + '\'' +
                '}';
    }
}

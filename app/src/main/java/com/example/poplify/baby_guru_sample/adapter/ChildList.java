package com.example.poplify.baby_guru_sample.adapter;

import java.util.ArrayList;
import java.util.List;

public class ChildList {
    private String childImageUrl;
    private String childName;
    private String readMore;
    private String shortDescription;
    private ArrayList<String> fullDescription;
    private String chooseMethpdTitle;


    public ArrayList<String> getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(ArrayList<String> fullDescription) {
        this.fullDescription = fullDescription;
    }

    public String getListenToSam() {
        return listenToSam;
    }

    public void setListenToSam(String listenToSam) {
        this.listenToSam = listenToSam;
    }

    private String listenToSam;

    public String getMethodTypeLabe() {
        return methodTypeLabe;
    }

    public void setMethodTypeLabe(String methodTypeLabe) {
        this.methodTypeLabe = methodTypeLabe;
    }

    private String methodTypeLabe;
    private int childId;

    public String getReadMore() {
        return readMore;
    }

    public void setReadMore(String readMore) {
        this.readMore = readMore;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }



    public String getChooseMethpdTitle() {
        return chooseMethpdTitle;
    }

    public void setChooseMethpdTitle(String chooseMethpdTitle) {
        this.chooseMethpdTitle = chooseMethpdTitle;
    }


    public String getChildImageUrl() {
        return childImageUrl;
    }

    public void setChildImageUrl(String childImageUrl) {
        this.childImageUrl = childImageUrl;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public int getChildId() {
        return childId;
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }
}

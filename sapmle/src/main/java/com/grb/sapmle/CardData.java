package com.grb.sapmle;

import com.ramotion.expandingcollection.ECCardData;

import java.util.List;
import java.util.Random;

public class CardData implements ECCardData<Comment> {

    private String headTitle;
    private String headBackgroundResource;
    private String mainBackgroundResource;
    private Integer key;
    private Integer personPictureResource;
    private String personName;
    private String personMessage;
    private int personViewsCount;
    private int personCommentsCount;
    private int personLikesCount;
    private List<Comment> listItems;

    public CardData(String headTitle, String headBackgroundResource, String mainBackgroundResource, Integer key, Integer personPictureResource, String personName, String personMessage, int personViewsCount, int personCommentsCount, int personLikesCount, List<Comment> listItems) {
        this.headTitle = headTitle;
        this.headBackgroundResource = headBackgroundResource;
        this.mainBackgroundResource = mainBackgroundResource;
        this.key = key;
        this.personPictureResource = personPictureResource;
        this.personName = personName;
        this.personMessage = personMessage;
        this.personViewsCount = personViewsCount;
        this.personCommentsCount = personCommentsCount;
        this.personLikesCount = personLikesCount;
        this.listItems = listItems;
    }

    public CardData() {
        Random rnd = new Random();
        this.personViewsCount = 50 + rnd.nextInt(950);
        this.personCommentsCount = 35 + rnd.nextInt(170);
        this.personLikesCount = 10 + rnd.nextInt(1000);
    }

    public String getHeadTitle() {
        return headTitle;
    }

    public void setHeadTitle(String headTitle) {
        this.headTitle = headTitle;
    }

    public Integer setKey(Integer key){
       return this.key=key;
    }

    public Integer getKey(){
        return  this.key;
    }

    public String getHeadBackgroundResource() {
        return headBackgroundResource;
    }

    public void setHeadBackgroundResource(String headBackgroundResource) {
        this.headBackgroundResource = headBackgroundResource;
    }

    public String getMainBackgroundResource() {
        return mainBackgroundResource;
    }


    public void setMainBackgroundResource(String mainBackgroundResource) {
        this.mainBackgroundResource = mainBackgroundResource;
    }

    public Integer getPersonPictureResource() {
        return personPictureResource;
    }

    public void setPersonPictureResource(Integer personPictureResource) {
        this.personPictureResource = personPictureResource;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonMessage() {
        return personMessage;
    }

    public void setPersonMessage(String personMessage) {
        this.personMessage = personMessage;
    }

    public int getPersonViewsCount() {
        return personViewsCount;
    }

    public void setPersonViewsCount(int personViewsCount) {
        this.personViewsCount = personViewsCount;
    }

    public int getPersonCommentsCount() {
        return personCommentsCount;
    }

    public void setPersonCommentsCount(int personCommentsCount) {
        this.personCommentsCount = personCommentsCount;
    }

    public int getPersonLikesCount() {
        return personLikesCount;
    }

    public void setPersonLikesCount(int personLikesCount) {
        this.personLikesCount = personLikesCount;
    }

    @Override
    public List<Comment> getListItems() {
        return listItems;
    }

    public void setListItems(List<Comment> listItems) {
        this.listItems = listItems;
    }
}

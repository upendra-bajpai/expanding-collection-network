package com.grb.sapmle;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Comment {
    private ArrayList<Integer> commentPersonPictureRes;
    private String commentPersonName;
    private String commentText;
    private String commentDate;
    private String categoryId;

    public Comment(ArrayList<Integer> commentPersonPictureRes, String commentPersonName, String commentText, String commentDate,String categoryId) {
        this.commentPersonPictureRes = commentPersonPictureRes;
        this.commentPersonName = commentPersonName;
        this.commentText = commentText;
        this.commentDate = commentDate;
        this.categoryId=categoryId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public ArrayList<Integer> getCommentPersonPictureRes() {
        return commentPersonPictureRes;
    }

    public void setCommentPersonPictureRes(ArrayList<Integer> commentPersonPictureRes) {
        this.commentPersonPictureRes = commentPersonPictureRes;
    }

    public String getCommentPersonName() {
        return commentPersonName;
    }

    public void setCommentPersonName(String commentPersonName) {
        this.commentPersonName = commentPersonName;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }
}

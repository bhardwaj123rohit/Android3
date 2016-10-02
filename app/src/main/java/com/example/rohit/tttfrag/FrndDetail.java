package com.example.rohit.tttfrag;

/**
 * Created by Rohit on 02/10/2016.
 */

public class FrndDetail {

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public FrndDetail(int _id, String text) {
        this._id = _id;
        this.text = text;
    }

    public FrndDetail() {
    }

    int _id;
    String text;
}

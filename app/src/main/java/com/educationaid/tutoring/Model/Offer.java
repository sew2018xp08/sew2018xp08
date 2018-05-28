package com.educationaid.tutoring.Model;

public class Offer {
    public String _id;
    public String _offerTitle;
    public String _offerOwnerForename;
    public String _offerOwnerLastName;
    public boolean _isOfferOfProUser;

    public Offer(String id, String offerTitle, String offerOwnerForename, String offerOwnerLastname) {
        this._id = id;
        this._offerTitle = offerTitle;
        this._offerOwnerForename = offerOwnerForename;
        this._offerOwnerLastName = offerOwnerLastname;
        //this._isOfferOfProUser = isOfferPro;
    }

    public Offer(String id, String title) {
        this._id = id;
        this._offerTitle = title;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_offerTitle() {
        return _offerTitle;
    }

    public void set_offerTitle(String _offerTitle) {
        this._offerTitle = _offerTitle;
    }

    public String get_offerOwnerForename() {
        return _offerOwnerForename;
    }

    public void set_offerOwnerForename(String _offerOwnerForename) {
        this._offerOwnerForename = _offerOwnerForename;
    }

    public String get_offerOwnerLastName() {
        return _offerOwnerLastName;
    }

    public void set_offerOwnerLastName(String _offerOwnerLastName) {
        this._offerOwnerLastName = _offerOwnerLastName;
    }

    public boolean is_isOfferOfProUser() {
        return _isOfferOfProUser;
    }

    public void set_isOfferOfProUser(boolean _isOfferOfProUser) {
        this._isOfferOfProUser = _isOfferOfProUser;
    }
}

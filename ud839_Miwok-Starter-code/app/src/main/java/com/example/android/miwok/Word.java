package com.example.android.miwok;

/**
 * Created by powflash on 2018. 2. 7..
 */

public class Word {

    private String mDefaultTranslation;

    private String mMiwokTranslation;

    private int mImageResourceId = NO_IMAGE_PROVIDED;

    private static final int NO_IMAGE_PROVIDED = -1;

    private int mMediaResourceId = NO_IMAGE_PROVIDED;

    public Word(String defaultTranslation, String miworkTranslation) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miworkTranslation;
    }

    public Word(String defaultTranslation, String miworkTranslation, int imageResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miworkTranslation;
        mImageResourceId = imageResourceId;
    }
    public Word(String defaultTranslation, String miworkTranslation, int imageResourceId, int mediaResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miworkTranslation;
        mImageResourceId = imageResourceId;
        mMediaResourceId = mediaResourceId;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public int getMediaResourceId() {
        return mMediaResourceId;
    }

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mImageResourceId=" + mImageResourceId +
                ", mMediaResourceId=" + mMediaResourceId +
                '}';
    }
}

package com.pra.interview.data.model;

import android.os.Parcel;
import android.os.Parcelable;



public class Content implements Parcelable {

    MediaDate mediaDate;
    Integer mediaId;
    String mediaTitleCustom;
    String mediaType;
    String mediaUrl;
    String mediaUrlBig;

    Content() {

    }

    protected Content(Parcel in) {
        if (in.readByte() == 0) {
            mediaId = null;
        } else {
            mediaId = in.readInt();
        }
        mediaTitleCustom = in.readString();
        mediaType = in.readString();
        mediaUrl = in.readString();
        mediaUrlBig = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (mediaId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(mediaId);
        }
        dest.writeString(mediaTitleCustom);
        dest.writeString(mediaType);
        dest.writeString(mediaUrl);
        dest.writeString(mediaUrlBig);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Content> CREATOR = new Creator<Content>() {
        @Override
        public Content createFromParcel(Parcel in) {
            return new Content(in);
        }

        @Override
        public Content[] newArray(int size) {
            return new Content[size];
        }
    };

    public MediaDate getMediaDate() {
        return mediaDate;
    }

    public void setMediaDate(MediaDate mediaDate) {
        this.mediaDate = mediaDate;
    }

    public Integer getMediaId() {
        return mediaId;
    }

    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }

    public String getMediaTitleCustom() {
        return mediaTitleCustom;
    }

    public void setMediaTitleCustom(String mediaTitleCustom) {
        this.mediaTitleCustom = mediaTitleCustom;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getMediaUrlBig() {
        return mediaUrlBig;
    }

    public void setMediaUrlBig(String mediaUrlBig) {
        this.mediaUrlBig = mediaUrlBig;
    }
}

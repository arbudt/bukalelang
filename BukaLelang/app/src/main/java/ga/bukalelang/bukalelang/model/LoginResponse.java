package ga.bukalelang.bukalelang.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by arbudt on 5/25/2017.
 */

public class LoginResponse {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("user_id")
    @Expose
    private Object userId;
    @SerializedName("user_name")
    @Expose
    private Object userName;
    @SerializedName("confirmed")
    @Expose
    private Boolean confirmed;
    @SerializedName("token")
    @Expose
    private Object token;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("confirmed_phone")
    @Expose
    private Object confirmedPhone;
    @SerializedName("omnikey")
    @Expose
    private Object omnikey;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    public Object getUserName() {
        return userName;
    }

    public void setUserName(Object userName) {
        this.userName = userName;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getConfirmedPhone() {
        return confirmedPhone;
    }

    public void setConfirmedPhone(Object confirmedPhone) {
        this.confirmedPhone = confirmedPhone;
    }

    public Object getOmnikey() {
        return omnikey;
    }

    public void setOmnikey(Object omnikey) {
        this.omnikey = omnikey;
    }

    public class Data {

        @SerializedName("tfa_status")
        @Expose
        private Object tfaStatus;

        public Object getTfaStatus() {
            return tfaStatus;
        }

        public void setTfaStatus(Object tfaStatus) {
            this.tfaStatus = tfaStatus;
        }

    }
}

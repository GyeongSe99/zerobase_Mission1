package dbTest;

import com.google.gson.annotations.SerializedName;

public class ResponseData {
    @SerializedName("TbPublicWifiInfo")
    private TbPublicWifiInfo tbPublicWifiInfo;

    public TbPublicWifiInfo getTbPublicWifiInfo() {
        return tbPublicWifiInfo;
    }

    public void setTbPublicWifiInfo(TbPublicWifiInfo tbPublicWifiInfo) {
        this.tbPublicWifiInfo = tbPublicWifiInfo;
    }
}



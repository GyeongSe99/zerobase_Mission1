package dbTest;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Row {
    @SerializedName("X_SWIFI_MGR_NO")
    private String managerNo;

    @SerializedName("X_SWIFI_WRDOFC")
    private String wardOffice;

    @SerializedName("X_SWIFI_MAIN_NM")
    private String mainName;

    @SerializedName("X_SWIFI_ADRES1")
    private String address1;

    @SerializedName("X_SWIFI_ADRES2")
    private String address2;

    @SerializedName("X_SWIFI_INSTL_TY")
    private String installationType;

    @SerializedName("X_SWIFI_INSTL_FLOOR")
    private String installationFloor;

    @SerializedName("X_SWIFI_INSTL_MBY")
    private String installationMby;

    @SerializedName("X_SWIFI_SVC_SE")
    private String serviceSe;

    @SerializedName("X_SWIFI_CMCWR")
    private String cmcwr;

    @SerializedName("X_SWIFI_CNSTC_YEAR")
    private String constructionYear;

    @SerializedName("X_SWIFI_INOUT_DOOR")
    private String inOutDoor;

    @SerializedName("X_SWIFI_REMARS3")
    private String remarks3;

    @SerializedName("LAT")
    private String latitude;

    @SerializedName("LNT")
    private String longitude;

    @SerializedName("WORK_DTTM")
    private String workDateTime;

}

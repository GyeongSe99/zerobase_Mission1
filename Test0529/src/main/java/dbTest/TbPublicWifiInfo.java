package dbTest;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TbPublicWifiInfo {
    @SerializedName("list_total_count")
    private int listTotalCount;

    @SerializedName("RESULT")
    private Result result;

    @SerializedName("row")
    private Row[] rows;

}

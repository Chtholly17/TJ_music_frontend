package com.example.tj_music.VO;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Data
@ApiModel(value = "获取预约信息返回数据", description = "获取预约信息返回数据")
public class GetWorkListVO {
    int workId;
    String workName;
    String workAuthorNickname;
    int score;
    int like;
    int workAuthorFans;
    int workCommentCnt;
    String workPrefaceFilename;
}

package com.mybury.bucketlist.auth.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mybury.bucketlist.core.base.BaseResponseVO;
import com.mybury.bucketlist.core.constants.ApiReturnCodes;
import com.mybury.bucketlist.core.domain.Bucketlist;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CategoryResponseVO extends BaseResponseVO{

    @JsonProperty
    List<Bucketlist> bucketlists;

    public CategoryResponseVO(List<Bucketlist> bucketlists) {
        super(ApiReturnCodes.OK);
        this.bucketlists = bucketlists;
    }
}

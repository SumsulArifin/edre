package com.tkgroupbd.pusti.api.data.models.common;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Region;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.util.Collection;
import java.util.List;

@Data
public class ApiAndMessageResponse<T> {
    private int count;
    private T data;
    private String message;

    public ApiAndMessageResponse(int count, T data) {
        this.count = count;
        this.data = data;
    }

    public ApiAndMessageResponse(int count, T data, String message) {
        this.count = count;
        this.data = data;
        this.message = message;
    }
}

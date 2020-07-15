package com.kx.iot.response;

public enum ResponseCode {
    SUCCESS(200, "Succ"),
    IOT_DEVICE_ID_REQUIRED(1001, "设备的iot device id不能为空."),
    FROM_DATE_REQUIRED(1002, "查询开始时间不能为空"),
    TO_DATE_REQUIRED(1003, "查询结束时间不能为空"),
    PAGE_NUM_INVALID(1004, "页码值非法，必须从1开始"),
    PAGE_SIZE_INVALID(1005, "每页显示数必须大于0"),
    START_DATE_INVALID(1006, "开始日期格式错误，日期格式必须为：yyyy-MM-dd, 比如2020-06-01"),
    END_DATE_INVALID(1007, "结束日期格式错误，日期格式必须为：yyyy-MM-dd, 比如2020-06-01");

    private Integer code;
    private String description;
    ResponseCode(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
    public Integer getCode() {
        return this.code;
    }
    public String getDescription() {
        return this.description;
    }

}

package com.kx.iot.response;

import lombok.Data;

@Data
public class Paging {
    private long total;

    private int pages;

    private int pageNo;

    private int pageSize;
}

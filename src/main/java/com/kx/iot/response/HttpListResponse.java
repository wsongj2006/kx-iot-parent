package com.kx.iot.response;

import lombok.Data;

import java.util.List;

@Data
public class HttpListResponse<T> {

    private List<T> data;

    private StatusCode statusCode;

    private Paging paging;

    public static HttpListResponse create() {
        return new HttpListResponse();
    }

    public HttpListResponse buildStatus(Integer code, String msg) {
        StatusCode statusCode = new StatusCode();
        statusCode.setMessage(msg);
        statusCode.setCode(code);
        this.setStatusCode(statusCode);
        return this;
    }

    public HttpListResponse buildStatus(StatusCode statusCode) {
        this.setStatusCode(statusCode);
        return this;
    }

    public HttpListResponse buildPaging(long total, int pages, int pageNo, int pageSize) {
        Paging paging = new Paging();
        paging.setPages(pages);
        paging.setTotal(total);
        paging.setPageNo(pageNo);
        paging.setPageSize(pageSize);
        this.setPaging(paging);
        return this;
    }

    public HttpListResponse data(List<T> data) {
        this.setData(data);
        return this;
    }
}

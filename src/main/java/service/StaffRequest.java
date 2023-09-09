package service;

public class StaffRequest {
    private int pageNow;
    private int pageSize;
    public int getStart() {
        return (pageNow -1) * pageSize;
    }

    //查询词
    private String searchKey;

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }
}

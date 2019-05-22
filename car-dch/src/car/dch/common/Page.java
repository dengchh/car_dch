package car.dch.common;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Page implements Serializable{

    private Integer currPage; // 当前所在的页数,如第1页,第2页
    private Integer pageSize; // 每页所包含的记录数量
  
    public Integer getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
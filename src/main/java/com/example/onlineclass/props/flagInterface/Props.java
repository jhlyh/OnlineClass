package com.example.onlineclass.props.flagInterface;

public interface Props {
    /**
     * 排序中方向位置
     * @return
     */
    Integer getSortDirectionIndex();

    /**
     * 排序属性的位置
     * @return
     */
    Integer getTheSortByIndex();

    /**
     * 返回当前页的JSON名称
     * @return
     */
    String getReturnCurrentPage();

    /**
     * 返回所有页书的JSON名称
     * @return
     */
    String getReturnTotalPages();

    /**
     * 返回所有元素数量的JSON名称
     * @return
     */
    String getReturnTotalItems();

    /**
     * 返回元素所属类型JSON名称
     * @return
     */
    String getReturnDomain();
}

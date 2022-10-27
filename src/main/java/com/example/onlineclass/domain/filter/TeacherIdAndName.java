package com.example.onlineclass.domain.filter;

/**前台查找只返回老师姓名以及对应的ID
 * @author jhlyh
 */
public interface TeacherIdAndName {
    /** 返回老师的ID
     *
     * @return 老师ID
     */
    Long getId();

    /** 返回老师的名字
     *
     * @return　老师名字
     */
    String getTeacherName();
}

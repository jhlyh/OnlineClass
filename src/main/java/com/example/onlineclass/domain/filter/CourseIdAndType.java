package com.example.onlineclass.domain.filter;

/** 前台查找只返回课程ID和相应的课程类型接口
 *
 * @author jhlyh
 *
 */
public interface CourseIdAndType {
    /**返回课程的ID
     *
     * @return 课程的ID
     */
    Long getId();

    /** 返回课程相应的课程类型
     *
     * @return 课程相应的课程类型
     */
    String getTypeName();
}

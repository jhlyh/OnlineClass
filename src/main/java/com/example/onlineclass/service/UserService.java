package com.example.onlineclass.service;

import java.util.Map;

/**
 * @author jhlyh
 */
public interface UserService {
    /**
     * 分页排序查询
     * @param page
     * @param size
     * @param sort
     * @return
     */
    Map<String, Object> getAllUsersPage(int page, int size, String[] sort);
}

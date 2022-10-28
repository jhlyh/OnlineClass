package com.example.onlineclass.controller;

import com.example.onlineclass.domain.User;
import com.example.onlineclass.repository.UserRepository;
import com.example.onlineclass.service.imp.UserServiceImp;
import com.example.onlineclass.util.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @author jhlyh
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;
    private final UserServiceImp userServiceImp;

    public UserController(UserRepository userRepository, UserServiceImp userServiceImp) {
        this.userRepository = userRepository;
        this.userServiceImp = userServiceImp;
    }

    /**
     * 增加
     *
     * @param user
     * @return
     */
    @PostMapping("/add")
    public Result<?> add(@RequestBody User user) {
        try {
            return Result.success(userRepository.save(user));
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }

    /**
     * 更新
     *
     * @param user
     * @return
     */
    @PostMapping("update")
    public Result<?> update(@RequestBody User user) {
        try {
            return Result.success(userRepository.save(user));
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }

    /**
     * 分页排序查询
     *
     * @param page
     * @param size
     * @param sort
     * @return
     */
    @GetMapping("/findAll")
    public Result<?> findAll(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "3") Integer size,
            @RequestParam(defaultValue = "id,desc") String[] sort
    ) {
        return Result.success(userServiceImp.getAllUsersPage(name, page, size, sort));
    }

    /**
     * 根据ID查找用户
     *
     * @param id
     * @return
     */
    @GetMapping("find")
    public Result<?> findById(@RequestParam Long id) {
        try {
            User user = userRepository.findById(id).get();
            return Result.success(user);
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }

    /**
     * 根据Id删除用户
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public Result<?> deleteById(@RequestParam Long id) {
        try {
            userRepository.deleteById(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }
}

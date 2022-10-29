package com.example.onlineclass.service.imp;

import com.example.onlineclass.domain.User;
import com.example.onlineclass.props.UserProps;
import com.example.onlineclass.repository.UserRepository;
import com.example.onlineclass.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final UserProps userProps;

    public UserServiceImp(UserRepository userRepository, UserProps userProps) {
        this.userRepository = userRepository;
        this.userProps = userProps;
    }

    @Override
    public Map<String, Object> getAllUsersPage(String name, Integer page, Integer size, String[] sort) {
        try {
            Specification<User> queryCondition = new Specification<User>() {
                @Override
                public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    List<Predicate> predicateList = new ArrayList<>();
                    if (name != null) {
                        predicateList.add(criteriaBuilder.like(root.get("nickName"), "%" + name + "%"));
                    }
                    return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
                }
            };

            List<Sort.Order> orders = new ArrayList<>();
            if (sort[0].contains(",")) {
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Sort.Order(Sort.Direction.fromString(_sort[userProps.getSortDirectionIndex()]), _sort[userProps.getTheSortByIndex()]));
                }
            } else {
                orders.add(new Sort.Order(Sort.Direction.fromString(sort[userProps.getSortDirectionIndex()]), sort[userProps.getTheSortByIndex()]));
            }

            Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
            Page<User> sectionPage = userRepository.findAll(queryCondition, pageable);
            List<User> users = sectionPage.getContent();
            Map<String, Object> response = new HashMap<>();

            response.put(userProps.getReturnDomain(), users);
            response.put(userProps.getReturnTotalPages(), sectionPage.getTotalPages());
            response.put(userProps.getReturnCurrentPage(), sectionPage.getNumber());
            response.put(userProps.getReturnTotalItems(), sectionPage.getTotalElements());

            return response;

        } catch (Exception e) {
            return Collections.emptyMap();
        }
    }
}

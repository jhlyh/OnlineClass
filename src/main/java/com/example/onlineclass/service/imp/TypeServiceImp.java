package com.example.onlineclass.service.imp;

import com.example.onlineclass.domain.Teacher;
import com.example.onlineclass.domain.Type;
import com.example.onlineclass.props.TypeProps;
import com.example.onlineclass.repository.TypeRepository;
import com.example.onlineclass.service.TypeService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jhlyh
 */
@Service
public class TypeServiceImp implements TypeService {
    private TypeRepository typeRepository;
    private TypeProps typeProps;
    public TypeServiceImp(TypeRepository typeRepository, TypeProps typeProps) {
        this.typeRepository = typeRepository;
        this.typeProps = typeProps;
    }

    /**
     *
     * @param name
     * @return
     */
    @Override
    public Map<String, Object> getAllType(String name) {
        try {
            Specification<Type> queryCondition = new Specification<Type>() {
                @Override
                public Predicate toPredicate(Root<Type> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    List<Predicate> predicateList = new ArrayList<>();
                    if (name != null) {
                        predicateList.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
                    }
                    return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
                }
            };

            List<Type> chapters = typeRepository.findAll(queryCondition);
            Map<String, Object> response = new HashMap<>();

            response.put(typeProps.getReturnDomain(), chapters);
            response.put(typeProps.getReturnTotalItems(), chapters.size());

            return response;

        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
}

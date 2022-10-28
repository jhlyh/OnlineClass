package com.example.onlineclass.service.imp;

import com.example.onlineclass.props.GradeProps;
import com.example.onlineclass.repository.GradeRepository;
import com.example.onlineclass.service.GradeService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GradeServiceImp implements GradeService {
    private final GradeProps gradeProps;
    private final GradeRepository gradeRepository;
    private final CommonServiceImp commonServiceImp;

    public GradeServiceImp(GradeProps gradeProps, GradeRepository gradeRepository, CommonServiceImp commonServiceImp) {
        this.commonServiceImp = commonServiceImp;
        this.gradeProps = gradeProps;
        this.gradeRepository = gradeRepository;
    }

    @Override
    public Map<String, Object> getAllGradePage(Integer page,Integer size, String[] sort) {
        return commonServiceImp.getAllPage(page, size, sort, gradeRepository, gradeProps);
    }
}

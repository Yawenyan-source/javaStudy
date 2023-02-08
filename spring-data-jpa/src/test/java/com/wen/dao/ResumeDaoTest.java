package com.wen.dao;

import com.wen.pojo.Resume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Optional;

/**
 * @author wen
 * @version 1.0
 * @project javaStudy
 * @description
 * @since 2022/10/30 20:02:07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ResumeDaoTest {
    @Autowired
    private ResumeDao resumeDao;

    @Test
    public void testFindById() {
        Optional<Resume> optional = resumeDao.findById(1L);
        Resume resume = optional.get();
        System.out.println(resume);
    }

    @Test
    public void testOne() {
        Resume resume = new Resume();
        resume.setId(1L);
        Example<Resume> example = Example.of(resume);
        Optional<Resume> optional = resumeDao.findOne(example);
        System.out.println(optional.get());
    }

    //引入jpql（jpa查询语言）
    @Test
    public void testJpql() {
        Resume resume = resumeDao.findAllByJpql(1L);
        System.out.println(resume);
    }

    @Test
    public void testSql() {
        List<Resume> resumes = resumeDao.findBySql("张%", "北%");
        for (Resume resume : resumes) {
            System.out.println(resume);
        }
    }

    @Test
    public void testName() {
        List<Resume> byNameLike = resumeDao.findByNameLike("张%");
        for (Resume resume : byNameLike) {
            System.out.println(resume);
        }
    }

    /**
     * 动态查询，根据单个条件查询单个对象
     * 动态条件封装
     * 匿名内部类
     * root：获取需要查询的对象属性
     * criteriaBuilder：构建查询条件（模糊查询；精准查询）
     */
    @Test
    public void testSpecfication() {
        Specification<Resume> specification = (root, query, criteriaBuilder) -> {
            //获取name属性
            Path<Object> name = root.get("name");
            //使用criteriaBuilder针对name属性构建条件（精准查询）
            Predicate predicate = criteriaBuilder.equal(name, "张三");
            return predicate;
        };
        Optional<Resume> optional = resumeDao.findOne(specification);
        Resume resume = optional.get();
        System.out.println(resume);
    }

    /**
     * 根据多个条件组合查询
     */
    @Test
    public void testSpecfications() {
        Specification<Resume> specification = (root, query, criteriaBuilder) -> {
            //获取name属性
            Path<Object> name = root.get("name");
            Path<Object> address = root.get("address");
            //使用criteriaBuilder针对name属性构建条件（精准查询）
            Predicate predicate1 = criteriaBuilder.equal(name, "张三");

            Predicate predicate2 = criteriaBuilder.like(address.as(String.class), "北%");

            Predicate predicate = criteriaBuilder.and(predicate1, predicate2);
            return predicate;
        };
        Optional<Resume> optional = resumeDao.findOne(specification);
        Resume resume = optional.get();
        System.out.println(resume);
    }

    /**
     * 测试排序
     */
    @Test
    public void testSort() {

        List<Resume> resumeList = resumeDao.findAll(Sort.by(Sort.Direction.DESC, "id"));
        for (Resume resume : resumeList) {
            System.out.println(resume);
        }
    }

    @Test
    public void testPage() {
        Page<Resume> resumePage = resumeDao.findAll(PageRequest.of(0,1));
        System.out.println(resumePage);
    }

}

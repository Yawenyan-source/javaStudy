package com.wen.dao;

import com.wen.pojo.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author wen
 * @version 1.0
 * @project javaStudy
 * @description ResumeDao接口
 * @since 2022/10/30 14:15:20
 * <p>
 * 一个符合springDataJpa要求的Dao层接口是需要继承JpaRepository和JpaSpecificationExecutor
 * -------JpaRepository<-操作的实体类型,主键类型->
 * 封装了基本的CRUD操作
 * -------JpaSpecificationExecutor<-封装的实体类型->
 * 封装了复杂的查询（分页，排序）
 */

public interface ResumeDao extends JpaRepository<Resume, Long>, JpaSpecificationExecutor<Resume> {
    Resume findFirstBy();

    @Override
    long count();

    /**
     * 使用jpql语句查询
     * jpql语句类似于sql，sql操作的是数据库，jpql操作的是实体
     *
     * @return
     */
    @Query("from Resume where id = ?1")
    Resume findAllByJpql(Long id);

    /**
     * 使用原生的sql语句
     * nativeQuery = true
     *
     * @param name
     * @param address
     * @return
     */
    @Query(nativeQuery = true, value = "select * from tb_resume t where t.name like ?1 and t.address like ?2")
    List<Resume> findBySql(String name, String address);


    /**
     * 方法命名规则查询
     * 按照name模糊查询（like）
     * 方法名以findBy开头
     * ---属性名（首字母大写）
     * ---查询方式（模糊查询，等价查询）;不写查询方式，默认等价查询
     *
     * @param name
     * @return
     */
    List<Resume> findByNameLike(String name);


}

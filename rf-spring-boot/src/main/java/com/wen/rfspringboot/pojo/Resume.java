package com.wen.rfspringboot.pojo;

import lombok.*;

import javax.persistence.*;

/**
 * @author wen
 * @version 1.0
 * @project javaStudy
 * @description springboot整合jpa
 * @since 2023/2/13 21:58:46
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "tb_resume")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String address;
    private String name;
    private String phone;
}

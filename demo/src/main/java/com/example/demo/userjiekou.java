package com.example.demo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface userjiekou extends JpaRepository<usershiti, Long> {
    // 自动生成 SQL：SELECT * FROM t_post WHERE post_category = ?
    // （因为实体类 category 映射到表字段 post_category，JPA 会自动转换）
    List<usershiti> findByCategory(String category);
    List<usershiti> findByIdLessThan(Long maxId);
}
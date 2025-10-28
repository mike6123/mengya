package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping; // 新增：处理POST请求
import org.springframework.web.bind.annotation.RequestBody; // 新增：接收JSON数据
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime; // 新增：时间类型
import java.util.List;
// 导入路径参数注解
import org.springframework.web.bind.annotation.PathVariable;
// 导入 Optional 类（用于处理可能为 null 的结果）
import java.util.Optional;
// 导入 ResponseEntity（如果用了异常处理的话）
import org.springframework.http.ResponseEntity;

@RestController
public class userkongzhi {

    // 注入Repository（自动由Spring创建实例）
    @Autowired

    private userjiekou postRepository;

    @GetMapping("/posts/list")
    public List<usershiti> getPosts(
            @RequestParam(defaultValue = "最新") String category
    ) {
        List<usershiti> posts;
        // 判断分类是否为“最新”
        if ("最新".equals(category)) {
            // 分类为“最新”时：查询 id < 5 的所有数据
            posts = postRepository.findByIdLessThan(5L); // 假设 id 是 Long 类型，5L 表示 5
        } else {
            // 其他分类：按正常分类查询
            posts = postRepository.findByCategory(category);
        }
        return posts;
    }
    @PostMapping("/posts/save")
    public usershiti savePost(@RequestBody usershiti post) {
        System.out.println("接收的帖子数据：" + post.getAuthor() + "," + post.getTitle());
        post.setTime(LocalDateTime.now()); // 设置当前发布时间
        return postRepository.save(post); // 保存到数据库
    }
    @GetMapping("/posts/{id}") // 路径参数 id，对应前端的 route.params.id
    public usershiti getPostById(@PathVariable long id) { // @PathVariable 获取路径中的 id
        // 从数据库查询对应 ID 的帖子
        Optional<usershiti> post = postRepository.findById(id);
        // 如果存在则返回，不存在返回 null（或抛异常，根据业务需求处理）
        return post.orElse(null);
    }
}


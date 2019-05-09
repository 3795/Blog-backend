package cn.ntshare.blog.service.impl;

import cn.ntshare.blog.dto.ArticleDTO;
import cn.ntshare.blog.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ArticleServiceImplTest {

    @Autowired
    private ArticleService articleService;

    @Test
    public void queryContentById() {
       ArticleDTO result = articleService.selectById(5);
        System.out.println(result.getTitle());
    }
}
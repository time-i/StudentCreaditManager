package org.example;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import org.example.mapper.StudentMapper;
import org.example.mapper.UserMapper;
import org.example.pojo.Student;
import org.example.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*","null"})
@RestController
public class Cotroller {

    @Autowired
    StudentMapper mapper;
    @Autowired
    UserMapper userMapper;
    Gson gson = new Gson();

    @GetMapping("/Student")
    public String selectAll() {
        List<Student> students = mapper.selectList(null);
        return  gson.toJson(students);
    }

    @PostMapping("/update")
    public void update(@RequestBody Student stu) {
         mapper.updateById(stu);
    }

    @PostMapping("/add")
    public void add(@RequestBody Student stu) {
        mapper.insert(stu);
    }
    @PostMapping("delete")
    public void delete(@RequestBody Student stu) {
        mapper.deleteById(stu);
    }

    @PostMapping("/login")
    public int login(@RequestBody User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(user);
        User user1 = userMapper.selectOne(queryWrapper);
        if(user1 ==null) {
            return 0;
        }
        return 1;
    }

}

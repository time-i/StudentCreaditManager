<template>
  <div id="app">
   <div class="clo-8 offset-2"> 
     <table class="table caption-top table-hover">
  <caption class="text-center">
    <h1>学生管理系统</h1>
    <el-button type="primary" round @click="getStudents">获取信息</el-button>
    <el-button type="text" @click="dialogVisible = true">添加学生信息</el-button>
    <el-button type="success" @click="dialogVisiblelogin = true">登录 </el-button>
<el-dialog
  title="添加信息"
  :visible.sync="dialogVisible"
  width="30%"
  :before-close="handleClose">
  <div>
    <span>学号</span><input v-model="newStudent.number"/>
  </div>
  <div>
    <span>姓名</span><input v-model="newStudent.name"/>
  </div>
  <div>
    <span>年龄</span><input v-model.number="newStudent.age"/>
  </div>
  <div>
    <span>语文</span><input v-model.number="newStudent.chi"/>
  </div>
  <div>
    <span>数学</span><input v-model.number="newStudent.math"/>
  </div>
  <div>
    <span>英语</span><input v-model.number="newStudent.eng"/>
  </div>

  <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="add">确 定</el-button>
  </span>
</el-dialog>

<el-dialog
  title="登录"
  :visible.sync="dialogVisiblelogin"
  width="30%"
  :before-close="handleClose">
  <span>姓名</span>
  <input type="text" v-model="user_login.name"/> <br>
  <span>密码</span>
  <input type="text" v-model="user_login.password"/> <br>
  <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisiblelogin = false">取 消</el-button>
    <el-button type="primary" @click="login">确 定</el-button>
  </span>
</el-dialog>
  </caption>
  <thead>
    <tr>
      <th scope="col">学号</th>
      <th scope="col">姓名</th>
      <th scope="col">年龄</th>
      <th scope="col">语文</th>
      <th scope="col">数学</th>
      <th scope="col">英语</th>
      <th scope="col">操作</th>
    </tr>
  </thead>
  <tbody>
    <Student v-for="stu in stu_forpage" :key="stu.id" :student="stu"></Student>
  </tbody>
</table>
  <div class="text-center">
      <el-button-group>
        <el-button @click="getlast" type="primary" icon="el-icon-arrow-left">上一页</el-button>
        <el-button @click="getnext" type="primary">下一页<i class="el-icon-arrow-right el-icon--right"></i></el-button>
      </el-button-group>
  </div>
   </div>
  </div>
</template>

<script>
import axios from 'axios'
import Student from './components/Student'
export default {
  name: 'App',
  components: {
    Student
  },
  data(){
    return{
      user_login:{
        name:"",
        password:""
      },
      students:[],
      dialogVisiblelogin:false,
      dialogVisible:false,
      newStudent:{
        number:"",
        name:"",
        age:"",
        chi:"",
        place:"",
        math:"",
        eng:""
      },
      page:1
    }
  },
  computed:{
    stu_forpage() {
      return this.students.slice(this.page*5-5,this.page*5);
    }
  },
  methods:{
    login() {
      axios({
        url:"http://localhost:8080/login",
        method:"post",
        data:this.user_login
      }).then(res => {
        if(res.data==1) {
          sessionStorage.setItem("isLogined","true")
          this.dialogVisiblelogin=false;
        }else {
          this.$alert("登录失败")
        }
      })
    },
    getnext(){
      if(this.page <5) {
        this.page+=1;
      }
    },
    getlast() {
      if(this.page >1) {
        this.page-=1;
      }
    },
    getStudents() {
      if(sessionStorage.getItem("isLogined") == "true") {
        axios({
        url:"http://localhost:8080/Student",
        method:'GET'
      }).then(res=>{
        console.log(res.data);
        this.students=res.data;
      })
      } 
    },
    handleClose(done) {
        this.$confirm('确认关闭？')
          .then( ()=> {
            done();
          })
          .catch( ()=> {});
    },
    add() {
      console.log(this.newStudent);
      axios({
        url:"http://localhost:8080/add",
        method:"post",
        data:this.newStudent
      })
      this.dialogVisible=false;
    }
  }

}
</script>

<style>

</style>

<template>
  <div class="app-container">
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item label="申请用户">
        <el-select
          v-model="dataVo.userId"
          placeholder="请选择农产品类别"
        >
          <el-option
            v-for="item in userList"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="审核状态">
        <el-select v-model="dataVo.status" placeholder="请选择审核状态">
          <el-option label="审核中" value="0"/>
          <el-option label="申请成功" value="1"/>
          <el-option label="拒绝" value="2"/>
        </el-select>
      </el-form-item>
      <el-button type="primary" icon="el-icon-search" @click="getList()">查询</el-button>
      <el-button type="default" @click="resetData()">重置</el-button>
    </el-form>
    <!-- 表格 -->
    <el-table
      :data="list"
      border
      fit
      highlight-current-row
      empty-text="暂无数据"
    >
      <el-table-column align="center" label="申请图片">
        <template slot-scope="scope">
          <img
            style="width: 80px; height: 80px"
            :src="scope.row.image"/>
        </template>
      </el-table-column>
      <el-table-column prop="userName" label="申请人"/>
      <el-table-column align="content" label="申请理由">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" icon="el-icon-search" @click="viewDetails(scope.row.content)">查看申请理由</el-button>
        </template>
      </el-table-column>
      <el-table-column align="content" label="申请资料">
        <template slot-scope="scope">
          <a @click="downloadFile(scope.row.materials)">下载资料</a>
        </template>
      </el-table-column>

      <el-table-column prop="status" label="状态">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status === '0'" type="danger">审核中</el-tag>
          <el-tag v-if="scope.row.status === '1'" type="blue">申请成功</el-tag>
          <el-popover
            v-if="scope.row.status === '2'"
            placement="top-start"
            title="拒绝理由"
            width="200"
            trigger="hover"
            :content="scope.row.reason">
            <el-tag  slot="reference" type="danger">拒绝</el-tag>
          </el-popover>

        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间"/>
      <el-table-column label="操作" align="center" width="300">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" icon="el-icon-edit" v-if="scope.row.status === '0'" @click="updateAndSave(scope.row)">审核</el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeDataById(scope.row.id)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>


    <!--    审核-->
    <el-dialog
      title="扶贫项目审核"
      :visible.sync="dialogVisible"
      width="30%"
    >
      <el-form :model="formData" label-position="left" label-width="120px">
        <el-form-item label="审核状态">
          <el-select v-model="formData.status" placeholder="请选择审核状态">
            <el-option label="申请成功" value="1"/>
            <el-option label="拒绝" value="2"/>
          </el-select>
        </el-form-item>
        <el-form-item label="拒绝理由" v-if="formData.status === '2'">
          <el-input v-model="formData.reason"  type="textarea"
                    :rows="2" placeholder="请填写拒绝理由"/>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancelBtn">取 消</el-button>
        <el-button type="primary" @click="handleBtn">确 定</el-button>
      </span>
    </el-dialog>


    <!-- 内容查看 -->
    <el-dialog
      title="申请资料"
      :visible.sync="dialogVisible1"
      width="50%"
    >
      <div v-html="content"></div>
    </el-dialog>

    <!-- 分页 -->
    <el-pagination
      :current-page="page"
      style="padding: 30px 0; text-align: center;"
      :page-size="limit"
      layout="total, prev, pager, next, jumper"
      :total="total"
      @current-change="getList"
    />
  </div>
</template>
<script>
import { deleteById, pageQuery, audit} from '@/api/application'
import Tinymce from '@/components/Tinymce'
import { getUserAll } from '@/api/user'
import {  getWord } from '@/api/project'

export default {
  components: { Tinymce }, // 注册组件
  data() { // 定义变量和初始值
    return {
      list: null, // 查询之后接口返回集合
      dataVo: {}, // 条件封装对象
      page: 1, // 当前页
      limit: 9, // 每页记录数
      total: 100, // 总页数
      dialogVisible: false,
      dialogVisible1: false,
      userList:[],
      formData:{},
      title: '',
      content:'',
    }
  },
  created() { // 页面渲染之前执行，一般调用method定义方法
    this.getList();
    this.getInit();
  },
  methods: { // 创建具体的方法, 定义的方法
    // 查询
    getList(page = 1) {
      this.page = page
      pageQuery(this.page, this.limit, this.dataVo)
        .then(response => { // 请求成功
          this.list = response.data.rows.records
          this.total = response.data.rows.total
        })
    },
    // 确认按钮
    handleBtn() {
      if(this.formData.status === '1'){
        this.formData.reason = "";
      }
      audit(this.formData)
        .then(response => { // 请求成功
          if(response.success){
            this.$message({
              type: 'success',
              message: '提交成功！'
            })
          }
          this.getList()
        })
      this.dialogVisible = false;
    },
    getInit(){
      getUserAll().then(response=>{
        this.userList = response.data.data
      })
    },

    // 修改和更新功能
    updateAndSave(row) {

      this.formData = row
      this.formData.status = '';
      this.dialogVisible = true
    },
    downloadFile(fileUrl) {
      if(fileUrl == '' || fileUrl == null){
        this.$message({
          type: 'error',
          message: '未上传申请资料！'
        })
      }else{
        getWord(fileUrl).then(res=>{
          const blob = new Blob([res]);
          const fileName = "申请资料"+new Date().getTime()+".docx";
          const linkNode = document.createElement('a');
          linkNode.download = fileName;
          linkNode.style.display = 'none';
          linkNode.href = URL.createObjectURL(blob);
          document.body.appendChild(linkNode);
          linkNode.click();
          URL.revokeObjectURL(linkNode.href);
          document.body.removeChild(linkNode);
        })

      }

    },
    viewDetails(content){
      this.dialogVisible1 = true;
      this.content = content;
    },
    // 删除
    removeDataById(id) {
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'

      }).then(() => { // 确定执行的方法
        deleteById(id)
          .then(response => { // 删除成功执行的方法
            if (response.success) {
              this.$message({
                type: 'success',
                message: '删除成功！'
              })
              // 刷新表格
              this.getList()
            }
          })
      })
    },
    cancelBtn(){
      this.dialogVisible = false;
      this.getList();
    },
    // 清空按按钮执行的方法
    resetData() {
      // 第一步清空条件数据
      this.dataVo = {};
      this.page = 1; // 当前页
      this.limit = 10; // 当前页
      this.getList()
    }
  }
}
</script>
<style scoped>
.productPicture-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.productPicture-uploader .el-upload:hover {
  border-color: #409EFF;
}
.productPicture-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
  border-style: dotted;
  margin: 10px 0;
}
.productPicture {
  width: 178px;
  height: 178px;
  display: block;
}
</style>

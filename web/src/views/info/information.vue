<template>
  <div class="app-container">
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item label="资讯标题">
        <el-input v-model="dataVo.title" placeholder="请输入资讯标题"/>
      </el-form-item>
      <el-button type="primary" icon="el-icon-search" @click="getList()">查询</el-button>
      <el-button type="default" @click="resetData()">重置</el-button>
      <el-button type="primary" @click="updateAndSave(null)">新增</el-button>
    </el-form>
    <!-- 表格 -->
    <el-table
      :data="list"
      border
      fit
      highlight-current-row
      empty-text="暂无数据"
    >
<!--      <el-table-column align="center" label="资讯封面">-->
<!--        <template slot-scope="scope">-->
<!--          <img-->
<!--            style="width: 80px; height: 80px"-->
<!--            :src="scope.row.image"/>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column prop="title" label="资讯标题"/>
      <el-table-column align="content" label="资讯内容">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" icon="el-icon-search" @click="viewDetails(scope.row.content)">查看内容</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间"/>
      <el-table-column label="操作" align="center" width="300">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" icon="el-icon-edit" @click="updateAndSave(scope.row)">修改</el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeDataById(scope.row.id)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 新增修改弹框 -->
    <el-dialog
      :title="title"
      :visible.sync="dialogVisible"
      width="60%"
    >
      <el-form :model="formData" label-position="left" label-width="120px">
        <el-form-item label="资讯标题">
          <el-input v-model="formData.title"   placeholder="请填写资讯标题"/>
        </el-form-item>

<!--        <el-form-item label="资讯封面">-->
<!--          <el-upload-->
<!--            ref="upload"-->
<!--            action="http://localhost:9002/files/file"-->
<!--            :on-success="fileHandleSuccess"-->
<!--            accept="image/*"-->
<!--            class="productPicture-uploader"-->
<!--          >-->
<!--            <img v-if="formData.image" :src="formData.image" class="productPicture">-->
<!--            <i v-else class="el-icon-plus productPicture-uploader-icon"></i>-->
<!--          </el-upload>-->
<!--        </el-form-item>-->

        <el-form-item label="资讯内容">
          <tinymce v-if="dialogVisible" v-model="formData.content" :height="300"></tinymce>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleBtn">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 内容查看 -->
    <el-dialog
      title="资讯内容"
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
import { deleteById, update, insert, pageQuery} from '@/api/information'
import Tinymce from '@/components/Tinymce'
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
      formData: {
        imgPath:''
      },
      title: '',
      content:''
    }
  },
  created() { // 页面渲染之前执行，一般调用method定义方法
    this.getList()
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

    // 修改和更新功能
    updateAndSave(row) {
      this.disabledFlag = false
      if (row == null) {
        this.title = '新增扶贫资讯信息'
        this.formData = {
          image: ''
        }
      } else {
        this.title = '修改扶贫资讯信息'
        this.formData = row
        this.disabledFlag = true
      }
      this.dialogVisible = true
    },

    // 上传封面成功
    fileHandleSuccess(response) {
      this.formData.image = response.data.row;
      this.$refs.upload.clearFiles() //去掉文件列表
    },

    viewDetails(content){
      this.dialogVisible1 = true;
      this.content = content.replace(
        /<img/g,
        "<img style='max-width:100%';height:auto;"
      )
    },

    // 确认按钮
    handleBtn() {
      // 关闭弹窗
      this.dialogVisible = false
      if (this.formData.id) {
        update(this.formData)
          .then(response => { // 请求成功
            if(response.success){
              this.$message({
                type: 'success',
                message: '修改成功！'
              })
            }
            this.getList()
          })
      } else {
        insert(this.formData)
          .then(response => { // 请求成功
            if(response.success){
              this.$message({
                type: 'success',
                message: '新增成功！'
              })
            }

            this.getList()
          })
      }
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

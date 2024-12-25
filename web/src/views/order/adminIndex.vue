<template>
  <div class="app-container">
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item label="订单号">
        <el-input v-model="dataVo.orderId" placeholder="请输入订单号"/>
      </el-form-item>
      <el-form-item label="订单状态">
        <el-select v-model="dataVo.status" clearable placeholder="请选择订单状态">
          <el-option value="1" label="已支付"/>
          <el-option value="2" label="待收货"/>
          <el-option value="3" label="订单完成"/>
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
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-table
            :data="props.row.ordersList"
            border
            fit
            highlight-current-row
            empty-text="暂无数据"
          >
            <el-table-column align="center" label="封面">
              <template slot-scope="scope">
                <img
                  style="width: 80px; height: 80px"
                  :src="scope.row.productPicture"/>
              </template>
            </el-table-column>
            <el-table-column prop="productName" label="商品名称"/>
            <el-table-column prop="productNum" label="数量"/>
            <el-table-column prop="productPrice" label="单价"/>
          </el-table>
        </template>
      </el-table-column>
      <el-table-column prop="orderId" label="订单号"/>
      <el-table-column prop="userName" label="下单用户"/>
      <el-table-column prop="totalPrice" label="总价"/>
      <el-table-column prop="name" label="收货人姓名"/>
      <el-table-column prop="phone" label="收货人手机号"/>
      <el-table-column prop="address" label="收货人地址"/>
      <el-table-column prop="status" label="订单状态">
        <template slot-scope="scope">
          <el-tag type="warning" v-if="scope.row.status === '1'">待发货</el-tag>
          <el-tag type="primary" v-if="scope.row.status === '2'">待收货</el-tag>
          <el-tag type="danger" v-if="scope.row.status === '3'">订单完成</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="下单时间"/>
      <el-table-column label="操作" align="center" width="300">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" icon="el-icon-edit" v-if="scope.row.status === '1'" @click="sendDeliver(scope.row.orderId)">确认发货</el-button>
        </template>
      </el-table-column>
    </el-table>
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
import { pageQuery,update} from '@/api/orders'
import { getToken} from '@/utils/auth'
export default {
  data() { // 定义变量和初始值
    return {
      list: [], // 查询之后接口返回集合
      dataVo: {}, // 条件封装对象
      page: 1, // 当前页
      limit: 9, // 每页记录数
      total: 100, // 总页数
    }
  },
  created() { // 页面渲染之前执行，一般调用method定义方法
    this.getList()
  },
  methods: { // 创建具体的方法, 定义的方法
    // 查询
    getList(page = 1) {
      this.page = page // 获取用户点击的页码赋值
      // this.dataVo.userId = getToken();
      pageQuery(this.page, this.limit, this.dataVo)
        .then(response => { // 请求成功
          this.list = response.data.rows.records
          this.total = response.data.rows.total
        })
    },
    sendDeliver(id){
      this.$confirm('此操作将商品发货, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'success'

      }).then(() => {
        update({
          status: '2',
          orderId: id
        })
          .then(response => {
            this.$message({
              type: 'success',
              message: '发货成功！'
            })

            this.getList()
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
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>

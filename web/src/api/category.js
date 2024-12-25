import request from '@/utils/request'

export function pageQuery(page, limit, data) {
  return request({
    url: `/category/pageQuery/${page}/${limit}`,
    method: 'post',
    data: data
  })
}
export function deleteById(id) {
  return request({
    url: `/category/deleteById/${id}`,
    method: 'delete'
  })
}
export function insert(data){
  return request({
    url: `/category/insert`,
    method: 'post',
    data: data
  })
}

export function update(data){
  return request({
    url: `/category/update`,
    method: 'put',
    data: data
  })
}
export function getCategoryAll() {
  return request({
    url: '/category/getCategoryAll',
    method: 'get'
  })
}

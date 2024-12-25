import request from '@/utils/request'

export function pageQuery(page, limit, data) {
  return request({
    url: `/orders/pageQuery/${page}/${limit}`,
    method: 'post',
    data: data
  })
}
export function deleteById(id) {
  return request({
    url: `/orders/deleteById/${id}`,
    method: 'delete'
  })
}
export function update(data){
  return request({
    url: `/orders/update`,
    method: 'put',
    data: data
  })
}

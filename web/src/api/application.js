import request from '@/utils/request'

export function pageQuery(page, limit, data) {
  return request({
    url: `/application/pageQuery/${page}/${limit}`,
    method: 'post',
    data: data
  })
}
export function deleteById(id) {
  return request({
    url: `/application/deleteById/${id}`,
    method: 'delete'
  })
}
export function audit(data){
  return request({
    url: `/application/audit`,
    method: 'put',
    data: data
  })
}

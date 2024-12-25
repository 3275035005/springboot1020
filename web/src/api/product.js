import request from '@/utils/request'

export function pageQuery(page, limit, data) {
  return request({
    url: `/product/pageQuery/${page}/${limit}`,
    method: 'post',
    data: data
  })
}
export function deleteById(id) {
  return request({
    url: `/product/deleteById/${id}`,
    method: 'delete'
  })
}
export function insert(data){
  return request({
    url: `/product/insert`,
    method: 'post',
    data: data
  })
}

export function update(data){
  return request({
    url: `/product/update`,
    method: 'put',
    data: data
  })
}

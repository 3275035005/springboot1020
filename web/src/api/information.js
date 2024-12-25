import request from '@/utils/request'

export function pageQuery(page, limit, data) {
  return request({
    url: `/information/pageQuery/${page}/${limit}`,
    method: 'post',
    data: data
  })
}
export function deleteById(id) {
  return request({
    url: `/information/deleteById/${id}`,
    method: 'delete'
  })
}
export function insert(data){
  return request({
    url: `/information/insert`,
    method: 'post',
    data: data
  })
}

export function update(data){
  return request({
    url: `/information/update`,
    method: 'put',
    data: data
  })
}

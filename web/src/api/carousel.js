import request from '@/utils/request'

export function pageQuery(page, limit, data) {
  return request({
    url: `/carousel/pageQuery/${page}/${limit}`,
    method: 'post',
    data: data
  })
}
export function deleteById(id) {
  return request({
    url: `/carousel/deleteById/${id}`,
    method: 'delete'
  })
}
export function insert(data){
  return request({
    url: `/carousel/insert`,
    method: 'post',
    data: data
  })
}

export function update(data){
  return request({
    url: `/carousel/update`,
    method: 'put',
    data: data
  })
}

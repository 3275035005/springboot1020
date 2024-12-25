import request from '@/utils/request'

export function pageQuery(page, limit, data) {
  return request({
    url: `/policy/pageQuery/${page}/${limit}`,
    method: 'post',
    data: data
  })
}
export function deleteById(id) {
  return request({
    url: `/policy/deleteById/${id}`,
    method: 'delete'
  })
}
export function insert(data){
  return request({
    url: `/policy/insert`,
    method: 'post',
    data: data
  })
}

export function update(data){
  return request({
    url: `/policy/update`,
    method: 'put',
    data: data
  })
}

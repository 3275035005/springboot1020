import request from '@/utils/request'

export function pageQuery(page, limit, data) {
  return request({
    url: `/project/pageQuery/${page}/${limit}`,
    method: 'post',
    data: data
  })
}
export function deleteById(id) {
  return request({
    url: `/project/deleteById/${id}`,
    method: 'delete'
  })
}
export function audit(data){
  return request({
    url: `/project/audit`,
    method: 'put',
    data: data
  })
}

export function getWord(url) {
  return request({
    url: url,
    method: 'get',
    responseType: 'blob'
  })
}

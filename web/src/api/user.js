import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/users/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/users/info?token='+token,
    method: 'get'
  })
}

export function pageQuery(page, limit, data) {
  return request({
    url: `/users/pageQuery/${page}/${limit}`,
    method: 'post',
    data: data
  })
}
export function deleteById(id) {
  return request({
    url: `/users/deleteById/${id}`,
    method: 'delete'
  })
}
export function insert(data){
  return request({
    url: `/users/insert`,
    method: 'post',
    data: data
  })
}

export function update(data){
  return request({
    url: `/users/update`,
    method: 'put',
    data: data
  })
}


export function getUserAll() {
  return request({
    url: '/users/getUserAll',
    method: 'get'
  })
}
export function updatePassword(data) {
  return request({
    url: `/users/updatePassword`,
    method: 'post',
    data: data
  })
}

export function resetPassword(id){
  return request({
    url: `/users/resetPassword/`+id,
    method: 'post'
  })
}


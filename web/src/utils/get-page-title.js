import defaultSettings from '@/settings'

const title = defaultSettings.title || '农产品助销扶贫管理系统'

export default function getPageTitle(pageTitle) {
  if (pageTitle) {
    return `${pageTitle} - ${title}`
  }
  return `${title}`
}

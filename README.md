# Erwin Message

即时通讯

## 端口

后端端口：9191

服务端口：8899

## 提供
- 网页
- 端API
- 后台管理API
- 后台管理系统

## 功能

## 登录

```
POST http://localhost:9191/api-index/auth/login
{
    "username":"xfsy_2015@163.com",
    "password":"123456"
}
```

```json
{
    "code": "1",
    "msg": "Success",
    "success": true,
    "body": {
        "token": "eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2Mjc2MTEyNjMsInN1YiI6Inhmc3lfMjAxNUAxNjMuY29tIiwiaWF0IjoxNjI3NTgyNDYzMTM5fQ.dlKEC3HAQJGnr_-dQQkCJQLBDTYvXVILA6GmIEpwaXh1JOYojhj7p1345NqDD1D8hBLGOCN22jL0n821F63cLQ"
    }
}
```

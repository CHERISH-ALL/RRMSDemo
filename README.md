## 前后端分离项目

包含登录、注册、密码重置等功能，可以二次开发编写具体场景下的应用程序

* 登录功能（支持用户名、邮箱登录）
* 1.用户访问成功后才能访问index下的页面
* 2.用户没有登录则自动跳转到登录界面
* 3.用户访问的是不存在的页面，依然强制返回登陆界面，如果已经登录，返回到index
* 解决方案
* 无论是否登录直接相浏览器请求用户信息
* 如果请求成功，说明已经登录
* 如果请求失败，则是未登录，跳转到登陆界面

登录功能：

* 用户登录成功之后才能访问index之后的目录
* 用户没有登陆自动跳转到登录页面
* 如果请求的是不存在的页面，强制返回到登录界面
* 已经登录返回到index首页

解决方案

* 无论是否登录，直接向后端请求用户信息
* 请求成功则已经登录
* 请求失败则未登录


* 注册用户（通过邮箱注册）

* 重置密码（通过邮箱重置密码）

Date:2023.7.12

* 完成用户按照用户名和邮箱登录
* 准备尝试用户按照id登录

Date:2023.7.13,part1

* 尝试失败
* 完成前端登录简单设计
* 尝试对项目进行简单修改

Date:2023.7.13,part2

* 简单修改完成，完成项目路由修改
* 完成登录成功界面简单布局

Date:2023.7.13,part3

* 实现已存在用户登录功能
* 完成对错误信息的提示功能

Date:2023.7.14,part1

* 完成用户注册界面的设计
* 为项目添加页面变更动画

Date:2023.7.14,part2

* 完成注册信息的输入校验

Date:2023.7.14,part3

* 完成邮箱验证码的发送
* 限定时间内不许再次发送失败（正在调试）

Date:2023.7.14,part4

* 完成限定时间内不许再次发送失败
* ！！！！要下载redis！！！！！

Date:2023.7.15,part1

* 完成匹配验证码注册
* 完成用户邮箱不能重复注册
* 完成密码加密

Date:2023.7.15,part2

* 完成用户注册
* 完成忘记密码页面设计

Date:2023.7.15,part3

* 完成用户忘记密码功能
* 完成不能重复用户名功能

Date:2023.7.16,part1

* 完成用户信息获取后端代码

Date:2023.7.17,part1/2

* 完成需求修改
* 完成参照其他表验证身份
* ！！！！一定要备份！！！！！

Date:2023.7.18,part1

* 完成后端验证用户登录的功能
* 用session传递用户数据

Date:2023.7.20,part1

* 完成用户登录界面设计
* 正在尝试重写获取用户登录信息

Date:2023.7.20,part2

* 完成获取用户登录信息

Date:2023.7.20,part3

* 解决可能同时发送两份邮件的问题
* 修复身份获取的问题
* 准备开始对用户身份最初页面区分

Date:2023.7.21,part1

* 完成记住我的功能
* 开始对用户权限进行管理

Date:2023.7.22,part2

* 完成用户权限管理
* 完成最后index界面设计

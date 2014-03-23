wifi_demon
==========
Wifi 精灵接口定义说明:
***
0324：新增 六 用户评论接口； 新增 七 其他非接口内容
***
0303:新增 五 外部接口内容，电信认证接口及热点位置接口
***
0220：新增1.3密码修改接口
***
测试数据接口可以访问http://demonwf.ap01.aws.af.cm ， 目前已经有测试数据。目前没有数据加密。
***


所有手机端发送请求需要在http头设置参数{"datatype":"wifidemon"}
***
用户登录后，所有请求的http头还需加入{"userid": userid}, 其中userid在登录时获取。
***


一、用户注册
***

   1.注册
***

请求类型:POST
***
请求地址：http://ip:port/demon/rest/login/register
***
参数：{"username": username, "password": password, "mobile":mobile, "nickname":nickname, "mail":mail, "optname": optname, "locale":locale}
***

    返回：{"status": status, "data": null, "userid": null, "msg": msg}
***

    备注：
***
       注册是需要在http head加入{"regtype":"demonregister"}信息
***

       参数：用户名，密码，手机，昵称，邮件，运营商名字，归属地
***

       status=ok时注册成功，status=err时注册失败，msg为提示信息。下同。
***

   2.检查用户名
***
请求类型:POST
***
请求地址：http://ip:port/demon/rest/login/checkname
***
参数：{"username": username}
***
    返回：{"status": status, "data": "", "userid": "", "msg": msg}
***

    备注：
***

       参数：用户名
***

       status=ok时用户名可用，status=err时用户名不可用，msg为提示信息。下同。
***
3.修改密码：
***
请求类型:POST
***
请求地址：http://ip:port/demon/rest/user/chnpwd
***
参数：{"userid": userid, "old": oldpassword, "new": newpassword}
***
    返回：{"status": status, "data": "", "msg": msg}
***

    备注：
***

       参数：用户id(登录时获取的唯一用户标识，唯一区别用户)，old(原始密码),new(新密码)
***


二、用户登录
***

   1.用户登录
***

    请求类型: POST
***

    请求地址：http://ip:port/demon/rest/login
***

    参数：{"username": username, "password": password}
***

    返回：{"status": status, "data": key, "userid": userid, "msg": msg}
***

    备注：
***

       密码加密
***

       status=ok时登录成功，status=err时登录失败，msg为提示信息。下同。
***

       data为此次连接的密钥。userid为该用户id
***


三、上网管理
***

   1.获取wifi用户账号
***

    请求类型：GET
***

    请求地址：http://ip:port/demon/rest/account/set?opt=params
***

    参数：params为运营商代码，若为多个，用逗号分隔。运营商代码：中国移动:10086; 中国联通:10010;电信189
***

         例如:http://ip:port/demon/rest/account/set?opt=10086,189
***

    返回：{"status": status, "data": {"opt": operator, "id": id, "name": name, "pwd": pwd}, "msg": msg}
***

    备注: data为wifi登录的运营商代码(operator)、帐号ID、wifi帐号名(name)、密码(pwd);
***

         密码使用DES加密，密钥为登录时获取的密钥值，算法示例见本仓库。
***


   2.成功登录wifi
***

    请求类型:GET
***

    请求地址:http://ip:port/demon/rest/account/on?id=id
***

    参数：id为wifi帐号id。
***

    返回:{"status": status, "data": null, "msg": "Start timming"}
***

    备注:计时开始
***


   3.心跳请求
***

    请求类型:GET
***

    请求地址:http://ip:port/demon/rest/account/alive?id=id
***

    参数：
*** 上网帐号wifi的id

    返回：{"status": status, "data": null, "msg": null}
***

    备注:app至少每10分钟发送一次，证明终端用户还在使用wifi上网。若超时，则服务端停止该用户计时。
***


   4.断开wifi
***

    请求类型:GET
***

    请求地址：http://ip:port/demon/rest/account/off?id=id
***

    参数：
***上网帐号wifi的id

    返回：{"status": status, "data": null, "msg": null}
***


四、套餐信息
***
五、外部接口

***
1.oauth认证(emp/oauth2/v2/access_token)
***
请求类型：GET
***
请求地址：http://ip:port/demon/rest/api/oauth
***
参数：{"app_id"：app_id， "app_secret":app_secret, "grant_type":grant_type,"redirect_uri":redirect_uri,"code":code}
***
返回：电信标准内容
***
2.hotpot loation（HpInfoQuery/Enabler/location_hp）
***
请求类型：GET
***
请求地址：http://ip:port/demon/rest/api/location
***
参数：{"app_id":app_id, "access_token":access_token,"format":format,"timestamp":timestamp}
***
返回：电信标准内容
***
备注：参数format默认JSON,参数timestamp默认服务器当前时间。
***
六、用户反馈

***
1.发表评论
***
请求类型：POST
***
请求地址：http://ip:port/demon/rest/helper/comment
***
参数：{"userid"：userid， "comment":comment, "priority":priority,"pid":pid}
***
返回：{"status": status, "data": "", "msg": msg}
***
备注：
***
    —— userid、comment必填，其它参数目前可以没有
***
2.获取评论
***
请求类型：GET
***
请求地址：http://ip:port/demon/rest/helper/comment_a?userid=userid&pid=pid&priority=priority
***
参数：根据userid, pid, priority查询，条件可以为空，返回全部
***
返回：{"status": status, "data": data, "msg": ""}
***
七、其它非接口页面地址：
***
1.套餐选择页面：http://ip:port/demon/rest/set/mset?userid 
***
2.软件介绍：http://ip:port/demon/rest/helper/intr
***
3.关于：http://ip:port/demon/rest/helper/about
***
4.使用向导：http://ip:port/demon/rest/helper/guide

package com.degal.webdemo;

import android.content.Intent;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.yanzhenjie.andserver.annotation.PostMapping;
import com.yanzhenjie.andserver.annotation.RequestMapping;
import com.yanzhenjie.andserver.annotation.RequestParam;
import com.yanzhenjie.andserver.annotation.RestController;



/**
 * Created by user:hsj
 * data:on 2018/11/15 0015.
 * class:接口
 * 修改人员：
 * 修改时间：
 * 修改内容：
 */
@RestController
@RequestMapping(path = "/user")
public class UserController {


    @PostMapping(value = "/detail")
    public Object detail(@RequestParam(value = "position", required = false) String position,
                         @RequestParam(value = "sex", required = false) String sex,
                         @RequestParam("flag") String flag,
                         @RequestParam(value = "icsn", required = false) String icsn,
                         @RequestParam(value = "department", required = false) String department,
                         @RequestParam(value = "company", required = false) String company,
                         @RequestParam(value = "empcode", required = false) String empcode,
                         @RequestParam(value = "empname", required = false) String empname,
                         @RequestParam(value = "ickh", required = false) String ickh,
                         @RequestParam(value = "photo", required = false) String photo) {
        User user = new User();
        user.setPosition(!TextUtils.isEmpty(position) ? position : "未知");
        user.setSex(!TextUtils.isEmpty(sex) ? sex : "未知");
        user.setFlag(!TextUtils.isEmpty(flag) ? flag : "");
        user.setIcsn(!TextUtils.isEmpty(icsn) ? icsn : "未知");
        user.setDepartment(!TextUtils.isEmpty(department) ? department : "未知");
        user.setCompany(!TextUtils.isEmpty(company) ? company : "未知");
        user.setEmpcode(!TextUtils.isEmpty(empcode) ? empcode : "未知");
        user.setEmpname(!TextUtils.isEmpty(empname) ? empname : "异常人员");
        user.setIckh(!TextUtils.isEmpty(ickh) ? ickh : "未知");
        user.setPhoto(!TextUtils.isEmpty(photo) ? photo : "");

        Intent intent = new Intent(MainActivity.ACTION_REFRESH);
        intent.putExtra("user", new Gson().toJson(user));
        ResultBean resultBean = new ResultBean();
        if (null != MainActivity.mContext) {
            MainActivity.mContext.sendBroadcast(intent);
            resultBean.setCode(0);
            resultBean.setMessage("ok");
            return new Gson().toJson(resultBean);
        } else {
            resultBean.setCode(100);
            resultBean.setMessage("fail");
            return new Gson().toJson(resultBean);
        }
    }

}

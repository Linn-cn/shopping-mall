package com.shopping.mall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shopping.mall.constant.ApplicationConstants;
import com.shopping.mall.constant.HttpStatusConstants;
import com.shopping.mall.entity.User;
import com.shopping.mall.exception.BizException;
import com.shopping.mall.service.ShoppCartService;
import com.shopping.mall.service.UserService;
import com.shopping.mall.util.*;
import com.shopping.mall.validate.UserLogin;
import com.shopping.mall.validate.UserRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.util.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * <p>
 * 用户控制器
 * </p>
 *
 * @author zhulin
 * @since 2019-11-25
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppCartService shoppCartService;

    @Autowired
    private HttpSession session;

    @Value("${upload.sqlUserImg}")
    private String SQL_USER_IMG;

    @Value("${upload.serverUserImg}")
    private String SERVER_USER_IMG;

    /**
     * 登录
     *
     * @param user
     * @return com.shopping.mall.util.Result
     * @date 2019/11/26 15:44
     */
    @GetMapping("/login")
    public Result<String> login(@Validated(UserLogin.class) User user, @NotBlank(message = "无效验证码!") String verifyCode) {
        String sessionVerifyCode = (String) session.getAttribute(ApplicationConstants.LOGIN_VERIFY_CODE_KEY);
        if (!verifyCode.equals(sessionVerifyCode)) {
            return ResultGenerator.getResultByHttp(HttpStatusConstants.BAD_REQUEST, "验证码错误!");
        }
        // 登录成功后清除session中的验证码，防止反复利用该key登录。
        session.removeAttribute(ApplicationConstants.LOGIN_VERIFY_CODE_KEY);
        user.setUserPassword(DigestUtils.md5DigestAsHex(user.getUserPassword().getBytes()));
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>(user);
        User sqlUser = userService.getOne(queryWrapper);
        if (sqlUser != null) {
            session.setAttribute(ApplicationConstants.MALL_USER_SESSION_KEY, sqlUser);
            return ResultGenerator.getResultByHttp(HttpStatusConstants.OK);
        }
        return ResultGenerator.getResultByHttp(HttpStatusConstants.UNAUTHORIZED);
    }

    /**
     * 注册
     *
     * @param user
     * @return com.shopping.mall.util.Result
     * @date 2019/11/26 15:44
     */
    @PostMapping("/register")
    public Result<String> register(@Validated(UserRegister.class) User user,
                           @NotBlank(message = "无效验证码!") String verifyCode) {
        String sessionVerifyCode = (String) session.getAttribute(ApplicationConstants.REGISTER_VERIFY_CODE_KEY);
        if (!verifyCode.equals(sessionVerifyCode)) {
            return ResultGenerator.getResultByHttp(HttpStatusConstants.BAD_REQUEST, "验证码错误!");
        }
        // 注册成功后清除session中的验证码，防止反复利用该key重复注册。
        session.removeAttribute(ApplicationConstants.REGISTER_VERIFY_CODE_KEY);
        user.setUserName(ApplicationConstants.USER_DEFAULT_PREFIX + user.getUserPhone());
        user.setUserImg(ApplicationConstants.USER_DEFAULT_IMG);
        user.setUserPassword(DigestUtils.md5DigestAsHex(user.getUserPassword().getBytes()));
        user.setCreateTime(DateUtils.getLocalDateTime());
        boolean flag = false;
        try {
            flag = userService.save(user);
        } catch (DataAccessException e) {
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                throw new BizException("当前手机号已注册!");
            }
        }
        return flag ? ResultGenerator.getResultByHttp(HttpStatusConstants.OK)
                : ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR, "注册失败");
    }

    /**
     * 上传用户头像
     *
     * @param request
     * @param file
     * @return com.shopping.mall.util.Result<java.lang.String>
     * @date 2019/11/28 18:35
     */
    @PostMapping("/uploadImg")
    public Result<String> uploadUserImg(HttpServletRequest request,
                                        HttpServletResponse response,
                                        @RequestParam("file") MultipartFile file) throws URISyntaxException {
        String suffixName = UploadFileUtils.getSuffixName(file);
        //生成通用文件名称
        String newFileName = UploadFileUtils.getNewFileName(suffixName);
        File fileDirectory = new File(SERVER_USER_IMG);
        //创建文件
        File destFile = new File(SERVER_USER_IMG + newFileName);
        try {
            if (!fileDirectory.exists()) {
                if (!fileDirectory.mkdirs()) {
                    throw new IOException("文件夹创建失败,路径为：" + fileDirectory);
                }
            }
            file.transferTo(destFile);
            String userImg = URIutils.getHost(new URI(request.getRequestURL().toString())) + SQL_USER_IMG + newFileName;
            User user = (User) session.getAttribute(ApplicationConstants.MALL_USER_SESSION_KEY);
            if (userService.updateById(new User().setUserId(user.getUserId()).setUserImg(userImg))) {
                refreshSessionUser(user.getUserId());
                return ResultGenerator.getResultByHttp(HttpStatusConstants.OK, userImg);
            }
            // 使响应状态为500
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 修改用户信息
     * @param userName
     * @param userPassword
     * @return com.shopping.mall.util.Result<java.lang.String>
     * @date 2019/11/29 14:46
     */
    @PostMapping("/update")
    public Result<String> updateUserInfo(String userName, String userPassword,String userAddress) {
        User user = (User) session.getAttribute(ApplicationConstants.MALL_USER_SESSION_KEY);
        boolean flag = userService.updateById(new User()
                .setUserId(user.getUserId())
                .setUserName(userName)
                .setUserPassword(DigestUtils.md5DigestAsHex(userPassword.getBytes()))
                .setUserAddress(userAddress));
        if (flag){
            refreshSessionUser(user.getUserId());
            return ResultGenerator.getResultByHttp(HttpStatusConstants.OK);
        }
        return ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR);
    }

    /**
     * 密码验证
     *
     * @param password
     * @return
     */
    @GetMapping("/validatePwd")
    public Result validatePwd(String password) {
        User user = (User) session.getAttribute(ApplicationConstants.MALL_USER_SESSION_KEY);
        User sqlUser = userService.getById(user.getUserId());
        if (sqlUser.getUserPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
            return ResultGenerator.getResultByHttp(HttpStatusConstants.OK, true);
        }
        return ResultGenerator.getResultByHttp(HttpStatusConstants.OK, false);
    }

    /**
     * 注销用户
     *
     * @param
     * @return com.shopping.mall.util.Result
     * @date 2019/11/27 15:43
     */
    @GetMapping("/logout")
    public Result<String> Logout() {
        session.removeAttribute(ApplicationConstants.MALL_USER_SESSION_KEY);
        return ResultGenerator.getResultByHttp(HttpStatusConstants.OK);
    }

    /**
     * 刷新会话里的user信息
     *
     * @param userId
     * @return void
     * @date 2019/11/28 21:59
     */
    private void refreshSessionUser(Integer userId) {
        session.setAttribute(ApplicationConstants.MALL_USER_SESSION_KEY, userService.getById(userId));
    }

}


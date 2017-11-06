package com.mymvc.system.provider;

import com.mymvc.constant.Constant;
import com.mymvc.constant.ConstantAuthority;
import com.mymvc.repository.hibernate.basic.Status;
import com.mymvc.repository.hibernate.pojo.UserToken;
import com.mymvc.repository.hibernate.service.resource.IUserTokenService2;
import com.mymvc.system.annotation.Provider;
import com.mymvc.system.basic.CompactProvider;
import com.mymvc.system.basic.ExceptionError;
import com.mymvc.system.callback.IFilePutCallback;
import com.mymvc.system.exception.IllegalAccessDeniedException;
import com.mymvc.system.exception.IllegalServiceException;
import com.mymvc.system.listener.RequestListener;
import com.mymvc.system.pojo.AuthorityPojo;
import com.mymvc.system.provider.basic.ISecurity;
import com.mymvc.system.utils.CommonUtil;
import com.mymvc.system.utils.DateUtil;
import com.mymvc.system.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * Created by alan.luo on 2017/11/1.
 */
@Provider
public class SecurityProvider extends CompactProvider implements ISecurity {

    @Autowired
    private IUserTokenService2 tokenService;

    public SecurityProvider() {
        super(SecurityProvider.class);
    }

    @Override
    public boolean intersection() throws IllegalAccessDeniedException {

        RequestListener listener = RequestListener.getInstance();

        /**
         * check the http request version is or not valid.
         */
//        if (this.checkVersion(listener.getHeader(Constant.version)) == false){
//            return false;
//        }

        /**
         * if the ip address has in the black box,then return false.
         */
        if (checkIps(listener.getClientIp()) == false){
            return false;
        }

        if (listener.getController().indexOf("/api/") > -1){
            return checkLoginForUser(listener);
        }else {
            return checkRolesAccessForAdmin(listener);
        }
    }

    @Override
    public boolean checkRolesAccessForAdmin(RequestListener listener) throws IllegalAccessDeniedException {

        System.out.println("+++++++++++"+listener.getController());
        return false;
    }

    @Override
    public boolean checkLoginForUser(RequestListener listener) throws IllegalAccessDeniedException {
        String ctl = listener.getController();
        if (listener.getAction().equals("POST")){
            ctl += "/" + listener.getAction().toLowerCase();
        }
        if (listener.getAction().equals("PUT") || listener.getAction().equals("DELETE")){
            ctl = ctl.substring(0,ctl.lastIndexOf("/")) + listener.getAction().toLowerCase();
        }
        ctl = ctl.toLowerCase();
        if (ConstantAuthority.authority.containsKey(ctl)){
            AuthorityPojo auth = ConstantAuthority.authority.get(ctl);
            if (auth.isAuthentication()){
                String token = listener.getHeader(Constant.tokenKey);
                if (token == null || token.length() != 32){
                    throw new IllegalAccessDeniedException(ExceptionError.error_need_logined);
                }
                try {
                    /**
                     * 有token,但是数据没有,可能是在其他地方登录过!
                     */
                    UserToken userToken = tokenService.getByUserToken(token);
                    if (userToken == null){
                        throw new IllegalAccessDeniedException(ExceptionError.error_unauthorized_access_2);
                    }

                    /**
                     * 无效,或者过期
                     */
                    if (userToken.getStatus() == Status.invalid || userToken.getExpireIn() < (DateUtil.getTime()/1000)){
                        throw new IllegalAccessDeniedException(ExceptionError.error_user_token_invalid);
                    }

                } catch (IllegalServiceException e) {
                    e.printStackTrace();
                    throw new IllegalAccessDeniedException(e.getCode());
                }
            }
        }

        return true;
    }

    /**
     * 后期加入的IP黑名单
     * @param ip
     * @return
     * @throws IllegalAccessDeniedException
     */
    @Override
    public boolean checkIps(String ip) throws IllegalAccessDeniedException {
        return true;
    }

    @Override
    public boolean checkVersion(String version) throws IllegalAccessDeniedException {
        return version != null && version.equals("1.0.0");
    }

    public String makeUserToken(String hash){
        return Md5Util.md5(hash+ DateUtil.getTime());
    }

}

package com.yk.mvpframe.event;

/**
 * @FileName LoginEvent
 * @Author alan
 * @Date 2019/8/15 15:17
 * @Describe TODO
 * @Mark
 **/
public class LoginEvent {

    public final static int LOGIN=0;
    public final static int LOGINOUT=1;

    private int loginFlag;

    public LoginEvent(int flag) {
        this.loginFlag = flag;
    }

    public int getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(int flag) {
        this.loginFlag = flag;
    }
}

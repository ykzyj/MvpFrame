package com.yk.mvpframe.model;

import java.util.List;

/**
 * @FileName UserInfoModel
 * @Author alan
 * @Date 2019/7/8 9:36
 * @Describe TODO
 * @Mark
 **/
public class UserInfoModel {
    private AppUser appUser;

    private List<UserHaveRobotList> userHaveRobotList ;

    public void setAppUser(AppUser appUser){
        this.appUser = appUser;
    }
    public AppUser getAppUser(){
        return this.appUser;
    }
    public void setUserHaveRobotList(List<UserHaveRobotList> userHaveRobotList){
        this.userHaveRobotList = userHaveRobotList;
    }
    public List<UserHaveRobotList> getUserHaveRobotList(){
        return this.userHaveRobotList;
    }

    public static class UserHaveRobotList {
        private Address address;

        private String babyName;

        private int bindingCount;

        private String chatAccount;

        private String chatPassword;

        private long copyTimestamp;

        private String firmwareVersion;

        private long firstBindingTimestamp;

        private long firstUse4gTimestamp;

        private String id;

        private int isMasterNumber;

        private int isOnline;

        private long lastOnlineTimestamp;

        private String nightModeTimeRange;

        private long productTimeStamp;

        private long recentBindingTimestamp;

        private int robotStatus;

        private RobotType robotType;

        private String secretKey;

        private String serialNumber;

        public void setAddress(Address address){
            this.address = address;
        }
        public Address getAddress(){
            return this.address;
        }
        public void setBabyName(String babyName){
            this.babyName = babyName;
        }
        public String getBabyName(){
            return this.babyName;
        }
        public void setBindingCount(int bindingCount){
            this.bindingCount = bindingCount;
        }
        public int getBindingCount(){
            return this.bindingCount;
        }
        public void setChatAccount(String chatAccount){
            this.chatAccount = chatAccount;
        }
        public String getChatAccount(){
            return this.chatAccount;
        }
        public void setChatPassword(String chatPassword){
            this.chatPassword = chatPassword;
        }
        public String getChatPassword(){
            return this.chatPassword;
        }
        public void setCopyTimestamp(long copyTimestamp){
            this.copyTimestamp = copyTimestamp;
        }
        public long getCopyTimestamp(){
            return this.copyTimestamp;
        }
        public void setFirmwareVersion(String firmwareVersion){
            this.firmwareVersion = firmwareVersion;
        }
        public String getFirmwareVersion(){
            return this.firmwareVersion;
        }
        public void setFirstBindingTimestamp(long firstBindingTimestamp){
            this.firstBindingTimestamp = firstBindingTimestamp;
        }
        public long getFirstBindingTimestamp(){
            return this.firstBindingTimestamp;
        }
        public void setFirstUse4gTimestamp(long firstUse4gTimestamp){
            this.firstUse4gTimestamp = firstUse4gTimestamp;
        }
        public long getFirstUse4gTimestamp(){
            return this.firstUse4gTimestamp;
        }
        public void setId(String id){
            this.id = id;
        }
        public String getId(){
            return this.id;
        }
        public void setIsMasterNumber(int isMasterNumber){
            this.isMasterNumber = isMasterNumber;
        }
        public int getIsMasterNumber(){
            return this.isMasterNumber;
        }
        public void setIsOnline(int isOnline){
            this.isOnline = isOnline;
        }
        public int getIsOnline(){
            return this.isOnline;
        }
        public void setLastOnlineTimestamp(long lastOnlineTimestamp){
            this.lastOnlineTimestamp = lastOnlineTimestamp;
        }
        public long getLastOnlineTimestamp(){
            return this.lastOnlineTimestamp;
        }
        public void setNightModeTimeRange(String nightModeTimeRange){
            this.nightModeTimeRange = nightModeTimeRange;
        }
        public String getNightModeTimeRange(){
            return this.nightModeTimeRange;
        }
        public void setProductTimeStamp(long productTimeStamp){
            this.productTimeStamp = productTimeStamp;
        }
        public long getProductTimeStamp(){
            return this.productTimeStamp;
        }
        public void setRecentBindingTimestamp(long recentBindingTimestamp){
            this.recentBindingTimestamp = recentBindingTimestamp;
        }
        public long getRecentBindingTimestamp(){
            return this.recentBindingTimestamp;
        }
        public void setRobotStatus(int robotStatus){
            this.robotStatus = robotStatus;
        }
        public int getRobotStatus(){
            return this.robotStatus;
        }
        public void setRobotType(RobotType robotType){
            this.robotType = robotType;
        }
        public RobotType getRobotType(){
            return this.robotType;
        }
        public void setSecretKey(String secretKey){
            this.secretKey = secretKey;
        }
        public String getSecretKey(){
            return this.secretKey;
        }
        public void setSerialNumber(String serialNumber){
            this.serialNumber = serialNumber;
        }
        public String getSerialNumber(){
            return this.serialNumber;
        }
    }

    public static class Manufactor {
        private String address;

        private int cooperateStatus;

        private long establishTimestamp;

        private String id;

        private String linkMan;

        private String name;

        private String phone;

        private String secretKey;

        public void setAddress(String address){
            this.address = address;
        }
        public String getAddress(){
            return this.address;
        }
        public void setCooperateStatus(int cooperateStatus){
            this.cooperateStatus = cooperateStatus;
        }
        public int getCooperateStatus(){
            return this.cooperateStatus;
        }
        public void setEstablishTimestamp(long establishTimestamp){
            this.establishTimestamp = establishTimestamp;
        }
        public long getEstablishTimestamp(){
            return this.establishTimestamp;
        }
        public void setId(String id){
            this.id = id;
        }
        public String getId(){
            return this.id;
        }
        public void setLinkMan(String linkMan){
            this.linkMan = linkMan;
        }
        public String getLinkMan(){
            return this.linkMan;
        }
        public void setName(String name){
            this.name = name;
        }
        public String getName(){
            return this.name;
        }
        public void setPhone(String phone){
            this.phone = phone;
        }
        public String getPhone(){
            return this.phone;
        }
        public void setSecretKey(String secretKey){
            this.secretKey = secretKey;
        }
        public String getSecretKey(){
            return this.secretKey;
        }

    }

    public static class RobotType {
        private String color;

        private String id;

        private Manufactor manufactor;

        private String nickName;

        private long productTime;

        private int robotLevel;

        public void setColor(String color){
            this.color = color;
        }
        public String getColor(){
            return this.color;
        }
        public void setId(String id){
            this.id = id;
        }
        public String getId(){
            return this.id;
        }
        public void setManufactor(Manufactor manufactor){
            this.manufactor = manufactor;
        }
        public Manufactor getManufactor(){
            return this.manufactor;
        }
        public void setNickName(String nickName){
            this.nickName = nickName;
        }
        public String getNickName(){
            return this.nickName;
        }
        public void setProductTime(long productTime){
            this.productTime = productTime;
        }
        public long getProductTime(){
            return this.productTime;
        }
        public void setRobotLevel(int robotLevel){
            this.robotLevel = robotLevel;
        }
        public int getRobotLevel(){
            return this.robotLevel;
        }
    }

    public static class AppUser {
        private Address address;

        private String chatAccount;

        private String chatPassword;

        private int id;

        private long lastLoginTimestamp;

        private int messageId;

        private String nickName;

        private String phone;

        private String photoName;

        private long registerTimestamp;

        private int whetherBuilding;

        public void setAddress(Address address){
            this.address = address;
        }
        public Address getAddress(){
            return this.address;
        }
        public void setChatAccount(String chatAccount){
            this.chatAccount = chatAccount;
        }
        public String getChatAccount(){
            return this.chatAccount;
        }
        public void setChatPassword(String chatPassword){
            this.chatPassword = chatPassword;
        }
        public String getChatPassword(){
            return this.chatPassword;
        }
        public void setId(int id){
            this.id = id;
        }
        public int getId(){
            return this.id;
        }
        public void setLastLoginTimestamp(long lastLoginTimestamp){
            this.lastLoginTimestamp = lastLoginTimestamp;
        }
        public long getLastLoginTimestamp(){
            return this.lastLoginTimestamp;
        }
        public void setMessageId(int messageId){
            this.messageId = messageId;
        }
        public int getMessageId(){
            return this.messageId;
        }
        public void setNickName(String nickName){
            this.nickName = nickName;
        }
        public String getNickName(){
            return this.nickName;
        }
        public void setPhone(String phone){
            this.phone = phone;
        }
        public String getPhone(){
            return this.phone;
        }
        public void setPhotoName(String photoName){
            this.photoName = photoName;
        }
        public String getPhotoName(){
            return this.photoName;
        }
        public void setRegisterTimestamp(long registerTimestamp){
            this.registerTimestamp = registerTimestamp;
        }
        public long getRegisterTimestamp(){
            return this.registerTimestamp;
        }
        public void setWhetherBuilding(int whetherBuilding){
            this.whetherBuilding = whetherBuilding;
        }
        public int getWhetherBuilding(){
            return this.whetherBuilding;
        }
    }

    public static class Address {
        private int id;

        private String name;

        public void setId(int id){
            this.id = id;
        }
        public int getId(){
            return this.id;
        }
        public void setName(String name){
            this.name = name;
        }
        public String getName(){
            return this.name;
        }

    }

}

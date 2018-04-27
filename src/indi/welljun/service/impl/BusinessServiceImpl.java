package indi.welljun.service.impl;

import indi.welljun.dao.NoteDao;
import indi.welljun.dao.SendDao;
import indi.welljun.dao.UserDao;
import indi.welljun.dao.impl.NoteDaoImpl;
import indi.welljun.dao.impl.SendDaoImpl;
import indi.welljun.dao.impl.UserDaoImpl;
import indi.welljun.domain.Note;
import indi.welljun.domain.User;
import indi.welljun.exception.UserExistException;
import indi.welljun.service.BusinessService;
import indi.welljun.utils.ServiceUtils;

import java.util.List;

public class BusinessServiceImpl implements BusinessService{
    private UserDao userDao = new UserDaoImpl();
    private NoteDao noteDao = new NoteDaoImpl();
    private SendDao sendDao = new SendDaoImpl();
    //对web层提供注册服务
    @Override
    public void register(User user) throws UserExistException{
        boolean flag = userDao.findUser(user.getUname());
        if(flag){
            throw new UserExistException();
        }
        else{
            user.setPassword(ServiceUtils.md5(user.getPassword())); //将密码MD5
            userDao.addUser(user);
        }
    }
    //用户登录业务
    @Override
    public User userLogin(String uname, String password){
        password = ServiceUtils.md5(password); //先把密码MD5
        return userDao.findUser(uname,password);
    }
    //根据用户id返回用户信息
    @Override
    public User findUser(int uid){
        return userDao.findUser(uid);
    }
    //根据用户名返回用户id
    @Override
    public int getUserId(String uname){return userDao.getUserId(uname);}

    //添加笔记
    @Override
    public void addNote(int aid, String nname, String content){
        noteDao.addNote(aid,nname,content);
    }
    //返回用户所有笔记
    @Override
    public List<Note> getAllNotes(int uid){
        return noteDao.getAllNotes(uid);
    }
    //删除笔记
    @Override
    public void delNote(int nid){ noteDao.delNote(nid);}
    //查找用户笔记
    @Override
    public boolean findNote(int uid, String nname){return noteDao.findNote(uid,nname);}
    //修改用户笔记
    @Override
    public void editNode(int uid, String nname,String content){
        noteDao.changeVer(uid,nname);
        noteDao.addNote(uid,nname,content);
    }
    //返回笔记所有版本
    @Override
    public List<Note> findNotes(int uid, String nname){
        return noteDao.findNotes(uid,nname);
    }
    //返回笔记信息
    @Override
    public Note getNoteInfo(int nid){return noteDao.getNoteInfo(nid);}
    //返回笔记ID
    @Override
    public int getNoteId(int uid, String nname){return noteDao.getNoteId(uid, nname);}
    //返回用户接受的所有笔记
    @Override
    public List<Note> getReceivedNote(int uid){return noteDao.getReceivedNote(uid);}
    //发送笔记
    @Override
    public void sendNote(int uid, int nid){sendDao.sendNote(uid,nid);}
}

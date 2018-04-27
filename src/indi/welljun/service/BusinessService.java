package indi.welljun.service;

import indi.welljun.domain.Note;
import indi.welljun.domain.User;
import indi.welljun.exception.UserExistException;

import java.util.List;

public interface BusinessService {
    /*User操作*/
    void register(User user) throws UserExistException; //用户注册业务
    User userLogin(String uname, String password); //用户登录业务
    User findUser(int uid); //查找用户
    int getUserId(String uname); //返回用户id
    /*Note操作*/
    void addNote(int aid, String nname, String content); //添加笔记
    List<Note> getAllNotes(int uid); //返回该用户所有笔记
    void delNote(int nid); //删除用户笔记
    boolean findNote(int uid, String nname); //查找用户笔记
    void editNode(int uid, String nname,String content); //修改用户笔记
    List<Note> findNotes(int uid, String nname); //返回笔记所有版本
    Note getNoteInfo(int nid); //返回笔记信息
    int getNoteId(int uid, String nname); //返回笔记ID
    List<Note> getReceivedNote(int uid); //返回用户接收的所有笔记
    /*发送层操作*/
    void sendNote(int uid, int nid); //发送笔记
}

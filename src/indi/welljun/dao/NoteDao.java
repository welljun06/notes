package indi.welljun.dao;

import indi.welljun.domain.Note;

import java.util.List;

public interface NoteDao {
    void addNote(int aid, String nname, String content); //创建笔记
    List<Note> getAllNotes(int uid); //返回用户所有笔记
    void delNote(int nid); //删除用户笔记
    boolean findNote(int uid, String nname); //判断用户是否已经创建该笔记
    void changeVer(int uid, String nname); //更新笔记版本
    List<Note> findNotes(int uid, String nname); //返回笔记所有版本
    Note getNoteInfo(int nid); //返回笔记信息
    int getNoteId(int uid, String nname); //返回笔记ID
    List<Note> getReceivedNote(int uid); //返回用户收到的所有笔记
}

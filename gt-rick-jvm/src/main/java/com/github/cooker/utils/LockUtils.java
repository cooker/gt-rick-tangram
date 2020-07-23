package com.github.cooker.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * grant
 * 21/7/2020 10:25 上午
 * 描述：
 */
public class LockUtils {

    public static final TFileLock tryLockFile(String No){
        FileLock fileLock = null;
        FileChannel lock = null;
        try {
            lock = new FileOutputStream("./lock/"+No+"").getChannel();
            fileLock = lock.tryLock(0, 100, false);
        } catch (Exception e) { }
        return new TFileLock(fileLock, lock, No);
    }

    public static final void unLockFile(TFileLock lock){
        if (lock.fileLock != null) {
//            synchronized (LockUtils.class){
                try {
                    lock.fileLock.release();
                } catch (IOException e) {
                e.printStackTrace();
                }

                try {
                    lock.lock.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    Files.delete(Paths.get("./lock/"+lock.No));
                } catch (IOException e) {
                e.printStackTrace();
                }
//            }
        }else if (lock.lock != null){
            try {
                lock.lock.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class TFileLock{
        FileLock fileLock;
        FileChannel lock;
        String No;

        public TFileLock(FileLock fileLock, FileChannel lock, String no) {
            this.fileLock = fileLock;
            this.lock = lock;
            No = no;
        }

        public FileLock getFileLock() {
            return fileLock;
        }

        public FileChannel getLock() {
            return lock;
        }

        public String getNo() {
            return No;
        }

        public boolean isVaild(){
            return fileLock != null;
        }
    }

}

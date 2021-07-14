package com.highy.modules.plot.service;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.rosuda.JRI.RMainLoopCallbacks;
import org.rosuda.JRI.Rengine;

/**
 * Rengine单例类
 * @author jchaoy high哥工作室
 * 20200906
 */
public class SingleRengineBuilder {



    private static Object _lock = new Object();
    //私有的静态内部类 构造类时是原子操作



        private static Rengine rengine = null;

        final static private SingleRengineBuilder self = new SingleRengineBuilder();


        public static SingleRengineBuilder getBuilder(){
            return self;
        }

        private   String[] args = null;
        private   String[] otherArgs = null;
        private   RMainLoopCallbacks rMainLoopCallbacks = null;
        private   boolean var2 = false;


        public  SingleRengineBuilder args(String[] arguments) {
            args=arguments;
            return self;
        }

        public   SingleRengineBuilder addArgs(String[] arguments) {
            otherArgs=arguments;
            return self;
        }

        public   SingleRengineBuilder RMainLoopCallbacks(RMainLoopCallbacks r_MainLoopCallbacks) {
            rMainLoopCallbacks =r_MainLoopCallbacks;
            return self;
        }

        public   SingleRengineBuilder var2(boolean b) {
             var2 = b;
            return self;
        }


        public   Rengine GetInstance() {
            if (rengine == null) {
                synchronized (_lock) {
                    if (rengine == null) {
                        rengine = newRengine();
                    }
                }
            }
            return rengine;
        }


        public Rengine GetInstanceDefault() {
            if (rengine == null) {
                synchronized (_lock) {
                    if (rengine == null) {
                        rengine = newRengineDefault();
                    }
                }
            }
            return rengine;
        }

        private static Rengine newRengineDefault() {
            return new Rengine(new String[]{"--quiet", "--no-restore", "--no-save"}, false, new TextConsole());
        };

        private   Rengine newRengine() {
            int arrLen1 = args.length;
            int arrlen2 = otherArgs.length;
            if(otherArgs!=null && arrlen2>0){
                args = Arrays.copyOf(args, arrLen1 + arrlen2);
                System.arraycopy(otherArgs, 0, args, arrLen1, arrlen2);
            }


            return new Rengine(args, var2, new TextConsole());
//            return new Rengine(new String[]{"--quiet", "--no-restore", "--no-save"}, false, new TextConsole()); //,"--args abc"
        }















//        public static Rengine getRegineInstance() {
//        return SingleRengineIn.singleRengine;
//    }











    private SingleRengineBuilder() {}

    private static class TextConsole implements RMainLoopCallbacks {
        @Override
        public void rWriteConsole(Rengine re, String text, int oType) {
            System.out.print(text);
        }
        @Override
        public void rBusy(Rengine re, int which) {
            System.out.println("rBusy(" + which + ")");
        }
        @Override
        public String rReadConsole(Rengine re, String prompt, int addToHistory) {
            System.out.print(prompt);
            try {
                BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
                String s = br.readLine();
                return (s == null || s.length() == 0) ? s : s +"\n";
            }catch (Exception e) {
                System.out.println("jriReadConsole exception: " + e.getMessage());
            }
            return null;
        }
        @Override
        public void rShowMessage(Rengine re, String message) {
            System.out.println("rShowMessage \"" + message + "\"");
        }
        @Override
        public void rFlushConsole(Rengine re) {
        }
        @Override
        public void rLoadHistory(Rengine re, String filename) {
        }
        @Override
        public void rSaveHistory(Rengine re, String filename) {
        }

        @SuppressWarnings("deprecation")
        @Override
        public String rChooseFile(Rengine re, int newFile) {
            FileDialog fd =new FileDialog(new Frame(), (newFile == 0) ?"Select a file" :"Select a new file",
                    (newFile ==0) ? FileDialog.LOAD : FileDialog.SAVE);
            fd.show();
            String res =null;
            if(fd.getDirectory() != null)
                res = fd.getDirectory();
            if(fd.getFile() != null)
                res = (res ==null) ? fd.getFile() : (res + fd.getFile());
            return res;
        }
    }

}

package com.yinww.demo.springboot2.demo012.controller;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinTool {
    public static void main(String[] args) {
        try {
            PinyinTool pinyinTool = new PinyinTool();
            String pinYinSub = pinyinTool.toPinYinSub("河北省");
            System.out.println(pinYinSub);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    HanyuPinyinOutputFormat format = null;
    public static enum Type {
        UPPERCASE,              //全部大写
        LOWERCASE,              //全部小写
        FIRSTUPPER              //首字母大写
    }
 
    public PinyinTool(){
        format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    }
 
    public String toPinYin(String str) throws BadHanyuPinyinOutputFormatCombination{
        return toPinYin(str, "", Type.LOWERCASE);
    }
 
    public String toPinYin(String str,String spera) throws BadHanyuPinyinOutputFormatCombination{
        return toPinYin(str, spera, Type.LOWERCASE);
    }
 
    /**
     * 将str转换成拼音，如果不是汉字或者没有对应的拼音，则不作转换
     * 如： 明天 转换成 MINGTIAN
     * @param str
     * @param spera
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    public String toPinYin(String str, String spera, Type type) throws BadHanyuPinyinOutputFormatCombination {
        if(str == null || str.trim().length()==0)
            return "";
        if(type == Type.UPPERCASE)
            format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        else
            format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
 
        String py = "";
        String temp = "";
        String[] t;
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            if((int)c <= 128)
                py += c;
            else{
                t = PinyinHelper.toHanyuPinyinStringArray(c, format);
                if(t == null)
                    py += c;
                else{
                    try {
                        temp = t[0];
                    } catch (Exception e) {
                        System.out.println("error==" + str);
                    }
                    if(type == Type.FIRSTUPPER)
                        temp = t[0].toUpperCase().charAt(0)+temp.substring(1);
                    py += temp+(i==str.length()-1?"":spera);
                }
            }
        }
        return py.trim();
    }

    public String toPinYinShort(String str) throws BadHanyuPinyinOutputFormatCombination {
        return toPinYinShort(str, "", Type.LOWERCASE);
    }

    public String toPinYinShort(String str, String spera, Type type) throws BadHanyuPinyinOutputFormatCombination {
        if(str == null || str.trim().length()==0)
            return "";
        if(type == Type.UPPERCASE)
            format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        else
            format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
 
        String py = "";
        String temp = "";
        String[] t;
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            if((int)c <= 128)
                py += c;
            else{
                t = PinyinHelper.toHanyuPinyinStringArray(c, format);
                if(t == null)
                    py += c;
                else{
                    try {
                        temp = t[0];
                    } catch (Exception e) {
                        System.out.println("short error==" + str);
                    }
                    if(type == Type.FIRSTUPPER) {
                        temp = t[0].toUpperCase().charAt(0)+temp.substring(1);
                    }
                    py += temp.substring(0, 1)+(i==str.length()-1?"":spera);
                }
            }
        }
        return py.trim();
    }

    public String toPinYinSub(String str) throws BadHanyuPinyinOutputFormatCombination {
        if(str == null || str.trim().length()==0 || str.trim().length()==1)
            return "";
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        str = str.substring(0, str.trim().length()-1);
 
        String py = "";
        String temp = "";
        String[] t;
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            if((int)c <= 128)
                py += c;
            else{
                t = PinyinHelper.toHanyuPinyinStringArray(c, format);
                if(t == null)
                    py += c;
                else{
                    try {
                        temp = t[0];
                    } catch (Exception e) {
                        System.out.println("error==" + str);
                    }
                    temp = t[0].toUpperCase().charAt(0)+temp.substring(1);
                    py += temp;
                }
            }
        }
        return py.trim();
    }
}
